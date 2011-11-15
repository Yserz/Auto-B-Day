package de.fhb.autobday.data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Michael Koppen
 */
@Entity
@Table(name = "abdgroup")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Abdgroup.findAll", query = "SELECT a FROM Abdgroup a"),
	@NamedQuery(name = "Abdgroup.findById", query = "SELECT a FROM Abdgroup a WHERE a.id = :id"),
	@NamedQuery(name = "Abdgroup.findByName", query = "SELECT a FROM Abdgroup a WHERE a.name = :name"),
	@NamedQuery(name = "Abdgroup.findByActive", query = "SELECT a FROM Abdgroup a WHERE a.active = :active")})
public class Abdgroup implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
	private Integer id;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 56)
    @Column(name = "name")
	private String name;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "template")
	private String template;
	@Basic(optional = false)
    @NotNull
    @Column(name = "active")
	private boolean active;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "abdgroup", fetch = FetchType.LAZY)
	private Collection<Contact> contactCollection;
	@JoinColumn(name = "account", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Accountdata account;

	public Abdgroup() {
	}

	public Abdgroup(Integer id) {
		this.id = id;
	}

	public Abdgroup(Integer id, String name, String template, boolean active) {
		this.id = id;
		this.name = name;
		this.template = template;
		this.active = active;
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

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@XmlTransient
	public Collection<Contact> getContactCollection() {
		return contactCollection;
	}

	public void setContactCollection(Collection<Contact> contactCollection) {
		this.contactCollection = contactCollection;
	}

	public Accountdata getAccount() {
		return account;
	}

	public void setAccount(Accountdata account) {
		this.account = account;
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
		if (!(object instanceof Abdgroup)) {
			return false;
		}
		Abdgroup other = (Abdgroup) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "de.fhb.autobday.data.Abdgroup[ id=" + id + " ]";
	}
	
}
