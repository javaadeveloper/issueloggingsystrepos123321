/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging;

import com.samsoft.issuelogging.model.query.entity.Testhistory;
import com.samsoft.issuelogging.model.service.TestHistoryService;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.flow.FlowScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SAM
 */
@ManagedBean(name="testHistoryBean")
@ViewScoped
public class TestHistoryBean implements Serializable{
    

    public String getDialogMode() {
        return dialogMode;
    }

    public Testhistory getEditTest() {
        return editTest;
    }

    public void setEditTest(Testhistory editTest) {
        this.editTest = editTest;
    }

    public void setDialogMode(String dialogMode) {
        this.dialogMode = dialogMode;
    }
    private String dialogMode;
    private Testhistory selectedTest;
    private List currentList;
    private Testhistory editTest;
    TestHistoryService testHistoryService = new TestHistoryService();

    public Testhistory getSelectedTest() {
        return selectedTest;
    }

    public void setSelectedTest(Testhistory selectedTest) {
       this.selectedTest = selectedTest;
    }
    public List getTestHistoryList() {
       if (currentList == null) {
          currentList =  testHistoryService.findAll();
       } 
       return currentList; 
    }
    
    public void editIssues(ActionEvent av) {
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "issuesOfTest");
    }
    public void submitEdit(ActionEvent av) {
        
        if (getDialogMode().equals("C")) {
//            selectedTest.setId(Integer.valueOf("1000"));
            editTest.setId(maximumIdIntheList() + 1);
            Object userName = JSFUtils.resolveExpression("#{login.systemUserName}");
            if (userName != null) 
              editTest.setUserId(userName.toString().toUpperCase());
            currentList.add(editTest);
            RequestContext.getCurrentInstance().update(":form:dataTable");

        }
        if (getDialogMode().equals("E")) {
            for (int i=0;i < currentList.size(); i++ ) {
                Object iterate = currentList.get(i);
                if (iterate instanceof Testhistory && editTest != null) {
                    if ( ((Testhistory)iterate).equals(editTest) ) {
                        ((Testhistory)iterate).setModuleName(editTest.getModuleName());
                        ((Testhistory)iterate).setVersion(editTest.getVersion());
                        currentList.remove(currentList.get(i));
                        currentList.add((Testhistory)iterate);
                        RequestContext.getCurrentInstance().update(":form:dataTable");
                        return;        
                    }
                }
            }
            
        }
        
        setDialogMode("N");
    }
    
   
    
    public void createActionListener(ActionEvent av) {
        setSelectedTest(new Testhistory());
        setEditTest(getSelectedTest());
        setDialogMode("C");

    }
    
    public void editActionListener(ActionEvent av) {
        setDialogMode("E");
        editTest = new Testhistory();
        editTest.setId(selectedTest.getId());
        editTest.setModuleName(selectedTest.getModuleName());
        editTest.setVersion(selectedTest.getVersion());
        editTest.setUserId(selectedTest.getUserId());
        setEditTest(selectedTest);
        
    }
    
    public Integer maximumIdIntheList() {
        Integer currentMax = 1;
        for (Object o: currentList) {
           if  (((Testhistory)o).getId() > currentMax )
                currentMax =((Testhistory)o).getId();     
        }
        return currentMax;        
    }
    
    public void commitChanges (ActionEvent av) {
       for (Object o : currentList) {
         testHistoryService.save((Testhistory)o);
       }
        RequestContext.getCurrentInstance().update("growl1");
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"","Transaction commited sucessfully"));
       
    }
    
}
