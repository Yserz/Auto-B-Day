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
@Table(name = "accountdata")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Accountdata.findAll", query = "SELECT a FROM Accountdata a"),
	@NamedQuery(name = "Accountdata.findById", query = "SELECT a FROM Accountdata a WHERE a.id = :id"),
	@NamedQuery(name = "Accountdata.findByUsername", query = "SELECT a FROM Accountdata a WHERE a.username = :username")})
public class Accountdata implements Serializable {
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
    @Column(name = "username")
	private String username;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "passwort")
	private String passwort;
	@Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "type")
	private String type;
	@JoinColumn(name = "abduser", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Abduser abduser;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.LAZY)
	private Collection<Abdgroup> abdgroupCollection;

	public Accountdata() {
	}

	public Accountdata(Integer id) {
		this.id = id;
	}

	public Accountdata(Integer id, String username, String passwort, String type) {
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

	public Abduser getAbduser() {
		return abduser;
	}

	public void setAbduser(Abduser abduser) {
		this.abduser = abduser;
	}

	@XmlTransient
	public Collection<Abdgroup> getAbdgroupCollection() {
		return abdgroupCollection;
	}

	public void setAbdgroupCollection(Collection<Abdgroup> abdgroupCollection) {
		this.abdgroupCollection = abdgroupCollection;
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
		if (!(object instanceof Accountdata)) {
			return false;
		}
		Accountdata other = (Accountdata) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "de.fhb.autobday.data.Accountdata[ id=" + id + " ]";
	}
	
}
