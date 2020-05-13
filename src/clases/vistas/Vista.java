package clases.vistas;

import clases.utils.Consola;

public class Vista {

    /**
     * Muestra al usuario vía consola el menú de selección de línea de subte
     *
     * @param CORTE El valor de corte a mostrar.
     * @return La opción elegida
     */
    public String mostrarMenuSeleccionLinea(final String CORTE) {
        System.out.println("CONSULTA DE ESTACIONES DE SUBTE");
        System.out.println("¿Qué línea deseás consultar?");
        System.out.println("Línea [A]");
        System.out.println("Línea [B]");
        System.out.println("Línea [C]");
        System.out.println("Línea [D]");
        System.out.println("Línea [E]");
        System.out.println("Línea [H]");
        System.out.print("Introducí la letra correspondiente ('" + CORTE + "' para salir): ");
        return String.valueOf(Consola.leerCaracter());
    }

    /**
     * Muestra al usuario vía consola el menú de selección de estación de subte
     *
     * @param cantEstaciones La cantidad de estaciones de la línea seleccionada
     * para mostrar en la consola
     * @return La opción elegida
     */
    public int mostrarMenuSeleccionEstacion(int cantEstaciones) {
        System.out.println("¿Qué estación deseás consultar?");
        System.out.print("Introducí el número de estación correspondiente [1 a " + cantEstaciones + "]  (0 para salir): ");
        return Consola.leerEntero();
    }

    /**
     * Muestra un mensaje de error en la consola
     *
     * @param msj El mensaje a mostrar
     */
    public void mostrarMensajeError(String msj) {
        System.err.println(msj);
    }

    /**
     * Muestra un mensaje en la consola
     *
     * @param msj El mensaje a mostrar
     */
    public void mostrarMensaje(String msj) {
        System.out.println(msj);
    }

}
