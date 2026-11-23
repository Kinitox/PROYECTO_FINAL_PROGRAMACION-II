package interfaz;

import modelo.*;
import negocio.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainConsola {
    private static Scanner sc = new Scanner(System.in);

    private static GestorCompatibilidad gestorCompatibilidad = new GestorCompatibilidad();
    private static GestorEspacial gestorEspacial = new GestorEspacial();
    private static GestorRiego gestorRiego = new GestorRiego();
    private static GestorFitosanitario gestorFitosanitario = new GestorFitosanitario();
    private static GestorPresupuesto gestorPresupuesto = new GestorPresupuesto();

    public static void main(String[] args) {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n====================================================");
            System.out.println("  SISTEMA DE OPTIMIZACION BOTANICA Y PAISAJISMO");
            System.out.println("====================================================");
            System.out.println("1. Modulo 1: Compatibilidad Biologica (Gabriel Torres)");
            System.out.println("2. Modulo 2: Modelado Espacial (Joseph Ona)");
            System.out.println("3. Modulo 3: Balance Hidrico (Gabriel Anchaluisa)");
            System.out.println("4. Modulo 4: Diagnostico Fitosanitario (Ariel Quezada)");
            System.out.println("5. Modulo 5: Presupuesto y Cotizacion (Sebastian Simba)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1: menuModulo1(); break;
                case 2: menuModulo2(); break;
                case 3: menuModulo3(); break;
                case 4: menuModulo4(); break;
                case 5: menuModulo5(); break;
                case 0: System.out.println("Cerrando sistema. Hasta pronto!"); break;
                default: System.out.println("Error: Opcion no valida.");
            }
        }
    }

    // ==================== MODULO 1: GABRIEL TORRES ====================
    private static void menuModulo1() {
        System.out.println("\n--- MODULO 1: COMPATIBILIDAD BIOLOGICA ---");
        System.out.println("1. Listar catalogo de especies");
        System.out.println("2. Registrar nueva ficha botanica (RF1.2)");
        System.out.println("3. Evaluar alelopatia y compatibilidad (RF1.1)");
        System.out.print("Opcion: ");
        int op = leerEntero();

        if (op == 1) {
            mostrarCatalogoEspecies();
        } else if (op == 2) {
            System.out.print("ID de especie (ej. E05): ");
            String id = sc.nextLine().trim();
            System.out.print("Nombre comun: ");
            String nombre = sc.nextLine().trim();
            System.out.print("Precio unitario ($): ");
            double precio = leerDecimalPositivo();
            System.out.print("Nombre cientifico: ");
            String cientifico = sc.nextLine().trim();
            System.out.print("Requerimiento de luz (SOL / SEMISOMBRA / SOMBRA): ");
            String luz = sc.nextLine().trim().toUpperCase();
            System.out.print("pH ideal (ej. 6.5): ");
            double ph = leerDecimalPositivo();
            System.out.print("Diametro maximo de copa (m): ");
            double copa = leerDecimalPositivo();
            System.out.print("Diametro maximo de raiz (m): ");
            double raiz = leerDecimalPositivo();
            System.out.print("Indice de alelopatia (ALTA / MEDIA / BAJA): ");
            String alelo = sc.nextLine().trim().toUpperCase();
            System.out.print("Es sensible a alelopatia? (S/N): ");
            boolean sensible = sc.nextLine().trim().equalsIgnoreCase("S");
            System.out.print("Coeficiente de cultivo Kc (ej. 0.85): ");
            double kc = leerDecimalPositivo();

            Especie nueva = new Especie(id, nombre, precio, cientifico, luz, ph, copa, raiz, alelo, sensible, kc);
            if (gestorCompatibilidad.registrarEspecie(nueva)) {
                System.out.println("Exito: Ficha botanica guardada en el catalogo.");
            } else {
                System.out.println("Error: Ya existe una especie con ese ID.");
            }
        } else if (op == 3) {
            mostrarCatalogoEspecies();
            System.out.print("Ingrese ID de Especie 1: ");
            Especie e1 = gestorCompatibilidad.buscarPorId(sc.nextLine().trim());
            System.out.print("Ingrese ID de Especie 2: ");
            Especie e2 = gestorCompatibilidad.buscarPorId(sc.nextLine().trim());

            if (e1 == null || e2 == null) {
                System.out.println("Error: Una o ambas especies no existen.");
                return;
            }
            System.out.print("Ingrese el pH del suelo del terreno (ej. 6.5): ");
            double phSuelo = leerDecimalPositivo();

            System.out.println("\n--- RESULTADO DE COMPATIBILIDAD ---");
            System.out.println(gestorCompatibilidad.evaluarCompatibilidad(e1, e2, phSuelo));
        }
    }

    // ==================== MODULO 2: JOSEPH ONA ====================
    private static void menuModulo2() {
        System.out.println("\n--- MODULO 2: MODELADO ESPACIAL ---");
        System.out.println("1. Calcular densidad de siembra y guardar terreno (RF2.1)");
        System.out.println("2. Consultar historial de terrenos (RF2.2)");
        System.out.print("Opcion: ");
        int op = leerEntero();

        if (op == 1) {
            mostrarCatalogoEspecies();
            System.out.print("ID de especie a sembrar: ");
            Especie esp = gestorCompatibilidad.buscarPorId(sc.nextLine().trim());
            if (esp == null) {
                System.out.println("Error: Especie no encontrada.");
                return;
            }
            System.out.print("ID del terreno (ej. TER01): ");
            String idTer = sc.nextLine().trim();
            System.out.print("Nombre del cliente: ");
            String cliente = sc.nextLine().trim();
            System.out.print("Ubicacion del proyecto: ");
            String ubicacion = sc.nextLine().trim();
            System.out.print("Area total del terreno (m2): ");
            double area = leerDecimalPositivo();
            System.out.print("pH del suelo: ");
            double ph = leerDecimalPositivo();
            System.out.print("Retencion del suelo (BAJA / MEDIA / ALTA): ");
            String retencion = sc.nextLine().trim().toUpperCase();

            int maxPlantas = gestorEspacial.calcularDensidadSiembra(area, esp);
            System.out.println("\nCantidad maxima recomendada: " + maxPlantas + " plantas de " + esp.getNombre());

            System.out.print("Cuantas plantas desea sembrar el cliente?: ");
            int deseadas = leerEntero();
            System.out.println(gestorEspacial.evaluarSaturacion(deseadas, maxPlantas));

            Terreno terreno = new Terreno(idTer, cliente, ubicacion, area, ph, retencion, esp, maxPlantas);
            if (gestorEspacial.registrarTerreno(terreno)) {
                System.out.println("Terreno guardado en el historial.");
            } else {
                System.out.println("Aviso: El ID del terreno ya existia; no se duplico en historial.");
            }
        } else if (op == 2) {
            System.out.print("Ingrese ID de terreno o nombre de cliente (Enter para ver todos): ");
            String criterio = sc.nextLine().trim();
            ArrayList<Terreno> lista = criterio.isEmpty()
                    ? gestorEspacial.getHistorialTerrenos()
                    : gestorEspacial.buscarTerrenos(criterio);

            if (lista.isEmpty()) {
                System.out.println("No se encontraron terrenos registrados.");
            } else {
                for (int i = 0; i < lista.size(); i++) {
                    System.out.println(lista.get(i).toString());
                }
            }
        }
    }

    // ==================== MODULO 3: GABRIEL ANCHALUISA ====================
    private static void menuModulo3() {
        System.out.println("\n--- MODULO 3: BALANCE HIDRICO ---");
        System.out.println("Clima actual -> " + gestorRiego.getClimaActual().toString());
        System.out.println("1. Proyectar riego dinamico semanal (RF3.1)");
        System.out.println("2. Actualizar variables climaticas (RF3.2)");
        System.out.print("Opcion: ");
        int op = leerEntero();

        if (op == 1) {
            mostrarCatalogoEspecies();
            System.out.print("ID de especie: ");
            Especie esp = gestorCompatibilidad.buscarPorId(sc.nextLine().trim());
            if (esp == null) {
                System.out.println("Error: Especie no encontrada.");
                return;
            }
            System.out.print("Area del cuadrante a regar (m2): ");
            double area = leerDecimalPositivo();
            System.out.print("Retencion de humedad del sustrato (BAJA / MEDIA / ALTA): ");
            String retencion = sc.nextLine().trim().toUpperCase();

            double temp = gestorRiego.getClimaActual().getTemperaturaPromedio();
            double litrosM2 = gestorRiego.calcularRiegoSemanal(esp, temp, retencion);

            System.out.println("\n--- PROYECCION DE RIEGO SEMANAL (FAO-56) ---");
            System.out.println(gestorRiego.generarCronograma(litrosM2, area, retencion));
        } else if (op == 2) {
            System.out.print("Nueva temperatura ambiente promedio (C): ");
            double temp = leerDecimal();
            System.out.print("Nueva humedad relativa (%): ");
            double hum = leerDecimal();

            if (gestorRiego.actualizarVariablesClimaticas(temp, hum)) {
                System.out.println("Variables climaticas actualizadas: " + gestorRiego.getClimaActual().toString());
            } else {
                System.out.println("Error: Valores fuera de rango logico.");
            }
        }
    }

    // ==================== MODULO 4: ARIEL QUEZADA ====================
    private static void menuModulo4() {
        System.out.println("\n--- MODULO 4: DIAGNOSTICO FITOSANITARIO ---");
        System.out.println("1. Ejecutar motor de diagnostico de plagas (RF4.1)");
        System.out.println("2. Registrar nuevo tratamiento (RF4.2)");
        System.out.println("3. Listar catalogo de tratamientos");
        System.out.print("Opcion: ");
        int op = leerEntero();

        if (op == 1) {
            String[] sintomas = gestorFitosanitario.getSintomas();
            boolean[] respuestas = new boolean[sintomas.length];

            System.out.println("Responda S (Si) o N (No) para cada sintoma observado:");
            for (int i = 0; i < sintomas.length; i++) {
                System.out.print("- Presenta " + sintomas[i] + "? (S/N): ");
                respuestas[i] = sc.nextLine().trim().equalsIgnoreCase("S");
            }

            System.out.println("\n--- RESULTADO DEL DIAGNOSTICO ---");
            System.out.println(gestorFitosanitario.diagnosticarAfeccion(
                    respuestas[0], respuestas[1], respuestas[2]));
        } else if (op == 2) {
            System.out.print("ID del tratamiento (ej. T04): ");
            String id = sc.nextLine().trim();
            System.out.print("Nombre del producto: ");
            String nombre = sc.nextLine().trim();
            System.out.print("Precio unitario ($): ");
            double precio = leerDecimalPositivo();
            System.out.print("Tipo (ORGANICO / QUIMICO): ");
            String tipo = sc.nextLine().trim().toUpperCase();
            System.out.print("Dosis recomendada: ");
            String dosis = sc.nextLine().trim();
            System.out.print("Afeccion que trata: ");
            String afeccion = sc.nextLine().trim();

            Tratamiento nuevo = new Tratamiento(id, nombre, precio, tipo, dosis, afeccion);
            if (gestorFitosanitario.registrarTratamiento(nuevo)) {
                System.out.println("Exito: Tratamiento registrado.");
            } else {
                System.out.println("Error: Ya existe un tratamiento con ese ID.");
            }
        } else if (op == 3) {
            mostrarCatalogoTratamientos();
        }
    }

    // ==================== MODULO 5: SEBASTIAN SIMBA ====================
    private static void menuModulo5() {
        System.out.println("\n--- MODULO 5: PRESUPUESTO Y COTIZACION ---");
        System.out.println("1. Generar proyeccion financiera de proyecto (RF5.1)");
        System.out.println("2. Actualizar catalogo de precios (RF5.2)");
        System.out.println("3. Ver historial de cotizaciones");
        System.out.print("Opcion: ");
        int op = leerEntero();

        if (op == 1) {
            if (gestorEspacial.getHistorialTerrenos().isEmpty()) {
                System.out.println("Aviso: Primero registre y calcule un terreno en el Modulo 2.");
                return;
            }
            System.out.println("Terrenos disponibles:");
            for (int i = 0; i < gestorEspacial.getHistorialTerrenos().size(); i++) {
                System.out.println(gestorEspacial.getHistorialTerrenos().get(i).toString());
            }
            System.out.print("Ingrese ID del terreno a cotizar: ");
            Terreno terreno = gestorEspacial.buscarPorId(sc.nextLine().trim());
            if (terreno == null) {
                System.out.println("Error: Terreno no encontrado.");
                return;
            }

            mostrarCatalogoTratamientos();
            System.out.print("ID de tratamiento a incluir (Enter si no requiere): ");
            String idTrat = sc.nextLine().trim();
            Tratamiento trat = idTrat.isEmpty() ? null : gestorFitosanitario.buscarPorId(idTrat);
            double costoInsumos = (trat != null) ? trat.getPrecioUnitario() : 0.0;

            System.out.print("Volumen de sustrato requerido (m3): ");
            double volSustrato = leerDecimalPositivo();
            System.out.print("Margen de ganancia deseado (%): ");
            double margen = leerDecimalPositivo();
            System.out.print("Costo de envio ($): ");
            double envio = leerDecimalPositivo();

            int cantPlantas = terreno.getCantidadPlantasCalculada();
            double precioPlanta = terreno.getEspecieAsignada().getPrecioUnitario();
            double precioSustrato = gestorPresupuesto.getPrecioSustratoM3();

            double subtotal = gestorPresupuesto.calcularSubtotal(
                    cantPlantas, precioPlanta, volSustrato, precioSustrato, costoInsumos);
            double total = gestorPresupuesto.generarCotizacion(
                    cantPlantas, precioPlanta, volSustrato, precioSustrato, costoInsumos, margen, envio);

            String idCot = "COT-" + (gestorPresupuesto.getHistorialCotizaciones().size() + 1);
            Cotizacion cot = new Cotizacion(idCot, terreno, trat, volSustrato, precioSustrato, margen, envio, subtotal, total);
            gestorPresupuesto.guardarCotizacion(cot);

            System.out.println("\n" + cot.generarResumenTexto());
        } else if (op == 2) {
            // Lista polimorfica combinando Especie y Tratamiento como ProductoVivero
            ArrayList<ProductoVivero> todosLosProductos = new ArrayList<>();
            todosLosProductos.addAll(gestorCompatibilidad.getCatalogoEspecies());
            todosLosProductos.addAll(gestorFitosanitario.getCatalogoTratamientos());

            System.out.println("\n--- CATALOGO GENERAL DE PRECIOS ---");
            for (int i = 0; i < todosLosProductos.size(); i++) {
                System.out.println(todosLosProductos.get(i).obtenerResumen());
            }
            System.out.println("[SUST] Sustrato por m3 - $" + String.format("%.2f", gestorPresupuesto.getPrecioSustratoM3()));

            System.out.print("Ingrese ID del producto a actualizar (o SUST para sustrato): ");
            String idProd = sc.nextLine().trim();
            System.out.print("Nuevo precio base ($): ");
            double nuevoPrecio = leerDecimalPositivo();

            boolean exito;
            if (idProd.equalsIgnoreCase("SUST")) {
                exito = gestorPresupuesto.actualizarPrecioSustrato(nuevoPrecio);
            } else {
                exito = gestorPresupuesto.actualizarPrecioProducto(todosLosProductos, idProd, nuevoPrecio);
            }

            if (exito) {
                System.out.println("Exito: Precio actualizado correctamente.");
            } else {
                System.out.println("Error: Producto no encontrado o precio invalido.");
            }
        } else if (op == 3) {
            ArrayList<Cotizacion> lista = gestorPresupuesto.getHistorialCotizaciones();
            if (lista.isEmpty()) {
                System.out.println("No existen cotizaciones registradas.");
            } else {
                for (int i = 0; i < lista.size(); i++) {
                    System.out.println(lista.get(i).generarResumenTexto());
                }
            }
        }
    }

    // ==================== UTILIDADES DE CONSOLA ====================
    private static void mostrarCatalogoEspecies() {
        System.out.println("\nCatalogo de Especies:");
        ArrayList<Especie> lista = gestorCompatibilidad.getCatalogoEspecies();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).obtenerResumen());
        }
    }

    private static void mostrarCatalogoTratamientos() {
        System.out.println("\nCatalogo de Tratamientos:");
        ArrayList<Tratamiento> lista = gestorFitosanitario.getCatalogoTratamientos();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).obtenerResumen());
        }
    }

    private static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida. Ingrese un numero entero: ");
            }
        }
    }

    private static double leerDecimal() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida. Ingrese un numero decimal: ");
            }
        }
    }

    private static double leerDecimalPositivo() {
        while (true) {
            double valor = leerDecimal();
            if (valor >= 0) {
                return valor;
            }
            System.out.print("El valor no puede ser negativo. Intente nuevamente: ");
        }
    }
}