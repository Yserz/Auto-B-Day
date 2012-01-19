package de.fhb.autobday.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * group for many contacts
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Entity
@Table(name = "abdgroup")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "AbdGroup.findAll", query = "SELECT a FROM AbdGroup a"),
	@NamedQuery(name = "AbdGroup.findById", query = "SELECT a FROM AbdGroup a WHERE a.id = :id"),
	@NamedQuery(name = "AbdGroup.findByName", query = "SELECT a FROM AbdGroup a WHERE a.name = :name"),
	@NamedQuery(name = "AbdGroup.findByActive", query = "SELECT a FROM AbdGroup a WHERE a.active = :active")})
public class AbdGroup implements Serializable {

	@Basic(optional = false)
	@NotNull
	@Column(name = "updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "id")
	private String id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "abdGroup", fetch = FetchType.LAZY)
	private Collection<AbdGroupToContact> abdGroupToContactCollection;
	@JoinColumn(name = "account", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private AbdAccount account;

	public AbdGroup() {
	}

	public AbdGroup(String id) {
		this.id = id;
	}

	public AbdGroup(String id, String name, String template, boolean active) {
		this.id = id;
		this.name = name;
		this.template = template;
		this.active = active;
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
	public Collection<AbdGroupToContact> getAbdGroupToContactCollection() {
		return abdGroupToContactCollection;
	}

	public void setAbdGroupToContactCollection(Collection<AbdGroupToContact> abdGroupToContactCollection) {
		this.abdGroupToContactCollection = abdGroupToContactCollection;
	}

	public AbdAccount getAccount() {
		return account;
	}

	public void setAccount(AbdAccount account) {
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
		if (!(object instanceof AbdGroup)) {
			return false;
		}
		AbdGroup other = (AbdGroup) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "de.fhb.autobday.data.AbdGroup[ id=" + id + " ]";
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}
