/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging.model.query;

import com.samsoft.issuelogging.model.query.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author SAM
 */
public class DQuery {
    EntityManagerFactory emf;
    EntityManager em;
    public DQuery() {
        emf = Persistence.createEntityManagerFactory("IssueLoggingPU");
        em = emf.createEntityManager();
    }
    public boolean LoginCheck(String userName,String password) {
        try {
            User u = em.createNamedQuery("User.doLogin",User.class).setParameter("userName", userName).setParameter("password", password).getSingleResult();
            if (u != null) {
                return true;
            }
                    
        } catch(Exception ex) {
            ex.printStackTrace();
            
        }
        return false;
    }
    public boolean updatePassword(String userName,String oldPassword,String newPassword) {
      if (!LoginCheck(userName,oldPassword)) {
          return false;
      }
      User u = em.createNamedQuery("User.findByUserId",User.class).setParameter("userName", userName).getSingleResult();
      u.setPassword(newPassword);
      em.getTransaction().begin();
      em.persist(u);
      em.getTransaction().commit();
      return true;   
    }
}
