package negocio;

import modelo.Especie;
import java.util.ArrayList;

public class GestorCompatibilidad {
    private ArrayList<Especie> catalogoEspecies;

    public GestorCompatibilidad() {
        this.catalogoEspecies = new ArrayList<>();
        cargarEspeciesIniciales();
    }

    private void cargarEspeciesIniciales() {
        catalogoEspecies.add(new Especie("E01", "Rosa Ornamental", 3.50,
                "Rosa spp.", "SOL", 6.5, 0.50, 0.30, "BAJA", true, 0.90));
        catalogoEspecies.add(new Especie("E02", "Lavanda", 2.75,
                "Lavandula angustifolia", "SOL", 6.8, 0.45, 0.25, "BAJA", false, 0.60));
        catalogoEspecies.add(new Especie("E03", "Hortensia", 5.00,
                "Hydrangea macrophylla", "SEMISOMBRA", 5.5, 0.70, 0.40, "MEDIA", true, 1.05));
        catalogoEspecies.add(new Especie("E04", "Eucalipto Decorativo", 6.20,
                "Eucalyptus cinerea", "SOL", 6.2, 0.90, 0.60, "ALTA", false, 0.85));
    }

    public boolean registrarEspecie(Especie nueva) {
        if (buscarPorId(nueva.getId()) != null) {
            return false;
        }
        catalogoEspecies.add(nueva);
        return true;
    }

    public Especie buscarPorId(String id) {
        for (int i = 0; i < catalogoEspecies.size(); i++) {
            Especie actual = catalogoEspecies.get(i);
            if (actual.getId().equalsIgnoreCase(id)) {
                return actual;
            }
        }
        return null;
    }

    public ArrayList<Especie> getCatalogoEspecies() {
        return catalogoEspecies;
    }

    public String evaluarCompatibilidad(Especie e1, Especie e2, double phSuelo) {
        int puntaje = 100;
        String detalles = "";

        if (!e1.getRequerimientoLuz().equalsIgnoreCase(e2.getRequerimientoLuz())) {
            puntaje -= 30;
            detalles += "- Penalizacion (-30): Distinto requerimiento de luz.\n";
        }

        double difPh1 = Math.abs(e1.getPhIdeal() - phSuelo);
        double difPh2 = Math.abs(e2.getPhIdeal() - phSuelo);
        if (difPh1 > 1.0 || difPh2 > 1.0) {
            puntaje -= 25;
            detalles += "- Penalizacion (-25): El pH del suelo (" + phSuelo + ") se aleja del ideal.\n";
        }

        boolean conflictoAlelopatia = (e1.getIndiceAlelopatia().equalsIgnoreCase("ALTA") && e2.isEsSensible())
                || (e2.getIndiceAlelopatia().equalsIgnoreCase("ALTA") && e1.isEsSensible());
        if (conflictoAlelopatia) {
            puntaje -= 40;
            detalles += "- Penalizacion (-40): Conflicto severo de alelopatia con especie sensible.\n";
        }

        if (puntaje < 0) {
            puntaje = 0;
        }

        String veredicto;
        if (puntaje >= 70) {
            veredicto = "Viabilidad Alta: Especies compatibles";
        } else if (puntaje >= 40) {
            veredicto = "Viabilidad Media: Requiere separacion o ajuste de sustrato";
        } else {
            veredicto = "Incompatible: Alto riesgo de mortalidad por competencia o alelopatia";
        }

        return "Puntaje final: " + puntaje + "/100\n"
                + "Resultado: " + veredicto + "\n"
                + (detalles.isEmpty() ? "- Sin conflictos biologicos detectados." : detalles);
    }
}
