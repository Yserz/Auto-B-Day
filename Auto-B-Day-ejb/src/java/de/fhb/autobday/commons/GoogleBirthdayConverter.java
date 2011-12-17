package de.fhb.autobday.commons;

import java.sql.Date;

/**
 * Helper to convert a string with a birthdaydate to a date-object.
 * 
 * @author Tino Reuschel
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class GoogleBirthdayConverter {
	
	/**
	 * Converts a string with a birthdaydate to a date-object
	 * 
	 * @param String birthday
	 * @return Date
	 */
	@SuppressWarnings("deprecation")
	public static Date convertBirthday(String birthdayString){
		Date date;
		int day=1,month=1,year=1970;
		
		if (birthdayString.trim().length()==0){
			return null;
		}
		
		if (birthdayString.length()==7){
			year=0;
			try {
				month=Integer.parseInt(birthdayString.substring(2, 4));
			} catch (NumberFormatException e){
				System.out.println("Can not convert the Month from String to int");
			}
			try {
				day=Integer.parseInt(birthdayString.substring(5, 7));
			} catch (NumberFormatException e){
				System.out.println("Can not convert the Day from String to int");
			}
		} else {
			try {
				year=Integer.parseInt(birthdayString.substring(0, 4));
			} catch (NumberFormatException e){
				System.out.println("Can not convert the Year from String to int");
			}
			try {
				month=Integer.parseInt(birthdayString.substring(5, 7));
			} catch (NumberFormatException e){
				System.out.println("Can not convert the Month from String to int");
			}
			try {
				day=Integer.parseInt(birthdayString.substring(8, 10));
			} catch (NumberFormatException e){
				System.out.println("Can not convert the Day from String to int");
			}			
		}
		
		date = new Date(year-1900,month-1,day);
		
		return date;
	}

}
