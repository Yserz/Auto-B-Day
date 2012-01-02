package de.fhb.autobday.commons;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
/**
 * This class generates a SHA1- or MD5-hashvalue of a string.
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class HashHelper {

	/**
	 * This method really calcutates the hashvalues.
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
	 * This method generates the SHA1-hashvalue of a String.
	 * 
	 * @param pw 
	 * @return hashvalue - String
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
    public static String calcSHA1(String pw) 
			throws UnsupportedEncodingException, NoSuchAlgorithmException{

        MessageDigest sha1 = MessageDigest.getInstance("SHA1");      

        return calculateHash(sha1, pw);
    }
    
	/**
	 * This method generates the MD5-hashvalue of a String.
	 * 
	 * @param pw 
	 * @return hashvalue - String
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException  
	 */
	public static String calcMD5(String pw) 
			throws UnsupportedEncodingException, NoSuchAlgorithmException{

        MessageDigest md5  = MessageDigest.getInstance("MD5");        

        return calculateHash(md5, pw);
    }
	
}