/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers2adeers.track;


import org.springframework.beans.factory.InitializingBean;

public class FileTracker implements InitializingBean {
    private String folder;


    public void afterPropertiesSet() throws Exception {
        if(folder == null) folder = "data/log";
    }

    public String fileURI(IntegrationLog.Stage s){
        return "file://"+ folder +"/?fileName=${date:now:yyyy}/${date:now:MM}/${date:now:dd}/${in.header.c2a_entity}/${in.header.c2a_correlation_id}/"+ s.name() + ".xml";
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

}
