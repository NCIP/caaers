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
public class SAEReportPriorTherapyMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<SAEReportPriorTherapy> srcSAEReportPriorTherapys = aeReportSrc.getSaeReportPriorTherapies();
		List<SAEReportPriorTherapy> destSAEReportPriorTherapys = aeReportDest.getSaeReportPriorTherapies();
    	
    	if ( srcSAEReportPriorTherapys == null || srcSAEReportPriorTherapys.size() == 0) {
    		outcome.addWarning("WR-SPT-1", "Input doesn't contain any SAEReportPriorTherapy Values.");
    		return;
    	}
		
    	if ( destSAEReportPriorTherapys == null ) {
    		destSAEReportPriorTherapys = new ArrayList<SAEReportPriorTherapy>();
    	}
    	// Copy the SAEReportPriorTherapys Information from Source to Destination.
    	for ( SAEReportPriorTherapy spt : srcSAEReportPriorTherapys) {
    		SAEReportPriorTherapy destSAEReportPriorTherapy = new SAEReportPriorTherapy();
    		copyProperties(spt, destSAEReportPriorTherapy);
    		destSAEReportPriorTherapys.add(destSAEReportPriorTherapy);
    	}
	}    	 
	
	/**
	 * Copy the Details from the UserInput.
	 * @param src
	 * @param dest
	 */
	private void copyProperties(SAEReportPriorTherapy src, SAEReportPriorTherapy dest) {
		dest.setEndDate(src.getEndDate());
		dest.setStartDate(src.getStartDate());
		dest.setOther(src.getOther());
		dest.setPriorTherapy(src.getPriorTherapy());
		dest.setPriorTherapyAgentsInternal(src.getPriorTherapyAgentsInternal());
	}
}