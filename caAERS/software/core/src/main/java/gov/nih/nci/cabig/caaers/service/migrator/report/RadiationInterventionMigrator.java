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
public class RadiationInterventionMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<RadiationIntervention> srcRadiations = aeReportSrc.getRadiationInterventions();
		List<RadiationIntervention> destRadiations = aeReportDest.getRadiationInterventions();
    	
    	if ( srcRadiations == null || srcRadiations.size() == 0 ) {
    		outcome.addWarning("WR-RIM-1", "Input doesn't contain any Radiation values, so skipping this step.");
    		return;
    	}
    	
    	if ( srcRadiations.size() > 1) {
    		outcome.addError("ER-RIM-1", "Input contains more than one Radiation Intervention");
    		return;
    	}

        // 1. Load the study.
        Study study = aeReportDest.getStudy();

        List<OtherIntervention> otherStudyRadiationList = study.getActiveStudyRadiations();
        if ( srcRadiations.size() > 0 && (otherStudyRadiationList == null || otherStudyRadiationList.isEmpty()) ) {
            outcome.addError("ER-RIM-2", "Study doesn't contain any Active Radiation Intervention." );
            return;
        } 
        
        OtherIntervention oi = otherStudyRadiationList.get(0);

    	if ( destRadiations == null ) {
    		destRadiations = new ArrayList<RadiationIntervention>();
    	}
    	// Copy the Labs Information from Source to Destination.
    	for ( RadiationIntervention ri : srcRadiations) {
            RadiationIntervention destRI = new RadiationIntervention();
            destRI.setStudyRadiation(oi);
            destRI.setReport(aeReportDest);
    		copyRadiationInterventionDetails(ri, destRI);
    		destRadiations.add(destRI);
        }

	}

    /**
     *  Copy Radiation Details from UserInput.
     * @param src
     * @param dest
     */
	private void copyRadiationInterventionDetails(RadiationIntervention src, RadiationIntervention dest) {
		dest.setAdministration(src.getAdministration());
        dest.setLastTreatmentDate(src.getLastTreatmentDate());
		dest.setFractionNumber(src.getFractionNumber());
		dest.setDosageUnit(src.getDosageUnit());
		dest.setDosage(src.getDosage());
		dest.setDaysElapsed(src.getDaysElapsed());
		dest.setAdjustment(src.getAdjustment());
		dest.setDescription(src.getDescription());
		dest.setTreatmentArm(src.getTreatmentArm());
	}
}
