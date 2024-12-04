package compraya.api.facade;

import compraya.api.models.DetalleFacturaModel;
import compraya.api.services.DetalleFacturaService;
import compraya.api.notification.NotificationService;
import compraya.api.log.ActivityLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DetalleFacturaFacade {

    private final DetalleFacturaService detalleFacturaService;
    private final NotificationService notificationService;
    private final ActivityLogService activityLogService;

    public DetalleFacturaFacade(DetalleFacturaService detalleFacturaService,
                                NotificationService notificationService,
                                ActivityLogService activityLogService) {
        this.detalleFacturaService = detalleFacturaService;
        this.notificationService = notificationService;
        this.activityLogService = activityLogService;
    }

    public ResponseEntity<?> createDetalleFactura(DetalleFacturaModel detalleFactura) {
        ResponseEntity<?> response = detalleFacturaService.post(detalleFactura);
        if (response.getStatusCode().is2xxSuccessful()) {
            activityLogService.logActivity("Detalle de factura creado: " + detalleFactura.getId());
            notificationService.sendEmail("admin@example.com", "Nuevo Detalle de Factura Creado", "Se ha creado un nuevo detalle de factura con ID: " + detalleFactura.getId());
        } else {
            activityLogService.logActivity("Error al crear detalle de factura: " + detalleFactura.getId());
        }
        return response;
    }

    public ResponseEntity<?> getDetallesFactura() {
        return detalleFacturaService.get();
    }

    public ResponseEntity<?> getDetalleFacturaById(Long id) {
        return detalleFacturaService.getOne(id);
    }

    public ResponseEntity<?> updateDetalleFactura(DetalleFacturaModel detalleFactura, Long id) {
        ResponseEntity<?> response = detalleFacturaService.put(detalleFactura, id);
        if (response.getStatusCode().is2xxSuccessful()) {
            activityLogService.logActivity("Detalle de factura actualizado: " + detalleFactura.getId());
            notificationService.sendEmail("admin@example.com", "Detalle de Factura Actualizado", "Se ha actualizado el detalle de factura con ID: " + detalleFactura.getId());
        } else {
            activityLogService.logActivity("Error al actualizar detalle de factura: " + detalleFactura.getId());
        }
        return response;
    }

    public ResponseEntity<?> deleteDetalleFactura(Long id) {
        ResponseEntity<?> response = detalleFacturaService.delete(id);
        if (response.getStatusCode().is2xxSuccessful()) {
            activityLogService.logActivity("Detalle de factura eliminado con ID: " + id);
            notificationService.sendEmail("admin@example.com", "Detalle de Factura Eliminado", "Se ha eliminado el detalle de factura con ID: " + id);
        } else {
            activityLogService.logActivity("Error al eliminar detalle de factura con ID: " + id);
        }
        return response;
    }
}