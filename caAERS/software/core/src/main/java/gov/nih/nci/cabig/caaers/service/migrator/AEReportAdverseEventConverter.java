package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.TimeValue;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventMeddraLowLevelTermType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.aereport.OutcomeType;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;

public class AEReportAdverseEventConverter {
	private CtcTermDao ctcTermDao = null;
	private LowLevelTermDao lowLevelTermDao = null;
	private MessageSource messageSource;

	public AdverseEvent convertAdverseEventDtoToAdverseEventDomain(AdverseEventType adverseEventDto, 
			 AeTerminology terminology  , Date startDateOfFirstCourse) throws CaaersSystemException{
			AdverseEvent adverseEvent = new AdverseEvent();
		
		try{
			if (adverseEventDto.getVerbatim() != null) { 
				adverseEvent.setDetailsForOther(adverseEventDto.getVerbatim());
			}
			adverseEvent.setExternalId(adverseEventDto.getExternalId());
			populateGrade(adverseEventDto,adverseEvent);
			populateHospitalization(adverseEventDto,adverseEvent);
			adverseEvent.setExpected(adverseEventDto.isExpected());
	
			populateAttributionSummary(adverseEventDto,adverseEvent);
			adverseEvent.setComments(adverseEventDto.getComments());
			if(adverseEventDto.getStartDate() != null){
				//check for future date .
				int dateCompare = DateUtils.compareDate(new Date(), adverseEventDto.getStartDate().toGregorianCalendar().getTime());
				if (dateCompare == -1) {
					throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_031", new String[]{adverseEventDto.getStartDate()+""},"",Locale.getDefault()));
				}
				adverseEvent.setStartDate(adverseEventDto.getStartDate().toGregorianCalendar().getTime());
			}
			if(adverseEventDto.getEndDate() != null){
				int dateCompare = DateUtils.compareDate(new Date(), adverseEventDto.getEndDate().toGregorianCalendar().getTime());
				if (dateCompare == -1) {
					throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_031", new String[]{adverseEventDto.getEndDate()+""},"",Locale.getDefault()));
				}
				adverseEvent.setEndDate(adverseEventDto.getEndDate().toGregorianCalendar().getTime());
			}	
	        // Check if the start date is equal to or before the end date.
			if(adverseEventDto.getStartDate() != null && startDateOfFirstCourse != null){
				int dateCompare = DateUtils.compareDate(adverseEventDto.getStartDate().toGregorianCalendar().getTime(),startDateOfFirstCourse);
				if (dateCompare < 0) {
					throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_059", new String[]{adverseEventDto.getStartDate()+"",startDateOfFirstCourse+""},"",Locale.getDefault()));
				}
			}

			if (terminology.getCtcVersion() != null && adverseEventDto.getAdverseEventCtepTerm() != null && adverseEventDto.getAdverseEventCtepTerm().getCtepCode()!=null) {
				populateCtcTerm(adverseEventDto,adverseEvent,terminology.getCtcVersion());
			}
			
			if (terminology.getMeddraVersion() != null && adverseEventDto.getAdverseEventMeddraLowLevelTerm() != null) {
				populateLowLevelTerm(adverseEventDto.getAdverseEventMeddraLowLevelTerm() ,adverseEvent); 
			}				
			
			if (adverseEventDto.getEventApproximateTime() != null) { 
				TimeValue tv = new TimeValue();
				tv.setHour(adverseEventDto.getEventApproximateTime().getHour());
				tv.setMinute(adverseEventDto.getEventApproximateTime().getMinute());
				if (adverseEventDto.getEventApproximateTime().getAmpm().equals("AM")) {
					tv.setType(0);
				} else {
					tv.setType(1);
				}
				adverseEvent.setEventApproximateTime(tv);
			}

			if (adverseEventDto.getEventLocation() != null) { 
				adverseEvent.setEventLocation(adverseEventDto.getEventLocation());
			}
			if (adverseEventDto.getOutcome() != null) {
				populateOutcomes(adverseEventDto,adverseEvent);
			}
			
		}catch(Exception e){
			throw new CaaersSystemException(e);
		}
		
		return adverseEvent;
	}
	
	private void populateGrade(AdverseEventType adverseEventDto, AdverseEvent adverseEvent){		
		Grade grade = Grade.getByCode(adverseEventDto.getGrade());
		adverseEvent.setGrade(grade);		
	}
	private void populateHospitalization(AdverseEventType adverseEventDto, AdverseEvent adverseEvent){		
		if (adverseEventDto.getHospitalization() != null){
			Hospitalization hospitalization = Hospitalization.valueOf(adverseEventDto.getHospitalization().name());
			adverseEvent.setHospitalization(hospitalization);			
		}
		
	}	
	private void populateAttributionSummary(AdverseEventType adverseEventDto, AdverseEvent adverseEvent){
		if (adverseEventDto.getAttributionSummary() != null){
			Attribution attributionSummary = Attribution.valueOf(adverseEventDto.getAttributionSummary().name());
			adverseEvent.setAttributionSummary(attributionSummary);
		}
	}
	private LowLevelTerm getLowLevelTerm(String code, String term) {
		LowLevelTerm lowLevelTerm = null;
		lowLevelTerm=lowLevelTermDao.getByCodeAndTerm(code,term);
		return lowLevelTerm;
	}
	
