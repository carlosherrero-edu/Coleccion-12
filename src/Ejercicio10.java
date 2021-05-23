
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio10 {

	static final String NOMBREFICHERO = "ficheros/liga.dat";

	/**
	 * Método para escribir información de los equipos de la liga de fúbtol y guardarla en fichero binario
	 * Pide datos de cada equipo hasta que se introduzca la cadena "fin"
	 * Utiliza el flujo binario DataOutputStream
	 * 
	 */
	public void introducirEquipos() {

		// declaramos el manejador del fichero
		File fich = new File(NOMBREFICHERO);

		// abrimos como argumento del try un objeto Scanner para capturar la informacion
		// junto a un objeto DataOutputStream para ir escribiendo los campos en un
		// fichero binario
		// lo abrimos en modo no destructivo con el parámetro del flujo básico
		// FileOutputStream a true
		try (Scanner entrada = new Scanner(System.in);
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(fich, true))) {

			// variables necesarias para guardar la información de cada equipo
			int ganados = 0, perdidos = 0, empatados = 0;
			int equipos = 0;
			String equipo;
			boolean infoCorrecta;

			do {
				System.out.println("Introduce el nombre del siguiente equipo, o FIN para finalizar");
				equipo = entrada.nextLine();
				if (!equipo.trim().equalsIgnoreCase("FIN")) {
					// leemos el número de partidos jugados

					infoCorrecta = false;
					while (!infoCorrecta) {
						try {
							System.out.println("Partidos ganados:>>>");
							ganados = Integer.parseInt(entrada.nextLine());
							System.out.println("Partidos perdidos:>>>");
							perdidos = Integer.parseInt(entrada.nextLine());
							System.out.println("Partidos empatados:>>>");
							empatados = Integer.parseInt(entrada.nextLine());
							// si no se han producido excepciones hasta aquí, la info es correcta
							infoCorrecta = true;
						} catch (NumberFormatException e) {
							System.out.println("Uno de los valores introducidos no es un número válido");
							System.out.println("Por favor, introduzca de nuevo el número de partidos jugados");
						}
					}

					// guardamos ahora la información campo a campo
					dos.writeUTF(equipo);
					dos.writeInt(ganados + perdidos + empatados);
					dos.writeInt(ganados);
					dos.writeInt(perdidos);
					dos.writeInt(empatados);
					dos.writeInt(3 * ganados + empatados);
					equipos++;

				} else {
					System.out.println("Se escribieron en el fichero " + equipos + "equipos");
				}
			} while (!equipo.trim().equalsIgnoreCase("FIN"));

		} catch (FileNotFoundException e) {
			System.out.println("El fichero no se puede encontrar en el directorio actual");

		} catch (IOException e) {
			System.out.println("Se han producido errores. Se muestra la traza");
			e.printStackTrace();

		}

	} // fin del método
	

	/**
	 * Método para leer información de los equipos de un fichero binario.
	 * Va leyendo campo a campo hasta llegar al final del fichero
	 * Utiliza el flujo binario DataInputStream
	 * 
	 */
	public void leerEquipos() {

		// declaramos el manejador del fichero
		File fich = new File(NOMBREFICHERO);

		try (DataInputStream dis = new DataInputStream(new FileInputStream(fich));) {

			String equipo;
			int partidos, ganados, perdidos, empatados, puntos;

			System.out.format("%n%30s  %8s  %2s  %2s  %2s  %6s", "Nombre del Equipo", "Partidos", "GA", "PE", "EM",
					"Puntos");

			// leemos registros hasta llegar al final del fichero
			int equiposLeidos = 0;

			while (dis.available() > 0) {
				// leemos los campos del fichero
				equipo = dis.readUTF();
				partidos = dis.readInt();
				ganados = dis.readInt();
				perdidos = dis.readInt();
				empatados = dis.readInt();
				puntos = dis.readInt();

				// los mostramos en pantalla
				System.out.format("%n%30s  %8d  %2d  %2d  %2d  %6d%n%n", equipo, partidos, ganados, perdidos, empatados,
						puntos);
				equiposLeidos++;
			} // fin del bucle

			System.out.println("\n\nEquipos leídos del fichero : " + equiposLeidos);

		} catch (FileNotFoundException e) {
			System.out.println("El fichero no se puede encontrar en el directorio actual");

		} catch (IOException e) {
			System.out.println("Se han producido errores. Se muestra la traza");
			e.printStackTrace();
		}

	}// fin del método

	public static void main(String[] args) throws IOException {
		
		//podríamos hacer un pequeño menú, pero por simplicidad llamamos a los métodos
		
		Ejercicio10 probarEquipos= new Ejercicio10();
		
		probarEquipos.leerEquipos();
		probarEquipos.introducirEquipos();
		probarEquipos.leerEquipos();

	}

}
