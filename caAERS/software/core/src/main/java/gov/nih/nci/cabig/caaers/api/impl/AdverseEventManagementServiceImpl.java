/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.api.impl;

import com.semanticbits.rules.impl.BusinessRulesExecutionServiceImpl;
import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.AbstractImportService;
import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.dao.index.AdverseEventIndexDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.ajax.StudySearchableAjaxableDomainObject;
import gov.nih.nci.cabig.caaers.rules.business.service.AdverseEventEvaluationService;
import gov.nih.nci.cabig.caaers.service.AdverseEventReportingPeriodService;
import gov.nih.nci.cabig.caaers.service.migrator.adverseevent.AdverseEventConverter;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.*;
import gov.nih.nci.cabig.caaers.integration.schema.common.*;
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

import java.util.*;

public class AdverseEventManagementServiceImpl extends AbstractImportService implements ApplicationContextAware {

    public static final String CREATE = "create";
   	public static final String UPDATE = "update";
   	public static final String DELETE = "delete";

	protected BusinessRulesExecutionServiceImpl executionService;
	protected AdverseEventDao adverseEventDao;
	protected ParticipantDao participantDao;
	protected StudyDao studyDao;
	protected StudyParticipantAssignmentDao studyParticipantAssignmentDao;
	protected AdverseEventIndexDao adverseEventIndexDao;
	protected AdverseEventReportingPeriodDao adverseEventReportingPeriodDao;
	protected TreatmentAssignmentDao treatmentAssignmentDao;
	protected CtcTermDao ctcTermDao;
	
	private AdverseEventConverter adverseEventConverter;
	private AdverseEventEvaluationService adverseEventEvaluationService;
	private AdverseEventReportingPeriodService adverseEventReportingPeriodService;
	private MailSender mailSender;
	private ApplicationContext applicationContext;
	private MessageSource messageSource;
	
	private static Log logger = LogFactory.getLog(AdverseEventManagementServiceImpl.class);
	
