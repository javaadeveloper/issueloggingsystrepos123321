/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging.model.query.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SAM
 */
@Entity
@Table(name = "testhistory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Testhistory.findAll", query = "SELECT t FROM Testhistory t"),
    @NamedQuery(name = "Testhistory.findById", query = "SELECT t FROM Testhistory t WHERE t.id = :id"),
    @NamedQuery(name = "Testhistory.findByModuleName", query = "SELECT t FROM Testhistory t WHERE t.moduleName = :moduleName"),
    @NamedQuery(name = "Testhistory.findByVersion", query = "SELECT t FROM Testhistory t WHERE t.version = :version"),
    @NamedQuery(name = "Testhistory.findByUserId", query = "SELECT t FROM Testhistory t WHERE t.userId = :userId")})

public class Testhistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MODULE_NAME")
    private String moduleName;
    @Basic(optional = false)
    @Column(name = "VERSION")
    private String version;
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private String userId;

    public Testhistory() {
    }

    public Testhistory(Integer id) {
        this.id = id;
    }

    public Testhistory(Integer id, String version, String userId) {
        this.id = id;
        this.version = version;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        if (!(object instanceof Testhistory)) {
            return false;
        }
        Testhistory other = (Testhistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.samsoft.issuelogging.model.query.entity.Testhistory[ id=" + id + " ]";
    }
    
}
