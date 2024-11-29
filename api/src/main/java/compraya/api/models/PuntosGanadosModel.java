package compraya.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "PuntosGanados")
public class PuntosGanadosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad_puntos;
    private Date fecha_ganancia;
    private String motivo;
    private String referencia;

    @ManyToOne
    @JoinColumn(name = "id_puntos", nullable = false)
    private PuntosModel puntos;

    public PuntosGanadosModel() {
    }

    public PuntosGanadosModel(Long id, int cantidad_puntos, Date fecha_ganancia, String motivo, String referencia, PuntosModel puntos) {
        this.id = id;
        this.cantidad_puntos = cantidad_puntos;
        this.fecha_ganancia = fecha_ganancia;
        this.motivo = motivo;
        this.referencia = referencia;
        this.puntos = puntos;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad_puntos() {
        return cantidad_puntos;
    }

    public void setCantidad_puntos(int cantidad_puntos) {
        this.cantidad_puntos = cantidad_puntos;
    }

    public Date getFecha_ganancia() {
        return fecha_ganancia;
    }

    public void setFecha_ganancia(Date fecha_ganancia) {
        this.fecha_ganancia = fecha_ganancia;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public PuntosModel getPuntos() {
        return puntos;
    }

    public void setPuntos(PuntosModel puntos) {
        this.puntos = puntos;
    }
}