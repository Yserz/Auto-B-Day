package de.fhb.autobday.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class PropertyLoader {
	
	
	public static Properties loadSystemMailProperty() throws IOException{
		Properties props = new Properties();
		try {
			FileInputStream stream = new FileInputStream("settings/SystemMail.properties");
			props.load(stream);
			stream.close();
		} finally {
			
		}
		return props;
	}
	public static Properties loadSystemMailAccountProperty() throws IOException{
		Properties props = new Properties();
		try {
			FileInputStream stream = new FileInputStream("settings/SystemMailAccount.properties");
			props.load(stream);
			stream.close();
		} finally {
			
		}
		return props;
	}
}
