package clases;

import java.io.FileNotFoundException;
import java.io.IOException;

import clases.utils.LectorTexto;
import clases.modelo.LineaSubte;
import clases.vistas.Vista;

public class Controlador {
	private Vista v;

	public Controlador() {
		this.v = new Vista();
		generarLineasDeSubte();
	}

	/**
	 * Inicia la aplicación
	 */
	public void iniciar() {
		String opcLinea = v.mostrarMenuSeleccionLinea();
		while (!opcLinea.equals("S") && !opcLinea.equals("s")) {
			LineaSubte lineaSeleccionada = obtenerLineaSegunLetra(opcLinea);
			int cantEstaciones = lineaSeleccionada.getCantEstaciones();
			int opcEstacion = v.mostrarMenuSeleccionEstacion(cantEstaciones);
			while (opcEstacion != 0) {
				String nomEstacion = lineaSeleccionada.getEstacion(opcEstacion - 1);
				v.mostrarMensaje("La estación correspondiente es: " + nomEstacion);
				opcEstacion = v.mostrarMenuSeleccionEstacion(cantEstaciones);
			}
			opcLinea = v.mostrarMenuSeleccionLinea();
		}
		System.out.println("FIN DEL PROGRAMA");
	}

	/**
	 * Busca una línea de subte según la letra. Si no la encuentra, devuelve null
	 * 
	 * @param letra El nombre de la línea de subte
	 * @return La línea de subte correspondiente
	 * @throws Exception
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
	 * Carga las estaciones de cada línea de subte desde los archivos de datos
	 * 
	 * @throws FileNotFoundException si no se encuentra el archivo
	 * @throws IOException           si ocurre un error al leer el archivo
	 */
	private void generarLineasDeSubte() {
		try {
			LineaSubte[] lineas = LineaSubte.values();
			for (int i = 0; i < lineas.length; i++) {
				LectorTexto lt = new LectorTexto("src/datos/linea" + lineas[i].getNombre() + ".txt");
				for (String nombreEstacion : lt.leerLineas()) {
					lineas[i].addEstacion(nombreEstacion);
				}
			}
		} catch (FileNotFoundException fnfe) {
			v.mostrarMensajeError("No se encontró el archivo con los datos de estaciones");
		} catch (IOException ioe) {
			v.mostrarMensajeError("Error de lectura");
		}
	}
}
