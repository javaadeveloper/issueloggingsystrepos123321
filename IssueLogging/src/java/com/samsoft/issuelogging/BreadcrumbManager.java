/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author SAM
 */
@ManagedBean(name="breadcrumbManager")
@SessionScoped
public class BreadcrumbManager {
    private MenuModel menuModel;
    public Map<String,String> systemMenuMap;
    
    public BreadcrumbManager() {
        menuModel = new DefaultMenuModel();
        systemMenuMap = new HashMap<String,String>();
        systemMenuMap.put("testHist","Test History");
        systemMenuMap.put("issues","Issues");
        systemMenuMap.put("issuesOfTest","Issues");
        
    }
    public MenuModel getMenuModel() {
        return menuModel;
    }
    public void rebuild(Stack<String> items) {
       Iterator<String> iterator = items.iterator();
       menuModel = new DefaultMenuModel();
       
       while (iterator.hasNext()) {
           Object o = iterator.next();
           Object menuName = systemMenuMap.get(o);
           if (menuName != null) {
               DefaultMenuItem item = new DefaultMenuItem();
               item.setTitle(menuName.toString());
               item.setValue(menuName.toString());
               item.setOutcome(o.toString());
               item.setCommand(o.toString());
               item.setUrl("#");
               menuModel.addElement(item);
           }
       }
       
    }
    
}
