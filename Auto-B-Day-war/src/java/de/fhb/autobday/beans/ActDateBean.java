/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.beans;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author MacYser
 */
@ManagedBean
public class ActDateBean {
	private Date date = new Date();


	
	public void printDate(){
		System.out.println(date);
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
