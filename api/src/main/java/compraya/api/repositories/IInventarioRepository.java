package compraya.api.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import compraya.api.models.InventarioModel;

@Repository
public interface IInventarioRepository extends JpaRepository<InventarioModel, Long> {
    
}
