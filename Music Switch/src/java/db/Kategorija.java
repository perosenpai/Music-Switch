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
@Table(name = "kategorija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kategorija.findAll", query = "SELECT k FROM Kategorija k")})
public class Kategorija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kat_id")
    private Integer katId;
    @Size(max = 50)
    @Column(name = "kat_naziv")
    private String katNaziv;
    @OneToMany(mappedBy = "ktgId")
    private Set<Potkategorija> potkategorijaSet;

    public Kategorija() {
    }

    public Kategorija(Integer katId) {
        this.katId = katId;
    }

    public Integer getKatId() {
        return katId;
    }

    public void setKatId(Integer katId) {
        this.katId = katId;
    }

    public String getKatNaziv() {
        return katNaziv;
    }

    public void setKatNaziv(String katNaziv) {
        this.katNaziv = katNaziv;
    }

    @XmlTransient
    public Set<Potkategorija> getPotkategorijaSet() {
        return potkategorijaSet;
    }

    public void setPotkategorijaSet(Set<Potkategorija> potkategorijaSet) {
        this.potkategorijaSet = potkategorijaSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (katId != null ? katId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kategorija)) {
            return false;
        }
        Kategorija other = (Kategorija) object;
        if ((this.katId == null && other.katId != null) || (this.katId != null && !this.katId.equals(other.katId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Kategorija[ katId=" + katId + " ]";
    }
    
}
