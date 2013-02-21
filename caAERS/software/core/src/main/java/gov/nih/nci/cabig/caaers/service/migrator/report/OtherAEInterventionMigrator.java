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
public class OtherAEInterventionMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
		List<OtherAEIntervention> srcOtherAEInterventions = aeReportSrc.getOtherAEInterventions();
		List<OtherAEIntervention> destOtherAEInterventions = aeReportDest.getOtherAEInterventions();
    	
    	if ( srcOtherAEInterventions == null || srcOtherAEInterventions.size() == 0) {
    		outcome.addWarning("WR-OAI-1", "Input doesn't contain any OtherAEIntervention Values.");
    		return;
    	}
		
    	if ( destOtherAEInterventions == null ) {
    		destOtherAEInterventions = new ArrayList<OtherAEIntervention>();
    	}

        // 1. Load the study.
        Study study = aeReportDest.getStudy();

        List<OtherIntervention> otherAEInterventionList = study.getActiveStudyOtherInterventions();

    	// Copy the OtherAEInterventions Information from Source to Destination.
    	for ( OtherAEIntervention otherAE : srcOtherAEInterventions) {

            OtherIntervention oi = findActiveOtherAEOnStudy(otherAEInterventionList, otherAE.getStudyIntervention());
            if ( oi == null ) {
                outcome.addError("ER-OAI-1", "Input doesn't contain valid values" );
                break;
            }

    		OtherAEIntervention destOtherAEIntervention = new OtherAEIntervention();
            destOtherAEIntervention.setStudyIntervention(oi);
            destOtherAEIntervention.setReport(aeReportDest);
    		copyProperties(otherAE, destOtherAEIntervention);
    		destOtherAEInterventions.add(destOtherAEIntervention);
    	}	
	}    	 
	
	/**
	 * Copy the Details from the UserInput.
	 * @param src
	 * @param dest
	 */
	private void copyProperties(AbstractAEIntervention src, AbstractAEIntervention dest) {
		dest.setDescription(src.getDescription());
	}

    private OtherIntervention findActiveOtherAEOnStudy(List<OtherIntervention> otherAEInterventionList, OtherIntervention oi) {

        OtherIntervention result = null;
        for( OtherIntervention iter: otherAEInterventionList) {
            if ( iter.getName().equals(oi.getName()) && iter.getDescription().equals(oi.getDescription())) {
                result = iter;
                break;
            }
        }
        return result;
    }
}