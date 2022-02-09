/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;


import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Petrik
 */
@Entity
@Table(name = "potkategorija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Potkategorija.findAll", query = "SELECT p FROM Potkategorija p")})
public class Potkategorija implements Serializable {

    @Size(max = 255)
    @Column(name = "pot_opis")
    private String potOpis;
    @OneToMany(mappedBy = "potId")
    private Collection<Slika> slikaCollection;

    private static final long serialVersionUID = 1L;
    @OneToOne(mappedBy = "potId")
    private Slika slika;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pot_id")
    private Integer potId;
    @Size(max = 50)
    @Column(name = "pot_naziv")
    private String potNaziv;
    @OneToMany(mappedBy = "potId")
    private Set<Artikal> artikalSet;
    @JoinColumn(name = "ktg_id", referencedColumnName = "kat_id")
    @ManyToOne
    private Kategorija ktgId;

    public Potkategorija() {
    }

    public Potkategorija(Integer potId) {
        this.potId = potId;
    }

    public Integer getPotId() {
        return potId;
    }

    public void setPotId(Integer potId) {
        this.potId = potId;
    }

    public String getPotNaziv() {
        return potNaziv;
    }

    public void setPotNaziv(String potNaziv) {
        this.potNaziv = potNaziv;
    }

    @XmlTransient
    public Set<Artikal> getArtikalSet() {
        return artikalSet;
    }

    public void setArtikalSet(Set<Artikal> artikalSet) {
        this.artikalSet = artikalSet;
    }

    public Kategorija getKtgId() {
        return ktgId;
    }

    public void setKtgId(Kategorija ktgId) {
        this.ktgId = ktgId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (potId != null ? potId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Potkategorija)) {
            return false;
        }
        Potkategorija other = (Potkategorija) object;
        if ((this.potId == null && other.potId != null) || (this.potId != null && !this.potId.equals(other.potId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Potkategorija[ potId=" + potId + " ]";
    }

    public String getPotOpis() {
        return potOpis;
    }

    public void setPotOpis(String potOpis) {
        this.potOpis = potOpis;
    }
    /*
    @XmlTransient
    public Collection<Slika> getSlikaCollection() {
        return slikaCollection;
    }

    public void setSlikaCollection(Collection<Slika> slikaCollection) {
        this.slikaCollection = slikaCollection;
    }
    */
    public Slika getSlika(){
        return slika;
    }
    public void setSlika(Slika slika){
        this.slika = slika;
    }
}
