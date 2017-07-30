/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging.model.dao;

import com.samsoft.issuelogging.base.IBaseDAO;
import com.samsoft.issuelogging.model.query.entity.Issue;
import com.samsoft.issuelogging.model.query.entity.Screenshot;
import com.samsoft.issuelogging.model.query.entity.Testhistory;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author SAM
 */
public class IssueDAO implements IBaseDAO{

    EntityManagerFactory emf;
    EntityManager em;
    public IssueDAO() {
        emf = Persistence.createEntityManagerFactory("IssueLoggingPU");
        em = emf.createEntityManager();
    }
   
   public List findAll(Map paramMap) {
      if (paramMap != null) {
         Object testId = paramMap.get("selectedTestId");
         if (testId != null)
             return em.createNamedQuery("Issue.findByTestId",Issue.class).setParameter("testId",  Integer.valueOf(testId.toString())  ).getResultList();
      }
      return em.createNamedQuery("Issue.findAll",Issue.class).getResultList();

   }

   public void save(Issue issue) {
     em.getTransaction().begin();
     issue = em.merge(issue);
     for (Screenshot s : issue.getScreenshots()) {
         em.persist(s);
         em.merge(s);
        
     }
     em.getTransaction().commit();
     
   }

}
