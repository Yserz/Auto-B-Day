package de.fhb.autobday.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * the primary key for AbdGroupToContact
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
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
	@Column(name = "group1")
	private String group1;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "contact")
	private String contact;

	public AbdGroupToContactPK() {
	}

	public AbdGroupToContactPK(String group, String contact) {
		this.group1 = group;
		this.contact = contact;
	}

	public String getGroup() {
		return group1;
	}

	public void setGroup(String group) {
		this.group1 = group;
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
		hash += (group1 != null ? group1.hashCode() : 0);
		hash += (contact != null ? contact.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AbdGroupToContactPK)) {
			return false;
		}
		AbdGroupToContactPK other = (AbdGroupToContactPK) object;
		if ((this.group1 == null && other.group1 != null) || (this.group1 != null && !this.group1.equals(other.group1))) {
			return false;
		}
		if ((this.contact == null && other.contact != null) || (this.contact != null && !this.contact.equals(other.contact))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "de.fhb.autobday.data.AbdGroupToContactPK[ group=" + group1 + ", contact=" + contact + " ]";
	}
}
