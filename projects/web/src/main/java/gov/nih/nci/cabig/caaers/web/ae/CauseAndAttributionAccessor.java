package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.OtherCause;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

/**
 * This class is part of the implementation of AttributionMap and AttributionTab.
 *
 * @author Rhett Sutphin
 */
public abstract class CauseAndAttributionAccessor<C extends DomainObject, A extends AdverseEventAttribution<C>> {
    private static final Map<String, CauseAndAttributionAccessor<?, ?>> KEY_TO_ACCESSOR
        = new LinkedHashMap<String, CauseAndAttributionAccessor<?, ?>>();

    public static final CauseAndAttributionAccessor<CourseAgent, CourseAgentAttribution>
        COURSE_AGENT = new CourseAgentAccessor();
    public static final CauseAndAttributionAccessor<ConcomitantMedication, ConcomitantMedicationAttribution>
        CONCOMITANT_MEDICATION = new ConcomitantMedicationAccessor();
    public static final CauseAndAttributionAccessor<OtherCause, OtherCauseAttribution>
        OTHER_CAUSE = new OtherCauseAccessor();

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
            return AdverseEventInputCommand.COURSE_AGENT_ATTRIBUTION_KEY;
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
            TreatmentInformation treatmentInformation = aeReport.getTreatmentInformation();
            return treatmentInformation == null
                ? Collections.<CourseAgent>emptyList()
                : treatmentInformation.getCourseAgents();
        }

        @Override
        public String getDisplayName(CourseAgent courseAgent) {
            return courseAgent.getDisplayName();
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

    private static class OtherCauseAccessor extends CauseAndAttributionAccessor<OtherCause, OtherCauseAttribution> {
        @Override
        public String getKey() {
            return AdverseEventInputCommand.OTHER_CAUSES_ATTRIBUTION_KEY;
        }

        @Override
        protected List<OtherCause> getCauseList(AdverseEventReport aeReport) {
            return aeReport.getOtherCauses();
        }

        @Override
        public OtherCauseAttribution createAttribution() {
            return new OtherCauseAttribution();
        }

        @Override
        public List<OtherCauseAttribution> getAttributionsList(AdverseEvent adverseEvent) {
            return adverseEvent.getOtherCauseAttributions();
        }

        @Override
        public String getDisplayName(OtherCause otherCause) {
            return otherCause.getText();
        }
    }
}
