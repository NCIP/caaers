/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.dao.AnatomicSiteDao;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.List;

/**
 * User:medaV
 * Date: 1/21/13
 */
public class DiseaseHistoryMigrator implements Migrator<ExpeditedAdverseEventReport> {

    public AnatomicSiteDao getAnatomicSiteDao() {
        return anatomicSiteDao;
    }

    public void setAnatomicSiteDao(AnatomicSiteDao anatomicSiteDao) {
        this.anatomicSiteDao = anatomicSiteDao;
    }

    private AnatomicSiteDao anatomicSiteDao ;
    
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
    		if ( history != null ) {
    			CopyFromStudyParticipantDiseaseHistory(history, destDisHis);
    		}
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

        List<String> anatomicSites = new ArrayList<String>();
        int counter = 0;
    	for ( MetastaticDiseaseSite diseaseSite : srcDisHis.getMetastaticDiseaseSites()) {
            if ( diseaseSite.getCodedSite() != null ) {
                anatomicSites.add(diseaseSite.getCodedSite().getName());
            }
            counter++;
    	}
         String[] anatomicSitesArr =   anatomicSites.toArray(new String[anatomicSites.size()]);
         List<AnatomicSite> anaSites =  anatomicSiteDao.getBySubnames(anatomicSitesArr);

        for ( MetastaticDiseaseSite diseaseSite : srcDisHis.getMetastaticDiseaseSites()) {
            if ( diseaseSite.getCodedSite() != null) {
                AnatomicSite result =  findAnatomicSiteByName(anaSites, diseaseSite.getCodedSite());
                MetastaticDiseaseSite mds = new MetastaticDiseaseSite();
                mds.setCodedSite(result);
                destDisHis.getMetastaticDiseaseSites().add(mds);
            }
        }

    	destDisHis.setDiagnosisDate(srcDisHis.getDiagnosisDate());

    }

    private AnatomicSite findAnatomicSiteByName(List<AnatomicSite> anaSites, AnatomicSite site) {
        AnatomicSite result = null;

        for ( AnatomicSite anaSite : anaSites) {
                if  ( anaSite.getName().equals(site.getName()) ) {
                    result = anaSite;
                    break;
                }
        }
        return result;

    }
    
    /**
     * Copy the Details from the Participant Object. 
     * @param history
     * @param destHistory
     */
    private void CopyFromStudyParticipantDiseaseHistory(StudyParticipantDiseaseHistory history, DiseaseHistory destHistory) {

        destHistory.setVersion(history.getVersion());
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
