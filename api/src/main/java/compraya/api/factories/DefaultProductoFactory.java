package compraya.api.factories;

import compraya.api.factories.interfaces.IProductoFactory;
import compraya.api.models.ProductoModel;

public class DefaultProductoFactory implements IProductoFactory {

    @Override
    public ProductoModel createProducto() {
        return new ProductoModel();
    }
    
}
