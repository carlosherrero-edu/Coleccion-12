package punto;

import java.io.Serializable;

public class Punto implements Serializable {

	private static final long serialVersionUID = 1L;
	// propiedades
	private int coordX;
	private int coordY;
	private String color;

	// constructor
	public Punto(int x, int y, String color) {
		this.coordX = x;
		this.coordY = y;
		this.color = color;
	}

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
		return " Posición x:  " + getCoordX() + " --Posición y " + getCoordY() + "--Color: " + getColor();
	}

}
