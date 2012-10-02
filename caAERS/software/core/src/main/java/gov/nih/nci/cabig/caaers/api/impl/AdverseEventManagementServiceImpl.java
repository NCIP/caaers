package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.AbstractImportService;
import gov.nih.nci.cabig.caaers.api.ProcessingOutcome;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ExternalAdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.ExternalAdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.index.AdverseEventIndexDao;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ExternalAEReviewStatus;
import gov.nih.nci.cabig.caaers.domain.ExternalAdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ExternalAdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.domain.repository.AdverseEventRoutingAndReviewRepository;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventMeddraLowLevelTermType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEvents;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventsInputMessage;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.Criteria;
import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.rules.business.service.AdverseEventEvaluationService;
import gov.nih.nci.cabig.caaers.security.SecurityUtils;
import gov.nih.nci.cabig.caaers.service.AdverseEventReportingPeriodService;
import gov.nih.nci.cabig.caaers.service.migrator.adverseevent.AdverseEventConverter;
import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.validation.AdverseEventGroup;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.semanticbits.rules.impl.BusinessRulesExecutionServiceImpl;
import org.springframework.transaction.annotation.Transactional;

public class AdverseEventManagementServiceImpl extends AbstractImportService implements ApplicationContextAware {

	public static final String CREATE = "create";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	
	private Validator validator;

	protected BusinessRulesExecutionServiceImpl executionService;
	protected AdverseEventDao adverseEventDao;
	protected ParticipantDao participantDao;
	protected StudyDao studyDao;
	protected StudyParticipantAssignmentDao studyParticipantAssignmentDao;
	protected AdverseEventIndexDao adverseEventIndexDao;
	protected AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	protected ExternalAdverseEventReportingPeriodDao externalAdverseEventReportingPeriodDao;
	protected ExternalAdverseEventDao externalAdverseEventDao;

	protected TreatmentAssignmentDao treatmentAssignmentDao;
	protected CtcTermDao ctcTermDao;

	private AdverseEventConverter adverseEventConverter;
	private AdverseEventEvaluationService adverseEventEvaluationService;
	private AdverseEventReportingPeriodService adverseEventReportingPeriodService;
	private MailSender mailSender;
	private ApplicationContext applicationContext;
	private MessageSource messageSource;
    private Configuration configuration;
    private AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository;

	private static Log logger = LogFactory.getLog(AdverseEventManagementServiceImpl.class);

	public CaaersServiceResponse deleteAdverseEvent(AdverseEventsInputMessage adverseEventsInputMessage) {
		CaaersServiceResponse response = Helper.createResponse();
		if (adverseEventsInputMessage.getAdverseEvents() == null) {
			return Helper.populateError(response, "WS_AEMS_026", messageSource.getMessage("WS_AEMS_026",
					new String[] {}, "", Locale.getDefault()));
		}
		return saveAdverseEvent(adverseEventsInputMessage, DELETE);
	}

	public CaaersServiceResponse createOrUpdateAdverseEvent(AdverseEventsInputMessage adverseEventsInputMessage) {
		return saveAdverseEvent(adverseEventsInputMessage, UPDATE);
	}

	public CaaersServiceResponse createAdverseEvent(AdverseEventsInputMessage adverseEventsInputMessage) {
		return saveAdverseEvent(adverseEventsInputMessage, CREATE);
	}
	
	private boolean validateAdverseEventInputMessage(AdverseEventsInputMessage adverseEventsInputMessage, CaaersServiceResponse caaersServiceResponse){
		
		Boolean validMessage = true;
		if (adverseEventsInputMessage.getAdverseEvents() == null) {
			Helper.populateError(caaersServiceResponse, "WS_AEMS_025",
                    messageSource.getMessage( "WS_AEMS_025", new String[] {}, "", Locale.getDefault())
            );
			validMessage = false;
		}
		
		if (adverseEventsInputMessage.getCriteria().getCourse() == null) {
            Helper.populateError(caaersServiceResponse, "WS_AEMS_065",
                    messageSource.getMessage("WS_AEMS_065", new String[] { }, "", Locale.getDefault())
            );
            validMessage = false;
		}
		
		return validMessage;
	}

    public boolean isWorkflowEnabled(){
        return configuration.get(Configuration.ENABLE_WORKFLOW);
    }
	
	/**
	 * @param criteria
	 * @param assignment
	 * @param caaersServiceResponse
	 * @return AdverseEventReportingPeriod
	 */
	private AdverseEventReportingPeriod fetchAEReportingPeriod(Criteria criteria,
			StudyParticipantAssignment assignment, CaaersServiceResponse caaersServiceResponse, String externalId) {
		AdverseEventReportingPeriod adverseEventReportingPeriod = null;
		if (criteria.getCourse() != null) {
			try {
				adverseEventReportingPeriod = createDomainReportingPeriodIfNotExists(criteria, assignment, externalId);
			} catch (CaaersSystemException e) {
				logger.error(e);
				Helper.populateError(caaersServiceResponse, e.getErrorCode(), e.getMessage());
			}
		}
		return adverseEventReportingPeriod;
	}
	
	private AdverseEventReportingPeriod createDomainReportingPeriodIfNotExists(Criteria criteria, StudyParticipantAssignment assignment, String externalId) throws CaaersSystemException {
		List<AdverseEventReportingPeriod> rPeriodList = adverseEventReportingPeriodDao.getByAssignment(assignment);
		
		String treatmentAssignmentCode = null;
		String otherTreatmentAssignmentDescription = null; 
		if((treatmentAssignmentCode = criteria.getCourse().getTreatmentAssignmentCode()) != null){
			for (AdverseEventReportingPeriod matchingDomainAdverseEventReportingPeriod : rPeriodList) {
				// compare TAC and treatment type to determine if matchingDomainAdverseEventReportingPeriod exists.
				if(matchingDomainAdverseEventReportingPeriod.getTreatmentAssignment().getCode() != null && matchingDomainAdverseEventReportingPeriod.
						getTreatmentAssignment().getCode().equals(treatmentAssignmentCode) && criteria.getCourse().getTreatmentType() == null ? 
								true:criteria.getCourse().getTreatmentType().equals(matchingDomainAdverseEventReportingPeriod.getEpoch().getName())){
					return matchingDomainAdverseEventReportingPeriod;
				}
			}
		} else if ((otherTreatmentAssignmentDescription = criteria.getCourse().getOtherTreatmentAssignmentDescription()) != null){
					for (AdverseEventReportingPeriod matchingDomainAdverseEventReportingPeriod : rPeriodList) {
						if(matchingDomainAdverseEventReportingPeriod.getTreatmentAssignment().getDescription() != null && matchingDomainAdverseEventReportingPeriod.
								getTreatmentAssignmentDescription().equals(otherTreatmentAssignmentDescription)){
							return matchingDomainAdverseEventReportingPeriod;
						}
					}
		} else {
			throw new CaaersSystemException("WS_AEMS_070", "One of treatment assignment code or other treatment assignment description is required");
		}
		
		// get study from assignment
		Study study = assignment.getStudySite().getStudy();
		Date xmlStartDate = criteria.getCourse().getStartDateOfThisCourse().toGregorianCalendar().getTime();
		Date xmlEndDate = null;
		if (criteria.getCourse().getEndDateOfThisCourse() != null) {
			xmlEndDate = criteria.getCourse().getEndDateOfThisCourse().toGregorianCalendar().getTime();
		}
		
		AdverseEventReportingPeriod newAeReportingPeriod = new AdverseEventReportingPeriod();
		
		if(treatmentAssignmentCode != null){
			TreatmentAssignment ta = resolveTreamtmentAssignment(criteria, study);
			if (ta == null) {
				throw new CaaersSystemException("WS_AEMS_009", messageSource.getMessage("WS_AEMS_009",
						new String[] { criteria.getCourse().getTreatmentAssignmentCode() }, "", Locale
								.getDefault()));
			}
			newAeReportingPeriod.setTreatmentAssignment(ta);
		} else {
			newAeReportingPeriod.setTreatmentAssignmentDescription(otherTreatmentAssignmentDescription);
		}
		newAeReportingPeriod.setStartDate(xmlStartDate);
		newAeReportingPeriod.setEndDate(xmlEndDate);
		if(criteria.getCourse().getCycleNumber() != null) newAeReportingPeriod.setCycleNumber(criteria.getCourse().getCycleNumber().intValue());
		assignment.addReportingPeriod(newAeReportingPeriod);
		Epoch epochToSave = getEpoch(criteria, study);
		
		// CAAERS-2813
	/*	if (epochToSave == null) {
			throw new CaaersSystemException("WS_AEMS_010", messageSource.getMessage("WS_AEMS_010",
					new String[] { criteria.getCourse().getTreatmentType() }, "", Locale.getDefault()));
		}*/
		newAeReportingPeriod.setEpoch(epochToSave);
		// get the externalId from the message and set here.
		newAeReportingPeriod.setExternalId(externalId);
		
		Map<String, String> errors = validateRepPeriodDates(newAeReportingPeriod, rPeriodList,
				assignment.getStartDateOfFirstCourse());
		
		// update workflow to DCC
	//	adverseEventRoutingAndReviewRepository.advanceReportingPeriodWorkflow(newAeReportingPeriod.getWorkflowId(), null, newAeReportingPeriod, getUserId());
		if (errors.size() == 0) {
			try {
				adverseEventReportingPeriodDao.save(newAeReportingPeriod);
                if(isWorkflowEnabled()){
                    Long wfId = adverseEventRoutingAndReviewRepository.enactReportingPeriodWorkflow(newAeReportingPeriod);
                    logger.debug("Enacted workflow : " + wfId);
                }
			} catch (Exception e) {
				throw new CaaersSystemException("WS_AEMS_068", messageSource.getMessage("WS_AEMS_068",
						new String[] { }, "", Locale.getDefault()));
			}
			
		} else {
			String keys[] = errors.keySet().toArray(new String[] {});
			throw new CaaersSystemException(keys[0], errors.get(keys[0]));
		}
		
			
		return newAeReportingPeriod;
	}
	
