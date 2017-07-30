/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging.model.query.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SAM
 */
@Entity
@Table(name = "issuehistory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Issuehistory.findAll", query = "SELECT i FROM Issuehistory i"),
    @NamedQuery(name = "Issuehistory.findById", query = "SELECT i FROM Issuehistory i WHERE i.id = :id"),
    @NamedQuery(name = "Issuehistory.findByIssueId", query = "SELECT i FROM Issuehistory i WHERE i.issueId = :issueId"),
    @NamedQuery(name = "Issuehistory.findByUserId", query = "SELECT i FROM Issuehistory i WHERE i.userId = :userId"),
    @NamedQuery(name = "Issuehistory.findByDescription", query = "SELECT i FROM Issuehistory i WHERE i.description = :description"),
    @NamedQuery(name = "Issuehistory.findByOldstatus", query = "SELECT i FROM Issuehistory i WHERE i.oldstatus = :oldstatus"),
    @NamedQuery(name = "Issuehistory.findByNewstatus", query = "SELECT i FROM Issuehistory i WHERE i.newstatus = :newstatus")})
public class Issuehistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "issue_id")
    private int issueId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private String userId;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "oldstatus")
    private String oldstatus;
    @Basic(optional = false)
    @Column(name = "newstatus")
    private String newstatus;

    @Temporal(TemporalType.DATE)
    @Column(name="hist_date")
    private Date histDate;

    public Date getHistDate() {
        return histDate;
    }

    public void setHistDate(Date histDate) {
        this.histDate = histDate;
    }
 
    public Issue getIssue() {
        return issue;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ISSUE_ID",referencedColumnName = "ISSUE_ID",insertable = false,updatable = false)
    private Issue issue;
    
    public Issuehistory() {
    }

    public Issuehistory(Long id) {
        this.id = id;
    }

    public Issuehistory(Long id, int issueId, String userId, String description, String oldstatus, String newstatus) {
        this.id = id;
        this.issueId = issueId;
        this.userId = userId;
        this.description = description;
        this.oldstatus = oldstatus;
        this.newstatus = newstatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOldstatus() {
        return oldstatus;
    }

    public void setOldstatus(String oldstatus) {
        this.oldstatus = oldstatus;
    }

    public String getNewstatus() {
        return newstatus;
    }

    public void setNewstatus(String newstatus) {
        this.newstatus = newstatus;
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
        if (!(object instanceof Issuehistory)) {
            return false;
        }
        Issuehistory other = (Issuehistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.samsoft.issuelogging.model.query.entity.Issuehistory[ id=" + id + " ]";
    }
    
}
