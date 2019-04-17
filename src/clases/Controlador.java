package clases;

import java.io.FileNotFoundException;
import java.io.IOException;
import clases.utils.LectorTexto;
import clases.misexcepciones.EstacionInvalidaException;
import clases.misexcepciones.LineaInvalidaException;
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
			try {
				LineaSubte lineaSeleccionada = obtenerLineaSegunLetra(opcLinea);
				int cantEstaciones = lineaSeleccionada.getCantEstaciones();
				int opcEstacion = v.mostrarMenuSeleccionEstacion(cantEstaciones);
				while (opcEstacion != 0) {
					String nomEstacion = lineaSeleccionada.getEstacion(opcEstacion - 1);
					v.mostrarMensaje("La estación correspondiente es: " + nomEstacion);
					opcEstacion = v.mostrarMenuSeleccionEstacion(cantEstaciones);
				}
			} catch (StringIndexOutOfBoundsException e) {
				v.mostrarMensajeError("No se ingresó un valor");
			} catch (NumberFormatException e) {
				v.mostrarMensajeError("No se pudo convertir la entrada a un número");
			} catch (LineaInvalidaException e) {
				v.mostrarMensajeError(e.getMessage());
			} catch (EstacionInvalidaException e) {
				v.mostrarMensajeError(e.getMessage());
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
	 * @throws LineaInvalidaException Si no encuentra la línea
	 */
	private LineaSubte obtenerLineaSegunLetra(String letra) throws LineaInvalidaException {
		LineaSubte[] lineas = LineaSubte.values();
		LineaSubte lineaADevolver = null;
		int i = 0;
		while (lineaADevolver == null && i < lineas.length) {
			if (lineas[i].getNombre().equals(letra)) {
				lineaADevolver = lineas[i];
			}
			i++;
		}
		if (lineaADevolver == null) {
			throw new LineaInvalidaException("Línea inválida");
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
