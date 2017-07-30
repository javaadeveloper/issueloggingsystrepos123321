/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging;

import java.util.Map;
import java.util.Set;
import java.util.Stack;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.NavigationCase;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author SAM
 */
public class AltNavigationHandler extends ConfigurableNavigationHandler 
{

    private final Stack<String> outcomes;

    
    private NavigationHandler parent;

    public AltNavigationHandler(NavigationHandler parent) {
        this.parent = parent;
        this.outcomes = new Stack<String>();
    }
    

    @Override
    public void handleNavigation(final FacesContext context, final String fromAction, final String outcome)
    {
        ConfigurableNavigationHandler d;
        System.out.println("outcome ============" + outcome);
        Object o = JSFUtils.readFromRequest("DEEP_LINK_MENU");
        BreadcrumbManager breadcrumbManager = (BreadcrumbManager)JSFUtils.resolveExpression("#{breadcrumbManager}");
        boolean isDeepLink = true;
        if (o != null && o.equals("0")) {
            isDeepLink = false;
        }
        if (outcome != null)  {
            if (outcome.equals("back"))   {
                final String lastViewId = this.outcomes.pop();
//                final ViewHandler viewHandler = context.getApplication().getViewHandler();
//                final UIViewRoot viewRoot = viewHandler.createView(context, lastViewId);
//                context.setViewRoot(viewRoot);
//                context.renderResponse();
 
                 breadcrumbManager.rebuild(this.outcomes);
                 this.parent.handleNavigation(context, null, lastViewId);
                 return;
            }
            
            if (isDeepLink) {
                this.outcomes.push(outcome);
            } else {
                if (!this.outcomes.isEmpty()) {
                    String current = this.outcomes.peek();
                    while (current != null && !current.equals(outcome) && !this.outcomes.isEmpty()) {
                       current = this.outcomes.pop();
                    }
                }
                if (this.outcomes.isEmpty()) {
                   this.outcomes.push(outcome); 
                }
            }     
        }
        breadcrumbManager.rebuild(this.outcomes);
        this.parent.handleNavigation(context, fromAction, outcome);
    }
    
    @Override
    public NavigationCase getNavigationCase(FacesContext context, String fromAction, String outcome) {
        if (parent instanceof ConfigurableNavigationHandler) {
            return ((ConfigurableNavigationHandler) parent).getNavigationCase(context, fromAction, outcome);
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Set<NavigationCase>> getNavigationCases() {
        if (parent instanceof ConfigurableNavigationHandler) {
            return ((ConfigurableNavigationHandler) parent).getNavigationCases();
        } else {
            return null;
        }
    }  
}