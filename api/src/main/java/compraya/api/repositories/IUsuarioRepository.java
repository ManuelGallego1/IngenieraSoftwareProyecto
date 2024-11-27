package compraya.api.repositories;

import org.springframework.stereotype.Repository;
import compraya.api.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioModel, Long> {

}
