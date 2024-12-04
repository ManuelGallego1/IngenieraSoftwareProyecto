package compraya.api.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import compraya.api.models.CategoriaModel;
import compraya.api.services.CategoriaService;
import java.util.ArrayList;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCategorias() {
        return categoriaService.get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoriaById(@PathVariable Long id) {
        return categoriaService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<?> createCategoria(@RequestBody CategoriaModel categoria) {
        return categoriaService.post(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoria(@RequestBody CategoriaModel categoria, @PathVariable Long id) {
        return categoriaService.put(categoria, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        return categoriaService.delete(id);
    }
}