	private Epoch getEpoch(Criteria criteria, Study study){
		List<Epoch> epochs = study.getEpochs();
		if (StringUtils.isNotEmpty(criteria.getCourse().getTreatmentType())) {
			for (Epoch epoch : epochs) {
				if (epoch.getName().equals(criteria.getCourse().getTreatmentType())) {
					return epoch;
				}
			}
		}
		
		return null;
	}

    @Transactional
	public CaaersServiceResponse createProvisionalAdverseEvents(AdverseEventsInputMessage adverseEventsInputMessage){
		CaaersServiceResponse caaersServiceResponse = Helper.createResponse();

        if (!validateAdverseEventInputMessage(adverseEventsInputMessage,caaersServiceResponse)){
			return caaersServiceResponse;
		}

        if(!isWorkflowEnabled()){
            Helper.populateError(caaersServiceResponse, "WS_AEMS_072", messageSource.getMessage("WS_AEMS_072", new Object[]{}, Locale.getDefault()) );
            return caaersServiceResponse;
        }
		
		Criteria criteria = adverseEventsInputMessage.getCriteria();
		String studyIdentifier = criteria.getStudyIdentifier();
		String studySubjectIdentifier = criteria.getStudySubjectIdentifier();
		// fetch study
		Study dbStudy = fetchStudy(studyIdentifier, caaersServiceResponse);
		if (dbStudy == null) {
			//response must be populated with appropriate errors that lead to one of these being null
			return caaersServiceResponse;
		}
		// fetch participant
		Participant dbParticipant = fetchParticipant(studySubjectIdentifier, dbStudy.getId(), caaersServiceResponse);
		if (dbParticipant == null) {
			//response must be populated with appropriate errors that lead to one of these being null
			return caaersServiceResponse;
		}
		
		// fetch study participant assignment
		StudyParticipantAssignment assignment = fetchAssignment(caaersServiceResponse, studyIdentifier,
				studySubjectIdentifier, dbStudy, dbParticipant);
		if (assignment == null) {
			//response must be populated with appropriate errors that lead to one of these being null
			return caaersServiceResponse;
		}
		
		
		ExternalAdverseEventReportingPeriod externalAdverseEventReportingPeriod = buildExternalReportingPeriod(adverseEventsInputMessage, caaersServiceResponse, assignment, null);
		
		// fetch AE reporting period
		AdverseEventReportingPeriod adverseEventReportingPeriod = fetchAEReportingPeriod(criteria, assignment, caaersServiceResponse, 
				adverseEventsInputMessage.getCriteria().getCourse().getExternalId());
		
		// update the  the status of the old adv events( from earlier msgs), that have same external ids (from the current msg) and which are 
		// still in pending to ignored.
		List<String> externalIds = getExternalIdsOfExternalAEs(externalAdverseEventReportingPeriod);
		if(externalIds != null && externalIds.size() > 0){
			externalAdverseEventDao.updateStatus(ExternalAEReviewStatus.IGNORED, ExternalAEReviewStatus.PENDING, externalIds);
		}
		
		// associate externalAdverseEventReportingPeriod to the domain reporting period
		externalAdverseEventReportingPeriod.setDomainReportingPeriod(adverseEventReportingPeriod);
		
		// save external reporting period along with the external adverse events
		saveExternalAdverseEventReportingPeriod(externalAdverseEventReportingPeriod, caaersServiceResponse);

        List<String> possibleTransitions = adverseEventRoutingAndReviewRepository.nextTransitionNames(adverseEventReportingPeriod.getWorkflowId(), SecurityUtils.getUserLoginName());
        if(possibleTransitions.contains("Submit to Data Coordinator")) {
            adverseEventRoutingAndReviewRepository.advanceReportingPeriodWorkflow(adverseEventReportingPeriod.getWorkflowId(), "Submit to Data Coordinator", adverseEventReportingPeriod, SecurityUtils.getUserLoginName());
        }
        
		return caaersServiceResponse;
	}
	
	private List<String> getExternalIdsOfExternalAEs(ExternalAdverseEventReportingPeriod externalAdverseEventReportingPeriod){
		List<String> externalIds = new ArrayList<String>();
		for(ExternalAdverseEvent externalAdverseEvent : externalAdverseEventReportingPeriod.getExternalAdverseEvents()){
			externalIds.add(externalAdverseEvent.getExternalId());
		}
		
		return externalIds;
	}
	
	public void saveExternalAdverseEventReportingPeriod(ExternalAdverseEventReportingPeriod externalAdverseEventReportingPeriod, CaaersServiceResponse caaersServiceResponse){
		try{
			externalAdverseEventReportingPeriodDao.save(externalAdverseEventReportingPeriod);
			caaersServiceResponse.getServiceResponse().setMessage("Completed the request processing successfully");
		}catch(Exception ex){
			String message = messageSource.getMessage("WS_AEMS_071", null, Locale.getDefault());
			logger.error(message, ex);
			Helper.populateError(caaersServiceResponse, "WS_AEMS_071", message);
			logger.error(ex.getMessage());
			caaersServiceResponse.getServiceResponse().setMessage("Error while saving provisional adverse event reporting period.");
		}
		
	}
	
	
	private ExternalAdverseEventReportingPeriod buildExternalReportingPeriod(AdverseEventsInputMessage adverseEventsInputMessage, CaaersServiceResponse caaersServiceResponse, StudyParticipantAssignment assignment,String externalId) throws CaaersSystemException {
		
		Criteria criteria = adverseEventsInputMessage.getCriteria();
		// get study from assignment
		Study study = assignment.getStudySite().getStudy();
		Date xmlStartDate = criteria.getCourse().getStartDateOfThisCourse().toGregorianCalendar().getTime();
		Date xmlEndDate = null;
		if (criteria.getCourse().getEndDateOfThisCourse() != null) {
			xmlEndDate = criteria.getCourse().getEndDateOfThisCourse().toGregorianCalendar().getTime();
		}
		ExternalAdverseEventReportingPeriod externalAeReportingPeriod = new ExternalAdverseEventReportingPeriod();
		externalAeReportingPeriod.setStartDate(xmlStartDate);
		externalAeReportingPeriod.setEndDate(xmlEndDate);
		if(criteria.getCourse().getCycleNumber() != null){
			externalAeReportingPeriod.setCycleNumber(criteria.getCourse().getCycleNumber().intValue());
		}
		
		if(criteria.getCourse().getTreatmentAssignmentCode() != null){
			TreatmentAssignment ta = resolveTreamtmentAssignment(criteria, study);
			if (ta == null) {
				throw new CaaersSystemException("WS_AEMS_009", messageSource.getMessage("WS_AEMS_009",
						new String[] { criteria.getCourse().getTreatmentAssignmentCode() }, "", Locale
								.getDefault()));
			}
			externalAeReportingPeriod.setTreatmentAssignmentCode(ta.getCode());
		} else {
			externalAeReportingPeriod.setOtherTreatmentAssignmentDescription(criteria.getCourse().getOtherTreatmentAssignmentDescription());
		}
		externalAeReportingPeriod.setExternalId(externalId);
		
		processExternalAdverseEvents(adverseEventsInputMessage,caaersServiceResponse,study, assignment, externalAeReportingPeriod);
		
		
		return externalAeReportingPeriod;
	}
	
