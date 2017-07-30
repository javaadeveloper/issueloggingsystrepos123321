/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging.model.service;

import com.samsoft.issuelogging.model.dao.IssueDAO;
import com.samsoft.issuelogging.model.query.entity.Issue;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SAM
 */
public class IssueService implements Serializable{
    
    public List findAll(Map testId) {
        IssueDAO issueDAO = new IssueDAO();
        return issueDAO.findAll(testId);
    }
    
    public void save(Issue issue) {
        IssueDAO issueDAO = new IssueDAO();
        issueDAO.save(issue);
    }
    
}
