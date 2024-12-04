package compraya.api.models;

import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MetodosPago")
public class MetodoPagoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String metodo;

    public MetodoPagoModel() {
    }

    public MetodoPagoModel(Long id, String metodo) {
        this.id = id;
        this.metodo = metodo;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetodo() {
        return this.metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

}