	private void processExternalAdverseEvents(AdverseEventsInputMessage adverseEventsInputMessage, CaaersServiceResponse caaersServiceResponse, 
			Study study, StudyParticipantAssignment assignment,	ExternalAdverseEventReportingPeriod externalAdverseEventReportingPeriod){
		AdverseEvents xmlAdverseEvents = adverseEventsInputMessage.getAdverseEvents();
		int index =1;
		for(AdverseEventType adverseEventDto : xmlAdverseEvents.getAdverseEvent()){
			ExternalAdverseEvent externalAdverseEvent = new ExternalAdverseEvent();
			adverseEventConverter.convertAdverseEventDtoToExternalAdverseEventDTO( adverseEventDto, externalAdverseEvent,
                    study.getAeTerminology(), assignment.getStartDateOfFirstCourse());
				try {
					
					Set<ConstraintViolation<ExternalAdverseEvent>> constraintViolations = validator.validate(externalAdverseEvent, AdverseEventGroup.class, Default.class);
					convertConstraintViolationsToProcessOutcomes(
							externalAdverseEvent, String.valueOf(index), constraintViolations, caaersServiceResponse);
					// add it to external adverse event reporting period to be saved ...
					if(constraintViolations.isEmpty()) {
						externalAdverseEventReportingPeriod.addExternalAdverseEvent(externalAdverseEvent);
					}
				} catch (CaaersSystemException e) {
					Helper.populateErrorOutcome(caaersServiceResponse, null, null, (index) + "", Arrays.asList(new String[]{e.getMessage()}));
				}
			index++;
		}
	}
	
	private TreatmentAssignment resolveTreamtmentAssignment(Criteria criteria, Study study){
		TreatmentAssignment ta = null;
		try {
			ta = treatmentAssignmentDao.getAssignmentsByStudyIdExactMatch(criteria
					.getCourse().getTreatmentAssignmentCode(), study.getId());
		} catch (Exception e) {
			throw new CaaersSystemException("WS_AEMS_064", messageSource.getMessage("WS_AEMS_064",
					new String[] { String.valueOf(study.getId()) }, "", Locale
							.getDefault()));
		}
		
		return ta;
	}

	private CaaersServiceResponse saveAdverseEvent(AdverseEventsInputMessage adverseEventsInputMessage, String operation) {
		// boolean authorizationOnByDefault = enableAuthorization(false);
		// switchUser("SYSTEM_ADMIN", "caaers_super_user");
		// WebRequest stubWebRequest = preProcess();
		CaaersServiceResponse caaersServiceResponse = Helper.createResponse();

		if (operation.equals(AdverseEventManagementServiceImpl.CREATE)
				|| operation.equals(AdverseEventManagementServiceImpl.UPDATE)) {
			if (adverseEventsInputMessage.getAdverseEvents() == null) {
				return Helper.populateError(caaersServiceResponse, "WS_AEMS_025", messageSource.getMessage(
						"WS_AEMS_025", new String[] {}, "", Locale.getDefault()));
			}
		}

		Criteria criteria = adverseEventsInputMessage.getCriteria();
		String studyIdentifier = criteria.getStudyIdentifier();
		String studySubjectIdentifier = criteria.getStudySubjectIdentifier();
		// fetch study
		Study dbStudy = fetchStudy(studyIdentifier, caaersServiceResponse);
		if (dbStudy == null) {
			//response must be populated with appropriate errors that lead to one of these being null
			return caaersServiceResponse;
		}
		// fetch participant
		Participant dbParticipant = fetchParticipant(studySubjectIdentifier, dbStudy.getId(), caaersServiceResponse);
		if (dbParticipant == null) {
			//response must be populated with appropriate errors that lead to one of these being null
			return caaersServiceResponse;
		}
		
		// fetch study participant assignment
		StudyParticipantAssignment assignment = fetchAssignment(caaersServiceResponse, studyIdentifier,
				studySubjectIdentifier, dbStudy, dbParticipant);
		if (assignment == null) {
			//response must be populated with appropriate errors that lead to one of these being null
			return caaersServiceResponse;
		}

		// fetch AE reporting period
		AdverseEventReportingPeriod adverseEventReportingPeriod = fetchAEReportingPeriod(criteria, assignment,
				operation, caaersServiceResponse);

		// set default response message
		caaersServiceResponse.getServiceResponse().setMessage("Completed the request processing sucessfully");

		if (adverseEventReportingPeriod == null) {
			//response must be populated with appropriate errors that lead to one of these being null
			return caaersServiceResponse;
		}
		
		AeTerminology terminology = dbStudy.getAeTerminology();
		
		//fetch AEs in DB
		List<AdverseEvent> dbAdverseEvents = null;
		try {
			dbAdverseEvents = getAdverseEventDao().getByAssignment(
					adverseEventReportingPeriod.getAssignment());
		} catch (Exception e) {
			String message = messageSource.getMessage("WS_AEMS_069", new String[] { studyIdentifier, studySubjectIdentifier }, "", Locale
					.getDefault());
			logger.error(message, e);
			Helper.populateError(caaersServiceResponse, "WS_AEMS_069", message);
			return caaersServiceResponse;
		}
		// process adverse events ...

		// handle delete adverse events
		if (operation.equals(AdverseEventManagementServiceImpl.DELETE)) {
			handleAEsDeletion(adverseEventsInputMessage, terminology, adverseEventReportingPeriod, dbAdverseEvents, 
					caaersServiceResponse);
			return caaersServiceResponse;
		}

		// proceeds further only to handle CREATE or UPDATE
		AdverseEvents xmlAdverseEvents = adverseEventsInputMessage.getAdverseEvents();
		Map<Integer, AdverseEvent> aesToSave = new HashMap<Integer, AdverseEvent>();

		// process and build list of AEs to be saved
		processAEsToSave(xmlAdverseEvents, adverseEventReportingPeriod, terminology, aesToSave, operation, dbStudy,
				dbAdverseEvents, caaersServiceResponse);

		if (operation.equals(CREATE) || operation.equals(UPDATE)) {
			saveAEs(aesToSave, adverseEventReportingPeriod, operation, caaersServiceResponse);
		}

		// FIRE SAE RULES ..
		fireSAERules(adverseEventReportingPeriod, dbStudy, caaersServiceResponse);

		return caaersServiceResponse;
	}

	/**
	 * @param adverseEventReportingPeriod
	 * @param dbStudy
	 * @param caaersServiceResponse
	 */
	private void fireSAERules(AdverseEventReportingPeriod adverseEventReportingPeriod, Study dbStudy,
			CaaersServiceResponse caaersServiceResponse) {

		for (AdverseEvent ae : adverseEventReportingPeriod.getAdverseEvents()) {
			String sae = "";

			try {

				sae = adverseEventEvaluationService.assesAdverseEvent(ae, dbStudy);
				if (sae.equals("SERIOUS_ADVERSE_EVENT")) {
					caaersServiceResponse.getServiceResponse().setMessage(
							"Rules enabled for this study , Reporting required for exported Adverse Events");
				}

			} catch (Exception e) {
				Helper.populateError(caaersServiceResponse, "WS_GEN_000", "Error :  " + e.getMessage());
			}
			/*
			 * try { notifyStudyPersonnel(assignment);
			 * messages.add("Email Sent .."); } catch (MailException e) {
			 * messages
			 * .add("Error sending email to Study Personnel . "+e.getMessage());
			 * adverseEventResponse.setMessage(messages);
			 * caaersServiceResponse.setResponse(adverseEventResponse); return
			 * caaersServiceResponse; }
			 */

		}
	}

