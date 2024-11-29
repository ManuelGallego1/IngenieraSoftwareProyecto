package compraya.api.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "DetalleFactura")
public class DetalleFacturaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private double valorTotal;
    private double descuento;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoModel producto;

    @ManyToOne
    @JoinColumn(name = "id_factura")
    private FacturaModel factura;

    public DetalleFacturaModel() {
    }

    public DetalleFacturaModel(Long id, int cantidad, double valorTotal, double descuento, ProductoModel producto, FacturaModel factura) {
        this.id = id;
        this.cantidad = cantidad;
        this.valorTotal = valorTotal;
        this.descuento = descuento;
        this.producto = producto;
        this.factura = factura;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public ProductoModel getProducto() {
        return producto;
    }

    public void setProducto(ProductoModel producto) {
        this.producto = producto;
    }

    public FacturaModel getFactura() {
        return factura;
    }

    public void setFactura(FacturaModel factura) {
        this.factura = factura;
    }
}