package compraya.api.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import compraya.api.interfaces.IFacturaService;
import compraya.api.models.FacturaModel;
import compraya.api.repositories.IFacturaRepository;

@Service
public class FacturaService implements IFacturaService {

    private final IFacturaRepository facturaRepository;

    @Autowired
    public FacturaService(IFacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        return ResponseEntity.ok((ArrayList<FacturaModel>) facturaRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        Optional<FacturaModel> factura = facturaRepository.findById(id);
        if (factura.isPresent()) {
            return ResponseEntity.ok(factura.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Factura no encontrado.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(FacturaModel factura) {
        try {
            FacturaModel savedfactura = facturaRepository.save(factura);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedfactura);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al guardar la factura.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(FacturaModel factura, Long id) {
        if (facturaRepository.existsById(id)) {
            factura.setId(id);
            try {
                FacturaModel updatedfactura = facturaRepository.save(factura);
                return ResponseEntity.ok(updatedfactura);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al actualizar la factura.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Factura no encontrado.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (facturaRepository.existsById(id)) {
            try {
                facturaRepository.deleteById(id);
                return ResponseEntity.ok("{\"message\": \"Factura eliminado.\"}");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al eliminar la factura.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Factura no encontrado.\"}");
        }
    }
}
