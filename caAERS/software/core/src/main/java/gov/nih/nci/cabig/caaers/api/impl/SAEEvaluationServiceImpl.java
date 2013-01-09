package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.dto.EvaluationResultDTO;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ResponseDataType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.Status;
import gov.nih.nci.cabig.caaers.integration.schema.common.WsError;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.AdverseEvents;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.EvaluateAEsInputMessage;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.RecommendedReports;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.ReportType;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.migrator.adverseevent.SAEEvaluationAdverseEventConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;

/**
 * EvaluationService evaluate rules on the given AE's submitted by Web service.
 * 
 * @author MedaV
 * 
 */

public class SAEEvaluationServiceImpl implements ApplicationContextAware {

	protected StudyDao studyDao;
	protected TreatmentAssignmentDao treatmentAssignmentDao;

	private EvaluationService evaluationService;
	private ApplicationContext applicationContext;
	private MessageSource messageSource;
	private SAEEvaluationAdverseEventConverter converter;

	private static Log logger = LogFactory.getLog(SAEEvaluationServiceImpl.class);
	
	public CaaersServiceResponse processAdverseEvents(EvaluateAEsInputMessage evaluateAEsInputMessage) {
		if ( evaluateAEsInputMessage == null ) {
			CaaersServiceResponse response =  Helper.createResponse();
			Helper.populateError(response, "WS_GEN_001",
					messageSource.getMessage("WS_GEN_001", new String[]{},  "", Locale.getDefault())
					);
			return response;
		}
		
		gov.nih.nci.cabig.caaers.integration.schema.saerules.Study study = evaluateAEsInputMessage.getStudy();
		
		if ( study == null ) {
			CaaersServiceResponse response =  Helper.createResponse();
			response.setServiceResponse(new ServiceResponse());
			Helper.populateError(response, "WS_GEN_001",
					messageSource.getMessage("WS_GEN_001", new String[]{},  "", Locale.getDefault())
					);
			return response;
		}
		
		if ( evaluateAEsInputMessage.getAdverseEvents() == null || evaluateAEsInputMessage.getAdverseEvents().getAdverseEvent() == null || evaluateAEsInputMessage.getAdverseEvents().getAdverseEvent().size() == 0 ) {
			CaaersServiceResponse response =  Helper.createResponse();
			response.setServiceResponse(new ServiceResponse());
			Helper.populateError(response, "WS_SAE_006",
					messageSource.getMessage("WS_SAE_006", new String[]{},  "", Locale.getDefault())
					);
			return response;
		}
		
		StudyParticipantAssignment spa = new StudyParticipantAssignment();
		StudySite studySite = new StudySite();
		Organization siteOrg = new LocalOrganization();
		siteOrg.setNciInstituteCode(study.getParticipantSiteIdentifier());		
		studySite.setOrganization(siteOrg);
		spa.setStudySite(studySite);
		
		return processAdverseEvents(evaluateAEsInputMessage.getStudy().getStudyIdentifier(),evaluateAEsInputMessage.getAdverseEvents(),spa,evaluateAEsInputMessage.getStudy().getTreatmentAssignmentCode());
	}

	public CaaersServiceResponse processAdverseEvents(String studyId, AdverseEvents adverseEvents,
			StudyParticipantAssignment assignment, String tacCode) {

		// Construct from the input Message.

		List<AdverseEvent> aes = new ArrayList<AdverseEvent>();
		Map<AdverseEvent, AdverseEventType> mapAE2DTO = new HashMap<AdverseEvent, AdverseEventType>();
		CaaersServiceResponse response = Helper.createResponse();

		Study study = null;
		TreatmentAssignment tas = null;

		try {
			
			// Fetch the study from Database
			study = fetchStudy(studyId);
			
			AeTerminology terminology = study.getAeTerminology();
		
			for (AdverseEventType adverseEventDto : adverseEvents.getAdverseEvent()) {
				AdverseEvent ae = converter.convertAdverseEventDtoToAdverseEventDomain(adverseEventDto, terminology);
				aes.add(ae);
				mapAE2DTO.put(ae, adverseEventDto);
			}

			if (tacCode != null) {
				tas = resolveTreatmentAssignment(tacCode, study);
			}

		} catch (CaaersSystemException e) {
			Helper.populateError(response, e.getErrorCode(), e.getMessage());
			return response;
		}

		// Populate AdverseEventReporting Period
		AdverseEventReportingPeriod period = new AdverseEventReportingPeriod();
		
		period.setAdverseEvents(aes);
		period.setAssignment(assignment);
		period.setTreatmentAssignment(tas);
		
		if (assignment.getStudySite() != null) {
			assignment.getStudySite().setStudy(study);
		}
		/**
		 * Fill the reporting period into ae's.
		 */
		for (AdverseEvent ae : aes) {
			ae.setReportingPeriod(period);
		}

		fireSAERules(period, study, mapAE2DTO, response);

		return response;
	}

