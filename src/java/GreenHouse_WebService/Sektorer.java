/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GreenHouse_WebService;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Philip
 */
@Entity
@Table(name = "sektorer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sektorer.findAll", query = "SELECT s FROM Sektorer s")
    , @NamedQuery(name = "Sektorer.findById", query = "SELECT s FROM Sektorer s WHERE s.id = :id")
    , @NamedQuery(name = "Sektorer.findBySektor", query = "SELECT s FROM Sektorer s WHERE s.sektor = :sektor")
    , @NamedQuery(name = "Sektorer.findByRad", query = "SELECT s FROM Sektorer s WHERE s.rad = :rad")})
public class Sektorer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Sektor")
    private String sektor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Rad")
    private float rad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sektorId")
    private Collection<Sensordata> sensordataCollection;

    public Sektorer() {
    }

    public Sektorer(Integer id) {
        this.id = id;
    }

    public Sektorer(Integer id, String sektor, float rad) {
        this.id = id;
        this.sektor = sektor;
        this.rad = rad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSektor() {
        return sektor;
    }

    public void setSektor(String sektor) {
        this.sektor = sektor;
    }

    public float getRad() {
        return rad;
    }

    public void setRad(float rad) {
        this.rad = rad;
    }

    @XmlTransient
    public Collection<Sensordata> getSensordataCollection() {
        return sensordataCollection;
    }

    public void setSensordataCollection(Collection<Sensordata> sensordataCollection) {
        this.sensordataCollection = sensordataCollection;
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
        if (!(object instanceof Sektorer)) {
            return false;
        }
        Sektorer other = (Sektorer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GreenHouse_WebService.Sektorer[ id=" + id + " ]";
    }
    
}
