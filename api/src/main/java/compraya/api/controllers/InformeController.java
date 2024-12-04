package compraya.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import compraya.api.models.InformeModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Qualifier;
import compraya.api.mediator.interfaces.InformeMediator;

@RestController
@RequestMapping("/informes")
public class InformeController {

    private final InformeMediator informeMediator;

    @Autowired
    public InformeController(@Qualifier("informeMediatorImpl") InformeMediator informeMediator) {
        this.informeMediator = informeMediator;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getInformes() {
        return informeMediator.getInformes();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createInforme(@RequestBody InformeModel informe) {
        return informeMediator.createInforme(informe);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getInformeById(@PathVariable("id") Long id) {
        return informeMediator.getInformeById(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateInforme(@RequestBody InformeModel informe, @PathVariable("id") Long id) {
        return informeMediator.updateInforme(id, informe);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteInforme(@PathVariable("id") Long id) {
        return informeMediator.deleteInforme(id);
    }
}