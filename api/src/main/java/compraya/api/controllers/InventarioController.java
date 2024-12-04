package compraya.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import compraya.api.models.InventarioModel;
import compraya.api.services.InventarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/inventarios")
public class InventarioController {

    private final InventarioService InventarioService;

    @Autowired
    public InventarioController(InventarioService InventarioService) {
        this.InventarioService = InventarioService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getInventarios() {
        return InventarioService.get();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createInventario(@RequestBody InventarioModel Inventario) {
        return InventarioService.post(Inventario);
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getInventarioById(@PathVariable("id") Long id) {
        return InventarioService.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateInventario(@RequestBody InventarioModel Inventario, @PathVariable("id") Long id) {
        return InventarioService.put(Inventario, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteInventario(@PathVariable("id") Long id) {
        return InventarioService.delete(id);
    }
}