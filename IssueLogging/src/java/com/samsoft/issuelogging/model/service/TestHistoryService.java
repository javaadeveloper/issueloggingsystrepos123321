/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging.model.service;

import com.samsoft.issuelogging.model.dao.TestHistoryDAO;
import com.samsoft.issuelogging.model.query.entity.Testhistory;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author SAM
 */
public class TestHistoryService  implements Serializable{
    
    public List findAll() {
        TestHistoryDAO testHistoryDAO = new TestHistoryDAO();
        return testHistoryDAO.findAll(null);
    }
    
    public void save(Testhistory ts) {
        TestHistoryDAO testHistoryDAO = new TestHistoryDAO();
        testHistoryDAO.save(ts);
    }
    
}
