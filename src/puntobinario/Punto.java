package puntobinario;

import java.io.Serializable;

public class Punto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int coordX;
	private int coordY;
	private String color;
	
	
	
	public Punto(int coordX, int coordY, String color) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.color = color;
	}
	
	public int getCoordX() {
		return coordX;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		return String.format(" (%d : %d) - Color:%s", this.getCoordX(), this.getCoordY(), this.getColor());
	}
	
	

}
