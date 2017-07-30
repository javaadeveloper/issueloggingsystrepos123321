/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author SAM
 */
@ManagedBean(name="actionManagerBean")
public class ActionManagerBean {
    MenuModel menuModel;
    public void navigate(ActionEvent av) {
       UIComponent uic = av.getComponent();
       UIMenuItem uim = (UIMenuItem)uic;
       JSFUtils.saveOnRequest("DEEP_LINK_MENU", "0");
       if (uim.getValue().equals("Change Password")) {
           FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "changePass");
       }
       if (uim.getValue().equals("Log Out")) {
           LoginController login = (LoginController)JSFUtils.resolveExpression("#{login}");
       }
       if (uim.getValue().equals("Test History")) {
           FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "testHist");
       }
       if (uim.getValue().equals("Issues")) {
           FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "issues");
       }
       
       if (uim.getValue().equals("Module Types")) {
           FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "moduleType");
       }

       if (uim.getValue().equals("Modules")) {
           FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "modules");
       }
       
       
       System.out.println(uic.getClass().getName());
    }
    
    public void updateBreadCrumb() {
        
    }
    
}
