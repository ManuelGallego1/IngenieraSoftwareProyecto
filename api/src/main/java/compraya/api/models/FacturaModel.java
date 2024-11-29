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
@Table(name = "Facturas")
public class FacturaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private Date fecha;
    private double subtotal;
    private double totalImpuestos;
    private double total;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private UsuarioModel cliente;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago")
    private MetodoPagoModel metodoPago;

    public FacturaModel() {
    }

    public FacturaModel(Long id, String codigo, Date fecha, double subtotal, double totalImpuestos, double total, String estado, UsuarioModel cliente, MetodoPagoModel metodoPago) {
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.totalImpuestos = totalImpuestos;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.metodoPago = metodoPago;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotalImpuestos() {
        return totalImpuestos;
    }

    public void setTotalImpuestos(double totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public UsuarioModel getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioModel cliente) {
        this.cliente = cliente;
    }

    public MetodoPagoModel getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPagoModel metodoPago) {
        this.metodoPago = metodoPago;
    }
}