package modelo;

public class Cotizacion {
    private String idCotizacion;
    private Terreno terreno;
    private Tratamiento tratamiento;
    private double volumenSustratoM3;
    private double precioSustrato;
    private double margenGananciaPct;
    private double costoEnvio;
    private double subtotal;
    private double totalFinal;

    public Cotizacion(String idCotizacion, Terreno terreno, Tratamiento tratamiento,
                      double volumenSustratoM3, double precioSustrato,
                      double margenGananciaPct, double costoEnvio,
                      double subtotal, double totalFinal) {
        this.idCotizacion = idCotizacion;
        this.terreno = terreno;
        this.tratamiento = tratamiento;
        this.volumenSustratoM3 = volumenSustratoM3;
        this.precioSustrato = precioSustrato;
        this.margenGananciaPct = margenGananciaPct;
        this.costoEnvio = costoEnvio;
        this.subtotal = subtotal;
        this.totalFinal = totalFinal;
    }

    public String getIdCotizacion() { return idCotizacion; }
    public double getSubtotal() { return subtotal; }
    public double getTotalFinal() { return totalFinal; }

    public String generarResumenTexto() {
        String nombreTrat = (tratamiento != null) ? tratamiento.getNombre() : "Ninguno";
        return "==========================================\n"
                + "COTIZACION: " + idCotizacion + "\n"
                + "Cliente: " + terreno.getNombreCliente() + " (" + terreno.getUbicacion() + ")\n"
                + "Especie: " + terreno.getEspecieAsignada().getNombre()
                + " x " + terreno.getCantidadPlantasCalculada() + " unidades\n"
                + "Sustrato: " + volumenSustratoM3 + " m3 ($" + String.format("%.2f", precioSustrato) + "/m3)\n"
                + "Tratamiento incluido: " + nombreTrat + "\n"
                + "Subtotal insumos: $" + String.format("%.2f", subtotal) + "\n"
                + "Margen aplicado: " + margenGananciaPct + "%\n"
                + "Costo de envio: $" + String.format("%.2f", costoEnvio) + "\n"
                + "TOTAL FINAL PROYECTO: $" + String.format("%.2f", totalFinal) + "\n"
                + "==========================================";
    }
}