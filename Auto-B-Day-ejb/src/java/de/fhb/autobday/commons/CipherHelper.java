package de.fhb.autobday.commons;

import de.fhb.autobday.manager.LoggerInterceptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class CipherHelper {

	/**
	 * Ciphers a given String.
	 * key length has to be 8 characters.
	 * 
	 * @param raw
	 * @param key
	 * @return ciphered String
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException 
	 */
	public static String cipher(String raw, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
		System.out.println("cip RAW: "+raw);
		System.out.println("cip KEY: "+key);
		
		
		Key k = new SecretKeySpec( key.getBytes(), "DES" );
		
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, k);
		
		byte[] verschlusselt = cipher.doFinal(raw.getBytes("UTF8"));
		return new sun.misc.BASE64Encoder().encode(verschlusselt);
		
	}
	/**
	 * Deciphers a given String.
	 * key length has to be 8 characters.
	 * 
	 * @param raw
	 * @param key
	 * @return decipered String
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException 
	 */
	public static String decipher(String raw, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException, IOException {
		System.out.println("decip RAW: "+raw);
		System.out.println("bytes RAW: "+raw.getBytes());
		System.out.println("decip KEY: "+key);
		System.out.println("bytes KEY: "+key.getBytes());
		
		
		String output;
		
		Key k = new SecretKeySpec( key.getBytes(), "DES" );
		
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, k);
		
		// Decode base64 to get bytes
		byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(raw);
		
		byte[] unverschlusselt = cipher.doFinal(dec);
		
		output = new String(unverschlusselt);
		
		System.out.println("output: "+output);
		
		
		return output;
	}
}
