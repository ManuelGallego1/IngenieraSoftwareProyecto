package compraya.api.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import compraya.api.models.PuntosGanadosModel;

@Repository
public interface IPuntosGanadosRepository extends JpaRepository<PuntosGanadosModel, Long> {
    
}
