package compraya.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import compraya.api.models.UsuarioModel;
import compraya.api.services.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @ResponseBody
    public ArrayList<UsuarioModel> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PostMapping
    @ResponseBody
    public UsuarioModel createUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.createUsuario(usuario);
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public UsuarioModel getUsuarioById(@PathVariable("id") Long id) {
        return usuarioService.getUsuarioById(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public UsuarioModel updateUsuario(@RequestBody UsuarioModel usuario, @PathVariable("id") Long id) {
        return usuarioService.updateUsuario(usuario, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteUsuario(@PathVariable("id") Long id) {
        boolean ok = usuarioService.deleteUsuario(id);

        if (ok) {
            return "Usuario " + id + "eliminado correctamente";
        } else {
            return "Error al eliminar el usuario " + id;
        }
    }
    
}
