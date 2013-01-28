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
public class BehavioralInterventionMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<BehavioralIntervention> srcBehavioralInterventions = aeReportSrc.getBehavioralInterventions();
		List<BehavioralIntervention> destBehavioralInterventions = aeReportDest.getBehavioralInterventions();
    	
    	if ( srcBehavioralInterventions == null || srcBehavioralInterventions.size() == 0) {
    		outcome.addWarning("WR-BEM-1", "Input doesn't contain any BehavioralIntervention Values.");
    		return;
    	}
		
    	if ( destBehavioralInterventions == null ) {
    		destBehavioralInterventions = new ArrayList<BehavioralIntervention>();
    	}
    	// Copy the BehavioralInterventions Information from Source to Destination.
    	for ( BehavioralIntervention behavioralIntervention : srcBehavioralInterventions) {
    		BehavioralIntervention destBehavioralIntervention = new BehavioralIntervention();
    		copyProperties(behavioralIntervention, destBehavioralIntervention);
    		destBehavioralInterventions.add(destBehavioralIntervention);
    		destBehavioralIntervention.setReport(aeReportDest);
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