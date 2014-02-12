package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.attribution.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ExpeditedAdverseEventSynchronizer implements Migrator<ExpeditedAdverseEventReport> {
    public void migrate(ExpeditedAdverseEventReport xmlAeReport, ExpeditedAdverseEventReport dbAeReport, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

        List<AdverseEvent> newlyFoundAEs = new ArrayList<AdverseEvent>();

        //create an index of AEs
        HashMap<Integer, AdverseEvent> aeIndex = new HashMap<Integer, AdverseEvent>();
        for(AdverseEvent ae : dbAeReport.getAdverseEvents()){ aeIndex.put(ae.getId(), ae);}

        //try to find the AE in source , if found synchronize it.
        for(AdverseEvent ae : xmlAeReport.getAdverseEventsInternal()){
            AdverseEvent aeFound = dbAeReport.findAdverseEventByIdTermAndDates(ae);
            if(aeFound != null) {
                synchronizeAdverseEvent(ae, aeFound);
                aeIndex.remove(aeFound.getId());
            }else {
                newlyFoundAEs.add(ae);
            }
            synchronizeDiseaseAttributions(dbAeReport, aeFound != null ? aeFound : ae);
            synchronizeRadiationAttributions(dbAeReport, aeFound != null ? aeFound : ae);
            synchronizeSurgeryAttributions(dbAeReport, aeFound != null ? aeFound : ae);
            synchronizeCourseAgentAttributions(dbAeReport, aeFound != null ? aeFound : ae);
            synchronizeDeviceAttributions(dbAeReport, aeFound != null ? aeFound : ae);
            synchronizeConMedAttributions(dbAeReport, aeFound != null ? aeFound : ae);
            synchronizeOtherCauseAttributions(dbAeReport, aeFound != null ? aeFound : ae);
            synchronizeOtherAeInterventionAttributions(dbAeReport, aeFound != null ? aeFound : ae);
        }

        //delete the AE in destination, which are not present in source
        for(AdverseEvent ae : aeIndex.values()){
            dbAeReport.removeAdverseEvent(ae);
        }
        //add the new AEs that are present in source.
        for(AdverseEvent ae : newlyFoundAEs){
            dbAeReport.addAdverseEvent(ae);
        }
    }

    public void synchronizeAdverseEvent(AdverseEvent xmlAe, AdverseEvent dbAe){
        dbAe.setStartDate(xmlAe.getStartDate());
        dbAe.setEndDate(xmlAe.getEndDate());
        if(StringUtils.isBlank(dbAe.getReporterEmail())) {
        	dbAe.setReporterEmail(xmlAe.getReporterEmail());
        }
    }

    public void synchronizeDiseaseAttributions( ExpeditedAdverseEventReport aeReport,  AdverseEvent ae){
        for(DiseaseAttribution attribution : ae.getDiseaseAttributions()){
            attribution.setCause(aeReport.getDiseaseHistory());
        }
    }

    private void synchronizeRadiationAttributions(ExpeditedAdverseEventReport aeReport, AdverseEvent ae){
        List<RadiationIntervention> interventions = new ArrayList<RadiationIntervention>(aeReport.getRadiationInterventions());
        for(RadiationAttribution aei : ae.getRadiationAttributions()){
            final RadiationIntervention intervention = aei.getCause();
            RadiationIntervention cause = CollectionUtils.find(interventions, new Predicate<RadiationIntervention>() {
                public boolean evaluate(RadiationIntervention newIntervention) {
                    return DateUtils.compareDate(newIntervention.getLastTreatmentDate(), intervention.getLastTreatmentDate()) == 0 &&
                            newIntervention.getAdministration() == newIntervention.getAdministration();
                }
            });
            if(cause != null){
                aei.setCause(cause);
                interventions.remove(cause);
            }
        }
    }

    private void synchronizeSurgeryAttributions(ExpeditedAdverseEventReport aeReport, AdverseEvent ae){
        List<SurgeryIntervention> interventions = new ArrayList<SurgeryIntervention>(aeReport.getSurgeryInterventions());
        for(SurgeryAttribution aei : ae.getSurgeryAttributions()){
            final SurgeryIntervention intervention = aei.getCause();
            SurgeryIntervention cause = CollectionUtils.find(interventions, new Predicate<SurgeryIntervention>() {
                public boolean evaluate(SurgeryIntervention newIntervention) {
                    return DateUtils.compareDate(newIntervention.getInterventionDate(), intervention.getInterventionDate()) == 0 &&
                            ObjectUtils.equals(newIntervention.getInterventionSite(), intervention.getInterventionSite());
                }
            });
            if(cause != null){
                aei.setCause(cause);
                interventions.remove(cause);
            }
        }
    }

    private void synchronizeCourseAgentAttributions(ExpeditedAdverseEventReport aeReport, AdverseEvent ae){
        List<CourseAgent> interventions = new ArrayList<CourseAgent>(aeReport.getTreatmentInformation().getCourseAgents());
        for(CourseAgentAttribution aei : ae.getCourseAgentAttributions()){
            final CourseAgent intervention = aei.getCause();
            CourseAgent cause = CollectionUtils.find(interventions, new Predicate<CourseAgent>() {
                public boolean evaluate(CourseAgent newIntervention) {
                    return  ObjectUtils.equals(newIntervention.getStudyAgent().getId(), intervention.getStudyAgent().getId());
                }
            });
            if(cause != null){
                aei.setCause(cause);
                interventions.remove(cause);
            }
        }
    }

    private void synchronizeDeviceAttributions(ExpeditedAdverseEventReport aeReport, AdverseEvent ae){
        List<MedicalDevice> interventions = new ArrayList<MedicalDevice>(aeReport.getMedicalDevices());
        for(DeviceAttribution aei : ae.getDeviceAttributions()){
            final MedicalDevice intervention = aei.getCause();
            MedicalDevice cause = aeReport.findReportMedicalDevice(intervention);
            if(cause != null){
                aei.setCause(cause);
                interventions.remove(cause);
            }
        }
    }

    private void synchronizeConMedAttributions(ExpeditedAdverseEventReport aeReport, AdverseEvent ae){
        List<ConcomitantMedication> interventions = new ArrayList<ConcomitantMedication>(aeReport.getConcomitantMedications());
        for(ConcomitantMedicationAttribution aei : ae.getConcomitantMedicationAttributions()){
            final ConcomitantMedication intervention = aei.getCause();
            ConcomitantMedication cause = CollectionUtils.find(interventions, new Predicate<ConcomitantMedication>() {
                public boolean evaluate(ConcomitantMedication newIntervention) {
                    return StringUtils.equals(newIntervention.getAgentName(), intervention.getAgentName());
                }
            });
            if(cause != null){
                aei.setCause(cause);
                interventions.remove(cause);
            }
        }
    }

    private void synchronizeOtherCauseAttributions(ExpeditedAdverseEventReport aeReport, AdverseEvent ae){
        List<OtherCause> interventions = new ArrayList<OtherCause>(aeReport.getOtherCauses());
        for(OtherCauseAttribution aei : ae.getOtherCauseAttributions()){
            final OtherCause intervention = aei.getCause();
            OtherCause cause = CollectionUtils.find(interventions, new Predicate<OtherCause>() {
                public boolean evaluate(OtherCause newIntervention) {
                    return StringUtils.equals(newIntervention.getText(), intervention.getText());
                }
            });
            if(cause != null){
                aei.setCause(cause);
                interventions.remove(cause);
            }
        }
    }

    public void synchronizeOtherAeInterventionAttributions(ExpeditedAdverseEventReport aeReport, AdverseEvent ae){
        synchronizeAbstractAeInterventionAttributions(aeReport.getOtherAEInterventions(), ae.getOtherInterventionAttributions());
        synchronizeAbstractAeInterventionAttributions(aeReport.getBehavioralInterventions(), ae.getBehavioralInterventionAttributions());
        synchronizeAbstractAeInterventionAttributions(aeReport.getBiologicalInterventions(), ae.getBiologicalInterventionAttributions());
        synchronizeAbstractAeInterventionAttributions(aeReport.getDietaryInterventions(), ae.getDietarySupplementInterventionAttributions());
        synchronizeAbstractAeInterventionAttributions(aeReport.getGeneticInterventions(), ae.getGeneticInterventionAttributions());
    }

    public void synchronizeAbstractAeInterventionAttributions( List<? extends AbstractAEIntervention> aeInterventions, List<? extends AdverseEventAttribution> aeAttributions){
        List<AbstractAEIntervention> interventions = new ArrayList<AbstractAEIntervention>(aeInterventions);
        for(AdverseEventAttribution aei : aeAttributions){
            final AbstractAEIntervention intervention = (AbstractAEIntervention) aei.getCause();
            AbstractAEIntervention cause = CollectionUtils.find(interventions, new Predicate<AbstractAEIntervention>() {
                public boolean evaluate(AbstractAEIntervention newIntervention) {
                    return StringUtils.equals(newIntervention.getDescription(), intervention.getDescription());
                }
            });
            if(cause != null){
                aei.setCause(cause);
                interventions.remove(cause);
            }
        }
    }



}