	/**
	 * @param aesToSave
	 * @param adverseEventReportingPeriod
	 * @param operation
	 * @param caaersServiceResponse
	 */
	private void saveAEs(Map<Integer, AdverseEvent> aesToSave, AdverseEventReportingPeriod adverseEventReportingPeriod,
			String operation, CaaersServiceResponse caaersServiceResponse) {

		Set<Entry<Integer, AdverseEvent>> entries = aesToSave.entrySet();

		// perform saveAEs
		for (Entry<Integer, AdverseEvent> pairs : entries) {

			AdverseEvent adverseEvent = (AdverseEvent) pairs.getValue();

			// get term
			String term = "";
			if (adverseEvent.getAdverseEventTerm() != null) {
				term = adverseEvent.getAdverseEventTerm().getFullName();
			} else {
				term = adverseEvent.getDetailsForOther();
			}

			ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();

			aeReport.addAdverseEventUnidirectional(adverseEvent);

			ValidationErrors errors = fireRules(aeReport, "gov.nih.nci.cabig.caaers.rules.reporting_basics_section");
			if (errors.getErrorCount() > 0) {
				// FIXME: do we need this
				Helper.populateError(caaersServiceResponse, "WS_GEN_000",
				 "Unable to process the request");
				String valErrmsg = null;
				for (ValidationError error : errors.getErrors()) {
					valErrmsg = error.getMessage() + " (" + term + ")";
					Helper.populateErrorOutcome(caaersServiceResponse, null, null, pairs.getKey() + "", Arrays
							.asList(new String[] { valErrmsg }));
				}
			} else {
				// SAVE AE				
				adverseEventReportingPeriod.addAdverseEvent(adverseEvent);

				try {
					adverseEventReportingPeriodDao.save(adverseEventReportingPeriod);
				} catch (Exception e) {
					String message = messageSource.getMessage("WS_AEMS_061", new String[] { term, e.getMessage() }, "",
							Locale.getDefault());
					Helper.populateErrorOutcome(caaersServiceResponse, null, adverseEvent.getId().toString(), pairs
							.getKey().toString(), Arrays.asList(new String[] { message }));
				}
			}
			String message = messageSource.getMessage("WS_AEMS_006", new String[] { term, operation + "d" }, "", Locale
					.getDefault());
			ProcessingOutcome po = Helper.createOutcome(AdverseEvent.class, null, false, message);
			Helper.populateProcessingOutcome(caaersServiceResponse, po);
		}// end of for

	}

	/**
	 * @param xmlAdverseEvents
	 * @param adverseEventReportingPeriod
	 * @param terminology
	 * @param aesToSave
	 * @param operation
	 * @param dbStudy
	 * @param caaersServiceResponse
	 */
	private void processAEsToSave(AdverseEvents xmlAdverseEvents,
			AdverseEventReportingPeriod adverseEventReportingPeriod, AeTerminology terminology,
			Map<Integer, AdverseEvent> aesToSave, String operation, Study dbStudy, List<AdverseEvent> dbAdverseEvents,
			CaaersServiceResponse caaersServiceResponse) {
		int index = 0;
		for (AdverseEventType adverseEventType : xmlAdverseEvents.getAdverseEvent()) {
			index++;
			try {

				AdverseEvent adverseEvent = processAdverseEvent(adverseEventType, adverseEventReportingPeriod,
						operation, dbStudy, terminology, dbAdverseEvents);
				
				Set<ConstraintViolation<AdverseEvent>> constraintViolations = validator.validate(adverseEvent, AdverseEventGroup.class, Default.class);
				convertConstraintViolationsToProcessOutcomes(
						adverseEvent, String.valueOf(index), constraintViolations, caaersServiceResponse);
				// build list of aes that can be saved ...
				if(constraintViolations.isEmpty()) {
					aesToSave.put(index, adverseEvent);
				}
			} catch (CaaersSystemException e) {
				Helper.populateErrorOutcome(caaersServiceResponse, null, null, (index) + "", Arrays
						.asList(new String[] { e.getMessage() }));
			}
		}
	}
	
	/**
	 * @param adverseEventsInputMessage
	 * @param caaersServiceResponse
	 * @param terminology
	 * @param adverseEventReportingPeriod
	 */
	private void handleAEsDeletion(AdverseEventsInputMessage adverseEventsInputMessage, AeTerminology terminology,
			AdverseEventReportingPeriod adverseEventReportingPeriod, List<AdverseEvent> dbAdverseEvents, 
			CaaersServiceResponse caaersServiceResponse) {

		String errorCode = "";

		AdverseEvents adverseEvents = adverseEventsInputMessage.getAdverseEvents();

		for (AdverseEventType aeType : adverseEvents.getAdverseEvent()) {
			AdverseEvent adverseEvent = null;
			String code = null;

			try {
				if (aeType.getId() != null) {
					code = String.valueOf(aeType.getId());
					adverseEvent = this.getAdverseEventDao().getById(aeType.getId().intValue());
				} else if (aeType.getCtepCode() != null) {
					code = aeType.getCtepCode();
					adverseEvent = checkIfCtcTermExists(adverseEventReportingPeriod, aeType.getCtepCode(), terminology
							.getCtcVersion(), dbAdverseEvents);
				} else if (aeType.getAdverseEventMeddraLowLevelTerm() != null) {
					code = aeType.getAdverseEventMeddraLowLevelTerm().getMeddraCode();
					adverseEvent = checkIfMeddraTermExists(adverseEventReportingPeriod, aeType
							.getAdverseEventMeddraLowLevelTerm(), dbAdverseEvents);
				}
			} catch (Exception e) {				
				  String message = messageSource.getMessage("WS_AEMS_069", new
						  String[] { adverseEventsInputMessage.getCriteria().getStudyIdentifier()
						  , adverseEventsInputMessage.getCriteria().getStudySubjectIdentifier()},
						  "", Locale.getDefault());
				  logger.error(message, e);
				  Helper.populateError(caaersServiceResponse, "WS_AEMS_069", message);
				 
			}
			
			try {
				if (adverseEvent != null) {
					String message = "";
					if (adverseEvent.getSolicited()) {
						message = messageSource.getMessage("WS_AEMS_024", new String[] { code }, "", Locale
								.getDefault());
						errorCode = "WS_AEMS_024";
						Helper.populateError(caaersServiceResponse, errorCode, message);
					} else {
						deleteAdverseEvent(adverseEvent, adverseEventReportingPeriod);
						message = messageSource.getMessage("WS_AEMS_006", new String[] {
								adverseEvent.getAdverseEventTerm().getFullName(), "deleted" }, "", Locale.getDefault());
						errorCode = "WS_AEMS_006";
					
						ProcessingOutcome po = Helper.createOutcome(AdverseEvent.class, adverseEvent.getId().toString(), false, message);
						Helper.populateProcessingOutcome(caaersServiceResponse, po);
					}
				}
			} catch (Exception e) {				
				 String message = messageSource.getMessage("WS_AEMS_060", new
						 String[] { e.getMessage() }, "", Locale.getDefault());
				  logger.error(message, e);
				  Helper.populateError(caaersServiceResponse, "WS_AEMS_060", message);				 
			}
		}// end of for
	}

	
	private void deleteAdverseEvent(AdverseEvent adverseEvent, AdverseEventReportingPeriod adverseEventReportingPeriod) {
		List<AdverseEvent> dbAdverseEvents = adverseEventReportingPeriod.getAdverseEvents();

		for (Iterator<AdverseEvent> iterator = dbAdverseEvents.iterator(); iterator.hasNext();) {
			AdverseEvent dbAE = iterator.next();
			if (dbAE.getId() == adverseEvent.getId()) {
				adverseEventIndexDao.deleteByEntityIdColumn(dbAE.getId() + "");
				iterator.remove();
			}
		}
		adverseEventReportingPeriod.setAdverseEvents(dbAdverseEvents);

		adverseEventReportingPeriodDao.save(adverseEventReportingPeriod);
	}

	
	/**
	 * @param caaersServiceResponse
	 * @param studyIdentifier
	 * @param studySubjectIdentifier
	 * @param dbStudy
	 * @param dbParticipant
	 * @return StudyParticipantAssignment
	 */
	private StudyParticipantAssignment fetchAssignment(CaaersServiceResponse caaersServiceResponse,
			String studyIdentifier, String studySubjectIdentifier, Study dbStudy, Participant dbParticipant) {
		StudyParticipantAssignment assignment = null;
		
		try {
			assignment = studyParticipantAssignmentDao.getAssignment(dbParticipant, dbStudy);
			if (assignment != null) {
				logger.info("Participant is  assigned to Study");
			} else {
				String message = messageSource.getMessage("WS_AEMS_005", new String[] { studySubjectIdentifier,
						studyIdentifier }, "", Locale.getDefault());
				logger.error(message);
				Helper.populateError(caaersServiceResponse, "WS_AEMS_005", message);
			}
		} catch (Exception e) {
			String message = messageSource.getMessage("WS_AEMS_063", new String[] { studyIdentifier, studySubjectIdentifier }, "", Locale
					.getDefault());
			logger.error(message, e);
			Helper.populateError(caaersServiceResponse, "WS_AEMS_063", message);
			assignment = null;
		}
		return assignment;
	}

