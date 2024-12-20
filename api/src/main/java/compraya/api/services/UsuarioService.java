package compraya.api.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import compraya.api.interfaces.IUsuarioService;
import compraya.api.models.UsuarioModel;
import compraya.api.repositories.IUsuarioRepository;
import compraya.api.factories.interfaces.IUsuarioFactory;
import compraya.api.factories.DefaultUsuarioFactory;

@Service
public class UsuarioService implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioFactory usuarioFactory = new DefaultUsuarioFactory();

    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    public ResponseEntity<?> getByRol(String rol) {
        List<UsuarioModel> usuarios = usuarioRepository.findByRol(rol);
        return ResponseEntity.ok(usuarios);
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Usuario no encontrado.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(UsuarioModel usuario) {
        try {
            UsuarioModel newUsuario = usuarioFactory.createUsuario();
            newUsuario.setNombre(usuario.getNombre());
            newUsuario.setContrasena(usuario.getContrasena());
            newUsuario.setIdentificacion(usuario.getIdentificacion());
            newUsuario.setEmail(usuario.getEmail());
            newUsuario.setCelular(usuario.getCelular());
            newUsuario.setRol(usuario.getRol());
            usuarioRepository.save(newUsuario);
            return ResponseEntity.ok("{\"message\": \"Usuario Creado.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al guardar el usuario.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(UsuarioModel usuario, Long id) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            try {
                UsuarioModel updatedUsuario = usuarioRepository.save(usuario);
                return ResponseEntity.ok(updatedUsuario);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al actualizar el usuario.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Usuario no encontrado.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (usuarioRepository.existsById(id)) {
            try {
                usuarioRepository.deleteById(id);
                return ResponseEntity.ok("{\"message\": \"Usuario eliminado.\"}");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al eliminar el usuario.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Usuario no encontrado.\"}");
        }
    }
}