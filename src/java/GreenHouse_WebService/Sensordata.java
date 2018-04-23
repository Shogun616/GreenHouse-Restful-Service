/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GreenHouse_WebService;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Philip
 */
@Entity
@Table(name = "sensordata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sensordata.findAll", query = "SELECT s FROM Sensordata s")
    , @NamedQuery(name = "Sensordata.findById", query = "SELECT s FROM Sensordata s WHERE s.id = :id")
    , @NamedQuery(name = "Sensordata.findByTemperatur", query = "SELECT s FROM Sensordata s WHERE s.temperatur = :temperatur")
    , @NamedQuery(name = "Sensordata.findByBelysning", query = "SELECT s FROM Sensordata s WHERE s.belysning = :belysning")
    , @NamedQuery(name = "Sensordata.findByEl", query = "SELECT s FROM Sensordata s WHERE s.el = :el")
    , @NamedQuery(name = "Sensordata.findByLuftfuktighet", query = "SELECT s FROM Sensordata s WHERE s.luftfuktighet = :luftfuktighet")
    , @NamedQuery(name = "Sensordata.findByTid", query = "SELECT s FROM Sensordata s WHERE s.tid = :tid")})
public class Sensordata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Temperatur")
    private int temperatur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Belysning")
    private int belysning;
    @Basic(optional = false)
    @NotNull
    @Column(name = "El")
    private int el;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Luftfuktighet")
    private int luftfuktighet;
    @Column(name = "Tid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tid;
    @JoinColumn(name = "SektorId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sektorer sektorId;

    public Sensordata() {
    }

    public Sensordata(Integer id) {
        this.id = id;
    }

    public Sensordata(Integer id, int temperatur, int belysning, int el, int luftfuktighet) {
        this.id = id;
        this.temperatur = temperatur;
        this.belysning = belysning;
        this.el = el;
        this.luftfuktighet = luftfuktighet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTemperatur() {
        return temperatur;
    }

    public void setTemperatur(int temperatur) {
        this.temperatur = temperatur;
    }

    public int getBelysning() {
        return belysning;
    }

    public void setBelysning(int belysning) {
        this.belysning = belysning;
    }

    public int getEl() {
        return el;
    }

    public void setEl(int el) {
        this.el = el;
    }

    public int getLuftfuktighet() {
        return luftfuktighet;
    }

    public void setLuftfuktighet(int luftfuktighet) {
        this.luftfuktighet = luftfuktighet;
    }

    public Date getTid() {
        return tid;
    }

    public void setTid(Date tid) {
        this.tid = tid;
    }

    public Sektorer getSektorId() {
        return sektorId;
    }

    public void setSektorId(Sektorer sektorId) {
        this.sektorId = sektorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sensordata)) {
            return false;
        }
        Sensordata other = (Sensordata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "greenhouse.Sensordata[ id=" + id + " ]";
    }
    
}
