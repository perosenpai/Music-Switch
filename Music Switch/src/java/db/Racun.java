/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Petrik
 */
@Entity
@Table(name = "racun")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Racun.findAll", query = "SELECT r FROM Racun r")})
public class Racun implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rac_id")
    private Integer racId;
    @Column(name = "rac_datum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date racDatum;
    @OneToMany(mappedBy = "racId", cascade = CascadeType.ALL)
    private Set<Stavka> stavkaSet;
    @JoinColumn(name = "kup_id", referencedColumnName = "kup_id")
    @ManyToOne
    private Kupac kupId;
    @JoinColumn(name = "nac_id", referencedColumnName = "nac_id")
    @ManyToOne
    private Nacinplacanja nacId;

    public Racun() {
    }

    public Racun(Integer racId) {
        this.racId = racId;
    }

    

    public Integer getRacId() {
        return racId;
    }

    public void setRacId(Integer racId) {
        this.racId = racId;
    }

    

    

    public Date getRacDatum() {
        return racDatum;
    }

    public void setRacDatum(Date racDatum) {
        this.racDatum = racDatum;
    }

    

    

    @XmlTransient
    public Set<Stavka> getStavkaSet() {
        return stavkaSet;
    }

    public void setStavkaSet(Set<Stavka> stavkaSet) {
        this.stavkaSet = stavkaSet;
    }

    

    

    public Kupac getKupId() {
        return kupId;
    }

    public void setKupId(Kupac kupId) {
        this.kupId = kupId;
    }

    public Nacinplacanja getNacId() {
        return nacId;
    }

    public void setNacId(Nacinplacanja nacId) {
        this.nacId = nacId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (racId != null ? racId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Racun)) {
            return false;
        }
        Racun other = (Racun) object;
        if ((this.racId == null && other.racId != null) || (this.racId != null && !this.racId.equals(other.racId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Racun[ racId=" + racId + " ]";
    }

//    public void setKupId(String kupac_email) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
}
