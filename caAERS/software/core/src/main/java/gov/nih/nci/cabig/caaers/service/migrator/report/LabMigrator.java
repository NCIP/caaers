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
public class LabMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<Lab> srcLabs = aeReportSrc.getLabs();
		List<Lab> destLabs = aeReportDest.getLabs();
    	
    	if ( srcLabs == null) {
    		outcome.addWarning("WR_LM_1", "Input doesn't contain any Lab Values.");
    	}
		
    	if ( destLabs == null ) {
    		destLabs = new ArrayList<Lab>();
    	}
    	// Copy the Labs Information from Source to Destination.
    	for ( Lab lab : srcLabs) {
    		Lab destLab = new Lab();
    		copyProperties(lab, destLab);
    		destLabs.add(destLab);
    	}
	}    	 
	
	/**
	 * Copy the Details from the UserInput.
	 * @param src
	 * @param dest
	 */
	private void copyProperties(Lab src, Lab dest) {
		dest.setLabTerm(src.getLabTerm());
		dest.setUnits(src.getUnits());
		dest.setBaseline(src.getBaseline());
		dest.setNadir(src.getNadir());
		dest.setRecovery(src.getRecovery());
		dest.setOther(src.getOther());
		dest.setInfectiousAgent(src.getInfectiousAgent());
		dest.setLabDate(src.getLabDate());
		dest.setSite(src.getSite());
		dest.setNormalRange(src.getNormalRange());
	}
}