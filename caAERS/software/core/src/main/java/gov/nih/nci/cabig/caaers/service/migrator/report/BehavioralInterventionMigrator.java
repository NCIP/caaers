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
 * Date: 1/24/13
 */
public class BehavioralInterventionMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		List<BehavioralIntervention> srcBehavioralInterventions = aeReportSrc.getBehavioralInterventions();
		List<BehavioralIntervention> destBehavioralInterventions = aeReportDest.getBehavioralInterventions();
    	
    	if ( srcBehavioralInterventions == null || srcBehavioralInterventions.size() == 0) {
    		outcome.addWarning("WR-BEM-1", "Input doesn't contain any BehavioralIntervention Values.");
    		return;
    	}
		
    	if ( destBehavioralInterventions == null ) {
    		destBehavioralInterventions = new ArrayList<BehavioralIntervention>();
    	}
        Study study = aeReportDest.getStudy();
        List<OtherIntervention> otherBehaviorList = study.getActiveStudyBehavioralInterventions();

    	// Copy the BehavioralInterventions Information from Source to Destination.
    	for ( BehavioralIntervention behavioralIntervention : srcBehavioralInterventions) {
    		BehavioralIntervention destBI = new BehavioralIntervention();
            OtherIntervention oi =  findActiveBehavioralOnStudy(otherBehaviorList,behavioralIntervention.getStudyIntervention() );
            if ( oi == null ) {
                outcome.addError("ER-BIM-1", "Study doesn't contain the value provided from the Input." );
                break;
            }
            destBI.setReport(aeReportDest);
            destBI.setStudyIntervention(oi);
    		copyProperties(behavioralIntervention, destBI);
    		destBehavioralInterventions.add(destBI);

    	}
	}    	 
	
	/**
	 * Copy the Details from the UserInput.
	 * @param src
	 * @param dest
	 */
	private void copyProperties(BehavioralIntervention src, BehavioralIntervention dest) {
        if ( src.getDescription() != null)
		    dest.setDescription(src.getDescription());
        if (src.getStudyIntervention() != null)
		    dest.setStudyIntervention(src.getStudyIntervention());
        if (src.getVersion() != null)
            dest.setVersion(src.getVersion());
	}

    /**
     *  find the Active Radiation on Study.
     * @param otherBehaviorList
     * @param oi
     * @return
     */
    private OtherIntervention findActiveBehavioralOnStudy(List<OtherIntervention> otherBehaviorList, OtherIntervention oi) {
        return ReportUtil.findActiveInterventionOnStudy(otherBehaviorList, oi);
    }
}
