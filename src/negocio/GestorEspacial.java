package negocio;

import modelo.Especie;
import modelo.Terreno;
import java.util.ArrayList;

public class GestorEspacial {
    private ArrayList<Terreno> historialTerrenos;

    public GestorEspacial() {
        this.historialTerrenos = new ArrayList<>();
    }

    // Calcular Densidad de Siembra (Logica Compleja - Seccion 5.2)
    public int calcularDensidadSiembra(double areaTerrenoM2, Especie especie) {
        if (areaTerrenoM2 <= 0 || especie == null) {
            return 0;
        }
        double sumaDiametros = especie.getDiametroCopa() + especie.getDiametroRaiz();
        double areaEfectivaPlanta = sumaDiametros * sumaDiametros;

        if (areaEfectivaPlanta <= 0) {
            return 0;
        }

        // Se multiplica por 0.85 para reservar 15% de circulacion y drenaje
        double areaUtil = areaTerrenoM2 * 0.85;
        return (int) (areaUtil / areaEfectivaPlanta);
    }

    public String evaluarSaturacion(int cantidadDeseada, int cantidadMaxima) {
        if (cantidadDeseada <= cantidadMaxima) {
            return "DISENO OPTIMO: Las " + cantidadDeseada
                    + " plantas caben respetando el 15% de margen (Maximo: " + cantidadMaxima + ").";
        } else {
            int exceso = cantidadDeseada - cantidadMaxima;
            return "ADVERTENCIA DE SATURACION: Excede la capacidad en " + exceso
                    + " plantas. Maximo recomendado: " + cantidadMaxima + ".";
        }
    }

    //Registrar y Consultar Historial de Terrenos (CRUD)
    public boolean registrarTerreno(Terreno nuevo) {
        if (buscarPorId(nuevo.getIdTerreno()) != null) {
            return false;
        }
        historialTerrenos.add(nuevo);
        return true;
    }

    public Terreno buscarPorId(String idTerreno) {
        for (int i = 0; i < historialTerrenos.size(); i++) {
            if (historialTerrenos.get(i).getIdTerreno().equalsIgnoreCase(idTerreno)) {
                return historialTerrenos.get(i);
            }
        }
        return null;
    }

    public ArrayList<Terreno> buscarTerrenos(String criterio) {
        ArrayList<Terreno> resultados = new ArrayList<>();
        String texto = criterio.toLowerCase();
        for (int i = 0; i < historialTerrenos.size(); i++) {
            Terreno t = historialTerrenos.get(i);
            if (t.getIdTerreno().toLowerCase().contains(texto)
                    || t.getNombreCliente().toLowerCase().contains(texto)) {
                resultados.add(t);
            }
        }
        return resultados;
    }

    public ArrayList<Terreno> getHistorialTerrenos() {
        return historialTerrenos;
    }
}