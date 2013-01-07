package gov.nih.nci.cabig.caaers.ws.impl;

import java.util.ArrayList;
import java.util.Locale;

import gov.nih.nci.cabig.caaers.api.impl.Helper;
import gov.nih.nci.cabig.caaers.api.impl.SAEEvaluationServiceImpl;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.integration.schema.common.*;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.EvaluateAEsInputMessage;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.Study;
import gov.nih.nci.cabig.caaers.ws.SAEEvaluationService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.context.MessageSource;


/**
 * API to evaluate adverse events for SAE reporting using caAERS System. 
 * Other external systems like any CDMS can evaluate their AEs for SAE reporting using this webservice
 * Pre-Conditions:
 * Expects the study to be present in caAERS
 * All required data types are in sync between the systems
 * 
 * @author chandrasekaravr
 *
 */
@WebService(name="SAEEvaluationServiceInterface",targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/saerules")
public class SAEEvaluationWebServiceImpl implements SAEEvaluationService {
	private SAEEvaluationServiceImpl svcImpl;
	private MessageSource messageSource;
	
	// Getters/Setters.
	public SAEEvaluationServiceImpl getSvcImpl() {
		return svcImpl;
	}
	public void setSvcImpl(SAEEvaluationServiceImpl svcImpl) {
		this.svcImpl = svcImpl;
	}
	
	public MessageSource getMessageSource() {
		return messageSource;
	}
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	/**
	 *  Evaluate Adverse Events for a Study from an external system.<br/>
	 *  Refer to SAERulesServiceSchema.xsd for schema definition.<br/>
	 * Few rules to enforce in implementation , return messages to client in CaaersServiceResponse.<br/>
	 *	1. Study should be existing in caAERS.<br/>
	 *  2. Site should exist in caAERS but need not be study site
	 * @param adverseEventsInputMessage
	 * @return gov.nih.nci.cabig.caaers.webservice.CaaersServiceResponse
	 */
	@WebMethod
	public CaaersServiceResponse evaluateAEs(@WebParam(name="EvaluateAEsInputMessage", targetNamespace="http://schema.integration.caaers.cabig.nci.nih.gov/saerules") EvaluateAEsInputMessage evaluateAEsInputMessage) {
		Study study = evaluateAEsInputMessage.getStudy();
		
		if ( study == null ) {
			CaaersServiceResponse response = new CaaersServiceResponse();
			Helper.populateError(response, "WS_SAE_005",
					messageSource.getMessage("WS_SAE_005", new String[]{},  "", Locale.getDefault())
					);
			return response;
		}
		
		StudyParticipantAssignment spa = new StudyParticipantAssignment();
		StudySite studySite = new StudySite();
		Organization siteOrg = new LocalOrganization();
		siteOrg.setNciInstituteCode(study.getParticipantSiteIdentifier());		
		studySite.setOrganization(siteOrg);
		spa.setStudySite(studySite);
		
		return svcImpl.processAdverseEvents(evaluateAEsInputMessage.getStudy().getStudyIdentifier(),evaluateAEsInputMessage.getAdverseEvents(),spa,evaluateAEsInputMessage.getStudy().getTreatmentAssignmentCode());
	}
	
	
}
