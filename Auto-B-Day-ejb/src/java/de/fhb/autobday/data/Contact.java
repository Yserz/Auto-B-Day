package de.fhb.autobday.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michael Koppen
 */
@Entity
@Table(name = "contact")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
	@NamedQuery(name = "Contact.findById", query = "SELECT c FROM Contact c WHERE c.id = :id"),
	@NamedQuery(name = "Contact.findByName", query = "SELECT c FROM Contact c WHERE c.name = :name"),
	@NamedQuery(name = "Contact.findByFirstname", query = "SELECT c FROM Contact c WHERE c.firstname = :firstname"),
	@NamedQuery(name = "Contact.findBySex", query = "SELECT c FROM Contact c WHERE c.sex = :sex"),
	@NamedQuery(name = "Contact.findByBday", query = "SELECT c FROM Contact c WHERE c.bday = :bday"),
	@NamedQuery(name = "Contact.findByActive", query = "SELECT c FROM Contact c WHERE c.active = :active"),
	@NamedQuery(name = "Contact.findByHashid", query = "SELECT c FROM Contact c WHERE c.hashid = :hashid")})
public class Contact implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
	private Integer id;
	@Size(max = 56)
    @Column(name = "name")
	private String name;
	@Size(max = 56)
    @Column(name = "firstname")
	private String firstname;
	@Column(name = "sex")
	private Character sex;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
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
    @Size(min = 1, max = 20)
    @Column(name = "hashid")
	private String hashid;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "urlid")
	private String urlid;
	@JoinColumn(name = "abdgroup", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Abdgroup abdgroup;

	public Contact() {
	}

	public Contact(Integer id) {
		this.id = id;
	}

	public Contact(Integer id, String mail, Date bday, boolean active, String hashid, String urlid) {
		this.id = id;
		this.mail = mail;
		this.bday = bday;
		this.active = active;
		this.hashid = hashid;
		this.urlid = urlid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getUrlid() {
		return urlid;
	}

	public void setUrlid(String urlid) {
		this.urlid = urlid;
	}

	public Abdgroup getAbdgroup() {
		return abdgroup;
	}

	public void setAbdgroup(Abdgroup abdgroup) {
		this.abdgroup = abdgroup;
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
		if (!(object instanceof Contact)) {
			return false;
		}
		Contact other = (Contact) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "de.fhb.autobday.data.Contact[ id=" + id + " ]";
	}
	
}
