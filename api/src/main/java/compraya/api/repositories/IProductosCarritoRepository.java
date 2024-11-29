package compraya.api.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import compraya.api.models.ProductosCarritoModel;

@Repository
public interface IProductosCarritoRepository extends JpaRepository<ProductosCarritoModel, Long> {
    
}
