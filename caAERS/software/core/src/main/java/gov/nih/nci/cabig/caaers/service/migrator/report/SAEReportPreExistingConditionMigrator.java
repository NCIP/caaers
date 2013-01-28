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
public class SAEReportPreExistingConditionMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<SAEReportPreExistingCondition> srcPreExistingConditions = aeReportSrc.getSaeReportPreExistingConditions();
		List<SAEReportPreExistingCondition> destPreExistingConditions = aeReportDest.getSaeReportPreExistingConditions();
    	
    	if ( srcPreExistingConditions == null || srcPreExistingConditions.size() == 0) {
    		outcome.addWarning("WR-SPM-1", "Input doesn't contain any SAE report pre-existing Values.");
    		return;
    	}
    	
    	if ( destPreExistingConditions == null ) {
    		destPreExistingConditions = new ArrayList<SAEReportPreExistingCondition>();
    	}
    	// Copy the SAEReportPriorTherapys Information from Source to Destination.
    	for ( SAEReportPreExistingCondition spc : srcPreExistingConditions) {
    		SAEReportPreExistingCondition destPreExistingCondition = new SAEReportPreExistingCondition();
    		copyProperties(spc, destPreExistingCondition);
    		destPreExistingCondition.setReport(aeReportDest);
    		destPreExistingConditions.add(destPreExistingCondition);
    	}
	}    	 
	
	/**
	 * Copy the Details from the UserInput.
	 * @param src
	 * @param dest
	 */
	private void copyProperties(SAEReportPreExistingCondition src, SAEReportPreExistingCondition dest) {
		dest.setLinkedToOtherCause(src.getLinkedToOtherCause());
		dest.setOther(src.getOther());
		dest.setPreExistingCondition(src.getPreExistingCondition());
		dest.setVersion(src.getVersion());
	}
}