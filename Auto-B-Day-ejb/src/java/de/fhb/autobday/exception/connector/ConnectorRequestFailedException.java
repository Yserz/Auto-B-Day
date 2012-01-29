/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.exception.connector;

/**
 *
 * @author Dragon
 */
public class ConnectorRequestFailedException extends ConnectorException {
	
	/**
	 * Constructor
	 */
	public ConnectorRequestFailedException() {
		super();
	}
	
	/**
	 * Constructor with message
	 * @param string
	 */
	public ConnectorRequestFailedException(String string) {
		super(string);
	}
	
}
