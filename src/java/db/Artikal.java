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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "artikal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artikal.findAll", query = "SELECT a FROM Artikal a")})
public class Artikal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "art_id")
    private Integer artId;
    @Size(max = 50)
    @Column(name = "art_naziv")
    private String artNaziv;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "art_opis")
    private String artOpis;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "art_cena")
    private Double artCena;
    @JoinTable(name = "kar_art", joinColumns = {
        @JoinColumn(name = "art_id", referencedColumnName = "art_id")}, inverseJoinColumns = {
        @JoinColumn(name = "kar_id", referencedColumnName = "kar_id")})
    @ManyToMany
    private Set<Karakteristike> karakteristikeSet;
    @OneToOne(mappedBy = "artId")
    private Slika slika;
    @OneToMany(mappedBy = "artId")
    private Set<Stavka> stavkaSet;
    @JoinColumn(name = "pot_id", referencedColumnName = "pot_id")
    @ManyToOne
    private Potkategorija potId;

    public Artikal() {
    }

    public Artikal(Integer artId) {
        this.artId = artId;
    }

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(Integer artId) {
        this.artId = artId;
    }

    public String getArtNaziv() {
        return artNaziv;
    }

    public void setArtNaziv(String artNaziv) {
        this.artNaziv = artNaziv;
    }

    public String getArtOpis() {
        return artOpis;
    }

    public void setArtOpis(String artOpis) {
        this.artOpis = artOpis;
    }

    public Double getArtCena() {
        return artCena;
    }

    public void setArtCena(Double artCena) {
        this.artCena = artCena;
    }

    @XmlTransient
    public Set<Karakteristike> getKarakteristikeSet() {
        return karakteristikeSet;
    }

    public void setKarakteristikeSet(Set<Karakteristike> karakteristikeSet) {
        this.karakteristikeSet = karakteristikeSet;
    }
    /*
    @XmlTransient
    public Set<Slika> getSlikaSet() {
        return slikaset;
    }
    
    public void setSlikaSet(Set<Slika> slikaSet) {
        this.slikaSet = slikaSet;
    }
*/
    
    public Slika getSlika(){
        return slika;
    }
    //set
    
    public void setSlika(Slika slika){
        this.slika = slika;
    }
    
    
    @XmlTransient
    public Set<Stavka> getStavkaSet() {
        return stavkaSet;
    }

    public void setStavkaSet(Set<Stavka> stavkaSet) {
        this.stavkaSet = stavkaSet;
    }

    public Potkategorija getPotId() {
        return potId;
    }

    public void setPotId(Potkategorija potId) {
        this.potId = potId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artId != null ? artId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Artikal)) {
            return false;
        }
        Artikal other = (Artikal) object;
        if ((this.artId == null && other.artId != null) || (this.artId != null && !this.artId.equals(other.artId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Artikal[ artId=" + artId + " ]";
    }
    
}
