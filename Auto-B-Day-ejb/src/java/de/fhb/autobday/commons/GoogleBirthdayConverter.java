package de.fhb.autobday.commons;

import java.sql.Date;

import de.fhb.autobday.exception.CanNotConvetGoogleBirthdayException;

/**
 * Helper to convert a string with a birthdaydate to a date-object.
 * 
 * @author 
 * Tino Reuschel <>
 * Michael Koppen <koppen@fh-brandenburg.de>
 * Andy Klay <klay@fh-brandenburg.de>
 */
public class GoogleBirthdayConverter {
	
	/**
	 * Converts a string with a birthdaydate to a date-object
	 * 
	 * @param String birthday
	 * @return Date
	 * @throws CanNotConvetGoogleBirthdayException 
	 */
	@SuppressWarnings({ "deprecation", "finally" })
	public static Date convertBirthday(String birthdayString){
		Date date;
		int day=1,month=1,year=1970;
		
		if (birthdayString.trim().length()==0){
			return null;
		}
		try {
			if (birthdayString.length()==7){
				year=0;
				month=Integer.parseInt(birthdayString.substring(2, 4));
				day=Integer.parseInt(birthdayString.substring(5, 7));
			} else {
				year=Integer.parseInt(birthdayString.substring(0, 4));
				month=Integer.parseInt(birthdayString.substring(5, 7));
				day=Integer.parseInt(birthdayString.substring(8, 10));			
			}
		} catch (NumberFormatException e){
			e.printStackTrace();
		} finally {
			date = new Date(year-1900,month-1,day);
			return date;
		}
		
		
	}

}