	private Participant fetchParticipant(String studySubjectIdentifier, Integer studyIdentifier, CaaersServiceResponse caaersServiceResponse) {
		if (studySubjectIdentifier == null) {
			String message = messageSource.getMessage("WS_AEMS_032", new String[] { studySubjectIdentifier }, "", Locale
					.getDefault());
			logger.error(message);
			Helper.populateError(caaersServiceResponse, "WS_AEMS_032", message);
			return null;
		}
		
		if (studyIdentifier == null) {
			String message = messageSource.getMessage("WS_AEMS_034", new String[] { studyIdentifier.toString() }, "", Locale
					.getDefault());
			logger.error(message);
			Helper.populateError(caaersServiceResponse, "WS_AEMS_034", message);
			return null;
		}
		
		Participant dbParticipant = null;
		try {
			ParticipantQuery pq = new ParticipantQuery();
			pq.joinStudy();
			pq.filterByStudySubjectIdentifier(studySubjectIdentifier, "=");
			pq.filterByStudyId(studyIdentifier, "=");
			
			logger.error("AE part >>> " + pq.getQueryString());
			List<Participant> dbParticipants = participantDao.searchParticipant(pq);			
			if (dbParticipants != null && dbParticipants.size() == 1) {
				logger.info("Participant exists in caAERS");
				dbParticipant = dbParticipants.get(0);
			} else {
				String message = messageSource.getMessage("WS_AEMS_005", new String[] { studySubjectIdentifier, studyIdentifier.toString() }, "", Locale
						.getDefault());
				logger.error(message);
				Helper.populateError(caaersServiceResponse, "WS_AEMS_005", message);				
			}
			
		} catch (Exception e) {
			String message = messageSource.getMessage("WS_AEMS_002", new String[] { e.getMessage() }, "", Locale
					.getDefault());
			logger.error("Participant Criteria to ParticipantDomain Conversion Failed ", e);
			Helper.populateError(caaersServiceResponse, "WS_AEMS_002", message);
			dbParticipant = null;
		}
		return dbParticipant;
	}

	private Study fetchStudy(String identifier, CaaersServiceResponse caaersServiceResponse) {

		if (identifier == null) {
			String message = messageSource.getMessage("WS_AEMS_034", new String[] { identifier }, "", Locale
					.getDefault());
			logger.error(message);
			Helper.populateError(caaersServiceResponse, "WS_AEMS_034", message);
			return null;
		}
		Study dbStudy = null;
		try {
			dbStudy = fetchStudy(identifier);
			if (dbStudy != null) {
				logger.info("Study exists in caAERS");
			} else {
				String message = messageSource.getMessage("WS_AEMS_003", new String[] { identifier }, "", Locale
						.getDefault());
				logger.error("Study Does not exist ");
				Helper.populateError(caaersServiceResponse, "WS_AEMS_003", message);				
			}
			List<StudySearchableAjaxableDomainObject> authorizedStudies = getAuthorizedStudies(identifier);
			if (authorizedStudies.size() == 0) {
				String message = messageSource.getMessage("WS_AEMS_027", new String[] { identifier }, "", Locale
						.getDefault());
				Helper.populateError(caaersServiceResponse, "WS_AEMS_027", message);
			}
		} catch (Exception e) {
			String message = messageSource.getMessage("WS_AEMS_004", new String[] { e.getMessage() }, "", Locale
					.getDefault());
			logger.error("Study Criteria to StudyDomain Conversion Failed ", e);
			Helper.populateError(caaersServiceResponse, "WS_AEMS_004", message);
			dbStudy = null;
		}
		return dbStudy;
	}

	
	private Study fetchStudy(String identifier) {
		Identifier si = new Identifier();
		si.setValue(identifier);
		return studyDao.getByIdentifier(si);
	}

	
	/**
	 * @param criteria
	 * @param assignment
	 * @param operation
	 * @param caaersServiceResponse
	 * @return AdverseEventReportingPeriod
	 */
	private AdverseEventReportingPeriod fetchAEReportingPeriod(Criteria criteria,
			StudyParticipantAssignment assignment, String operation, CaaersServiceResponse caaersServiceResponse) {
		AdverseEventReportingPeriod adverseEventReportingPeriod = null;
		if (criteria.getCourse() != null) {
			try {
				adverseEventReportingPeriod = fetchReportingPeriod(criteria, assignment, operation);
			} catch (CaaersSystemException e) {
				logger.error(e);
				Helper.populateError(caaersServiceResponse, e.getErrorCode(), e.getMessage());
			}
		}
		return adverseEventReportingPeriod;
	}
	
