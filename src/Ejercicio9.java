import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio9 {
	
	public static void main(String[] args) {
		Scanner teclado=new Scanner(System.in);
		
		File fichero_origen=null;
		File fichero_destino=null;
		
		//leer las rutas
		String ruta, nombre_orig, nombre_copia;
		
		System.out.println("Escribe la ruta donde está el fichero");
		ruta= teclado.nextLine();
		System.out.println("Escribe el nombre del fichero a copiar");
		nombre_orig=teclado.nextLine();
		System.out.println("Escribe el nombre que tendrá la copia");
		nombre_copia=teclado.nextLine();
		
		try {
			fichero_origen= new File (ruta+"/"+nombre_orig);
			fichero_destino= new File (ruta+"/"+nombre_copia);
			
		} catch (Exception e) {
			System.out.println("NO se han podido abrir los ficheros");
			System.exit(1);
			
		}
		
		
		try(
		FileInputStream fis = new FileInputStream(fichero_origen);
		FileOutputStream fos = new FileOutputStream(fichero_destino))
		{

			int unByte;
			unByte = fis.read();
			while (unByte != -1) {
				// la marca de fin de fichero es -1
				fos.write(unByte);
				unByte = fis.read();
			}
			System.out.println("Copia realizada correctamente");
		}catch(IOException e){
			e.printStackTrace();
		}
		//cerrar el objeto teclado
		teclado.close();
	}


	

	}


