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

        // 1. Load the study.
        Study study = aeReportDest.getStudy();

        List<OtherIntervention> otherStudyRadiationList = study.getActiveStudyRadiations();

    	if ( destRadiations == null ) {
    		destRadiations = new ArrayList<RadiationIntervention>();
    	}
    	// Copy the Labs Information from Source to Destination.
    	for ( RadiationIntervention ri : srcRadiations) {
    		RadiationIntervention destRI = new RadiationIntervention();
            OtherIntervention oi = findActiveRadiationOnStudy(otherStudyRadiationList, ri.getStudyRadiation());
            if ( oi == null ) {
                outcome.addError("ER-RIM-1", "Input doesn't contain valid values" );
                break;
            }
            destRI.setStudyRadiation(oi);
            destRI.setReport(aeReportDest);
    		copyRadiationInterventionDetails(ri, destRI);
    		destRadiations.add(destRI);
        }

	}

    /**
     *  find the Active Radiation on Study.
     * @param otherStudyRadiationList
     * @param oi
     * @return
     */
    private OtherIntervention findActiveRadiationOnStudy(List<OtherIntervention> otherStudyRadiationList, OtherIntervention oi) {

        OtherIntervention result = null;
        for( OtherIntervention iter: otherStudyRadiationList) {
            if ( iter.getName().equals(oi.getName()) && iter.getDescription().equals(oi.getDescription())) {
                result = iter;
                break;
            }
        }
        return result;
    }

    /**
     *  Copy Radiation Details from UserInput.
     * @param src
     * @param dest
     */
	private void copyRadiationInterventionDetails(RadiationIntervention src, RadiationIntervention dest) {

		if (src.getAdministration() != null)
		    dest.setAdministration(src.getAdministration());
        if (src.getLastTreatmentDate() != null)
		    dest.setLastTreatmentDate(src.getLastTreatmentDate());
        if (src.getFractionNumber() != null)
		    dest.setFractionNumber(src.getFractionNumber());
        if (src.getDosageUnit() != null)
		    dest.setDosageUnit(src.getDosageUnit());
        if (src.getDosage() != null)
		    dest.setDosage(src.getDosage());
        if (src.getDaysElapsed() != null)
		    dest.setDaysElapsed(src.getDaysElapsed());
        if (src.getAdjustment() != null)
		    dest.setAdjustment(src.getAdjustment());
        if (src.getDescription() != null)
		    dest.setDescription(src.getDescription());
        if (src.getTreatmentArm() != null)
		    dest.setTreatmentArm(src.getTreatmentArm());
		
	}
}
