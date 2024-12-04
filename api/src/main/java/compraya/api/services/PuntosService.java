package compraya.api.services;

import org.springframework.http.ResponseEntity;

import compraya.api.interfaces.IPuntosService;
import compraya.api.models.PuntosModel;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import compraya.api.repositories.IPuntosRepository;

@Service
public class PuntosService implements IPuntosService {

    private final IPuntosRepository puntosRepository;

    public PuntosService(IPuntosRepository puntosRepository) {
        this.puntosRepository = puntosRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        return ResponseEntity.ok((ArrayList<PuntosModel>) puntosRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        Optional<PuntosModel> puntos = puntosRepository.findById(id);
        if (puntos.isPresent()) {
            return ResponseEntity.ok(puntos.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(PuntosModel puntos) {
        try {
            PuntosModel savedPuntos = puntosRepository.save(puntos);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPuntos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al guardar los puntos.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(PuntosModel puntos, Long id) {
        if (puntosRepository.existsById(id)) {
            puntos.setId(id);
            try {
                PuntosModel updatedPuntos = puntosRepository.save(puntos);
                return ResponseEntity.ok(updatedPuntos);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al actualizar los puntos.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (puntosRepository.existsById(id)) {
            try {
                puntosRepository.deleteById(id);
                return ResponseEntity.ok("{\"message\": \"Puntos eliminados.\"}");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al eliminar los puntos.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Puntos no encontrados.\"}");
        }
    }
}