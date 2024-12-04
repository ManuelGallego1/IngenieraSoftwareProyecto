package compraya.api.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import compraya.api.interfaces.IInventarioService;
import compraya.api.models.InventarioModel;
import compraya.api.repositories.IInventarioRepository;

@Service
public class InventarioService implements IInventarioService {

    private final IInventarioRepository InventarioRepository;

    @Autowired
    public InventarioService(IInventarioRepository InventarioRepository) {
        this.InventarioRepository = InventarioRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        return ResponseEntity.ok((ArrayList<InventarioModel>) InventarioRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        Optional<InventarioModel> Inventario = InventarioRepository.findById(id);
        if (Inventario.isPresent()) {
            return ResponseEntity.ok(Inventario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Inventario no encontrado.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(InventarioModel Inventario) {
        try {
            InventarioModel savedInventario = InventarioRepository.save(Inventario);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedInventario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al guardar el Inventario.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(InventarioModel Inventario, Long id) {
        if (InventarioRepository.existsById(id)) {
            Inventario.setId(id);
            try {
                InventarioModel updatedInventario = InventarioRepository.save(Inventario);
                return ResponseEntity.ok(updatedInventario);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al actualizar el Inventario.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Inventario no encontrado.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (InventarioRepository.existsById(id)) {
            try {
                InventarioRepository.deleteById(id);
                return ResponseEntity.ok("{\"message\": \"Inventario eliminado.\"}");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al eliminar el Inventario.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Inventario no encontrado.\"}");
        }
    }
}