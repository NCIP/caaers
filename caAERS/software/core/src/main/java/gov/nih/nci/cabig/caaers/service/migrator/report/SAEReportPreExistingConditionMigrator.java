/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.dao.PreExistingConditionDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User:medaV
 * Date: 1/24/13
 */
public class SAEReportPreExistingConditionMigrator implements Migrator<ExpeditedAdverseEventReport> {

    public PreExistingConditionDao getPreExistingConditionDao() {
        return preExistingConditionDao;
    }

    public void setPreExistingConditionDao(PreExistingConditionDao preExistingConditionDao) {
        this.preExistingConditionDao = preExistingConditionDao;
    }

    private PreExistingConditionDao preExistingConditionDao;

	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<SAEReportPreExistingCondition> srcPreExistingConditions = aeReportSrc.getSaeReportPreExistingConditions();

    	if ( srcPreExistingConditions == null || srcPreExistingConditions.isEmpty()) {
    		return;
    	}
    	

    	// Copy the SAEReportPriorTherapys Information from Source to Destination.
    	for ( SAEReportPreExistingCondition spc : srcPreExistingConditions) {
            PreExistingCondition pc =  findPreconditions(spc.getPreExistingCondition(), outcome);
            if ( outcome.hasErrors()) {
                return;
            }
    		SAEReportPreExistingCondition destPreExistingCondition = new SAEReportPreExistingCondition();
            destPreExistingCondition.setPreExistingCondition(pc);
            copyProperties(spc, destPreExistingCondition);
    		aeReportDest.addSaeReportPreExistingCondition(destPreExistingCondition);
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
	}
    /**
     *   find the Pre Existing Condition from the List.
     */
     private PreExistingCondition findPreconditions(PreExistingCondition pc, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {         

         if(pc == null) return null;

         List<PreExistingCondition> resultLst = getPreExistingConditionDao().searchByExample(pc, false);
         if(resultLst == null || resultLst.isEmpty()) {
         	outcome.addError("ER-SPM-1", "Matching preExisting condition is not found for " + pc.getText());
         	return null;
         }
         if(resultLst.size() > 1 ) {
         	outcome.addError("ER-SPM-2", "Multiple matching preExisting conditions found for " + pc.getText() );
         	return null;
         }
         return resultLst.get(0);
     }
    
}
