package compraya.api.services;

import org.springframework.http.ResponseEntity;
import compraya.api.interfaces.IDetalleFacturaService;
import compraya.api.models.DetalleFacturaModel;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import compraya.api.repositories.IDetalleFacturaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DetalleFacturaService implements IDetalleFacturaService{

    private final IDetalleFacturaRepository detalleFacturaRepository;
    private static final Logger logger = LoggerFactory.getLogger(DetalleFacturaService.class);

    @Autowired
    public DetalleFacturaService(IDetalleFacturaRepository detalleFacturaRepository) {
        this.detalleFacturaRepository = detalleFacturaRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.ok((ArrayList<DetalleFacturaModel>) detalleFacturaRepository.findAll());
        } catch (Exception e) {
            logger.error("Error al obtener los detalles de la factura", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al obtener los detalles de la factura.\"}");
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            Optional<DetalleFacturaModel> puntos = detalleFacturaRepository.findById(id);
            if (puntos.isPresent()) {
                return ResponseEntity.ok(puntos.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Detalles de factura no encontrados.\"}");
            }
        } catch (Exception e) {
            logger.error("Error al obtener los detalles de la factura con id " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al obtener los detalles de la factura.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(DetalleFacturaModel puntos) {
        try {
            DetalleFacturaModel savedPuntos = detalleFacturaRepository.save(puntos);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPuntos);
        } catch (Exception e) {
            logger.error("Error al guardar los detalles de factura", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al guardar los detalles.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(DetalleFacturaModel puntos, Long id) {
        if (detalleFacturaRepository.existsById(id)) {
            puntos.setId(id);
            try {
                DetalleFacturaModel updatedPuntos = detalleFacturaRepository.save(puntos);
                return ResponseEntity.ok(updatedPuntos);
            } catch (Exception e) {
                logger.error("Error al actualizar los detalles de factura", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al actualizar los detales de factura.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Detalles de factura no encontrados.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (detalleFacturaRepository.existsById(id)) {
            try {
                detalleFacturaRepository.deleteById(id);
                return ResponseEntity.ok("{\"message\": \"Detalles de factura eliminados.\"}");
            } catch (Exception e) {
                logger.error("Error al eliminar los detalles de factura", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al eliminar los detalles de factura.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Detalles de factura no encontrados.\"}");
        }
    }

    
}




