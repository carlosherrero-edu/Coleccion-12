package agendacontactos;

import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.JOptionPane;

/**
 * Programa para gestionar una AGenda de Contactos que se guardará como un fichero binario
 * @author Miguel Rodriguez 
 * @author Carlos Herrero
 * @see Utilities  : donde se encuentran los métodos estáticos asociados a cada opción de la agenda
 * @see Contacto:  clase Contacto  con la información de cada contacto en la agenda
 *
 */
public class GestionarAgenda {
	
	/**
	 * Colección de tipo TreeSet (elementos no repetidos y ordenados) donde guardaremos los contactos de la agenda
	 */
	static TreeSet<Contacto> miAgenda = new TreeSet<>();
	
	
	/**
	 * cadena con la ruta y nombre donde guardamos el fichero binario con los contactos de la agenda
	 */
	static final String rutaFichero = "ficheros/agendacontactos.dat";
	
	static final int OPCIONMIN=1;
	static final int OPCIONMAX=6;
	
	public static void main(String[] args) {
		
		try(Scanner opcion = new Scanner(System.in)) {
			
			boolean seguirEnPrograma = true;
			
			//método que carga inicialmente los contactos que exisitiesen en el fichero
			miAgenda = Utilities.cargarContactos(rutaFichero);
			
			while (seguirEnPrograma) {
				//imprimimos el menú de opciones
				menu();
				boolean opcionOK=false;
				int opcionSeleccionada=0;
				//mientras la opción no sea válida, la pedimos por teclado
				while (!opcionOK) {
					
					try {
						opcionSeleccionada = Integer.parseInt(opcion.nextLine());
						if (opcionSeleccionada<OPCIONMIN|| opcionSeleccionada> OPCIONMAX) {
							throw new IllegalArgumentException("");
						} else {
							opcionOK=true;
						}
					}  catch (Exception e ){
						System.out.println("Introdujuste un valor u opción incorrecta. Por favor, vuelve introducirlo");
						System.out.print("Ingrese una opcion de menú >>>>");
						
					}
					
				}
				
				switch (opcionSeleccionada) {
				case 1: {
					Utilities.listarContactos(miAgenda);
					break;
				}
				case 2: {
					System.out.println("Ingrese Apellidos");
					String apellidos = opcion.nextLine();
					System.out.println("Ingrese nombre");
					String nombre = opcion.nextLine();
					System.out.println("Ingrese telefono, solo digitos");
					String telefono = opcion.nextLine();
					Utilities.aniadirContacto(apellidos, nombre, telefono, miAgenda);
					break;
				}
				case 3: {
					System.out.println("Ingrese apellidos persona a buscar");
					String apellidos = opcion.nextLine();
					Utilities.localizarContacto(apellidos, miAgenda);
					break;
				}
				case 4: {
					System.out.println("Ingrese apellidos persona a buscar");
					String apellidos = opcion.nextLine().trim();
					System.out.println("Ingrese el nombre, puede dejarlo en blanco");
					String nombre = opcion.nextLine().trim();
					Utilities.eliminarContacto(apellidos, nombre, miAgenda);
					break;
				}
				case 5: {
					System.out.println("Ingrese apellidos persona a buscar");
					String apellidos = opcion.nextLine().trim();
					System.out.println("Ingrese el nombre, puede dejarlo en blanco");
					String nombre = opcion.nextLine().trim();
					Utilities.cambiarTelefono(apellidos, nombre, miAgenda, opcion);
					break;
				}
				case 6: {
					//llamamos al método para guardar la agenda de contactos en el fichero
					Utilities.guardarContactos(miAgenda, rutaFichero);
					seguirEnPrograma = false;
					opcion.close(); //cerramos el objeto escáner
				
					System.out.println(" \n !!! Muchas gracias por utilizar este programa!!!");
					System.exit(0);
					break;

				}
				
				}//fin del switch-case
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Programa finalizó inesperadamente", "Error: " + e.getMessage(),
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	
	}
	
	public static void menu() {
		System.out.println(" \t\t MENÚ DE OPCIONES :                    ");
		System.out.println(" \t\t---------------------------------------\n");
		System.out.println("1. Listar contactos alfabéticamente");
		System.out.println("2. Añadir nuevo contacto");
		System.out.println("3. Localizar contacto");
		System.out.println("4. Eliminar contacto");
		System.out.println("5. Cambiar teléfono de contacto");
		System.out.println("6. Cerrar programa");
		System.out.print("Ingrese una opcion de menú >>>>");
	}
}
