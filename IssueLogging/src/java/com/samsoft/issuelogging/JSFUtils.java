/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging;

import com.sun.faces.component.visit.FullVisitContext;
import java.util.Map;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author SAM
 */
public class JSFUtils {
   public static Object resolveExpression(String expression) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
        return valueExp.getValue(elContext);
   }
   public static void saveOnSession(String variableName,String value) {
     ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
     Map<String, Object> sessionMap = externalContext.getSessionMap();  
     sessionMap.put(variableName, value);
   }
   
   public static Object readFromSession(String variableName) {
     ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
     Map<String, Object> sessionMap = externalContext.getSessionMap();  
     return sessionMap.get(variableName);
   }

   public static void saveOnRequest(String variableName,String value) {
     ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
     Map<String, Object> requestMap = externalContext.getRequestMap();  
     requestMap.put(variableName, value);
   }
   
   public static Object readFromRequest(String variableName) {
     ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
     Map<String, Object> requestMap = externalContext.getRequestMap();  
     return requestMap.get(variableName);
   }
   
 public static UIComponent findComponent(final String id) {

    FacesContext context = FacesContext.getCurrentInstance(); 
    UIViewRoot root = context.getViewRoot();
    final UIComponent[] found = new UIComponent[1];

    root.visitTree(new FullVisitContext(context), new VisitCallback() {     
        @Override
        public VisitResult visit(VisitContext context, UIComponent component) {
            if(component.getId().equals(id)){
                found[0] = component;
                return VisitResult.COMPLETE;
            }
            return VisitResult.ACCEPT;              
        }

    });

    return found[0];

}  
}
