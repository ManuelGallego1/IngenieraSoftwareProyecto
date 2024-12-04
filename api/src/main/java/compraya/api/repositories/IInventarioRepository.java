package compraya.api.repositories;

import compraya.api.models.InventarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventarioRepository extends JpaRepository<InventarioModel, Long> {
}