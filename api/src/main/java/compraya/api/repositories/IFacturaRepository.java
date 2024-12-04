package compraya.api.repositories;

import compraya.api.models.FacturaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacturaRepository extends JpaRepository<FacturaModel, Long> {
}