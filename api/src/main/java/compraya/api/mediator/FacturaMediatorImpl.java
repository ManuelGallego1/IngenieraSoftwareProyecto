package compraya.api.mediator;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import compraya.api.models.FacturaModel;
import compraya.api.services.FacturaService;
import compraya.api.mediator.interfaces.FacturaMediator;

@Component
public class FacturaMediatorImpl implements FacturaMediator {
    private final FacturaService facturaService;

    public FacturaMediatorImpl(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @Override
    public ResponseEntity<?> getFacturas() {
        return facturaService.get();
    }

    @Override
    public ResponseEntity<?> createFactura(FacturaModel factura) {
        return facturaService.post(factura);
    }

    @Override
    public ResponseEntity<?> getFacturaById(Long id) {
        return facturaService.getOne(id);
    }

    @Override
    public ResponseEntity<?> updateFactura(FacturaModel factura, Long id) {
        return facturaService.put(factura, id);
    }

    @Override
    public ResponseEntity<?> deleteFactura(Long id) {
        return facturaService.delete(id);
    }

    @Override
    public ResponseEntity<?> generateFacturaXML(Long id) {
        return facturaService.generateFacturaXML(id);
    }
}