package negocio;

import modelo.Especie;
import modelo.VariablesClimaticas;

public class GestorRiego {
    private VariablesClimaticas climaActual;

    // Arreglos estaticos paralelos para la Tabla 14 (Factores de retencion)
    private final String[] TIPOS_RETENCION = {"BAJA", "MEDIA", "ALTA"};
    private final double[] FACTORES_RETENCION = {1.2, 1.0, 0.8};

    public GestorRiego() {
        // Clima base referencial de Nayon, Quito owo
        this.climaActual = new VariablesClimaticas("Nayon - Quito", 18.5, 65.0);
    }

    public VariablesClimaticas getClimaActual() {
        return climaActual;
    }

    // RF3.2: Actualizar Variables Climaticas (CRUD)
    public boolean actualizarVariablesClimaticas(double nuevaTemp, double nuevaHumedad) {
        if (nuevaTemp < -10 || nuevaTemp > 55 || nuevaHumedad < 0 || nuevaHumedad > 100) {
            return false;
        }
        climaActual.setTemperaturaPromedio(nuevaTemp);
        climaActual.setHumedadRelativa(nuevaHumedad);
        return true;
    }

    public double obtenerFactorRetencion(String retencionSuelo) {
        for (int i = 0; i < TIPOS_RETENCION.length; i++) {
            if (TIPOS_RETENCION[i].equalsIgnoreCase(retencionSuelo)) {
                return FACTORES_RETENCION[i];
            }
        }
        return 1.0; // Valor por defecto (MEDIA)
    }

    // RF3.1: Proyectar Riego Dinamico (Logica Compleja - Modelo FAO-56, Seccion 5.3) creo
    public double calcularRiegoSemanal(Especie especie, double tempPromedio, String retencionSuelo) {
        double etc = (0.46 * tempPromedio + 8.0) * especie.getCoeficienteKc() * 7.0;
        double factor = obtenerFactorRetencion(retencionSuelo);
        return etc * factor; // Litros por semana por m2
    }

    public String generarCronograma(double litrosPorM2, double areaM2, String retencionSuelo) {
        double totalLitrosSemana = litrosPorM2 * areaM2;
        int frecuenciasPorSemana;

        if (retencionSuelo.equalsIgnoreCase("BAJA")) {
            frecuenciasPorSemana = 4; // Suelo arenoso drena rapido (se seca)
        } else if (retencionSuelo.equalsIgnoreCase("ALTA")) {
            frecuenciasPorSemana = 2; // Suelo arcilloso retiene mas humedad y aja
        } else {
            frecuenciasPorSemana = 3; // Suelo franco
        }

        double litrosPorSesion = totalLitrosSemana / frecuenciasPorSemana;

        return "Riego por m2 semanal: " + String.format("%.2f", litrosPorM2) + " L/m2\n"
                + "Total semanal para " + areaM2 + " m2: " + String.format("%.2f", totalLitrosSemana) + " litros\n"
                + "Cronograma sugerido: " + frecuenciasPorSemana + " riegos por semana de "
                + String.format("%.2f", litrosPorSesion) + " litros por sesion.";
    }
}