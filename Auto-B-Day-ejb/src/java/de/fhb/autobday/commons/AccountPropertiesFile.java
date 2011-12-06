package de.fhb.autobday.commons;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AccountPropertiesFile {
	
	public static HashMap<String,String> getProperties(String filename){
		HashMap<String,String> props = new HashMap<String,String>();
		Properties properties = new Properties();
		String loginname;
		String encodedPassword;
		byte[] key;
		byte[] encryptedData;
		
		try {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(filename));
			properties.load(stream);
			stream.close();
			
			loginname= properties.getProperty("loginname");
			encodedPassword = properties.getProperty("password");			

			key = loginname.getBytes(); 
			encryptedData = encodedPassword.getBytes();
			
			try {
				Cipher c = Cipher.getInstance("AES");

			SecretKeySpec k =new SecretKeySpec(key, "AES");
			c.init(Cipher.DECRYPT_MODE, k);
			byte[] data = c.doFinal(encryptedData);

			props.put("loginname", loginname);
			props.put("password", data.toString());
			return props;
			
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e){
			
		}
		return null;
	}

}
