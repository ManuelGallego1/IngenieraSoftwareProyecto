package compraya.api.controllers;

import compraya.api.models.CategoriaModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import compraya.api.interfaces.ICrudService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController extends BaseController<CategoriaModel> {

    private final ICrudService<CategoriaModel> categoriaService;

    public CategoriaController(ICrudService<CategoriaModel> categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Override
    protected ICrudService<CategoriaModel> getService() {
        return categoriaService;
    }
}
