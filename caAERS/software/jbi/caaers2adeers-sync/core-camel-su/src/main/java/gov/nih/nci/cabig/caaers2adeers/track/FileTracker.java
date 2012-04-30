package gov.nih.nci.cabig.caaers2adeers.track;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.InitializingBean;

import java.util.Calendar;

public class FileTracker implements InitializingBean {
    private String folder;


    public void afterPropertiesSet() throws Exception {
        if(folder == null) folder = "data/log";
    }

    public String fileURI(IntegrationLog.Stage s){
        Calendar c = Calendar.getInstance();
        String subFolder =  c.get(Calendar.YEAR)
                + "/"
                + (c.get(Calendar.MONTH) + 1)
                +"/"
                + c.get(Calendar.DAY_OF_MONTH)  ;

        return "file://"+ folder +"/?fileName=" + subFolder + "/${in.header.c2a_correlation_id}/"+ s.name() + ".xml";
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
