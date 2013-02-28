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
		List<SAEReportPreExistingCondition> destPreExistingConditions = aeReportDest.getSaeReportPreExistingConditions();
    	
    	if ( srcPreExistingConditions == null || srcPreExistingConditions.size() == 0) {
    		outcome.addWarning("WR-SPM-1", "Input doesn't contain any SAE report pre-existing Values.");
    		return;
    	}
    	
    	if ( destPreExistingConditions == null ) {
    		destPreExistingConditions = new ArrayList<SAEReportPreExistingCondition>();
    	}

        List<String> pcs = new ArrayList<String>();

        for ( SAEReportPreExistingCondition spc : srcPreExistingConditions) {
            pcs.add(spc.getPreExistingCondition().getText());
        }
        List<PreExistingCondition> preExistingConditionList =    loadPreExistingConditions(pcs);

    	// Copy the SAEReportPriorTherapys Information from Source to Destination.
    	for ( SAEReportPreExistingCondition spc : srcPreExistingConditions) {
            PreExistingCondition pc =  findPreconditions(preExistingConditionList, spc.getPreExistingCondition());
            if ( pc == null) {
                outcome.addError("ER-SPM-1", "SAE report pre-existing conditions are not matching.");
                return;
            }
    		SAEReportPreExistingCondition destPreExistingCondition = new SAEReportPreExistingCondition();
            destPreExistingCondition.setPreExistingCondition(pc);
            destPreExistingCondition.setReport(aeReportDest);
    		copyProperties(spc, destPreExistingCondition);
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
		dest.setVersion(src.getVersion());
	}
    /**
     *   find the Pre Existing Condition from the List.
     */
     private PreExistingCondition findPreconditions(List<PreExistingCondition> preExistingConditionList, PreExistingCondition pc) {
         PreExistingCondition result = null;
         for (PreExistingCondition iter : preExistingConditionList) {
              if(iter.getText().equals(pc.getText())) {
                  result = iter;
                  break;
              }
         }
         return result;
     }
    /**
     * query the existing pre conditions from the database.
     * @param pcs
     * @return
     */
    private List<PreExistingCondition> loadPreExistingConditions(List<String> pcs) {
        String[] pcsArr = pcs.toArray(new String[pcs.size()]);
        return preExistingConditionDao.getBySubnames(pcsArr);

    }
}
