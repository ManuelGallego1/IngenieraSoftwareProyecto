package compraya.api.mediator.interfaces;

import org.springframework.http.ResponseEntity;
import compraya.api.models.FacturaModel;

public interface FacturaMediator {
    ResponseEntity<?> getFacturas();
    ResponseEntity<?> createFactura(FacturaModel factura);
    ResponseEntity<?> getFacturaById(Long id);
    ResponseEntity<?> updateFactura(FacturaModel factura, Long id);
    ResponseEntity<?> deleteFactura(Long id);
    ResponseEntity<?> generateFacturaXML(Long id);
}
