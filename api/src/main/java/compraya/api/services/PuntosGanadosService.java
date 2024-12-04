package compraya.api.services;

import org.springframework.http.ResponseEntity;
import compraya.api.interfaces.IPuntosGanadosService;
import compraya.api.models.PuntosGanadosModel;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import compraya.api.repositories.IPuntosGanadosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PuntosGanadosService implements IPuntosGanadosService {

    private final IPuntosGanadosRepository puntosGanadosRepository;
    private static final Logger logger = LoggerFactory.getLogger(PuntosGanadosService.class);

    public PuntosGanadosService(IPuntosGanadosRepository puntosGanadosRepository) {
        this.puntosGanadosRepository = puntosGanadosRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.ok((ArrayList<PuntosGanadosModel>) puntosGanadosRepository.findAll());
        } catch (Exception e) {
            logger.error("Error al obtener los puntos ganados", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al obtener los puntos ganados.\"}");
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            Optional<PuntosGanadosModel> puntos = puntosGanadosRepository.findById(id);
            if (puntos.isPresent()) {
                return ResponseEntity.ok(puntos.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
            }
        } catch (Exception e) {
            logger.error("Error al obtener el punto ganado con id " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al obtener el punto ganado.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(PuntosGanadosModel puntos) {
        try {
            PuntosGanadosModel savedPuntos = puntosGanadosRepository.save(puntos);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPuntos);
        } catch (Exception e) {
            logger.error("Error al guardar los puntos ganados", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al guardar los puntos.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(PuntosGanadosModel puntos, Long id) {
        if (puntosGanadosRepository.existsById(id)) {
            puntos.setId(id);
            try {
                PuntosGanadosModel updatedPuntos = puntosGanadosRepository.save(puntos);
                return ResponseEntity.ok(updatedPuntos);
            } catch (Exception e) {
                logger.error("Error al actualizar los puntos ganados", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al actualizar los puntos.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (puntosGanadosRepository.existsById(id)) {
            try {
                puntosGanadosRepository.deleteById(id);
                return ResponseEntity.ok("{\"message\": \"Puntos eliminados.\"}");
            } catch (Exception e) {
                logger.error("Error al eliminar los puntos ganados", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al eliminar los puntos.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
        }
    }
}