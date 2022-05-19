package contarpalabras;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Clase para resolver el ejercicio 8 de la Colección 12
 * 
 * @author CArlos Herrero
 *
 */
public class Ejercicio8 {
	
	static final String PREFIJO="Dicc_";  //prefijo por el que comenzará el fichero con el diccionario

	static final String SEPARADOR="[\\p{Space}\\p{Punct}]\\p{Space}*";
	
	static TreeSet<String> palabrasExcluidas;
	
	/**
	 * Constructor que carga el fichero de palabras excluidas en un TreeSet
	 * @param rutaFicheroExcluidas
	 */
	public Ejercicio8(String rutaFicheroExcluidas) {
		palabrasExcluidas= new TreeSet<>();
		/*
		 * leer el fichero de palabras excluidas y cargarlo en el TreeSet
		 */
		try (BufferedReader lector=new BufferedReader(new FileReader(rutaFicheroExcluidas))){
			
			while (lector.ready()) {
				
				palabrasExcluidas.add(lector.readLine().toUpperCase());
			}
			
		} catch (IOException e) {
			System.out.println("Error al leer el fichero :"+e.getMessage());
			System.exit(1);
		}
		
	}
	
	 
	
	/**
	 * Método para leer un texto de un fichero usando la clase Scanner
	 * @param ruta ruta absoluta al fichero de texto
	 * @return cadena con el contenido del fichero
	 * @throws Exception si no existe el fichero, hay errores con el objeto Escaner...
	 */
	static String leerDeFichero(File fichero) throws IOException {

		//definimos e instanciamos un objeto Scanner que tome como entrada la referencia al fichero
		try  (BufferedReader  entrada = new BufferedReader(new FileReader(fichero))){
				
			String texto = "";   //texto que devolveremos
			//leemos el contenido del fichero línea a línea y lo agregamos a la cadena de texto a devolver
			while (entrada.ready()) {
				texto = texto + entrada.readLine()+ "\n";
			}
			
			//cuando lleguemos al final, devolvemos el texto leído
			return texto;

		} catch (IOException e) {
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

		Set<String> miSet = new TreeSet<>();
		try(Formatter salida = new Formatter(fichero);){
			miSet = diccio.keySet();
			for (String palabra : miSet) {
				int numVeces=diccio.get(palabra);
				salida.format("%s %d %n", palabra,numVeces);
			}
			
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
		
		//troceamos el texto
		String[] trocitos=texto.split(SEPARADOR);
		//voy recorriendo los trocitos
		for (String palabra: trocitos) {
			//paso la palabra a mayúscula
			palabra=palabra.toUpperCase();
			//veo si la palabra está en la lista de palabras excluidas
			if (palabrasExcluidas.contains(palabra)) {
				//ignoro esta palabra, no hago nada con ella
			} else {
				//compruebo si está en el diccionario
				if (diccio.containsKey(palabra)) {
					//obtengo el número de veces que ya estaba
					int repeticiones= diccio.get(palabra);
					//vuelvo a escribirla incrementando el número de veces en 1
					diccio.replace(palabra, Integer.valueOf(repeticiones+1));
					
				} else {
					//la palabra es nueva
					diccio.put(palabra, Integer.valueOf(1));
				}
			}
			
		} //fin del bucle
		
	
		
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
			String nombreFichero=fichero_entrada.getName();
			
			//construimos el nombre del fichero de salida
			
			fichero_salida = new File(fichero_entrada.getParent()+"/"+PREFIJO+nombreFichero);
		} catch (Exception e) {
			// finalizamos la aplicación
			System.out.println("Se produjo el error siguiente: " + e.getMessage()+
								"\n . La aplicación ha finalizado.");
			System.exit(1);
		}
		String frase="";  //cadena donde guardamos el texto a analizar
		
		Ejercicio8 tratarTexto=new Ejercicio8("ficheros/noincluir.txt");
		
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
					System.out.println("Se ha generado el diccionario de palabras correctamente.");

				} catch (Exception e) {
					// imprimimos el error 
					e.printStackTrace();
					
				}

	} // fin del metodo main

}