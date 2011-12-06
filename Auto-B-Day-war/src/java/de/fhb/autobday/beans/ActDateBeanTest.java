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
	Date date = new Date();
	String hallo = "hallo";


	
	public void printDate(){
		System.out.println(date);
	}
	public String printHallo(){
		System.out.println(hallo);
		return "index";
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHallo() {
		return hallo;
	}

	public void setHallo(String hallo) {
		this.hallo = hallo;
	}
	
	
}
