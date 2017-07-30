/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging;

import com.samsoft.issuelogging.model.query.entity.Screenshot;
import com.samsoft.issuelogging.model.service.ScreenshotService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SAM
 */
@ManagedBean(name="screenshotManagerBean")
@ViewScoped
public class ScreenShotJManagerBean implements Serializable{
  
    private List<Screenshot> currentList;
    private String type;
    private Integer issueId;
    private String pageLoadFlag;

    public Integer getIssueId() {
        return issueId;
    }
    
    public void setPageLoadFlag(String value) {
        //nothing to do
    }

    public String getPageLoadFlag() {
        if (pageLoadFlag == null) {
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("type", null );
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("parentId", null );
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("payload",null  );
           
        }
           pageLoadFlag = String.valueOf("Y");    
        return pageLoadFlag;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }
    private ScreenshotService screenshotService = new ScreenshotService();
    private Screenshot selectedScreenShot;

    public Screenshot getSelectedScreenShot() {
        return selectedScreenShot;
    }

    public void setSelectedScreenShot(Screenshot selectedScreenShot) {
        this.selectedScreenShot = selectedScreenShot;
    }
    
    public String addToScreenshot() {
        Object fileName = JSFUtils.readFromRequest("LastFileName");
        if (fileName != null) {
           Screenshot newScr = new Screenshot();
           String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();

           newScr.setFileName(  fileName.toString());
           if (issueId != null)
              newScr.setType("ISSUE");
//           newScr.setIssueId(issueId);
           newScr.setId(999999);
           newScr.setLogDate(new Date(System.currentTimeMillis()));
           currentList.add(newScr);
           IssuesBean issueManagementBean =  (IssuesBean)JSFUtils.resolveExpression("#{issueManagementBean}");
           if (issueManagementBean.getSelectedIssue() != null) {
              issueManagementBean.getSelectedIssue().setScreenshots(getScreenshotList());

       }
           
           
        } else {
           FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"","Error on Saving file..."));
            
        }
        return null;
        
    }

    public List<Screenshot> getScreenshotList() {
        if (pageLoadFlag == null) {
             Object scrType = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("type");
             Object parentId = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("parentId");
             Object latestList = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("payload");
             if (latestList != null && latestList instanceof ArrayList) {
                if ( ((ArrayList)latestList).size() != 0 ) {
                   if (((ArrayList)latestList).get(0) instanceof Screenshot) {
                       currentList = (ArrayList)latestList;
                       return currentList; 
                   } 
                }
             } 
             if (scrType != null && parentId != null) {
                issueId = Integer.valueOf(parentId.toString()); 
                currentList = new ArrayList<Screenshot>();
                currentList = screenshotService.findAll(scrType.toString(), Integer.valueOf(parentId.toString()));
             }  
             
             
            /*
            Map paramMap =  (Map)JSFUtils.resolveExpression("#{param}"); //(Map)FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("selectedTest");
            Set k = paramMap.keySet();
            Iterator it = k.iterator();
            while (it.hasNext()) {
                Object o = it.next();
            }
            if (type == null) {
                type = (String)paramMap.get("type");
                type = "ISSUE";
            }
            if (type != null) {
                Map<Object,Object> requestParams = RequestContext.getCurrentInstance().getAttributes();
                ArrayList parentDataList = (ArrayList) requestParams.get("data");
                if (issueId == null && parentDataList != null) {
                  String issueDetailIdStr = parentDataList.size() !=0 ? parentDataList.get(0).toString():null;
                  issueId = Integer.valueOf(issueDetailIdStr);
                }
                if (issueId == null) {
                    issueId = paramMap.get("data") != null ? Integer.valueOf(paramMap.get("data").toString()) : null ;
                }
                
                IssuesBean issuesBean  = (IssuesBean)JSFUtils.resolveExpression("#{issueManagementBean}"); 
                if (issuesBean != null && issuesBean.getSelectedIssue() != null && issuesBean.getSelectedIssue().getScreenshots() != null) {
                  currentList = issuesBean.getSelectedIssue().getScreenshots();
                } else  {
                    if (issueId != null) {
                      currentList = new ArrayList<Screenshot>();
                      currentList = screenshotService.findAll(type, issueId);
                    }
                    
                }
                

            } 
            */

        }
        return currentList;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    private Integer typeId; 
  
    public void returnFromScrDialog(ActionEvent actionEvent) {
       IssuesBean issueManagementBean =  (IssuesBean)JSFUtils.resolveExpression("#{issueManagementBean}");
       if (issueManagementBean.getSelectedIssue() != null) {
         issueManagementBean.getSelectedIssue().setScreenshots(getScreenshotList());
           
       }
    }
    
    public void closeDialog(ActionEvent av) {
        RequestContext.getCurrentInstance().closeDialog(currentList);
        RequestContext.getCurrentInstance().execute("PF('form_dataTable_0_scrbutton_dlgwidget').hide();");     
    } 

    public String close() {
        RequestContext.getCurrentInstance().closeDialog(currentList);
        return "issueListAction";
    } 

}
