package launcher;

import java.nio.file.*;

public class ChecksumReader {
	
	public static String readFileAsString(String fileName)throws Exception 
	  { 
	    String data = ""; 
	    data = new String(Files.readAllBytes(Paths.get(fileName))); 
	    return data; 
	  } 
	
	public static boolean isUpdated() throws Exception {
		String oldChecksum = readFileAsString(OSValidator.installDir() + "checksum.txt"); 
	    String newChecksum = readFileAsString(OSValidator.installDir() + "newChecksum.txt");
	    if (oldChecksum == newChecksum) {
	    	return false;
	    } else if (oldChecksum != newChecksum) {
	    	return true;
	    }
		return true;
		
	}
	  
	  public static void main(String[] args) throws Exception 
	  { 
	    isUpdated();
	  } 

}
