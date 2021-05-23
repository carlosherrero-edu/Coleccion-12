package contarpalabras;


import java.io.File;

import java.util.Formatter;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * Clase para resolver el ejercicio 7 de la Colección 12
 * 
 * @author CArlos Herrero
 *
 */
public class ContarPalabras {
	
	static final String PREFIJO="Dicc_";  //prefijo por el que comenzará el fichero con el diccionario

	static final String SEPARADOR="[\\p{Space}\\p{Punct}]\\p{Space}*";
	
	/**
	 * Método para leer un texto de un fichero usando la clase Scanner
	 * @param ruta ruta absoluta al fichero de texto
	 * @return cadena con el contenido del fichero
	 * @throws Exception si no existe el fichero, hay errores con el objeto Escaner...
	 */
	static String leerDeFichero(File fichero) throws Exception {

		//definimos e instanciamos un objeto Scanner que tome como entrada la referencia al fichero
		try  (Scanner teclado = new Scanner(fichero)){
				
			String texto = "";   //texto que devolveremos
			//leemos el contenido del fichero línea a línea y lo agregamos a la cadena de texto a devolver
			while (teclado.hasNextLine()) {
				texto = texto + teclado.nextLine() + "\n";
			}
			
			//cuando lleguemos al final, devolvemos el texto leído
			return texto;

		} catch (Exception e) {
			throw e;
		}

	} //fin del método
	
	/**
	 * M�todo para escribir el contenido del diccionario en un fichero usando la clase Formatter
	 * 
	 * @param ruta cadena con la ruta  al fichero de texto destino
	 * @param diccio   diccionario/Map con los pares palabras-frecuencias que escribiremos en el fichero
	 * @throws Exception si no existe el fichero, hay errores con el objeto Esc�ner...
	 */
	static void escribirEnFichero(File fichero, TreeMap<String, Integer> diccio) throws Exception {

		try {

			// definimos e instanciamos un flujo de tipo Formatter que tome como entrada la
			// referencia al fichero
			Formatter salida=new Formatter(fichero);
			//obtenemos la colección de palabras del diccionario con el método keySet de Map
			Set<String> lista_palabras = diccio.keySet();

			//recorremos el diccionario iterando sobre lista_palabras
			//guardamos los pares   "palabra/frecuencia" en el fichero
			for (String palabra : lista_palabras) {
				salida.format("%20s\t\t%d%n", palabra, diccio.get(palabra));
			}
			//cerramos los recursos utilizados
			salida.close();	

		} catch (Exception e) {
			throw e;

		}

	} //fin del método

	/**
	 * M�todo para separar las palabras de un texto y rellenar un objeto TreeMap en
	 * el cual se guardarán: 
	 * -- LAs palabras que aparecen en el texto -ordenadas alfabéticamente
	 * -- El n�mero de ocurrencias de cada palabra en el texto
	 * 
	 * @param diccio objeto TreeMap donde se guarda la lista de palabras y sus frecuencias
	 * @param texto  cadena de texto de la que se desean separar las palabras
	 */
	static void rellenarDiccionario(TreeMap<String, Integer> diccio, String texto) {
		
		//Divido el texto en palabras
		String [] palabras = texto.split(SEPARADOR);
		// recorro el array
		for (int i=0; i<palabras.length; i++) {
			//veo si tengo esa palabra en el diccionario
			String clave=palabras[i].toUpperCase();  //convierto la clave a may�scula
			
			if (!diccio.containsKey(clave)) {
				//si no tengo esa palabra en el diccionario, la agrego con frecuencia inicial 1
				diccio.put(clave, Integer.valueOf(1));
			}  else {
				//en caso contrario, si ya la tengo, incremento el número de veces que ha aparecido
				int rep= diccio.get(clave);
				diccio.put(clave, Integer.valueOf(rep+1));
			} 
		
				
			} //fin del bucle for
		
		}//fin del método
	

	/**
	 * M�todo que muestra la lista de palabras ordenadas, junto con sus frecuencias
	 * 
	 * @param diccio objeto TreeMap con la lista de palabras y sus frecuencias
	 */
	static void mostrarDiccionario(TreeMap<String, Integer> diccio) {
		
		Set<String> lista= diccio.keySet();
		
		for (String elem: lista) {
			System.out.println(elem+ " \t "+ diccio.get(elem));
		}

		

	}//FIN del métodoa

	

	/**
	 * M�dulo principal /controlador de nuestra aplicaci�n
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner teclado= new Scanner (System.in);
		
		System.out.println("Introduce la ruta y nombre del fichero de texto a analizar");
		
		String ruta_entrada= teclado.nextLine().trim();
		
		//comprobamos si se pueden instanciar los ficheros de entrada y salida
		File fichero_entrada=null, fichero_salida=null;
		try {
			fichero_entrada = new File(ruta_entrada);
			//construimos el nombre del fichero de salida
			String ruta_salida= ruta_entrada.replace(fichero_entrada.getName(), 
									PREFIJO+fichero_entrada.getName());
			fichero_salida = new File(ruta_salida);
		} catch (Exception e) {
			// finalizamos la aplicación
			System.out.println("Se produjo el error siguiente: " + e.getMessage()+
								"\n . La aplicación ha finalizado.");
			System.exit(1);
		}
		String frase="";  //cadena donde guardamos el texto a analizar
		
		try {
			frase= leerDeFichero(fichero_entrada);
			
		} catch (Exception e) {
			//imprimimos el error y cerramos el programa
			e.printStackTrace();
			System.exit(1);
		}
	

		// objeto TreeMap donde guardaremos el diccionario. Elegimos TreeMap para mantener la lista
		// ordenada
		TreeMap<String, Integer> diccio = new TreeMap<>();

		// realizamos el conteo de palabras
		rellenarDiccionario(diccio, frase);

		
		//los guardamos en el fichero de salida
				try {
					escribirEnFichero(fichero_salida,diccio);

				} catch (Exception e) {
					// imprimimos el error 
					e.printStackTrace();
					
				}

	} // fin del metodo main

}