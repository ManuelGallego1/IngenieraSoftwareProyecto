package compraya.api.services;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import compraya.api.interfaces.IInventarioService;
import compraya.api.models.InventarioModel;
import compraya.api.repositories.IInventarioRepository;

@Service
public class InventarioService implements IInventarioService {

    private final IInventarioRepository InventarioRepository;

    @Autowired
    public InventarioService(IInventarioRepository InventarioRepository) {
        this.InventarioRepository = InventarioRepository;
=======
import compraya.api.models.InventarioModel;
import compraya.api.repositories.IInventarioRepository;
import compraya.api.interfaces.ICrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InventarioService implements ICrudService<InventarioModel> {

    private final IInventarioRepository inventarioRepository;

    public InventarioService(IInventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
>>>>>>> 460c4a014c8dcc8af193b8d2beea3b34c8036ddf
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
}