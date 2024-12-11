package compraya.api.services;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import compraya.api.interfaces.IFacturaService;
import compraya.api.models.FacturaModel;
import compraya.api.repositories.IFacturaRepository;

@Service
public class FacturaService implements IFacturaService {

    private final IFacturaRepository facturaRepository;

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

    @Override
    public ResponseEntity<?> generateFacturaXML(Long id) {
        Optional<FacturaModel> facturaOpt = facturaRepository.findById(id);
        if (!facturaOpt.isPresent()) {
            return ResponseEntity.status(404).body("Factura no encontrada");
        }

        FacturaModel factura = facturaOpt.get();

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Root element
            org.w3c.dom.Document doc = docBuilder.newDocument();
            org.w3c.dom.Element rootElement = doc.createElement("Factura");
            doc.appendChild(rootElement);

            // Add elements to the XML
            rootElement.appendChild(createElement(doc, "Codigo", factura.getCodigo()));
            rootElement.appendChild(createElement(doc, "Fecha", factura.getFecha().toString()));
            rootElement.appendChild(createElement(doc, "Subtotal", String.valueOf(factura.getSubtotal())));
            rootElement.appendChild(createElement(doc, "TotalImpuestos", String.valueOf(factura.getTotalImpuestos())));
            rootElement.appendChild(createElement(doc, "Total", String.valueOf(factura.getTotal())));
            rootElement.appendChild(createElement(doc, "Estado", factura.getEstado()));
            rootElement.appendChild(createElement(doc, "Cliente", factura.getCliente().getNombre()));
            rootElement.appendChild(createElement(doc, "MetodoPago", factura.getMetodoPago().getMetodo()));

            // Transform the document to a string
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));

            return ResponseEntity.ok(writer.toString());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al generar el XML");
        }
    }

    private org.w3c.dom.Element createElement(org.w3c.dom.Document doc, String name, String value) {
        org.w3c.dom.Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }
}
