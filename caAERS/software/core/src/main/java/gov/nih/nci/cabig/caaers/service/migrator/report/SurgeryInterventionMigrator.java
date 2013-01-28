package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User:medaV
 * Date: 1/21/13
 */
public class SurgeryInterventionMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
		List<SurgeryIntervention> srcSurgeryInterventions = aeReportSrc.getSurgeryInterventions();
		List<SurgeryIntervention> destSurgeryInterventions = aeReportDest.getSurgeryInterventions();
    	
    	if ( srcSurgeryInterventions == null || srcSurgeryInterventions.size() == 0) {
    		outcome.addWarning("WR-SIM-1", "Input doesn't contain any SurgeryIntervention Values.");
    		return;
    	}
		
    	if ( destSurgeryInterventions == null ) {
    		destSurgeryInterventions = new ArrayList<SurgeryIntervention>();
    	}
    	// Copy the SurgeryInterventions Information from Source to Destination.
    	for ( SurgeryIntervention surIntervention : srcSurgeryInterventions) {
    		SurgeryIntervention destSurgeryIntervention = new SurgeryIntervention();
    		copyProperties(surIntervention, destSurgeryIntervention);
    		destSurgeryInterventions.add(destSurgeryIntervention);
    	}
	}    	 
	
	/**
	 * Copy the Details from the UserInput.
	 * @param src
	 * @param dest
	 */
	private void copyProperties(SurgeryIntervention src, SurgeryIntervention dest) {
		dest.setDescription(src.getDescription());
		dest.setInterventionDate(src.getInterventionDate());
		dest.setInterventionSite(src.getInterventionSite());
		dest.setStudySurgery(src.getStudySurgery());
		dest.setTreatmentArm(src.getTreatmentArm());
	}
}