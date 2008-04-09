package gov.nih.nci.cabig.caaers.service;

import com.semanticbits.aenotification.AENotification;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.factory.AENotificationFactory;
import gov.nih.nci.cabig.caaers.esb.client.MessageBroadcastService;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;
import org.springframework.beans.factory.annotation.Required;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Sujith Vellat Thayyilthodi
 */
public class InteroperationServiceImpl implements InteroperationService {

    private MessageBroadcastService messageBroadcastService;

    private String certificateLocation = "/sample.txt";
    private AENotificationFactory aeNotificationFactory;

    public void pushToStudyCalendar(ExpeditedAdverseEventReport aeReport)
            throws CaaersSystemException {
        AENotification aeNotification = aeNotificationFactory.createAENotificationForExpeditedAdverseEventReport(aeReport);
        // getMessageBroadcastService().broadcast(secure(XMLUtil.getXML(aeNotification)));
        getMessageBroadcastService().broadcast(XMLUtil.getXML(aeNotification));
    }

    public void pushToStudyCalendar(RoutineAdverseEventReport roReport)
            throws CaaersSystemException {
        AENotification aeNotification = aeNotificationFactory.createAENotificationForRoutineAdverseEventReport(roReport);
        // getMessageBroadcastService().broadcast(secure(XMLUtil.getXML(aeNotification)));
        getMessageBroadcastService().broadcast(XMLUtil.getXML(aeNotification));
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