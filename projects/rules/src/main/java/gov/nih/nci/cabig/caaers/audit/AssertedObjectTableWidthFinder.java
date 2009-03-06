package gov.nih.nci.cabig.caaers.audit;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;

import java.util.List;

public class AssertedObjectTableWidthFinder {

    public int maxWidth(List<Object> objects) {
        int max = 0;

        for (int i = 0; i < objects.size(); i++) {
            Object obj = objects.get(i);
            if (obj instanceof gov.nih.nci.cabig.caaers.domain.AdverseEvent) {
                int a = findForAdverseEvent((AdverseEvent) obj);
                if (a > max) {
                    max = a;
                }
            }
            if (obj instanceof gov.nih.nci.cabig.caaers.domain.Study) {
                int b = findForStudy((Study) obj);
                if (b > max) {
                    max = b;
                }
            }
            if (obj instanceof gov.nih.nci.cabig.caaers.domain.StudyAgent) {
                int c = findForStudyAgent((StudyAgent) obj);
                if (c > max) {
                    max = c;
                }
            }

        }
        return max;
    }

    private int findForAdverseEvent(AdverseEvent ae) {
        int i = 0;
        int j = 5 + LogTitle.ADVERSE_EVENT.getTitle().length();
        if (j > i) {
            i = j;
        }
        j = 5 + LogTitle.GRADE.getTitle().length();
        if (j > i) {
            i = j;
        }
        j = 5 + AttributeValueGetter.getGradeFromAdverseEvent(ae).length();
        if (j > i) {
            i = j;
        }
        j = 5 + LogTitle.TERM.getTitle().length();
        if (j > i) {
            i = j;
        }
        j = 5 + AttributeValueGetter.getTermFromAdverseEvent(ae).length();
        if (j > i) {
            i = j;
        }
        j = 5 + LogTitle.CATEGORY.getTitle().length();
        if (j > i) {
            i = j;
        }
        j = 5 + AttributeValueGetter.getCategoryFromAdverseEvent(ae).length();
        if (j > i) {
            i = j;
        }
        j = 5 + LogTitle.HOSPITALIZATION.getTitle().length();
        if (j > i) {
            i = j;
        }
        j = 5 + AttributeValueGetter.getHospitalizationFromAdverseEvent(ae).length();
        if (j > i) {
            i = j;
        }
        j = 5 + LogTitle.EXPECTED.getTitle().length();
        if (j > i) {
            i = j;
        }
        j = 5 + AttributeValueGetter.getExpectedFromAdverseEvent(ae).length();
        if (j > i) {
            i = j;
        }
        j = 5 + LogTitle.ATTRRIBUTION.getTitle().length();
        if (j > i) {
            i = j;
        }
        j = 5 + AttributeValueGetter.getAttributionFromAdverseEvent(ae).length();
        return i;
    }

    private int findForStudy(Study study) {
        int i = 0;
        int j = 5 + LogTitle.STUDY.getTitle().length();
        if (j > i) {
            i = j;
        }
        j = 5 + LogTitle.PHASE.getTitle().length();
        if (j > i) {
            i = j;
        }
        j = 5 + AttributeValueGetter.getPhaseFromStudy(study).length();
        if (j > i) {
            i = j;
        }
        return i;

    }

    private int findForStudyAgent(StudyAgent studyAgent) {
        int i = 0;
        int j = 5 + LogTitle.STUDY_AGENT.getTitle().length();
        j = 5 + LogTitle.INVESTIGATIONAL_NEW_DRUG_INDICATOR.getTitle().length();
        if (j > i) {
            i = j;
        }
        j = 5 + AttributeValueGetter.getIndicatorFromStudyAgent(studyAgent).length();
        if (j > i) {
            i = j;
        }
        return i;

    }

}
