package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.dao.InterventionSiteDao;
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
import java.util.List;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ExpeditedAdverseEventMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
    public void migrate(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

        AdverseEventReportingPeriod reportingPeriod = dest.getReportingPeriod();
        List<AdverseEvent> destAdverseEvents = new ArrayList<AdverseEvent>();
         // Associate the updated information to adverse events.
        for ( AdverseEvent aeSrc : src.getAdverseEvents() ) {
            AdverseEvent aeDest = reportingPeriod.findAdverseEventByIdTermAndDates(aeSrc);
            if(aeDest == null ){
                outcome.addError("WS_AEMS_079", "Could not find the AE linked to Safety report", aeSrc.getAdverseEventTerm()!=null? aeSrc.getAdverseEventTerm().getFullName() : "",
                        String.valueOf(aeSrc.getStartDate()), String.valueOf(aeSrc.getEndDate()), String.valueOf(aeSrc.getExternalId()));
                return;
            }


            aeDest.setStartDate(aeSrc.getStartDate());
            aeDest.setEndDate(aeSrc.getEndDate());
            aeDest.setReporterEmail(aeSrc.getReporterEmail());
            migrateCourseAgentAttributions(src,dest,aeSrc, aeDest, outcome);
            migrateDeviceAttributions(src,dest,aeSrc, aeDest, outcome);
            migrateSurgeryAttributions(src,dest,aeSrc, aeDest, outcome);
            migrateRadiationAttributions(src,dest,aeSrc, aeDest, outcome);
            migrateConcomitantMedicationAttributions(src,dest,aeSrc, aeDest, outcome);
            migrateDiseaseAttributions(src,dest,aeSrc, aeDest, outcome);
            migrateOtherCauseAttributions(src,dest,aeSrc, aeDest, outcome);
            migrateOtherAdverseEventInterventionAttributions(src.getBehavioralInterventions(), dest.getBehavioralInterventions(), aeSrc.getBehavioralInterventionAttributions(), aeDest.getBehavioralInterventionAttributions(), aeDest, outcome);
            migrateOtherAdverseEventInterventionAttributions(src.getBiologicalInterventions(), dest.getBiologicalInterventions(), aeSrc.getBiologicalInterventionAttributions(), aeDest.getBiologicalInterventionAttributions(), aeDest, outcome);
            migrateOtherAdverseEventInterventionAttributions(src.getDietaryInterventions(), dest.getDietaryInterventions(), aeSrc.getDietarySupplementInterventionAttributions(), aeDest.getDietarySupplementInterventionAttributions(), aeDest, outcome);
            migrateOtherAdverseEventInterventionAttributions(src.getGeneticInterventions(), dest.getGeneticInterventions(), aeSrc.getGeneticInterventionAttributions(), aeDest.getGeneticInterventionAttributions(), aeDest, outcome);
            migrateOtherAdverseEventInterventionAttributions(src.getOtherAEInterventions(), dest.getOtherAEInterventions(), aeSrc.getOtherInterventionAttributions(), aeDest.getOtherInterventionAttributions(), aeDest, outcome);

//            migrateAttributions(src, dest, aeSrc, aeDest, outcome);
            destAdverseEvents.add(aeDest);
        }
        dest.setAdverseEventsInternal(destAdverseEvents);



    }


    private void migrateRadiationAttributions(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, AdverseEvent aeSrc, AdverseEvent aeDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){

        //  migrate radiation attributions
        if(aeSrc.getRadiationAttributions().isEmpty()){
            aeDest.getRadiationAttributions().clear();
            return;
        }

        //take a local copy
        List<RadiationIntervention> existingRadiationInterventions = new ArrayList<RadiationIntervention>(dest.getRadiationInterventions());
        List<RadiationAttribution> existingAdverseEventAttributions = new ArrayList<RadiationAttribution>(aeDest.getRadiationAttributions());


        for(RadiationAttribution srcAdverseEventAttribution : aeSrc.getRadiationAttributions()){
            //check if the attribution is present in destination
            final RadiationIntervention srcRadiationIntervention = srcAdverseEventAttribution.getCause();
            if(srcRadiationIntervention == null){
                outcome.addError("ER-CA-1", "Error migrating Radiation Attribution. Could not find matching Radiation information" );
                return;
            }

            //check if the Cause is present among the list of radiations in the report
            final RadiationIntervention existingRadiationIntervention = CollectionUtils.find(existingRadiationInterventions, new Predicate<RadiationIntervention>() {
                public boolean evaluate(RadiationIntervention radiationIntervention) {
                    return DateUtils.compareDate(radiationIntervention.getLastTreatmentDate(), srcRadiationIntervention.getLastTreatmentDate()) == 0 &&
                           srcRadiationIntervention.getAdministration() == radiationIntervention.getAdministration();
                }
            });
            if(existingRadiationIntervention == null){
                outcome.addError("ER-CA-1", "Error migrating RadiationAttribution. Could not find matching RadiationIntervention in report" );
                break;
            }
            //remove the intervention from the list of interventions obtained from report.
            existingRadiationInterventions.remove(existingRadiationIntervention);

            //find if any attribution for this agent exist.
            AdverseEventAttribution existingAttribution = CollectionUtils.find(existingAdverseEventAttributions, new Predicate<RadiationAttribution>() {
                public boolean evaluate(RadiationAttribution adverseEventAttribution) {
                    return adverseEventAttribution.getCause() != null &&
                           DateUtils.compareDate(adverseEventAttribution.getCause().getLastTreatmentDate(), existingRadiationIntervention.getLastTreatmentDate()) == 0 &&
                           srcRadiationIntervention.getAdministration() == existingRadiationIntervention.getAdministration();
                }
            });

            if(existingAttribution != null){
                existingAttribution.setAttribution(srcAdverseEventAttribution.getAttribution());
                existingAdverseEventAttributions.remove(existingAttribution);
            } else {
                aeDest.addAttribution(existingRadiationIntervention, srcAdverseEventAttribution.getAttribution());
            }
        }

        //remove unwanted attributions
        for(RadiationAttribution unwantedAdverseEventAttribution : existingAdverseEventAttributions){
            aeDest.getRadiationAttributions().remove(unwantedAdverseEventAttribution);
        }
    }

    private void migrateSurgeryAttributions(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, AdverseEvent aeSrc, AdverseEvent aeDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){

        //  migrate Surgery attributions
        if(aeSrc.getSurgeryAttributions().isEmpty()){
            aeDest.getSurgeryAttributions().clear();
            return;
        }

        //take a local copy
        List<SurgeryIntervention> existingSurgeryInterventions = new ArrayList<SurgeryIntervention>(dest.getSurgeryInterventions());
        List<SurgeryAttribution> existingAdverseEventAttributions = new ArrayList<SurgeryAttribution>(aeDest.getSurgeryAttributions());


        for(SurgeryAttribution srcAdverseEventAttribution : aeSrc.getSurgeryAttributions()){
            //check if the attribution is present in destination
            final SurgeryIntervention srcSurgeryIntervention = srcAdverseEventAttribution.getCause();
            if(srcSurgeryIntervention == null){
                outcome.addError("ER-CA-1", "Error migrating Surgery Attribution. Could not find matching Surgery information" );
                return;
            }

            //check if the Cause is present among the list of Surgery in the report
            final SurgeryIntervention existingSurgeryIntervention = CollectionUtils.find(existingSurgeryInterventions, new Predicate<SurgeryIntervention>() {
                public boolean evaluate(SurgeryIntervention surgeryIntervention) {
                    return DateUtils.compareDate(surgeryIntervention.getInterventionDate(), srcSurgeryIntervention.getInterventionDate()) == 0 &&
                           ObjectUtils.equals(srcSurgeryIntervention.getInterventionSite(), surgeryIntervention.getInterventionSite());
                }
            });
            if(existingSurgeryIntervention == null){
                outcome.addError("ER-CA-1", "Error migrating SurgeryAttribution. Could not find matching surgeryIntervention in report" );
                break;
            }
            //remove the intervention from the list of interventions obtained from report.
            existingSurgeryInterventions.remove(existingSurgeryIntervention);

            //find if any attribution for this agent exist.
            AdverseEventAttribution existingAttribution = CollectionUtils.find(existingAdverseEventAttributions, new Predicate<SurgeryAttribution>() {
                public boolean evaluate(SurgeryAttribution adverseEventAttribution) {
                    return adverseEventAttribution.getCause() != null &&
                            DateUtils.compareDate(adverseEventAttribution.getCause().getInterventionDate(), existingSurgeryIntervention.getInterventionDate()) == 0 &&
                            ObjectUtils.equals(adverseEventAttribution.getCause().getInterventionSite(), existingSurgeryIntervention.getInterventionSite());
                }
            });

            if(existingAttribution != null){
                existingAttribution.setAttribution(srcAdverseEventAttribution.getAttribution());
                existingAdverseEventAttributions.remove(existingAttribution);
            } else {
                aeDest.addAttribution(existingSurgeryIntervention, srcAdverseEventAttribution.getAttribution());
            }
        }

        //remove unwanted attributions
        for(SurgeryAttribution unwantedAdverseEventAttribution : existingAdverseEventAttributions){
            aeDest.getSurgeryAttributions().remove(unwantedAdverseEventAttribution);
        }
    }

    private void migrateCourseAgentAttributions(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, AdverseEvent aeSrc, AdverseEvent aeDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){

        //  migrate course attributions
        if(aeSrc.getCourseAgentAttributions().isEmpty()){
            aeDest.getCourseAgentAttributions().clear();
            return;
        }

        //take a local copy
        List<CourseAgentAttribution> existingAdverseEventAttributions = new ArrayList<CourseAgentAttribution>(aeDest.getCourseAgentAttributions());


        for(CourseAgentAttribution srcAdverseEventAttribution : aeSrc.getCourseAgentAttributions()){
            //check if the attribution is present in destination
            StudyAgent srcStudyAgent = srcAdverseEventAttribution.getCause().getStudyAgent();
            if(srcStudyAgent == null){
                outcome.addError("ER-CA-1", "Error migrating Course Agent Attribution. Could not find matching Agent information" );
                return;
            }
            String agentToSearch = srcStudyAgent.getAgent() != null ? srcStudyAgent.getAgent().getNscNumber() : srcStudyAgent.getOtherAgent();
            if(StringUtils.isEmpty(agentToSearch)){
                outcome.addError("ER-CA-1", "Error migrating Course Agent Attribution. Could not find matching Agent information" );
                return;
            }
            //check if the Cause is present in the destination report?
            final CourseAgent existingCourseAgent = dest.findReportCourseAgentByNscNumber(agentToSearch);
            if(existingCourseAgent == null){
                outcome.addError("ER-CA-1", "Error migrating Course Agent Attribution. Could not find matching Agent in report" );
                break;
            }
            //find if any attribution for this agent exist.
            AdverseEventAttribution existingAttribution = CollectionUtils.find(existingAdverseEventAttributions, new Predicate<CourseAgentAttribution>() {
                public boolean evaluate(CourseAgentAttribution adverseEventAttribution) {
                    return adverseEventAttribution.getCause() != null && adverseEventAttribution.getCause().getStudyAgent().getId().equals(existingCourseAgent.getStudyAgent().getId());
                }
            });

            if(existingAttribution != null){
                existingAttribution.setAttribution(srcAdverseEventAttribution.getAttribution());
                existingAdverseEventAttributions.remove(existingAttribution);
            } else {
                aeDest.addAttribution(existingCourseAgent, srcAdverseEventAttribution.getAttribution());
            }
        }

        //remove unwanted attributions
        for(CourseAgentAttribution unwantedAdverseEventAttribution : existingAdverseEventAttributions){
            aeDest.getCourseAgentAttributions().remove(unwantedAdverseEventAttribution);
        }
    }

    private void migrateDeviceAttributions(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, AdverseEvent aeSrc, AdverseEvent aeDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){

        //  migrate device attributions
        if(aeSrc.getDeviceAttributions().isEmpty()){
            aeDest.getDeviceAttributions().clear();
            return;
        }

        //take a local copy
        List<DeviceAttribution> existingAdverseEventAttributions = new ArrayList<DeviceAttribution>(aeDest.getDeviceAttributions());


        for(DeviceAttribution srcAdverseEventAttribution : aeSrc.getDeviceAttributions()){
            //check if the attribution is present in destination
            MedicalDevice srcMedicalDevice = srcAdverseEventAttribution.getCause();
            if(srcMedicalDevice == null){
                outcome.addError("ER-CA-1", "Error migrating Device Attribution. Could not find matching Device information" );
                return;
            }

            //check if the Cause is present in the destination report?
            final MedicalDevice existingMedicalDevice = dest.findReportMedicalDevice(srcMedicalDevice);
            if(existingMedicalDevice == null){
                outcome.addError("ER-CA-1", "Error migrating MedicalDevice Attribution. Could not find matching Medical Device in report" );
                break;
            }
            //find if any attribution for this agent exist.
            AdverseEventAttribution existingAttribution = CollectionUtils.find(existingAdverseEventAttributions, new Predicate<DeviceAttribution>() {
                public boolean evaluate(DeviceAttribution adverseEventAttribution) {
                    return adverseEventAttribution.getCause() != null && adverseEventAttribution.getCause().getStudyDevice().getId().equals(existingMedicalDevice.getStudyDevice().getId());
                }
            });

            if(existingAttribution != null){
                existingAttribution.setAttribution(srcAdverseEventAttribution.getAttribution());
                existingAdverseEventAttributions.remove(existingAttribution);
            } else {
                aeDest.addAttribution(existingMedicalDevice, srcAdverseEventAttribution.getAttribution());
            }
        }

        //remove unwanted attributions
        for(DeviceAttribution unwantedAdverseEventAttribution : existingAdverseEventAttributions){
            aeDest.getDeviceAttributions().remove(unwantedAdverseEventAttribution);
        }
    }

    private void migrateConcomitantMedicationAttributions(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, AdverseEvent aeSrc, AdverseEvent aeDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){

        //  migrate radiation attributions
        if(aeSrc.getConcomitantMedicationAttributions().isEmpty()){
            aeDest.getConcomitantMedicationAttributions().clear();
            return;
        }

        //take a local copy
        List<ConcomitantMedication> existingConcomitantMedications = new ArrayList<ConcomitantMedication>(dest.getConcomitantMedications());
        List<ConcomitantMedicationAttribution> existingAdverseEventAttributions = new ArrayList<ConcomitantMedicationAttribution>(aeDest.getConcomitantMedicationAttributions());


        for(ConcomitantMedicationAttribution srcAdverseEventAttribution : aeSrc.getConcomitantMedicationAttributions()){
            //check if the attribution is present in destination
            final ConcomitantMedication srcConcomitantMedication = srcAdverseEventAttribution.getCause();
            if(srcConcomitantMedication == null){
                outcome.addError("ER-CA-1", "Error migrating  ConcomitantMedication Attribution. Could not find matching ConcomitantMedication information" );
                return;
            }

            //check if the Cause is present among the list of radiations in the report
            final ConcomitantMedication existingConcomitantMedication = CollectionUtils.find(existingConcomitantMedications, new Predicate<ConcomitantMedication>() {
                public boolean evaluate(ConcomitantMedication conMed) {
                    return StringUtils.equals(conMed.getAgentName(), srcConcomitantMedication.getAgentName());
                }
            });
            if(existingConcomitantMedication == null){
                outcome.addError("ER-CA-1", "Error migrating ConcomitantMedicationAttribution. Could not find matching ConcomitantMedication in report" );
                break;
            }
            //remove the intervention from the list of interventions obtained from report.
            existingConcomitantMedications.remove(existingConcomitantMedication);

            //find if any attribution for this agent exist.
            AdverseEventAttribution existingAttribution = CollectionUtils.find(existingAdverseEventAttributions, new Predicate<ConcomitantMedicationAttribution>() {
                public boolean evaluate(ConcomitantMedicationAttribution adverseEventAttribution) {
                    return adverseEventAttribution.getCause() != null && StringUtils.equals(adverseEventAttribution.getCause().getAgentName(), existingConcomitantMedication.getAgentName());

                }
            });

            if(existingAttribution != null){
                existingAttribution.setAttribution(srcAdverseEventAttribution.getAttribution());
                existingAdverseEventAttributions.remove(existingAttribution);
            } else {
                aeDest.addAttribution(existingConcomitantMedication, srcAdverseEventAttribution.getAttribution());
            }
        }

        //remove unwanted attributions
        for(ConcomitantMedicationAttribution unwantedAdverseEventAttribution : existingAdverseEventAttributions){
            aeDest.getConcomitantMedicationAttributions().remove(unwantedAdverseEventAttribution);
        }
    }

    private void migrateDiseaseAttributions(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, AdverseEvent aeSrc, AdverseEvent aeDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){

        //  migrate radiation attributions
        if(aeSrc.getDiseaseAttributions().isEmpty()){
            aeDest.getDiseaseAttributions().clear();
            return;
        }

        //take a local copy

        List<DiseaseAttribution> existingAdverseEventAttributions = new ArrayList<DiseaseAttribution>(aeDest.getDiseaseAttributions());


        for(DiseaseAttribution srcAdverseEventAttribution : aeSrc.getDiseaseAttributions()){
            //check if the attribution is present in destination
            final DiseaseHistory srcDiseaseHistory = srcAdverseEventAttribution.getCause();
            if(srcDiseaseHistory == null){
                outcome.addError("ER-CA-1", "Error migrating  DiseaseHistory Attribution. Could not find matching DiseaseHistory information" );
                return;
            }

            //check if the Cause is present among the list of radiations in the report
            final DiseaseHistory existingDiseaseHistory = dest.getDiseaseHistory();

            //find if any attribution for this agent exist.
            AdverseEventAttribution existingAttribution = CollectionUtils.find(existingAdverseEventAttributions, new Predicate<DiseaseAttribution>() {
                public boolean evaluate(DiseaseAttribution adverseEventAttribution) {
                    return adverseEventAttribution.getCause() != null;
                }
            });

            if(existingAttribution != null){
                existingAttribution.setAttribution(srcAdverseEventAttribution.getAttribution());
                existingAdverseEventAttributions.remove(existingAttribution);
            } else {
                aeDest.addAttribution(existingDiseaseHistory, srcAdverseEventAttribution.getAttribution());
            }
        }

        //remove unwanted attributions
        for(DiseaseAttribution unwantedAdverseEventAttribution : existingAdverseEventAttributions){
            aeDest.getDiseaseAttributions().remove(unwantedAdverseEventAttribution);
        }
    }


    private void migrateOtherCauseAttributions(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, AdverseEvent aeSrc, AdverseEvent aeDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){

        //  migrate Surgery attributions
        if(aeSrc.getOtherCauseAttributions().isEmpty()){
            aeDest.getOtherCauseAttributions().clear();
            return;
        }

        //take a local copy
        List<OtherCause> existingOtherCauses = new ArrayList<OtherCause>(dest.getOtherCauses());
        List<OtherCauseAttribution> existingAdverseEventAttributions = new ArrayList<OtherCauseAttribution>(aeDest.getOtherCauseAttributions());


        for(OtherCauseAttribution srcAdverseEventAttribution : aeSrc.getOtherCauseAttributions()){
            //check if the attribution is present in destination
            final OtherCause srcOtherCause = srcAdverseEventAttribution.getCause();
            if(srcOtherCause == null){
                outcome.addError("ER-CA-1", "Error migrating OtherCause Attribution. Could not find matching OtherCause information" );
                return;
            }

            //check if the Cause is present among the list of Surgery in the report
            final OtherCause existingOtherCause = CollectionUtils.find(existingOtherCauses, new Predicate<OtherCause>() {
                public boolean evaluate(OtherCause otherCause) {
                    return StringUtils.equals(srcOtherCause.getText(), otherCause.getText());
                }
            });
            if(existingOtherCause == null){
                outcome.addError("ER-CA-1", "Error migrating OtherCauseAttribution. Could not find matching OtherCause in report" );
                break;
            }
            //remove the intervention from the list of interventions obtained from report.
            existingOtherCauses.remove(existingOtherCause);

            //find if any attribution for this agent exist.
            AdverseEventAttribution existingAttribution = CollectionUtils.find(existingAdverseEventAttributions, new Predicate<OtherCauseAttribution>() {
                public boolean evaluate(OtherCauseAttribution adverseEventAttribution) {
                    return adverseEventAttribution.getCause() != null && StringUtils.equals(adverseEventAttribution.getCause().getText(), existingOtherCause.getText());
                }
            });

            if(existingAttribution != null){
                existingAttribution.setAttribution(srcAdverseEventAttribution.getAttribution());
                existingAdverseEventAttributions.remove(existingAttribution);
            } else {
                aeDest.addAttribution(existingOtherCause, srcAdverseEventAttribution.getAttribution());
            }
        }

        //remove unwanted attributions
        for(OtherCauseAttribution unwantedAdverseEventAttribution : existingAdverseEventAttributions){
            aeDest.getOtherCauseAttributions().remove(unwantedAdverseEventAttribution);
        }
    }


    private void migrateOtherAdverseEventInterventionAttributions( List<? extends AbstractAEIntervention>  srcInterventions,  List<? extends AbstractAEIntervention>  destInterventions, List<? extends AdverseEventAttribution> srcAttributions, List<? extends AdverseEventAttribution> destAttributions, AdverseEvent aeDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){

        //  migrate Surgery attributions
        if(srcAttributions.isEmpty()){
            destAttributions.clear();
            return;
        }

        //take a local copy
        List<AbstractAEIntervention> existingInterventions = new ArrayList<AbstractAEIntervention>(destInterventions);
        List<AdverseEventAttribution> existingAdverseEventAttributions = new ArrayList<AdverseEventAttribution>(destAttributions);


        for(AdverseEventAttribution srcAdverseEventAttribution : srcAttributions){
            //check if the attribution is present in destination
            final AbstractAEIntervention srcOtherCause = (AbstractAEIntervention)srcAdverseEventAttribution.getCause();
            if(srcOtherCause == null){
                outcome.addError("ER-CA-1", "Error migrating AbstractAEIntervention attribution. Could not find matching AbstractAEIntervention information" );
                return;
            }

            //check if the Cause is present among the list of AbstractAEIntervention in the report
            final AbstractAEIntervention existingOtherCause = CollectionUtils.find(existingInterventions, new Predicate<AbstractAEIntervention>() {
                public boolean evaluate(AbstractAEIntervention otherCause) {
                    return StringUtils.equals(srcOtherCause.getDescription(), otherCause.getDescription());
                }
            });
            if(existingOtherCause == null){
                outcome.addError("ER-CA-1", "Error migrating AbstractAEInterventionAttribution. Could not find matching AbstractAEIntervention in report" );
                break;
            }
            //remove the intervention from the list of interventions obtained from report.
            existingInterventions.remove(existingOtherCause);

            //find if any attribution for this agent exist.
            AdverseEventAttribution existingAttribution = CollectionUtils.find(existingAdverseEventAttributions, new Predicate<AdverseEventAttribution>() {
                public boolean evaluate(AdverseEventAttribution adverseEventAttribution) {
                    return adverseEventAttribution.getCause() != null && StringUtils.equals(((AbstractAEIntervention)adverseEventAttribution.getCause()).getDescription(), existingOtherCause.getDescription());
                }
            });

            if(existingAttribution != null){
                existingAttribution.setAttribution(srcAdverseEventAttribution.getAttribution());
                existingAdverseEventAttributions.remove(existingAttribution);
            } else {
                aeDest.addAttribution(existingOtherCause, srcAdverseEventAttribution.getAttribution());
            }
        }

        //remove unwanted attributions
        for(AdverseEventAttribution unwantedAdverseEventAttribution : existingAdverseEventAttributions){
            aeDest.getOtherCauseAttributions().remove(unwantedAdverseEventAttribution);
        }
    }

}