	private void populateLowLevelTerm(AdverseEventMeddraLowLevelTermType adverseEventMeddraLowLevelTermType, AdverseEvent adverseEvent) {
		if (adverseEventMeddraLowLevelTermType != null) {
			AdverseEventMeddraLowLevelTermType xmlLowLevelTerm = adverseEventMeddraLowLevelTermType;
			
			LowLevelTerm lowLevelTerm = getLowLevelTerm(xmlLowLevelTerm.getMeddraCode(),xmlLowLevelTerm.getMeddraTerm());
			if (lowLevelTerm == null) {
				throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_021", new String[]{xmlLowLevelTerm.getMeddraCode().toString()},"",Locale.getDefault()));
			} else {	
				//for update, lowlevelterm already exists
				AdverseEventMeddraLowLevelTerm adverseEventMeddraLowLevelTerm = adverseEvent.getAdverseEventMeddraLowLevelTerm();
				if( adverseEventMeddraLowLevelTerm == null ) {
					adverseEventMeddraLowLevelTerm = new AdverseEventMeddraLowLevelTerm();	
				}
				
				adverseEventMeddraLowLevelTerm.setLowLevelTerm(lowLevelTerm);
				adverseEventMeddraLowLevelTerm.setAdverseEvent(adverseEvent);
				adverseEvent.setAdverseEventMeddraLowLevelTerm(adverseEventMeddraLowLevelTerm);
			}
		}
	}
	private void populateCtcTerm(AdverseEventType adverseEventDto, AdverseEvent adverseEvent,Ctc ctc) throws CaaersSystemException{
		if (adverseEventDto.getAdverseEventCtepTerm() != null && adverseEventDto.getAdverseEventCtepTerm().getCtepCode() != null) {
			//CtcTerm ctcTerm = ctcTermDao.getCtcTerm(new String[]{adverseEventDto.getCtepCode()});//getByCtepCodeandVersion(adverseEventDto.getAdverseEventCtcTerm().getCtepCode(), adverseEventDto.getAdverseEventCtcTerm().getCtcVersion());
			CtcTerm ctcTerm = ctcTermDao.getByCtepCodeandVersion(adverseEventDto.getAdverseEventCtepTerm().getCtepCode(), ctc);

			if (ctcTerm == null) {
				throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_020", new String[]{adverseEventDto.getAdverseEventCtepTerm().getCtepCode()},"",Locale.getDefault()));
			} else {
				if (ctcTerm.isOtherRequired()) {
					if (adverseEventDto.getAdverseEventCtepTerm().getOtherMeddra() != null) {
						LowLevelTerm lowLevelTerm = getLowLevelTerm(adverseEventDto.getAdverseEventCtepTerm().getOtherMeddra().getMeddraCode(),adverseEventDto.getAdverseEventCtepTerm().getOtherMeddra().getMeddraTerm());
						if (lowLevelTerm == null) {
							throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_021", new String[]{adverseEventDto.getAdverseEventCtepTerm().getOtherMeddra().getMeddraCode().toString()},"",Locale.getDefault()));
							
						} else {
							adverseEvent.setLowLevelTerm(lowLevelTerm);
						}
					} else {
						throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_022", new String[]{},"",Locale.getDefault()));
					}
				} else {
					if (adverseEventDto.getAdverseEventCtepTerm().getOtherMeddra() != null) {
						throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_023", new String[]{adverseEventDto.getAdverseEventCtepTerm().getCtepCode()},"",Locale.getDefault()));
					}
				}
				List<CtcGrade> ctcGrades = ctcTerm.getContextualGrades();
				boolean gradeAllowed = false;
				for (CtcGrade ctcGrade:ctcGrades) {
					if (ctcGrade.getGrade().getCode() == adverseEventDto.getGrade()) {
						gradeAllowed = true;
						break;
					}
				}
				if (!gradeAllowed) {
					throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_030", new String[]{adverseEventDto.getGrade()+"",adverseEventDto.getAdverseEventCtepTerm().getCtepCode()},"",Locale.getDefault()));
				}
				//for update AECtcTerm already exists
				AdverseEventCtcTerm adverseEventCtcTerm = (AdverseEventCtcTerm) adverseEvent.getAdverseEventTerm();
				if(adverseEventCtcTerm == null) {
					adverseEventCtcTerm = new AdverseEventCtcTerm();
				}				
				adverseEventCtcTerm.setCtcTerm(ctcTerm);
				adverseEventCtcTerm.setAdverseEvent(adverseEvent);
				adverseEvent.setAdverseEventTerm(adverseEventCtcTerm);
			}

		}
	}

	public void populateOutcomes(AdverseEventType adverseEventDto, AdverseEvent adverseEvent) {
		for (OutcomeType xmlOutcome:adverseEventDto.getOutcome()) {
			String xmlOc = xmlOutcome.getOutComeEnumType().name();
			gov.nih.nci.cabig.caaers.domain.OutcomeType oct = gov.nih.nci.cabig.caaers.domain.OutcomeType.valueOf(xmlOc);
			Outcome outCome = new Outcome();
			outCome.setOutcomeType(oct);
			if (oct.equals(gov.nih.nci.cabig.caaers.domain.OutcomeType.OTHER_SERIOUS) && xmlOutcome.getOther() !=null) {
				outCome.setOther(xmlOutcome.getOther());
			}
			adverseEvent.addOutcome(outCome);
		}
	}

	public void setCtcTermDao(CtcTermDao ctcTermDao) {
		this.ctcTermDao = ctcTermDao;
	}


	public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
		this.lowLevelTermDao = lowLevelTermDao;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
