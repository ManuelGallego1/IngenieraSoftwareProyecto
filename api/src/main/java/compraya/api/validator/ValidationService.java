package compraya.api.validator;

import org.springframework.stereotype.Service;
import compraya.api.models.CategoriaModel;

@Service
public class ValidationService {

    public boolean validateCategoria(CategoriaModel categoria) {
        if (categoria == null) {
            return false;
        }

        String nombre = categoria.getNombre();
        if (nombre == null || nombre.isEmpty()) {
            return false;
        }

        if (nombre.length() <= 5) {
            return false;
        }

        if (!nombre.matches("[a-zA-Z0-9 ]+")) {
            return false;
        }

        return true;
    }
}
