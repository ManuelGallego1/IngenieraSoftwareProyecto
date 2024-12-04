package compraya.api.services;

import compraya.api.repositories.ICategoriaRepository;
import compraya.api.interfaces.ICrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import compraya.api.models.CategoriaModel;
import org.springframework.http.HttpStatus;
import compraya.api.validator.ValidationService;
import compraya.api.notification.NotificationService;
import compraya.api.log.ActivityLogService;

@Service
public class CategoriaService implements ICrudService<CategoriaModel> {

    private final ICategoriaRepository categoriaRepository;
    private final ValidationService validationService;
    private final ActivityLogService activityLogService;
    private final NotificationService notificationService;
    private static final Logger logger = LoggerFactory.getLogger(CategoriaService.class);

    public CategoriaService(ICategoriaRepository categoriaRepository,
                            ValidationService validationService,
                            ActivityLogService activityLogService,
                            NotificationService notificationService) {
        this.categoriaRepository = categoriaRepository;
        this.validationService = validationService;
        this.activityLogService = activityLogService;
        this.notificationService = notificationService;
    }

    @Override
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.ok().body(categoriaRepository.findAll());
        } catch (Exception e) {
            logger.error("Error al obtener las categorías", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al obtener las categorías.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(CategoriaModel categoriaModel) {
        try {
            if (validationService.validateCategoria(categoriaModel)) {
                CategoriaModel savedCategoria = categoriaRepository.save(categoriaModel);
                activityLogService.logActivity("Categoría creada: " + categoriaModel.getNombre());
                notificationService.sendEmail("admin@example.com", "Nueva Categoría Creada", "Se ha creado la categoría: " + categoriaModel.getNombre());
                return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoria);
            } else {
                activityLogService.logActivity("Error al crear categoría: datos inválidos");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Datos inválidos.\"}");
            }
        } catch (Exception e) {
            logger.error("Error al guardar la categoría", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al guardar la categoría.\"}");
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            CategoriaModel categoria = categoriaRepository.findById(id).orElse(null);
            return categoria != null ? ResponseEntity.ok(categoria)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Categoría no encontrada.\"}");
        } catch (Exception e) {
            logger.error("Error al obtener la categoría con id " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al obtener la categoría.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(CategoriaModel categoriaModel, Long id) {
        CategoriaModel categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria != null) {
            categoria.setNombre(categoriaModel.getNombre());
            categoriaRepository.save(categoria);
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Categoría no encontrada.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoriaRepository.delete(categoria);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"error\": \"Categoría no encontrada.\"}"));
    }
}