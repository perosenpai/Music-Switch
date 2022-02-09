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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "grad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grad.findAll", query = "SELECT g FROM Grad g")})
public class Grad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gra_id")
    private Integer graId;
    @Size(max = 50)
    @Column(name = "gra_naziv")
    private String graNaziv;
    @OneToMany(mappedBy = "graId")
    private Set<Kupac> kupacSet;

    @JoinColumn(name = "drz_id", referencedColumnName = "drz_id")
    @ManyToOne
    private Drzava drzId;

    public Grad() {
    }

    public Grad(Integer graId) {
        this.graId = graId;
    }

    public Integer getGraId() {
        return graId;
    }

    public void setGraId(Integer graId) {
        this.graId = graId;
    }

    public String getGraNaziv() {
        return graNaziv;
    }

    public void setGraNaziv(String graNaziv) {
        this.graNaziv = graNaziv;
    }

    @XmlTransient
    public Set<Kupac> getKupacSet() {
        return kupacSet;
    }

    public void setKupacSet(Set<Kupac> kupacSet) {
        this.kupacSet = kupacSet;
    }

 
    public Drzava getDrzId() {
        return drzId;
    }

    public void setDrzId(Drzava drzId) {
        this.drzId = drzId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (graId != null ? graId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grad)) {
            return false;
        }
        Grad other = (Grad) object;
        if ((this.graId == null && other.graId != null) || (this.graId != null && !this.graId.equals(other.graId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Grad[ graId=" + graId + " ]";
    }
    
}
