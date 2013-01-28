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
public class GeneticInterventionMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<GeneticIntervention> srcGeneticInterventions = aeReportSrc.getGeneticInterventions();
		List<GeneticIntervention> destGeneticInterventions = aeReportDest.getGeneticInterventions();
    	
    	if ( srcGeneticInterventions == null || srcGeneticInterventions.size() == 0) {
    		outcome.addWarning("WR-GIM-1", "Input doesn't contain any GeneticIntervention Values.");
    		return;
    	}
		
    	if ( destGeneticInterventions == null ) {
    		destGeneticInterventions = new ArrayList<GeneticIntervention>();
    	}
    	// Copy the GeneticInterventions Information from Source to Destination.
    	for ( GeneticIntervention bioIntervention : srcGeneticInterventions) {
    		GeneticIntervention destGeneticIntervention = new GeneticIntervention();
    		copyProperties(bioIntervention, destGeneticIntervention);
    		destGeneticInterventions.add(destGeneticIntervention);
    		destGeneticIntervention.setReport(aeReportDest);
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