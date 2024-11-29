package compraya.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.util.Date;

@Entity
@Table(name = "PuntosRedimidos")
public class PuntosRedimidosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidadPuntos;
    private Date fechaRedencion;

    @ManyToOne
    @JoinColumn(name = "detalle_factura_id", nullable = false)
    private DetalleFacturaModel detalleFactura;

    @ManyToOne
    @JoinColumn(name = "id_puntos", nullable = false)
    private PuntosModel puntos;

    public PuntosRedimidosModel() {
    }

    public PuntosRedimidosModel(Long id, int cantidadPuntos, Date fechaRedencion, DetalleFacturaModel detalleFactura, PuntosModel puntos) {
        this.id = id;
        this.cantidadPuntos = cantidadPuntos;
        this.fechaRedencion = fechaRedencion;
        this.detalleFactura = detalleFactura;
        this.puntos = puntos;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidadPuntos() {
        return cantidadPuntos;
    }

    public void setCantidadPuntos(int cantidadPuntos) {
        this.cantidadPuntos = cantidadPuntos;
    }

    public Date getFechaRedencion() {
        return fechaRedencion;
    }

    public void setFechaRedencion(Date fechaRedencion) {
        this.fechaRedencion = fechaRedencion;
    }

    public DetalleFacturaModel getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(DetalleFacturaModel detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    public PuntosModel getPuntos() {
        return puntos;
    }

    public void setPuntos(PuntosModel puntos) {
        this.puntos = puntos;
    }
}