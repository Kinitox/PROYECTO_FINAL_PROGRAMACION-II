package modelo;

public class Especie extends ProductoVivero {
    private String nombreCientifico;
    private String requerimientoLuz; // "SOL", "SEMISOMBRA", "SOMBRA"
    private double phIdeal;
    private double diametroCopa;     // en metros
    private double diametroRaiz;     // en metros
    private String indiceAlelopatia; // "ALTA", "MEDIA", "BAJA"
    private boolean esSensible;
    private double coeficienteKc;    // coeficiente del cultivo para FAO-56

    public Especie(String id, String nombre, double precioUnitario,
                   String nombreCientifico, String requerimientoLuz, double phIdeal,
                   double diametroCopa, double diametroRaiz, String indiceAlelopatia,
                   boolean esSensible, double coeficienteKc) {
        super(id, nombre, precioUnitario);
        this.nombreCientifico = nombreCientifico;
        this.requerimientoLuz = requerimientoLuz;
        this.phIdeal = phIdeal;
        this.diametroCopa = diametroCopa;
        this.diametroRaiz = diametroRaiz;
        this.indiceAlelopatia = indiceAlelopatia;
        this.esSensible = esSensible;
        this.coeficienteKc = coeficienteKc;
    }

    public String getNombreCientifico() { return nombreCientifico; }
    public void setNombreCientifico(String nombreCientifico) { this.nombreCientifico = nombreCientifico; }

    public String getRequerimientoLuz() { return requerimientoLuz; }
    public void setRequerimientoLuz(String requerimientoLuz) { this.requerimientoLuz = requerimientoLuz; }

    public double getPhIdeal() { return phIdeal; }
    public void setPhIdeal(double phIdeal) { this.phIdeal = phIdeal; }

    public double getDiametroCopa() { return diametroCopa; }
    public void setDiametroCopa(double diametroCopa) { this.diametroCopa = diametroCopa; }

    public double getDiametroRaiz() { return diametroRaiz; }
    public void setDiametroRaiz(double diametroRaiz) { this.diametroRaiz = diametroRaiz; }

    public String getIndiceAlelopatia() { return indiceAlelopatia; }
    public void setIndiceAlelopatia(String indiceAlelopatia) { this.indiceAlelopatia = indiceAlelopatia; }

    public boolean isEsSensible() { return esSensible; }
    public void setEsSensible(boolean esSensible) { this.esSensible = esSensible; }

    public double getCoeficienteKc() { return coeficienteKc; }
    public void setCoeficienteKc(double coeficienteKc) { this.coeficienteKc = coeficienteKc; }

    @Override
    public String obtenerResumen() {
        return "[Especie " + getId() + "] " + getNombre() + " (" + nombreCientifico + ")"
                + " | Luz: " + requerimientoLuz
                + " | pH: " + phIdeal
                + " | Copa/Raiz: " + diametroCopa + "m / " + diametroRaiz + "m"
                + " | Alelopatia: " + indiceAlelopatia
                + " | Kc: " + coeficienteKc
                + " | Precio: $" + String.format("%.2f", getPrecioUnitario());
    }
}