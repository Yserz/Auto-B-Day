package de.fhb.autobday.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.DateSelectEvent;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Named
@SessionScoped
public class CalendarBean implements Serializable {

	private Date date1;

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public void handleDateSelect(DateSelectEvent event) {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		
		//Speichern des Dates
		setDate1(event.getDate());
		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getDate())));
	}
}
