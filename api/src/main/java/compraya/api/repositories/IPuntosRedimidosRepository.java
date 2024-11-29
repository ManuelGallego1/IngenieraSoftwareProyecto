package compraya.api.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import compraya.api.models.PuntosRedimidosModel;

@Repository
public interface IPuntosRedimidosRepository extends JpaRepository<PuntosRedimidosModel, Long> {
    
}
