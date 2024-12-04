package compraya.api.services;

import org.springframework.http.ResponseEntity;
import compraya.api.interfaces.IInformeService;
import compraya.api.models.InformeModel;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import compraya.api.repositories.IInformeRepository;

@Service
public class InformeService implements IInformeService {

    private final IInformeRepository informeRepository;

    @Autowired
    public InformeService(IInformeRepository informeRepository) {
        this.informeRepository = informeRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        return ResponseEntity.ok((ArrayList<InformeModel>) informeRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        Optional<InformeModel> informe = informeRepository.findById(id);
        if (informe.isPresent()) {
            return ResponseEntity.ok(informe.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"informes no encontrados.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(InformeModel informe) {
        try {
            InformeModel savedinforme = informeRepository.save(informe);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedinforme);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al guardar los informes.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(InformeModel informe, Long id) {
        if (informeRepository.existsById(id)) {
            informe.setId(id);
            try {
                InformeModel updatedinforme = informeRepository.save(informe);
                return ResponseEntity.ok(updatedinforme);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al actualizar los informes.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"informes no encontrados.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (informeRepository.existsById(id)) {
            try {
                informeRepository.deleteById(id);
                return ResponseEntity.ok("{\"message\": \"informes eliminados.\"}");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al eliminar los informes.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"informes no encontrados.\"}");
        }
    }
}