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
public class DietarySupplementInterventionMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<DietarySupplementIntervention> srcDietarySupplementInterventions = aeReportSrc.getDietaryInterventions();
		List<DietarySupplementIntervention> destDietarySupplementInterventions = aeReportDest.getDietaryInterventions();
    	
    	if ( srcDietarySupplementInterventions == null || srcDietarySupplementInterventions.size() == 0 ) {
    		outcome.addWarning("WR-DSM-1", "Input doesn't contain any DietarySupplementIntervention Values.");
    		return;
    	}
		
    	if ( destDietarySupplementInterventions == null ) {
    		destDietarySupplementInterventions = new ArrayList<DietarySupplementIntervention>();
    	}
    	// Copy the DietarySupplementInterventions Information from Source to Destination.
    	for ( DietarySupplementIntervention bioIntervention : srcDietarySupplementInterventions) {
    		DietarySupplementIntervention destDietarySupplementIntervention = new DietarySupplementIntervention();
    		copyProperties(bioIntervention, destDietarySupplementIntervention);
    		destDietarySupplementInterventions.add(destDietarySupplementIntervention);
    		destDietarySupplementIntervention.setReport(aeReportDest);
    	}	
	}    	 
	/**
	 * Copy the Details from the UserInput.
	 * @param src
	 * @param dest
	 */
	private void copyProperties(AbstractAEIntervention src, AbstractAEIntervention dest) {
		dest.setDescription(src.getDescription());
		dest.setStudyIntervention(src.getStudyIntervention());
	}

}