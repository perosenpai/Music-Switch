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
@Table(name = "kupac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kupac.findAll", query = "SELECT k FROM Kupac k")})
public class Kupac implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kup_id")
    private Integer kupId;
    @Size(max = 50)
    @Column(name = "kup_ime")
    private String kupIme;
    @Size(max = 50)
    @Column(name = "kup_prezime")
    private String kupPrezime;
    @Size(max = 50)
    @Column(name = "kup_email")
    private String kupEmail;
    @Size(max = 50)
    @Column(name = "kup_sifra")
    private String kupSifra;
    @Size(max = 50)
    @Column(name = "kup_telefon")
    private String kupTelefon;
    @Size(max = 50)
    @Column(name = "kup_adresa")
    private String kupAdresa;
    @Column(name = "kup_postBroj")
    private Integer kuppostBroj;
    @JoinColumn(name = "gra_id", referencedColumnName = "gra_id")
    @ManyToOne
    private Grad graId;
    @OneToMany(mappedBy = "kupId")
    private Set<Racun> racunSet;

    public Kupac() {
    }

    public Kupac(Integer kupId) {
        this.kupId = kupId;
    }

    public Kupac(String email, String pass, String ime, String prezime, String kontakt) {
        
        this.kupEmail = email;
        this.kupSifra = pass;
        this.kupIme = ime;
        this.kupPrezime = prezime;
        this.kupTelefon = kontakt;
    }

    public Kupac(String ime, String prezime, String kontakt, String email, String pass, String ulica, Integer id_grad, String postanskiBroj) {
        this.kupIme = ime;
        this.kupPrezime = prezime;
        this.kupTelefon = kontakt;
        this.kupEmail = email;
        this.kupSifra = pass;
        this.kupAdresa = ulica;
        
        
        Grad new_grad = new Grad(id_grad);
        this.graId = new_grad;
        
        this.kuppostBroj = Integer.parseInt(postanskiBroj);
        
    }

    public Integer getKupId() {
        return kupId;
    }

    public void setKupId(Integer kupId) {
        this.kupId = kupId;
    }

    

    

    public String getKupIme() {
        return kupIme;
    }

    public void setKupIme(String kupIme) {
        this.kupIme = kupIme;
    }

    public String getKupPrezime() {
        return kupPrezime;
    }

    public void setKupPrezime(String kupPrezime) {
        this.kupPrezime = kupPrezime;
    }

    public String getKupEmail() {
        return kupEmail;
    }

    public void setKupEmail(String kupEmail) {
        this.kupEmail = kupEmail;
    }

    public String getKupSifra() {
        return kupSifra;
    }

    public void setKupSifra(String kupSifra) {
        this.kupSifra = kupSifra;
    }

    public String getKupTelefon() {
        return kupTelefon;
    }

    public void setKupTelefon(String kupTelefon) {
        this.kupTelefon = kupTelefon;
    }

    public String getKupAdresa() {
        return kupAdresa;
    }

    public void setKupAdresa(String kupAdresa) {
        this.kupAdresa = kupAdresa;
    }

    public Integer getKuppostBroj() {
        return kuppostBroj;
    }

    public void setKuppostBroj(Integer kuppostBroj) {
        this.kuppostBroj = kuppostBroj;
    }

    public Grad getGraId() {
        return graId;
    }

    public void setGraId(Grad graId) {
        this.graId = graId;
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
        hash += (kupId != null ? kupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kupac)) {
            return false;
        }
        Kupac other = (Kupac) object;
        if ((this.kupId == null && other.kupId != null) || (this.kupId != null && !this.kupId.equals(other.kupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.Kupac[ kupId=" + kupId + " ]";
    }
    
}
