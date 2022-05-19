package cuentas;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LeerCuentasBancariasSerial {
	/*
	 * Leer del fichero cuentas.dat todas las cuentas guardadas y mostrar su
	 * informaci�n por consola. Usaremos el flujo DataInputStream
	 */

	static final String RUTA = "ficheros/cuentasSerial.dat";

	public static void main(String[] args) {

		File fichero = null;
		// instanciamos el manejador del fichero
		try {
			fichero = new File(RUTA);
		} catch (Exception e) {
			// finalizamos la aplicaci�n
			e.printStackTrace();
			System.exit(1);
		}

		// instanciamos DataInputStream y recorremos el fichero
		try (ObjectInputStream ois = new ObjectInputStream (new FileInputStream(fichero))) {
			
	
			
			//creo una lista de objetos Cuenta
			ArrayList<CuentaSerie> listaCuentas= new ArrayList<>();
			CuentaSerie unaCuenta;
			
			try {
				//hago un bucle infinito para leer
				while (true) {
					//vamos leyendo los campos
					unaCuenta=(CuentaSerie)ois.readObject();
					//lo agrego a la lista
					listaCuentas.add(unaCuenta);			
				}
			} catch (EOFException e) {
				//hemos terminado de leer el fichero
				System.out.println("N�mero de clientes  " + listaCuentas.size());
				
				for ( CuentaSerie miCuenta  : listaCuentas) {
					System.out.println(miCuenta.toString());
				}
			}
			
	
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		

	}

}
