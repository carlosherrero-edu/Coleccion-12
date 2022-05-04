
import java.io.*;

public class LeerFichData {
	public static void main(String[] args) {
		File fichero = new File("ficheros/FichData.dat");

		int contador = 0;
		try (DataInputStream dataIS = new DataInputStream(new FileInputStream(fichero));) {

			// campos que se van a ir leyendo del fichero
			String n;
			int e;

			// recorremos el fichero usando un bucle "infinito"
			// al llegar al final, se producirá la excepción EOFException
			
			while (dataIS.available() > 0) {
				n = dataIS.readUTF(); // recupera el nombre
				e = dataIS.readInt(); // recupera la edad
				System.out.println("Nombre: " + n + ", edad: " + e);
				contador++;
			}
			System.out.format("%nSe han leido correctamente %d   registros", contador);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
