package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.dao.CtcTermDao;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventCtcTerm;
import gov.nih.nci.cabig.caaers.domain.AdverseEventMeddraLowLevelTerm;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcGrade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Outcome;
import gov.nih.nci.cabig.caaers.domain.TimeValue;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.AdverseEventMeddraLowLevelTermType;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.HospitalizationType;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.OutComeEnumType;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.OutcomeType;
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
	private CtcTermDao ctcTermDao = null;
	private LowLevelTermDao lowLevelTermDao = null;
	private MessageSource messageSource;

	public AdverseEvent convertAdverseEventDtoToAdverseEventDomain(AdverseEventType adverseEventDto, AeTerminology terminology) throws CaaersSystemException{
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
				if (dateCompare <= -1) {
					throw new CaaersSystemException ("WS_SAE_003", messageSource.getMessage("WS_SAE_003", new String[]{adverseEventDto.getStartDate()+""},"",Locale.getDefault()));
				}
				adverseEvent.setStartDate(adverseEventDto.getStartDate().toGregorianCalendar().getTime());
			}
			if(adverseEventDto.getEndDate() != null){
				int dateCompare = DateUtils.compareDate(adverseEventDto.getEndDate().toGregorianCalendar().getTime(), new Date());
				if (dateCompare <= -1) {
					throw new CaaersSystemException ("WS_SAE_003", messageSource.getMessage("WS_SAE_003", new String[]{adverseEventDto.getEndDate()+""},"",Locale.getDefault()));
				}
				adverseEvent.setEndDate(adverseEventDto.getEndDate().toGregorianCalendar().getTime());
			}	
			
			if (terminology.getCtcVersion() != null && adverseEventDto.getAdverseEventCtepTerm() != null && adverseEventDto.getAdverseEventCtepTerm().getCtepCode()!=null) {
				populateCtcTerm(adverseEventDto,adverseEvent,terminology.getCtcVersion());
			}
			
			if (terminology.getMeddraVersion() != null && adverseEventDto.getAdverseEventMeddraLowLevelTerm() != null) {
				populateLowLevelTerm(adverseEventDto.getAdverseEventMeddraLowLevelTerm() ,adverseEvent); 
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
			if ( e instanceof CaaersSystemException) {
				throw (CaaersSystemException)e;
			}
			throw new CaaersSystemException("WS_GEN_001", e);
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
				throw new CaaersSystemException ("WS_SAE_021",messageSource.getMessage("WS_SAE_021", new String[]{xmlLowLevelTerm.getMeddraCode().toString()},"",Locale.getDefault()));
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
				throw new CaaersSystemException ("WS_SAE_020",messageSource.getMessage("WS_SAE_020", new String[]{adverseEventDto.getAdverseEventCtepTerm().getCtepCode()},"",Locale.getDefault()));
			} else {
				if (ctcTerm.isOtherRequired()) {
					if (adverseEventDto.getAdverseEventCtepTerm().getOtherMeddra() != null) {
						LowLevelTerm lowLevelTerm = getLowLevelTerm(adverseEventDto.getAdverseEventCtepTerm().getOtherMeddra().getMeddraCode(),adverseEventDto.getAdverseEventCtepTerm().getOtherMeddra().getMeddraTerm());
						if (lowLevelTerm == null) {
							throw new CaaersSystemException ("WS_SAE_021",messageSource.getMessage("WS_SAE_021", new String[]{adverseEventDto.getAdverseEventCtepTerm().getOtherMeddra().getMeddraCode().toString()},"",Locale.getDefault()));
							
						} else {
							adverseEvent.setLowLevelTerm(lowLevelTerm);
						}
					} else {
						throw new CaaersSystemException ("WS_SAE_022",messageSource.getMessage("WS_SAE_022", new String[]{},"",Locale.getDefault()));
					}
				} else {
					if (adverseEventDto.getAdverseEventCtepTerm().getOtherMeddra() != null) {
						throw new CaaersSystemException ("WS_SAE_023",messageSource.getMessage("WS_SAE_023", new String[]{adverseEventDto.getAdverseEventCtepTerm().getCtepCode()},"",Locale.getDefault()));
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
					throw new CaaersSystemException ("WS_SAE_030",messageSource.getMessage("WS_SAE_030", new String[]{adverseEventDto.getGrade()+"",adverseEventDto.getAdverseEventCtepTerm().getCtepCode()},"",Locale.getDefault()));
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
				throw new CaaersSystemException("WS_GEN_001", e);
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
				throw new CaaersSystemException("WS_GEN_001", e);
			}
		}
		
		if ( adverseEvent.getEventApproximateTime() != null ) {
			GregorianCalendar cal = new GregorianCalendar();
			
			Calendar c = Calendar.getInstance();
			c.set(c.HOUR, adverseEvent.getEventApproximateTime().getHour());
			c.set(c.MINUTE, adverseEvent.getEventApproximateTime().getMinute());
			if ( adverseEvent.getEventApproximateTime().isAM() ) 
				c.set(c.AM_PM, 0);
			else 
				c.set(c.AM_PM, 1);
			
			cal.setTime(c.getTime());
			XMLGregorianCalendar gdate;
			try {
				gdate = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
				adverseEventDto.setDateFirstLearned(gdate);
			} catch (DatatypeConfigurationException e) {
				throw new CaaersSystemException("WS_GEN_001", e);
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

	public CtcTermDao getCtcTermDao() {
		return ctcTermDao;
	}

	public void setCtcTermDao(CtcTermDao ctcTermDao) {
		this.ctcTermDao = ctcTermDao;
	}

	public LowLevelTermDao getLowLevelTermDao() {
		return lowLevelTermDao;
	}

	public void setLowLevelTermDao(LowLevelTermDao lowLevelTermDao) {
		this.lowLevelTermDao = lowLevelTermDao;
	}

}
