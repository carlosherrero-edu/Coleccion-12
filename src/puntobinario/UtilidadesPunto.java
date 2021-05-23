package puntobinario;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de utilidades para gestionar el almacenamiento en fichero de objetos de
 * la clase Punto
 * 
 * @author Carlos Herrero
 *
 */
public class UtilidadesPunto {

	/**
	 * Método para escribir una lista de objetos Punto en un fichero binario. LA
	 * escritura sólo puede realizarse una vez debido a los problemas de las
	 * cabeceras Resuelve el Ejercicio 12, Colección 11
	 * 
	 * @param lp   - lista de objetos de la clase Punto
	 * @param ruta - ruta donde se escribir� el fichero de puntos
	 */
	public static void escribirPuntosEnFichero(List<Punto> lp, String ruta) {

		File fichero = new File(ruta);

		/**
		 * se supone que el fichero no existe o, si existe, se puede sobreescribir su
		 * contenido Por eso, el flujo básico FileOutputStream se inicia en modo
		 * destructivo, parámetro "false"
		 */
		try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(fichero, false));) {

			for (Punto p : lp) {
				ous.writeObject(p);
			}
			// si llegamos hasta aquí, el fichero se ha escrito correctamente
			System.out.println("Fichero generado y escrito con éxito");
			fichero = null;

		} catch (FileNotFoundException e) {
			System.out.println("El fichero no se puede encontrar en la ruta " + ruta);

		} catch (IOException e) {
			System.out.println("Se han producido errores. Se muestra la traza");
			e.printStackTrace();

		}
	}// fin del método

	/**
	 * Método que lee una lista de objetos Punto de un fichero binario usando
	 * ObjectInputStream Resuelve el ejercicio 12 de la Colección 12
	 * 
	 * @param ruta - ruta donde se encuentra un fichero con objetos de tipo Punto
	 * @return List<Punto> - lista de objetos recuperados del fichero
	 */
	public static List<Punto> leerPuntosDeFichero(String ruta) {

		File fichero = new File(ruta);
		List<Punto> lp = new ArrayList<>();

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));) {

			// leemos todo el contenido del fichero hasta el final
			while (true) {
				// se necesita hacer la conversión descendente de Object a Punto
				lp.add((Punto) ois.readObject());
			}

		} catch (EOFException e) {
			System.out.println("Fichero leido correctamente");
			// una vez leido, devolvemos la referencia a la lista
			fichero = null;
			return lp;

		} catch (FileNotFoundException e) {
			System.out.println("El fichero no se puede encontrar en la ruta " + ruta);

		} catch (ClassNotFoundException e) {
			System.out.println("El fichero guarda objetos de una clase desconocida");

		}

		catch (IOException e) {
			System.out.println("Se han producido errores. Se muestra la traza");
			e.printStackTrace();

		}
		// en el caso de que por cualquier excepción no se haya devuelto la lista...
		return null;

	} // fin del método

	/**
	 * Método para escribir una lista de objetos Punto en un fichero binario. LA
	 * escritura puede realizarse varias veces, comprobando si el fichero existe y
	 * tiene contenido Resuelve el Ejercicio 12, Colección 13
	 * 
	 * @param lp   - lista de objetos de la clase Punto
	 * @param ruta - ruta donde se escribirá el fichero de puntos
	 */
	public static void agregarPuntosEnFichero(List<Punto> lp, String ruta) {

		ObjectOutputStream ous = null;
		File fichero = new File(ruta);
		try {
			// dependiendo de si el fichero existe o no, se utiliza una clase u otra
			if (!fichero.exists()) {
				// el fichero no existe. Usamos ObjectOutputStream, que escribe la cabecera
				ous = new ObjectOutputStream(new FileOutputStream(fichero, false));
			} else {
				// el fichero existe. Usamos ObjectOutputStreamAppend, que no escribe la
				// cabecera
				// además, el flujo básico FileOutputStream se abre en modo de adición,
				// parámetro 'true'
				ous = new ObjectOutputStreamAppend(new FileOutputStream(fichero, true));
			}

			for (Punto p : lp) {
				ous.writeObject(p);
			}
			// si llegamos hasta aquí, el fichero se ha escrito correctamente
			System.out.println("Fichero generado y escrito con éxito");

		} catch (FileNotFoundException e) {
			System.out.println("El fichero no se puede encontrar en la ruta " + ruta);

		} catch (IOException e) {
			System.out.println("Se han producido errores. Se muestra la traza");
			e.printStackTrace();

		} finally {
			try {
				// tenemos que cerrar expresamente la referencia al flujo de escritura
				ous.close();
				fichero = null;
			} catch (IOException e) {
				System.out.println("Error al tratar de cerrar el flujo");
			}
		}

	}// fin del método

}
