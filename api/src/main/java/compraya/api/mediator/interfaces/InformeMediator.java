package compraya.api.mediator.interfaces;
import org.springframework.http.ResponseEntity;
import compraya.api.models.InformeModel;

public interface InformeMediator {
    ResponseEntity<?> getInformes();
    ResponseEntity<?> getInformeById(Long id);
    ResponseEntity<?> createInforme(InformeModel informe);
    ResponseEntity<?> updateInforme(Long id, InformeModel informe);
    ResponseEntity<?> deleteInforme(Long id);
}
