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
@Table(name = "drzava")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Drzava.findAll", query = "SELECT d FROM Drzava d")})
public class Drzava implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "drz_id")
    private Integer drzId;
    @Size(max = 50)
    @Column(name = "drz_naziv")
    private String drzNaziv;
    @OneToMany(mappedBy = "drzId")
    private Set<Grad> gradSet;

    public Drzava() {
    }

    public Drzava(Integer drzId) {
        this.drzId = drzId;
    }

    public Integer getDrzId() {
        return drzId;
    }

    public void setDrzId(Integer drzId) {
        this.drzId = drzId;
    }

    public String getDrzNaziv() {
        return drzNaziv;
    }

    public void setDrzNaziv(String drzNaziv) {
        this.drzNaziv = drzNaziv;
    }

    @XmlTransient
    public Set<Grad> getGradSet() {
        return gradSet;
    }

    public void setGradSet(Set<Grad> gradSet) {
        this.gradSet = gradSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (drzId != null ? drzId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Drzava)) {
            return false;
        }
        Drzava other = (Drzava) object;
        if ((this.drzId == null && other.drzId != null) || (this.drzId != null && !this.drzId.equals(other.drzId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Drzava[ drzId=" + drzId + " ]";
    }
    
}
