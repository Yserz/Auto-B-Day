/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author MacYser
 */
@Embeddable
public class AbdGroupToContactPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "group")
	private String group;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "contact")
	private String contact;

	public AbdGroupToContactPK() {
	}

	public AbdGroupToContactPK(String group, String contact) {
		this.group = group;
		this.contact = contact;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (group != null ? group.hashCode() : 0);
		hash += (contact != null ? contact.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AbdGroupToContactPK)) {
			return false;
		}
		AbdGroupToContactPK other = (AbdGroupToContactPK) object;
		if ((this.group == null && other.group != null) || (this.group != null && !this.group.equals(other.group))) {
			return false;
		}
		if ((this.contact == null && other.contact != null) || (this.contact != null && !this.contact.equals(other.contact))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "de.fhb.autobday.data.AbdGroupToContactPK[ group=" + group + ", contact=" + contact + " ]";
	}
	
}
