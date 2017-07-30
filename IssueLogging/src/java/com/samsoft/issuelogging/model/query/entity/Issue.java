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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SAM
 */
@Entity
@Table(name = "issue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Issue.findAll", query = "SELECT i FROM Issue i"),
    @NamedQuery(name = "Issue.findByIssueId", query = "SELECT i FROM Issue i WHERE i.issueId = :issueId"),
    @NamedQuery(name = "Issue.findByLogDate", query = "SELECT i FROM Issue i WHERE i.logDate = :logDate"),
    @NamedQuery(name = "Issue.findByUserId", query = "SELECT i FROM Issue i WHERE i.userId = :userId"),
    @NamedQuery(name = "Issue.findByDescription", query = "SELECT i FROM Issue i WHERE i.description = :description"),
    @NamedQuery(name = "Issue.findByModuleName", query = "SELECT i FROM Issue i WHERE i.moduleName = :moduleName"),
    @NamedQuery(name = "Issue.findByTestId", query = "SELECT i FROM Issue i WHERE i.testId = :testId"),
    @NamedQuery(name = "Issue.findByStatus", query = "SELECT i FROM Issue i WHERE i.status = :status")})
public class Issue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ISSUE_ID")
    private Integer issueId;
    @Basic(optional = false)
    @Column(name = "LOG_DATE")
    @Temporal(TemporalType.DATE)
    private Date logDate;
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private String userId;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "MODULE_NAME")
    private String moduleName;
    @Column(name = "TEST_ID")
    private Integer testId;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    
    @Transient
    private Boolean newRecord;

    public Boolean getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(Boolean newRecord) {
        this.newRecord = newRecord;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "issue",cascade = CascadeType.MERGE)
    private List<Issuehistory> issuehistories;

    public List<Issuehistory> getIssuehistories() {
        return issuehistories;
    }
    public void setIssuehistories(List<Issuehistory> list) {
        this.issuehistories = list;
    }
    

    public Issue() {
    }

    public Issue(Integer issueId) {
        this.issueId = issueId;
    }

    public Issue(Integer issueId, Date logDate, String userId, String description, String moduleName, String status) {
        this.issueId = issueId;
        this.logDate = logDate;
        this.userId = userId;
        this.description = description;
        this.moduleName = moduleName;
        this.status = status;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (issueId != null ? issueId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Issue)) {
            return false;
        }
        Issue other = (Issue) object;
        if ((this.issueId == null && other.issueId != null) || (this.issueId != null && !this.issueId.equals(other.issueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.samsoft.issuelogging.model.query.entity.Issue[ issueId=" + issueId + " ]";
    }
    


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "issue",cascade = CascadeType.ALL)
    private List<Screenshot> screenshots;

    public List<Screenshot> getScreenshots() {
        return screenshots;
    }
    public void setScreenshots(List<Screenshot> list) {
        this.screenshots = list;
    }
    
    
}
