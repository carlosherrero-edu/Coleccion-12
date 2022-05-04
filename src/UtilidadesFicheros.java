import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class UtilidadesFicheros {

	static final int LINEAS_DEFECTO = 10;

	/**
	 * Método que imprime cada línea de un fichero de texto, junto al número de línea al comienzo
	 * Resuelve el Ejercicio 1 de la Coleccion 12
	 * 
	 * @param ruta cadena con la ruta completa -absoluta/relativa/ donde se ubica el fichero
	 */
	public static void imprimirLineasFichero(String ruta) {

		// primero tratamos de abrir el manejador del fichero
		File fichero;
		try {
			fichero = new File(ruta);
		} catch (Exception e) {
			// la ruta no es correcta o no existe el fichero.salimos del m�todo
			return;
		}

		try (Scanner sc = new Scanner(fichero)) {

			int contador = 1;
			String linea;
			// si el fichero est� vac�o
			if (!sc.hasNext()) {
				throw new Exception("El fichero se encuentra vacio");
			}
			// leemos l�nea a l�nea
			while (sc.hasNextLine()) {
				linea = sc.nextLine();
				System.out.format("%n [%d]: %s", contador, linea);
				contador++;
			}

		} catch (FileNotFoundException e) {
			System.out.println("No es posible abrir el fichero por los siguientes motivos: " + e.getMessage());

		} catch (IllegalStateException e) {
			System.out.println("El fichero est� cerrado");

		} catch (Exception e) {
			System.out.println("se ha producido el siguiente error: " + e.getMessage());
		}

	} // fin del m�todo

	/**
	 * Metodo que imprime las primeras líneas de un fichero de texto 
	 * Resuelve el Ejercicio 3 de la Colecci�n 12
	 * 
	 * @param ruta cadena con la ruta completa -absoluta/relativa/ donde se ubica el fichero
	 * @param numLineas  entero con el numero de lineas que queremos imprimir -debe ser positivo-
	 */

	public static void head2(String ruta, int numLineas) {

		// validamos lineas
		if (numLineas <= 0) {
			throw new IllegalArgumentException("El n�mero de l�neas debe ser mayor de 0");
		}

		// primero tratamos de abrir el manejador del fichero
		File fichero;
		try {
			fichero = new File(ruta);
		} catch (Exception e) {
			// la ruta no es correcta o no existe el fichero.salimos del m�todo
			return;
		}

		try (BufferedReader lector= new BufferedReader(new FileReader(fichero))) {
			int contador = 1;
			String linea;
		
		
			// si el fichero est� vac�o
			if (!lector.ready()) {
				throw new IOException("El fichero se encuentra vac�o");
			}

			while (lector.ready() && contador <= numLineas) {
				linea = lector.readLine();
				System.out.format("%n [%d]: %s", contador, linea);
				contador++;
			}
			//no necesitamos cerrar el fichero
		} catch (FileNotFoundException e) {
			System.out.println("No es posible abrir el fichero por los siguientes motivos: " + e.getMessage());

		} catch (IOException e) {
			System.out.println("se ha producido el siguiente error: " + e.getMessage());
		}

	} // fin del ejercicio 3
	
	
	
	/**
	 * Metodo que imprime las primeras líneas de un fichero de texto 
	 * Resuelve el Ejercicio 3 de la Colecci�n 12
	 * 
	 * @param ruta cadena con la ruta completa -absoluta/relativa/ donde se ubica el fichero
	 * @param numLineas  entero con el numero de lineas que queremos imprimir -debe ser positivo-
	 */

	public static void head(String ruta, int numLineas) {

		// validamos lineas
		if (numLineas <= 0) {
			throw new IllegalArgumentException("El n�mero de l�neas debe ser mayor de 0");
		}

		// primero tratamos de abrir el manejador del fichero
		File fichero;
		try {
			fichero = new File(ruta);
		} catch (Exception e) {
			// la ruta no es correcta o no existe el fichero.salimos del m�todo
			return;
		}

		try (BufferedReader lector= new BufferedReader(new FileReader(fichero))) {
	
			String linea;	
		
			// si el fichero est� vac�o
			if (!lector.ready()) {
				throw new IOException("El fichero se encuentra vacío o no se ha podido abrir ");
			}
			
			for (int contador=1; contador<=numLineas;contador++) {
				linea = lector.readLine();
				if (linea==null) {
					//hemos llegado al final del fichero...
					break;
				} else {
					System.out.format("%n [%d]: %s", contador, linea);
				}
			}

			//no necesitamos cerrar el fichero
		} catch (FileNotFoundException e) {
			System.out.println("No es posible abrir el fichero por los siguientes motivos: " + e.getMessage());

		} catch (IOException e) {
			System.out.println("El fichero est� cerrado");

		} 


	} // fin del ejercicio 3

	/**
	 * Método que imprime las 10 primeras líneas de un fichero de texto
	 * Resuelve el Ejercicio 2 de la Colecci�n 12
	 * 
	 * @param ruta cadena con la ruta completa -absoluta/relativa/ donde se ubica el fichero
	 * @see head(String ruta, int numLineas)
	 */
	public static void head(String ruta) {

		head(ruta, LINEAS_DEFECTO);
	}

	
	/**
	 * Metodo que imprime las últimas líneas de un fichero de texto
	 * Resuelve el Ejercicio 5, Coleccion 12
	 *  
	 *  @param ruta cadena con la ruta completa -absoluta/relativa/ donde se ubica el fichero
	 * @param numLineas  entero con el numero de lineas que queremos imprimir -debe ser positivo-
	 */
	public static void tail2(String ruta, int numLineas) {

		// validamos lineas
		if (numLineas <= 0) {
			throw new IllegalArgumentException("El n�mero de l�neas debe ser mayor de 0");
		}

		// primero tratamos de abrir el manejador del fichero
		File fichero;
		try {
			fichero = new File(ruta);
		} catch (Exception e) {
			// la ruta no es correcta o no existe el fichero.salimos del m�todo
			return;
		}
		
		
		try (BufferedReader lector= new BufferedReader(new FileReader(fichero))) {
	
			
			// definimos un LinkedList que gestionaremos como una cola donde guardaremos n líneas
			LinkedList<String> lineas = new LinkedList<>();
		
			// si el fichero est� vac�o
			if (!lector.ready()) {
				throw new Exception("El fichero se encuentra vac�o");
			}

			while (lector.ready()) {
				//agregamos la línea al final de la lista
				lineas.add(lector.readLine());

				if (lineas.size() > numLineas) {
					// si ya hay numLineas l�neas en el fichero, se elimina y descarta la m�s antigua
					lineas.pop();
				}

			
			}

			// al final se imprimen las numLineas �ltimas l�neas
			int contador = 1;
			for (String linea : lineas) {
				System.out.format("%n [%d]: %s", contador++, linea);
			}
			//no necesitamos cerrar el fichero
		} catch (FileNotFoundException e) {
			System.out.println("No es posible abrir el fichero por los siguientes motivos: " + e.getMessage());

		} catch (IllegalStateException e) {
			System.out.println("El fichero est� cerrado");

		} catch (Exception e) {
			System.out.println("se ha producido el siguiente error: " + e.getMessage());
		}

		try (Scanner sc = new Scanner(fichero);) {

			



		} catch (FileNotFoundException e) {
			System.out.println("No es posible abrir el fichero por los siguientes motivos: " + e.getMessage());

		} catch (IllegalStateException e) {
			System.out.println("El fichero est� cerrado");

		} catch (Exception e) {
			System.out.println("se ha producido el siguiente error: " + e.getMessage());
		}

	}
	
	
	/**
	 * Metodo que imprime las últimas líneas de un fichero de texto
	 * Resuelve el Ejercicio 5, Coleccion 12
	 *  
	 *  @param ruta cadena con la ruta completa -absoluta/relativa/ donde se ubica el fichero
	 * @param numLineas  entero con el numero de lineas que queremos imprimir -debe ser positivo-
	 */
	public static void tail(String ruta, int numLineas) {

		// validamos lineas
		if (numLineas <= 0) {
			throw new IllegalArgumentException("El n�mero de l�neas debe ser mayor de 0");
		}

		// primero tratamos de abrir el manejador del fichero
		File fichero;
		try {
			fichero = new File(ruta);
		} catch (Exception e) {
			// la ruta no es correcta o no existe el fichero.salimos del m�todo
			return;
		}
		
		
		try (BufferedReader lector= new BufferedReader(new FileReader(fichero))) {
	
			
			// definimos un LinkedList que gestionaremos como una cola donde guardaremos numLineas
			LinkedList<String> lineas = new LinkedList<>();
		
			// si el fichero est� vac�o
			if (!lector.ready()) {
				throw new Exception("El fichero se encuentra vac�o");
			}
			String nuevaLinea;
			while ((nuevaLinea=lector.readLine())!=null) {
				//agregamos la línea al final de la lista
				lineas.add(nuevaLinea);

				if (lineas.size() > numLineas) {
					// si ya hay numLineas  en el fichero, se elimina y descarta la m�s antigua
					lineas.pop();
				}

			
			}//fin del bucle while

			// al final se imprimen las numLineas �ltimas l�neas
			int contador = 1;
			for (String linea : lineas) {
				System.out.format("%n [%d]: %s", contador++, linea);
			}
			//no necesitamos cerrar el fichero
		} catch (FileNotFoundException e) {
			System.out.println("No es posible abrir el fichero por los siguientes motivos: " + e.getMessage());

		} catch (IllegalStateException e) {
			System.out.println("El fichero est� cerrado");

		} catch (Exception e) {
			System.out.println("se ha producido el siguiente error: " + e.getMessage());
		}

		try (Scanner sc = new Scanner(fichero);) {

			



		} catch (FileNotFoundException e) {
			System.out.println("No es posible abrir el fichero por los siguientes motivos: " + e.getMessage());

		} catch (IllegalStateException e) {
			System.out.println("El fichero est� cerrado");

		} catch (Exception e) {
			System.out.println("se ha producido el siguiente error: " + e.getMessage());
		}

	}

	
	/**
	 * Método que imprime las 10 ultimas líneas de un fichero de texto
	 * Resuelve el Ejercicio 4 de la Coleccion 12
	 * 
	 * @param ruta cadena con la ruta completa -absoluta/relativa/ donde se ubica el fichero
	 * @see tail(String ruta, int numLineas)
	 */
	public static void tail(String ruta) {
		tail(ruta, LINEAS_DEFECTO);
	}
	
	
	/**
	 * M�todo que emula el comando "grep" de linux
	 * El programa recorre todos los elementos contenidos en un directorio.
	 * Cuando se trate de un fichero, lo lee  l�nea a l�nea y comprobar� si la l�nea contiene el patr�n buscado.
	 * Cada coincidencia se guarda en un TreeMap cuya clave es el nombre del fichero y el n�mero de l�nea,
	 * y cuyo valor es la l�nea que contiene la cadena
	 * Como salida, imprime el Map con todas las coincidencias
	 * @param patron: cadena que se desea buscar
	 * @param directorio: directorio desde el que se inicia la b�squeda
	 */
	public static void grep (String patron, File directorio){


		TreeMap<String,String> coincidencias= new TreeMap<>();
		try {	
			
			File[] hijos= directorio.listFiles();
			
						
			for (File hijo: hijos){
		
				
				if (hijo.isFile()) { 
					
				    	//si es un fichero, tratamos de leerlo
	
					try (Scanner sc= new Scanner (hijo)){
					
						
						
						
						String linea;
						int contador=1;				
						while (sc.hasNextLine()){
							linea= sc.nextLine();
							if (linea.indexOf(patron)>=0){
								coincidencias.put(new String(hijo.getCanonicalPath()+":L�nea "+contador),linea);
							}
							contador++;
						}//fin de la lectura del fichero
					} catch (Exception e){
						// si se produce una excepci�n,no hacemos nada, pasamos al siguiente fichero
						//seguramente se produce porque no es un fichero de texto
					}
				
				} //fin de la condici�n
				
			} //fin del recorrido por los ficheros
			 
			//mostramos ahora el resultado
			Set<String> lineas=coincidencias.keySet();
			for (String cad: lineas){
				System.out.format("%n  %s : %s", cad, coincidencias.get(cad));
			}

		}catch (SecurityException e){
			System.out.println("No tenemos permisos para acceder a este directorio");
			
		}catch (Exception e){
			System.out.println("se ha producido el siguiente error: "+ e.getMessage());
		}
			
	
		
		
	}//fin del m�todo grep

}
