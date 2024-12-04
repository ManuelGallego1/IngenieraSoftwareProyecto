package compraya.api.mediator;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import compraya.api.mediator.interfaces.MetodoPagoMediator;
import compraya.api.models.MetodoPagoModel;
import compraya.api.services.MetodoPagoService;

@Component("metodoPagoMediatorImpl")
public class MetodoPagoMediatorImpl implements MetodoPagoMediator {
    private final MetodoPagoService metodoPagoService;

    public MetodoPagoMediatorImpl(MetodoPagoService metodoPagoService) {
        this.metodoPagoService = metodoPagoService;
    }

    @Override
    public ResponseEntity<?> getMetodosPago() {
        return metodoPagoService.get();
    }

    @Override
    public ResponseEntity<?> getMetodoPagoById(Long id) {
        return metodoPagoService.getOne(id);
    }

    @Override
    public ResponseEntity<?> createMetodoPago(MetodoPagoModel metodoPago) {
        return metodoPagoService.post(metodoPago);
    }

    @Override
    public ResponseEntity<?> updateMetodoPago(Long id, MetodoPagoModel metodoPago) {
        return metodoPagoService.put(metodoPago, id);
    }

    @Override
    public ResponseEntity<?> deleteMetodoPago(Long id) {
        return metodoPagoService.delete(id);
    }
}