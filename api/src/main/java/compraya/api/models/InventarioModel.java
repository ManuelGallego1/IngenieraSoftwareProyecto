package compraya.api.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "inventario")
public class InventarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entrada")
    private int entrada;

    @Column(name = "salida")
    private int salida;

    @Column(name = "referencia_compra")
    private String referenciaCompra;

    @ManyToOne
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private ProductoModel producto;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEntrada() {
        return entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public int getSalida() {
        return salida;
    }

    public void setSalida(int salida) {
        this.salida = salida;
    }

    public String getReferenciaCompra() {
        return referenciaCompra;
    }

    public void setReferenciaCompra(String referenciaCompra) {
        this.referenciaCompra = referenciaCompra;
    }

    public ProductoModel getProducto() {
        return producto;
    }

    public void setProducto(ProductoModel producto) {
        this.producto = producto;
    }
}