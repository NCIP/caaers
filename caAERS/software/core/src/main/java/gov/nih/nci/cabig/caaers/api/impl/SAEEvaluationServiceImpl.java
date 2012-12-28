package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.dto.EvaluationResultDTO;
import gov.nih.nci.cabig.caaers.domain.dto.ReportDefinitionWrapper;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ResponseDataType;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.Status;
import gov.nih.nci.cabig.caaers.integration.schema.common.WsError;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.AdverseEvents;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.RecommendedReports;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.ReportType;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.service.migrator.adverseevent.SAEEvaluationAdverseEventConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;


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

	
	private TreatmentAssignment resolveTreamtmentAssignment(String tacCode, Study study){
		TreatmentAssignment ta = null;
		try {
			ta = treatmentAssignmentDao.getAssignmentsByStudyIdExactMatch(tacCode, study.getId());
		} catch (Exception e) {	
			throw new CaaersSystemException("WS_AEMS_064", messageSource.getMessage("WS_AEMS_064",
					new String[] { String.valueOf(study.getId()) }, "", Locale
							.getDefault()));
		}
		
		return ta;
	}

	public CaaersServiceResponse processAdverseEvents(String studyId, AdverseEvents adverseEvents, StudyParticipantAssignment assignment, String tacCode) {
		
		// Construct from  the input Message.
		
		List<AdverseEvent> aes = new ArrayList<AdverseEvent>();
		Map<AdverseEvent,AdverseEventType> mapAE2DTO = new HashMap<AdverseEvent,AdverseEventType>();
		CaaersServiceResponse response = Helper.createResponse();
		
		for ( AdverseEventType adverseEventDto: adverseEvents.getAdverseEvent()) {
			AdverseEvent ae = converter.convertAdverseEventDtoToAdverseEventDomain(adverseEventDto);
			aes.add(ae);
			mapAE2DTO.put(ae, adverseEventDto);
		}
		
		// Fetch the study from Database
		Study study = fetchStudy(studyId);
		
		if ( study == null) {
			ServiceResponse resp = new ServiceResponse();
			resp.setStatus(Status.FAILED_TO_PROCESS);
			WsError error = new WsError();
			error.setErrorCode(Status.FAILED_TO_PROCESS.name());
			error.setErrorDesc("Study " + studyId + "does not exist inside the Caaers System");
			error.setException(toString());
			List<WsError> errors = new ArrayList<WsError>();
			errors.add(error);
			resp.setWsError(errors);
			response.setServiceResponse(resp);
			
			return response;
			
		}
		
		TreatmentAssignment tas = null;
		if ( tacCode != null) {
			 tas = resolveTreamtmentAssignment(tacCode, study);
		}
		
		// Populate AdverseEventReporting Period
		AdverseEventReportingPeriod period = new AdverseEventReportingPeriod();
		
		period.setAdverseEvents(aes);
		period.setAssignment(assignment);
		period.setTreatmentAssignment(tas);
		
		if ( assignment.getStudySite() != null) {
			assignment.getStudySite().setStudy(study);
		}
		/**
		 * Fill the reporting period into aes.
		 */
		for (AdverseEvent ae: aes) {
			ae.setReportingPeriod(period);
		}
		
		fireSAERules(period, study, mapAE2DTO, response);
		
		return response;
	}
	
	

	/**
	 * @param adverseEventReportingPeriod
	 * @param dbStudy
	 * @param caaersServiceResponse
	 */

	private void fireSAERules(AdverseEventReportingPeriod reportingPeriod, Study study,
			Map<AdverseEvent,AdverseEventType> mapAE2DTO, CaaersServiceResponse caaersServiceResponse) {
		try {
			
			EvaluationResultDTO dto = evaluationService.evaluateSAERules(reportingPeriod);
		
			//Assumption that both the input list and IndexMap have equal size and same Order.
			
			int rdCount = 0; 
			for (AdverseEvent ae: mapAE2DTO.keySet() ) {

				//List<ReportDefinitionWrapper> rds = new ArrayList<ReportDefinitionWrapper>();
				
				Map<AdverseEvent, Set<ReportDefinition>> aeIndexMap = dto.getAdverseEventIndexMap().get(rdCount);
				
				if (aeIndexMap != null) {
					Set<ReportDefinition> rds = aeIndexMap.get(ae); // Assuming
																	// only one
																	// row for

					// Get the Response DTO and populate that object
					// Accordingly.
					AdverseEventType aeDTO = mapAE2DTO.get(ae);
					String dueString = "";

					// Now the process the Report Definitions
					if (rds != null && rds.size() > 0) {

						RecommendedReports recommendedRpts = new RecommendedReports();
						List<ReportType> rptTypeArr = new ArrayList<ReportType>();
						for (ReportDefinition rd : rds) {
							ReportType rpt = new ReportType();
							rpt.setReportName(rd.getName());
							rpt.setReportOrganizationId(rd.getOrganization().getNciInstituteCode());
							rpt.setReportOrganizationName(rd.getOrganization().getName());
							dueString = rd.getExpectedDisplayDueDate(aeDTO.getDateFirstLearned().toGregorianCalendar().getTime());
							rptTypeArr.add(rpt);

						}

						// Set the due value

						recommendedRpts.setDueIn(dueString);
						// Recommended Reports
						recommendedRpts.setReports(rptTypeArr);
						// Set the output
						aeDTO.setRequiresReporting(true);
						aeDTO.setRecommendedReports(recommendedRpts);

					} else {
						aeDTO.setRequiresReporting(false);
					}
				}
				
				rdCount++;
			}
			// Convert the map to respond with only Type Values
			List<AdverseEventType> dtoValues = new ArrayList<AdverseEventType>(mapAE2DTO.values());
			
			AdverseEvents respEventsObj = new AdverseEvents();
			respEventsObj.setAdverseEvent(dtoValues);
			
			// Set the Response
			ServiceResponse resp = new ServiceResponse();
			ResponseDataType respType = new ResponseDataType();
			respType.setAny(respEventsObj);
			resp.setResponseData(respType);
			
			// Populate the response object with the Reporting Definitions.
			caaersServiceResponse.setServiceResponse(resp);
			
		}catch (Exception e) {
			ServiceResponse resp = new ServiceResponse();
			resp.setStatus(Status.FAILED_TO_PROCESS);
			WsError error = new WsError();
			error.setErrorCode(Status.FAILED_TO_PROCESS.name());
			error.setErrorDesc("Unable to process SAE rules");
			error.setException(toString());
			List<WsError> errors = new ArrayList<WsError>();
			errors.add(error);
			resp.setWsError(errors);
			caaersServiceResponse.setServiceResponse(resp);
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
		Identifier si = new Identifier();
		si.setValue(identifier);
		return studyDao.getByIdentifier(si);
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

	public void setTreatmentAssignmentDao(
			TreatmentAssignmentDao treatmentAssignmentDao) {
		this.treatmentAssignmentDao = treatmentAssignmentDao;
	}

	public EvaluationService getAdverseEventEvaluationService() {
		return evaluationService;
	}

	public void setAdverseEventEvaluationService(
			EvaluationService evaluationService) {
		this.evaluationService = evaluationService;
	}
	 
}
