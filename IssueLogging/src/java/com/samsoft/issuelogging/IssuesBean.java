/*selectedIssuehistory
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging;

import com.samsoft.issuelogging.model.query.entity.Issue;
import com.samsoft.issuelogging.model.query.entity.Issuehistory;
import com.samsoft.issuelogging.model.query.entity.Screenshot;
import com.samsoft.issuelogging.model.service.IssueService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.rowtoggler.RowToggler;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

/**
 *
 * @author SAM
 */
@ManagedBean(name="issueManagementBean")
@ViewScoped
public class IssuesBean implements Serializable{

    public Integer getCurrentTestId() {
        return currentTestId;
    }

    public void setCurrentTestId(Integer currentTestId) {
        this.currentTestId = currentTestId;
    }
    private Boolean freezeCurrentIssue = false;
    private Issue selectedIssue;
    private List currentList;
    private List currentHistoryList;


    public Issuehistory getEditIssueHist() {
        return editIssueHist;
    }

    public void setEditIssueHist(Issuehistory editIssueHist) {
        this.editIssueHist = editIssueHist;
    }

    public String getDialogModeHist() {
        return dialogModeHist;
    }

    public void setDialogModeHist(String dialogModeHist) {
        this.dialogModeHist = dialogModeHist;
    }
    private Issue editIssue;
    private String dialogMode;
    private String dialogModeHist;
    private Integer currentTestId;
    private Integer currentIssueId;
    private String currentModuleName;

    public void setCurrentIssueId(Integer currentIssueId) {
        this.currentIssueId = currentIssueId;
    }
    
    private Issuehistory editIssueHist;

    
    
    public Issuehistory getSelectedIssuehistory() {
        return selectedIssuehistory;
    }

    public void setSelectedIssuehistory(Issuehistory selectedIssuehistory) {
        if (selectedIssuehistory != null) {
            currentIssueId = selectedIssuehistory.getIssueId();
            setSelectedIssue(selectedIssuehistory.getIssue());
            this.selectedIssuehistory = selectedIssuehistory;
        }
    }

    IssueService issueService = new IssueService();
    
    private Issuehistory selectedIssuehistory;

    public Issue getSelectedIssue() {
        return selectedIssue;
    }

