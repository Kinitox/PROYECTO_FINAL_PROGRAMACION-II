package modelo;

public class VariablesClimaticas {
    private String zona;
    private double temperaturaPromedio; // en grados Celsius
    private double humedadRelativa;     // en porcentaje (0 - 100)

    public VariablesClimaticas(String zona, double temperaturaPromedio, double humedadRelativa) {
        this.zona = zona;
        this.temperaturaPromedio = temperaturaPromedio;
        this.humedadRelativa = humedadRelativa;
    }

    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }

    public double getTemperaturaPromedio() { return temperaturaPromedio; }
    public void setTemperaturaPromedio(double temperaturaPromedio) {
        this.temperaturaPromedio = temperaturaPromedio;
    }

    public double getHumedadRelativa() { return humedadRelativa; }
    public void setHumedadRelativa(double humedadRelativa) {
        this.humedadRelativa = humedadRelativa;
    }

    @Override
    public String toString() {
        return "Zona: " + zona
                + " | Temperatura promedio: " + temperaturaPromedio + " C"
                + " | Humedad relativa: " + humedadRelativa + "%";
    }
}