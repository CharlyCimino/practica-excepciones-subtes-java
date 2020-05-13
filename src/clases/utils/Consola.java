package clases.utils;

import java.util.Scanner;

public class Consola {

    private static Scanner sc = new Scanner(System.in);

    /**
     * Lee un caracter desde la consola.
     * Si se ingresa más de una letra, solo se lee la primera.
     * Si la entrada se deja en blanco, se devuelve un caracter nulo.
     * @return el caracter leido
     */
    public static char leerCaracter() {
        String entrada = sc.nextLine();
        return entrada.isEmpty() ? 0 : entrada.charAt(0);
    }

    /**
     * Lee un entero desde la consola.
     * @return el entero leído
     * @throws NumberFormatException si la entrada no representa un número entero.
     */
    public static int leerEntero() {
        int entero = Integer.parseInt(sc.nextLine()); // Lee una cadena y convierte a entero
        return entero;
    }
}