    public void setSelectedIssue(Issue selectedIssue) { 
        if (!freezeCurrentIssue || selectedIssue != null ) {
             RowToggler toggle = (RowToggler) JSFUtils.findComponent("rowToggle");
             this.selectedIssue = selectedIssue;
             if (toggle != null) {
                String updateClientId = toggle.getClientId();
                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientId);
                 
             }

        }
           
        
    }
    
    public void onIssueSelect(SelectEvent se) {
       
    }

    public Issue getEditIssue() {
        return editIssue;
    }

    public void setEditIssue(Issue editIssue) {
        this.editIssue = editIssue;
    }

    public String getDialogMode() {
        return dialogMode;
    }

    public void setDialogMode(String dialogMode) {
        this.dialogMode = dialogMode;
    }

    public String getCurrentModuleName() {

        if (currentModuleName == null) {
           Map paramMap =  (Map)JSFUtils.resolveExpression("#{param}"); //(Map)FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("selectedTest");
            Object currentModuleNameObj = paramMap.get("selectedModuleName");
            if (currentModuleNameObj != null && ! currentModuleNameObj.equals("null")) {
                currentModuleName = currentModuleNameObj.toString();
            }
              
        }
        return currentModuleName;
        
    }
    public List getIssueList() {
       Map paramMap =  (Map)JSFUtils.resolveExpression("#{param}"); //(Map)FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("selectedTest");
       Object testId = paramMap.get("selectedTestId");
       if (testId != null && ! testId.equals("null")) {
           currentTestId = Integer.valueOf(testId.toString());
       }
       
       if (currentList == null) {
          currentList =  issueService.findAll(paramMap);
       } 
       
       return currentList;
    }


    
    public void createActionListener(ActionEvent av) {
        Issue createIssue  = new Issue();
        createIssue.setTestId(currentTestId);
        createIssue.setModuleName(currentModuleName);
        createIssue.setNewRecord(true);
        setDialogMode("C");
        createIssue.setStatus("O");
        setEditIssue(createIssue);
    }
    
    public void editActionListener(ActionEvent av) {
        freezeCurrentIssue = true;
        setDialogMode("E");
        editIssue = new Issue();
        editIssue.setIssueId(selectedIssue.getIssueId());
        editIssue.setModuleName(selectedIssue.getModuleName());
        editIssue.setDescription(selectedIssue.getDescription());
        editIssue.setUserId(selectedIssue.getUserId());
        editIssue.setTestId(selectedIssue.getTestId());
        editIssue.setStatus(selectedIssue.getStatus());
        setEditIssue(editIssue);
        
    }
    
    public Integer maximumIdIntheList() {
        Integer currentMax = 1;
        for (Object o: currentList) {
           if  (((Issue)o).getIssueId() > currentMax )
                currentMax =((Issue)o).getIssueId();     
        }
        return currentMax;        
    }
    
    public void submitEdit(ActionEvent av) {
        
        if (getDialogMode().equals("C")) {
//            selectedTest.setId(Integer.valueOf("1000"));
            editIssue.setIssueId(maximumIdIntheList() + 1);
            editIssue.setTestId(currentTestId);
            editIssue.setStatus("O");
            editIssue.setLogDate(new Date(System.currentTimeMillis()));
            Object userName = JSFUtils.resolveExpression("#{login.systemUserName}");
            if (userName != null) 
              editIssue.setUserId(userName.toString().toUpperCase());
            currentList.add(editIssue);
        }
        if (getDialogMode().equals("E")) {
            for (int i=0;i < currentList.size(); i++ ) {
                Object iterate = currentList.get(i);
                if (iterate instanceof Issue && editIssue != null) {
                    if ( ((Issue)iterate).equals(editIssue) ) {
                        ((Issue)iterate).setModuleName(editIssue.getModuleName());
                        ((Issue)iterate).setDescription(editIssue.getDescription());
                        ((Issue)iterate).setTestId(editIssue.getTestId());
                        ((Issue)iterate).setStatus(editIssue.getStatus());
                        currentList.remove(currentList.get(i));
                        currentList.add((Issue)iterate);
                        RequestContext.getCurrentInstance().update(":form:dataTable");
                        return;        
                    }
                }
            }
            
        }
        setDialogMode("N");
    }
    
    
    public void commitChanges (ActionEvent av) {
       for (Object o : currentList) {
         issueService.save((Issue)o);
         for (Object s: ((Issue)o).getScreenshots() ) {
             
         }
       }
        RequestContext.getCurrentInstance().update("growl1");
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"","Transaction commited sucessfully"));
       
    }
    
    public void onRowToggle(ToggleEvent event) {
        
        if (selectedIssue != null ) {
           currentHistoryList = getSelectedIssue().getIssuehistories();
           currentIssueId = getSelectedIssue().getIssueId();
           Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
           int index = Integer.parseInt(params.get("form:dataTable_expandedRowIndex") != null ? params.get("form:dataTable_expandedRowIndex") : "0" );
           
           DataTable dataTable = (DataTable)JSFUtils.findComponent("dataTable");
           dataTable.setRowIndex(index);
        }
        Object o = JSFUtils.readFromRequest("queueEvent");
/*        if (o == null ) {
            SelectEvent selectEvent = new SelectEvent (dataTable, event.getBehavior(),event.getData());
            selectEvent.setPhaseId(PhaseId.INVOKE_APPLICATION);
            dataTable.queueEvent(selectEvent);
            JSFUtils.saveOnRequest("queueEvent", "1");
        }
*/    

        
    }

    
    
    public void createHistActionListener(ActionEvent av) {
        Issuehistory createHistIssue  = new Issuehistory();
        createHistIssue.setIssueId(currentIssueId);
        setDialogModeHist("C");
        createHistIssue.setOldstatus("O");
        setEditIssueHist(createHistIssue);
        freezeCurrentIssue = true;
    }
    
    public void editHistActionListener(ActionEvent av) {
        if (selectedIssuehistory == null ) return;
        setDialogModeHist("E");
        editIssueHist = new Issuehistory();
        editIssueHist.setId(selectedIssuehistory.getId());
        editIssueHist.setIssueId(selectedIssuehistory.getIssueId());
        editIssueHist.setDescription(selectedIssuehistory.getDescription());
        editIssueHist.setOldstatus(selectedIssue.getStatus());
        setEditIssueHist(editIssueHist);
        
    }
    
    public Long maximumHistIdIintheList() {
        Long currentMax = 1L;
        for (Object o: currentHistoryList) {
           if  (((Issuehistory)o).getId() > currentMax )
                currentMax =((Issuehistory)o).getId();     
        }
        return currentMax;        
    }
    
    public void submitEditHist(ActionEvent av) {
        
        if (getDialogModeHist().equals("C")) {
            editIssueHist.setId(maximumHistIdIintheList() + 1);
            editIssueHist.setOldstatus("O");
            editIssueHist.setHistDate(new Date(System.currentTimeMillis()));
            Object userName = JSFUtils.resolveExpression("#{login.systemUserName}");
            if (userName != null) 
              editIssueHist.setUserId(userName.toString().toUpperCase());
            currentHistoryList.add(editIssueHist);
        }
        if (getDialogModeHist().equals("E")) {
            for (int i=0;i < currentHistoryList.size(); i++ ) {
                Object iterate = currentHistoryList.get(i);
                if (iterate instanceof Issuehistory && editIssueHist != null) {
                    if ( ((Issuehistory)iterate).equals(editIssueHist) ) {
                        ((Issuehistory)iterate).setDescription(editIssueHist.getDescription());
                        ((Issuehistory)iterate).setOldstatus(editIssueHist.getOldstatus());
                        ((Issuehistory)iterate).setNewstatus(editIssueHist.getNewstatus());
                        ((Issuehistory)iterate).setHistDate(new Date(System.currentTimeMillis()));

                        currentHistoryList.remove(currentHistoryList.get(i));
                        currentHistoryList.add((Issuehistory)iterate);
                        break;
                    }
                }
            }
            
        }

        selectedIssue.setIssuehistories(currentHistoryList);
        for (Object issue : currentList) {
           if  (  ((Issue)issue).getIssueId().equals(selectedIssue.getIssueId())  ) {
                currentList.remove(issue);
                currentList.add(selectedIssue);
                break;
               
           }  
        }
        RequestContext context = RequestContext.getCurrentInstance();
   //     context.update(":form:dataTable:0:dataTableHist");
  //      context.update("form:dataTable:dataTableHist");
//        context.update(":dataTable:dataTableHist");
//        context.update("form.dataTable.dataTableHist");
//        context.update("dataTable.dataTableHist");
//        context.update("dataTableHist");
        

        UIData table = (UIData) JSFUtils.findComponent("dataTableHist");
        
        String updateClientId = table.getClientId();
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientId);

        UIData tableIssues = (UIData) JSFUtils.findComponent("dataTable");
        String updateClientIdIssues = table.getClientId();
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientIdIssues);

        setDialogModeHist("N");
    }
    
    public void handleIssueHistoryDialogClose(CloseEvent event) {
      freezeCurrentIssue = false;
    }
    
    public void disableToggleAgain(SelectEvent selectEvent) {
        
    }
    
    
    public void openScrDialog(ActionEvent av) {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("closable", false);
        options.put("draggable", true);
        options.put("resizable", true);
        options.put("appendtobody", false);

        options.put("contentHeight", "500");
        options.put("contentWidth", "800");
        List<String> paramList = new ArrayList<String>();
        if (getSelectedIssue() == null) {
           RequestContext.getCurrentInstance().update("growl1");
           FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"","Please highlight an issue"));
           return;

        }
        paramList.add( getSelectedIssue().getIssueId().toString());
        
        Map<String, List<String>> paramMap = new HashMap<String, List<String>>();
        paramMap.put("data", paramList);        
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("type", "ISSUE" );
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("parentId", getSelectedIssue().getIssueId() );
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("payload",getSelectedIssue().getScreenshots()  );
        
   
        RequestContext.getCurrentInstance().openDialog("screenshotDialog", options, paramMap);
    }
    public void onReturnFromDialog(SelectEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        if (event.getObject() != null && event.getObject() instanceof Vector) {
           Vector v = (Vector)event.getObject();
           if (getSelectedIssue() != null) {
               List<Screenshot> l = new ArrayList<Screenshot>();
               for (int i=0;i<v.size();i++) {
                   if (v.get(i) instanceof Screenshot) {
                       ((Screenshot)v.get(i)).setIssue(getSelectedIssue());
                       l.add((Screenshot)v.get(i));
                   }
               }
               if (l.size() >0 && getSelectedIssue() != null) {
                   getSelectedIssue().setScreenshots(l);
               }
               
               for (int i=0;i<currentList.size();i++) {
                   Issue is = (Issue)currentList.get(i);
                   if (is.getIssueId().equals(getSelectedIssue().getIssueId())) {
                       currentList.remove(is);
                       currentList.add(getSelectedIssue());
                   }
                   
               }
                
           }
        }
         
        
        //System.out.println("return from dialog " + event.getObject() != null ? event.getObject().getClass().getName(): "no return" );
    }    
}

