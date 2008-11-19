package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.CourseAgent;
import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.MedicalDevice;
import gov.nih.nci.cabig.caaers.domain.OtherCause;
import gov.nih.nci.cabig.caaers.domain.RadiationIntervention;
import gov.nih.nci.cabig.caaers.domain.SurgeryIntervention;
import gov.nih.nci.cabig.caaers.domain.TreatmentInformation;
import gov.nih.nci.cabig.caaers.domain.attribution.AdverseEventAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DeviceAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DiseaseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.RadiationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.SurgeryAttribution;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * This class is part of the implementation of AttributionMap and AttributionTab.
 * 
 * @author Rhett Sutphin
 */
public abstract class CauseAndAttributionAccessor<C extends DomainObject, A extends AdverseEventAttribution<C>> {
    private static final Map<String, CauseAndAttributionAccessor<?, ?>> KEY_TO_ACCESSOR = new LinkedHashMap<String, CauseAndAttributionAccessor<?, ?>>();

    public static final CauseAndAttributionAccessor<CourseAgent, CourseAgentAttribution> COURSE_AGENT = new CourseAgentAccessor();

    public static final CauseAndAttributionAccessor<ConcomitantMedication, ConcomitantMedicationAttribution> CONCOMITANT_MEDICATION = new ConcomitantMedicationAccessor();

    public static final CauseAndAttributionAccessor<OtherCause, OtherCauseAttribution> OTHER_CAUSE = new OtherCauseAccessor();

    public static final CauseAndAttributionAccessor<DiseaseHistory, DiseaseAttribution> DISEASE = new DiseaseAccessor();

    public static final CauseAndAttributionAccessor<SurgeryIntervention, SurgeryAttribution> SURGERY = new SurgeryAccessor();

    public static final CauseAndAttributionAccessor<RadiationIntervention, RadiationAttribution> RADIATION = new RadiationAccessor();

    public static final CauseAndAttributionAccessor<MedicalDevice, DeviceAttribution> DEVICE = new DeviceAccessor();

    protected CauseAndAttributionAccessor() {
        KEY_TO_ACCESSOR.put(getKey(), this);
    }

    public static CauseAndAttributionAccessor<?, ?> getByKey(String key) {
        return KEY_TO_ACCESSOR.get(key);
    }

    public final C findCause(int i, ExpeditedAdverseEventReport aeReport) {
        List<C> causes = getCauseList(aeReport);
        if (causes.size() <= i) {
            throw new CaaersSystemException("Could not locate cause " + i
                            + " for report using accessor " + getClass().getSimpleName()
                            + ".  This may indicate a problem in generating the attribution form.");
        }
        return causes.get(i);
    }

    protected abstract List<C> getCauseList(ExpeditedAdverseEventReport aeReport);

    public abstract A createAttribution();

    public abstract List<A> getAttributionsList(AdverseEvent adverseEvent);

    public abstract String getKey();

    public abstract String getDisplayName(C c);

