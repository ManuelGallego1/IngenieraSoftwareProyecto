package compraya.api.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import compraya.api.models.CarritoModel;

@Repository
public interface ICarritoRepository extends JpaRepository<CarritoModel, Long> {
    
}
