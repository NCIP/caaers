package gov.nih.nci.cabig.caaers.esb.client;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.report.ReportDao;

import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;
/**
 * This class is responsible for the processing of response messages from external system (currently AdEERS).
 * @author Srini
 * @author Biju Joseph
 *
 */
public abstract class ResponseMessageProcessor {
	protected final Log log = LogFactory.getLog(getClass());
	private MessageNotificationService messageNotificationService;
	protected ReportDao reportDao;
	
	 //will be used to obtain resource bundle messages
    protected MessageSource messageSource;
	
	public Namespace getNameSpace(Element ele){
		return ele.getNamespace();
	}
    public Element getResponseElement(String message,String responseType,String child) throws CaaersSystemException{
    	log.debug("in getResponseElement");
        SAXBuilder saxBuilder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");
        Reader stringReader = new StringReader(message);
        Element jobInfo = null;
        try {
            org.jdom.Document jdomDocument = saxBuilder.build(stringReader);
            org.jdom.Element root = jdomDocument.getRootElement();

            Element body = root.getChild("Body", getNameSpace(root));
            log.debug("got body ");
            Namespace n1 = ((Element)body.getChildren().get(0)).getNamespace();
            Element response = body.getChild(responseType,n1);
            log.debug("got response ");
            Namespace n = ((Element) response.getChildren().get(0)).getNamespace();
            jobInfo = response.getChild(child, n);
            log.debug("got child ");

        } catch (Exception e) {
        	log.error("Error in getResponseElement()" + e);
            throw new CaaersSystemException(e);
        } 
        return jobInfo;
    }
    
	public void setMessageNotificationService(
			MessageNotificationService messageNotificationService) {
		this.messageNotificationService = messageNotificationService;
	}
    
	public MessageNotificationService getMessageNotificationService() {
		return messageNotificationService;
	}
	
	@Required
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	@Required
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	public abstract void processMessage(String message) throws CaaersSystemException;
	
}
