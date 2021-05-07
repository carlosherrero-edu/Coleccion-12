package appcuentas;

import java.io.File;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
	
	static final int OPCIONMIN=1;
	static final int OPCIONMAX=3;
	static final String RUTA="ficheros/cuentas.txt";
	
	static Scanner lector_opciones;
	static Locale spanish;
	static NumberFormat nf;
	
	//OJO: Aquí usamos una opción de JAva14 en modo preview
	static final String menu = """
			       MENÚ DE OPCIONES 
			---------------------------------
			(1): Agregar una nueva cuenta
			(2): Listar las cuentas existentes
			(3): Abandonar la aplicación
			
			Introduzca el número de opción deseada:>>>>""";
	
	
	static int obtenerOpcion(Scanner lector) {
		
		System.out.println(menu);
		int opcion = lector.nextInt();
		return opcion;
		
	}

	public static void main(String[] args) {
		
		File fichero = null;
		// instanciamos el manejador del fichero y el lector de opciones, así como la opción de idioma
		try {
			fichero = new File(RUTA);
			lector_opciones= new Scanner (System.in);
			spanish = new Locale("es", "ES");
			nf= NumberFormat.getInstance(spanish);
		} catch (Exception e) {
			// finalizamos la aplicación
			System.out.println("Se produjo el error siguiente: " + e.getMessage()+
								"\n . La aplicación ha finalizado.");
			System.exit(1);
		}
		


		int opcion=0;
		while (opcion!=OPCIONMAX) {
			boolean opcionValida=false;
			
			while (!opcionValida) {
				opcion= obtenerOpcion(lector_opciones);
				if (opcion>=OPCIONMIN && opcion<=OPCIONMAX) {
					opcionValida=true;
				} else {
					System.out.println("Introdujo una opción incorrecta. Por favor, vuelva a intentarlo");
				}
				
			}
			//cuando se haya introducido una opción válida, la procesamos
			switch (opcion) {
			
			case  1-> {
				try {
					UtilidadesCuentas.escribir(fichero,spanish);
				} catch (Exception e) {
					System.out.println("Se produjo el siguiente error: "+e.getMessage());
				}
			}
			case  2-> {
				try {
					UtilidadesCuentas.leer(fichero, nf);
				} catch (Exception e) {
					System.out.println("Se produjo el siguiente error: "+e.getMessage());
				}
			} 
			case  3-> {
				System.out.println("Muchas gracias por usar este programa.");
				lector_opciones.close();
				System.exit(0);
			}
			
		} //fin del bucle

	}
		
	}

}
