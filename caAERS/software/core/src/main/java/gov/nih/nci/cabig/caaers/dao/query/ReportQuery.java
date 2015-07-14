/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.utils.CaaersUtils;

public class ReportQuery extends AbstractQuery {
	
	public static final String REPORT_ALIAS = "report";
	
	public static final String AE_REPORTING_PERIOD_ALIAS = "aeRp";
	
	public static final String EXPEDITED_AE_REPORT_ALIAS = "aeReport";
	
	public static final String REPORT_DEFINITION_ALIAS = "repDef";
	
	public static final String REPORT_VERSION_ALIAS = "repVer";
	
	public static final String TREATMENT_ASSIGNMENT_ALIAS = "trAss";
	
	public static final String TREATMENT_INFORMATION_ALIAS = "trInf";
	
	public static final String AE_RESPONSE_DESC_ALIAS = "aeResDesc";
	
	public static final String STUDY_PARTICIPANT_ALIAS = "studyPart";
	
	public static final String STUDY_SITE_ALIAS = "studySite";
	
	public ReportQuery() {
		super("select distinct "+REPORT_ALIAS+" from Report " + REPORT_ALIAS);
	}
	
	public void joinExpeditedAEReport() {
		join (REPORT_ALIAS +".aeReport " + EXPEDITED_AE_REPORT_ALIAS);
	}
	
	public void joinReportingPeriod() {
		joinExpeditedAEReport();
		join (EXPEDITED_AE_REPORT_ALIAS +".reportingPeriod "+AE_REPORTING_PERIOD_ALIAS);
	}
	
	public void joinStudy() {
		joinReportingPeriod();
		join (STUDY_PARTICIPANT_ALIAS +".studySite.study "+STUDY_ALIAS);

	}
	
	public void joinParticipant() {
		joinReportingPeriod();
		join (STUDY_PARTICIPANT_ALIAS +".participant "+PARTICIPANT_ALIAS);
	}
	
	public void joinStudyParticipantAssignment() {
		joinReportingPeriod();
		join (AE_REPORTING_PERIOD_ALIAS + ".assignment " + STUDY_PARTICIPANT_ALIAS);
	}
	
	public void joinStudySiteAssignment() {
		joinStudyParticipantAssignment();
		join (STUDY_PARTICIPANT_ALIAS + ".studySite " + STUDY_SITE_ALIAS);
	}
	
	public void filterByStudySubjectIdentifier(String studySubjectIdentifier,String operator) {
		
		final String ssid= generateParam();
    	andWhere("lower(" + STUDY_PARTICIPANT_ALIAS + ".studySubjectIdentifier) " + parseOperator(operator) + " :" + ssid);
    	if (operator.equals("like")) {
    		setParameter(ssid, getLikeValue(studySubjectIdentifier.toLowerCase()));
    	} else {
    		setParameter(ssid, studySubjectIdentifier.toLowerCase());
    	}
        
    }
	
	public void outerjoinStudyParticipantAssignment() {
		outerjoinReportingPeriod();
		leftJoin(AE_REPORTING_PERIOD_ALIAS + ".assignment " + STUDY_PARTICIPANT_ALIAS);
	}
	
	public void joinReportDefinition() {
		join (REPORT_ALIAS +".reportDefinition " + REPORT_DEFINITION_ALIAS);
	}

	public void joinReportVersion() {
		join (REPORT_ALIAS +".reportVersions " + REPORT_VERSION_ALIAS);
	}

	public void joinTreatmentAssignment() {
		joinReportingPeriod();
		join (AE_REPORTING_PERIOD_ALIAS +".treatmentAssignment " + TREATMENT_ASSIGNMENT_ALIAS);
	}
	
	public void joinTreatmentInformation() {
		joinExpeditedAEReport();
		join (EXPEDITED_AE_REPORT_ALIAS +".treatmentInformation " + TREATMENT_INFORMATION_ALIAS);
	}

	public void joinAdverseEventResponseDescription() {
		joinExpeditedAEReport();
		join (EXPEDITED_AE_REPORT_ALIAS +".responseDescription " + AE_RESPONSE_DESC_ALIAS);
	}
	
