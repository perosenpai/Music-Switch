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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Petrik
 */
@Entity
@Table(name = "karakteristike")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Karakteristike.findAll", query = "SELECT k FROM Karakteristike k")
    , @NamedQuery(name = "Karakteristike.findByKarId", query = "SELECT k FROM Karakteristike k WHERE k.karId = :karId")
    , @NamedQuery(name = "Karakteristike.findByKarNaziv", query = "SELECT k FROM Karakteristike k WHERE k.karNaziv = :karNaziv")
    , @NamedQuery(name = "Karakteristike.findByKarVrednost", query = "SELECT k FROM Karakteristike k WHERE k.karVrednost = :karVrednost")})
public class Karakteristike implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kar_id")
    private Integer karId;
    @Size(max = 50)
    @Column(name = "kar_naziv")
    private String karNaziv;
    @Size(max = 50)
    @Column(name = "kar_vrednost")
    private String karVrednost;

    public Karakteristike() {
    }

    public Karakteristike(Integer karId) {
        this.karId = karId;
    }

    public Integer getKarId() {
        return karId;
    }

    public void setKarId(Integer karId) {
        this.karId = karId;
    }

    public String getKarNaziv() {
        return karNaziv;
    }

    public void setKarNaziv(String karNaziv) {
        this.karNaziv = karNaziv;
    }

    public String getKarVrednost() {
        return karVrednost;
    }

    public void setKarVrednost(String karVrednost) {
        this.karVrednost = karVrednost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (karId != null ? karId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Karakteristike)) {
            return false;
        }
        Karakteristike other = (Karakteristike) object;
        if ((this.karId == null && other.karId != null) || (this.karId != null && !this.karId.equals(other.karId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Karakteristike[ karId=" + karId + " ]";
    }

    public Set<Artikal> getArtikalSet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
