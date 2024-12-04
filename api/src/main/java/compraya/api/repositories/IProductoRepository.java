package compraya.api.repositories;

import org.springframework.stereotype.Repository;
import compraya.api.models.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IProductoRepository extends JpaRepository<ProductoModel, Long> {

}
