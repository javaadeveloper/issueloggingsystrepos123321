/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging.model.service;

import com.samsoft.issuelogging.model.dao.ModuleDAO;
import com.samsoft.issuelogging.model.dao.TestHistoryDAO;
import com.samsoft.issuelogging.model.query.entity.Module;
import com.samsoft.issuelogging.model.query.entity.Testhistory;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author SAM
 */
public class ModuleService  implements Serializable{
    
    public List findAll() {
        ModuleDAO moduleDAO = new ModuleDAO();
        return moduleDAO.findAll(null);
    }
    
    public void save(Module module) {
        ModuleDAO moduleDAO = new ModuleDAO();
        moduleDAO.save(module);
    }
    
}
