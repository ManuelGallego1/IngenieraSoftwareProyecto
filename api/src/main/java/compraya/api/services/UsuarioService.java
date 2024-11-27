package compraya.api.services;

import org.springframework.stereotype.Service;
import compraya.api.models.UsuarioModel;
import compraya.api.repositories.IUsuarioRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UsuarioService{

    @Autowired
    private IUsuarioRepository usuarioRepository;
    
    public ArrayList<UsuarioModel> getUsuarios() {
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel getUsuarioById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    public UsuarioModel createUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public UsuarioModel updateUsuario(UsuarioModel usuario, Long id) {
        UsuarioModel user = usuarioRepository.getReferenceById(id);
        user.setNombre(usuario.getNombre());
        user.setIdentificacion(usuario.getIdentificacion());
        user.setEmail(usuario.getEmail());
        user.setContrasena(usuario.getContrasena());
        user.setCelular(usuario.getCelular());
        return usuarioRepository.save(user);
    }

    public boolean deleteUsuario(Long id) {
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
            return false;
        } 
    }
}
