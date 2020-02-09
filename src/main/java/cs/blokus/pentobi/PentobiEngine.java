package cs.blokus.pentobi;

import java.io.IOException;

public class PentobiEngine { 
	 private static PentobiEngine single_instance = null; 
	
	 private  String address = "C:\\Users\\User\\Documents\\pentobi-master\\pentobi_gtp\\pentobi-gtp";
	 
	 private ProcessBuilder builder;
	
	 // private constructor restricted to this class itself 
	 private PentobiEngine() 
	 { 
		 builder = new ProcessBuilder(address, "--quiet");
		
	 } 
	
	 public static PentobiEngine getInstance() 
	 { 
	     if (single_instance == null) 
	         single_instance = new PentobiEngine(); 
	
	     return single_instance; 
	 }

	public Process getProcess() {
		 try {
				return builder.start();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
	}

} 
