package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.AdverseEventReportingPeriodDao;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventType;
import gov.nih.nci.cabig.caaers.webservice.adverseevent.LowLevelTermType;

public class AdverseEventConverter {
	private CtcTermDao ctcTermDao = null;

	public void convertAdverseEventDtoToAdverseEventDomain(gov.nih.nci.cabig.caaers.webservice.adverseevent.AdverseEventType adverseEventDto, AdverseEvent adverseEvent) throws CaaersSystemException{
		if(adverseEvent == null){
			adverseEvent = new AdverseEvent();
		}
		
		try{
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
		//	populateLowLevelTerm(adverseEventDto,adverseEvent);
			
			 
			populateCtcTerm(adverseEventDto,adverseEvent);
		}catch(Exception e){
			throw new CaaersSystemException(e);
		}
	}
	
	private void populateCourseAgentAttribution(AdverseEventType adverseEventDto, AdverseEvent adverseEvent) {
		
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
	private void populateLowLevelTerm(AdverseEventType adverseEventDto, AdverseEvent adverseEvent) {
		if (adverseEventDto.getLowLevelTerm() != null) {
			LowLevelTermType xmlLowLevelTerm = adverseEventDto.getLowLevelTerm();
			LowLevelTerm lowLevelTerm = new LowLevelTerm();
			lowLevelTerm.setMeddraCode(xmlLowLevelTerm.getMeddraCode());
			lowLevelTerm.setMeddraTerm(xmlLowLevelTerm.getMeddraTerm());
		}
	}
	private void populateCtcTerm(AdverseEventType adverseEventDto, AdverseEvent adverseEvent) throws CaaersSystemException{
		if (adverseEventDto.getAdverseEventCtcTerm() != null) {
			CtcTerm ctcTerm = ctcTermDao.getCtcTerm(new String[]{adverseEventDto.getAdverseEventCtcTerm().getCtepTerm()});//getByCtepCodeandVersion(adverseEventDto.getAdverseEventCtcTerm().getCtepCode(), adverseEventDto.getAdverseEventCtcTerm().getCtcVersion());
			if (ctcTerm == null) {
				throw new CaaersSystemException ("CTC term not found " + adverseEventDto.getAdverseEventCtcTerm().getCtepTerm().toString());
			} else {
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


}
