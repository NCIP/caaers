package gov.nih.nci.cabig.caaers.service;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.semanticbits.aenotification.AENotification;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.esb.client.MessageBroadcastService;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class InteroperationServiceImpl implements InteroperationService {

	private MessageBroadcastService messageBroadcastService;
	
	private String certificateLocation = "/sample.txt";
	
	public void pushToStudyCalendar(ExpeditedAdverseEventReport aeReport) throws CaaersSystemException {
		AENotification aeNotification = new AENotification();
		aeNotification.setRegistrationGridId(
				aeReport.getAssignment().getGridId());
		aeNotification.setDetectionDate(
				new java.sql.Date(aeReport.getDetectionDate().getTime()));
        aeNotification.setDescription(aeReport.getNotificationMessage());
		//getMessageBroadcastService().broadcast(secure(XMLUtil.getXML(aeNotification)));
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

	public void setMessageBroadcastService(
			MessageBroadcastService messageBroadcastService) {
		this.messageBroadcastService = messageBroadcastService;
	}
	
	private String getCertificate() {
		StringBuffer certificate = new StringBuffer();
		InputStream inputStream = InteroperationServiceImpl.class.getResourceAsStream(getCertificateLocation());
		DataInputStream dataInputStream = new DataInputStream(inputStream);
		String line = "";
		try {
			while((line= dataInputStream.readLine()) != null){
				certificate.append(line);
			}
		} catch (IOException e) {
			throw new CaaersSystemException(e.getMessage(), e);
		} finally{
			try {
				inputStream.close();
			} catch (IOException ioException) {
				throw new CaaersSystemException(ioException.getMessage(), ioException);
			}
		}
		
		return certificate.toString();

	}

	public String getCertificateLocation() {
		return certificateLocation;
	}

	public void setCertificateLocation(String certificateLocation) {
		this.certificateLocation = certificateLocation;
	}

}