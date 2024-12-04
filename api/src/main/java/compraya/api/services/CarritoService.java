package compraya.api.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import compraya.api.factories.interfaces.ICarritoFactory;
import compraya.api.factories.DefaultCarritoFactory;
import compraya.api.interfaces.ICarritoService;
import compraya.api.models.CarritoModel;
import compraya.api.repositories.ICarritoRepository;

@Service
public class CarritoService implements ICarritoService {

    private final ICarritoRepository carritoRepository;
    private final ICarritoFactory carritoFactory = new DefaultCarritoFactory();

    @Autowired
    public CarritoService(ICarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        return ResponseEntity.ok((ArrayList<CarritoModel>) carritoRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        Optional<CarritoModel> carrito = carritoRepository.findById(id);
        if (carrito.isPresent()) {
            return ResponseEntity.ok(carrito.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Carrito no encontrado.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(CarritoModel carrito) {
        CarritoModel newCarrito = carritoFactory.createCarrito();
        newCarrito.setUsuario(carrito.getUsuario());
        try {
            carritoRepository.save(newCarrito);
            return ResponseEntity.ok("{\"message\": \"Carrito Creado.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al guardar el carrito.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(CarritoModel carrito, Long id) {
        if (carritoRepository.existsById(id)) {
            carrito.setId(id);
            try {
                CarritoModel updatedCarrito = carritoRepository.save(carrito);
                return ResponseEntity.ok(updatedCarrito);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al actualizar el carrito.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Carrito no encontrado.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (carritoRepository.existsById(id)) {
            try {
                carritoRepository.deleteById(id);
                return ResponseEntity.ok("{\"message\": \"Carrito eliminado.\"}");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al eliminar el carrito.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Carrito no encontrado.\"}");
        }
    }
}