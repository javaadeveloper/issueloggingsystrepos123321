/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging;

import com.samsoft.issuelogging.model.query.DQuery;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
//
/**
 *
 * @author SAM
 */
@ManagedBean(name="changePasswordBean")
public class ChangePasswordBean {
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
    

    public String getOldPassword() {
        return oldPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    public String home() {
        return "testHist.xhtml?faces-redirect=true";
        
    }
    public void doChangePassword(ActionEvent av) {
        if (!confirmNewPassword.equals(newPassword)) {
            RequestContext.getCurrentInstance().update("growl1");
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"","New password and confirm password does not match"));
        } else {
        DQuery dq = new DQuery();
        LoginController loginControler = (LoginController)JSFUtils.resolveExpression("#{login}");
        Boolean isUserOK = dq.updatePassword(loginControler.getSystemUserName(), oldPassword,newPassword);
        if (isUserOK) {
            RequestContext.getCurrentInstance().update("growl1");
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Password has been changed successfully."));

        } else {
            RequestContext.getCurrentInstance().update("growl1");
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Error changing password"));
            
        }

            
        }
        
    }
    
    
}
