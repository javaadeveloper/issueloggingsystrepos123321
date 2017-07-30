/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging.model.service;

import com.samsoft.issuelogging.model.dao.IssueDAO;
import com.samsoft.issuelogging.model.dao.ScreenshotDAO;
import com.samsoft.issuelogging.model.query.entity.Issue;
import com.samsoft.issuelogging.model.query.entity.Screenshot;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SAM
 */
public class ScreenshotService  implements Serializable{
    public List findAll(String type,Integer issueDetailId) {
        ScreenshotDAO screenshotDAO = new ScreenshotDAO();
        return screenshotDAO.findAll(type,issueDetailId);
    }
    
    public void save(Screenshot screenshot) {
        ScreenshotDAO  scrDAO = new ScreenshotDAO();
        scrDAO.save(screenshot);
    }
    
    public void remove(Screenshot screenshot) {
        ScreenshotDAO  scrDAO = new ScreenshotDAO();
        scrDAO.remove(screenshot);
    }

}
