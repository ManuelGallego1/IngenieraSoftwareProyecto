package compraya.api.services;

import org.springframework.http.ResponseEntity;
import compraya.api.interfaces.IMetodoPagoService;
import compraya.api.models.MetodoPagoModel;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import compraya.api.repositories.IMetodoPagoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MetodoPagoService implements IMetodoPagoService{

    private final IMetodoPagoRepository metodoPagoRepository;
    private static final Logger logger = LoggerFactory.getLogger(MetodoPagoService.class);

    public MetodoPagoService(IMetodoPagoRepository metodoPagoRepository) {
        this.metodoPagoRepository = metodoPagoRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.ok((ArrayList<MetodoPagoModel>) metodoPagoRepository.findAll());
        } catch (Exception e) {
            logger.error("Error al obtener los detalles del metodo de pago", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al obtener los detalles del metodo de pago.\"}");
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            Optional<MetodoPagoModel> metodo_pago = metodoPagoRepository.findById(id);
            if (metodo_pago.isPresent()) {
                return ResponseEntity.ok(metodo_pago.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Detalles de metodo de pago no encontrados.\"}");
            }
        } catch (Exception e) {
            logger.error("Error al obtener los detalles del metodo de pago con id " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al obtener los detalles del metodo de pago.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(MetodoPagoModel metodo_pago) {
        try {
            MetodoPagoModel savedmetodo_pago = metodoPagoRepository.save(metodo_pago);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedmetodo_pago);
        } catch (Exception e) {
            logger.error("Error al guardar los detalles de metodo de pago", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al guardar los detalles.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(MetodoPagoModel metodo_pago, Long id) {
        if (metodoPagoRepository.existsById(id)) {
            metodo_pago.setId(id);
            try {
                MetodoPagoModel updatedmetodo_pago = metodoPagoRepository.save(metodo_pago);
                return ResponseEntity.ok(updatedmetodo_pago);
            } catch (Exception e) {
                logger.error("Error al actualizar los detalles de metodo de pago", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al actualizar los detalles de metodo de pago.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Detalles de metodo de pago no encontrados.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (metodoPagoRepository.existsById(id)) {
            try {
                metodoPagoRepository.deleteById(id);
                return ResponseEntity.ok("{\"message\": \"Detalles de metodo de pago eliminados.\"}");
            } catch (Exception e) {
                logger.error("Error al eliminar los detalles de metodo de pago", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al eliminar los detalles de metodo de pago.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Detalles de metodo de pago no encontrados.\"}");
        }
    }

    
}





