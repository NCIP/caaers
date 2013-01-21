package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User:medaV
 * Date: 1/17/13
 */
public class ParticipantHistoryMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		ParticipantHistory srcParticipantHis = aeReportSrc.getParticipantHistory();
    	ParticipantHistory destParticipantHis = aeReportDest.getParticipantHistory();
    	
    	if ( srcParticipantHis == null ) {
    		outcome.addWarning("WR-PHM-1", "Participant doesn't have any prior history");
    		return;
    	}
    	
    	if ( destParticipantHis == null ) {
    		destParticipantHis = new ParticipantHistory();
    	}
    	
    	copyParticipantHistory(srcParticipantHis, destParticipantHis);
    }
    
    private void copyParticipantHistory(ParticipantHistory srcParticipantHis, ParticipantHistory destParticipantHis) {
    	
    	destParticipantHis.setBaselinePerformanceStatus(srcParticipantHis.getBaselinePerformanceStatus());
    	destParticipantHis.setBsa(srcParticipantHis.getBsa());
    	destParticipantHis.setHeight(srcParticipantHis.getHeight());
    	destParticipantHis.setVersion(srcParticipantHis.getVersion());
    	destParticipantHis.setWeight(srcParticipantHis.getWeight());
    	
    }
}
