package compraya.api.services;

import compraya.api.models.PuntosModel;
import compraya.api.repositories.IPuntosRepository;
import compraya.api.interfaces.ICrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PuntosService implements ICrudService<PuntosModel> {

    private final IPuntosRepository puntosRepository;
    private static final Logger logger = LoggerFactory.getLogger(PuntosService.class);

    public PuntosService(IPuntosRepository puntosRepository) {
        this.puntosRepository = puntosRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.ok().body(puntosRepository.findAll());
        } catch (Exception e) {
            logger.error("Error al obtener los puntos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al obtener los puntos.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(PuntosModel puntosModel) {
        try {
            PuntosModel savedPuntos = puntosRepository.save(puntosModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPuntos);
        } catch (Exception e) {
            logger.error("Error al guardar los puntos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al guardar los puntos.\"}");
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            PuntosModel puntos = puntosRepository.findById(id).orElse(null);
            return puntos != null ? ResponseEntity.ok(puntos)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
        } catch (Exception e) {
            logger.error("Error al obtener el punto con id " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al obtener el punto.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(PuntosModel puntosModel, Long id) {
        PuntosModel puntos = puntosRepository.findById(id).orElse(null);
        if (puntos != null) {
            puntos.setCantidad(puntosModel.getCantidad());
            puntos.setUsuario(puntosModel.getUsuario());
            puntosRepository.save(puntos);
            return ResponseEntity.ok(puntos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return puntosRepository.findById(id)
                .map(puntos -> {
                    puntosRepository.delete(puntos);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"error\": \"Puntos no encontrados.\"}"));
    }
}