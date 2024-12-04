package compraya.api.mediator.interfaces;

import org.springframework.http.ResponseEntity;
import compraya.api.models.MetodoPagoModel;

public interface MetodoPagoMediator {
    ResponseEntity<?> getMetodosPago();
    ResponseEntity<?> getMetodoPagoById(Long id);
    ResponseEntity<?> createMetodoPago(MetodoPagoModel metodoPago);
    ResponseEntity<?> updateMetodoPago(Long id, MetodoPagoModel metodoPago);
    ResponseEntity<?> deleteMetodoPago(Long id);
}