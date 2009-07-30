package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.AdverseEventManagementService;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.TimeValue;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventMeddraLowLevelTermType;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventType;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.OutcomeType;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;

public class AdverseEventConverter {
	private CtcTermDao ctcTermDao = null;
	private LowLevelTermDao lowLevelTermDao = null;
	private MessageSource messageSource;

	public void convertAdverseEventDtoToAdverseEventDomain(gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventType adverseEventDto, 
			AdverseEvent adverseEvent, String operation) throws CaaersSystemException{
		if(adverseEvent == null){
			adverseEvent = new AdverseEvent();
		}
		
		try{
			if (adverseEventDto.getVerbatim() != null) { 
				adverseEvent.setDetailsForOther(adverseEventDto.getVerbatim());
			}
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

			if (operation.equals(AdverseEventManagementService.CREATE)) {
				populateCtcTerm(adverseEventDto,adverseEvent);
				if (adverseEventDto.getAdverseEventMeddraLowLevelTerm() != null) {
					populateLowLevelTerm(adverseEventDto.getAdverseEventMeddraLowLevelTerm() ,adverseEvent); 
				}				
			}
			
			if (adverseEventDto.getEventApproximateTime() != null) { 
				TimeValue tv = new TimeValue();
				tv.setHour(adverseEventDto.getEventApproximateTime().getHour().intValue());
				tv.setMinute(adverseEventDto.getEventApproximateTime().getMinute().intValue());
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
			/*
			if(adverseEventDto.getGradedDate() != null){
				adverseEvent.setGradedDate(adverseEventDto.getGradedDate().toGregorianCalendar().getTime());
			}
						
			if (adverseEventDto.isReported() != null) {
				adverseEvent.setReported(adverseEventDto.isReported());
			}
			
			if (adverseEventDto.isSolicited() != null){
				adverseEvent.setSolicited(adverseEventDto.isSolicited());
			}			
			
			if (adverseEventDto.isRequiresReporting() != null) {
				adverseEvent.setRequiresReporting(adverseEventDto.isRequiresReporting());
			}*/			
			
		}catch(Exception e){
			throw new CaaersSystemException(e);
		}
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
				AdverseEventMeddraLowLevelTerm adverseEventMeddraLowLevelTerm = new AdverseEventMeddraLowLevelTerm();
				adverseEventMeddraLowLevelTerm.setLowLevelTerm(lowLevelTerm);
				adverseEventMeddraLowLevelTerm.setAdverseEvent(adverseEvent);
				adverseEvent.setAdverseEventMeddraLowLevelTerm(adverseEventMeddraLowLevelTerm);
			}
		}
	}
	private void populateCtcTerm(AdverseEventType adverseEventDto, AdverseEvent adverseEvent) throws CaaersSystemException{
		if (adverseEventDto.getAdverseEventCtcTerm() != null) {
			CtcTerm ctcTerm = ctcTermDao.getCtcTerm(new String[]{adverseEventDto.getAdverseEventCtcTerm().getCtepTerm()});//getByCtepCodeandVersion(adverseEventDto.getAdverseEventCtcTerm().getCtepCode(), adverseEventDto.getAdverseEventCtcTerm().getCtcVersion());
			if (ctcTerm == null) {
				throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_020", new String[]{adverseEventDto.getAdverseEventCtcTerm().getCtepTerm().toString()},"",Locale.getDefault()));
			} else {
				if (ctcTerm.isOtherRequired()) {
					if (adverseEventDto.getOtherMeddra() != null) {
						LowLevelTerm lowLevelTerm = getLowLevelTerm(adverseEventDto.getOtherMeddra().getMeddraCode(),adverseEventDto.getOtherMeddra().getMeddraTerm());
						if (lowLevelTerm == null) {
							throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_021", new String[]{adverseEventDto.getOtherMeddra().getMeddraCode().toString()},"",Locale.getDefault()));
							
						} else {
							adverseEvent.setLowLevelTerm(lowLevelTerm);
						}
					} else {
						throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_022", new String[]{},"",Locale.getDefault()));
					}
				} else {
					if (adverseEventDto.getOtherMeddra() != null) {
						throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_023", new String[]{adverseEventDto.getAdverseEventCtcTerm().getCtepTerm()},"",Locale.getDefault()));
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
					throw new CaaersSystemException (messageSource.getMessage("WS_AEMS_030", new String[]{adverseEventDto.getGrade()+"",adverseEventDto.getAdverseEventCtcTerm().getCtepTerm()},"",Locale.getDefault()));
				}
				AdverseEventCtcTerm adverseEventCtcTerm = new AdverseEventCtcTerm();
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
