package compraya.api.services;
import compraya.api.models.InventarioModel;
import compraya.api.models.ProductoModel;
import compraya.api.repositories.IInventarioRepository;
import compraya.api.repositories.IProductoRepository;
import compraya.api.interfaces.ICrudService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InventarioService implements ICrudService<InventarioModel> {

    private final IInventarioRepository inventarioRepository;
    @Autowired
    private IProductoRepository productoRepository;

    public InventarioService(IInventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().body(inventarioRepository.findAll());
    }

    @Override
    public ResponseEntity<?> post(InventarioModel inventarioModel) {
        try {
            InventarioModel savedInventario = inventarioRepository.save(inventarioModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedInventario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al guardar los inventarios.\"}");
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            InventarioModel inventario = inventarioRepository.findById(id).orElse(null);
            return inventario != null ? ResponseEntity.ok(inventario)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Inventario no encontrado.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al obtener el inventario.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(InventarioModel inventarioModel, Long id) {
        InventarioModel inventario = inventarioRepository.findById(id).orElse(null);
        if (inventario != null) {
            inventario.setEntrada(inventarioModel.getEntrada());
            inventario.setSalida(inventarioModel.getSalida());
            inventario.setReferenciaCompra(inventarioModel.getReferenciaCompra());
            inventario.setProducto(inventarioModel.getProducto());
            inventarioRepository.save(inventario);
            return ResponseEntity.ok(inventario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Inventario no encontrado.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return inventarioRepository.findById(id)
                .map(inventario -> {
                    inventarioRepository.delete(inventario);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"error\": \"Inventario no encontrado.\"}"));
    }

    public ResponseEntity<?> registrarEntrada(Long productoId, int entrada, String referenciaCompra) {
        ProductoModel producto = productoRepository.findById(productoId).orElse(null);
        if (producto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Producto no encontrado.\"}");
        }

        // Crear registro de entrada
        InventarioModel inventario = new InventarioModel();
        inventario.setProducto(producto);
        inventario.setEntrada(entrada);
        inventario.setReferenciaCompra(referenciaCompra);

        inventarioRepository.save(inventario);
        return ResponseEntity.ok("{\"message\": \"Entrada registrada correctamente.\"}");
    }

    // Registrar salida de productos del inventario
    public ResponseEntity<?> registrarSalida(Long productoId, int salida, String referenciaCompra) {
        ProductoModel producto = productoRepository.findById(productoId).orElse(null);
        if (producto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Producto no encontrado.\"}");
        }

        // Validar que existan suficientes entradas para la salida
        int entradasTotales = inventarioRepository.sumEntradasByProductoId(productoId);
        int salidasTotales = inventarioRepository.sumSalidasByProductoId(productoId);
        int inventarioActual = entradasTotales - salidasTotales;

        if (inventarioActual < salida) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Cantidad insuficiente en inventario.\"}");
        }

        // Crear registro de salida
        InventarioModel inventario = new InventarioModel();
        inventario.setProducto(producto);
        inventario.setSalida(salida);
        inventario.setReferenciaCompra(referenciaCompra);

        inventarioRepository.save(inventario);
        return ResponseEntity.ok("{\"message\": \"Salida registrada correctamente.\"}");
    }

    // Obtener inventario actual de un producto
    public ResponseEntity<?> obtenerInventario(Long productoId) {
        ProductoModel producto = productoRepository.findById(productoId).orElse(null);
        if (producto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Producto no encontrado.\"}");
        }

        int entradasTotales = inventarioRepository.sumEntradasByProductoId(productoId);
        int salidasTotales = inventarioRepository.sumSalidasByProductoId(productoId);
        int inventarioActual = entradasTotales - salidasTotales;

        return ResponseEntity.ok("{\"producto\": \"" + producto.getNombre() + "\", \"inventario\": " + inventarioActual + "}");
    }
}