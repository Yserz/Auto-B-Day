package de.fhb.autobday.data;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Entity
@Table(name = "abdgrouptocontact")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "AbdGroupToContact.findAll", query = "SELECT a FROM AbdGroupToContact a"),
	@NamedQuery(name = "AbdGroupToContact.findByGroup", query = "SELECT a FROM AbdGroupToContact a WHERE a.abdGroupToContactPK.group1 = :group1"),
	@NamedQuery(name = "AbdGroupToContact.findByContact", query = "SELECT a FROM AbdGroupToContact a WHERE a.abdGroupToContactPK.contact = :contact"),
	@NamedQuery(name = "AbdGroupToContact.findByActive", query = "SELECT a FROM AbdGroupToContact a WHERE a.active = :active")})
public class AbdGroupToContact implements Serializable {
	@JoinColumn(name = "group1", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private AbdGroup abdGroup;
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected AbdGroupToContactPK abdGroupToContactPK;
	@Basic(optional = false)
    @NotNull
    @Column(name = "active")
	private boolean active;
	@JoinColumn(name = "contact", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private AbdContact abdContact;

	public AbdGroupToContact() {
	}

	public AbdGroupToContact(AbdGroupToContactPK abdGroupToContactPK) {
		this.abdGroupToContactPK = abdGroupToContactPK;
	}

	public AbdGroupToContact(AbdGroupToContactPK abdGroupToContactPK, boolean active) {
		this.abdGroupToContactPK = abdGroupToContactPK;
		this.active = active;
	}

	public AbdGroupToContact(String group, String contact) {
		this.abdGroupToContactPK = new AbdGroupToContactPK(group, contact);
	}

	public AbdGroupToContactPK getAbdGroupToContactPK() {
		return abdGroupToContactPK;
	}

	public void setAbdGroupToContactPK(AbdGroupToContactPK abdGroupToContactPK) {
		this.abdGroupToContactPK = abdGroupToContactPK;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public AbdGroup getAbdGroup() {
		return abdGroup;
	}

	public void setAbdGroup(AbdGroup abdGroup) {
		this.abdGroup = abdGroup;
	}

	public AbdContact getAbdContact() {
		return abdContact;
	}

	public void setAbdContact(AbdContact abdContact) {
		this.abdContact = abdContact;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (abdGroupToContactPK != null ? abdGroupToContactPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AbdGroupToContact)) {
			return false;
		}
		AbdGroupToContact other = (AbdGroupToContact) object;
		if ((this.abdGroupToContactPK == null && other.abdGroupToContactPK != null) || (this.abdGroupToContactPK != null && !this.abdGroupToContactPK.equals(other.abdGroupToContactPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "de.fhb.autobday.data.AbdGroupToContact[ abdGroupToContactPK=" + abdGroupToContactPK + " ]";
	}

	
}
