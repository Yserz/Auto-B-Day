package de.fhb.autobday.commons;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.Properties;

/**
 * 
 * This class read a properties file with logindata.
 * 
 * @author Tino Reuschel<reuschel@fh-brandenburg.de
 * 
 */
public class AccountPropertiesFile {
	
	/**
	 * this methode return a hashmap with the logindata
	 * with key "loginname" you get the loginname
	 * with key "password" you get the password
	 * 
	 * @param String filename
	 * 
	 * @return HashMap<String,String>
	 */
	public static HashMap<String,String> getProperties(String filename){
		
		HashMap<String,String> props = new HashMap<String,String>();
		Properties properties = new Properties();
		String loginname;
		String password;
		
		try {
			System.out.println("bla");
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(filename));
			System.out.println("bla1");
			properties.load(stream);
			System.out.println("bla2");
			stream.close();
			System.out.println("bla3");
			//get data from the propertyfile
			loginname= properties.getProperty("loginname");
			password = properties.getProperty("password");			

			System.out.println(loginname + password);
			
			props.put("loginname", loginname);
			props.put("password", password);
			System.out.println("bla4");
			return props;
			
		} catch (IOException e){
			
		}
		return null;
	}

}
