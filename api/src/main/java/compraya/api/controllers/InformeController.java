package compraya.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import compraya.api.models.InformeModel;
import compraya.api.services.InformeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/informes")
public class InformeController {

    private final InformeService InformeService;

    @Autowired
    public InformeController(InformeService InformeService) {
        this.InformeService = InformeService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getinformes() {
        return InformeService.get();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createinforme(@RequestBody InformeModel informe) {
        return InformeService.post(informe);
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getinformeById(@PathVariable("id") Long id) {
        return InformeService.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateinforme(@RequestBody InformeModel informe, @PathVariable("id") Long id) {
        return InformeService.put(informe, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteinforme(@PathVariable("id") Long id) {
        return InformeService.delete(id);
    }
}