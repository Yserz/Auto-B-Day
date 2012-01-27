package de.fhb.autobday.beans.util;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 * This Class creates the options for the filter in Primefaces
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
@Named
@RequestScoped
public class TableOptions {

	private SelectItem[] trueFalseOption;

	/**
	 * Creates a new instance of TableOptions
	 */
	public TableOptions() {
		trueFalseOption = createFilterOptionsBoolean();
	}
	/**
	 * Formats options into SelectItem[] out of a String[].
	 * @param data
	 * @return itemArray
	 */
	private SelectItem[] createFilterOptions(String[] data) {
		SelectItem[] options = new SelectItem[data.length + 1];

		options[0] = new SelectItem("", "Select");
		for (int i = 0; i < data.length; i++) {
			options[i + 1] = new SelectItem(data[i], data[i]);
		}

		return options;
	}
	/**
	 * Formats true and false into SelectItem[].
	 * @return itemArray
	 */
	private SelectItem[] createFilterOptionsBoolean() {
		SelectItem[] options = new SelectItem[3];

		options[0] = new SelectItem("", "Select");
		options[1] = new SelectItem("true", "√");
		options[2] = new SelectItem("false", "X");

		return options;
	}

	public SelectItem[] getTrueFalseOption() {
		return trueFalseOption;
	}

	public void setTrueFalseOption(SelectItem[] trueFalseOption) {
		this.trueFalseOption = trueFalseOption;
	}
}
