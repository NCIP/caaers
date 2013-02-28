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
        // 1. Load the study.
        Study study = aeReportDest.getStudy();

        List<OtherIntervention> otherGeneticInterventionList = study.getActiveStudyGeneticInterventions();

        // Copy the GeneticInterventions Information from Source to Destination.
    	for ( GeneticIntervention geIntervention : srcGeneticInterventions) {

            OtherIntervention oi = findActiveGIOnStudy(otherGeneticInterventionList, geIntervention.getStudyIntervention());
            if ( oi == null ) {
                outcome.addError("ER-GIM-1", "Input doesn't contain valid values" );
                break;
            }
            GeneticIntervention destGeneticIntervention = new GeneticIntervention();
            destGeneticIntervention.setStudyIntervention(oi);
            destGeneticIntervention.setReport(aeReportDest);
    		copyProperties(geIntervention, destGeneticIntervention);
    		destGeneticInterventions.add(destGeneticIntervention);

    	}	

	}

    /**
     *  find the Active GI on Study.
     * @param otherGeneticInterventionList
     * @param oi
     * @return
     */
    private OtherIntervention findActiveGIOnStudy(List<OtherIntervention> otherGeneticInterventionList, OtherIntervention oi) {

        OtherIntervention result = null;
        for( OtherIntervention iter: otherGeneticInterventionList) {
            if ( iter.getName().equals(oi.getName()) && iter.getDescription().equals(oi.getDescription())) {
                result = iter;
                break;
            }
        }
        return result;
    }
	
	/**
	 * Copy the Details from the UserInput.
	 * @param src
	 * @param dest
	 */
	private void copyProperties(AbstractAEIntervention src, AbstractAEIntervention dest) {
		dest.setDescription(src.getDescription());
	}
}
