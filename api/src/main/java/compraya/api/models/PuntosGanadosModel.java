package compraya.api.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PuntosGanados")
public class PuntosGanadosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad_puntos", nullable = false)
    private int cantidadPuntos;

    @Column(name = "fecha_ganancia", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaGanancia;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "referencia")
    private String referencia;

    @ManyToOne
    @JoinColumn(name = "id_puntos", nullable = false)
    private PuntosModel puntos;

    // Getters y Setters
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

    public Date getFechaGanancia() {
        return fechaGanancia;
    }

    public void setFechaGanancia(Date fechaGanancia) {
        this.fechaGanancia = fechaGanancia;
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