package compraya.api.factories;

import compraya.api.factories.interfaces.IUsuarioFactory;
import compraya.api.models.UsuarioModel;

public class DefaultUsuarioFactory implements IUsuarioFactory {

    @Override
    public UsuarioModel createUsuario() {
        return new UsuarioModel();
    }
    
}
