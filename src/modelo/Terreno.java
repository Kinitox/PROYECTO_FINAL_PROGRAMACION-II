package modelo;

public class Terreno {
    private String idTerreno;
    private String nombreCliente;
    private String ubicacion;
    private double areaM2;
    private double phSuelo;
    private String retencionSuelo; // "BAJA", "MEDIA", "ALTA"
    private Especie especieAsignada;
    private int cantidadPlantasCalculada;

    public Terreno(String idTerreno, String nombreCliente, String ubicacion,
                   double areaM2, double phSuelo, String retencionSuelo,
                   Especie especieAsignada, int cantidadPlantasCalculada) {
        this.idTerreno = idTerreno;
        this.nombreCliente = nombreCliente;
        this.ubicacion = ubicacion;
        this.areaM2 = areaM2;
        this.phSuelo = phSuelo;
        this.retencionSuelo = retencionSuelo;
        this.especieAsignada = especieAsignada;
        this.cantidadPlantasCalculada = cantidadPlantasCalculada;
    }

    public String getIdTerreno() { return idTerreno; }
    public String getNombreCliente() { return nombreCliente; }
    public String getUbicacion() { return ubicacion; }
    public double getAreaM2() { return areaM2; }
    public double getPhSuelo() { return phSuelo; }
    public String getRetencionSuelo() { return retencionSuelo; }
    public Especie getEspecieAsignada() { return especieAsignada; }
    public int getCantidadPlantasCalculada() { return cantidadPlantasCalculada; }

    public void setEspecieAsignada(Especie especieAsignada) {
        this.especieAsignada = especieAsignada;
    }

    public void setCantidadPlantasCalculada(int cantidadPlantasCalculada) {
        this.cantidadPlantasCalculada = cantidadPlantasCalculada;
    }

    @Override
    public String toString() {
        String nombreEspecie = (especieAsignada != null) ? especieAsignada.getNombre() : "Sin asignar";
        return "[" + idTerreno + "] Cliente: " + nombreCliente
                + " | Ubicacion: " + ubicacion
                + " | Area: " + areaM2 + " m2"
                + " | pH: " + phSuelo
                + " | Retencion: " + retencionSuelo
                + " | Especie: " + nombreEspecie
                + " | Max. plantas: " + cantidadPlantasCalculada;
    }
}
