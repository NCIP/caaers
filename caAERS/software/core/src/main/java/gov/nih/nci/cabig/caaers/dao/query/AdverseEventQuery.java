/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;

import org.apache.commons.lang.StringUtils;

public class AdverseEventQuery extends AbstractQuery {
	
	public static final String AE_ALIAS = "ae";
	
	public static final String AE_TERM_ALIAS = "aeTerm";
	
	public static final String AE_REPORTING_PERIOD_ALIAS = "aeRp";
	
	public static final String TERMINOLOGY_ALIAS = "terminology";
	
	public static final String STUDY_PARTICIPANT_ALIAS = "spa";
	
	public static final String OUTCOMES_ALIAS = "outcomes";
	
	public static final String TAC = "tac";
	
	public static final String TAC_EXPECTED_AE_PROFILE = "exp";
	
	public static final String LL_TERM_ALIAS = "llt";
	
	public AdverseEventQuery() {
		super("select distinct "+AE_ALIAS+" from AdverseEvent "+ AE_ALIAS);
	}
	
	public AdverseEventQuery(String...selections) {
		super("select distinct "+AE_ALIAS+", "+StringUtils.join(selections, ", ")+" from AdverseEvent "+ AE_ALIAS);
	}
	
	public void joinAdverseEventTerm(){
		join (AE_ALIAS +".adverseEventTerm "+AE_TERM_ALIAS);
	}

	public void joinLowLevelTerm(){
		join (AE_ALIAS +".lowLevelTerm "+LL_TERM_ALIAS);
	}

	public void outerjoinLowLevelTerm(){
		leftOuterJoin (AE_ALIAS +".lowLevelTerm "+LL_TERM_ALIAS);
	}
	
	public void joinReportingPeriod() {
		join (AE_ALIAS +".reportingPeriod "+AE_REPORTING_PERIOD_ALIAS);
	}
	
	public void joinStudy() {
		joinReportingPeriod();
		join (AE_REPORTING_PERIOD_ALIAS +".assignment.studySite.study "+STUDY_ALIAS);
	}
	
	public void joinStudyIdentifiers() {
		join (STUDY_ALIAS +".identifiers sids");	
	}
	
	public void joinParticipantIdentifiers() {
		join (PARTICIPANT_ALIAS +".identifiers pids");	
	}

	public void joinParticipant() {
		joinReportingPeriod();
		join (AE_REPORTING_PERIOD_ALIAS +".assignment.participant "+PARTICIPANT_ALIAS);
	}
	
    public void joinStudyParticipantAssignment() {
    	joinReportingPeriod();
    	join (AE_REPORTING_PERIOD_ALIAS +".assignment "+STUDY_PARTICIPANT_ALIAS);
    }
    
    public void joinAeTerminology() {
    	joinStudy();
        join(STUDY_ALIAS+".aeTerminology "+TERMINOLOGY_ALIAS);
    }
    
    public void joinOutcomes() {
    	join (AE_ALIAS +".outcomes "+OUTCOMES_ALIAS);
    }
    
    public void outerjoinOutcomes() {
    	leftOuterJoin (AE_ALIAS +".outcomes "+OUTCOMES_ALIAS);
    }   
    
	public void joinTreatmentAssignment() {
		joinReportingPeriod();
		join (AE_REPORTING_PERIOD_ALIAS +".treatmentAssignment "+TAC);
	}
	
	public void joinTreatmentAssignmentExpectedAEProfile() {
		joinTreatmentAssignment();
		join (TAC +".abstractStudyInterventionExpectedAEs "+TAC_EXPECTED_AE_PROFILE);
	}

	public void outerjoinTreatmentAssignment() {
		joinReportingPeriod();
		leftOuterJoin (AE_REPORTING_PERIOD_ALIAS +".treatmentAssignment "+TAC);
	}
	/**
	public void joinIdentifiers() {
		joinStudy();
		join (STUDY_ALIAS +".identifiers "+STUDY_IDS_ALIAS);		
	}
	public void filterBySCCIdentifier() {
		orWhere(STUDY_IDS_ALIAS+".type = 'Coordinating Center Identifier'" );
	}
	
	public void filterBySFSIdentifier() {
		orWhere(STUDY_IDS_ALIAS+".type = 'Protocol Authority Identifier'" );
	}
	*/
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

    public void filterByTerminology(Integer code, String operator) {
    	andWhere(TERMINOLOGY_ALIAS+".term "+operator+" :term");
        setParameter("term", Term.getByCode(code));
    }
    
    public void filterByAEStartDate(String dateString , String operator) throws Exception {
    	andWhere(createDateQuery(AE_ALIAS+".startDate", dateString, operator));
    }

    public void filterByAEEndDate(String dateString , String operator) throws Exception {
    	andWhere(createDateQuery(AE_ALIAS+".endDate", dateString, operator));
    }

    public void filterByCourseStartDate(String dateString , String operator) throws Exception {
    	andWhere(createDateQuery(AE_REPORTING_PERIOD_ALIAS+".startDate", dateString, operator));
    }

    public void filterByCourseEndDate(String dateString , String operator) throws Exception {
    	andWhere(createDateQuery(AE_REPORTING_PERIOD_ALIAS+".endDate", dateString, operator));
    }

    public void filterByCourseNumber(Integer num , String operator) throws Exception {
    	andWhere(AE_REPORTING_PERIOD_ALIAS+".cycleNumber "+operator+" :num");
        setParameter("num", num);
    }
    
    public void filterByStudy(Study study){
    	andWhere(STUDY_ALIAS+"=:study");
    	setParameter("study", study);
    }
    
    public void filterByMatchingTermsOnExpectedAEProfileAndReportedAE(){
    	andWhere(TAC_EXPECTED_AE_PROFILE+".term="+AE_TERM_ALIAS+".term");
    }
    
}
