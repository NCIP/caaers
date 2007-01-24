package gov.nih.nci.cabig.caaers.service;

import com.semanticbits.aenotification.AENotification;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.esb.client.MessageBroadcastService;
import gov.nih.nci.cabig.caaers.utils.XMLUtil;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class InteroperationServiceImpl implements InteroperationService {

	private MessageBroadcastService messageBroadcastService;
	
	public void pushToStudyCalendar(AdverseEventReport aeReport) throws CaaersSystemException {
		AENotification aeNotification = new AENotification();
		aeNotification.setRegistrationGridId(
				aeReport.getAssignment().getGridId());
		aeNotification.setDetectionDate(
				new java.sql.Date(aeReport.getPrimaryAdverseEvent().getDetectionDate().getTime()));
		aeNotification.setDescription(aeReport.getPrimaryAdverseEvent().getDetailsForOther());
		getMessageBroadcastService().broadcast(XMLUtil.getXML(aeNotification));
	}

	public MessageBroadcastService getMessageBroadcastService() {
		return messageBroadcastService;
	}

	public void setMessageBroadcastService(
			MessageBroadcastService messageBroadcastService) {
		this.messageBroadcastService = messageBroadcastService;
	}

}