	/**
	 * Retrieve TreatmentAssignment from the Study for the
	 * TreatmentAssignmentCode
	 * 
	 * @param tacCode
	 *            - String representing TreatmentAssignmentCode
	 * @param study
	 *            - instance of Study
	 * @return instance of TreatmentAssignment
	 */
	private TreatmentAssignment resolveTreatmentAssignment(String tacCode, Study study) {
		TreatmentAssignment ta = null;
		try {
			ta = treatmentAssignmentDao.getAssignmentsByStudyIdExactMatch(tacCode, study.getId());
		} catch (Exception e) {
			throw new CaaersSystemException("WS_SAE_002", messageSource.getMessage("WS_SAE_002",
					new String[] { String.valueOf(study.getId()) }, "", Locale.getDefault()));
		}
		
		return ta;
	}

	/**
	 * @param adverseEventReportingPeriod
	 * @param dbStudy
	 * @param caaersServiceResponse
	 */

	private void fireSAERules(AdverseEventReportingPeriod reportingPeriod, Study study,
			Map<AdverseEvent, AdverseEventType> mapAE2DTO, CaaersServiceResponse caaersServiceResponse) {
		try {

			EvaluationResultDTO dto = evaluationService.evaluateSAERules(reportingPeriod);
			
			// Convert the map to respond with only Type Values
			List<AdverseEventType> dtoValues = new ArrayList<AdverseEventType>(mapAE2DTO.values());
			
			// Initialize the dtoValues to false;
			for (AdverseEventType aeDTO: dtoValues) {
				aeDTO.setRequiresReporting(false);
			}
						
			Map<Integer, Map<AdverseEvent, Set<ReportDefinition>>> repAEIndexMap = dto.getAdverseEventIndexMap();
			if(repAEIndexMap !=null) {
				for (Map<AdverseEvent, Set<ReportDefinition>> aeIndexMap : repAEIndexMap.values()) {
					for (Entry<AdverseEvent, Set<ReportDefinition>> entry : aeIndexMap.entrySet()) {
						
						AdverseEvent ae = entry.getKey();
						Set<ReportDefinition> rds = entry.getValue();

						// Get the Response DTO and populate that object
						// Accordingly.
						AdverseEventType aeDTO = mapAE2DTO.get(ae);
						
						// Now the process the Report Definitions
						if (rds != null && rds.size() > 0) {
	
							RecommendedReports recommendedRpts = new RecommendedReports();
							List<ReportType> rprtTypLst = recommendedRpts.getReportType();
							for (ReportDefinition rd : rds) {
								ReportType rpt = new ReportType();
								rpt.setReportName(rd.getName());
								rpt.setReportOrganizationId(rd.getOrganization().getNciInstituteCode());
								rpt.setReportOrganizationName(rd.getOrganization().getName());
								rpt.setDueIn(rd.getExpectedDisplayDueDate(aeDTO.getDateFirstLearned().toGregorianCalendar()
										.getTime()));
								rprtTypLst.add(rpt);	
							}

							// Set the output
							aeDTO.setRequiresReporting(true);
							aeDTO.setRecommendedReports(recommendedRpts);
						}
					}//end of for AEs
				}//end of for reports
			}//end of if
			
			AdverseEvents respEventsObj = new AdverseEvents();
			respEventsObj.setAdverseEvent(dtoValues);

			// Set the Response	
			ServiceResponse resp = caaersServiceResponse.getServiceResponse();
			ResponseDataType respType = new ResponseDataType();
			respType.setAny(respEventsObj);
			resp.setResponseData(respType);

			// Populate the response object with the Reporting Definitions.
			caaersServiceResponse.setServiceResponse(resp);

		} catch (Exception e) {
			Helper.populateError(caaersServiceResponse, "WS_SAE_001",
					messageSource.getMessage("WS_SAE_001", new String[]{},  "", Locale.getDefault())
					);
			logger.error(" Exception Occured when processing rules" + e.toString());
		}

	}

	public SAEEvaluationAdverseEventConverter getConverter() {
		return converter;
	}

	public void setConverter(SAEEvaluationAdverseEventConverter converter) {
		this.converter = converter;
	}

	private Study fetchStudy(String identifier) {
		if (StringUtils.isEmpty(identifier)) {
			throw new CaaersSystemException("WS_SAE_004", messageSource.getMessage("WS_SAE_004", new String[] {}, "",
					Locale.getDefault()));
		}
		Study study = null;
		try {
			Identifier si = new Identifier();
			si.setValue(identifier);
			study = studyDao.getByIdentifier(si);
		} catch (Exception e) {
			throw new CaaersSystemException("WS_GEN_001", messageSource.getMessage("WS_GEN_001", new String[] {}, "",
					Locale.getDefault()));
		}
		if (study == null) {
			throw new CaaersSystemException("WS_SAE_005", messageSource.getMessage("WS_SAE_005",
					new String[] { identifier }, "", Locale.getDefault()));
		}
		return study;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public StudyDao getStudyDao() {
		return studyDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public TreatmentAssignmentDao getTreatmentAssignmentDao() {
		return treatmentAssignmentDao;
	}

	public void setTreatmentAssignmentDao(TreatmentAssignmentDao treatmentAssignmentDao) {
		this.treatmentAssignmentDao = treatmentAssignmentDao;
	}

	public EvaluationService getAdverseEventEvaluationService() {
		return evaluationService;
	}

	public void setAdverseEventEvaluationService(EvaluationService evaluationService) {
		this.evaluationService = evaluationService;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
