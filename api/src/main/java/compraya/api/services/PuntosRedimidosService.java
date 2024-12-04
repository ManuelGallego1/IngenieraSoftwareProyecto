package compraya.api.services;

import compraya.api.models.PuntosRedimidosModel;
import compraya.api.repositories.IPuntosRedimidosRepository;
import compraya.api.interfaces.ICrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PuntosRedimidosService implements ICrudService<PuntosRedimidosModel> {

    private final IPuntosRedimidosRepository puntosRedimidosRepository;
    private static final Logger logger = LoggerFactory.getLogger(PuntosRedimidosService.class);

    public PuntosRedimidosService(IPuntosRedimidosRepository puntosRedimidosRepository) {
        this.puntosRedimidosRepository = puntosRedimidosRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.ok().body(puntosRedimidosRepository.findAll());
        } catch (Exception e) {
            logger.error("Error al obtener los puntos redimidos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al obtener los puntos redimidos.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(PuntosRedimidosModel puntosRedimidosModel) {
        try {
            PuntosRedimidosModel savedPuntos = puntosRedimidosRepository.save(puntosRedimidosModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPuntos);
        } catch (Exception e) {
            logger.error("Error al guardar los puntos redimidos", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al guardar los puntos.\"}");
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            PuntosRedimidosModel puntos = puntosRedimidosRepository.findById(id).orElse(null);
            return puntos != null ? ResponseEntity.ok(puntos)
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
        } catch (Exception e) {
            logger.error("Error al obtener el punto redimido con id " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al obtener el punto redimido.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(PuntosRedimidosModel puntosRedimidosModel, Long id) {
        PuntosRedimidosModel puntos = puntosRedimidosRepository.findById(id).orElse(null);
        if (puntos != null) {
            puntos.setCantidadPuntos(puntosRedimidosModel.getCantidadPuntos());
            puntos.setFechaRedencion(puntosRedimidosModel.getFechaRedencion());
            puntos.setPuntos(puntosRedimidosModel.getPuntos());
            puntos.setDetalleFactura(puntosRedimidosModel.getDetalleFactura());
            puntosRedimidosRepository.save(puntos);
            return ResponseEntity.ok(puntos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return puntosRedimidosRepository.findById(id)
                .map(puntos -> {
                    puntosRedimidosRepository.delete(puntos);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"error\": \"Puntos no encontrados.\"}"));
    }
}