	public void outerjoinReportDefinition() {
		leftJoin (REPORT_ALIAS +".reportDefinition " + REPORT_DEFINITION_ALIAS);
	}

	public void outerjoinReportVersion() {
		leftJoin (REPORT_ALIAS +".reportVersions " + REPORT_VERSION_ALIAS);
	}

	public void outerjoinTreatmentAssignment() {
		joinReportingPeriod();
		leftJoin (AE_REPORTING_PERIOD_ALIAS +".treatmentAssignment " + TREATMENT_ASSIGNMENT_ALIAS);
	}
	
	public void outerjoinTreatmentInformation() {
		joinExpeditedAEReport();
		leftJoin(EXPEDITED_AE_REPORT_ALIAS +".treatmentInformation " + TREATMENT_INFORMATION_ALIAS);
	}

	public void outerjoinAdverseEventResponseDescription() {
		joinExpeditedAEReport();
		leftJoin(EXPEDITED_AE_REPORT_ALIAS +".responseDescription " + AE_RESPONSE_DESC_ALIAS);
	}
	
	public void outerjoinReportingPeriod() {
		joinExpeditedAEReport();
		leftJoin (EXPEDITED_AE_REPORT_ALIAS +".reportingPeriod "+AE_REPORTING_PERIOD_ALIAS);
	}
	
    public void filterByReportDefinitionName(final String name, String operator) {
    	final String pname = generateParam();
        andWhere("lower("+REPORT_DEFINITION_ALIAS+".name) " + parseOperator(operator) + " :" + pname);
        if (operator.equals("like")) {
        	setParameter(pname, getLikeValue(name.toLowerCase()) );
        } else {
        	setParameter(pname, name.toLowerCase() );
        }
    }
    
    public void filterByReportVersionStatus(final Integer code,String operator) {
        andWhere(REPORT_VERSION_ALIAS+".reportStatus " + parseOperator(operator) + " :" 
        		+ generateParam(ReportStatus.getByCode(code)));
    } 

    public void filterByReportVersionId(final String reportVersionId,String operator) {
        andWhere("lower("+REPORT_VERSION_ALIAS+".reportVersionId) " + parseOperator(operator) + " :reportVersionId");
        if (operator.equals("like")) {
        	setParameter("reportVersionId", getLikeValue(reportVersionId.toLowerCase()) );
        } else {
        	setParameter("reportVersionId", reportVersionId.toLowerCase() );
        }
    } 
    
    public void filterByAssignedIdentifer(final String assignedIdentifer,String operator) {
        andWhere("lower("+REPORT_VERSION_ALIAS+".assignedIdentifer) " + parseOperator(operator) + " :assignedIdentifer");
        if (operator.equals("like")) {
        	setParameter("assignedIdentifer", getLikeValue(assignedIdentifer.toLowerCase()) );
        } else {
        	setParameter("assignedIdentifer", assignedIdentifer.toLowerCase() );
        }   	
    }
    
    public void filterByReportIdentifer(final String reportIdentifer,String operator) {
    	joinExpeditedAEReport();
        andWhere("(lower("+REPORT_VERSION_ALIAS+".assignedIdentifer) " + parseOperator(operator) + " :reportIdIdentifer "
        		+ "OR lower(" + EXPEDITED_AE_REPORT_ALIAS + ".externalId) " + parseOperator(operator) + " :reportIdIdentifer "
        		+ "OR lower(" + REPORT_ALIAS + ".caseNumber) " + parseOperator(operator) + " :reportIdIdentifer "
        		+ "OR str(" + REPORT_ALIAS + ".id) " + parseOperator(operator) + " :reportIdIdentifer "
        		+ ")");
        if (operator.equals("like")) {
        	setParameter("reportIdIdentifer", getLikeValue(reportIdentifer.toLowerCase()) );
        } else {
        	setParameter("reportIdIdentifer", reportIdentifer.toLowerCase() );
        }   	
    }
 
    public void filterByTreatmentAssignmentCode(final String treatmentAssignmentCode,String operator) {
        andWhere("lower("+TREATMENT_ASSIGNMENT_ALIAS+".code) " + parseOperator(operator) + " :treatmentAssignmentCode");
        if (operator.equals("like")) {
        	setParameter("treatmentAssignmentCode", getLikeValue(treatmentAssignmentCode.toLowerCase()) );
        } else {
        	setParameter("treatmentAssignmentCode", treatmentAssignmentCode.toLowerCase() );
        }   	
    }
    
