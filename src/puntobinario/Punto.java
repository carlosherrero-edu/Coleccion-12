package puntobinario;

import java.io.Serializable;

/**
 * Clase básica para modelar un punto en el plano
 * Implementa la interfaz Serializable para permitir su almacenamiento en fichero
 * @author Carlos Herrero
 *
 */
public class Punto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// propiedades
	private int coordX;
	private int coordY;
	private String color;

	// constructor único
	public Punto(int x, int y, String color) {
		this.coordX = x;
		this.coordY = y;
		this.color = color;
	}
	
	//métodos getXxX y setXxX
	public int getCoordX() {
		return this.coordX;
	}

	public int getCoordY() {
		return this.coordY;
	}

	public String getColor() {
		return this.color;
	}

	@Override
	public String toString() {
		return " Posici�n x:  " + getCoordX() + " --Posici�n y " + getCoordY() + "--Color: " + getColor();
	}

}
