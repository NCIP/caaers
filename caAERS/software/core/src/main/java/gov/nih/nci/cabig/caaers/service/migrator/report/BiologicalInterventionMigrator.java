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
public class BiologicalInterventionMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<BiologicalIntervention> srcBiologicalInterventions = aeReportSrc.getBiologicalInterventions();
		List<BiologicalIntervention> destBiologicalInterventions = aeReportDest.getBiologicalInterventions();
    	
    	if ( srcBiologicalInterventions == null || srcBiologicalInterventions.size() == 0) {
    		outcome.addWarning("WR-BIM-1", "Input doesn't contain any BiologicalIntervention Values.");
    		return;
    	}
		
    	if ( destBiologicalInterventions == null ) {
    		destBiologicalInterventions = new ArrayList<BiologicalIntervention>();
    	}

        Study study = aeReportDest.getStudy();
        List<OtherIntervention> otherStudyBiologicalList = study.getActiveStudyBiologicalInterventions();


    	// Copy the BiologicalInterventions Information from Source to Destination.
    	for ( BiologicalIntervention bioIntervention : srcBiologicalInterventions) {
    		BiologicalIntervention destBI = new BiologicalIntervention();
            OtherIntervention oi = findActiveBiologicalInterventionOnStudy(otherStudyBiologicalList, bioIntervention.getStudyIntervention());
            if ( oi == null ) {
                outcome.addError("ER_BIM_1", "Study doesn't contain the value provided from the Input." );
                break;
            }
            destBI.setStudyIntervention(oi);
            destBI.setReport(aeReportDest);
            copyProperties(bioIntervention, destBI);
    		destBiologicalInterventions.add(destBI);

    	}

	}

    /**
     *  find the Active Radiation on Study.
     * @param otherStudyBiologicalList
     * @param oi
     * @return
     */
    private OtherIntervention findActiveBiologicalInterventionOnStudy(List<OtherIntervention> otherStudyBiologicalList, OtherIntervention oi) {

        OtherIntervention result = null;
        for( OtherIntervention iter: otherStudyBiologicalList) {
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
	private void copyProperties(BiologicalIntervention src, BiologicalIntervention dest) {
        if (src.getDescription() != null)
		    dest.setDescription(src.getDescription());
        if (src.getVersion() != null)
            dest.setVersion(src.getVersion());
	}
}