    private static class CourseAgentAccessor extends
                    CauseAndAttributionAccessor<CourseAgent, CourseAgentAttribution> {
        @Override
        public String getKey() {
            return ExpeditedAdverseEventInputCommand.COURSE_AGENT_ATTRIBUTION_KEY;
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
        protected List<CourseAgent> getCauseList(ExpeditedAdverseEventReport aeReport) {
            TreatmentInformation treatmentInformation = aeReport.getTreatmentInformation();
            return treatmentInformation == null ? Collections.<CourseAgent> emptyList()
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
            return ExpeditedAdverseEventInputCommand.CONCOMITANT_MEDICATIONS_ATTRIBUTION_KEY;
        }

        @Override
        public ConcomitantMedicationAttribution createAttribution() {
            return new ConcomitantMedicationAttribution();
        }

        @Override
        public List<ConcomitantMedication> getCauseList(ExpeditedAdverseEventReport aeReport) {
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
            return ExpeditedAdverseEventInputCommand.OTHER_CAUSES_ATTRIBUTION_KEY;
        }

        @Override
        protected List<OtherCause> getCauseList(ExpeditedAdverseEventReport aeReport) {
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

    private abstract static class SingleObjectAccessor<C extends DomainObject, A extends AdverseEventAttribution<C>> extends CauseAndAttributionAccessor<C, A> {
        @Override
        protected final List<C> getCauseList(ExpeditedAdverseEventReport aeReport) {
            C cause = getSingleCause(aeReport);
            if (cause == null || considerEmpty(cause)) {
                return Collections.emptyList();
            } else {
                return Collections.singletonList(cause);
            }
        }

        protected abstract C getSingleCause(ExpeditedAdverseEventReport aeReport);

        /**
         * Return true if the provided (non-null) cause instance should be treated as though there's
         * nothing entered. (This is to support accessors which always return a non-null dependent
         * object, even if there's been no explicit user input.)
         * <p>
         * Note that this should always return true if {@link #getDisplayName} won't be able to
         * complete, or will return null. That might not be the only case where it should be true,
         * though.
         */
        protected abstract boolean considerEmpty(C cause);
    }

    private static class DiseaseAccessor extends SingleObjectAccessor<DiseaseHistory, DiseaseAttribution> {
        @Override
        public String getKey() {
            return ExpeditedAdverseEventInputCommand.DISEASE_ATTRIBUTION_KEY;
        }

        @Override
        protected DiseaseHistory getSingleCause(ExpeditedAdverseEventReport aeReport) {
            return aeReport.getDiseaseHistory();
        }

        @Override
        protected boolean considerEmpty(DiseaseHistory cause) {
            return (cause.getCtepStudyDisease() == null && cause.getOtherPrimaryDisease() == null && cause.getMeddraStudyDisease() == null && cause.getOtherCondition() == null);
        }

        @Override
        public DiseaseAttribution createAttribution() {
            return new DiseaseAttribution();
        }

        @Override
        public List<DiseaseAttribution> getAttributionsList(AdverseEvent adverseEvent) {
            return adverseEvent.getDiseaseAttributions();
        }

        @Override
        public String getDisplayName(DiseaseHistory diseaseHistory) {
            if (diseaseHistory.getOtherCondition() != null) {
                return diseaseHistory.getOtherCondition().getTerm().getConditionName();
            } else if (diseaseHistory.getCtepStudyDisease() != null) {
                return diseaseHistory.getCtepStudyDisease().getTerm().getTerm();
            } else if (diseaseHistory.getMeddraStudyDisease() != null) {
                return diseaseHistory.getMeddraStudyDisease().getTerm().getMeddraTerm();
            } else {
                return diseaseHistory.getOtherPrimaryDisease();
            }
        }
    }

    private static class SurgeryAccessor extends
                    CauseAndAttributionAccessor<SurgeryIntervention, SurgeryAttribution> {
        @Override
        public String getKey() {
            return ExpeditedAdverseEventInputCommand.SURGERY_ATTRIBUTION_KEY;
        }

        /*
         * @Override protected SurgeryIntervention getSingleCause(ExpeditedAdverseEventReport
         * aeReport) { return aeReport.getSurgeryIntervention(); }
         * 
         * @Override protected boolean considerEmpty(SurgeryIntervention cause) { return
         * cause.getDescription() == null; }
         */

        @Override
        protected List<SurgeryIntervention> getCauseList(ExpeditedAdverseEventReport aeReport) {
            return aeReport.getSurgeryInterventions();
        }

        @Override
        public SurgeryAttribution createAttribution() {
            return new SurgeryAttribution();
        }

        @Override
        public List<SurgeryAttribution> getAttributionsList(AdverseEvent adverseEvent) {
            return adverseEvent.getSurgeryAttributions();
        }

        @Override
        public String getDisplayName(SurgeryIntervention surgery) {
            return surgery.getDescription();
        }
    }

    private static class RadiationAccessor extends CauseAndAttributionAccessor<RadiationIntervention, RadiationAttribution> {
        @Override
        public String getKey() {
            return ExpeditedAdverseEventInputCommand.RADIATION_ATTRIBUTION_KEY;
        }

        /*
         * @Override protected RadiationIntervention getSingleCause(ExpeditedAdverseEventReport
         * aeReport) { return aeReport.getRadiationIntervention(); }
         * 
         * @Override protected boolean considerEmpty(RadiationIntervention cause) { return
         * cause.getDescription() == null; }
         */

        @Override
        protected List<RadiationIntervention> getCauseList(ExpeditedAdverseEventReport aeReport) {
            return aeReport.getRadiationInterventions();
        }

        @Override
        public RadiationAttribution createAttribution() {
            return new RadiationAttribution();
        }

        @Override
        public List<RadiationAttribution> getAttributionsList(AdverseEvent adverseEvent) {
            return adverseEvent.getRadiationAttributions();
        }

        @Override
        public String getDisplayName(RadiationIntervention radiation) {
            return radiation.getDescription();
        }
    }

    private static class DeviceAccessor extends CauseAndAttributionAccessor<MedicalDevice, DeviceAttribution> {
        @Override
        public String getKey() {
            return ExpeditedAdverseEventInputCommand.DEVICE_ATTRIBUTION_KEY;
        }

        @Override
        protected List<MedicalDevice> getCauseList(ExpeditedAdverseEventReport aeReport) {
            return aeReport.getMedicalDevices();
        }

        @Override
        public DeviceAttribution createAttribution() {
            return new DeviceAttribution();
        }

        @Override
        public List<DeviceAttribution> getAttributionsList(AdverseEvent adverseEvent) {
            return adverseEvent.getDeviceAttributions();
        }

        @Override
        public String getDisplayName(MedicalDevice device) {
            StringBuffer sb = new StringBuffer();
            if (!StringUtils.isEmpty(StringUtils.trim(device.getCommonName()))) sb.append(StringUtils.trim(device.getCommonName()));
            if (!StringUtils.isEmpty(StringUtils.trim(device.getBrandName()))) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(StringUtils.trim(device.getBrandName()));
            }
            return sb.toString();
        }
    }
}
