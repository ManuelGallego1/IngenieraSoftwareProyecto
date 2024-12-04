package compraya.api.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import compraya.api.models.InformeModel;

@Repository
public interface IInformeRepository extends JpaRepository<InformeModel, Long> {
    
}
