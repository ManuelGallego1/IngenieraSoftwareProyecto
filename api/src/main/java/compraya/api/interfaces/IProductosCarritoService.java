package compraya.api.interfaces;

import org.springframework.http.ResponseEntity;

import compraya.api.models.ProductosCarritoModel;

public interface IProductosCarritoService {
    ResponseEntity<?> get();
    ResponseEntity<?> getOne(Long id);
    ResponseEntity<?> post(ProductosCarritoModel productosCarrito);
    ResponseEntity<?> put(ProductosCarritoModel productosCarrito, Long id);
    ResponseEntity<?> delete(Long id);
}
