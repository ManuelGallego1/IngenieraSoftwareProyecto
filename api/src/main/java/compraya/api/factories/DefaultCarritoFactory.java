package compraya.api.factories;

import compraya.api.factories.interfaces.ICarritoFactory;
import compraya.api.models.CarritoModel;

public class DefaultCarritoFactory implements ICarritoFactory {

    @Override
    public CarritoModel createCarrito() {
        return new CarritoModel();
    }
}