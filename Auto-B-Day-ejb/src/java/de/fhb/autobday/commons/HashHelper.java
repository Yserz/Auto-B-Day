package de.fhb.autobday.commons;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
/**
 * Diese Klasse generiert einen SHA1- oder MD5-Haswert von einem String.
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class HashHelper {

	/**
	 * Diese Methode führt die eigentliche Berechnung des Hashwertes aus.
	 */
    private static String calculateHash(MessageDigest algorithm, String pw) throws UnsupportedEncodingException{

        // get the hash value as byte array
        byte[] hash = algorithm.digest(pw.getBytes("UTF-8"));

        return byteArray2Hex(hash);
    }
	/**
	 * Diese Methode formatiert den Byte-Array in einen Hexwert.
	 */
    private static String byteArray2Hex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
	/**
	 * Diese Methode generiert den SHA1-Hashwert von einem String.
	 * 
	 * @param pw 
	 * @return 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
    public static String calcSHA1(String pw) 
			throws UnsupportedEncodingException, NoSuchAlgorithmException{

        MessageDigest sha1 = MessageDigest.getInstance("SHA1");      

        return calculateHash(sha1, pw);
    }
	/**
	 * Diese Methode generiert den MD5-Hashwert von einem String.
	 * 
	 * @param pw 
	 * @return 
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException  
	 */
	public static String calcMD5(String pw) 
			throws UnsupportedEncodingException, NoSuchAlgorithmException{

        MessageDigest md5  = MessageDigest.getInstance("MD5");        

        return calculateHash(md5, pw);
    }
}
