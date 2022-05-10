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

public class UtilidadesPunto {

	public static void escribirPuntosEnFichero(List<Punto> lp, String ruta) {

		File fichero = new File(ruta);

		try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(fichero, false));) {

			for (int i = 0; i < lp.size(); i++) {
				escritor.writeObject(lp.get(i));
			}

		} catch (IOException e) {
			System.out.println("Se ha producido el siguiente error : " + e.getMessage());
		}

	}

	public static List<Punto> leerPuntosDeFichero(String ruta)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		List<Punto> lp = new ArrayList<Punto>();

		try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(ruta));) {
			while (true) {
				lp.add((Punto) oos.readObject());
			} //
		} catch (EOFException e) {
			// cuando hemos llegado al final del fichero

			/*
			 * Código para imprimir los puntos. Comentado
			 * 
			 * for ( Punto miPunto : lp) { System.out.println(miPunto.toString()); }
			 */

			return lp;

		}
	}

	public static void agregarPuntosEnFichero(List<Punto> lp, String ruta) {

		File fichero = null;
		try {
			fichero = new File(ruta);
		} catch (Exception e) {
//finalizamos la aplicaci�n
			e.printStackTrace();
			System.exit(1);
		}
	 
		try (ObjectOutputStream  escritor = 
				new ObjectOutputStreamAppend(new FileOutputStream(fichero, true));) {
			
				
				for (Punto miObjeto : lp) {
					escritor.writeObject(miObjeto);
				}
				
		
		} catch (IllegalArgumentException e) {
//fall� una de las validaciones para crear la cuenta
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

}
