package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.TimeValue;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.*;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.context.MessageSource;

public class SAEEvaluationAdverseEventConverter {
	private MessageSource messageSource;

	public AdverseEvent convertAdverseEventDtoToAdverseEventDomain(AdverseEventType adverseEventDto) throws CaaersSystemException{
		AdverseEvent adverseEvent = new AdverseEvent();
		
		try{
			if (adverseEventDto.getVerbatim() != null) { 
				adverseEvent.setDetailsForOther(adverseEventDto.getVerbatim());
			}
			populateGrade(adverseEventDto,adverseEvent);
			populateHospitalization(adverseEventDto,adverseEvent);
			adverseEvent.setExpected(adverseEventDto.isExpected());
	
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
	  
			
			if (adverseEventDto.getDateFirstLearned() != null) { 
				TimeValue tv = new TimeValue();
				tv.setHour(adverseEventDto.getDateFirstLearned().getHour());
				tv.setMinute(adverseEventDto.getDateFirstLearned().getMinute());
				if (adverseEventDto.getDateFirstLearned().getHour() < 12) { //AM : if time until 12:00:00
					tv.setType(0);
				} else {
					tv.setType(1);
				}
				adverseEvent.setEventApproximateTime(tv);
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
		if (adverseEventDto.getGrade() == null ) return;
		Grade grade = Grade.getByCode(adverseEventDto.getGrade());
		adverseEvent.setGrade(grade);		
	}
	
	private void populateHospitalization(AdverseEventType adverseEventDto, AdverseEvent adverseEvent){		
		if (adverseEventDto.getHospitalization() != null){
			Hospitalization hospitalization = Hospitalization.valueOf(adverseEventDto.getHospitalization().name());
			adverseEvent.setHospitalization(hospitalization);			
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
	
	public void convertAdverseEventDomainToAdverseEventDto(AdverseEvent adverseEvent,
			AdverseEventType adverseEventDto) throws CaaersSystemException{
		
		if(adverseEventDto == null ) {
			adverseEventDto = new AdverseEventType();
		}
		
		adverseEventDto.setVerbatim(adverseEvent.getDetailsForOther());
		if ( adverseEvent.getGrade() != null) {
			adverseEventDto.setGrade(adverseEvent.getGrade().getCode());
		}
		
		if (adverseEvent.getHospitalization() != null )
			adverseEventDto.setHospitalization(HospitalizationType.valueOf(adverseEvent.getHospitalization().getName()));
		
		if ( adverseEvent.getStartDate() != null) {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(adverseEvent.getStartDate());
			XMLGregorianCalendar gdate;
			try {
				gdate = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
				adverseEventDto.setStartDate(gdate);
			} catch (DatatypeConfigurationException e) {
				throw new CaaersSystemException(e);
			}
		}
		
		if ( adverseEvent.getEndDate() != null) {
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(adverseEvent.getEndDate());
			XMLGregorianCalendar gdate;
			try {
				gdate = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
				adverseEventDto.setEndDate(gdate);
			} catch (DatatypeConfigurationException e) {
				throw new CaaersSystemException(e);
			}
		}
		
		if ( adverseEvent.getEventApproximateTime() != null ) {
			GregorianCalendar cal = new GregorianCalendar();
			
			Date date = new Date();
			date.setHours(adverseEvent.getEventApproximateTime().getHour());
			date.setMinutes(adverseEvent.getEventApproximateTime().getMinute());
			
			cal.setTime(date);
			XMLGregorianCalendar gdate;
			try {
				gdate = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
				adverseEventDto.setDateFirstLearned(gdate);
			} catch (DatatypeConfigurationException e) {
				throw new CaaersSystemException(e);
			}
			
		}
				
		adverseEventDto.setOutcome(getOutcomeTypes(adverseEvent));
				
	}
	
	public List<OutcomeType> getOutcomeTypes(AdverseEvent adverseEvent) {
		
		List<OutcomeType> types = new ArrayList<OutcomeType>();
		for (Outcome outCome : adverseEvent.getOutcomes()) {
			if ( outCome.getOutcomeType().equals(gov.nih.nci.cabig.caaers.domain.OutcomeType.OTHER_SERIOUS) && outCome.getOther() != null) {
				OutcomeType outcomeType = new OutcomeType();
				outcomeType.setOutComeEnumType(OutComeEnumType.fromValue(gov.nih.nci.cabig.caaers.domain.OutcomeType.OTHER_SERIOUS.getName()));
				outcomeType.setOther(outCome.getOther());
				types.add(outcomeType);
			} else {
				OutcomeType outcomeType = new OutcomeType();
				OutComeEnumType enumType = OutComeEnumType.fromValue(outCome.getOutcomeType().getName());
				outcomeType.setOutComeEnumType(enumType);
				outcomeType.setOther(null);
				types.add(outcomeType);
			}
		}
		return types;

	}
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
