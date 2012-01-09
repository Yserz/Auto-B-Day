package de.fhb.autobday.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class PropertyLoader {
	
	
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
