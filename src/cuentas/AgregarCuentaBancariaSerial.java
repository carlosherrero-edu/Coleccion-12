package cuentas;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectOutputStream;
import java.util.Scanner;

public class AgregarCuentaBancariaSerial {
	
	
	/*
	 * Leer los campos/informaci�n para crear una Cuenta nueva usando un objeto Scanner
	 * Guardar la info de la nueva en un fichero binario cuentas.dat
	 * Usando el flujo DataOutputStream
	
	 */
	
	static final String RUTA="ficheros/cuentasSerial.dat";

	public static void main(String[] args) {
		
		//objeto Scanner para leer de teclado
		Scanner sc= new Scanner(System.in);
		
		//pedimos la informaci�n por teclado para escribir una nueva cuenta

		System.out.println("Introduce Nombre del propietario de la cuenta");
		String nombre = sc.nextLine();

		System.out.println("Introduce Apellido del propietario de la cuenta");
		String apellido = sc.nextLine();

		System.out.println("Introduce el nmero de cuenta");
		int numCuenta = sc.nextInt();

		System.out.println("Introduce el saldo inicial de la cuneta");
		double saldo = sc.nextDouble();
		
		File fichero=null;
		//instanciamos el manejador del fichero
		try {
			fichero = new File (RUTA);
		} catch (Exception e) {
			//finalizamos la aplicaci�n
			e.printStackTrace();
			System.exit(1);
		}
		
		CuentaSerie miCuenta;
		
		
		//veo si el fichero está vacío
		ObjectOutputStream escritor=null;
		
		try {
			if (fichero.exists() && fichero.length()>0) {
				escritor= new ObjectOutputStreamAppend(new FileOutputStream(fichero,true));
			} else {
				escritor= new ObjectOutputStream(new FileOutputStream(fichero)); 
				
			}
			
			//instancio la cuenta 
			miCuenta= new CuentaSerie(numCuenta,nombre,apellido, saldo);
			//la guardo en el fichero
			escritor.writeObject(miCuenta);
				
			System.out.println(" Se ha dado de alta una cuenta nueva con n�mero  "+numCuenta);
			
		}  catch (IllegalArgumentException e) {
			//fall� una de las validaciones para crear la cuenta
			System.out.println(e.getMessage());
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			//cierro el objeto Scanner
			sc.close();
			try {
				escritor.close();
			} catch (IOException e) {
				System.out.println("Se produjo un error al cerrar el fichero");
			}
			
		}
		
	
	}

}
