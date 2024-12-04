package compraya.api.controllers;

import compraya.api.models.CategoriaModel;
import compraya.api.validator.ValidationService;
import compraya.api.notification.NotificationService;
import compraya.api.log.ActivityLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import compraya.api.interfaces.ICrudService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController extends BaseController<CategoriaModel> {

    private final ICrudService<CategoriaModel> categoriaService;
    private final ValidationService validationService;
    private final NotificationService notificationService;
    private final ActivityLogService activityLogService;

    public CategoriaController(ICrudService<CategoriaModel> categoriaService,
                               ValidationService validationService,
                               NotificationService notificationService,
                               ActivityLogService activityLogService) {
        this.categoriaService = categoriaService;
        this.validationService = validationService;
        this.notificationService = notificationService;
        this.activityLogService = activityLogService;
    }

    @Override
    protected ICrudService<CategoriaModel> getService() {
        return categoriaService;
    }

    //Fabricación pura
    public void createCategoria(CategoriaModel categoria) {
        if (validationService.validateCategoria(categoria)) {
            categoriaService.post(categoria);
            activityLogService.logActivity("Categoría creada: " + categoria.getNombre());
            notificationService.sendEmail("admin@example.com", "Nueva Categoría Creada", "Se ha creado la categoría: " + categoria.getNombre());
        } else {
            activityLogService.logActivity("Error al crear categoría: datos inválidos");
        }
    }
}