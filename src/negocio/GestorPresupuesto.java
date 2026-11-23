package negocio;

import modelo.Cotizacion;
import modelo.ProductoVivero;
import java.util.ArrayList;

public class GestorPresupuesto {
    private ArrayList<Cotizacion> historialCotizaciones;
    private double precioSustratoM3;

    public GestorPresupuesto() {
        this.historialCotizaciones = new ArrayList<>();
        this.precioSustratoM3 = 25.00; // Precio base referencial por m3 de sustrato
    }

    public double getPrecioSustratoM3() {
        return precioSustratoM3;
    }

    public boolean actualizarPrecioSustrato(double nuevoPrecio) {
        if (nuevoPrecio <= 0) {
            return false;
        }
        this.precioSustratoM3 = nuevoPrecio;
        return true;
    }

    public double calcularSubtotal(int cantPlantas, double precioPlanta,
                                   double volSustrato, double precioSustrato,
                                   double costoInsumos) {
        return (cantPlantas * precioPlanta) + (volSustrato * precioSustrato) + costoInsumos;
    }

    //Generar Proyeccion Financiera (Logica Compleja - Seccion 5.5)
    public double generarCotizacion(int cantPlantas, double precioPlanta,
                                    double volSustrato, double precioSustrato,
                                    double costoInsumos, double margenPct,
                                    double costoEnvio) {
        double subtotal = calcularSubtotal(cantPlantas, precioPlanta, volSustrato, precioSustrato, costoInsumos);
        return subtotal * (1.0 + (margenPct / 100.0)) + costoEnvio;
    }

    public void guardarCotizacion(Cotizacion cotizacion) {
        historialCotizaciones.add(cotizacion);
    }

    public ArrayList<Cotizacion> getHistorialCotizaciones() {
        return historialCotizaciones;
    }

    // Actualizar Catalogo de Precios usando Polimorfismo (ProductoVivero)
    public boolean actualizarPrecioProducto(ArrayList<ProductoVivero> catalogo,
                                            String idProducto, double nuevoPrecio) {
        if (nuevoPrecio <= 0) {
            return false;
        }
        for (int i = 0; i < catalogo.size(); i++) {
            ProductoVivero p = catalogo.get(i);
            if (p.getId().equalsIgnoreCase(idProducto)) {
                p.setPrecioUnitario(nuevoPrecio);
                return true;
            }
        }
        return false;
    }
}