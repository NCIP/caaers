package gov.nih.nci.cabig.caaers.esb.client;

import org.springframework.context.ApplicationContextAware;

public interface ESBMessageConsumer extends ApplicationContextAware{
    public void processMessage(String message) throws Exception;
}
