package de.fhb.autobday.data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * realizes the user of the autobday-system
 *
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
@Entity
@Table(name = "abduser")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "AbdUser.findAll", query = "SELECT a FROM AbdUser a"),
	@NamedQuery(name = "AbdUser.findById", query = "SELECT a FROM AbdUser a WHERE a.id = :id"),
	@NamedQuery(name = "AbdUser.findByUsername", query = "SELECT a FROM AbdUser a WHERE a.username = :username"),
	@NamedQuery(name = "AbdUser.findByPasswort", query = "SELECT a FROM AbdUser a WHERE a.passwort = :passwort"),
	@NamedQuery(name = "AbdUser.findBySalt", query = "SELECT a FROM AbdUser a WHERE a.salt = :salt"),
	@NamedQuery(name = "AbdUser.findByName", query = "SELECT a FROM AbdUser a WHERE a.name = :name"),
	@NamedQuery(name = "AbdUser.findByFirstname", query = "SELECT a FROM AbdUser a WHERE a.firstname = :firstname")})
public class AbdUser implements Serializable {

	@Size(max = 255)
	@Column(name = "mail")
	private String mail;
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
	@Column(name = "salt")
	private String salt;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "name")
	private String name;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "firstname")
	private String firstname;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "abduser", fetch = FetchType.LAZY)
	private Collection<AbdAccount> abdAccountCollection;

	public AbdUser() {
	}

	public AbdUser(Integer id) {
		this.id = id;
	}

	public AbdUser(Integer id, String username, String passwort, String salt, String name, String firstname) {
		this.id = id;
		this.username = username;
		this.passwort = passwort;
		this.salt = salt;
		this.name = name;
		this.firstname = firstname;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	@XmlTransient
	public Collection<AbdAccount> getAbdAccountCollection() {
		return abdAccountCollection;
	}

	public void setAbdAccountCollection(Collection<AbdAccount> abdAccountCollection) {
		this.abdAccountCollection = abdAccountCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof AbdUser)) {
			return false;
		}
		AbdUser other = (AbdUser) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "de.fhb.autobday.data.AbdUser[ id=" + id + " ]";
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}
