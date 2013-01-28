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
    		outcome.addWarning("WR-RIM-1", "Input doesn't contain any Lab Values.");
    		return;
    	}
		
    	if ( destRadiations == null ) {
    		destRadiations = new ArrayList<RadiationIntervention>();
    	}
    	// Copy the Labs Information from Source to Destination.
    	for ( RadiationIntervention ri : srcRadiations) {
    		RadiationIntervention destRI = new RadiationIntervention();
    		copyRadiationInterventionDetails(ri, destRI);
    		destRadiations.add(destRI);
        }
	}    	 
	private void copyRadiationInterventionDetails(RadiationIntervention src, RadiationIntervention dest) {
		
		dest.setStudyRadiation(src.getStudyRadiation());
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