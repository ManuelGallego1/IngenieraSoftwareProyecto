package compraya.api.interfaces;

import org.springframework.http.ResponseEntity;

import compraya.api.models.InventarioModel;

public interface IInventarioService {
    ResponseEntity<?> get();
    ResponseEntity<?> getOne(Long id);
    ResponseEntity<?> post(InventarioModel inventario);
    ResponseEntity<?> put(InventarioModel inventario, Long id);
    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> registrarEntrada(Long id, int entrada, String referenciaCompra);
    ResponseEntity<?> registrarSalida(Long id, int salida, String referenciaVenta);
    ResponseEntity<?> obtenerInventario(Long id);
}
