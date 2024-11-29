package compraya.api.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import compraya.api.models.DetalleFacturaModel;

@Repository
public interface IDetalleFacturaRepository extends JpaRepository<DetalleFacturaModel, Long> {
	
}
