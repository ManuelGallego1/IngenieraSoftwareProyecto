package compraya.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import compraya.api.models.PuntosGanadosModel;
import compraya.api.interfaces.ICrudService;

@RestController
@RequestMapping("/puntos-ganados")
public class PuntosGanadosController extends BaseController<PuntosGanadosModel> {

    private final ICrudService<PuntosGanadosModel> puntosGanadosService;

    public PuntosGanadosController(ICrudService<PuntosGanadosModel> puntosGanadosService) {
        this.puntosGanadosService = puntosGanadosService;
    }

    @Override
    protected ICrudService<PuntosGanadosModel> getService() {
        return puntosGanadosService;
    }
}
