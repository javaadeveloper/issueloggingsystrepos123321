/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging.model.dao;

import com.samsoft.issuelogging.base.IBaseDAO;
import com.samsoft.issuelogging.model.query.entity.Issue;
import com.samsoft.issuelogging.model.query.entity.Module;
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
public class ModuleDAO implements IBaseDAO{

    EntityManagerFactory emf;
    EntityManager em;
    public ModuleDAO() {
        emf = Persistence.createEntityManagerFactory("IssueLoggingPU");
        em = emf.createEntityManager();
    }
   
   public List findAll(Map paramMap) {
      return em.createNamedQuery("Issue.findAll",Issue.class).getResultList();

   }

   public void save(Module module) {
     em.getTransaction().begin();
     em.persist(module);
     em.getTransaction().commit();
     
   }

}
