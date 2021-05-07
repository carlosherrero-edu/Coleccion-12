package appcuentas;

import java.io.Serializable;

public class Cuenta implements Serializable {
	
	/**
	 * N鷐ero de versi髇 de la Clase
	 */
	private static final long serialVersionUID = 1L;
	
	//propiedades
	private int numCuenta;
	private String nombre;
	private String apellidos;
	private double saldo;
	
	//constructor vacio
	public Cuenta(){
		super();
	} //fin del constructor
	
	//constructor com par谩metros, inicializando el saldo a cero
	public Cuenta ( int numCuenta, String nombre, String apellidos) {
		try {
			setNumCuenta(numCuenta);
			setNombre(nombre);
			setApellidos(apellidos);
			//inicializamos la cuenta a saldo 0
			setSaldo(0.0);
		} catch (IllegalArgumentException e) {
			//rebotamos la excepci贸n al m贸duo llamante
			throw e;
		}
	} //fin del construtor
	
	//constructor com par谩metros, inicializando el saldo a cero
		public Cuenta ( int numCuenta, String nombre, String apellidos, double saldo) {
			try {
				setNumCuenta(numCuenta);
				setNombre(nombre);
				setApellidos(apellidos);
				
				setSaldo(saldo);
			} catch (IllegalArgumentException e) {
				//rebotamos la excepci贸n al m贸duo llamante
				throw e;
			}
		} //fin del construtor
	
	
	
	//m茅todos get y set
	public int getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(int numCuenta) {
		if (numCuenta <=0){
			throw new IllegalArgumentException ("El n煤mero de cuenta debe ser positivo");
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
	

	@Override
	public String toString() {
		return String.format(" Nombre: %s  %s  \t *N鷐 cuenta: %d \t *Saldo: %.2f ", 
								getNombre(),getApellidos(), getNumCuenta(), getSaldo());
	}
	
	

}
