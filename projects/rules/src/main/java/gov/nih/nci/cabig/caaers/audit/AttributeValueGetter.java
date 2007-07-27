package gov.nih.nci.cabig.caaers.audit;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;

public class AttributeValueGetter {
	
	

	public static String getGradeFromAdverseEvent(AdverseEvent ae){
		String str = AuditStringUtils.isEmpty(ae.getGrade().getDisplayName())==true?"null":ae.getGrade().getDisplayName();
		return str;
	}
	
	public static String getTermFromAdverseEvent(AdverseEvent ae){
		String str = AuditStringUtils.isEmpty(ae.getCtcTerm().getTerm())==true?"null":ae.getCtcTerm().getTerm();
		return str;
	}
	public static String getCategoryFromAdverseEvent(AdverseEvent ae){
		String str = AuditStringUtils.isEmpty(ae.getCtcTerm().getCategory().getName())==true?"null":ae.getCtcTerm().getCategory().getName();
		return str;
	}
	public static String getHospitalizationFromAdverseEvent(AdverseEvent ae){
		String str = AuditStringUtils.isEmpty(ae.getHospitalization().getDisplayName())==true?"null":ae.getHospitalization().getDisplayName();
		return str;
	}
	public static String getExpectedFromAdverseEvent(AdverseEvent ae){
		String str = AuditStringUtils.isEmpty(ae.getExpected().toString())==true?"null":ae.getExpected().toString();
		return str;
	}
	
	public static String getAttributionFromAdverseEvent(AdverseEvent ae){
		String str = AuditStringUtils.isEmpty(ae.getAttributionSummary().getDisplayName())==true?"null":ae.getAttributionSummary().getDisplayName();
		return str;
	}
	
	public static String getIdFromAdverseEvent(AdverseEvent ae){
		String str = AuditStringUtils.isEmpty(ae.getId().toString())==true?"null":ae.getId().toString();
		return str;
	}
	
	public static String getPhaseFromStudy(Study study){
		String str = AuditStringUtils.isEmpty(study.getPhaseCode())==true?"null":study.getPhaseCode();
		return str;
	}
	
	public static String getIdFromStudy(Study study){
		String str = AuditStringUtils.isEmpty(study.getId().toString())==true?"null":study.getId().toString();
		return str;
	}
	
	public static String getIndicatorFromStudyAgent(StudyAgent sa){
		String str = AuditStringUtils.isEmpty(sa.getInvestigationalNewDrugIdentifier())==true?"null":sa.getInvestigationalNewDrugIdentifier();
		return str;
		
	}
	public static String getIdFromStudyAgent(StudyAgent studyAgent){
		String str ="null";
		if(studyAgent.getId()!=null){
		str = AuditStringUtils.isEmpty(studyAgent.getId().toString())==true?"null":studyAgent.getId().toString();
		}
		return str;
	}
}
