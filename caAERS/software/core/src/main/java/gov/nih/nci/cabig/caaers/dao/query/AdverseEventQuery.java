package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Term;

public class AdverseEventQuery extends AbstractQuery {
	
	public static final String AE_ALIAS = "ae";
	
	public static final String AE_TERM_ALIAS = "aeTerm";
	
	public static final String STUDY_ALIAS = "s";
	
	public static final String PARTICIPANT_ALIAS = "p";
	
	public static final String AE_REPORTING_PERIOD_ALIAS = "aeRp";
	
	public static final String TERMINOLOGY_ALIAS = "terminology";
	
	public AdverseEventQuery() {
		super("select distinct "+AE_ALIAS+" from AdverseEvent "+ AE_ALIAS);
	}
	
	public void joinAdverseEventTerm(){
		join (AE_ALIAS +".adverseEventTerm "+AE_TERM_ALIAS);
	}
	
	public void joinReportingPeriod() {
		join (AE_ALIAS +".reportingPeriod "+AE_REPORTING_PERIOD_ALIAS);
	}
	
	public void joinStudy() {
		joinReportingPeriod();
		join (AE_REPORTING_PERIOD_ALIAS +".assignment.studySite.study "+STUDY_ALIAS);
	}

	public void joinParticipant() {
		joinReportingPeriod();
		join (AE_REPORTING_PERIOD_ALIAS +".assignment.participant "+PARTICIPANT_ALIAS);
	}
	
    public void joinAeTerminology() {
    	joinStudy();
        join(STUDY_ALIAS+".aeTerminology "+TERMINOLOGY_ALIAS);
    }
    
	
	public void filterByCtcTerm(String term , String operator) {
		andWhere (AE_TERM_ALIAS+".term.term "+operator+" :term");
		if (operator.equals("like")) {
			setParameter("term" , getLikeValue(term));
		} else {
			setParameter("term" , term);
		}		
	}
	public void filterBySolicited(boolean flag , String operator) {
		andWhere (AE_ALIAS+".solicited "+operator+" :flag");
		setParameter("flag" , flag);
	}

	public void filterByGrade(Integer code , String operator) {
		andWhere (AE_ALIAS+".grade "+operator+" :grade");
		setParameter("grade" , Grade.getByCode(code));
	}

	public void filterByHospitalization(Integer code , String operator) {
		andWhere (AE_ALIAS+".hospitalization "+operator+" :hospitalization");
		setParameter("hospitalization" , Hospitalization.getByCode(code));
	}
	
	public void filterByExpected(boolean flag , String operator) {
		andWhere (AE_ALIAS+".expected "+operator+" :flag");
		setParameter("flag" , flag);
	}
	
	public void filterByAttribution(Integer code , String operator) {
		andWhere (AE_ALIAS+".attributionSummary "+operator+" :attributionSummary");
		setParameter("attributionSummary" , Attribution.getByCode(code));
	}
	
	public void filterByVerbatim(String verbatim , String operator) {
		andWhere (AE_ALIAS+".detailsForOther "+operator+" :verbatim");
		if (operator.equals("like")) {
			setParameter("verbatim" , getLikeValue(verbatim));
		} else {
			setParameter("verbatim" , verbatim);
		}
	}
	
	
    // shortTitle
    public void filterByStudyShortTitle(final String shortTitleText , String operator) {
    	andWhere("lower(s.shortTitle) "+operator+" :shortTitleText");
        
    	if (operator.equals("like")) {
    		setParameter("shortTitleText", getLikeValue(shortTitleText.toLowerCase()));
    	} else {
    		setParameter("shortTitleText", shortTitleText.toLowerCase());
    	}
    }

    // longTitle
    public void filterByStudyLongTitle(final String longTitleText ,String operator) {
    	andWhere("lower(s.longTitle) "+operator+" :longTitleText");
        
    	if (operator.equals("like")) {
    		setParameter("longTitleText", getLikeValue(longTitleText.toLowerCase()));
    	} else {
    		setParameter("longTitleText", longTitleText.toLowerCase());
    	}
    }
    
    // id
    public void filterStudyById(final Integer id,String operator) {
        andWhere("s.id "+operator+" :ID");
        setParameter("ID", id);
    } 
    
//  participant-id
    public void filterByParticipantId(final Integer id,String operator) {
        andWhere("p.id "+operator+" :id");
        setParameter("id", id);
    }    
    // participant-FirstName
    public void filterByParticipantFirstName(final String fName,String operator) {
        andWhere("lower(p.firstName) "+operator+" :pfName");
        if (operator.equals("like")) {
        	setParameter("pfName", getLikeValue(fName.toLowerCase()));
        } else {
        	setParameter("pfName", fName.toLowerCase());
        }
    }

    // participant - LastName
    public void filterByParticipantLastName(final String lName,String operator) {
        andWhere("lower(p.lastName) "+operator+" :plName");
        if (operator.equals("like")) {
        	setParameter("plName", getLikeValue(lName.toLowerCase()) );
        } else {
        	setParameter("plName", lName.toLowerCase() );
        }
    }

    // participant - Ethnicity
    public void filterByParticipantEthnicity(String ethenicity,String operator) {
        andWhere("lower(p.ethnicity) "+operator+" :pEthenicity");
        setParameter("pEthenicity", ethenicity.toLowerCase() );
    }

    // participant - Race
    public void filterByParticipantRace(String race,String operator) {
        andWhere("lower(p.race) "+operator+" :pRace");
        setParameter("pRace", race.toLowerCase() );
    }
    
    // p.gender
    public void filterByParticipantGender(final String gender,String operator) {
        andWhere("lower(p.gender) "+operator+" :pGender");
        setParameter("pGender", gender.toLowerCase());
    }
    
    public void filterByTerminology(Integer code, String operator) {
    	andWhere(TERMINOLOGY_ALIAS+".term "+operator+" :term");
        setParameter("term", Term.getByCode(code));
    }
    
    
}
