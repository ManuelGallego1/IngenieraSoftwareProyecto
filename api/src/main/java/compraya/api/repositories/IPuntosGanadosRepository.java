package compraya.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import compraya.api.models.PuntosGanadosModel;
@Repository
public interface IPuntosGanadosRepository extends JpaRepository<PuntosGanadosModel, Long> {
}