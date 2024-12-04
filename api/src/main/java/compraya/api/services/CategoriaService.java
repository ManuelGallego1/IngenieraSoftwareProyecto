package compraya.api.services;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import compraya.api.interfaces.ICategoriaService;
import compraya.api.models.CategoriaModel;
import compraya.api.repositories.ICategoriaRepository;


@Service
public class CategoriaService implements ICategoriaService {

    private final ICategoriaRepository categoriaRepository;

    public CategoriaService(ICategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        return ResponseEntity.ok((ArrayList<CategoriaModel>) categoriaRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        Optional<CategoriaModel> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Categoria no encontrada.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(CategoriaModel categoria) {
        try {
            CategoriaModel savedCategoria = categoriaRepository.save(categoria);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoria);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al guardar la categoria.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(CategoriaModel categoria, Long id) {
        if (categoriaRepository.existsById(id)) {
            categoria.setId(id);
            try {
                CategoriaModel updatedCategoria = categoriaRepository.save(categoria);
                return ResponseEntity.ok(updatedCategoria);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al actualizar la categoria.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Categoria no encontrada.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (categoriaRepository.existsById(id)) {
            try {
                categoriaRepository.deleteById(id);
                return ResponseEntity.ok("{\"message\": \"Categoria eliminada.\"}");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al eliminar la categoria.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Categoria no encontrada.\"}");
        }
    }
}