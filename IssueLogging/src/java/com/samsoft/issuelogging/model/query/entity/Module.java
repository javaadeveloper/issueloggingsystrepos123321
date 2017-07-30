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
@Table(name = "module")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Module.findAll", query = "SELECT m FROM Module m"),
    @NamedQuery(name = "Module.findByModuleName", query = "SELECT m FROM Module m WHERE m.moduleName = :moduleName"),
    @NamedQuery(name = "Module.findByModuleType", query = "SELECT m FROM Module m WHERE m.moduleType = :moduleType"),
    @NamedQuery(name = "Module.findBySubsystem", query = "SELECT m FROM Module m WHERE m.subsystem = :subsystem"),
    @NamedQuery(name = "Module.findBySystemtype", query = "SELECT m FROM Module m WHERE m.systemtype = :systemtype")})
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MODULE_NAME")
    private String moduleName;
    @Basic(optional = false)
    @Column(name = "MODULE_TYPE")
    private String moduleType;
    @Column(name = "SUBSYSTEM")
    private String subsystem;
    @Basic(optional = false)
    @Column(name = "SYSTEMTYPE")
    private String systemtype;

    public Module() {
    }

    public Module(String moduleName) {
        this.moduleName = moduleName;
    }

    public Module(String moduleName, String moduleType, String systemtype) {
        this.moduleName = moduleName;
        this.moduleType = moduleType;
        this.systemtype = systemtype;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getSubsystem() {
        return subsystem;
    }

    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }

    public String getSystemtype() {
        return systemtype;
    }

    public void setSystemtype(String systemtype) {
        this.systemtype = systemtype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (moduleName != null ? moduleName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Module)) {
            return false;
        }
        Module other = (Module) object;
        if ((this.moduleName == null && other.moduleName != null) || (this.moduleName != null && !this.moduleName.equals(other.moduleName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.samsoft.issuelogging.model.query.entity.Module[ moduleName=" + moduleName + " ]";
    }
    
}
