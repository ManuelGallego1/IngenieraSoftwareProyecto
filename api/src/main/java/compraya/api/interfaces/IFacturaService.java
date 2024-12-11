package compraya.api.interfaces;

import org.springframework.http.ResponseEntity;

import compraya.api.models.FacturaModel;

public interface IFacturaService {
    ResponseEntity<?> get();
    ResponseEntity<?> getOne(Long id);
    ResponseEntity<?> post(FacturaModel factura);
    ResponseEntity<?> put(FacturaModel factura, Long id);
    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> generateFacturaXML(Long id);
}

