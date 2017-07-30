/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samsoft.issuelogging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author SAM
 */
@ManagedBean(name = "fileUploadManagedBean" )
public class FileUploadManagedBean implements Serializable{

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
	UploadedFile uploadedFile;
        public void save(ActionEvent av) throws IOException {
            String filename = FilenameUtils.getName(uploadedFile.getFileName());
            InputStream input = uploadedFile.getInputstream();
    	    String rootPath = System.getProperty("catalina.home");  
            File file = new File(rootPath + File.separator + "issuemanageruploads",filename);
            OutputStream output = new FileOutputStream(file);

            try {
                IOUtils.copy(input, output);
                JSFUtils.saveOnRequest("LastFileName",  "/issuemanageruploads/"+ filename);
            } finally {
                IOUtils.closeQuietly(input);
                IOUtils.closeQuietly(output);
            }
        }

	
	public void fileUploadListener(FileUploadEvent e){
		// Get uploaded file from the FileUploadEvent
		this.uploadedFile = e.getFile();
		// Print out the information of the file
		System.out.println("Uploaded File Name Is :: "+uploadedFile.getFileName()+" :: Uploaded File Size :: "+uploadedFile.getSize());
	}
}