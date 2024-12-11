package compraya.api.repositories;

import org.springframework.stereotype.Repository;
import java.util.List;
import compraya.api.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    List<UsuarioModel> findByRol(String rol);
}
