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
public class OtherAEInterventionMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
		List<OtherAEIntervention> srcOtherAEInterventions = aeReportSrc.getOtherAEInterventions();
		List<OtherAEIntervention> destOtherAEInterventions = aeReportDest.getOtherAEInterventions();
    	
    	if ( srcOtherAEInterventions == null || srcOtherAEInterventions.size() == 0) {
    		outcome.addWarning("WR-OAI-1", "Input doesn't contain any OtherAEIntervention Values.");
    		return;
    	}
		
    	if ( destOtherAEInterventions == null ) {
    		destOtherAEInterventions = new ArrayList<OtherAEIntervention>();
    	}
    	// Copy the OtherAEInterventions Information from Source to Destination.
    	for ( OtherAEIntervention bioIntervention : srcOtherAEInterventions) {
    		OtherAEIntervention destOtherAEIntervention = new OtherAEIntervention();
    		copyProperties(bioIntervention, destOtherAEIntervention);
    		destOtherAEInterventions.add(destOtherAEIntervention);
    		destOtherAEIntervention.setReport(aeReportDest);
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