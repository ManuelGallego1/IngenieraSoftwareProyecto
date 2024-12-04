package compraya.api.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.Date;


@Entity
@Table(name = "Informes")
public class InformeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_informe", nullable = false)
    private String tipoInforme;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "datos_json", nullable = false, columnDefinition = "json")
    private String datosJson;

    public InformeModel() {
    }

    public InformeModel(Long id, String tipoInforme, Date fecha, String datosJson) {
        this.id = id;
        this.tipoInforme = tipoInforme;
        this.fecha = fecha;
        this.datosJson = datosJson;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoInforme() {
        return tipoInforme;
    }

    public void setTipoInforme(String tipoInforme) {
        this.tipoInforme = tipoInforme;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDatosJson() {
        return datosJson;
    }

    public void setDatosJson(String datosJson) {
        this.datosJson = datosJson;
    }
}