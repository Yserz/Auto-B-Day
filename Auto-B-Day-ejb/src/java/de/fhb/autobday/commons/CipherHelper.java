package de.fhb.autobday.commons;

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
	public static String cipher(String raw, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		Key k = new SecretKeySpec( key.getBytes(), "DES" );
		
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, k);
		byte[] verschlusselt = cipher.doFinal(raw.getBytes());
		
		return new String(verschlusselt);
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
	public static String decipher(String raw, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		Key k = new SecretKeySpec( key.getBytes(), "DES" );
		
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, k);
		byte[] unverschlusselt = cipher.doFinal(raw.getBytes());
		return new String(unverschlusselt);
	}
}
