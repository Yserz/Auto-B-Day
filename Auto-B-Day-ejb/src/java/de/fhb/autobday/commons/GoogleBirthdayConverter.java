package de.fhb.autobday.commons;

import java.sql.Date;

public class GoogleBirthdayConverter {
	
	@SuppressWarnings("deprecation")
	public static Date convertBirthday(String gbirthday){
		Date date;
		int day=1,month=1,year=1970;
		
		if (gbirthday.trim().length()==0){
			return null;
		}
		
		if (gbirthday.length()==7){
			year=0;
			try {
				month=Integer.parseInt(gbirthday.substring(2, 3));
			} catch (NumberFormatException e){
				System.out.println("Can not convert the Month from String to int");
			}
			try {
				day=Integer.parseInt(gbirthday.substring(5, 6));
			} catch (NumberFormatException e){
				System.out.println("Can not convert the Day from String to int");
			}
		} else {
			try {
				year=Integer.parseInt(gbirthday.substring(0, 3));
			} catch (NumberFormatException e){
				System.out.println("Can not convert the Year from String to int");
			}
			try {
				month=Integer.parseInt(gbirthday.substring(5, 6));
			} catch (NumberFormatException e){
				System.out.println("Can not convert the Month from String to int");
			}
			try {
				day=Integer.parseInt(gbirthday.substring(8, 9));
			} catch (NumberFormatException e){
				System.out.println("Can not convert the Day from String to int");
			}			
		}
		date = new Date(year,month,day);
		return date;
	}

}
