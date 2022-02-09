/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Petrik
 */
@Entity
@Table(name = "slika")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Slika.findAll", query = "SELECT s FROM Slika s")})
public class Slika implements Serializable {

    @Lob
    @Column(name = "slk_slika")
    private byte[] slkSlika;
    @JoinColumn(name = "pot_id", referencedColumnName = "pot_id")
    @ManyToOne
    private Potkategorija potId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "slk_id")
    private Integer slkId;
    @JoinColumn(name = "art_id", referencedColumnName = "art_id")
    @ManyToOne
    private Artikal artId;

    public Slika() {
    }

    public Slika(Integer slkId) {
        this.slkId = slkId;
    }

    public Integer getSlkId() {
        return slkId;
    }

    public void setSlkId(Integer slkId) {
        this.slkId = slkId;
    }

    public byte[] getSlkSlika() {
        return slkSlika;
    }

    public void setSlkSlika(byte[] slkSlika) {
        this.slkSlika = slkSlika;
    }

    public Artikal getArtId() {
        return artId;
    }

    public void setArtId(Artikal artId) {
        this.artId = artId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (slkId != null ? slkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Slika)) {
            return false;
        }
        Slika other = (Slika) object;
        if ((this.slkId == null && other.slkId != null) || (this.slkId != null && !this.slkId.equals(other.slkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Slika[ slkId=" + slkId + " ]";
    }

   

    public Potkategorija getPotId() {
        return potId;
    }

    public void setPotId(Potkategorija potId) {
        this.potId = potId;
    }
    
}
