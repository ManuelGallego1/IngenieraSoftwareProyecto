package compraya.api.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import compraya.api.models.MetodoPagoModel;

@Repository
public interface IMetodoPagoRepository extends JpaRepository<MetodoPagoModel, Long> {
	
}
