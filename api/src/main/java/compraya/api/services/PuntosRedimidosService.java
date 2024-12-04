
package compraya.api.services;

import org.springframework.http.ResponseEntity;
import compraya.api.interfaces.IPuntosRedimidosService;
import compraya.api.models.PuntosRedimidosModel;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import compraya.api.repositories.IPuntosRedimidosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PuntosRedimidosService implements IPuntosRedimidosService {

    private final IPuntosRedimidosRepository puntosRedimidosRepository;
    private static final Logger logger = LoggerFactory.getLogger(PuntosRedimidosService.class);

    @Autowired
    public PuntosRedimidosService(IPuntosRedimidosRepository puntosRedimidosRepository) {
        this.puntosRedimidosRepository = puntosRedimidosRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.ok((ArrayList<PuntosRedimidosModel>) puntosRedimidosRepository.findAll());
        } catch (Exception e) {
            logger.error("Error al obtener los puntos redimidos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al obtener los puntos redimidos.\"}");
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            Optional<PuntosRedimidosModel> puntos = puntosRedimidosRepository.findById(id);
            if (puntos.isPresent()) {
                return ResponseEntity.ok(puntos.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
            }
        } catch (Exception e) {
            logger.error("Error al obtener el punto ganado con id " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al obtener el punto redimido.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(PuntosRedimidosModel puntos) {
        try {
            PuntosRedimidosModel savedPuntos = puntosRedimidosRepository.save(puntos);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPuntos);
        } catch (Exception e) {
            logger.error("Error al guardar los puntos redimidos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al guardar los puntos.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(PuntosRedimidosModel puntos, Long id) {
        if (puntosRedimidosRepository.existsById(id)) {
            puntos.setId(id);
            try {
                PuntosRedimidosModel updatedPuntos = puntosRedimidosRepository.save(puntos);
                return ResponseEntity.ok(updatedPuntos);
            } catch (Exception e) {
                logger.error("Error al actualizar los puntos redimidos", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al actualizar los puntos.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (puntosRedimidosRepository.existsById(id)) {
            try {
                puntosRedimidosRepository.deleteById(id);
                return ResponseEntity.ok("{\"message\": \"Puntos eliminados.\"}");
            } catch (Exception e) {
                logger.error("Error al eliminar los puntos redimidos", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al eliminar los puntos.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
        }
    }
}