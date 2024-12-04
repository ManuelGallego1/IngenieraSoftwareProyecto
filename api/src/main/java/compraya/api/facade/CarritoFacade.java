package compraya.api.facade;

import compraya.api.models.CarritoModel;
import compraya.api.services.CarritoService;
import compraya.api.notification.NotificationService;
import compraya.api.log.ActivityLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CarritoFacade {

    private final CarritoService carritoService;
    private final NotificationService notificationService;
    private final ActivityLogService activityLogService;

    public CarritoFacade(CarritoService carritoService,
                         NotificationService notificationService,
                         ActivityLogService activityLogService) {
        this.carritoService = carritoService;
        this.notificationService = notificationService;
        this.activityLogService = activityLogService;
    }

    public ResponseEntity<?> createCarrito(CarritoModel carrito) {
        ResponseEntity<?> response = carritoService.post(carrito);
        if (response.getStatusCode().is2xxSuccessful()) {
            activityLogService.logActivity("Carrito creado para usuario: " + carrito.getUsuario().getNombre());
            notificationService.sendEmail("admin@example.com", "Nuevo Carrito Creado", "Se ha creado un nuevo carrito para el usuario: " + carrito.getUsuario().getNombre());
        } else {
            activityLogService.logActivity("Error al crear carrito para usuario: " + carrito.getUsuario().getNombre());
        }
        return response;
    }

    public ResponseEntity<?> getCarritos() {
        return carritoService.get();
    }

    public ResponseEntity<?> getCarritoById(Long id) {
        return carritoService.getOne(id);
    }

    public ResponseEntity<?> updateCarrito(CarritoModel carrito, Long id) {
        ResponseEntity<?> response = carritoService.put(carrito, id);
        if (response.getStatusCode().is2xxSuccessful()) {
            activityLogService.logActivity("Carrito actualizado para usuario: " + carrito.getUsuario().getNombre());
            notificationService.sendEmail("admin@example.com", "Carrito Actualizado", "Se ha actualizado el carrito para el usuario: " + carrito.getUsuario().getNombre());
        } else {
            activityLogService.logActivity("Error al actualizar carrito para usuario: " + carrito.getUsuario().getNombre());
        }
        return response;
    }

    public ResponseEntity<?> deleteCarrito(Long id) {
        ResponseEntity<?> response = carritoService.delete(id);
        if (response.getStatusCode().is2xxSuccessful()) {
            activityLogService.logActivity("Carrito eliminado con ID: " + id);
            notificationService.sendEmail("admin@example.com", "Carrito Eliminado", "Se ha eliminado el carrito con ID: " + id);
        } else {
            activityLogService.logActivity("Error al eliminar carrito con ID: " + id);
        }
        return response;
    }
}