package compraya.api.controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import compraya.api.models.PuntosModel;
import compraya.api.interfaces.ICrudService;


@RestController
@RequestMapping("/puntos")
public class PuntosController extends BaseController<PuntosModel> {

    private final ICrudService<PuntosModel> puntosService;

    public PuntosController(ICrudService<PuntosModel> puntosService) {
        this.puntosService = puntosService;
    }

    @Override
    protected ICrudService<PuntosModel> getService() {
        return puntosService;
    }
}