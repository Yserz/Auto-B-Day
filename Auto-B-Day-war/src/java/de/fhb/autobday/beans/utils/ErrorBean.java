package de.fhb.autobday.beans.utils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named(value = "errorBean")
@RequestScoped
public class ErrorBean {
	private String errorText;
	/**
	 * Creates a new instance of ErrorBean
	 */
	public ErrorBean() {
		errorText = null;
	}
	
	public String handleException(Exception ex){
		errorText = ex.getMessage();
		return "error";
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	
	
	
}
