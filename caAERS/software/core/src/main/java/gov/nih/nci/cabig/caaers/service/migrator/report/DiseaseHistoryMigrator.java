package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User:medaV
 * Date: 1/21/13
 */
public class DiseaseHistoryMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
	public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    
		DiseaseHistory srcDisHis = aeReportSrc.getDiseaseHistory();
		DiseaseHistory destDisHis = aeReportDest.getDiseaseHistory();
		
    	if ( destDisHis == null ) {
    		destDisHis = new DiseaseHistory();
    	}
    	
    	Participant participant = aeReportDest.getParticipant();
    	
    	 StudySite site = null;
     	
         if ( aeReportDest.getReportingPeriod() != null && aeReportDest.getReportingPeriod().getAssignment() != null ) {
         	site = aeReportDest.getReportingPeriod().getAssignment().getStudySite();
         }
         
         if ( site == null ) {
         	 outcome.addError("ER-DHM-1", "Study Site is missing in the source");
              return;
         }
    	
    	if ( participant != null ) {
    		StudyParticipantAssignment assignment = participant.getStudyParticipantAssignment(site);
    		StudyParticipantDiseaseHistory history = assignment.getDiseaseHistory();
    		CopyFromStudyParticipantDiseaseHistory(history, destDisHis);
    	}
    	
    	if ( srcDisHis != null ) {
    		copyDiseaseHistory(srcDisHis, destDisHis);
    	}
    }
	/**
	 * Copy Disease History details from Input to the Domain Object.
	 * @param srcDisHis
	 * @param destDisHis
	 */
    
    private void copyDiseaseHistory(DiseaseHistory srcDisHis, DiseaseHistory destDisHis) {
    	if ( srcDisHis.getOtherPrimaryDisease() != null) 
    	destDisHis.setOtherPrimaryDisease(srcDisHis.getOtherPrimaryDisease());
    	
    	if(srcDisHis.getMeddraStudyDisease() != null) 
    	destDisHis.setMeddraStudyDisease(srcDisHis.getMeddraStudyDisease());
    	
    	for ( MetastaticDiseaseSite diseaseSite : srcDisHis.getMetastaticDiseaseSites()) {
    		destDisHis.addMetastaticDiseaseSite(diseaseSite);
    	}
    	
    	destDisHis.setDiagnosisDate(srcDisHis.getDiagnosisDate());
    	destDisHis.setCodedPrimaryDiseaseSite(srcDisHis.getCodedPrimaryDiseaseSite());
    	destDisHis.setCtepStudyDisease(srcDisHis.getCtepStudyDisease());
    	destDisHis.setAbstractStudyDisease(srcDisHis.getAbstractStudyDisease());

    }
    
    /**
     * Copy the Details from the Participant Object. 
     * @param history
     * @param destHistory
     */
    private void CopyFromStudyParticipantDiseaseHistory(StudyParticipantDiseaseHistory history, DiseaseHistory destHistory) {
    	
    	destHistory.setOtherPrimaryDisease(history.getOtherPrimaryDisease());
    	destHistory.setOtherPrimaryDiseaseSite(history.getOtherPrimaryDiseaseSite());
    	destHistory.setMeddraStudyDisease(history.getMeddraStudyDisease());
    	for (StudyParticipantMetastaticDiseaseSite studyParticipantMetastaticDiseaseSite :  history.getMetastaticDiseaseSites()) {
    		destHistory.addMetastaticDiseaseSite(MetastaticDiseaseSite.createReportMetastaticDiseaseSite(studyParticipantMetastaticDiseaseSite));
    	}
    	destHistory.setDiagnosisDate(history.getDiagnosisDate());
    	destHistory.setCodedPrimaryDiseaseSite(history.getCodedPrimaryDiseaseSite());
    	destHistory.setCtepStudyDisease(history.getCtepStudyDisease());
    	destHistory.setAbstractStudyDisease(history.getAbstractStudyDisease());
    }
}
