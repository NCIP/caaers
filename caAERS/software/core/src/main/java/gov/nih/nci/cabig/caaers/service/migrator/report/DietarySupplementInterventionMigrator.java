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
public class DietarySupplementInterventionMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<DietarySupplementIntervention> srcDietarySupplementInterventions = aeReportSrc.getDietaryInterventions();
		List<DietarySupplementIntervention> destDietarySupplementInterventions = aeReportDest.getDietaryInterventions();
    	
    	if ( srcDietarySupplementInterventions == null || srcDietarySupplementInterventions.size() == 0 ) {
    		outcome.addWarning("WR-DSM-1", "Input doesn't contain any DietarySupplementIntervention Values.");
    		return;
    	}
		
    	if ( destDietarySupplementInterventions == null ) {
    		destDietarySupplementInterventions = new ArrayList<DietarySupplementIntervention>();
    	}

        Study study = aeReportDest.getStudy();
        List<OtherIntervention> studyDietaryInterventionList =  study.getActiveStudyDietaryInterventions();
    	// Copy the DietarySupplementInterventions Information from Source to Destination.
    	for ( DietarySupplementIntervention dsi : srcDietarySupplementInterventions) {
    		DietarySupplementIntervention destDietarySupplementIntervention = new DietarySupplementIntervention();
            OtherIntervention oi = findActiveDietariesOnStudy(studyDietaryInterventionList, dsi.getStudyIntervention() );
            if ( oi == null ) {
                outcome.addError("ER_DIM_1", "Study doesn't contain the value provided from the Input." );
                break;
            }
            destDietarySupplementIntervention.setStudyIntervention(oi);
            destDietarySupplementIntervention.setReport(aeReportDest);
    		copyProperties(dsi, destDietarySupplementIntervention);
    		destDietarySupplementInterventions.add(destDietarySupplementIntervention);

    	}	
	}

    /**
     *  find the Active Radiation on Study.
     * @param studyDietaryInterventionList
     * @param oi
     * @return
     */
    private OtherIntervention findActiveDietariesOnStudy(List<OtherIntervention> studyDietaryInterventionList, OtherIntervention oi) {
        return ReportUtil.findActiveInterventionOnStudy(studyDietaryInterventionList, oi);
    }

	/**
	 * Copy the Details from the UserInput.
	 * @param src
	 * @param dest
	 */
	private void copyProperties(AbstractAEIntervention src, AbstractAEIntervention dest) {
        if (src.getDescription() != null)
		    dest.setDescription(src.getDescription());
        if(src.getVersion() != null)
            dest.setVersion(src.getVersion());
	}

}
