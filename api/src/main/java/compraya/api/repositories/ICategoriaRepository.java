package compraya.api.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import compraya.api.models.CategoriaModel;

@Repository
public interface ICategoriaRepository extends JpaRepository<CategoriaModel, Long> {
    
}
