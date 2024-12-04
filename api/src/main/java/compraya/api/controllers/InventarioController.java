package compraya.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import compraya.api.models.InventarioModel;
import compraya.api.services.InventarioService;

import compraya.api.interfaces.ICrudService;

@RestController
@RequestMapping("/inventario")
public class InventarioController extends BaseController<InventarioModel> {

    private final ICrudService<InventarioModel> inventarioService;

    public InventarioController(ICrudService<InventarioModel> inventarioService) {
        this.inventarioService = inventarioService;
    }

    @Override
    protected ICrudService<InventarioModel> getService() {
        return inventarioService;
    }

    @PostMapping("/entrada")
    public ResponseEntity<?> registrarEntrada(@RequestParam Long productoId, 
                                              @RequestParam int entrada, 
                                              @RequestParam String referenciaCompra) {
        return ((InventarioService) inventarioService).registrarEntrada(productoId, entrada, referenciaCompra);
    }

    @PostMapping("/salida")
    public ResponseEntity<?> registrarSalida(@RequestParam Long productoId, 
                                             @RequestParam int salida, 
                                             @RequestParam String referenciaCompra) {
        return ((InventarioService) inventarioService).registrarSalida(productoId, salida, referenciaCompra);
    }

    @GetMapping("/{productoId}")
    public ResponseEntity<?> obtenerInventario(@PathVariable Long productoId) {
        return ((InventarioService) inventarioService).obtenerInventario(productoId);
    }
}
