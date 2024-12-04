package compraya.api.controllers;

import compraya.api.models.PuntosRedimidosModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import compraya.api.interfaces.ICrudService;

@RestController
@RequestMapping("/puntos-redimidos")
public class PuntosRedimidosController extends BaseController<PuntosRedimidosModel> {

    private final ICrudService<PuntosRedimidosModel> puntosRedimidosService;

    public PuntosRedimidosController(ICrudService<PuntosRedimidosModel> puntosRedimidosService) {
        this.puntosRedimidosService = puntosRedimidosService;
    }

    @Override
    protected ICrudService<PuntosRedimidosModel> getService() {
        return puntosRedimidosService;
    }
}