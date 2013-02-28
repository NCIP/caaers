/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import gov.nih.nci.cabig.caaers.dao.LabTermDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User:medaV
 * Date: 1/21/13
 */
public class LabMigrator implements Migrator<ExpeditedAdverseEventReport> {

    public LabTermDao getLabTermDao() {
        return labTermDao;
    }

    public void setLabTermDao(LabTermDao labTermDao) {
        this.labTermDao = labTermDao;
    }

    private LabTermDao labTermDao;

    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<Lab> srcLabs = aeReportSrc.getLabs();
		List<Lab> destLabs = aeReportDest.getLabs();
    	
    	if ( srcLabs == null || srcLabs.size() == 0) {
    		outcome.addWarning("WR-LM-1", "Input doesn't contain any Lab Values.");
    		return;
    	}
		
    	if ( destLabs == null ) {
    		destLabs = new ArrayList<Lab>();
    	}

        List<String> labNames  = new ArrayList<String>();

        for ( Lab lab : srcLabs) {
            labNames.add(lab.getLabTerm().getTerm());
        }
        List<LabTerm> labTermList = loadLabTerms(labNames);
    	// Copy the Labs Information from Source to Destination.
    	for ( Lab lab : srcLabs) {
            LabTerm result = findLabTerm(labTermList, lab.getLabTerm());
            if ( result == null) {
                outcome.addError("ER-LM-1", "Lab term is not found for " + lab.getLabTerm().getTerm());
                break;
            }
    		Lab destLab = new Lab();
            destLab.setLabTerm(result);
    		copyProperties(lab, destLab);
    		destLab.setReport(aeReportDest);
    		destLabs.add(destLab);
    	}
	}    	 
	
	/**
	 * Copy the Details from the UserInput.
	 * @param src
	 * @param dest
	 */
	private void copyProperties(Lab src, Lab dest) {
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

    /**
     * Load Labterm values.
     */
    private  List<LabTerm> loadLabTerms(List<String> labNames) {

        String[] labsArr = labNames.toArray(new String[labNames.size()]);
        List<LabTerm> resultSites = labTermDao.getBySubname(labsArr, null);

        return resultSites;

    }

    /**
     * find LabTerm value for given input.
     */
    private LabTerm findLabTerm(List<LabTerm> labTermList, LabTerm labTerm) {
        LabTerm result  = null;
        for (LabTerm lt :  labTermList) {
            if ( lt.getTerm().equals(labTerm.getTerm()) && lt.getCategory().getName().equals(labTerm.getCategory().getName())) {
                result = lt;
                break;
            }
        }
        return result;
    }
}
