/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging.model.query.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SAM
 */
@Entity
@Table(name = "screenshots")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Screenshot.findAll", query = "SELECT s FROM Screenshot s"),
    @NamedQuery(name = "Screenshot.findById", query = "SELECT s FROM Screenshot s WHERE s.id = :id"),
    @NamedQuery(name = "Screenshot.findByType", query = "SELECT s FROM Screenshot s WHERE s.type = :type"),
    @NamedQuery(name = "Screenshot.findByFileName", query = "SELECT s FROM Screenshot s WHERE s.fileName = :fileName"),
    @NamedQuery(name = "Screenshot.findByLogDate", query = "SELECT s FROM Screenshot s WHERE s.logDate = :logDate"),
    @NamedQuery(name = "Screenshot.findByIssueId", query = "SELECT s FROM Screenshot s WHERE s.issueId = :issueId"),
    @NamedQuery(name = "Screenshot.findByIssueDetailId", query = "SELECT s FROM Screenshot s WHERE s.issueDetailId = :issueDetailId"),
    @NamedQuery(name = "Screenshot.findByTypeAndIssueId", query = "SELECT s FROM Screenshot s WHERE s.type = :type and s.issueId = :issueId")
    
})
public class Screenshot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @Column(name = "FILE_NAME")
    private String fileName;
    @Basic(optional = false)
    @Column(name = "LOG_DATE")
    @Temporal(TemporalType.DATE)
    private Date logDate;
    @Column(name = "ISSUE_ID", insertable=false, updatable=false)
    private Integer issueId;
    @Column(name = "ISSUE_DETAIL_ID")
    private Integer issueDetailId;

    public Screenshot() {
    }

    public Screenshot(Integer id) {
        this.id = id;
    }

    public Screenshot(Integer id, String type, String fileName, Date logDate) {
        this.id = id;
        this.type = type;
        this.fileName = fileName;
        this.logDate = logDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public Integer getIssueDetailId() {
        return issueDetailId;
    }

    public void setIssueDetailId(Integer issueDetailId) {
        this.issueDetailId = issueDetailId;
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
        if (!(object instanceof Screenshot)) {
            return false;
        }
        Screenshot other = (Screenshot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.samsoft.issuelogging.model.query.entity.Screenshots[ id=" + id + " ]";
    }

    @ManyToOne
    @JoinColumn(name = "ISSUE_ID",referencedColumnName = "ISSUE_ID")
    private Issue issue;

    public Issue getIssue() {
        return issue;
    }
    
    public void setIssue(Issue value) {
        this.issue = value;
    }
    
}
