/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
public class OtherCauseMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<OtherCause> srcOtherCauses = aeReportSrc.getOtherCauses();
		List<OtherCause> destOtherCauses = aeReportDest.getOtherCauses();
    	
    	if ( srcOtherCauses == null || srcOtherCauses.size() == 0) {
    		outcome.addWarning("WR-OCM-1", "Input doesn't contain any OtherCause Values.");
    		return;
    	}
		
    	if ( destOtherCauses == null ) {
    		destOtherCauses = new ArrayList<OtherCause>();
    	}
    	// Copy the OtherCauses Information from Source to Destination.
    	for ( OtherCause oCause : srcOtherCauses) {
    		OtherCause destOtherCause = new OtherCause();
    		copyProperties(oCause, destOtherCause);
    		destOtherCause.setReport(aeReportDest);
    		destOtherCauses.add(destOtherCause);
    	}
	}    	 
	
	/**
	 * Copy the Details from the UserInput.
	 * @param src
	 * @param dest
	 */
	private void copyProperties(OtherCause src, OtherCause dest) {
		dest.setText(src.getText());
		dest.setVersion(src.getVersion());
	}
}
