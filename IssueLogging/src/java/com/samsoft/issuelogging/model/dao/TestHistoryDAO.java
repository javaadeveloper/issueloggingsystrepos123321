/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging.model.dao;

/**
 *
 * @author SAM
 */

import com.samsoft.issuelogging.base.IBaseDAO;
import com.samsoft.issuelogging.model.query.entity.Testhistory;
import com.samsoft.issuelogging.model.query.entity.User;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestHistoryDAO implements IBaseDAO{
    EntityManagerFactory emf;
    EntityManager em;
    public TestHistoryDAO() {
        emf = Persistence.createEntityManagerFactory("IssueLoggingPU");
        em = emf.createEntityManager();
    }
   
   public List findAll(Map paramMap) {
      return em.createNamedQuery("Testhistory.findAll",Testhistory.class).getResultList();

   }
   public void save(Testhistory ts) {
     em.getTransaction().begin();
     em.merge(ts);
     em.getTransaction().commit();
   }
}
