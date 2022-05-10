package cuentas;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class ObjectOutputStreamAppend extends ObjectOutputStream {
	
	public ObjectOutputStreamAppend (OutputStream ous) throws IOException{
		 super(ous);
		 
	}
	
	protected ObjectOutputStreamAppend ()throws IOException, SecurityException {
		super();
	}
	
	@Override()
	protected void writeStreamHeader() throws IOException{
		//no hace nada
		
	}

}



