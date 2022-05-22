package common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Converter {
	
	public static ByteArrayInputStream objectToByteArrayIS(Object obj) throws Exception{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    ObjectOutputStream oos = new ObjectOutputStream(baos);
	    oos.writeObject(obj);
	    byte[] objAsBytes = baos.toByteArray();
	    ByteArrayInputStream bais = new ByteArrayInputStream(objAsBytes);
	    return bais;
	}
	
	public static ObjectInputStream byteArrayToObjectIS(Object byteArray) throws Exception{
		byte tmp[]=(byte[])byteArray;
		ByteArrayInputStream baip = new ByteArrayInputStream(tmp);
		ObjectInputStream ois = new ObjectInputStream(baip);
		return ois;
		
	}

}
