/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.ConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantConcomitantMedication;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.List;

/**
 * User:medaV
 * Date: 1/24/13
 */
public class ConcomitantMedicationMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
		List<ConcomitantMedication> srcConcomitantMedications = aeReportSrc.getConcomitantMedications();
		List<ConcomitantMedication> destConcomitantMedications = aeReportDest.getConcomitantMedications();
	    	
	    	if ( srcConcomitantMedications == null || srcConcomitantMedications.size() == 0) {
	    		outcome.addWarning("ER-CM-1", "Input doesn't contain any ConcomitantMedication Values.");
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
	        	 outcome.addWarning("ER-CM-1", "Assignment is missing in the Reporting Period.");
	             return;
	        }
	    	
	        	        	        
	    	// Copy the ConcomitantMedications Information from Source to Destination.
	        // if it is already present in study participant assignment, then continue
	        // if it is not already present in study participant assignment, add it to SPA
	    	for ( ConcomitantMedication concomitantMedication : srcConcomitantMedications) {
	    		validateConcomitantMedicationDates(concomitantMedication, outcome);
	    		if (!isPresentInAssignment(assignment.getConcomitantMedications(), concomitantMedication)) {	    			
	    			assignment.getConcomitantMedications().add(
	    					StudyParticipantConcomitantMedication.createAssignmentConcomitantMedication(concomitantMedication));
	    		}
	    		ConcomitantMedication destConcomitantMedication = new ConcomitantMedication();
	    		copyProperties(concomitantMedication, destConcomitantMedication);
	    		destConcomitantMedications.add(destConcomitantMedication);
	    		destConcomitantMedication.setReport(aeReportDest);
	    	}
	}    	 
	
	
	private boolean isPresentInAssignment(
			List<StudyParticipantConcomitantMedication> concomitantMedications,
			ConcomitantMedication concomitantMedication) {
		for (StudyParticipantConcomitantMedication spc : concomitantMedications) {
			//check agentname
			if (spc.getAgentName() == null ) {
				if (concomitantMedication.getAgentName() != null) {			
					continue;
				}
			} else if (!spc.getAgentName().equals(concomitantMedication.getAgentName())) {
				continue;
			}
			
			//check enddate
			if (spc.getEndDate() == null) {
				if (concomitantMedication.getEndDate() != null) {
					continue;
				}				
			} else if (!spc.getEndDate().equals(concomitantMedication.getEndDate())) {
				continue;
			}
			
			//check startdate
			if (spc.getStartDate() == null) {
				if (concomitantMedication.getStartDate() != null) {		
					continue;
				}
			} else if (!spc.getStartDate().equals(concomitantMedication.getStartDate())) {
				continue;
			}
			
			return true;
		}
		return false;
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
