/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging.model.dao;

import com.samsoft.issuelogging.model.query.entity.Issue;
import com.samsoft.issuelogging.model.query.entity.Screenshot;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author SAM
 */
public class ScreenshotDAO {
    
    EntityManagerFactory emf;
    EntityManager em;

    public ScreenshotDAO() {
        emf = Persistence.createEntityManagerFactory("IssueLoggingPU");
        em = emf.createEntityManager();
    }
    
    public List findAll(String type,Integer issueDetailId) {
      return em.createNamedQuery("Screenshot.findByTypeAndIssueId",Screenshot.class).setParameter("issueId", issueDetailId).setParameter("type", type).getResultList();
    
    }
    
   
    
    public void remove(Screenshot sc) {
     em.getTransaction().begin();
     em.remove(sc);
     em.getTransaction().commit();
        
    }
    
    public void save(Screenshot sc ) {
     em.getTransaction().begin();
     em.merge(sc);
     em.getTransaction().commit();
    }
    
}
