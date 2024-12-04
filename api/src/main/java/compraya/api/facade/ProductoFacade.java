package compraya.api.facade;

import compraya.api.models.ProductoModel;
import compraya.api.services.ProductosService;
import compraya.api.notification.NotificationService;
import compraya.api.log.ActivityLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductoFacade {

    private final ProductosService productosService;
    private final NotificationService notificationService;
    private final ActivityLogService activityLogService;

    public ProductoFacade(ProductosService productosService,
                          NotificationService notificationService,
                          ActivityLogService activityLogService) {
        this.productosService = productosService;
        this.notificationService = notificationService;
        this.activityLogService = activityLogService;
    }

    public ResponseEntity<?> createProducto(ProductoModel producto) {
        ResponseEntity<?> response = productosService.post(producto);
        if (response.getStatusCode().is2xxSuccessful()) {
            activityLogService.logActivity("Producto creado: " + producto.getNombre());
            notificationService.sendEmail("admin@example.com", "Nuevo Producto Creado", "Se ha creado el producto: " + producto.getNombre());
        } else {
            activityLogService.logActivity("Error al crear producto: " + producto.getNombre());
        }
        return response;
    }

    public ResponseEntity<?> getProductos() {
        return productosService.get();
    }

    public ResponseEntity<?> getProductoById(Long id) {
        return productosService.getOne(id);
    }

    public ResponseEntity<?> updateProducto(ProductoModel producto, Long id) {
        ResponseEntity<?> response = productosService.put(producto, id);
        if (response.getStatusCode().is2xxSuccessful()) {
            activityLogService.logActivity("Producto actualizado: " + producto.getNombre());
            notificationService.sendEmail("admin@example.com", "Producto Actualizado", "Se ha actualizado el producto: " + producto.getNombre());
        } else {
            activityLogService.logActivity("Error al actualizar producto: " + producto.getNombre());
        }
        return response;
    }

    public ResponseEntity<?> deleteProducto(Long id) {
        ResponseEntity<?> response = productosService.delete(id);
        if (response.getStatusCode().is2xxSuccessful()) {
            activityLogService.logActivity("Producto eliminado: " + id);
            notificationService.sendEmail("admin@example.com", "Producto Eliminado", "Se ha eliminado el producto con ID: " + id);
        } else {
            activityLogService.logActivity("Error al eliminar producto con ID: " + id);
        }
        return response;
    }
}