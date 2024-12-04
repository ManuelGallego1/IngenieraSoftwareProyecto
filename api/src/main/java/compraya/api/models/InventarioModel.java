package compraya.api.models;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Inventario")
public class InventarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int entrada;
    private int salida;
    private String referencia_compra;
    
    @OneToOne
    @JoinColumn(name = "idProducto")
    private ProductoModel producto;

    public InventarioModel() {
    }

    public InventarioModel(Long id, int entrada, int salida, String referencia_compra, ProductoModel producto) {
        this.id = id;
        this.entrada = entrada;
        this.salida = salida;
        this.referencia_compra = referencia_compra;
        this.producto = producto;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEntrada() {
        return this.entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public int getSalida() {
        return this.salida;
    }

    public void setSalida(int salida) {
        this.salida = salida;
    }

    public String getReferencia_compra() {
        return this.referencia_compra;
    }

    public void setReferencia_compra(String referencia_compra) {
        this.referencia_compra = referencia_compra;
    }

    public ProductoModel getProducto() {
        return this.producto;
    }

    public void setProducto(ProductoModel producto) {
        this.producto = producto;
    }

}
