package de.fhb.autobday.commons;

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
 * Helper for cipher Strings with DES.
 *
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
public class CipherHelper {

	/**
	 * Ciphers a given String. key length has to be 8 characters.
	 *
	 * @param raw the String to cipher
	 * @param key the key to cipher the String
	 * @return ciphered String
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException  
	 */
	public static String cipher(String raw, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {

		Key k = new SecretKeySpec(key.getBytes(), "DES");

		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, k);

		byte[] verschlusselt = cipher.doFinal(raw.getBytes("UTF8"));
		return new sun.misc.BASE64Encoder().encode(verschlusselt);

	}

	/**
	 * Deciphers a given String. key length has to be 8 characters.
	 *
	 * @param raw the String to decipher
	 * @param key the key to decipher the String
	 * @return decipered String
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException
	 * @throws IOException  
	 */
	public static String decipher(String raw, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException, IOException {
		String output;

		Key k = new SecretKeySpec(key.getBytes(), "DES");

		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, k);

		// Decode base64 to get bytes
		byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(raw);

		byte[] unverschlusselt = cipher.doFinal(dec);

		output = new String(unverschlusselt);

		return output;
	}
}
