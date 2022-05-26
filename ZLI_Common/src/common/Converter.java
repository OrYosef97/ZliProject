package common;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;

public class Converter {
	
	public static BufferedInputStream objectToByteArrayIS(Object obj) throws Exception{
		File file = (File)obj;
	      byte[] mybytearray = new byte[(int) file.length()];
	      FileInputStream fis = new FileInputStream(file);
	      BufferedInputStream bis = new BufferedInputStream(fis);
	      bis.read(mybytearray,0,mybytearray.length);
	      return bis;
	}
	
	public static ObjectInputStream byteArrayToObjectIS(Object byteArray) throws Exception{
		byte tmp[]=(byte[])byteArray;
		ByteArrayInputStream baip = new ByteArrayInputStream(tmp);
		ObjectInputStream ois = new ObjectInputStream(baip);
		return ois;
		
	}

}
