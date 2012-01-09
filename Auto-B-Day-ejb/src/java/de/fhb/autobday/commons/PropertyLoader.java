package de.fhb.autobday.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class PropertyLoader {
	
	
	public static Properties loadSystemMailProperty(String path) throws IOException{
		Properties props = new Properties();
		try {
			FileInputStream stream = new FileInputStream(path);
			props.load(stream);
			stream.close();
		} finally {
			
		}
		return props;
	}
	public static Properties loadSystemMailAccountProperty(String path) throws IOException{
		Properties props = new Properties();
		try {
			FileInputStream stream = new FileInputStream(path);
			props.load(stream);
			stream.close();
		} finally {
			
		}
		return props;
	}
}
