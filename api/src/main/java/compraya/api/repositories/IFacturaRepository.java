package compraya.api.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import compraya.api.models.FacturaModel;

@Repository
public interface IFacturaRepository extends JpaRepository<FacturaModel, Long> {
    
}
