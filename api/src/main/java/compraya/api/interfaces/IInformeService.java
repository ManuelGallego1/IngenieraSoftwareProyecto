package compraya.api.interfaces;

import org.springframework.http.ResponseEntity;

import compraya.api.models.InformeModel;

public interface IInformeService {
    ResponseEntity<?> get();
    ResponseEntity<?> getOne(Long id);
    ResponseEntity<?> post(InformeModel informe);
    ResponseEntity<?> put(InformeModel informe, Long id);
    ResponseEntity<?> delete(Long id);
}
