package compraya.api.interfaces;

import org.springframework.http.ResponseEntity;

import compraya.api.models.DetalleFacturaModel;

public interface IDetalleFacturaService {
    ResponseEntity<?> get();
    ResponseEntity<?> getOne(Long id);
    ResponseEntity<?> post(DetalleFacturaModel detalleFactura);
    ResponseEntity<?> put(DetalleFacturaModel detalleFactura, Long id);
    ResponseEntity<?> delete(Long id);
}
