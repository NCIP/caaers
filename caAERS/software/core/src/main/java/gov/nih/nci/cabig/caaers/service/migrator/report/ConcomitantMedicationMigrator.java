package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User:medaV
 * Date: 1/24/13
 */
public class ConcomitantMedicationMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
		List<ConcomitantMedication> srcConcomitantMedications = aeReportSrc.getConcomitantMedications();
		List<ConcomitantMedication> destConcomitantMedications = aeReportDest.getConcomitantMedications();
	    	
	    	if ( srcConcomitantMedications == null || srcConcomitantMedications.size() == 0) {
	    		outcome.addWarning("WR-OAI-1", "Input doesn't contain any ConcomitantMedication Values.");
	    		return;
	    	}
			
	    	if ( destConcomitantMedications == null ) {
	    		destConcomitantMedications = new ArrayList<ConcomitantMedication>();
	    	}
	    	
	        StudyParticipantAssignment  assignment = null;
	        	
	        if ( aeReportDest != null && aeReportDest.getReportingPeriod() != null ) {
	        	assignment = aeReportDest.getReportingPeriod().getAssignment();
	        }
	        
	        if ( assignment == null ) {
	        	 outcome.addWarning("ER-PM-1", "Assignment is missing in the Reporting Period.");
	             return;
	        }
	    	
	        for ( StudyParticipantConcomitantMedication spc :  assignment.getConcomitantMedications() ) {
	    		ConcomitantMedication destConcomitantMedication = new ConcomitantMedication();
	    		copyPropertiesFromSource(spc, destConcomitantMedication);
	    		destConcomitantMedications.add(destConcomitantMedication);
	    		destConcomitantMedication.setReport(aeReportDest);
	        }
	        
	    	// Copy the ConcomitantMedications Information from Source to Destination.
	    	for ( ConcomitantMedication concomitantMedication : srcConcomitantMedications) {
	    		validateConcomitantMedicationDates(concomitantMedication, outcome);
	    		ConcomitantMedication destConcomitantMedication = new ConcomitantMedication();
	    		copyProperties(concomitantMedication, destConcomitantMedication);
	    		destConcomitantMedications.add(destConcomitantMedication);
	    		destConcomitantMedication.setReport(aeReportDest);
	    	}
	}    	 
	
	/**
	 * Copy the Details from the UserInput.
	 * @param src
	 * @param dest
	 */
	private void copyPropertiesFromSource(StudyParticipantConcomitantMedication src, ConcomitantMedication dest) {
		dest.setAgentName(src.getAgentName());
		dest.setEndDate(src.getEndDate()); 
		dest.setStartDate(src.getStartDate());
		dest.setStillTakingMedications(src.getStillTakingMedications());
	}
	
	
	/**
	 * Copy the Details from the UserInput.
	 * @param bioIntervention
	 * @param destConcomitantMedication
	 */
	private void copyProperties(ConcomitantMedication src, ConcomitantMedication dest) {
		dest.setAgentName(src.getAgentName());
		dest.setEndDate(src.getEndDate()); 
		dest.setStartDate(src.getStartDate());
		dest.setStillTakingMedications(src.getStillTakingMedications());
	}
	
	/**
	 * Validate Concomitant Medication Dates.
	 * @param concomitantMedication
	 * @param domainObjectImportOutcome
	 */
	private void validateConcomitantMedicationDates(ConcomitantMedication concomitantMedication,  DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){
		if(concomitantMedication.getStartDate() != null && concomitantMedication.getEndDate() != null){
			if(concomitantMedication.getStartDate().toDate().after(concomitantMedication.getEndDate().toDate())){
				 outcome.addError("PAT_CCM1_ERR", "Concomitant Medication 'End date' cannot be before 'Start date' ");
			}
		}
	}
}