package de.fhb.autobday.data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
@Entity
@Table(name = "abdaccount")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "AbdAccount.findAll", query = "SELECT a FROM AbdAccount a"),
	@NamedQuery(name = "AbdAccount.findById", query = "SELECT a FROM AbdAccount a WHERE a.id = :id"),
	@NamedQuery(name = "AbdAccount.findByUsername", query = "SELECT a FROM AbdAccount a WHERE a.username = :username"),
	@NamedQuery(name = "AbdAccount.findByPasswort", query = "SELECT a FROM AbdAccount a WHERE a.passwort = :passwort"),
	@NamedQuery(name = "AbdAccount.findByType", query = "SELECT a FROM AbdAccount a WHERE a.type = :type")})
public class AbdAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
	private Integer id;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
	private String username;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "passwort")
	private String passwort;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "type")
	private String type;
	@JoinColumn(name = "abduser", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private AbdUser abduser;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.LAZY)
	private Collection<AbdGroup> abdGroupCollection;

	public AbdAccount() {
	}

	public AbdAccount(Integer id) {
		this.id = id;
	}

	public AbdAccount(Integer id, String username, String passwort, String type) {
		this.id = id;
		this.username = username;
		this.passwort = passwort;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AbdUser getAbduser() {
		return abduser;
	}

	public void setAbduser(AbdUser abduser) {
		this.abduser = abduser;
	}

	@XmlTransient
	public Collection<AbdGroup> getAbdGroupCollection() {
		return abdGroupCollection;
	}

	public void setAbdGroupCollection(Collection<AbdGroup> abdGroupCollection) {
		this.abdGroupCollection = abdGroupCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AbdAccount)) {
			return false;
		}
		AbdAccount other = (AbdAccount) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "de.fhb.autobday.data.AbdAccount[ id=" + id + " ]";
	}
	
}
