package modelo;

public class Tratamiento extends ProductoVivero {
    private String tipoInsumo;
    private String dosisRecomendada;
    private String afeccionObjetivo;

    public Tratamiento(String id, String nombre, double precioUnitario,
                       String tipoInsumo, String dosisRecomendada, String afeccionObjetivo) {
        super(id, nombre, precioUnitario);
        this.tipoInsumo = tipoInsumo;
        this.dosisRecomendada = dosisRecomendada;
        this.afeccionObjetivo = afeccionObjetivo;
    }

    public String getTipoInsumo() { return tipoInsumo; }
    public void setTipoInsumo(String tipoInsumo) { this.tipoInsumo = tipoInsumo; }

    public String getDosisRecomendada() { return dosisRecomendada; }
    public void setDosisRecomendada(String dosisRecomendada) { this.dosisRecomendada = dosisRecomendada; }

    public String getAfeccionObjetivo() { return afeccionObjetivo; }
    public void setAfeccionObjetivo(String afeccionObjetivo) { this.afeccionObjetivo = afeccionObjetivo; }

    @Override
    public String obtenerResumen() {
        return "[Tratamiento " + getId() + "] " + getNombre()
                + " | Tipo: " + tipoInsumo
                + " | Dosis: " + dosisRecomendada
                + " | Uso: " + afeccionObjetivo
                + " | Precio: $" + String.format("%.2f", getPrecioUnitario());
    }
}