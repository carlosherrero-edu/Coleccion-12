package puntobinario;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


/**
 * Clase que hereda de ObjectOutputStream para permitir la escritura no destructiva de objetos
 * @author Carlos Herrero
 *
 */
public class ObjectOutputStreamAppend extends ObjectOutputStream {
	
	//constructores que invocan los constructores de la clase padre
	public ObjectOutputStreamAppend (OutputStream ous) throws IOException{
		 super(ous);
		 
	}
	
	protected ObjectOutputStreamAppend ()throws IOException, SecurityException {
		super();
	}
	
	/**
	 * A fin de permitir la escritura no destructiva, no debe volver a escribirse la cabecera
	 * con la meta-información de la clase de objetos
	 * Para ello, se sobreescribe el método writeStreamHeader, dejando su cuerpo vacío
	 */
	@Override()
	protected void writeStreamHeader() throws IOException{
		; //no hace nada
		
	}

}