    public void filterByTreatmentAssignmentDescription(final String treatmentAssignmentDescription,String operator) {
        andWhere("lower("+TREATMENT_ASSIGNMENT_ALIAS+".description) " + parseOperator(operator) + " :treatmentAssignmentDescription");
        if (operator.equals("like")) {
        	setParameter("treatmentAssignmentDescription", getLikeValue(treatmentAssignmentDescription.toLowerCase()) );
        } else {
        	setParameter("treatmentAssignmentDescription", treatmentAssignmentDescription.toLowerCase() );
        }   	
    }
    
    public void filterByInvestigationalAgentAdministered(boolean flag , String operator) {
		andWhere (TREATMENT_INFORMATION_ALIAS+".investigationalAgentAdministered " + parseOperator(operator) + " :flag");
		setParameter("flag" , flag);
	}
    
    public void filterByReportVersionCreatedDate(String dateString , String operator) throws Exception {
    	andWhere(createDateQuery(REPORT_VERSION_ALIAS+".createdOn", dateString, operator));
    }
    
    public void filterByReportVersionSubmittedDate(String dateString , String operator) throws Exception {
    	andWhere(createDateQuery(REPORT_VERSION_ALIAS+".submittedOn", dateString, operator));
    }
    
    public void filterByCourseStartDate(String dateString , String operator) throws Exception {
    	andWhere(createDateQuery(AE_REPORTING_PERIOD_ALIAS+".startDate", dateString, operator));
    }

    public void filterByCourseEndDate(String dateString , String operator) throws Exception {
    	andWhere(createDateQuery(AE_REPORTING_PERIOD_ALIAS+".endDate", dateString, operator));
    }
    
    public void filterByExpeditedReportAndReportDefinition (Integer expeditedReportId , Integer reportDefinitionId) {
    	joinExpeditedAEReport();
    	joinReportDefinition();
    	andWhere (ReportQuery.EXPEDITED_AE_REPORT_ALIAS +".id = : eId");
    	andWhere (ReportQuery.REPORT_DEFINITION_ALIAS  + ".id = : rId");
    	setParameter("eId" , expeditedReportId);
    	setParameter("rId" , reportDefinitionId);
    	
    }
    
    
    public void filterByStudyAndSubjectAndSubmissionStatusAndSearchIdentifier(Study study, Participant participant, 
    		ReportStatus reportStatus, String searchIdentifier){
    	if(study != null){
    		joinStudyParticipantAssignment();
    		joinStudy();
    		andWhere(STUDY_ALIAS+".id = :" + generateParam(study.getId()));
    	}
    	if(participant != null){
    		joinStudyParticipantAssignment();
    		joinParticipant();
    		andWhere(PARTICIPANT_ALIAS+".id = :" + generateParam(participant.getId()));
    	}
    	if(searchIdentifier != null){
    		 joinExpeditedAEReport();
    		 joinReportVersion();
    		 
	    	 orWhere("lower("+EXPEDITED_AE_REPORT_ALIAS+".externalId) like :" +generateParam(getLikeValue(searchIdentifier.toLowerCase())));
	         orWhere("lower("+REPORT_ALIAS+".caseNumber) like :" + generateParam(getLikeValue(searchIdentifier.toLowerCase())));
	         if(CaaersUtils.isAnInteger(searchIdentifier)){
	        	 orWhere(REPORT_ALIAS+".id = :" + generateParam(Integer.parseInt(searchIdentifier.toLowerCase())));
	         }
	       
	         orWhere("lower("+REPORT_VERSION_ALIAS+".assignedIdentifer) like :" + generateParam(getLikeValue(searchIdentifier.toLowerCase())));
    	}
    	
    	if(reportStatus != null){
    		if(searchIdentifier == null){
    			joinReportVersion();
    		}
    		
    		andWhere(REPORT_VERSION_ALIAS+".reportStatus = :" + generateParam(reportStatus));
    	}
    	
    	orderBy("report.id desc");
    	
    }
}