	private CaaersServiceResponse saveAdverseEvent(AdverseEventsInputMessage adverseEventsInputMessage,String operation) {
		//boolean authorizationOnByDefault = enableAuthorization(false);
		//switchUser("SYSTEM_ADMIN", "caaers_super_user");
	//	WebRequest stubWebRequest = preProcess();
        CaaersServiceResponse caaersServiceResponse = Helper.createResponse();

        if (operation.equals(AdverseEventManagementServiceImpl.CREATE) || operation.equals(AdverseEventManagementServiceImpl.UPDATE)) {
			if (adverseEventsInputMessage.getAdverseEvents() == null) {
                return Helper.populateError(caaersServiceResponse,
                        "WS_AEMS_025" , messageSource.getMessage("WS_AEMS_025", new String[]{},"",Locale.getDefault()));
			}
		}
		//DomainObjectImportOutcome<AdverseEvent> adverseEventImportOutcome = null;
		//add participant to criteria
		Participant dbParticipant = null;
		Study dbStudy = null;
		AeTerminology terminology = null;
		List messages = new ArrayList();
		Criteria criteria = adverseEventsInputMessage.getCriteria();

		if (criteria.getStudyIdentifier() != null) {
			//if (criteria.getStudy() != null) {
			try {
				//Study study = processStudyCriteria(criteria.getStudy());
				dbStudy = fetchStudy(criteria.getStudyIdentifier());
        		if(dbStudy != null){
        			terminology = dbStudy.getAeTerminology();
        			logger.info("Study Exists in caAERS");
        		}else{
       				String message = messageSource.getMessage("WS_AEMS_003", new String[]{criteria.getStudyIdentifier()},"",Locale.getDefault());
        			logger.error("Study Does not exist ");
    				//adverseEventImportOutcome.addErrorMessage("Study Does not exist in caAERS " , DomainObjectImportOutcome.Severity.ERROR);
                    return Helper.populateError(caaersServiceResponse,"WS_AEMS_003",message);

        		}
        		List<StudySearchableAjaxableDomainObject> authorizedStudies = getAuthorizedStudies(criteria.getStudyIdentifier());
        		if(authorizedStudies.size() == 0) {
        			String message = messageSource.getMessage("WS_AEMS_027", new String[]{criteria.getStudyIdentifier()},"",Locale.getDefault());
                    return Helper.populateError(caaersServiceResponse,"WS_AEMS_027",message);
        		}
			} catch (CaaersSystemException e){
				String message = messageSource.getMessage("WS_AEMS_004", new String[]{e.getMessage()},"",Locale.getDefault());
				//adverseEventImportOutcome = new DomainObjectImportOutcome<AdverseEvent>();
				logger.error("Study Criteria to StudyDomain Conversion Failed " , e);
				//adverseEventImportOutcome.addErrorMessage("Study Criteria to StudyDomain Conversion Failed " , DomainObjectImportOutcome.Severity.ERROR);
                return Helper.populateError(caaersServiceResponse,"WS_AEMS_004",message);
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
                    return Helper.populateError(caaersServiceResponse,"WS_AEMS_001",message);
        		}
			} catch (CaaersSystemException e){
				String message = messageSource.getMessage("WS_AEMS_002", new String[]{e.getMessage()},"",Locale.getDefault());
				//adverseEventImportOutcome = new DomainObjectImportOutcome<AdverseEvent>();
				logger.error("Participant Criteria to ParticipantDomain Conversion Failed " , e);
				//adverseEventImportOutcome.addErrorMessage("Participant Criteria to ParticipantDomain Conversion Failed " , DomainObjectImportOutcome.Severity.ERROR);
                return Helper.populateError(caaersServiceResponse,"WS_AEMS_002",message);
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
                return Helper.populateError(caaersServiceResponse,"WS_AEMS_005",message);
    		}

			AdverseEventReportingPeriod adverseEventReportingPeriod = null;
			if (criteria.getCourse() != null) {
				try {
					adverseEventReportingPeriod = getReportingPeriod(criteria, assignment, operation);
				} catch (CaaersSystemException e) {
		    	//	messages.add(e.getMessage());
                    return Helper.populateError(caaersServiceResponse,e.getErrorCode(), e.getMessage());
				}
			}
			
			//
			if (operation.equals(AdverseEventManagementServiceImpl.DELETE)) {
                String errorCode = "";

				AdverseEvents adverseEvents = adverseEventsInputMessage.getAdverseEvents();
				if (adverseEvents.getAdverseEvent().size() > 0) {
					for (AdverseEventType aeType:adverseEvents.getAdverseEvent()) {
						AdverseEvent adverseEvent = null;
						String code = null;

						if (aeType.getId()!=null) {
							adverseEvent = this.getAdverseEventDao().getById(aeType.getId().intValue());
							code = adverseEvent.getId()+"";
						}	else {
							if (aeType.getCtepCode()!=null) {
								adverseEvent = checkIfCtcTermExists(adverseEventReportingPeriod , aeType.getCtepCode(), terminology.getCtcVersion());
								code = aeType.getCtepCode();
							}
							if (aeType.getAdverseEventMeddraLowLevelTerm()!=null) {
								adverseEvent = checkIfMeddraTermExists(adverseEventReportingPeriod , aeType.getAdverseEventMeddraLowLevelTerm());
								code = aeType.getAdverseEventMeddraLowLevelTerm().getMeddraCode();
							}							
						}
						
						if (adverseEvent!=null) {
							String message = "";
							if (adverseEvent.getSolicited()) {
								message = messageSource.getMessage("WS_AEMS_024", new String[]{code},"",Locale.getDefault());
	                            errorCode = "WS_AEMS_024";
							} else {
								deleteAdverseEvent(adverseEvent, adverseEventReportingPeriod);
								message = messageSource.getMessage("WS_AEMS_006", new String[]{adverseEvent.getAdverseEventTerm().getFullName()+"",operation+"d"},"",Locale.getDefault());
	                            errorCode = "WS_AEMS_006";
							}
							//messages.add (message);
                            Helper.populateError(caaersServiceResponse,errorCode, message);
						}
					}
				}

				return caaersServiceResponse;
			}
			
			AdverseEvents xmlAdverseEvents = adverseEventsInputMessage.getAdverseEvents();
			Map <Integer , AdverseEvent> aesToSave = new HashMap<Integer,AdverseEvent>();
			//List<AdverseEvent> aesToSave = new ArrayList<AdverseEvent>();
			int index = 0; 
			for (AdverseEventType adverseEventType:xmlAdverseEvents.getAdverseEvent()) {
				index++;
				try {
					
					AdverseEvent adverseEvent = processAdverseEvent(adverseEventType,adverseEventReportingPeriod,operation,dbStudy,terminology);
					// build list of aes that can be saved ...
					aesToSave.put(index, adverseEvent);
					
				} catch (CaaersSystemException e) {
                    Helper.populateErrorOutcome(caaersServiceResponse, null , null, (index++) + "", Arrays.asList(new String[] {e.getMessage()}));
				}
			}
			Iterator it =  aesToSave.entrySet().iterator();
			while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        
		        AdverseEvent adverseEvent = (AdverseEvent)pairs.getValue();
		        

				ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
				
//				aeReport.addAdverseEvent(adverseEvent);
				aeReport.addAdverseEventUnidirectional(adverseEvent);

				ValidationErrors errors = fireRules(aeReport,"gov.nih.nci.cabig.caaers.rules.reporting_basics_section");
				if (errors.getErrorCount() > 0) {
                    Helper.populateError(caaersServiceResponse, "WS_GEN_000", "Unable to process the request");
					for (ValidationError error:errors.getErrors()) {
						//messages.add(error.getMessage() + " ("+adverseEvent.getAdverseEventTerm().getFullName()+")");
                         Helper.populateErrorOutcome(caaersServiceResponse, null, null, pairs.getKey() + "", Arrays.asList(new String[] {error.getMessage() + " ("+adverseEvent.getAdverseEventTerm().getFullName()+")"}));
					}
				} else {
					//SAVE AE
					if (operation.equals(CREATE) || operation.equals(UPDATE)) {	
						adverseEventReportingPeriod.addAdverseEvent(adverseEvent);
						adverseEventReportingPeriodDao.save(adverseEventReportingPeriod);
						String term = "";
						if (adverseEvent.getAdverseEventTerm() != null) {
							term = adverseEvent.getAdverseEventTerm().getFullName();
						} else {
							term = adverseEvent.getDetailsForOther();
						}

                        String message = messageSource.getMessage("WS_AEMS_006", new String[]{term, operation + "d"}, "", Locale.getDefault());
                        Helper.populateError(caaersServiceResponse, "WS_AEMS_006", message);
                        Helper.populateErrorOutcome(caaersServiceResponse, null, adverseEvent.getId().toString(), pairs.getKey().toString(), Arrays.asList(new String[] {message}));

                        //messages.add(message);
                    }
				}				
			}


			
			

	