	private AdverseEventReportingPeriod fetchReportingPeriod(Criteria criteria, StudyParticipantAssignment assignment,
			String operation) throws CaaersSystemException {
		AdverseEventReportingPeriod adverseEventReportingPeriod = null;

		if (criteria.getCourse() == null) {			
			throw new CaaersSystemException("WS_AEMS_065", 
					messageSource.getMessage("WS_AEMS_065",
					new String[] { }, "", Locale.getDefault()));
		}
		
		Date xmlStartDate = criteria.getCourse().getStartDateOfThisCourse().toGregorianCalendar().getTime();
		Date xmlEndDate = null;
		if (criteria.getCourse().getEndDateOfThisCourse() != null) {
			xmlEndDate = criteria.getCourse().getEndDateOfThisCourse().toGregorianCalendar().getTime();
		}

		List<AdverseEventReportingPeriod> rPeriodList;
		try {
			rPeriodList = adverseEventReportingPeriodDao.getByAssignment(assignment);
			for (AdverseEventReportingPeriod aerp : rPeriodList) {
				Date sDate = aerp.getStartDate();
				Date eDate = aerp.getEndDate();

				if (DateUtils.compateDateAndTime(xmlStartDate, sDate) == 0) {
					if ((xmlEndDate != null && DateUtils.compateDateAndTime(xmlEndDate, eDate) == 0)
							|| (xmlEndDate == null && eDate == null)) {
						adverseEventReportingPeriod = aerp;
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new CaaersSystemException("WS_AEMS_062", 
					messageSource.getMessage("WS_AEMS_062",
					new String[] { criteria.getStudyIdentifier(), criteria.getStudySubjectIdentifier() }, "", Locale
							.getDefault()));
		}

		// incase of update , the reporting period should be existing in database .

		Study study = assignment.getStudySite().getStudy();
		if (adverseEventReportingPeriod == null) {

			if (operation.equals(DELETE)) {
				throw new CaaersSystemException("WS_AEMS_008", messageSource.getMessage("WS_AEMS_008",
						new String[] {}, "", Locale.getDefault()));
			}
			if (criteria.getCourse().getStartDateOfFirstCourse() != null) {
				assignment.setStartDateOfFirstCourse(xmlStartDate);
			}
			// create one
			adverseEventReportingPeriod = new AdverseEventReportingPeriod();
			
			adverseEventReportingPeriod.setStartDate(xmlStartDate);
			adverseEventReportingPeriod.setEndDate(xmlEndDate);

			if (criteria.getCourse().getCycleNumber() != null) {
				adverseEventReportingPeriod.setCycleNumber(criteria.getCourse().getCycleNumber().intValue());
			}

			if (criteria.getCourse().getTreatmentAssignmentCode() == null) {
				adverseEventReportingPeriod.setTreatmentAssignmentDescription(criteria.getCourse()
						.getOtherTreatmentAssignmentDescription());
			} else {
				TreatmentAssignment ta = resolveTreamtmentAssignment(criteria, study);
				if (ta != null) {
					adverseEventReportingPeriod.setTreatmentAssignment(ta);
				} else {
					throw new CaaersSystemException("WS_AEMS_009", messageSource.getMessage("WS_AEMS_009",
							new String[] { criteria.getCourse().getTreatmentAssignmentCode() }, "", Locale
									.getDefault()));
				}
			}

			List<Epoch> epochs = study.getEpochs();
			Epoch epochToSave = null;

			if (StringUtils.isNotEmpty(criteria.getCourse().getTreatmentType())) {
				for (Epoch epoch : epochs) {
					if (epoch.getName().equals(criteria.getCourse().getTreatmentType())) {
						epochToSave = epoch;
						break;
					}
				}
				
				// CAAERS-2813
				if (epochToSave == null) {
					throw new CaaersSystemException("WS_AEMS_010", messageSource.getMessage("WS_AEMS_010",
							new String[] { criteria.getCourse().getTreatmentType() }, "", Locale.getDefault()));
				}
			}
			

			if (epochToSave != null) {
				adverseEventReportingPeriod.setEpoch(epochToSave);
			}
			//
			/*
			 * if (epochToSave == null) { throw new
			 * CaaersSystemException(messageSource.getMessage("WS_AEMS_010",
			 * new
			 * String[]{criteria.getCourse().getTreatmentType()},"",Locale
			 * .getDefault())); } else {
			 * adverseEventReportingPeriod.setEpoch(epochToSave); }
			 */

			adverseEventReportingPeriod.setAssignment(assignment);
			Map<String, String> errors = validateRepPeriodDates(adverseEventReportingPeriod, rPeriodList,
					assignment.getStartDateOfFirstCourse());
			if (errors.size() == 0) {
				try {
					studyParticipantAssignmentDao.save(assignment);
				} catch (Exception e) {
					throw new CaaersSystemException("WS_AEMS_067", messageSource.getMessage("WS_AEMS_067",
							new String[] { }, "", Locale.getDefault()));
				}
				try {
					adverseEventReportingPeriodDao.save(adverseEventReportingPeriod);
				} catch (Exception e) {
					throw new CaaersSystemException("WS_AEMS_068", messageSource.getMessage("WS_AEMS_068",
							new String[] { }, "", Locale.getDefault()));
				}
				
			} else {
				String keys[] = errors.keySet().toArray(new String[] {});
				throw new CaaersSystemException(keys[0], errors.get(keys[0]));
			}
		} else {
			//
			adverseEventReportingPeriodService.synchronizeReports(adverseEventReportingPeriod);
		}
			
		return adverseEventReportingPeriod;
	}
	
	private AdverseEvent processAdverseEvent(AdverseEventType xmlAdverseEvent,
			AdverseEventReportingPeriod adverseEventReportingPeriod, String operation, Study dbStudy,
			AeTerminology terminology, List<AdverseEvent> dbAdverseEvents) throws CaaersSystemException {
		logger.info("Entering processAdverseEvent() in AdverseEventManagementServiceImpl");

		AdverseEvent adverseEvent = null;

		boolean adeersReportingRequired = dbStudy.getAdeersReporting();
		if (adeersReportingRequired) {
			if (xmlAdverseEvent.getOutcome().size() > 0) {
				throw new CaaersSystemException("WS_AEMS_011", messageSource.getMessage("WS_AEMS_011",
						new String[] { "Outcomes" }, "", Locale.getDefault()));
			}
			if (xmlAdverseEvent.getEventApproximateTime() != null) {
				throw new CaaersSystemException("WS_AEMS_011", messageSource.getMessage("WS_AEMS_011",
						new String[] { "EventApproximateTime" }, "", Locale.getDefault()));
			}
			if (xmlAdverseEvent.getEventLocation() != null) {
				throw new CaaersSystemException("WS_AEMS_011", messageSource.getMessage("WS_AEMS_011",
						new String[] { "EventLocation" }, "", Locale.getDefault()));
			}
		}
		// if update get the adverse event to update ..
		
		if (xmlAdverseEvent.getId() != null) {
			adverseEvent = getAdverseEventDao().getById(xmlAdverseEvent.getId().intValue());
		} else if (xmlAdverseEvent.getCtepCode() != null) {
			adverseEvent = checkIfCtcTermExists(adverseEventReportingPeriod, xmlAdverseEvent.getCtepCode(), terminology
					.getCtcVersion(), dbAdverseEvents);
		} else if (xmlAdverseEvent.getAdverseEventMeddraLowLevelTerm() != null) {
			adverseEvent = checkIfMeddraTermExists(adverseEventReportingPeriod, xmlAdverseEvent
					.getAdverseEventMeddraLowLevelTerm(), dbAdverseEvents);
		}
		
		String operationOnThisAE = operation;
		if (operation.equals(UPDATE)) {
			// if doesn't exist create ...
			if (adverseEvent == null) {
				adverseEvent = new AdverseEvent();
				adverseEvent.setReportingPeriod(adverseEventReportingPeriod);
				operationOnThisAE = CREATE;
			}
		} else if (operation.equals(CREATE)) {
			// if AE exists , should not be able to create AE with same term ...
			if (adverseEvent == null) {
				adverseEvent = new AdverseEvent();
				adverseEvent.setReportingPeriod(adverseEventReportingPeriod);
				operationOnThisAE = CREATE;
			} else {
				String term = adverseEvent.getAdverseEventTerm().getFullName();
				throw new CaaersSystemException("WS_AEMS_012", messageSource.getMessage("WS_AEMS_012",
						new String[] { term }, "", Locale.getDefault()));
			}
		}
		/*
		 * if (operation.equals(DELETE)) { adverseEvent =
		 * checkIfAEExists(adverseEventReportingPeriod , xmlAdverseEvent); if
		 * (adverseEvent == null) { throw new
		 * CaaersSystemException(messageSource.getMessage("WS_AEMS_013", new
		 * String[]{},"",Locale.getDefault())); } return adverseEvent; }
		 */
		try {
			adverseEventConverter.convertAdverseEventDtoToAdverseEventDomain(xmlAdverseEvent, adverseEvent,
					terminology, adverseEventReportingPeriod.getAssignment().getStartDateOfFirstCourse(),
					operationOnThisAE);
		} catch (CaaersSystemException caEX) {
			throw new CaaersSystemException(caEX);
		}
		return adverseEvent;
	}
	
	private AdverseEvent checkIfCtcTermExists(AdverseEventReportingPeriod adverseEventReportingPeriod, String ctepCode,
			Ctc ctc, List<AdverseEvent> dbAdverseEvents) {
		AdverseEvent adverseEvent = null;
		
		for (AdverseEvent dbAdverseEvent : dbAdverseEvents) {
			if (matchAdverseEventForReportingPeriod(dbAdverseEvent, adverseEventReportingPeriod)) {
				if (dbAdverseEvent.getAdverseEventTerm() instanceof AdverseEventCtcTerm) {
					AdverseEventCtcTerm aeCtcTerm = (AdverseEventCtcTerm) dbAdverseEvent.getAdverseEventTerm();
					if (aeCtcTerm.getCtcTerm().getCtepCode().equals(ctepCode)
							&& aeCtcTerm.getCtcTerm().getCategory().getCtc().getId().equals(ctc.getId())) {
						adverseEvent = dbAdverseEvent;
						break;
					}
				}
			}// end of match
		}// end of for
		return adverseEvent;
	}

	private AdverseEvent checkIfMeddraTermExists(AdverseEventReportingPeriod adverseEventReportingPeriod,
			AdverseEventMeddraLowLevelTermType xmlAdverseEventMeddraLowLevelTerm, List<AdverseEvent> dbAdverseEvents) {
		AdverseEvent adverseEvent = null;

		for (AdverseEvent dbAdverseEvent : dbAdverseEvents) {
			if (matchAdverseEventForReportingPeriod(dbAdverseEvent, adverseEventReportingPeriod)) {
				if (dbAdverseEvent.getAdverseEventTerm() instanceof AdverseEventMeddraLowLevelTerm) {
					AdverseEventMeddraLowLevelTerm aeMeddraLowLevelTerm = (AdverseEventMeddraLowLevelTerm) dbAdverseEvent
							.getAdverseEventTerm();
					if (aeMeddraLowLevelTerm.getLowLevelTerm().getMeddraCode().equals(
							xmlAdverseEventMeddraLowLevelTerm.getMeddraCode())
							&& aeMeddraLowLevelTerm.getLowLevelTerm().getMeddraTerm().equals(
									xmlAdverseEventMeddraLowLevelTerm.getMeddraTerm())) {
						adverseEvent = dbAdverseEvent;
						break;
					}
				}
			}// end of match
		}// end of for
		return adverseEvent;
	}
	
	private boolean matchAdverseEventForReportingPeriod(AdverseEvent dbAdverseEvent,
			AdverseEventReportingPeriod adverseEventReportingPeriod) {
		boolean match = false;
		if (DateUtils.compateDateAndTime(adverseEventReportingPeriod.getStartDate(), dbAdverseEvent
				.getReportingPeriod().getStartDate()) == 0) {
			if ((adverseEventReportingPeriod.getEndDate() != null && DateUtils.compateDateAndTime(
					adverseEventReportingPeriod.getEndDate(), dbAdverseEvent.getReportingPeriod().getEndDate()) == 0)
					|| (adverseEventReportingPeriod.getEndDate() == null && dbAdverseEvent.getReportingPeriod()
							.getEndDate() == null)) {
				match = true;
			}// end date compare
		}// st date compare
		return match;
	}

	private Map<String, String> validateRepPeriodDates(AdverseEventReportingPeriod rPeriod,
			List<AdverseEventReportingPeriod> rPeriodList, Date firstCourseDate) {

		Date startDate = rPeriod.getStartDate();
		Date endDate = rPeriod.getEndDate();
		Map<String, String> errors = new HashMap<String, String>();

		// Check if the start date is equal to or before the end date.
		if (firstCourseDate != null && startDate != null && (firstCourseDate.getTime() - startDate.getTime() > 0)) {
			errors.put("WS_AEMS_014", messageSource.getMessage("WS_AEMS_014", new String[] {}, "", Locale
							.getDefault()));
		}

		if (startDate != null && endDate != null && (endDate.getTime() - startDate.getTime() < 0)) {
			errors.put("WS_AEMS_015", messageSource.getMessage("WS_AEMS_015", new String[] {}, "", Locale
							.getDefault()));
		}

		// Check if the start date is equal to end date.
		// This is allowed only for Baseline reportingPeriods and not for other
		// reporting periods.

		if (rPeriod.getEpoch() != null && !rPeriod.getEpoch().getName().equals("Baseline")) {
			if (endDate != null && startDate.equals(endDate)) {
				errors.put("WS_AEMS_016", messageSource.getMessage("WS_AEMS_016", new String[] {}, "", Locale
						.getDefault()));
			}

		}

		// Check if the start date - end date for the reporting Period overlaps
		// with the date range of an existing Reporting Period.
		for (AdverseEventReportingPeriod aerp : rPeriodList) {
			Date sDate = aerp.getStartDate();
			Date eDate = aerp.getEndDate();

			if (!aerp.getId().equals(rPeriod.getId())) {

				// we should make sure that no existing Reporting Period, start
				// date falls, in-between these dates.
				if (startDate != null && endDate != null) {
					if (DateUtils.compareDate(sDate, startDate) >= 0 && DateUtils.compareDate(sDate, endDate) < 0) {
						errors.put("WS_AEMS_017", messageSource.getMessage("WS_AEMS_017", new String[] {}, "", Locale
								.getDefault()));
						break;
					}
				} else if (startDate != null && DateUtils.compareDate(sDate, startDate) == 0) {
					errors.put("WS_AEMS_017", messageSource.getMessage("WS_AEMS_017", new String[] {}, "", Locale
							.getDefault()));
					break;
				}

				// newly created reporting period start date, should not fall
				// within any other existing reporting periods
				if (sDate != null && eDate != null) {
					if (DateUtils.compareDate(sDate, startDate) <= 0 && DateUtils.compareDate(startDate, eDate) < 0) {
						errors.put("WS_AEMS_017", messageSource.getMessage("WS_AEMS_017", new String[] {}, "", Locale
								.getDefault()));
						break;
					}
				} else if (sDate != null && DateUtils.compareDate(sDate, startDate) == 0) {
					errors.put("WS_AEMS_017", messageSource.getMessage("WS_AEMS_017", new String[] {}, "", Locale
							.getDefault()));
					break;
				}
			}

			// If the epoch of reportingPeriod is not - Baseline , then it
			// cannot be earlier than a Baseline
			if (rPeriod.getEpoch() != null && rPeriod.getEpoch().getName().equals("Baseline")) {
				if (!aerp.getEpoch().getName().equals("Baseline")) {
					if (DateUtils.compareDate(sDate, startDate) < 0) {
						errors.put("WS_AEMS_018", messageSource.getMessage("WS_AEMS_018", new String[] {}, "", Locale
								.getDefault()));
						return errors;
					}
				}
			} else {
				if (aerp.getEpoch() != null && aerp.getEpoch().getName().equals("Baseline")) {
					if (DateUtils.compareDate(startDate, sDate) < 0) {
						errors.put("WS_AEMS_019", messageSource.getMessage("WS_AEMS_019", new String[] {}, "", Locale
								.getDefault()));
						return errors;
					}
				}
			}

		}
		return errors;

	}
	
	private ValidationErrors fireRules(ExpeditedAdverseEventReport aeReport, String bindUri) {

		List<Object> input = new ArrayList<Object>();
		input.add(aeReport);
		ValidationErrors errors = new ValidationErrors();
		input.add(errors);

		List<Object> output = executionService.fireRules(bindUri, input);
		errors = retrieveValidationErrors(output);
		return errors;
	}

	private ValidationErrors retrieveValidationErrors(List<Object> list) {
		for (Object o : list)
			if (o instanceof ValidationErrors)
				return (ValidationErrors) o;
		return null;
	}
	
	private void convertConstraintViolationsToProcessOutcomes( 
			AdverseEvent adverseEvent, String index, Set<ConstraintViolation<AdverseEvent>> constraintViolations, 
			CaaersServiceResponse caaersServiceResponse) {
		String valErrmsg = null;
		for (ConstraintViolation violation : constraintViolations) {
			valErrmsg = violation.getMessage() 
				+ " (" + violation.getPropertyPath() 
				+ ") in " + adverseEvent.getClass().getSimpleName() 
				+ "(" + adverseEvent.getDisplayName() + ")";
			Helper.populateErrorOutcome(caaersServiceResponse, null, null, index, Arrays
					.asList(new String[] { valErrmsg }));
		}
	}
	
	private void convertConstraintViolationsToProcessOutcomes( 
			ExternalAdverseEvent adverseEvent, String index, Set<ConstraintViolation<ExternalAdverseEvent>> constraintViolations, 
			CaaersServiceResponse caaersServiceResponse) {
		String valErrmsg = null;
		for (ConstraintViolation violation : constraintViolations) {
			valErrmsg = violation.getMessage() 
				+ " (" + violation.getPropertyPath() 
				+ ") in " + adverseEvent.getClass().getSimpleName() 
				+ "(" + adverseEvent.getDisplayName() + ")";
			Helper.populateErrorOutcome(caaersServiceResponse, null, null, index, Arrays
					.asList(new String[] { valErrmsg }));
		}
	}
	
 	private void notifyStudyPersonnel(StudyParticipantAssignment assignment) throws MailException {
		List<String> emails = new ArrayList<String>();
		MailException mailException = null;
		List<StudyPersonnel> studyPersonnel = assignment.getStudySite().getStudyPersonnels();
		for (StudyPersonnel sp : studyPersonnel) {
			String email = sp.getEmailAddress();
			emails.add(email);
		}

		try {
			sendUserEmail(emails.toArray(new String[0]), "Reporting required for exported Adverse Events",
					"Reporting required for exported Adverse Events");
		} catch (MailException e) {
			mailException = e;
		}
		if (mailException != null)
			throw mailException;
	}

	public void sendUserEmail(String[] emailAddress, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emailAddress);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);

	}
	
	public void setAdverseEventDao(AdverseEventDao adverseEventDao) {
		this.adverseEventDao = adverseEventDao;
	}

	public void setExecutionService(BusinessRulesExecutionServiceImpl executionService) {
		this.executionService = executionService;
	}

	public void setParticipantDao(ParticipantDao participantDao) {
		this.participantDao = participantDao;
	}

	public void setStudyDao(StudyDao studyDao) {
		this.studyDao = studyDao;
	}

	public AdverseEventDao getAdverseEventDao() {
		return adverseEventDao;
	}

	public BusinessRulesExecutionServiceImpl getExecutionService() {
		return executionService;
	}

	public void setStudyParticipantAssignmentDao(StudyParticipantAssignmentDao studyParticipantAssignmentDao) {
		this.studyParticipantAssignmentDao = studyParticipantAssignmentDao;
	}

	
	public void setAdverseEventConverter(AdverseEventConverter adverseEventConverter) {
		this.adverseEventConverter = adverseEventConverter;
	}

	public void setAdverseEventReportingPeriodDao(AdverseEventReportingPeriodDao adverseEventReportingPeriodDao) {
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}

	public void setTreatmentAssignmentDao(TreatmentAssignmentDao treatmentAssignmentDao) {
		this.treatmentAssignmentDao = treatmentAssignmentDao;
	}

	public void setAdverseEventEvaluationService(AdverseEventEvaluationService adverseEventEvaluationService) {
		this.adverseEventEvaluationService = adverseEventEvaluationService;
	}

	public void setMailSender(final MailSender mailSender) {
		this.mailSender = mailSender;
	}

	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}

