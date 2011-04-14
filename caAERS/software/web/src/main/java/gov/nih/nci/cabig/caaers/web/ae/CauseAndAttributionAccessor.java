package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.attribution.*;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
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
    public static final CauseAndAttributionAccessor<OtherAEIntervention, OtherInterventionAttribution> OTHER_INTERVENTION = new OtherInterventionAccessor();
    public static final CauseAndAttributionAccessor<BiologicalIntervention, BiologicalInterventionAttribution> BIOLOGICAL_INTERVENTION = new BiologicalInterventionAccessor();
    public static final CauseAndAttributionAccessor<BehavioralIntervention, BehavioralInterventionAttribution> BEHAVIORAL_INTERVENTION = new BehavioralInterventionAccessor();
    public static final CauseAndAttributionAccessor<GeneticIntervention, GeneticInterventionAttribution> GENETIC_INTERVENTION = new GeneticInterventionAccessor();
    public static final CauseAndAttributionAccessor<DietarySupplementIntervention, DietarySupplementInterventionAttribution> DIETARY_INTERVENTION = new DietarySupplementInterventionAccessor();
    public static final CauseAndAttributionAccessor<RadiationIntervention, RadiationAttribution> RADIATION = new RadiationAccessor();
    public static final CauseAndAttributionAccessor<MedicalDevice, DeviceAttribution> DEVICE = new DeviceAccessor();
    public static final String DEFAULT_NAME = "DEFAULT_VALUE";
    
    protected CauseAndAttributionAccessor() {
        KEY_TO_ACCESSOR.put(getKey(), this);
    }

    public static CauseAndAttributionAccessor<?, ?> getByKey(String key) {
        return KEY_TO_ACCESSOR.get(key);
    }

    public final C findCause(int i, ExpeditedAdverseEventReport aeReport) {
        List<C> causes = getCauseList(aeReport);
        if (causes.size() <= i) {
            throw new CaaersSystemException("Could not locate cause " + i + " for report using accessor " + getClass().getSimpleName() + ".  This may indicate a problem in generating the attribution form.");
        }
        return causes.get(i);
    }

    protected abstract List<C> getCauseList(ExpeditedAdverseEventReport aeReport);

    public abstract A createAttribution();

    public abstract List<A> getAttributionsList(AdverseEvent adverseEvent);

    public abstract String getKey();

    public abstract String getDisplayName(C c);

    private static class CourseAgentAccessor extends CauseAndAttributionAccessor<CourseAgent, CourseAgentAttribution> {
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
            return treatmentInformation == null ? Collections.<CourseAgent> emptyList() : treatmentInformation.getCourseAgents();
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

    private static class SurgeryAccessor extends CauseAndAttributionAccessor<SurgeryIntervention, SurgeryAttribution> {
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
            StringBuffer s = new StringBuffer();
            if (surgery.getInterventionSite() != null) s.append(surgery.getInterventionSite().getName());
            if (surgery.getInterventionDate() != null) s.append(" (" + DateUtils.formatDate(surgery.getInterventionDate()) + ")");
            if (s.toString().trim().length() == 0) return DEFAULT_NAME;
            return s.toString();
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
            StringBuffer s =  new StringBuffer();
            if (radiation.getAdministration() == null) return DEFAULT_NAME;
            s.append(radiation.getAdministration().getDisplayName());
            if (radiation.getDosage() != null && radiation.getDosageUnit() != null)
                s.append(" (" + radiation.getDosage() + ", " + radiation.getDosageUnit() + ")");
            return s.toString();
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
            if (StringUtils.isEmpty(device.getCommonName()) && StringUtils.isEmpty(device.getBrandName())) return DEFAULT_NAME;

            StringBuffer sb = new StringBuffer();
            if (!StringUtils.isEmpty(StringUtils.trim(device.getCommonName()))) sb.append(StringUtils.trim(device.getCommonName()));
            if (!StringUtils.isEmpty(StringUtils.trim(device.getBrandName()))) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(StringUtils.trim(device.getBrandName()));
            }
            return sb.toString();
        }
    }

    private static class OtherInterventionAccessor extends CauseAndAttributionAccessor<OtherAEIntervention, OtherInterventionAttribution> {
        @Override
        public String getKey() {
            return ExpeditedAdverseEventInputCommand.OTHERINTERVENTION_ATTRIBUTION_KEY;
        }

        @Override
        protected List<OtherAEIntervention> getCauseList(ExpeditedAdverseEventReport aeReport) {
            return aeReport.getOtherAEInterventions();
        }

        @Override
        public OtherInterventionAttribution createAttribution() {
            return new OtherInterventionAttribution();
        }

        @Override
        public List<OtherInterventionAttribution> getAttributionsList(AdverseEvent adverseEvent) {
            return adverseEvent.getOtherInterventionAttributions();
        }

        @Override
        public String getDisplayName(OtherAEIntervention i) {
            if (StringUtils.isEmpty(i.getDescription())) return DEFAULT_NAME;
            else return i.getDescription();
        }
    }


    private static class BiologicalInterventionAccessor extends CauseAndAttributionAccessor<BiologicalIntervention, BiologicalInterventionAttribution> {
        @Override
        public String getKey() {
            return ExpeditedAdverseEventInputCommand.BIOLOGICALINTERVENTION_ATTRIBUTION_KEY;
        }

        @Override
        protected List<BiologicalIntervention> getCauseList(ExpeditedAdverseEventReport aeReport) {
            return aeReport.getBiologicalInterventions();
        }

        @Override
        public BiologicalInterventionAttribution createAttribution() {
            return new BiologicalInterventionAttribution();
        }

        @Override
        public List<BiologicalInterventionAttribution> getAttributionsList(AdverseEvent adverseEvent) {
            return adverseEvent.getBiologicalInterventionAttributions();
        }

        @Override
        public String getDisplayName(BiologicalIntervention i) {
            if (StringUtils.isEmpty(i.getDescription())) return DEFAULT_NAME;
            else return i.getDescription();
        }
    }


    private static class BehavioralInterventionAccessor extends CauseAndAttributionAccessor<BehavioralIntervention, BehavioralInterventionAttribution> {
        @Override
        public String getKey() {
            return ExpeditedAdverseEventInputCommand.BEHAVIORALINTERVENTION_ATTRIBUTION_KEY;
        }

        @Override
        protected List<BehavioralIntervention> getCauseList(ExpeditedAdverseEventReport aeReport) {
            return aeReport.getBehavioralInterventions();
        }

        @Override
        public BehavioralInterventionAttribution createAttribution() {
            return new BehavioralInterventionAttribution();
        }

        @Override
        public List<BehavioralInterventionAttribution> getAttributionsList(AdverseEvent adverseEvent) {
            return adverseEvent.getBehavioralInterventionAttributions();
        }

        @Override
        public String getDisplayName(BehavioralIntervention i) {
            if (StringUtils.isEmpty(i.getDescription())) return DEFAULT_NAME;
            else return i.getDescription();
        }
    }


    private static class GeneticInterventionAccessor extends CauseAndAttributionAccessor<GeneticIntervention, GeneticInterventionAttribution> {
        @Override
        public String getKey() {
            return ExpeditedAdverseEventInputCommand.GENETICINTERVENTION_ATTRIBUTION_KEY;
        }

        @Override
        protected List<GeneticIntervention> getCauseList(ExpeditedAdverseEventReport aeReport) {
            return aeReport.getGeneticInterventions();
        }

        @Override
        public GeneticInterventionAttribution createAttribution() {
            return new GeneticInterventionAttribution();
        }

        @Override
        public List<GeneticInterventionAttribution> getAttributionsList(AdverseEvent adverseEvent) {
            return adverseEvent.getGeneticInterventionAttributions();
        }

        @Override
        public String getDisplayName(GeneticIntervention i) {
            if (StringUtils.isEmpty(i.getDescription())) return DEFAULT_NAME;
            else return i.getDescription();
        }
    }


    private static class DietarySupplementInterventionAccessor extends CauseAndAttributionAccessor<DietarySupplementIntervention, DietarySupplementInterventionAttribution> {
        @Override
        public String getKey() {
            return ExpeditedAdverseEventInputCommand.DIETARYINTERVENTION_ATTRIBUTION_KEY;
        }

        @Override
        protected List<DietarySupplementIntervention> getCauseList(ExpeditedAdverseEventReport aeReport) {
            return aeReport.getDietaryInterventions();
        }

        @Override
        public DietarySupplementInterventionAttribution createAttribution() {
            return new DietarySupplementInterventionAttribution();
        }

        @Override
        public List<DietarySupplementInterventionAttribution> getAttributionsList(AdverseEvent adverseEvent) {
            return adverseEvent.getDietarySupplementInterventionAttributions();
        }

        @Override
        public String getDisplayName(DietarySupplementIntervention i) {
            if (StringUtils.isEmpty(i.getDescription())) return DEFAULT_NAME;
            else return i.getDescription();
        }
    }

}
