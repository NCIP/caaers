package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.api.AdverseEventManagementService;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.TimeValue;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventMeddraLowLevelTermType;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventType;

public class AdverseEventConverter {
	private CtcTermDao ctcTermDao = null;
	private LowLevelTermDao lowLevelTermDao = null;

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
				adverseEvent.setStartDate(adverseEventDto.getStartDate().toGregorianCalendar().getTime());
			}
			if(adverseEventDto.getEndDate() != null){
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
				tv.setHour(adverseEventDto.getEventApproximateTime().getHour());
				tv.setMinute(adverseEventDto.getEventApproximateTime().getMinute());
				adverseEvent.setEventApproximateTime(tv);
			}
			/*
			if (adverseEventDto.isSolicited() != null){
				adverseEvent.setSolicited(adverseEventDto.isSolicited());
			}			
			
			if (adverseEventDto.isRequiresReporting() != null) {
				adverseEvent.setRequiresReporting(adverseEventDto.isRequiresReporting());
			}*/
			if (adverseEventDto.getEventLocation() != null) { 
				adverseEvent.setEventLocation(adverseEventDto.getEventLocation());
			}
			//if (adverseEventDto.getOutcome() != null) {
				//populateOutcomes(adverseEventDto,adverseEvent);
			//}
			/*
			if(adverseEventDto.getGradedDate() != null){
				adverseEvent.setGradedDate(adverseEventDto.getGradedDate().toGregorianCalendar().getTime());
			}
						
			if (adverseEventDto.isReported() != null) {
				adverseEvent.setReported(adverseEventDto.isReported());
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
				throw new CaaersSystemException ("Meddra term not found " + xmlLowLevelTerm.getMeddraCode().toString());
				
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
				throw new CaaersSystemException ("CTC term not found " + adverseEventDto.getAdverseEventCtcTerm().getCtepTerm().toString());
			} else {
				if (ctcTerm.isOtherRequired()) {
					if (adverseEventDto.getOtherMeddra() != null) {
						LowLevelTerm lowLevelTerm = getLowLevelTerm(adverseEventDto.getOtherMeddra().getMeddraCode(),adverseEventDto.getOtherMeddra().getMeddraTerm());
						if (lowLevelTerm == null) {
							throw new CaaersSystemException ("Meddra term not found " + adverseEventDto.getOtherMeddra().getMeddraCode().toString());
						} else {
							adverseEvent.setLowLevelTerm(lowLevelTerm);
						}
					} else {
						throw new CaaersSystemException ("Other(Meddra) missing.");
					}
				}
				
				AdverseEventCtcTerm adverseEventCtcTerm = new AdverseEventCtcTerm();
				adverseEventCtcTerm.setCtcTerm(ctcTerm);
				adverseEventCtcTerm.setAdverseEvent(adverseEvent);
				adverseEvent.setAdverseEventTerm(adverseEventCtcTerm);
			}
			/*
			AdverseEventCtcTermType xmlCtcTerm = adverseEventDto.getAdverseEventCtcTerm();
			AdverseEventCtcTerm adverseEventCtcTerm = new AdverseEventCtcTerm();
			CtcTerm ctcTerm = new CtcTerm();
			ctcTerm.setCtepTerm(xmlCtcTerm.getCtepTerm());
			ctcTerm.setCtepCode(xmlCtcTerm.getCtepCode());*/
			
			//adverseEventCtcTerm.setsetMeddraTerm(xmlLowLevelTerm.getMeddraTerm());
		}
	}
	
	public void setCtcTermDao(CtcTermDao ctcTermDao) {
		this.ctcTermDao = ctcTermDao;
	}


	public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
		this.lowLevelTermDao = lowLevelTermDao;
	}


}