				// FIRE SAE RULES ..
		//		Study initializedStudy = studyDao.initialize(dbStudy);
				for (AdverseEvent ae:adverseEventReportingPeriod.getAdverseEvents()) {
					String sae = "";

					try {
						
						sae = adverseEventEvaluationService.assesAdverseEvent(ae, dbStudy);
						if (sae.equals("SERIOUS_ADVERSE_EVENT")) {
							//messages.add("Rules enabled for this study , Reporting required for exported Adverse Events");
                           caaersServiceResponse.getServiceResponse().setMessage("Rules enabled for this study , Reporting required for exported Adverse Events");

						}
						
					} catch (Exception e) {
						//messages.add("Error in firing SAE rules . "+e.getMessage());
                        return Helper.populateError(caaersServiceResponse, "WS_GEN_000", "Error :  " + e.getMessage() );
					}	
					/*
					try {						
						notifyStudyPersonnel(assignment);
						messages.add("Email Sent ..");
					} catch (MailException e) {
						messages.add("Error sending email to Study Personnel . "+e.getMessage());
						adverseEventResponse.setMessage(messages);
						caaersServiceResponse.setResponse(adverseEventResponse);
						return caaersServiceResponse;
					}*/


				}
			//adverseEventResponse.setMessage(messages);
		}
	//	postProcess(stubWebRequest);
        //enableAuthorization(authorizationOnByDefault);
		//switchUser(null);
		
            caaersServiceResponse.getServiceResponse().setMessage("Complete the request processing sucessfully");
            return caaersServiceResponse;
	}
	
	private void notifyStudyPersonnel(StudyParticipantAssignment assignment) throws MailException {
		List<String> emails = new ArrayList<String>();
		MailException mailException = null;
		List<StudyPersonnel> studyPersonnel = assignment.getStudySite().getStudyPersonnels();
		for (StudyPersonnel sp:studyPersonnel) {
			String email = sp.getEmailAddress();
			emails.add(email);
		}

		try {
			sendUserEmail(emails.toArray(new String[0]), "Reporting required for exported Adverse Events", "Reporting required for exported Adverse Events");
		} catch (MailException e) {
			mailException = e;
		}
		if(mailException != null) throw mailException;
	}
	
    public void sendUserEmail(String[] emailAddress, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailAddress);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);

    }
    
	private void deleteAdverseEvent(AdverseEvent adverseEvent,AdverseEventReportingPeriod adverseEventReportingPeriod) {
		List<AdverseEvent> dbAdverseEvents = adverseEventReportingPeriod.getAdverseEvents();
		
			for (int i=0;i<adverseEventReportingPeriod.getAdverseEvents().size();i++) {
				AdverseEvent dbAE = adverseEventReportingPeriod.getAdverseEvents().get(i);
				if (dbAE.getId() == adverseEvent.getId()) {
					dbAdverseEvents.remove(i);
					adverseEventIndexDao.deleteByEntityIdColumn(dbAE.getId()+"");  
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
					throw new CaaersSystemException("WS_AEMS_008", messageSource.getMessage("WS_AEMS_008", new String[]{},"",Locale.getDefault()));
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
			    		throw new CaaersSystemException("WS_AEMS_009",messageSource.getMessage("WS_AEMS_009", new String[]{criteria.getCourse().getTreatmentAssignmentCode()},"",Locale.getDefault()));
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
		    		throw new CaaersSystemException("WS_AEMS_010", messageSource.getMessage("WS_AEMS_010", new String[]{criteria.getCourse().getTreatmentType()},"",Locale.getDefault()));
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
		    	Map<String,String> errors = validateRepPeriodDates(adverseEventReportingPeriod,rPeriodList,assignment.getStartDateOfFirstCourse());
		    	if (errors.size() == 0) {
		    		studyParticipantAssignmentDao.save(assignment);
		    		adverseEventReportingPeriodDao.save(adverseEventReportingPeriod);
		    	} else {
                    String keys[] = errors.keySet().toArray(new String[]{});
		    		throw new CaaersSystemException(keys[0], errors.get(keys[0]));
		    	}
			} else {
                //
                adverseEventReportingPeriodService.synchronizeReports(adverseEventReportingPeriod);
            }
		}	
		return adverseEventReportingPeriod;
	}

 
	private AdverseEvent processAdverseEvent(AdverseEventType xmlAdverseEvent,
					AdverseEventReportingPeriod adverseEventReportingPeriod, String operation, Study dbStudy, AeTerminology terminology) throws CaaersSystemException{
		logger.info("Entering processAdverseEvent() in AdverseEventManagementServiceImpl");		
		
		AdverseEvent adverseEvent = null;
		
		
		boolean adeersReportingRequired = dbStudy.getAdeersReporting();
		if (adeersReportingRequired) {
			if (xmlAdverseEvent.getOutcome().size() > 0) {
				throw new CaaersSystemException("WS_AEMS_011", messageSource.getMessage("WS_AEMS_011", new String[]{"Oucomes"},"",Locale.getDefault()));
			}
			if (xmlAdverseEvent.getEventApproximateTime() != null) {
				throw new CaaersSystemException("WS_AEMS_011", messageSource.getMessage("WS_AEMS_011", new String[]{"EventApproximateTime"},"",Locale.getDefault()));
			}
			if (xmlAdverseEvent.getEventLocation() != null) {
				throw new CaaersSystemException("WS_AEMS_011", messageSource.getMessage("WS_AEMS_011", new String[]{"EventLocation"},"",Locale.getDefault()));
			}			
		}
		//if update get the adverse event to update ..
		List<AdverseEvent> dbAdverseEvents = getAdverseEventDao().getByAssignment(adverseEventReportingPeriod.getAssignment());

		if (xmlAdverseEvent.getId() != null) {
			adverseEvent = getAdverseEventDao().getById(xmlAdverseEvent.getId().intValue());
		} else if (xmlAdverseEvent.getCtepCode() != null) {
			adverseEvent = checkIfCtcTermExists(adverseEventReportingPeriod , xmlAdverseEvent.getCtepCode(),terminology.getCtcVersion());
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
				throw new CaaersSystemException("WS_AEMS_012", messageSource.getMessage("WS_AEMS_012", new String[]{term},"",Locale.getDefault()));
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
			adverseEventConverter.convertAdverseEventDtoToAdverseEventDomain(xmlAdverseEvent, adverseEvent, terminology ,adverseEventReportingPeriod.getAssignment().getStartDateOfFirstCourse(),  operationOnThisAE);
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
	private AdverseEvent checkIfCtcTermExists(AdverseEventReportingPeriod adverseEventReportingPeriod , String ctepCode, Ctc ctc){	
		AdverseEvent adverseEvent = null;
		List<AdverseEvent> dbAdverseEvents = getAdverseEventDao().getByAssignment(adverseEventReportingPeriod.getAssignment());
		for (AdverseEvent dbAdverseEvent:dbAdverseEvents) {
			if (DateUtils.compareDate(adverseEventReportingPeriod.getStartDate() , dbAdverseEvent.getReportingPeriod().getStartDate()) == 0) {					
				if ((adverseEventReportingPeriod.getEndDate() != null && DateUtils.compareDate(adverseEventReportingPeriod.getEndDate(),dbAdverseEvent.getReportingPeriod().getEndDate())==0)
						|| (adverseEventReportingPeriod.getEndDate() == null && dbAdverseEvent.getReportingPeriod().getEndDate() == null) ) {
						if (dbAdverseEvent.getAdverseEventTerm() instanceof AdverseEventCtcTerm) {
							AdverseEventCtcTerm aeCtcTerm = (AdverseEventCtcTerm)dbAdverseEvent.getAdverseEventTerm();
							if (aeCtcTerm.getCtcTerm().getCtepCode().equals(ctepCode) && aeCtcTerm.getCtcTerm().getCategory().getCtc().getId().equals(ctc.getId())) {
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
	
	   private Map<String, String> validateRepPeriodDates(AdverseEventReportingPeriod rPeriod, List<AdverseEventReportingPeriod> rPeriodList, Date firstCourseDate) {

	        Date startDate = rPeriod.getStartDate();
	        Date endDate = rPeriod.getEndDate();
	        Map<String, String> errors = new HashMap<String, String>();

	        // Check if the start date is equal to or before the end date.
	        if (firstCourseDate != null && startDate != null && (firstCourseDate.getTime() - startDate.getTime() > 0)) {
	            errors.put("WS_AEMS_014", messageSource.getMessage("WS_AEMS_014", new String[]{},"",Locale.getDefault()));
	        }

	        if (startDate != null && endDate != null && (endDate.getTime() - startDate.getTime() < 0)) {
                errors.put("WS_AEMS_015",messageSource.getMessage("WS_AEMS_015", new String[]{},"",Locale.getDefault()));
	        }

	        // Check if the start date is equal to end date.
	        // This is allowed only for Baseline reportingPeriods and not for other reporting periods.
	        
	        if (rPeriod.getEpoch() !=null && !rPeriod.getEpoch().getName().equals("Baseline")) {
	            if (endDate != null && startDate.equals(endDate)) {
                    errors.put("WS_AEMS_016",messageSource.getMessage("WS_AEMS_016", new String[]{},"",Locale.getDefault()));
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
                            errors.put("WS_AEMS_017",messageSource.getMessage("WS_AEMS_017", new String[]{},"",Locale.getDefault()));
	                		break;
	                	}
	                }else if(startDate != null && DateUtils.compareDate(sDate, startDate) == 0){
                        errors.put("WS_AEMS_017",messageSource.getMessage("WS_AEMS_017", new String[]{},"",Locale.getDefault()));
	                		break;
	                }
	                
	                //newly created reporting period start date, should not fall within any other existing reporting periods
	                if(sDate != null && eDate != null){
	                	if(DateUtils.compareDate(sDate, startDate) <=0 && DateUtils.compareDate(startDate, eDate) < 0){
                            errors.put("WS_AEMS_017",messageSource.getMessage("WS_AEMS_017", new String[]{},"",Locale.getDefault()));
	                		break;
	                	}
	                }else if(sDate != null && DateUtils.compareDate(sDate, startDate) == 0){
                        errors.put("WS_AEMS_017",messageSource.getMessage("WS_AEMS_017", new String[]{},"",Locale.getDefault()));
	            		break;
	                }
	            }
	            
	            // If the epoch of reportingPeriod is not - Baseline , then it cannot be earlier than a Baseline
	            if (rPeriod.getEpoch() !=null && rPeriod.getEpoch().getName().equals("Baseline")) {
	                if (!aerp.getEpoch().getName().equals("Baseline")) {
	                    if (DateUtils.compareDate(sDate, startDate) < 0) {
                            errors.put("WS_AEMS_018",messageSource.getMessage("WS_AEMS_018", new String[]{},"",Locale.getDefault()));
	                        return errors;
	                    }
	                }
	            } else {
	                if (aerp.getEpoch() !=null && aerp.getEpoch().getName().equals("Baseline")) {
	                    if (DateUtils.compareDate(startDate, sDate) < 0) {
                            errors.put("WS_AEMS_019",messageSource.getMessage("WS_AEMS_019", new String[]{},"",Locale.getDefault()));
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
        CaaersServiceResponse response = Helper.createResponse();
		if (adverseEventsInputMessage.getAdverseEvents() == null) {
			return  Helper.populateError(response, "WS_AEMS_026", messageSource.getMessage("WS_AEMS_026", new String[]{},"",Locale.getDefault()));
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
        //studyDao.evict(dbStudy);

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

	public void setAdverseEventEvaluationService(
			AdverseEventEvaluationService adverseEventEvaluationService) {
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
			code = adverseEventDto.getId()+"";
		} else if (adverseEventDto.getCtepCode() != null ) {
			code = adverseEventDto.getCtepCode();
		} else if (adverseEventDto.getAdverseEventMeddraLowLevelTerm() != null) {
			code = adverseEventDto.getAdverseEventMeddraLowLevelTerm().getMeddraCode();
		} else if (adverseEventDto.getVerbatim() != null) {
			code = adverseEventDto.getVerbatim();
		}
		
		
		return "Adverse Event with code , verbatim or ID : "+code ;
		
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

	public void setAdverseEventIndexDao(AdverseEventIndexDao adverseEventIndexDao) {
		this.adverseEventIndexDao = adverseEventIndexDao;
	}

    public AdverseEventReportingPeriodService getAdverseEventReportingPeriodService() {
        return adverseEventReportingPeriodService;
    }

    public void setAdverseEventReportingPeriodService(AdverseEventReportingPeriodService adverseEventReportingPeriodService) {
        this.adverseEventReportingPeriodService = adverseEventReportingPeriodService;
    }
}
