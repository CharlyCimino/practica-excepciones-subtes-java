package clases;

import java.io.FileNotFoundException;
import java.io.IOException;

import clases.utils.LectorTexto;
import clases.modelo.LineaSubte;
import clases.vistas.Vista;

public class Controlador {

    private static final String CORTE_PROGRAMA = "S";
    private static final int CORTE_ESTACION = 0;
    private Vista v;

    public Controlador() {
        this.v = new Vista();
    }

    /**
     * Inicia la aplicación
     */
    public void iniciar() {
        try {
            generarLineasDeSubte();
            String opcLinea = v.mostrarMenuSeleccionLinea(CORTE_PROGRAMA);
            while (!opcLinea.equalsIgnoreCase(CORTE_PROGRAMA)) {
                LineaSubte lineaSeleccionada = obtenerLineaSegunLetra(opcLinea);
                subMenuSeleccionEstacion(lineaSeleccionada);
                opcLinea = v.mostrarMenuSeleccionLinea(CORTE_PROGRAMA);
            }
        } catch (FileNotFoundException fnfe) {
            v.mostrarMensajeError("No se encontró el archivo con los datos de las estaciones ");
        } catch (IOException ioe) {
            v.mostrarMensajeError("Error de lectura: " + ioe.getMessage());
        }
        v.mostrarMensaje("*** FIN DEL PROGRAMA ***");
    }

    /**
     * Permite mostrar al usuario el submenú de selección de estación de la
     * línea seleccionada.
     *
     * @param lineaSeleccionada La línea de subte seleccionada por el usuario.
     */
    private void subMenuSeleccionEstacion(LineaSubte lineaSeleccionada) {
        int opcEstacion = elegirEstacion(lineaSeleccionada);
        while (opcEstacion != CORTE_ESTACION) {
            String nomEstacion = lineaSeleccionada.getEstacion(opcEstacion);
            v.mostrarMensaje("La estación correspondiente es: " + nomEstacion);
            opcEstacion = elegirEstacion(lineaSeleccionada);
        }
    }

    /**
     * Permite elegir un número de estación de acuerdo a la línea seleccionada.
     *
     * @param lineaSeleccionada La línea de subte seleccionada por el usuario.
     * @return El número de estación seleccionado por el usuario o el valor de
     * corte.
     */
    private int elegirEstacion(LineaSubte lineaSeleccionada) {
        int cantEstaciones = lineaSeleccionada.getCantEstaciones();
        int opcEstacion = v.mostrarMenuSeleccionEstacion(cantEstaciones);
        return opcEstacion;
    }

    /**
     * Busca una línea de subte según la letra. Si no la encuentra, devuelve
     * null
     *
     * @param letra La letra de la línea de subte.
     * @return La línea de subte correspondiente.
     */
    private LineaSubte obtenerLineaSegunLetra(String letra) {
        LineaSubte[] lineas = LineaSubte.values();
        LineaSubte lineaADevolver = null;
        int i = 0;
        while (lineaADevolver == null && i < lineas.length) {
            if (lineas[i].getNombre().equals(letra)) {
                lineaADevolver = lineas[i];
            }
            i++;
        }
        return lineaADevolver;
    }

    /**
     * Carga las estaciones de cada línea de subte desde los archivos de datos.
     *
     * @throws FileNotFoundException si no se encuentra algún archivo de datos.
     * @throws IOException si ocurre un error de entrada/salida.
     */
    private void generarLineasDeSubte() throws FileNotFoundException, IOException {
        LineaSubte[] lineas = LineaSubte.values();
        for (int i = 0; i < lineas.length; i++) {
            LectorTexto lt = new LectorTexto("src/datos/linea" + lineas[i].getNombre() + ".txt");
            for (String nombreEstacion : lt.leerLineas()) {
                lineas[i].addEstacion(nombreEstacion);
            }
        }
    }
}
