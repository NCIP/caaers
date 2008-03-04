package gov.nih.nci.cabig.caaers.audit;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;

public class AttributeValueGetter {

    public static String getGradeFromAdverseEvent(AdverseEvent ae) {
        if (ae.getGrade() == null) {
            return "null";
        }
        String str = AuditStringUtils.isEmpty(ae.getGrade().getDisplayName()) == true ? "null" : ae
                        .getGrade().getDisplayName();
        return str;
    }

    public static String getTermFromAdverseEvent(AdverseEvent ae) {
        if (ae.getAdverseEventCtcTerm().getCtcTerm() == null) {
            return "null";
        }
        String str = AuditStringUtils.isEmpty(ae.getAdverseEventCtcTerm().getCtcTerm().getTerm()) == true ? "null"
                        : ae.getAdverseEventCtcTerm().getCtcTerm().getTerm();
        return str;
    }

    public static String getCategoryFromAdverseEvent(AdverseEvent ae) {
        if (ae.getAdverseEventCtcTerm().getCtcTerm() == null) {
            return "null";
        }
        if (ae.getAdverseEventCtcTerm().getCtcTerm().getCategory() == null) {
            return "null";
        }
        String str = AuditStringUtils.isEmpty(ae.getAdverseEventCtcTerm().getCtcTerm()
                        .getCategory().getName()) == true ? "null" : ae.getAdverseEventCtcTerm()
                        .getCtcTerm().getCategory().getName();
        return str;
    }

    public static String getHospitalizationFromAdverseEvent(AdverseEvent ae) {
        if (ae.getHospitalization() == null) {
            return "null";
        }
        String str = AuditStringUtils.isEmpty(ae.getHospitalization().getDisplayName()) == true ? "null"
                        : ae.getHospitalization().getDisplayName();
        return str;
    }

    public static String getExpectedFromAdverseEvent(AdverseEvent ae) {
        if (ae.getExpected() == null) {
            return "null";
        }
        String str = AuditStringUtils.isEmpty(ae.getExpected().toString()) == true ? "null" : ae
                        .getExpected().toString();
        return str;
    }

    public static String getAttributionFromAdverseEvent(AdverseEvent ae) {
        if (ae.getAttributionSummary() == null) {
            return "null";
        }
        String str = AuditStringUtils.isEmpty(ae.getAttributionSummary().getDisplayName()) == true ? "null"
                        : ae.getAttributionSummary().getDisplayName();
        return str;
    }

    public static String getIdFromAdverseEvent(AdverseEvent ae) {
        if (ae.getId() == null) {
            return "null";
        }
        String str = AuditStringUtils.isEmpty(ae.getId().toString()) == true ? "null" : ae.getId()
                        .toString();
        return str;
    }

    public static String getPhaseFromStudy(Study study) {
        String str = AuditStringUtils.isEmpty(study.getPhaseCode()) == true ? "null" : study
                        .getPhaseCode();
        return str;
    }

    public static String getIdFromStudy(Study study) {
        if (study.getId() == null) {
            return "null";
        }
        String str = AuditStringUtils.isEmpty(study.getId().toString()) == true ? "null" : study
                        .getId().toString();
        return str;
    }

    public static String getIndicatorFromStudyAgent(StudyAgent sa) {
        String str = "null";// AuditStringUtils.isEmpty(sa.getInvestigationalNewDrugIdentifier())==true?"null":sa.getInvestigationalNewDrugIdentifier();
        return str;

    }

    public static String getIdFromStudyAgent(StudyAgent studyAgent) {
        String str = "null";
        if (studyAgent.getId() == null) {
            return str;
        }
        if (studyAgent.getId() != null) {
            str = AuditStringUtils.isEmpty(studyAgent.getId().toString()) == true ? "null"
                            : studyAgent.getId().toString();
        }
        return str;
    }
}
