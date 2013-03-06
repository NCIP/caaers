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

public class SafetySignalingQuery extends AbstractQuery {
	
	public static final String AE_ALIAS = "ae";
	
	public static final String AE_TERM_ALIAS = "aeTerm";
	
	public static final String AE_REPORTING_PERIOD_ALIAS = "aeRp";
	
	public static final String TERMINOLOGY_ALIAS = "terminology";
	
	public static final String STUDY_PARTICIPANT_ALIAS = "spa";
	
	public static final String OUTCOMES_ALIAS = "outcomes";
	
	public static final String TAC = "tac";
	
	public static final String TAC_EXPECTED_AE_PROFILE = "exp";
	
	public static final String LL_TERM_ALIAS = "llt";
	
	public SafetySignalingQuery() {
		super("select distinct "+AE_ALIAS+" from AdverseEvent "+ AE_ALIAS);
	}
	
	public SafetySignalingQuery(String...selections) {
		super("select distinct "+AE_ALIAS+", "+StringUtils.join(selections, ", ")+" from AdverseEvent "+ AE_ALIAS);
	}
	
	public void joinAdverseEventTerm(){
		join (AE_ALIAS +".adverseEventTerm "+AE_TERM_ALIAS);
	}

	public void joinReportingPeriod() {
		join (AE_ALIAS +".reportingPeriod "+AE_REPORTING_PERIOD_ALIAS);
	}
	
	public void joinStudy() {
		joinTreatmentAssignment();
		join (TAC +".study "+STUDY_ALIAS);
	}
	
	public void joinStudyParticipantAssignment() {
    	joinReportingPeriod();
    	join (AE_REPORTING_PERIOD_ALIAS +".assignment "+STUDY_PARTICIPANT_ALIAS);
    }
	
	public void joinTreatmentAssignment() {
		joinReportingPeriod();
		join (AE_REPORTING_PERIOD_ALIAS +".treatmentAssignment "+TAC);
	}
	
	public void joinTreatmentAssignmentExpectedAEProfile() {
		joinTreatmentAssignment();
		join (TAC +".abstractStudyInterventionExpectedAEs "+TAC_EXPECTED_AE_PROFILE);
	}
	
	public void joinObservedAE() {
		joinTreatmentAssignment();
		join (TAC +".study "+STUDY_ALIAS);
	}

    public void filterByStudy(Study study){
    	andWhere(STUDY_ALIAS+"=:study");
    	setParameter("study", study);
    }
    
    public void filterByMatchingTermsOnExpectedAEProfileAndReportedAE(){
    	andWhere(TAC_EXPECTED_AE_PROFILE+".term="+AE_TERM_ALIAS+".term");
    }
    
}
