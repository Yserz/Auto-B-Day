/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MacYser
 */
@Entity
@Table(name = "abdgrouptocontact")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Abdgrouptocontact.findAll", query = "SELECT a FROM Abdgrouptocontact a"),
	@NamedQuery(name = "Abdgrouptocontact.findByGroup", query = "SELECT a FROM Abdgrouptocontact a WHERE a.abdgrouptocontactPK.group = :group"),
	@NamedQuery(name = "Abdgrouptocontact.findByContact", query = "SELECT a FROM Abdgrouptocontact a WHERE a.abdgrouptocontactPK.contact = :contact"),
	@NamedQuery(name = "Abdgrouptocontact.findByActive", query = "SELECT a FROM Abdgrouptocontact a WHERE a.active = :active")})
public class Abdgrouptocontact implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected AbdgrouptocontactPK abdgrouptocontactPK;
	@Basic(optional = false)
    @NotNull
    @Column(name = "active")
	private boolean active;
	@JoinColumn(name = "group", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Abdgroup abdgroup;
	@JoinColumn(name = "contact", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Abdcontact abdcontact;

	public Abdgrouptocontact() {
	}

	public Abdgrouptocontact(AbdgrouptocontactPK abdgrouptocontactPK) {
		this.abdgrouptocontactPK = abdgrouptocontactPK;
	}

	public Abdgrouptocontact(AbdgrouptocontactPK abdgrouptocontactPK, boolean active) {
		this.abdgrouptocontactPK = abdgrouptocontactPK;
		this.active = active;
	}

	public Abdgrouptocontact(String group, String contact) {
		this.abdgrouptocontactPK = new AbdgrouptocontactPK(group, contact);
	}

	public AbdgrouptocontactPK getAbdgrouptocontactPK() {
		return abdgrouptocontactPK;
	}

	public void setAbdgrouptocontactPK(AbdgrouptocontactPK abdgrouptocontactPK) {
		this.abdgrouptocontactPK = abdgrouptocontactPK;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Abdgroup getAbdgroup() {
		return abdgroup;
	}

	public void setAbdgroup(Abdgroup abdgroup) {
		this.abdgroup = abdgroup;
	}

	public Abdcontact getAbdcontact() {
		return abdcontact;
	}

	public void setAbdcontact(Abdcontact abdcontact) {
		this.abdcontact = abdcontact;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (abdgrouptocontactPK != null ? abdgrouptocontactPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Abdgrouptocontact)) {
			return false;
		}
		Abdgrouptocontact other = (Abdgrouptocontact) object;
		if ((this.abdgrouptocontactPK == null && other.abdgrouptocontactPK != null) || (this.abdgrouptocontactPK != null && !this.abdgrouptocontactPK.equals(other.abdgrouptocontactPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "de.fhb.autobday.data.Abdgrouptocontact[ abdgrouptocontactPK=" + abdgrouptocontactPK + " ]";
	}
	
}
