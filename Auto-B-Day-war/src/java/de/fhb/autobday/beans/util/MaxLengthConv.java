package de.fhb.autobday.beans.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * TODO
 * @author MacYser
 */
@FacesConverter(value = "maxLengthConv")
public class MaxLengthConv implements Converter {

	/**
	 * Methode empfängt Eingabe, konvertiert den Wert und gibt ihn als Object
	 * zurück.
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.length() > 16) {
			String temp = "";
			char[] charArr = value.toCharArray();
			for (int i = 0; i < 16; i++) {
				temp += charArr[i];
			}
			return temp.toString()+"...";
		}
		return value.toUpperCase();
	}

	/**
	 * Methode gibt konvertierten Wert als String an Benutzer zurück
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value.toString().length() > 16) {
			String temp = "";
			char[] charArr = value.toString().toCharArray();
			for (int i = 0; i < 16; i++) {
				temp += charArr[i];
			}
			return temp.toString()+"...";
		}
		return (String) value;
	}
}
