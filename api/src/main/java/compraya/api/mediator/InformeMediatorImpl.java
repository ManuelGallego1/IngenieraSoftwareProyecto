package compraya.api.mediator;

import org.springframework.http.ResponseEntity;
import compraya.api.models.InformeModel;
import org.springframework.stereotype.Component;
import compraya.api.mediator.interfaces.InformeMediator;
import compraya.api.services.InformeService;

@Component
public class InformeMediatorImpl implements InformeMediator {
    private final InformeService informeService;

    public InformeMediatorImpl(InformeService informeService) {
        this.informeService = informeService;
    }

    @Override
    public ResponseEntity<?> getInformes() {
        return informeService.get();
    }

    @Override
    public ResponseEntity<?> getInformeById(Long id) {
        return informeService.getOne(id);   
    }

    @Override
    public ResponseEntity<?> createInforme(InformeModel informe) {
        return informeService.post(informe);
    }

    @Override
    public ResponseEntity<?> updateInforme(Long id, InformeModel informe) {
        return informeService.put(informe, id);
    }

    @Override
    public ResponseEntity<?> deleteInforme(Long id) {
        return informeService.delete(id);
    }
}
