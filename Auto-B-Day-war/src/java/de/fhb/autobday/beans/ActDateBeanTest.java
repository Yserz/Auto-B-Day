/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.beans;

import java.util.Date;

/**
 *
 * @author MacYser
 */
public class ActDateBeanTest {
	private Date date = new Date();
	private String hallo = "hallo";


	
	public void printDate(){
		System.out.println(date);
	}
	public String printHallo(){
		return hallo;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
