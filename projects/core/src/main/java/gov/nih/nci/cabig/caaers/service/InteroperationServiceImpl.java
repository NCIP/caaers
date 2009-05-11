package gov.nih.nci.cabig.caaers.service;

import edu.duke.cabig.c3pr.esb.Metadata;
import gme.ccts_cabig._1_0.gov_nih_nci_cabig_ccts_ae.AeNotification;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.factory.AENotificationFactory;
import gov.nih.nci.cabig.caaers.esb.client.MessageBroadcastService;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Required;

/**
 * @author Sujith Vellat Thayyilthodi
 */
public class InteroperationServiceImpl implements InteroperationService {

    private MessageBroadcastService messageBroadcastService;

    private String certificateLocation = "/sample.txt";
    private AENotificationFactory aeNotificationFactory;

    public void pushToStudyCalendar(ExpeditedAdverseEventReport aeReport)
            throws CaaersSystemException {
        AeNotification aeNotification = null;
		try {
			aeNotification = aeNotificationFactory.createAENotificationForExpeditedAdverseEventReport(aeReport);
			String xml = XMLUtil.getXML(aeNotification);
	        System.out.println("XML " + xml);
	        
	        // getMessageBroadcastService().broadcast(secure(XMLUtil.getXML(aeNotification)));
	        getMessageBroadcastService().broadcast(XMLUtil.getXML(aeNotification));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CaaersSystemException(e);
		}
        
    }

    public void pushToStudyCalendar(RoutineAdverseEventReport roReport)
            throws CaaersSystemException {
        AeNotification aeNotification = null;
		try {
			aeNotification = aeNotificationFactory.createAENotificationForRoutineAdverseEventReport(roReport);
			getMessageBroadcastService().broadcast(XMLUtil.getXML(aeNotification));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CaaersSystemException(e);
		}
        // getMessageBroadcastService().broadcast(secure(XMLUtil.getXML(aeNotification)));
        
    }
    public String broadcastCOPPA(String message,Metadata metaData) throws CaaersSystemException {
    	String result = null;
    	try {
    		result = getMessageBroadcastService().broadcastCOPPA(message, metaData);
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CaaersSystemException(e);
		}
    	return result;
    }

    private String secure(String message) {
        StringBuffer secureMessage = new StringBuffer();
        secureMessage.append("<message>");
        secureMessage.append("<credentials>");
        secureMessage.append("<proxy>").append(getCertificate()).append("</proxy>");
        secureMessage.append("</credentials>");
        secureMessage.append("<content>");
        secureMessage.append(message);
        secureMessage.append("</content>");
        secureMessage.append("</message>");
        return secureMessage.toString();
    }

    public MessageBroadcastService getMessageBroadcastService() {
        return messageBroadcastService;
    }

    public void setMessageBroadcastService(MessageBroadcastService messageBroadcastService) {
        this.messageBroadcastService = messageBroadcastService;
    }

    private String getCertificate() {
        StringBuffer certificate = new StringBuffer();
        InputStream inputStream = InteroperationServiceImpl.class
                .getResourceAsStream(getCertificateLocation());
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String line = "";
        try {
            while ((line = dataInputStream.readLine()) != null) {
                certificate.append(line);
            }
        } catch (IOException e) {
            throw new CaaersSystemException(e.getMessage(), e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ioException) {
                throw new CaaersSystemException(ioException.getMessage(), ioException);
            }
        }

        return certificate.toString();

    }

    @Required
    public void setAeNotificationFactory(final AENotificationFactory aeNotificationFactory) {
        this.aeNotificationFactory = aeNotificationFactory;
    }

    public String getCertificateLocation() {
        return certificateLocation;
    }

    public void setCertificateLocation(String certificateLocation) {
        this.certificateLocation = certificateLocation;
    }


}