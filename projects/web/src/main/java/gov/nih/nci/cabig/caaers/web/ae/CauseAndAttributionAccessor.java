package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.DomainObject;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
*/
public abstract class CauseAndAttributionAccessor<C extends DomainObject, A extends AdverseEventAttribution<C>> {
    private static final Map<String, CauseAndAttributionAccessor<?, ?>> KEY_TO_ACCESSOR
        = new LinkedHashMap<String, CauseAndAttributionAccessor<?, ?>>();

    public static final CauseAndAttributionAccessor<CourseAgent, CourseAgentAttribution>
        COURSE_AGENT = new CourseAgentAccessor();
    public static final CauseAndAttributionAccessor<ConcomitantMedication, ConcomitantMedicationAttribution>
        CONCOMITANT_MEDICATION = new ConcomitantMedicationAccessor();

    protected CauseAndAttributionAccessor() {
        KEY_TO_ACCESSOR.put(getKey(), this);
    }

    public static CauseAndAttributionAccessor<?, ?> getByKey(String key) {
        return KEY_TO_ACCESSOR.get(key);
    }

    public final C findCause(int i, AdverseEventReport aeReport) {
        List<C> causes = getCauseList(aeReport);
        if (causes.size() <= i) {
            throw new CaaersSystemException(
                "Could not locate cause " + i + " for report using accessor " + getClass().getSimpleName()
                + ".  This may indicate a problem in generating the attribution form."
            );
        }
        return causes.get(i);
    }

    protected abstract List<C> getCauseList(AdverseEventReport aeReport);
    public abstract A createAttribution();
    public abstract List<A> getAttributionsList(AdverseEvent adverseEvent);
    public abstract String getKey();

    public abstract String getDisplayName(C c);

    private static class CourseAgentAccessor extends CauseAndAttributionAccessor<CourseAgent, CourseAgentAttribution> {
        @Override
        public String getKey() {
            return AdverseEventInputCommand.STUDY_AGENT_ATTRIBUTION_KEY;
        }

        @Override
        public List<CourseAgentAttribution> getAttributionsList(AdverseEvent adverseEvent) {
            return adverseEvent.getCourseAgentAttributions();
        }

        @Override
        public CourseAgentAttribution createAttribution() {
            return new CourseAgentAttribution();
        }

        @Override
        protected List<CourseAgent> getCauseList(AdverseEventReport aeReport) {
            return aeReport.getTreatmentInformation().getCourseAgents();
        }

        @Override
        public String getDisplayName(CourseAgent studyAgent) {
            return studyAgent.getStudyAgent().getAgent().getName();
        }
    }

    private static class ConcomitantMedicationAccessor extends CauseAndAttributionAccessor<ConcomitantMedication, ConcomitantMedicationAttribution> {
        @Override
        public String getKey() {
            return AdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY;
        }

        @Override
        public ConcomitantMedicationAttribution createAttribution() {
            return new ConcomitantMedicationAttribution();
        }

        @Override
        public List<ConcomitantMedication> getCauseList(AdverseEventReport aeReport) {
            return aeReport.getConcomitantMedications();
        }

        @Override
        public List<ConcomitantMedicationAttribution> getAttributionsList(AdverseEvent adverseEvent) {
            return adverseEvent.getConcomitantMedicationAttributions();
        }

        @Override
        public String getDisplayName(ConcomitantMedication concomitantMedication) {
            return concomitantMedication.getName();
        }
    }
}
