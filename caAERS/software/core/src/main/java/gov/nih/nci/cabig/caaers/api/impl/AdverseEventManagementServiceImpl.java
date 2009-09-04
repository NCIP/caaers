package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.AbstractImportService;
import gov.nih.nci.cabig.caaers.api.AdverseEventManagementService;
import gov.nih.nci.cabig.caaers.dao.AdverseEventDao;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import gov.nih.nci.cabig.caaers.dao.TreatmentAssignmentDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.service.migrator.adverseevent.AdverseEventConverter;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventCtcTermType;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventMeddraLowLevelTermType;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventTerms;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventType;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEvents;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventsInputMessage;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.Criteria;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;

import com.semanticbits.rules.impl.BusinessRulesExecutionServiceImpl;

@WebService(endpointInterface="gov.nih.nci.cabig.caaers.api.AdverseEventManagementService", serviceName="AdverseEventManagementService")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public class AdverseEventManagementServiceImpl extends AbstractImportService implements AdverseEventManagementService , ApplicationContextAware {
	
	protected BusinessRulesExecutionServiceImpl executionService;
	protected AdverseEventDao adverseEventDao;
	protected ParticipantDao participantDao;
	protected StudyDao studyDao;
	protected StudyParticipantAssignmentDao studyParticipantAssignmentDao;
	protected AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	protected TreatmentAssignmentDao treatmentAssignmentDao;
	protected CtcTermDao ctcTermDao;
	
	private AdverseEventConverter adverseEventConverter;
	//private AdverseEventEvaluationService adverseEventEvaluationService;
	//private CaaersJavaMailSender caaersJavaMailSender;
	private ApplicationContext applicationContext;
	private MessageSource messageSource;
	
	private static Log logger = LogFactory.getLog(AdverseEventManagementServiceImpl.class);
	
	private CaaersServiceResponse saveAdverseEvent(AdverseEventsInputMessage adverseEventsInputMessage,String operation) {
		//boolean authorizationOnByDefault = enableAuthorization(false);
		//switchUser("SYSTEM_ADMIN", "ROLE_caaers_super_user");
		
		CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
		Response adverseEventResponse = new Response();
		if (operation.equals(AdverseEventManagementService.CREATE) || operation.equals(AdverseEventManagementService.UPDATE)) {
			if (adverseEventsInputMessage.getAdverseEvents() == null) {
				adverseEventResponse.setResponsecode("WS_AEMS_025");
				adverseEventResponse.setDescription(messageSource.getMessage("WS_AEMS_025", new String[]{},"",Locale.getDefault()));
				caaersServiceResponse.setResponse(adverseEventResponse);
				return caaersServiceResponse;
			}
		}
		//DomainObjectImportOutcome<AdverseEvent> adverseEventImportOutcome = null;
		//add participant to criteria
		Participant dbParticipant = null;
		Study dbStudy = null;
		List messages = new ArrayList();
		Criteria criteria = adverseEventsInputMessage.getCriteria();

		if (criteria.getStudyIdentifier() != null) {
			//if (criteria.getStudy() != null) {
			try {
				//Study study = processStudyCriteria(criteria.getStudy());
				dbStudy = fetchStudy(criteria.getStudyIdentifier());
        		if(dbStudy != null){
        			logger.info("Study Exists in caAERS");
        		}else{
       				String message = messageSource.getMessage("WS_AEMS_003", new String[]{criteria.getStudyIdentifier()},"",Locale.getDefault());
        			logger.error("Study Does not exist ");
    				//adverseEventImportOutcome.addErrorMessage("Study Does not exist in caAERS " , DomainObjectImportOutcome.Severity.ERROR);
    				adverseEventResponse.setResponsecode("WS_AEMS_003");
    				adverseEventResponse.setDescription(message);
    				caaersServiceResponse.setResponse(adverseEventResponse);
    				return caaersServiceResponse;
        		}
        		List<StudySearchableAjaxableDomainObject> authorizedStudies = getAuthorizedStudies(criteria.getStudyIdentifier());
        		if(authorizedStudies.size() == 0) {
        			String message = messageSource.getMessage("WS_AEMS_027", new String[]{criteria.getStudyIdentifier()},"",Locale.getDefault());
        			adverseEventResponse.setResponsecode("WS_AEMS_027");
    				adverseEventResponse.setDescription(message);
    				caaersServiceResponse.setResponse(adverseEventResponse);
    				return caaersServiceResponse;
        		}
			} catch (CaaersSystemException e){
				String message = messageSource.getMessage("WS_AEMS_004", new String[]{e.getMessage()},"",Locale.getDefault());
				//adverseEventImportOutcome = new DomainObjectImportOutcome<AdverseEvent>();
				logger.error("Study Criteria to StudyDomain Conversion Failed " , e);
				//adverseEventImportOutcome.addErrorMessage("Study Criteria to StudyDomain Conversion Failed " , DomainObjectImportOutcome.Severity.ERROR);
				adverseEventResponse.setResponsecode("WS_AEMS_004");
				adverseEventResponse.setDescription(message);
				caaersServiceResponse.setResponse(adverseEventResponse);
				return caaersServiceResponse;
			}
		}
		
		if (criteria.getParticipantIdentifier() != null) {
			//if (criteria.getParticipant() != null) {
			try {
				//Participant participant = processParticipantCriteria(criteria.getParticipantIdentifier());
				dbParticipant = fetchParticipant(criteria.getParticipantIdentifier());
        		if(dbParticipant != null){
        			logger.info("Participant Exists in caAERS");
        		}else{
    				String message = messageSource.getMessage("WS_AEMS_001", new String[]{criteria.getParticipantIdentifier()},"",Locale.getDefault());
        			logger.error(message);
    				//adverseEventImportOutcome.addErrorMessage("Participant Does not exist in caAERS " , DomainObjectImportOutcome.Severity.ERROR);
    				adverseEventResponse.setResponsecode("WS_AEMS_001");
    				adverseEventResponse.setDescription(message);
    				caaersServiceResponse.setResponse(adverseEventResponse);
    				return caaersServiceResponse;
        		}
			} catch (CaaersSystemException e){
				String message = messageSource.getMessage("WS_AEMS_002", new String[]{e.getMessage()},"",Locale.getDefault());
				//adverseEventImportOutcome = new DomainObjectImportOutcome<AdverseEvent>();
				logger.error("Participant Criteria to ParticipantDomain Conversion Failed " , e);
				//adverseEventImportOutcome.addErrorMessage("Participant Criteria to ParticipantDomain Conversion Failed " , DomainObjectImportOutcome.Severity.ERROR);
				adverseEventResponse.setResponsecode("WS_AEMS_002");				
				adverseEventResponse.setDescription(message);
			}
			
		}		
		//add criteria to criteria

		StudyParticipantAssignment assignment = null;
		
		if (dbParticipant != null && dbStudy != null) {
			// process adverse events ...
			// check for assignment .
			assignment = studyParticipantAssignmentDao.getAssignment(dbParticipant, dbStudy);
			if(assignment != null){
    			logger.info("Participant is  assigned to Study");
    		}else{
   				String message = messageSource.getMessage("WS_AEMS_005", new String[]{criteria.getParticipantIdentifier(),criteria.getStudyIdentifier()},"",Locale.getDefault());
    			logger.error(message);
			//	adverseEventImportOutcome.addErrorMessage("Participant is not assigned to Study " , DomainObjectImportOutcome.Severity.ERROR);
				adverseEventResponse.setResponsecode("WS_AEMS_005");
				adverseEventResponse.setDescription(message);
				caaersServiceResponse.setResponse(adverseEventResponse);
				return caaersServiceResponse;
    		}
			AdverseEventReportingPeriod adverseEventReportingPeriod = null;
			if (criteria.getCourse() != null) {
				try {
					adverseEventReportingPeriod = getReportingPeriod(criteria, assignment, operation);
				} catch (CaaersSystemException e) {
		    		messages.add(e.getMessage());
		    		adverseEventResponse.setMessage(messages);
					caaersServiceResponse.setResponse(adverseEventResponse);
					return caaersServiceResponse;
				}
			}
			
			//
			if (operation.equals(AdverseEventManagementService.DELETE)) {
				AdverseEventTerms adverseEventTerms = adverseEventsInputMessage.getAdverseEventTerms();
				if (adverseEventTerms.getAdverseEventCtcTerm().size() > 0) {
					for (AdverseEventCtcTermType adverseEventCtcTerm:adverseEventTerms.getAdverseEventCtcTerm()) {
						AdverseEvent adverseEvent = checkIfCtcTermExists(adverseEventReportingPeriod , adverseEventCtcTerm);
						String message = "";
						if (adverseEvent.getSolicited()) {
							message = messageSource.getMessage("WS_AEMS_024", new String[]{adverseEventCtcTerm.getCtepTerm()},"",Locale.getDefault());
						} else {
							deleteAdverseEvent(adverseEvent, adverseEventReportingPeriod);
							message = messageSource.getMessage("WS_AEMS_006", new String[]{adverseEvent.getId()+"",adverseEventCtcTerm.getCtepTerm(),operation+"d"},"",Locale.getDefault());
						}
						messages.add (message);	
					}
				} else if (adverseEventTerms.getAdverseEventMeddraLowLevelTerm().size() > 0) {
					for (AdverseEventMeddraLowLevelTermType adverseEventMeddraLowLevelTerm:adverseEventTerms.getAdverseEventMeddraLowLevelTerm()) {
						AdverseEvent adverseEvent = checkIfMeddraTermExists(adverseEventReportingPeriod , adverseEventMeddraLowLevelTerm);
						String message = "";
						if (adverseEvent.getSolicited()) {
							message = messageSource.getMessage("WS_AEMS_024", new String[]{adverseEventMeddraLowLevelTerm.getMeddraTerm()},"",Locale.getDefault());
						} else {
							deleteAdverseEvent(adverseEvent, adverseEventReportingPeriod);
							message = messageSource.getMessage("WS_AEMS_006", new String[]{adverseEvent.getId()+"",adverseEventMeddraLowLevelTerm.getMeddraTerm(),operation+"d"},"",Locale.getDefault());
							
						}
						
						messages.add (message);	
					}				
				}
		        //enableAuthorization(authorizationOnByDefault);
				//switchUser(null);
				adverseEventResponse.setMessage(messages);
				caaersServiceResponse.setResponse(adverseEventResponse);
				return caaersServiceResponse;
			}
			
			AdverseEvents xmlAdverseEvents = adverseEventsInputMessage.getAdverseEvents();
			for (AdverseEventType adverseEventType:xmlAdverseEvents.getAdverseEvent()) {
				try {
					AdverseEvent adverseEvent = processAdverseEvent(adverseEventType,adverseEventReportingPeriod,operation,dbStudy.getAdeersReporting());
					ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
					aeReport.addAdverseEvent(adverseEvent);
					ValidationErrors errors = fireRules(aeReport,"gov.nih.nci.cabig.caaers.rules.reporting_basics_section");
					if (errors.getErrorCount() > 0) {
						for (ValidationError error:errors.getErrors()) {
							messages.add(error.getMessage() + " ("+adverseEvent.getAdverseEventTerm().getFullName()+")");
						}
					} else {
						//SAVE AE
						if (operation.equals(CREATE) || operation.equals(UPDATE)) {	
							adverseEventReportingPeriod.addAdverseEvent(adverseEvent);
							adverseEventReportingPeriodDao.save(adverseEventReportingPeriod);
							String message = messageSource.getMessage("WS_AEMS_006", new String[]{adverseEvent.getId()+"",adverseEvent.getAdverseEventTerm().getFullName(),operation+"d"},"",Locale.getDefault());
							messages.add (message);
						}	
					}
					
					
				} catch (CaaersSystemException e) {
					messages.add(e.getMessage());
					adverseEventResponse.setMessage(messages);
					caaersServiceResponse.setResponse(adverseEventResponse);
					return caaersServiceResponse;
				}
			}
			

	


				// FIRE SAE RULES ..
				/*Study initializedStudy = studyDao.initialize(dbStudy);
				for (AdverseEvent ae:adverseEventReportingPeriod.getAdverseEvents()) {
					try {
						String reportDefinition = adverseEventEvaluationService.assesAdverseEvent(ae, dbStudy);
						messages.add(reportDefinition);

						//notifyStudyPersonnel(assignment);

					} catch (Exception e) {
						messages.add("Error in firing SAE rules . "+e.getMessage());
						adverseEventResponse.setMessage(messages);
						caaersServiceResponse.setResponse(adverseEventResponse);
						return caaersServiceResponse;
					}
				}*/
			adverseEventResponse.setMessage(messages);
		}
        //enableAuthorization(authorizationOnByDefault);
		//switchUser(null);
		
		caaersServiceResponse.setResponse(adverseEventResponse);
		return caaersServiceResponse;
	}/*
	private void notifyStudyPersonnel(StudyParticipantAssignment assignment) throws Exception {
		List<String> emails = new ArrayList<String>();
		List<StudyPersonnel> studyPersonnel = assignment.getStudySite().getStudyPersonnels();
		for (StudyPersonnel sp:studyPersonnel) {
			String email = sp.getResearchStaff().getEmailAddress();
			emails.add(email);
		}
		String content = "report email test ... ";
		caaersJavaMailSender.sendMail(emails.toArray(new String[0]), "Reporting required for exported Adverse Events", content,new String[0]);
		
	}*/
	private void deleteAdverseEvent(AdverseEvent adverseEvent,AdverseEventReportingPeriod adverseEventReportingPeriod) {
		List<AdverseEvent> dbAdverseEvents = adverseEventReportingPeriod.getAdverseEvents();
	
			for (int i=0;i<adverseEventReportingPeriod.getAdverseEvents().size();i++) {
				AdverseEvent dbAE = adverseEventReportingPeriod.getAdverseEvents().get(i);
				if (dbAE.getId() == adverseEvent.getId()) {
					dbAdverseEvents.remove(i);
				}
			}
			adverseEventReportingPeriod.setAdverseEvents(dbAdverseEvents);
			adverseEventReportingPeriodDao.save(adverseEventReportingPeriod);
	}

	private AdverseEventReportingPeriod getReportingPeriod(Criteria criteria,StudyParticipantAssignment assignment,String operation) throws CaaersSystemException {
		AdverseEventReportingPeriod adverseEventReportingPeriod = null;

		if (criteria.getCourse() != null) {
	        Date xmlStartDate = criteria.getCourse().getStartDateOfThisCourse().toGregorianCalendar().getTime();
	        Date xmlEndDate = null;
	        if (criteria.getCourse().getEndDateOfThisCourse() != null) {
	        	xmlEndDate = criteria.getCourse().getEndDateOfThisCourse().toGregorianCalendar().getTime();
	        }
	        
			List<AdverseEventReportingPeriod> rPeriodList = adverseEventReportingPeriodDao.getByAssignment(assignment);
			for (AdverseEventReportingPeriod aerp : rPeriodList) {
	        	Date sDate = aerp.getStartDate();
	            Date eDate = aerp.getEndDate();
	            
	            if (DateUtils.compareDate(xmlStartDate,sDate) == 0) {
		            if ((xmlEndDate != null && DateUtils.compareDate(xmlEndDate,eDate)==0) || (xmlEndDate == null && eDate == null)) {
	            			adverseEventReportingPeriod = aerp;
	            			break;
	            	}
	            }
			}
			
			// incase of update , the reporting period shud be existing in database . 
			
			Study study = assignment.getStudySite().getStudy();
			if (adverseEventReportingPeriod == null) {
				
				if (operation.equals(DELETE)) {
					throw new CaaersSystemException(messageSource.getMessage("WS_AEMS_008", new String[]{},"",Locale.getDefault()));
				}
				if(criteria.getCourse().getStartDateOfFirstCourse() != null) {
					assignment.setStartDateOfFirstCourse(criteria.getCourse().getStartDateOfFirstCourse().toGregorianCalendar().getTime());
				}
				//create one 
				adverseEventReportingPeriod = new AdverseEventReportingPeriod();
		    	//reportingPeriod.setDescription(criteria.getCourse().getd);
		    	adverseEventReportingPeriod.setStartDate(xmlStartDate);
		    	adverseEventReportingPeriod.setEndDate(xmlEndDate);
		    	
		    	if (criteria.getCourse().getCycleNumber() != null) {
		    		adverseEventReportingPeriod.setCycleNumber(criteria.getCourse().getCycleNumber().intValue());
		    	}
		    	
		    	if (criteria.getCourse().getTreatmentAssignmentCode() == null) {
		    		adverseEventReportingPeriod.setTreatmentAssignmentDescription(criteria.getCourse().getOtherTreatmentAssignmentDescription());
		    	} else {
		    		TreatmentAssignment ta = treatmentAssignmentDao.getAssignmentsByStudyIdExactMatch(criteria.getCourse().getTreatmentAssignmentCode(), study.getId());
		    		if (ta != null) {
		    			adverseEventReportingPeriod.setTreatmentAssignment(ta);
			    	} else {
			    		throw new CaaersSystemException(messageSource.getMessage("WS_AEMS_009", new String[]{criteria.getCourse().getTreatmentAssignmentCode()},"",Locale.getDefault()));
			    	}
		    	}
		    	
		    	
		    	List<Epoch> epochs = study.getEpochs();
		    	Epoch epochToSave = null;
		    	
		    	if (StringUtils.isNotEmpty(criteria.getCourse().getTreatmentType())) {
			    	for (Epoch epoch:epochs) {
			    		if (epoch.getName().equals(criteria.getCourse().getTreatmentType())) {
			    			epochToSave = epoch;
			    			break;
			    		}
			    	}
		    	}
		    	// CAAERS-2813
		    	if (StringUtils.isNotEmpty(criteria.getCourse().getTreatmentType()) && epochToSave == null) {
		    		throw new CaaersSystemException(messageSource.getMessage("WS_AEMS_010", new String[]{criteria.getCourse().getTreatmentType()},"",Locale.getDefault()));
		    	}
		    	
		    	if (epochToSave != null) {
		    		adverseEventReportingPeriod.setEpoch(epochToSave);
		    	}
		    	//
		    	/*
		    	if (epochToSave == null) {
		    		throw new CaaersSystemException(messageSource.getMessage("WS_AEMS_010", new String[]{criteria.getCourse().getTreatmentType()},"",Locale.getDefault()));
		    	} else {
		    		adverseEventReportingPeriod.setEpoch(epochToSave);
		    	}
		    	*/
		    	
		    	adverseEventReportingPeriod.setAssignment(assignment);
		    	List<String> errors = validateRepPeriodDates(adverseEventReportingPeriod,rPeriodList,assignment.getStartDateOfFirstCourse());
		    	if (errors.size() == 0) {
		    		studyParticipantAssignmentDao.save(assignment);
		    		adverseEventReportingPeriodDao.save(adverseEventReportingPeriod);
		    	} else {
		    		throw new CaaersSystemException(errors.get(0));
		    	}
			}			
		}	
		return adverseEventReportingPeriod;
	}

 
	private AdverseEvent processAdverseEvent(AdverseEventType xmlAdverseEvent,
					AdverseEventReportingPeriod adverseEventReportingPeriod, String operation, boolean adeersReportingRequired) throws CaaersSystemException{
		logger.info("Entering processAdverseEvent() in AdverseEventManagementServiceImpl");		
		
		AdverseEvent adverseEvent = null;
		if (adeersReportingRequired) {
			if (xmlAdverseEvent.getOutcome().size() > 0) {
				throw new CaaersSystemException(messageSource.getMessage("WS_AEMS_011", new String[]{"Oucomes"},"",Locale.getDefault()));
			}
			if (xmlAdverseEvent.getEventApproximateTime() != null) {
				throw new CaaersSystemException(messageSource.getMessage("WS_AEMS_011", new String[]{"EventApproximateTime"},"",Locale.getDefault()));
			}
			if (xmlAdverseEvent.getEventLocation() != null) {
				throw new CaaersSystemException(messageSource.getMessage("WS_AEMS_011", new String[]{"EventLocation"},"",Locale.getDefault()));
			}			
		}
		//if update get the adverse event to update ..
		List<AdverseEvent> dbAdverseEvents = getAdverseEventDao().getByAssignment(adverseEventReportingPeriod.getAssignment());

		if (xmlAdverseEvent.getAdverseEventCtcTerm() != null) {
			adverseEvent = checkIfCtcTermExists(adverseEventReportingPeriod , xmlAdverseEvent.getAdverseEventCtcTerm());
		} else if (xmlAdverseEvent.getAdverseEventMeddraLowLevelTerm() != null) {
			adverseEvent = checkIfMeddraTermExists(adverseEventReportingPeriod , xmlAdverseEvent.getAdverseEventMeddraLowLevelTerm());
		}
		String operationOnThisAE = operation;
		if (operation.equals(UPDATE)) {	
			//if doesn't exist create ... 
			if (adverseEvent == null) {
				adverseEvent = new AdverseEvent();
				adverseEvent.setReportingPeriod(adverseEventReportingPeriod);
				operationOnThisAE = CREATE;
			}
		} else if (operation.equals(CREATE)) {
			//if AE exists , shud not be able to create AE with same term ...
			if (adverseEvent == null) {
				adverseEvent = new AdverseEvent();
				adverseEvent.setReportingPeriod(adverseEventReportingPeriod);
				operationOnThisAE = CREATE;
			} else {
				String term = adverseEvent.getAdverseEventTerm().getFullName();
				throw new CaaersSystemException(messageSource.getMessage("WS_AEMS_012", new String[]{term},"",Locale.getDefault()));
			}
		} 
/*
		if (operation.equals(DELETE)) {
			adverseEvent = checkIfAEExists(adverseEventReportingPeriod , xmlAdverseEvent);
			if (adverseEvent == null) {
				throw new CaaersSystemException(messageSource.getMessage("WS_AEMS_013", new String[]{},"",Locale.getDefault()));
			}
			return adverseEvent;			
		}
*/		
		try{			
			adverseEventConverter.convertAdverseEventDtoToAdverseEventDomain(xmlAdverseEvent, adverseEvent, operationOnThisAE);
        }catch(CaaersSystemException caEX){
         	throw new CaaersSystemException(caEX);
        }
        return adverseEvent;
 	}
	/*
	private AdverseEvent checkIfAEExists(AdverseEventReportingPeriod adverseEventReportingPeriod , AdverseEventType xmlAdverseEvent){	
		AdverseEvent adverseEvent = null;
		List<AdverseEvent> dbAdverseEvents = getAdverseEventDao().getByAssignment(adverseEventReportingPeriod.getAssignment());
		for (AdverseEvent dbAdverseEvent:dbAdverseEvents) {
			if (DateUtils.compareDate(adverseEventReportingPeriod.getStartDate() , dbAdverseEvent.getReportingPeriod().getStartDate()) == 0) {					
				if ((adverseEventReportingPeriod.getEndDate() != null && DateUtils.compareDate(adverseEventReportingPeriod.getEndDate(),dbAdverseEvent.getReportingPeriod().getEndDate())==0)
						|| (adverseEventReportingPeriod.getEndDate() == null && dbAdverseEvent.getReportingPeriod().getEndDate() == null) ) {
						//ctc or meddra
						if (dbAdverseEvent.getAdverseEventTerm() instanceof AdverseEventCtcTerm) {
							AdverseEventCtcTerm aeCtcTerm = (AdverseEventCtcTerm)dbAdverseEvent.getAdverseEventTerm();
							if (aeCtcTerm.getCtcTerm().getTerm().equals(xmlAdverseEvent.getAdverseEventCtcTerm().getCtepTerm())) {
								adverseEvent = dbAdverseEvent;
								break;
							}
						} else if (dbAdverseEvent.getAdverseEventTerm() instanceof AdverseEventMeddraLowLevelTerm) {
							AdverseEventMeddraLowLevelTerm aeMeddraLowLevelTerm = (AdverseEventMeddraLowLevelTerm)dbAdverseEvent.getAdverseEventTerm();
							if (aeMeddraLowLevelTerm.getLowLevelTerm().getMeddraCode().equals(xmlAdverseEvent.getAdverseEventMeddraLowLevelTerm().getMeddraCode()) &&
									aeMeddraLowLevelTerm.getLowLevelTerm().getMeddraTerm().equals(xmlAdverseEvent.getAdverseEventMeddraLowLevelTerm().getMeddraTerm()) ) {
								adverseEvent = dbAdverseEvent;
								break;
							}								
						}					
				}
			}
		}
		return adverseEvent;
	}
	*/
	private AdverseEvent checkIfCtcTermExists(AdverseEventReportingPeriod adverseEventReportingPeriod , AdverseEventCtcTermType xmlAdverseEventCtcTerm){	
		AdverseEvent adverseEvent = null;
		List<AdverseEvent> dbAdverseEvents = getAdverseEventDao().getByAssignment(adverseEventReportingPeriod.getAssignment());
		for (AdverseEvent dbAdverseEvent:dbAdverseEvents) {
			if (DateUtils.compareDate(adverseEventReportingPeriod.getStartDate() , dbAdverseEvent.getReportingPeriod().getStartDate()) == 0) {					
				if ((adverseEventReportingPeriod.getEndDate() != null && DateUtils.compareDate(adverseEventReportingPeriod.getEndDate(),dbAdverseEvent.getReportingPeriod().getEndDate())==0)
						|| (adverseEventReportingPeriod.getEndDate() == null && dbAdverseEvent.getReportingPeriod().getEndDate() == null) ) {
						if (dbAdverseEvent.getAdverseEventTerm() instanceof AdverseEventCtcTerm) {
							AdverseEventCtcTerm aeCtcTerm = (AdverseEventCtcTerm)dbAdverseEvent.getAdverseEventTerm();
							if (aeCtcTerm.getCtcTerm().getTerm().equals(xmlAdverseEventCtcTerm.getCtepTerm())) {
								adverseEvent = dbAdverseEvent;
								break;
							}
						}					
				}
			}
		}
		return adverseEvent;
	}
	
	private AdverseEvent checkIfMeddraTermExists(AdverseEventReportingPeriod adverseEventReportingPeriod , AdverseEventMeddraLowLevelTermType xmlAdverseEventMeddraLowLevelTerm){	
		AdverseEvent adverseEvent = null;
		List<AdverseEvent> dbAdverseEvents = getAdverseEventDao().getByAssignment(adverseEventReportingPeriod.getAssignment());
		for (AdverseEvent dbAdverseEvent:dbAdverseEvents) {
			if (DateUtils.compareDate(adverseEventReportingPeriod.getStartDate() , dbAdverseEvent.getReportingPeriod().getStartDate()) == 0) {					
				if ((adverseEventReportingPeriod.getEndDate() != null && DateUtils.compareDate(adverseEventReportingPeriod.getEndDate(),dbAdverseEvent.getReportingPeriod().getEndDate())==0)
						|| (adverseEventReportingPeriod.getEndDate() == null && dbAdverseEvent.getReportingPeriod().getEndDate() == null) ) {
					 if (dbAdverseEvent.getAdverseEventTerm() instanceof AdverseEventMeddraLowLevelTerm) {
						AdverseEventMeddraLowLevelTerm aeMeddraLowLevelTerm = (AdverseEventMeddraLowLevelTerm)dbAdverseEvent.getAdverseEventTerm();
						if (aeMeddraLowLevelTerm.getLowLevelTerm().getMeddraCode().equals(xmlAdverseEventMeddraLowLevelTerm.getMeddraCode()) &&
								aeMeddraLowLevelTerm.getLowLevelTerm().getMeddraTerm().equals(xmlAdverseEventMeddraLowLevelTerm.getMeddraTerm()) ) {
							adverseEvent = dbAdverseEvent;
							break;
						}								
					}							
				}
			}
		}
		return adverseEvent;
	}
	
	   private List<String> validateRepPeriodDates(AdverseEventReportingPeriod rPeriod, List<AdverseEventReportingPeriod> rPeriodList, Date firstCourseDate) {

	        Date startDate = rPeriod.getStartDate();
	        Date endDate = rPeriod.getEndDate();
	        List<String> errors = new ArrayList<String>();

	        // Check if the start date is equal to or before the end date.
	        if (firstCourseDate != null && startDate != null && (firstCourseDate.getTime() - startDate.getTime() > 0)) {
	            errors.add(messageSource.getMessage("WS_AEMS_014", new String[]{},"",Locale.getDefault()));
	        }

	        if (startDate != null && endDate != null && (endDate.getTime() - startDate.getTime() < 0)) {
	            errors.add(messageSource.getMessage("WS_AEMS_015", new String[]{},"",Locale.getDefault()));
	        }

	        // Check if the start date is equal to end date.
	        // This is allowed only for Baseline reportingPeriods and not for other reporting periods.
	        
	        if (rPeriod.getEpoch() !=null && !rPeriod.getEpoch().getName().equals("Baseline")) {
	            if (endDate != null && startDate.equals(endDate)) {
	                errors.add(messageSource.getMessage("WS_AEMS_016", new String[]{},"",Locale.getDefault()));
	            }

	        }

	        // Check if the start date - end date for the reporting Period overlaps with the
	        // date range of an existing Reporting Period.
	        for (AdverseEventReportingPeriod aerp : rPeriodList) {
	        	Date sDate = aerp.getStartDate();
	            Date eDate = aerp.getEndDate();
	            
	            if (!aerp.getId().equals(rPeriod.getId())) {
	                
	                //we should make sure that no existing Reporting Period, start date falls, in-between these dates.
	                if(startDate != null && endDate != null){
	                	if(DateUtils.compareDate(sDate, startDate) >= 0 && DateUtils.compareDate(sDate, endDate) < 0){
	                		errors.add(messageSource.getMessage("WS_AEMS_017", new String[]{},"",Locale.getDefault()));
	                		break;
	                	}
	                }else if(startDate != null && DateUtils.compareDate(sDate, startDate) == 0){
	                		errors.add(messageSource.getMessage("WS_AEMS_017", new String[]{},"",Locale.getDefault()));
	                		break;
	                }
	                
	                //newly created reporting period start date, should not fall within any other existing reporting periods
	                if(sDate != null && eDate != null){
	                	if(DateUtils.compareDate(sDate, startDate) <=0 && DateUtils.compareDate(startDate, eDate) < 0){
	                		errors.add(messageSource.getMessage("WS_AEMS_017", new String[]{},"",Locale.getDefault()));
	                		break;
	                	}
	                }else if(sDate != null && DateUtils.compareDate(sDate, startDate) == 0){
	                	errors.add(messageSource.getMessage("WS_AEMS_017", new String[]{},"",Locale.getDefault()));
	            		break;
	                }
	            }
	            
	            // If the epoch of reportingPeriod is not - Baseline , then it cannot be earlier than a Baseline
	            if (rPeriod.getEpoch() !=null && rPeriod.getEpoch().getName().equals("Baseline")) {
	                if (!aerp.getEpoch().getName().equals("Baseline")) {
	                    if (DateUtils.compareDate(sDate, startDate) < 0) {
	                        errors.add(messageSource.getMessage("WS_AEMS_018", new String[]{},"",Locale.getDefault()));
	                        return errors;
	                    }
	                }
	            } else {
	                if (aerp.getEpoch() !=null && aerp.getEpoch().getName().equals("Baseline")) {
	                    if (DateUtils.compareDate(startDate, sDate) < 0) {
	                        errors.add(messageSource.getMessage("WS_AEMS_019", new String[]{},"",Locale.getDefault()));
	                        return errors;
	                    }
	                }
	            }
	            
	        }
	        return errors;
	      
	    }

	    
/*	
	private Study processStudyCriteria(StudyType xmlStudy) throws CaaersSystemException{
		logger.info("Entering processStudyCriteria() in AdverseEventManagementServiceImpl");		
		
		Study study = new Study();
		
		try{
			studyCriteriaConverter.convertStudyDtoToStudyDomain(xmlStudy, study);
        }catch(CaaersSystemException caEX){
         	throw new CaaersSystemException("Study Criteria to StudyDomain Conversion Failed ", caEX);
        }
        return study;
 	}	
	private Participant processParticipantCriteria(ParticipantType xmlParticipant) throws CaaersSystemException{
		logger.info("Entering processParticipant() in AdverseEventManagementServiceImpl");		
		
		Participant participant = new Participant();
		
		try{
			participantCriteriaConverter.convertParticipantDtoToParticipantDomain(xmlParticipant, participant);
        }catch(CaaersSystemException caEX){
         	throw new CaaersSystemException("Participant Criteria to ParticipantDomain Conversion Failed ", caEX);
        }
        return participant;
 	}*/
	public CaaersServiceResponse deleteAdverseEvent(AdverseEventsInputMessage adverseEventsInputMessage) {
		if (adverseEventsInputMessage.getAdverseEventTerms() == null) {
			CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
			Response adverseEventResponse = new Response();
			adverseEventResponse.setResponsecode("WS_AEMS_026");
			adverseEventResponse.setDescription(messageSource.getMessage("WS_AEMS_026", new String[]{},"",Locale.getDefault()));
			caaersServiceResponse.setResponse(adverseEventResponse);
			return caaersServiceResponse;
		}
		return saveAdverseEvent(adverseEventsInputMessage,DELETE);
	}

	public CaaersServiceResponse createOrUpdateAdverseEvent(AdverseEventsInputMessage adverseEventsInputMessage) {
		return saveAdverseEvent(adverseEventsInputMessage,UPDATE);
	}
	public CaaersServiceResponse createAdverseEvent(AdverseEventsInputMessage adverseEventsInputMessage) {
		return saveAdverseEvent(adverseEventsInputMessage,CREATE);
	}
	
	private Participant fetchParticipant(String identifier){
		Participant dbParticipant = null;
		Identifier pi = new Identifier();
		pi.setValue(identifier);
		dbParticipant = participantDao.getByIdentifier(pi);
		if(dbParticipant == null){
			return null;
		}
		participantDao.evict(dbParticipant);		
		/*
		for(Identifier identifier : participant.getIdentifiers()){
			dbParticipant = participantDao.getParticipantDesignByIdentifier(identifier);
			if(dbParticipant != null){
				break;
			}
			participantDao.evict(dbParticipant);
		}
		*/
		return dbParticipant;
	}	
	
	private Study fetchStudy(String identifier){
		Study dbStudy = null;
		Identifier si = new Identifier();
		si.setValue(identifier);
		dbStudy = studyDao.getByIdentifier(si);
        if(dbStudy == null){
        	return null;
        }
        studyDao.evict(dbStudy);

		return dbStudy;
	}

	
	public void setAdverseEventDao(AdverseEventDao adverseEventDao) {
		this.adverseEventDao = adverseEventDao;
	}

	public void setExecutionService(
			BusinessRulesExecutionServiceImpl executionService) {
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
	public void setStudyParticipantAssignmentDao(
			StudyParticipantAssignmentDao studyParticipantAssignmentDao) {
		this.studyParticipantAssignmentDao = studyParticipantAssignmentDao;
	}
	private ValidationErrors fireRules(ExpeditedAdverseEventReport aeReport  , String bindUri){

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
            if (o instanceof ValidationErrors) return (ValidationErrors) o;
        return null;
    }

	public void setAdverseEventConverter(AdverseEventConverter adverseEventConverter) {
		this.adverseEventConverter = adverseEventConverter;
	}

	public void setAdverseEventReportingPeriodDao(
			AdverseEventReportingPeriodDao adverseEventReportingPeriodDao) {
		this.adverseEventReportingPeriodDao = adverseEventReportingPeriodDao;
	}

	public void setTreatmentAssignmentDao(
			TreatmentAssignmentDao treatmentAssignmentDao) {
		this.treatmentAssignmentDao = treatmentAssignmentDao;
	}
/*
	public void setAdverseEventEvaluationService(
			AdverseEventEvaluationService adverseEventEvaluationService) {
		this.adverseEventEvaluationService = adverseEventEvaluationService;
	}
	public void setCaaersJavaMailSender(CaaersJavaMailSender caaersJavaMailSender) {
		this.caaersJavaMailSender = caaersJavaMailSender;
	}*/
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	/*
	private boolean enableAuthorization(boolean on) {
        AuthorizationSwitch sw = (AuthorizationSwitch) this.applicationContext.getBean("authorizationSwitch");
        if (sw == null) throw new RuntimeException("Authorization switch not found");
        boolean current = sw.isOn();
        sw.setOn(on);
        return current;
    }
	
	private void switchUser(String userName, String... roles) {
        GrantedAuthority[] authorities = new GrantedAuthority[roles.length];
        for (int i = 0; i < roles.length; i++) {
            authorities[i] = new GrantedAuthorityImpl(roles[i]);
        }
        Authentication auth = new TestingAuthenticationToken(userName, "ignored", authorities);
        auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }*/
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
