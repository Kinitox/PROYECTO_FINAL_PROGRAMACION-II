package modelo;

public class ProductoVivero {
    private String id;
    private String nombre;
    private double precioUnitario;

    public ProductoVivero(String id, String nombre, double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        if (precioUnitario >= 0) {
            this.precioUnitario = precioUnitario;
        }
    }

    // Metodo polimorfico que las clases hijas sobrescribiran
    public String obtenerResumen() {
        return "[" + id + "] " + nombre + " - $" + String.format("%.2f", precioUnitario);
    }
}