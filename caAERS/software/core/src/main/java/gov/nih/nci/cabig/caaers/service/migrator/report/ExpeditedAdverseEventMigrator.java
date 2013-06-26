package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.dao.InterventionSiteDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.attribution.ConcomitantMedicationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.CourseAgentAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DeviceAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.DiseaseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.OtherCauseAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.RadiationAttribution;
import gov.nih.nci.cabig.caaers.domain.attribution.SurgeryAttribution;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ExpeditedAdverseEventMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
    public void migrate(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

        AdverseEventReportingPeriod reportingPeriod = dest.getReportingPeriod();

        // Associate the updated information to adverse events.
        for ( AdverseEvent aeSrc : src.getAdverseEvents() ) {
            AdverseEvent aeDest = reportingPeriod.findAdverseEventByIdTermAndDates(aeSrc);   //TODO: BJ - May be we must pick unreported AEs here (see the big problem comment below)
            if(aeDest == null ){
                outcome.addError("WS_AEMS_079", "Could not find the AE linked to Safety report", aeSrc.getAdverseEventTerm()!=null? aeSrc.getAdverseEventTerm().getFullName() : "",
                        String.valueOf(aeSrc.getStartDate()), String.valueOf(aeSrc.getEndDate()), String.valueOf(aeSrc.getExternalId()));
                return;
            }


            aeDest.setStartDate(aeSrc.getStartDate());
            aeDest.setEndDate(aeSrc.getEndDate());

            //TODO: BJ - we need another transient holder of AE
            
            migrateAttributions(src, dest, aeSrc, aeDest, outcome);
            dest .addAdverseEvent(aeDest);
            
        }
    }


    /**
     * Load intervention Sites.
     */
    private  List<InterventionSite> loadInterventionSites(List<String> interventionSites) {

        String[] sitesArray = interventionSites.toArray(new String[interventionSites.size()]);
        List<InterventionSite> resultSites = interventionSiteDao.getBySubname(sitesArray);

        return resultSites;

    }

    /**
     * Populate the Intervention Sites.
     * @param srcSurgeryInterventions
     */
    private List<InterventionSite> populateInterventionSites(List<SurgeryIntervention> surgeryInterventions) {

        /**
         * Populate the Intervention Sites.
         */
        List<InterventionSite> iSites = new ArrayList<InterventionSite>() ;
        for ( SurgeryIntervention surIntervention : surgeryInterventions) {
            iSites.add(surIntervention.getInterventionSite());
        }

        return iSites;

    }


    /**
     * find Intervention Site from the List.
     */
    private InterventionSite findInterventionSite(List<InterventionSite> interventionSitesList, String siteName) {
        InterventionSite result  = null;
        for (InterventionSite site :  interventionSitesList) {
            if ( site.getName().equals(siteName)) {
                result = site;
                break;
            }
        }
        return result;
    }


    private void migrateAttributions(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, AdverseEvent aeSrc, AdverseEvent aeDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){
    	 Study study = dest.getStudy();
    	
    	// migrate adverse event attributions
    
    //  migrate course attributions
     if(!aeSrc.getCourseAgentAttributions().isEmpty()){
     	for(CourseAgentAttribution srcCAAttribution : aeSrc.getCourseAgentAttributions()){
     		 CourseAgentAttribution attribution = new CourseAgentAttribution();
              attribution.setAttribution(srcCAAttribution.getAttribution());
              CourseAgent ca = dest.findReportCourseAgentByNscNumber(srcCAAttribution.getCause().getStudyAgent().getAgent().getNscNumber());
              if(ca == null){
                  outcome.addError("ER-CA-1", "Error migrating Course Agent Attribution. Could not find matching Agent in report" );
                  break;
              }
              
              attribution.setCause(ca);
              attribution.setAdverseEvent(aeDest);
     		 aeDest.getCourseAgentAttributions().add(attribution);
     	}
     	
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

        // Populate the intervention Sites.
        List<InterventionSite> resultSites = populateInterventionSites(dest.getSurgeryInterventions());
     
     // migrate surgery attributions
     
     for(SurgeryAttribution srcSurgeryAttribution : aeSrc.getSurgeryAttributions() ){
         SurgeryAttribution attribution = new SurgeryAttribution();
         attribution.setAttribution(srcSurgeryAttribution.getAttribution());
         
        // SurgeryIntervention intervention = dest.findReportSurgeryInterventionBySiteAndDate(attribution.getCause());
         // Study supports surgery intervention otherwise throw error. Site Intervention site should be picked from databse otherwise throw error.

         List<OtherIntervention> otherSurgeryList = study.getActiveStudySurgeries();

         OtherIntervention oi = null;

         if ( otherSurgeryList.size() > 0) {
             oi =  otherSurgeryList.get(0);
         }

         if ( oi == null ) {
             outcome.addError("ER-SI-2", "Study doesn't contain any Active Surgery Radiation." );
             break;
         }

         SurgeryIntervention intervention = new SurgeryIntervention();
         intervention.setStudySurgery(oi);

         InterventionSite site = findInterventionSite(resultSites, srcSurgeryAttribution.getCause().getInterventionSite().getName());
         if ( site == null ) {
             outcome.addError("ER-SI-1", "Intervention Site is missing from Intervention sites LOV." );
             break;
         }

         intervention.setInterventionSite(site);
         
 		if ( srcSurgeryAttribution.getCause().getInterventionDate() != null ){
 			intervention.setInterventionDate(srcSurgeryAttribution.getCause().getInterventionDate());
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
