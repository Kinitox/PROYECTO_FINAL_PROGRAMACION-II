package negocio;

import modelo.Tratamiento;
import java.util.ArrayList;

public class GestorFitosanitario {
    private ArrayList<Tratamiento> catalogoTratamientos;

    private final String[] SINTOMAS = {
            "Hojas amarillas",
            "Manchas negras",
            "Tallo debil"
    };

    public GestorFitosanitario() {
        this.catalogoTratamientos = new ArrayList<>();
        cargarTratamientosIniciales();
    }

    private void cargarTratamientosIniciales() {
        catalogoTratamientos.add(new Tratamiento("T01", "Fungicida de Cobre", 8.50,
                "QUIMICO", "15 ml por litro de agua", "Infeccion fungica (hongos/roya)"));
        catalogoTratamientos.add(new Tratamiento("T02", "Fertilizante NPK Rico en Nitrogeno", 6.00,
                "ORGANICO", "30 g por planta cada 15 dias", "Deficiencia de nitrogeno"));
        catalogoTratamientos.add(new Tratamiento("T03", "Kit Fungicida + Insecticida Organico", 14.00,
                "ORGANICO", "20 ml por litro cada 7 dias", "Plaga severa / infeccion mixta"));
    }

    public String[] getSintomas() {
        return SINTOMAS;
    }

    public ArrayList<Tratamiento> getCatalogoTratamientos() {
        return catalogoTratamientos;
    }

    public boolean registrarTratamiento(Tratamiento nuevo) {
        if (buscarPorId(nuevo.getId()) != null) {
            return false;
        }
        catalogoTratamientos.add(nuevo);
        return true;
    }

    public Tratamiento buscarPorId(String id) {
        for (int i = 0; i < catalogoTratamientos.size(); i++) {
            if (catalogoTratamientos.get(i).getId().equalsIgnoreCase(id)) {
                return catalogoTratamientos.get(i);
            }
        }
        return null;
    }

    public String diagnosticarAfeccion(boolean hojasAmarillas, boolean manchasNegras, boolean talloDebil) {
        if (hojasAmarillas && manchasNegras && talloDebil) {
            return "Diagnostico: 90% Plaga severa / infeccion mixta.\n"
                    + "Tratamiento recomendado: Aislamiento, poda sanitaria y fungicida mas insecticida organico.";
        } else if (manchasNegras && !talloDebil) {
            return "Diagnostico: 80% Infeccion fungica (hongos/roya).\n"
                    + "Tratamiento recomendado: Aplicar fungicida a base de cobre o extracto de cola de caballo.";
        } else if (hojasAmarillas && !manchasNegras && !talloDebil) {
            return "Diagnostico: 75% Deficiencia de nitrogeno.\n"
                    + "Tratamiento recomendado: Aplicar fertilizante rico en nitrogeno (NPK) y revisar el pH.";
        } else if (hojasAmarillas && !manchasNegras && talloDebil) {
            return "Diagnostico: 70% Exceso de riego / pudricion.\n"
                    + "Tratamiento recomendado: Suspender el riego por 5 dias y mejorar el drenaje del sustrato.";
        } else if (!hojasAmarillas && !manchasNegras && talloDebil) {
            return "Diagnostico: 65% Falta de luz / estres termico.\n"
                    + "Tratamiento recomendado: Reubicar el especimen a una zona con mayor exposicion solar.";
        } else if (!hojasAmarillas && manchasNegras && talloDebil) {
            return "Diagnostico: 75% Infeccion fungica avanzada con debilitamiento estructural.\n"
                    + "Tratamiento recomendado: Poda de hojas afectadas y aplicacion inmediata de fungicida.";
        } else {
            return "Diagnostico: Planta aparentemente sana.\n"
                    + "Tratamiento recomendado: Mantener el plan regular de riego y nutricion preventiva.";
        }
    }
}