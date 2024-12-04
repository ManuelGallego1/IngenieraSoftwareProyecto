package compraya.api.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import compraya.api.interfaces.IUsuarioService;
import compraya.api.models.UsuarioModel;
import compraya.api.repositories.IUsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {
 // Singleton: Spring crea una única instancia de este servicio.
    private static UsuarioService instancia;  // Instancia única de UsuarioService
    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public static UsuarioService getInstancia(IUsuarioRepository usuarioRepository) {
        if (instancia == null) {
            instancia = new UsuarioService(usuarioRepository); // Crear la instancia solo si no existe
        }
        return instancia;
    }

    @Override
    public ResponseEntity<?> get() {
        return ResponseEntity.ok((ArrayList<UsuarioModel>) usuarioRepository.findAll());
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
            UsuarioModel savedUsuario = usuarioRepository.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
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
    
    
    // Facade: el servicio abstrae detalles del repositorio y expone métodos sencillos al controlador.
   
     public ResponseEntity<?> login(String email, String contrasena) {
        Optional<UsuarioModel> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent() && usuario.get().getContrasena().equals(contrasena)) {
            UsuarioModel loggedUsuario = usuario.get();
            loggedUsuario.setLoggedIn(true);
            usuarioRepository.save(loggedUsuario);
            return ResponseEntity.ok("{\"message\": \"Login exitoso.\"}");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Credenciales inválidas.\"}");
    }

    public ResponseEntity<?> logout(Long id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent() && usuario.get().isLoggedIn()) {
            UsuarioModel loggedUsuario = usuario.get();
            loggedUsuario.setLoggedIn(false);
            usuarioRepository.save(loggedUsuario);
            return ResponseEntity.ok("{\"message\": \"Logout exitoso.\"}");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"El usuario no está logueado.\"}");
    }
    
     public boolean isAdmin(Long id) {
         // Alta cohesión: verifica el rol del usuario, sin mezclar lógica ajena.
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        return usuario.isPresent() && usuario.get().getRol().equalsIgnoreCase("ADMIN");
    }

    public boolean isLoggedIn(Long id) {
        // Alta cohesión: verifica el estado de login del usuario.
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        return usuario.isPresent() && usuario.get().isLoggedIn();
    }
}
