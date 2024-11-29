package compraya.api.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import compraya.api.models.PuntosModel;

@Repository
public interface IPuntosRepository extends JpaRepository<PuntosModel, Long> {
    
}
