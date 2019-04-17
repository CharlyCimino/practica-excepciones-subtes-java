
package clases.modelo;

import java.util.ArrayList;

public enum LineaSubte {
	
	A("A"), B("B"), C("C"), D("D"), E("E"), H("H");
	
    private String nombre;
    private ArrayList<String> estaciones;

    private LineaSubte(String nombre) {
        this.nombre = nombre;
        this.estaciones = new ArrayList<String>();
    }
    
    public void addEstacion(String estacion) {
    	this.estaciones.add(estacion);
    }

    public String getEstacion(int indice) {
        return this.estaciones.get(indice);
    }  
    
    public int getCantEstaciones() {
        return this.estaciones.size();
    }  

    public String getNombre() {
        return nombre;
    }

	@Override
	public String toString() {
		return "LineaSubte [nombre=" + nombre + ", estaciones=" + estaciones + "]";
	}  
}
