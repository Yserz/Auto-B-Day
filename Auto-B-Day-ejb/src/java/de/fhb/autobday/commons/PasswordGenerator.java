package de.fhb.autobday.commons;

/**
 * This class generates passwords
 * and signs for security affairs.
 *
 *
 * @author 
 * Tino Reuschel <>
 * Michael Koppen <koppen@fh-brandenburg.de>
 * Andy Klay <klay@fh-brandenburg.de>
 * 
 */
public class PasswordGenerator {
	
	/**
	 * Mode for generating a random sequence
	 * of numbers and letters
	 */
	public final static int MODE_BOTH = 0;
	
	/**
	 * Mode for generating a random sequence of numbers
	 */
	public final static int MODE_ONLY_NUMERIC = 1;
	
	/**
	 * Mode for generating a random sequence of letters
	 */
	public final static int MODE_ONLY_LETTERS = 2;
	
	/**
	 * password length
	 */
	public final static int PASSWORD_LENGTH = 8;
	
	/**
	 * salt length
	 */
	public final static int SALT_LENGTH = 5;

	/**
	 * generate a random password consists of lowercase letters
	 * and uppercase letters
	 * 
	 * @return String - password
	 */
	public static String generatePassword() {

		return generateSigns(PASSWORD_LENGTH,MODE_ONLY_LETTERS);
	}
	
	/**
	 * generate a random sequence of char numbers
	 * 
	 * @return String - numbers
	 */
	public static String generateNumbers() {

		return generateSigns(SALT_LENGTH,MODE_ONLY_NUMERIC);
	}
	
	
	/**
	 * generate a random salt consists of lowercase letters
	 * and uppercase letters and numbers
	 * 
	 * @return String - salt
	 */
	public static String generateSalt() {

		return generateSigns(SALT_LENGTH,MODE_BOTH);
	}
	

	/**
	 * generate a random sequence of numbers or letters or both
	 *  
	 * @param int length
	 * @param int generationMode
	 * @return String - random char sequence
	 */
	private static String generateSigns(int length, int generationMode){
		
		final int ALPHABET_LENGTH = 26;
		final int NUMERIC_LENGTH = 10;
		final int ASCII_LOWERLETTER_OFFSET = 97;
		final int ASCII_UPPERLETTER_OFFSET = 65;
		final int ASCII_NUMBER_OFFSET = 48;
		
		StringBuilder generateContent = new StringBuilder();
		
		//generating
		switch(generationMode){
			case MODE_BOTH:
				
				// generate letters and numbers
				for (int x = 0; x < length; x++) {
					//letter or number?
					if(Math.round(Math.random())==0){
						//letter
						//upper or lower case letter?
						if(Math.round(Math.random())==0){
							//lower case
							generateContent.append((char) ((int) (Math.random() * ALPHABET_LENGTH) + ASCII_LOWERLETTER_OFFSET));
						}else{
							//upper case
							generateContent.append((char) ((int) (Math.random() * ALPHABET_LENGTH) + ASCII_UPPERLETTER_OFFSET));
						}
					}else{
						//number
						generateContent.append((char) ((int) (Math.random() * NUMERIC_LENGTH) + ASCII_NUMBER_OFFSET));
					}
				}
				
			break;
			
			case MODE_ONLY_NUMERIC:;
			
				//generate only numbers
				for (int x = 0; x < length; x++) {
					generateContent.append((char) ((int) (Math.random() * NUMERIC_LENGTH) + ASCII_NUMBER_OFFSET));
				}
			
			break;
			case MODE_ONLY_LETTERS:
				
				//generate only letters
				for (int x = 0; x < length; x++) {
					if(Math.round(Math.random())==0){
						generateContent.append((char) ((int) (Math.random() * ALPHABET_LENGTH) + ASCII_LOWERLETTER_OFFSET));
					}else{
						generateContent.append((char) ((int) (Math.random() * ALPHABET_LENGTH) + ASCII_UPPERLETTER_OFFSET));
					}
				}
				
			break;
		}
		
		return generateContent.toString();	
	}	
	
}