/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.ArrayList;
import java.util.List;

import gov.nih.nci.cabig.caaers.dao.PriorTherapyDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User:medaV
 * Date: 1/24/13
 */
public class SAEReportPriorTherapyMigrator implements Migrator<ExpeditedAdverseEventReport> {
    public PriorTherapyDao getPriorTherapyDao() {
        return priorTherapyDao;
    }

    public void setPriorTherapyDao(PriorTherapyDao priorTherapyDao) {
        this.priorTherapyDao = priorTherapyDao;
    }

    private PriorTherapyDao priorTherapyDao;

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

        List<String> pTherapies = new ArrayList<String>();

        for ( SAEReportPriorTherapy spt : srcSAEReportPriorTherapys) {
            pTherapies.add(spt.getPriorTherapy().getText());
        }
        List<PriorTherapy> priorTherapyList = loadPriorTherapies(pTherapies);

    	// Copy the SAEReportPriorTherapys Information from Source to Destination.
    	for ( SAEReportPriorTherapy spt : srcSAEReportPriorTherapys) {
            PriorTherapy pt = findPriorTherapies(priorTherapyList, spt.getPriorTherapy());
            if (pt == null) {
                outcome.addWarning("ER-SPT-1", "SAE Report Prior Therapy Values are not matching.");
                return;
            }
    		validateSAEREportPriorTherapyDates(spt, outcome);
    		SAEReportPriorTherapy destSAEReportPriorTherapy = new SAEReportPriorTherapy();
            destSAEReportPriorTherapy.setPriorTherapy(pt);
            destSAEReportPriorTherapy.setReport(aeReportDest);
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
	}

    /**
     *  find the Prior Therapy from List.
     */
    private PriorTherapy findPriorTherapies(List<PriorTherapy> priorTherapyList, PriorTherapy pt) {
        PriorTherapy result = null;
        for ( PriorTherapy iter: priorTherapyList) {
            if (iter.getText().equals(pt.getText())) {
                result = iter;
                break;
            }
        }
        return result;
    }


    /**
     *   Load the Prior Therapies.
     */
    private List<PriorTherapy> loadPriorTherapies(List<String> pTherapies) {
        String[] pTherapiesArr = pTherapies.toArray(new String[pTherapies.size()]) ;
        return priorTherapyDao.getBySubnames(pTherapiesArr);
    }
	
	/**
	 * Validate SAEREportPriorTherapy Dates.
	 * @param saeReportPriorTherapy
	 * @param outcome
	 */
	private void validateSAEREportPriorTherapyDates(SAEReportPriorTherapy saeReportPriorTherapy,  DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){
		if(saeReportPriorTherapy.getStartDate() != null && saeReportPriorTherapy.getEndDate() != null){
			if(saeReportPriorTherapy.getStartDate().toDate().after(saeReportPriorTherapy.getEndDate().toDate())){
				 outcome.addError("PAT_PTY1_ERR", "Report PriorTherapy 'end date' cannot be before 'start date'' ");
			}
		}
	}
}
