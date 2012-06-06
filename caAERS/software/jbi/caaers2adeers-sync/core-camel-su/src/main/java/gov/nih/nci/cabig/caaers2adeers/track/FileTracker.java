package gov.nih.nci.cabig.caaers2adeers.track;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.InitializingBean;

import java.util.Calendar;

public class FileTracker implements InitializingBean, Processor {
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

	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
