
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


public class Ejercicio9 {

	public static void main(String[] args) throws IOException {
		//objeto Scanner para preguntar por el directorio
		Scanner entrada= new Scanner(System.in);
				
		System.out.println("Introduce la ruta al archivo que quieres copiar \n>>>");
				
		String origen = entrada.nextLine();
		
		System.out.println("Introduce el nombre del archivo copia \n>>>");
		
		String destino = entrada.nextLine();
		
		//declaramos los objetos necesarios
		File f_ori, f_dest;
		f_ori= new File(origen);
		
		//suponemos que el fichero copia se va a depositar en la misma ruta
		//en la ruta del archivo, sustituimos el nombre del origen por el nombre del destino
		f_dest= new File(origen.replace(f_ori.getName(),destino));
		
		//ABRIMOS Un flujo de entrada y otro de salida como argumento de try
		try (FileInputStream fis= new FileInputStream(f_ori);
				FileOutputStream fos=new FileOutputStream(f_dest)){
			
			//leeemos elemento a elemento (byte a byte) hasta llegar al fin de fichero
			int elemento;
			
			elemento=fis.read();
			
			
			while (elemento!=-1){
				fos.write(elemento);
				elemento=fis.read();
			}
			
			System.out.println("Copia de archivos realizada correctamente");
		}catch (FileNotFoundException e){
			System.out.println("El fichero no se puede encontrar en la ruta "+origen);
		
		}catch (IOException e){
			System.out.println("Se han producido errores. Se muestra la traza");
			e.printStackTrace();
			
		} finally {
			//cerramos manejadores de fichero y objeto escáner
						
			f_ori=null;
			f_dest= null;
			entrada.close();
			
		}

	}

}