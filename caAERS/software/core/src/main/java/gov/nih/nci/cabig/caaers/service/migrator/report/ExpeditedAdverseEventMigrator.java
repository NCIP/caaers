package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.dao.InterventionSiteDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.attribution.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;
import org.apache.cxf.common.util.StringUtils;

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
             migrateCourseAgentAttributions(src,dest,aeSrc, aeDest, outcome);
             migrateDeviceAttributions(src,dest,aeSrc, aeDest, outcome);

//            migrateAttributions(src, dest, aeSrc, aeDest, outcome);
            destAdverseEvents.add(aeDest);
        }
        dest.setAdverseEventsInternal(destAdverseEvents);



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


    private void migrateAttributions(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, AdverseEvent aeSrc, AdverseEvent aeDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){
    
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
         final CourseAgent existingCourseAgent = dest.findReportCourseAgentByNscNumber(srcAdverseEventAttribution.getCause().getStudyAgent().getAgent().getNscNumber());
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
     for(CourseAgentAttribution courseAgentAttribution : existingAdverseEventAttributions){
         aeDest.getCourseAgentAttributions().remove(courseAgentAttribution);
     }

     
     // migrate concomitant medications attributions
     
     if(!aeSrc.getConcomitantMedicationAttributions().isEmpty()){
         for(ConcomitantMedicationAttribution srcConMedAttribution : aeSrc.getConcomitantMedicationAttributions() ){
             ConcomitantMedicationAttribution attribution = new ConcomitantMedicationAttribution();
             attribution.setAttribution(srcConMedAttribution.getAttribution());
             ConcomitantMedication conMed = dest.findReportConcomitantMedication(srcConMedAttribution.getCause());
             
             if(conMed == null){
                 outcome.addError("ER-CM-1", "Error migrating Concomitant Medication Attribution. Could not find matching Concomitant Medication in report" );
                 break;
             }
             
             attribution.setCause(conMed);
             attribution.setAdverseEvent(aeDest);
             aeDest.getConcomitantMedicationAttributions().add(attribution);
         }
     }
     
     // migrate other cause attributions
     
         for(OtherCauseAttribution srcOtherCauseAttribution : aeSrc.getOtherCauseAttributions() ){
             OtherCauseAttribution attribution = new OtherCauseAttribution();
             attribution.setAttribution(srcOtherCauseAttribution.getAttribution());
             
             OtherCause otherCause = dest.findOtherCauseByCause(srcOtherCauseAttribution.getCause().getText());
             if(otherCause == null){
                 outcome.addError("ER-OC-1", "Error migrating Other Cause Attribution. Could not find matching Other Cause in report" );
                 break;
             }
             
             attribution.setCause(otherCause);
             attribution.setAdverseEvent(aeDest);
             aeDest.getOtherCauseAttributions().add(attribution);
         }

     // migrate surgery attributions
     
     for(SurgeryAttribution srcSurgeryAttribution : aeSrc.getSurgeryAttributions() ){
         SurgeryAttribution attribution = new SurgeryAttribution();
         attribution.setAttribution(srcSurgeryAttribution.getAttribution());
         
         
         // Study supports surgery intervention otherwise throw error. This check is already made in surgery intervention migrator

         SurgeryIntervention intervention = dest.findReportSurgeryInterventionBySiteAndDate(srcSurgeryAttribution.getCause());
         
         if ( intervention == null ) {
             outcome.addError("ER-SI-2", "Could not find surgery intervention with site : " + attribution.getCause().getInterventionSite().getName() + 
            		 " and date : " + attribution.getCause().getInterventionDate().toString() + " in the report");
             break;
         }
        
         attribution.setCause(intervention);
         attribution.setAdverseEvent(aeDest);
         aeDest.getSurgeryAttributions().add(attribution);
     }



     // migrate radiation attributions
     
     for(RadiationAttribution srcRadiationAttribution : aeSrc.getRadiationAttributions() ){
         RadiationAttribution attribution = new RadiationAttribution();
         attribution.setAttribution(srcRadiationAttribution.getAttribution());
         
         RadiationIntervention intervention = dest.findReportRadiationInterventionByAdministrationAndLastTreatmentDate(srcRadiationAttribution.getCause());
         
         if(intervention == null){
             outcome.addError("ER-RI-1", "Error migrating Radiation Intervention Attribution. Could not find matching Radiation Intervention in report" );
             break;
         }
         attribution.setCause(intervention);
         attribution.setAdverseEvent(aeDest);
         aeDest.getRadiationAttributions().add(attribution);
     }
     
     // migrate device attributions
     
     for(DeviceAttribution srcdeviceAttribution : aeSrc.getDeviceAttributions() ){
         DeviceAttribution attribution = new DeviceAttribution();
         attribution.setAttribution(srcdeviceAttribution.getAttribution());
        
         
         MedicalDevice medicalDevice = dest.findReportMedicalDevice(srcdeviceAttribution.getCause());
         
         if(medicalDevice == null){
             outcome.addError("ER-MD-1", "Error migrating Medical Device Attribution. Could not find matching Medical Device in report" );
             break;
         }
 		
 		 attribution.setCause(medicalDevice);
         
         attribution.setAdverseEvent(aeDest);
         aeDest.getDeviceAttributions().add(attribution);
     }
    
     
     // migrate disease attributions
     
     for(DiseaseAttribution srcDiseaseAttribution : aeSrc.getDiseaseAttributions() ){
         DiseaseAttribution attribution = new DiseaseAttribution();
         attribution.setAttribution(srcDiseaseAttribution.getAttribution());
     	
     	DiseaseHistory diseaseHistory = dest.getDiseaseHistory();
     	
 		attribution.setCause(diseaseHistory);
     	
         attribution.setAdverseEvent(aeDest);
         aeDest.getDiseaseAttributions().add(attribution);
     }
     
     
   //  dest.addAdverseEvent(aeDest);

 }

    public InterventionSiteDao getInterventionSiteDao() {
        return interventionSiteDao;
    }

    public void setInterventionSiteDao(InterventionSiteDao interventionSiteDao) {
        this.interventionSiteDao = interventionSiteDao;
    }

    private InterventionSiteDao interventionSiteDao;
    
}