	private static String correlationStr(AdverseEventType adverseEventDto) {
		String code = null;
		if (adverseEventDto.getId() != null) {
			code = adverseEventDto.getId() + "";
		} else if (adverseEventDto.getCtepCode() != null) {
			code = adverseEventDto.getCtepCode();
		} else if (adverseEventDto.getAdverseEventMeddraLowLevelTerm() != null) {
			code = adverseEventDto.getAdverseEventMeddraLowLevelTerm().getMeddraCode();
		} else if (adverseEventDto.getVerbatim() != null) {
			code = adverseEventDto.getVerbatim();
		}

		return "Adverse Event with code , verbatim or ID : " + code;

	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	

	public void setAdverseEventIndexDao(AdverseEventIndexDao adverseEventIndexDao) {
		this.adverseEventIndexDao = adverseEventIndexDao;
	}

	public AdverseEventReportingPeriodService getAdverseEventReportingPeriodService() {
		return adverseEventReportingPeriodService;
	}

	
	public void setAdverseEventReportingPeriodService(
			AdverseEventReportingPeriodService adverseEventReportingPeriodService) {
		this.adverseEventReportingPeriodService = adverseEventReportingPeriodService;
	}

	
	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
	public void setExternalAdverseEventReportingPeriodDao(
			ExternalAdverseEventReportingPeriodDao externalAdverseEventReportingPeriodDao) {
		this.externalAdverseEventReportingPeriodDao = externalAdverseEventReportingPeriodDao;
	}

    public void setExternalAdverseEventDao(
            ExternalAdverseEventDao externalAdverseEventDao) {
        this.externalAdverseEventDao = externalAdverseEventDao;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public AdverseEventRoutingAndReviewRepository getAdverseEventRoutingAndReviewRepository() {
        return adverseEventRoutingAndReviewRepository;
    }

    public void setAdverseEventRoutingAndReviewRepository(AdverseEventRoutingAndReviewRepository adverseEventRoutingAndReviewRepository) {
        this.adverseEventRoutingAndReviewRepository = adverseEventRoutingAndReviewRepository;
    }

    /*
      * private Study processStudyCriteria(StudyType xmlStudy) throws
      * CaaersSystemException{logger.info(
      * "Entering processStudyCriteria() in AdverseEventManagementServiceImpl");
      *
      * Study study = new Study();
      *
      * try{ studyCriteriaConverter.convertStudyDtoToStudyDomain(xmlStudy,
      * study); }catch(CaaersSystemException caEX){ throw new
      * CaaersSystemException("Study Criteria to StudyDomain Conversion Failed ",
      * caEX); } return study; } private Participant
      * processParticipantCriteria(ParticipantType xmlParticipant) throws
      * CaaersSystemException{logger.info(
      * "Entering processParticipant() in AdverseEventManagementServiceImpl");
      *
      * Participant participant = new Participant();
      *
      * try{
      * participantCriteriaConverter.convertParticipantDtoToParticipantDomain
      * (xmlParticipant, participant); }catch(CaaersSystemException caEX){ throw
      * newCaaersSystemException(
      * "Participant Criteria to ParticipantDomain Conversion Failed ", caEX); }
      * return participant; }
      */
	
	/*
	 * private boolean enableAuthorization(boolean on) { AuthorizationSwitch sw
	 * = (AuthorizationSwitch)
	 * this.applicationContext.getBean("authorizationSwitch"); if (sw == null)
	 * throw new RuntimeException("Authorization switch not found"); boolean
	 * current = sw.isOn(); sw.setOn(on); return current; }
	 * 
	 * private void switchUser(String userName, String... roles) {
	 * GrantedAuthority[] authorities = new GrantedAuthority[roles.length]; for
	 * (int i = 0; i < roles.length; i++) { authorities[i] = new
	 * GrantedAuthorityImpl(roles[i]); } Authentication auth = new
	 * TestingAuthenticationToken(userName, "ignored", authorities);
	 * auth.setAuthenticated(true);
	 * SecurityContextHolder.getContext().setAuthentication(auth); }
	 */
	
	
	  /*
	  private AdverseEvent checkIfAEExists(AdverseEventReportingPeriod
	  adverseEventReportingPeriod , AdverseEventType xmlAdverseEvent){
	  AdverseEvent adverseEvent = null; List<AdverseEvent> dbAdverseEvents =
	  getAdverseEventDao
	  ().getByAssignment(adverseEventReportingPeriod.getAssignment()); for
	  (AdverseEvent dbAdverseEvent:dbAdverseEvents) { if
	  (DateUtils.compareDate(adverseEventReportingPeriod.getStartDate() ,
	  dbAdverseEvent.getReportingPeriod().getStartDate()) == 0) { if
	  ((adverseEventReportingPeriod.getEndDate() != null &&
	  DateUtils.compareDate
	  (adverseEventReportingPeriod.getEndDate(),dbAdverseEvent
	  .getReportingPeriod().getEndDate())==0) ||
	  (adverseEventReportingPeriod.getEndDate() == null &&
	  dbAdverseEvent.getReportingPeriod().getEndDate() == null) ) { //ctc or
	  meddra if (dbAdverseEvent.getAdverseEventTerm() instanceof
	  AdverseEventCtcTerm) { AdverseEventCtcTerm aeCtcTerm =
	  (AdverseEventCtcTerm)dbAdverseEvent.getAdverseEventTerm(); if
	  (aeCtcTerm.getCtcTerm
	  ().getTerm().equals(xmlAdverseEvent.getAdverseEventCtcTerm
	  ().getCtepTerm())) { adverseEvent = dbAdverseEvent; break; } } else if
	  (dbAdverseEvent.getAdverseEventTerm() instanceof
	  AdverseEventMeddraLowLevelTerm) { AdverseEventMeddraLowLevelTerm
	  aeMeddraLowLevelTerm =
	  (AdverseEventMeddraLowLevelTerm)dbAdverseEvent.getAdverseEventTerm(); if
	  (
	  aeMeddraLowLevelTerm.getLowLevelTerm().getMeddraCode().equals(xmlAdverseEvent
	  .getAdverseEventMeddraLowLevelTerm().getMeddraCode()) &&
	  aeMeddraLowLevelTerm
	  .getLowLevelTerm().getMeddraTerm().equals(xmlAdverseEvent
	  .getAdverseEventMeddraLowLevelTerm().getMeddraTerm()) ) { adverseEvent =
	  dbAdverseEvent; break; } } } } } return adverseEvent; }
	  */
	 
}
