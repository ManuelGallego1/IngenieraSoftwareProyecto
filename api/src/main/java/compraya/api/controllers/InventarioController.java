package compraya.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import compraya.api.models.InventarioModel;
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
}
