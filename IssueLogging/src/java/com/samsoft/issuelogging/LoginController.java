/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging;

import com.samsoft.issuelogging.model.query.DQuery;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SAM
 */
@ManagedBean(name="login")
@SessionScoped
public class LoginController {
    
    private String systemUserName;

    public String getSystemUserName() {
        return systemUserName;
    }
    private String userName;
    private String password;
    
    public String doLogout() {
       JSFUtils.saveOnSession("user_id", null);
       FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
       FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "login");
       return null;
    }
    public String loginControl() {
        DQuery dq = new DQuery();
        Boolean isUserOK = dq.LoginCheck(userName, password);
        if (isUserOK) {
            systemUserName = userName;
            JSFUtils.saveOnSession("user_id", userName);
            return "testHist.xhtml?faces-redirect=true";
        }
        RequestContext.getCurrentInstance().update("growl1");
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Username or Password is invalid"));
        return "";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
