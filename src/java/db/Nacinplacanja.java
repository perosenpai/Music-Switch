/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Petrik
 */
@Entity
@Table(name = "nacinplacanja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nacinplacanja.findAll", query = "SELECT n FROM Nacinplacanja n")})
    
public class Nacinplacanja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nac_id")
    private Integer nacId;
    @Size(max = 50)
    @Column(name = "nac_naziv")
    private String nacNaziv;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "nac_opis")
    private String nacOpis;
    @OneToMany(mappedBy = "nacId")
    private Set<Racun> racunSet;

    public Nacinplacanja() {
    }

    public Nacinplacanja(Integer nacId) {
        this.nacId = nacId;
    }

    public Integer getNacId() {
        return nacId;
    }

    public void setNacId(Integer nacId) {
        this.nacId = nacId;
    }

    public String getNacNaziv() {
        return nacNaziv;
    }

    public void setNacNaziv(String nacNaziv) {
        this.nacNaziv = nacNaziv;
    }

    public String getNacOpis() {
        return nacOpis;
    }

    public void setNacOpis(String nacOpis) {
        this.nacOpis = nacOpis;
    }

    @XmlTransient
    public Set<Racun> getRacunSet() {
        return racunSet;
    }

    public void setRacunSet(Set<Racun> racunSet) {
        this.racunSet = racunSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nacId != null ? nacId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nacinplacanja)) {
            return false;
        }
        Nacinplacanja other = (Nacinplacanja) object;
        if ((this.nacId == null && other.nacId != null) || (this.nacId != null && !this.nacId.equals(other.nacId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Nacinplacanja[ nacId=" + nacId + " ]";
    }

    public boolean getNazNaziv() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
