/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.autobday.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MacYser
 */
@Entity
@Table(name = "abdcontact")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Abdcontact.findAll", query = "SELECT a FROM Abdcontact a"),
	@NamedQuery(name = "Abdcontact.findById", query = "SELECT a FROM Abdcontact a WHERE a.id = :id"),
	@NamedQuery(name = "Abdcontact.findByName", query = "SELECT a FROM Abdcontact a WHERE a.name = :name"),
	@NamedQuery(name = "Abdcontact.findByFirstname", query = "SELECT a FROM Abdcontact a WHERE a.firstname = :firstname"),
	@NamedQuery(name = "Abdcontact.findBySex", query = "SELECT a FROM Abdcontact a WHERE a.sex = :sex"),
	@NamedQuery(name = "Abdcontact.findByMail", query = "SELECT a FROM Abdcontact a WHERE a.mail = :mail"),
	@NamedQuery(name = "Abdcontact.findByBday", query = "SELECT a FROM Abdcontact a WHERE a.bday = :bday"),
	@NamedQuery(name = "Abdcontact.findByActive", query = "SELECT a FROM Abdcontact a WHERE a.active = :active"),
	@NamedQuery(name = "Abdcontact.findByHashid", query = "SELECT a FROM Abdcontact a WHERE a.hashid = :hashid")})
public class Abdcontact implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "id")
	private String id;
	@Size(max = 255)
    @Column(name = "name")
	private String name;
	@Size(max = 255)
    @Column(name = "firstname")
	private String firstname;
	@Column(name = "sex")
	private Character sex;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "mail")
	private String mail;
	@Basic(optional = false)
    @NotNull
    @Column(name = "bday")
    @Temporal(TemporalType.DATE)
	private Date bday;
	@Basic(optional = false)
    @NotNull
    @Column(name = "active")
	private boolean active;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "hashid")
	private String hashid;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "abdcontact", fetch = FetchType.LAZY)
	private Collection<Abdgrouptocontact> abdgrouptocontactCollection;

	public Abdcontact() {
	}

	public Abdcontact(String id) {
		this.id = id;
	}

	public Abdcontact(String id, String mail, Date bday, boolean active, String hashid) {
		this.id = id;
		this.mail = mail;
		this.bday = bday;
		this.active = active;
		this.hashid = hashid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getBday() {
		return bday;
	}

	public void setBday(Date bday) {
		this.bday = bday;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getHashid() {
		return hashid;
	}

	public void setHashid(String hashid) {
		this.hashid = hashid;
	}

	@XmlTransient
	public Collection<Abdgrouptocontact> getAbdgrouptocontactCollection() {
		return abdgrouptocontactCollection;
	}

	public void setAbdgrouptocontactCollection(Collection<Abdgrouptocontact> abdgrouptocontactCollection) {
		this.abdgrouptocontactCollection = abdgrouptocontactCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Abdcontact)) {
			return false;
		}
		Abdcontact other = (Abdcontact) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "de.fhb.autobday.data.Abdcontact[ id=" + id + " ]";
	}
	
}
