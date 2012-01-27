/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.exception.commons;

import de.fhb.autobday.exception.AbdException;

/**
 *
 * @author Dragon
 */
public class CouldNotLoadMasterPasswordException extends AbdException{
	
		public CouldNotLoadMasterPasswordException(){
		super();
	}
	
	public CouldNotLoadMasterPasswordException(String s){
		super(s);
	}
	
}
