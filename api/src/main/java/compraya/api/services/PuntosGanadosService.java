package compraya.api.services;

import compraya.api.models.PuntosGanadosModel;
import compraya.api.repositories.IPuntosGanadosRepository;
import compraya.api.interfaces.ICrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PuntosGanadosService implements ICrudService<PuntosGanadosModel> {

    private final IPuntosGanadosRepository puntosGanadosRepository;
    private static final Logger logger = LoggerFactory.getLogger(PuntosGanadosService.class);

    public PuntosGanadosService(IPuntosGanadosRepository puntosGanadosRepository) {
        this.puntosGanadosRepository = puntosGanadosRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.ok().body(puntosGanadosRepository.findAll());
        } catch (Exception e) {
            logger.error("Error al obtener los puntos ganados", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al obtener los puntos ganados.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(PuntosGanadosModel puntosGanadosModel) {
        try {
            PuntosGanadosModel savedPuntos = puntosGanadosRepository.save(puntosGanadosModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPuntos);
        } catch (Exception e) {
            logger.error("Error al guardar los puntos ganados", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al guardar los puntos.\"}");
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            PuntosGanadosModel puntos = puntosGanadosRepository.findById(id).orElse(null);
            return puntos != null ? ResponseEntity.ok(puntos)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
        } catch (Exception e) {
            logger.error("Error al obtener el punto ganado con id " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al obtener el punto ganado.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(PuntosGanadosModel puntosGanadosModel, Long id) {
        PuntosGanadosModel puntos = puntosGanadosRepository.findById(id).orElse(null);
        if (puntos != null) {
            puntos.setCantidadPuntos(puntosGanadosModel.getCantidadPuntos());
            puntos.setFechaGanancia(puntosGanadosModel.getFechaGanancia());
            puntos.setPuntos(puntosGanadosModel.getPuntos());
            puntos.setMotivo(puntosGanadosModel.getMotivo());
            puntos.setReferencia(puntosGanadosModel.getReferencia());
            puntosGanadosRepository.save(puntos);
            return ResponseEntity.ok(puntos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return puntosGanadosRepository.findById(id)
                .map(puntos -> {
                    puntosGanadosRepository.delete(puntos);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"error\": \"Puntos no encontrados.\"}"));
    }
}
