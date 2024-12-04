package compraya.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getUsuarios() {
        return usuarioService.get();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioService.post(usuario);
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getUsuarioById(@PathVariable("id") Long id) {
        return usuarioService.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateUsuario(@RequestBody UsuarioModel usuario, @PathVariable("id") Long id) {
        return usuarioService.put(usuario, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") Long id) {
        return usuarioService.delete(id);
    }
}