import java.io.File;

public class PruebasUtilidadesFicheros {
	
	public static void main(String[] args) {
		
		
		String ruta="ficheros";
		String fich="cienAnios.txt";
		
	
		
		//UtilidadesFicheros.imprimirLineasFichero(ruta+"/"+fich);
		System.out.println();
		UtilidadesFicheros.head2(ruta+"/"+fich,20);
		System.out.println();
		//UtilidadesFicheros.tail(ruta+"/"+fich);
		System.out.println();
	
		//UtilidadesFicheros.grep("gitano", new File(ruta));
		
		
		

	}

}
