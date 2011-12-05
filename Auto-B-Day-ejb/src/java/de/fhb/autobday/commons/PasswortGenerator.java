package de.fhb.autobday.commons;

public class PasswortGenerator {

	public static String generatePassword()  
	  {  
		final int PASSWORD_LENGTH = 8;  
	    StringBuffer newPW = new StringBuffer();  
	    for (int x = 0; x < PASSWORD_LENGTH; x++)  
	    {  
	      newPW.append((char)((int)(Math.random()*26)+97));  
	    }  
	    return  newPW.toString(); 
	  }  
	
}
