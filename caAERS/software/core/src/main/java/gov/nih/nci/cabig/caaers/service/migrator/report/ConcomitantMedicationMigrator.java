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
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User:medaV
 * Date: 1/24/13
 */
public class ConcomitantMedicationMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
		List<ConcomitantMedication> srcConcomitantMedications = aeReportSrc.getConcomitantMedications();

	    	if ( srcConcomitantMedications == null || srcConcomitantMedications.size() == 0) {
	    		outcome.addWarning("ER-CM-1", "Input doesn't contain any ConcomitantMedication Values.");
	    		return;
	    	}

	    	// Copy the ConcomitantMedications Information from Source to Destination.
	        // if it is not already present in study participant assignment, add it to SPA
	    	for ( ConcomitantMedication concomitantMedication : srcConcomitantMedications) {
	    		validateConcomitantMedicationDates(concomitantMedication, outcome);
	    		ConcomitantMedication destConcomitantMedication = new ConcomitantMedication();
	    		copyProperties(concomitantMedication, destConcomitantMedication);
	    	    aeReportDest.addConcomitantMedication(destConcomitantMedication);
	    	}
	}


    /**
     * Will copy the startdate, endDate and agent details from src to dest
     * @param src  - ConcomitantMedication
     * @param dest - ConcomitantMedication
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
	 * @param outcome
	 */
	private void validateConcomitantMedicationDates(ConcomitantMedication concomitantMedication,  DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){
		if(concomitantMedication.getStartDate() != null && concomitantMedication.getEndDate() != null){
            if(DateUtils.compareDate(concomitantMedication.getStartDate().toDate() , concomitantMedication.getEndDate().toDate()) > 0){
				 outcome.addError("PAT_CCM1_ERR", "Concomitant Medication 'End date' cannot be before 'Start date' ");
			}
		}
	}
}
