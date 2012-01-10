package de.fhb.autobday.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Loads some propertyfiles
 *
 * @author 
 * Michael Koppen <koppen@fh-brandenburg.de>
 */
public class PropertyLoader {
	
	/**
	 * load the SystemMailProperty
	 * 
	 * @param String path
	 * @return Properties
	 * @throws IOException
	 */
	public Properties loadSystemMailProperty(String path) throws IOException{
		Properties props = new Properties();
		try {
			URL url =  de.fhb.autobday.commons.PropertyLoader.class.getResource("/SystemMail.properties");
			File temp = new File(url.getFile());
			FileInputStream stream = new FileInputStream(temp);
			props.load(stream);
			stream.close();
		} finally {
			
		}
		return props;
	}
	
	/**
	 * load the SystemMailAccountProperty
	 * 
	 * @param String path
	 * @return Properties
	 * @throws IOException
	 */
	public Properties loadSystemMailAccountProperty(String path) throws IOException{
		Properties props = new Properties();
		try {
			
			URL url =  de.fhb.autobday.commons.PropertyLoader.class.getResource("/SystemMailAccount.properties");
			File temp = new File(url.getFile());
			FileInputStream stream = new FileInputStream(temp);
			props.load(stream);
			stream.close();
		} finally {
			
		}
		return props;
	}
}
