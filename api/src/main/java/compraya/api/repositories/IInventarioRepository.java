package compraya.api.repositories;

import compraya.api.models.InventarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventarioRepository extends JpaRepository<InventarioModel, Long> {
        @Query("SELECT COALESCE(SUM(i.entrada), 0) FROM InventarioModel i WHERE i.producto.id = :productoId")
    int sumEntradasByProductoId(Long productoId);

    @Query("SELECT COALESCE(SUM(i.salida), 0) FROM InventarioModel i WHERE i.producto.id = :productoId")
    int sumSalidasByProductoId(Long productoId);
}