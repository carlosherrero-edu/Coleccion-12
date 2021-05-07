package appcuentas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class UtilidadesCuentas {

	

	/**
	 * @param fichero
	 * @param sc
	 * @throws Exception
	 */
	public static void escribir(File fichero, Locale spanish) throws Exception {
		//creamos un objeto escáner para introducir la información
		Scanner sc= new Scanner(System.in);
		//establecemos formato para separador decimal
		sc.useLocale(spanish);
		
		// pedimos la información por teclado para escribir una nueva cuenta

		System.out.print("\nIntroduce Nombre del propietario de la cuenta >>>>");
		String nombre = sc.nextLine();

		System.out.print("\nIntroduce Apellido del propietario de la cuenta >>>>");
		String apellido = sc.nextLine();
		
		System.out.println("\n Introduce el número de cuenta >>>>");
		int numCuenta = sc.nextInt();
		
		System.out.println("\nIntroduce el saldo inicial de la cuenta >>>>");
		double saldo = sc.nextDouble();

		
		
	
		

		try (PrintWriter escritor = new PrintWriter(new FileWriter(fichero, true))) {

			escritor.format("%d %s %s %.2f\n", numCuenta, nombre, apellido, saldo);
			System.out.println("Agregada la cuenta con número :"+numCuenta);

		} catch (Exception e) {
			throw e;
		} 

	}// fin del método

	public static void leer(File fichero, NumberFormat nf) throws Exception {

		try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {
			int contador=0;
			String linea;
			System.out.format("%10s %30s %30s %10s %n%n%n", "Número", "Nombre", "Apellidos", "Saldo");

			while ((linea = lector.readLine()) != null) {
				String[] tokens = separarComponentes(linea);
				System.out.format("%10d %30s %30s %10.2f %n", Integer.parseInt(tokens[0]), tokens[1], tokens[2],
						nf.parse(tokens[3]).doubleValue());
				contador++;
				
			}
			System.out.println("\n\n****Cuentas leídas: "+contador);

		} catch (Exception e) {
			throw e;
		}
		// no hace falta cerrar el PrintWriter

	}

	static String[] separarComponentes(String linea) {
		String[] tokens = linea.split("\\p{Space}+");
		return tokens;
	}

	static ArrayList<Cuenta> leerCuentas(File fichero, NumberFormat nf) throws Exception {

		try (BufferedReader lector = new BufferedReader(new FileReader(fichero))) {

			// iniciar un ArrayList de Cuentas
			ArrayList<Cuenta> listaCuentas = new ArrayList<>();
			Cuenta unaCuenta;
			String linea;

			while ((linea = lector.readLine()) != null) {
				// separamos cada campo de la línea leída
				String[] tokens = separarComponentes(linea);
				try {
					// creamos el objetoCuenta
					unaCuenta = new Cuenta(Integer.parseInt(tokens[0]), tokens[1], tokens[2],
							nf.parse(tokens[3]).doubleValue());
					// lo agregamos a la lista
					listaCuentas.add(unaCuenta);
				} catch (NumberFormatException e) {
					// la cuenta no tiene el formato esperado; la descartamos
				}
			}
			// aquí hemos terminado de leer los registros
			return listaCuentas;

		} catch (Exception e) {
			throw e;
		}

	} // fin del método

}
