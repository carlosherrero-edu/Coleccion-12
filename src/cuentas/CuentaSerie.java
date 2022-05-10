package cuentas;

import java.io.Serializable;

/**
 * Clase Cuenta que implementa la interfaz Serializable para poder escribirla a fichero
 * @author Carlos Herrero
 * @version 1.0
 *
 */
public class CuentaSerie implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//propiedades
	private int numCuenta;
	private String nombre;
	private String apellidos;
	private double saldo;
	
	//constructor vacío
	public CuentaSerie(){
		super();
	}
	
	//constructor pasándole todos los campos 
	public CuentaSerie(int numCuenta, String nombre, String apellidos, double saldo){
		try {
			setNumCuenta(numCuenta);
			setApellidos(apellidos);
			setNombre(nombre);
			setSaldo(saldo);
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}
	
	//métodos get y set
	public int getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(int numCuenta) {
		if (numCuenta <=0){
			throw new IllegalArgumentException ("El número de cuenta debe ser positivo");
		}
		this.numCuenta = numCuenta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		if (nombre.trim().length()==0){
			throw new IllegalArgumentException ("Los apellidos no pueden estar en blanco");
		}
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		if (apellidos.trim().length()==0){
			throw new IllegalArgumentException ("Los apellidos no pueden estar en blanco");
		}
		this.apellidos = apellidos;
	}
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public String toString() {
		return String.format("Número de cuenta : %d %n Titular: %s %n Saldo: %.2f", 
							getNumCuenta(), getNombre()+" "+getApellidos(), getSaldo());
	}
	
	

}
