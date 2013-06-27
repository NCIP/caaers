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
import org.apache.commons.lang.StringUtils;

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
    	
    	if ( participant == null ) {
    		 outcome.addError("ER-DHM-2", "Participant is missing in the destination");
             return;
    	}
    	
		StudyParticipantAssignment assignment = participant.getStudyParticipantAssignment(site);
		StudyParticipantDiseaseHistory history = assignment.getDiseaseHistory();
		if ( history != null ) {
			copyFromStudyParticipantDiseaseHistory(history, destDisHis);
		}

        // After copying it from the patient make sure we have removed it from the srcDisHis object to remove the duplicates.
        removeDuplicateMetaStaticSites(srcDisHis, destDisHis);

       	copyDiseaseHistory(srcDisHis, destDisHis, outcome);
       	
       	//copy new ones to the SPA
        copyToStudyParticipantDiseaseHistory(destDisHis, history);
       	
    }
	/**
	 * Copy Disease History details from Input to the Domain Object.
	 * @param srcDisHis
	 * @param destDisHis
	 */
    
    private void copyDiseaseHistory(DiseaseHistory srcDisHis, DiseaseHistory destDisHis, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {


       if ( srcDisHis.getMetastaticDiseaseSites().size() > 0) {
            List<String> anatomicSites = new ArrayList<String>();

            for ( MetastaticDiseaseSite diseaseSite : srcDisHis.getMetastaticDiseaseSites()) {
                if ( diseaseSite.getCodedSite() != null ) {
                    anatomicSites.add(diseaseSite.getCodedSite().getName());
                }

            }
             String[] anatomicSitesArr =   anatomicSites.toArray(new String[anatomicSites.size()]);
             List<AnatomicSite> anaSites =  anatomicSiteDao.getBySubnames(anatomicSitesArr);

            for ( MetastaticDiseaseSite diseaseSite : srcDisHis.getMetastaticDiseaseSites()) {
                if ( diseaseSite.getCodedSite() != null ) {
                    AnatomicSite result =  findAnatomicSiteByName(anaSites, diseaseSite.getCodedSite());
                    MetastaticDiseaseSite mds = new MetastaticDiseaseSite();
                    mds.setCodedSite(result);
                    destDisHis.getMetastaticDiseaseSites().add(mds);

                }
            }
       }


        if (StringUtils.isNotEmpty(srcDisHis.getCodedPrimaryDiseaseSite().getName()) ) {
                AnatomicSite anatomicSite = srcDisHis.getCodedPrimaryDiseaseSite();
                String[] codedPrimaryAnatomicSite = new String[1];
                codedPrimaryAnatomicSite[0] = anatomicSite.getName();

                List<AnatomicSite> codedPrimaryAnaSites =  anatomicSiteDao.getBySubnames(codedPrimaryAnatomicSite);
                AnatomicSite result =  findAnatomicSiteByName(codedPrimaryAnaSites, anatomicSite);

                if ( result != null) {
                    destDisHis.setCodedPrimaryDiseaseSite(result);
                }  else {
                    // Output with Error.
                    outcome.addError("ER-DHM-2", "Primary Site of  Disease is not found " + anatomicSite.getName() );
                    return;
                }

              // Copy the other Disease site if provided.
                if (StringUtils.isNotBlank(srcDisHis.getOtherPrimaryDiseaseSite()))
                    destDisHis.setOtherPrimaryDiseaseSite(srcDisHis.getOtherPrimaryDiseaseSite());

        }


    	destDisHis.setDiagnosisDate(srcDisHis.getDiagnosisDate());

        if (  destDisHis.getReport().getStudy().hasCtepEsysIdentifier() && StringUtils.isNotEmpty( ((DiseaseTerm)srcDisHis.getAbstractStudyDisease().getTerm()).getTerm()) )  {
            List<CtepStudyDisease> ctepStudyDiseases = destDisHis.getReport().getStudy().getActiveCtepStudyDiseases();

            boolean studyDiesaseFound = false;
            for ( CtepStudyDisease disease : ctepStudyDiseases) {
                if ( disease.getTermName().equals(srcDisHis.getAbstractStudyDisease().getTermName())) {
                    destDisHis.setAbstractStudyDisease(disease);
                    studyDiesaseFound = true;
                    break;
                }
            }

            if ( !studyDiesaseFound ) { // If not found throw the error back to user.
                outcome.addError("ER-DHM-3", "Primary Disease is not found on the Study " + srcDisHis.getAbstractStudyDisease().getTermName() );
                return;
            }
        }   else {

            List<MeddraStudyDisease> meddraStudyDiseases = destDisHis.getReport().getStudy().getActiveMeddraStudyDiseases();

            boolean studyDiesaseFound = false;
            for ( MeddraStudyDisease disease : meddraStudyDiseases) {
                if ( disease.getTermName().equals(srcDisHis.getAbstractStudyDisease().getTermName())) {
                    destDisHis.setAbstractStudyDisease(disease);
                    studyDiesaseFound = true;
                    break;
                }
            }

            if ( !studyDiesaseFound ) { // If not found throw the error back to user.
                outcome.addError("ER-DHM-3", "Primary Disease is not found on the Study " + srcDisHis.getAbstractStudyDisease().getTermName() );
                return;
            }

        }

        // Copy the other Primary disease site.
       if (StringUtils.isNotBlank(srcDisHis.getOtherPrimaryDisease()) )
           destDisHis.setOtherPrimaryDisease(srcDisHis.getOtherPrimaryDisease());
    }

    private  void removeDuplicateMetaStaticSites(DiseaseHistory srcDisHis, DiseaseHistory destDisHis) {    	
        for (MetastaticDiseaseSite destSite: destDisHis.getMetastaticDiseaseSites()) {

              int index = findIndexMetastaticSite(srcDisHis.getMetastaticDiseaseSites(), destSite);

             if ( index >= 0 ) srcDisHis.getMetastaticDiseaseSites().remove(index);

        }

    }

    private int findIndexMetastaticSite(List<MetastaticDiseaseSite> srcMetaStaticSites, MetastaticDiseaseSite destSite) {
        int index = -1;

        for ( MetastaticDiseaseSite site: srcMetaStaticSites ) {
            index ++;
            if ( site.getCodedSite().getName().equals(destSite.getCodedSite().getName())) { // Found a duplicate.
                     break;
            }
         }

        return  index;
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
    private void copyFromStudyParticipantDiseaseHistory(StudyParticipantDiseaseHistory history, DiseaseHistory destHistory) {

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
    
    /**
     * Copy the Details from the Participant Object. 
     * @param history
     * @param destHistory
     */
    private void copyToStudyParticipantDiseaseHistory(DiseaseHistory history, StudyParticipantDiseaseHistory destHistory) {

        destHistory.setVersion(history.getVersion());
    	destHistory.setOtherPrimaryDisease(history.getOtherPrimaryDisease());
    	destHistory.setOtherPrimaryDiseaseSite(history.getOtherPrimaryDiseaseSite());
    	destHistory.setMeddraStudyDisease(history.getMeddraStudyDisease());
    	for (MetastaticDiseaseSite metastaticDiseaseSite :  history.getMetastaticDiseaseSites()) {
    		destHistory.addMetastaticDiseaseSite(StudyParticipantMetastaticDiseaseSite.createAssignmentMetastaticDiseaseSite(metastaticDiseaseSite));
    	}
    	destHistory.setDiagnosisDate(history.getDiagnosisDate());
    	destHistory.setCodedPrimaryDiseaseSite(history.getCodedPrimaryDiseaseSite());
    	destHistory.setCtepStudyDisease(history.getCtepStudyDisease());
    	destHistory.setAbstractStudyDisease(history.getAbstractStudyDisease());
    }
}
