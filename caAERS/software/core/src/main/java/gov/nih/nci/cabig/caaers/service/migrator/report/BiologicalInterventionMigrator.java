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
public class BiologicalInterventionMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<BiologicalIntervention> srcBiologicalInterventions = aeReportSrc.getBiologicalInterventions();
		List<BiologicalIntervention> destBiologicalInterventions = aeReportDest.getBiologicalInterventions();
    	
    	if ( srcBiologicalInterventions == null || srcBiologicalInterventions.size() == 0) {
    		outcome.addWarning("WR-BIM-1", "Input doesn't contain any BiologicalIntervention Values.");
    		return;
    	}
		
    	if ( destBiologicalInterventions == null ) {
    		destBiologicalInterventions = new ArrayList<BiologicalIntervention>();
    	}
    	// Copy the BiologicalInterventions Information from Source to Destination.
    	for ( BiologicalIntervention bioIntervention : srcBiologicalInterventions) {
    		BiologicalIntervention destBiologicalIntervention = new BiologicalIntervention();
    		copyProperties(bioIntervention, destBiologicalIntervention);
    		destBiologicalInterventions.add(destBiologicalIntervention);
    		destBiologicalIntervention.setReport(aeReportDest);
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