package puntobinario;

import java.util.*;

public class ProbarUtilidadesPunto {

	public static void main(String[] args) {
		String dirHome = "ficheros";

		// genero una lista de puntos de prueba

		List<Punto> lista1 = new ArrayList<>();
		List<Punto> lista2=new ArrayList<>();
		lista1.add(new Punto(1, 1, "Rojo"));
		lista1.add(new Punto(2, 2, "Verde"));
		lista1.add(new Punto(3, 3, "Azul"));
		lista1.add(new Punto(4, 4, "Amarillo"));
		lista1.add(new Punto(5, 5, "Magenta"));

		// llamamos al método para guardar los puntos en el fichero de forma destructiva
		UtilidadesPunto.escribirPuntosEnFichero(lista1, dirHome + "/Puntos.dat");

		// llamamos ahora al m�todo para leer los puntos desde el fichero
		try {
			lista2 = UtilidadesPunto.leerPuntosDeFichero(dirHome + "/Puntos.dat");
			for (Punto p : lista2) {
				System.out.println(p.toString());
			}
		} catch (Exception e) {
			System.out.println(" se ha producido el error... "+e.getMessage());
		}
	

		// los leemos e imprimios
		

		// veo qué pasa si agrego puntos al final del fichero
		lista1.clear(); // vacío la lista
		lista1.add(new Punto(6, 6, "Cyan"));
		lista1.add(new Punto(7, 7, "Gris Claro"));
		lista1.add(new Punto(8, 8, "Rosa"));

		// llamo al m�todo para agregar los puntos al fichero
		UtilidadesPunto.agregarPuntosEnFichero(lista1, dirHome + "/Puntos.dat");

		// vuelvo a leerlos e imprimirlos
		
		// llamamos ahora al m�todo para leer los puntos desde el fichero
				try {
					lista2 = UtilidadesPunto.leerPuntosDeFichero(dirHome + "/Puntos.dat");
					for (Punto p : lista2) {
						System.out.println(p.toString());
					}
				} catch (Exception e) {
					System.out.println(" se ha producido el error... "+e.getMessage());
				}



	
		
	}

}