package compraya.api.controllers;

import compraya.api.models.InventarioModel;
import compraya.api.interfaces.IInventarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private final IInventarioService inventarioService;

    public InventarioController(IInventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return inventarioService.get();
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody InventarioModel inventarioModel) {
        return inventarioService.post(inventarioModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return inventarioService.getOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@RequestBody InventarioModel inventarioModel, @PathVariable Long id) {
        return inventarioService.put(inventarioModel, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return inventarioService.delete(id);
    }

    @PostMapping("/{id}/entrada")
    public ResponseEntity<?> registrarEntrada(@PathVariable Long id, @RequestParam int entrada, @RequestParam String referenciaCompra) {
        return inventarioService.registrarEntrada(id, entrada, referenciaCompra);
    }

    @PostMapping("/{id}/salida")
    public ResponseEntity<?> registrarSalida(@PathVariable Long id, @RequestParam int salida, @RequestParam String referenciaCompra) {
        return inventarioService.registrarSalida(id, salida, referenciaCompra);
    }

    @GetMapping("/{productoId}/total")
    public ResponseEntity<?> obtenerInventario(@PathVariable Long productoId) {
        return inventarioService.obtenerInventario(productoId);
    }
}
