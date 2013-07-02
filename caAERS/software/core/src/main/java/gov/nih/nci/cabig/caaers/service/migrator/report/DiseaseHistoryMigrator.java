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
		
       	copyDiseaseHistory(srcDisHis, destDisHis, history, outcome);
       	if(outcome.hasErrors()) return;	
    }
	/**
	 * Copy Disease History details from Input to the Domain Object.
	 * @param srcDisHis
	 * @param destDisHis
	 * @param history
	 * @param outcome
	 */
    
    private void copyDiseaseHistory(DiseaseHistory srcDisHis, DiseaseHistory destDisHis, StudyParticipantDiseaseHistory history, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
    	
    	if (StringUtils.isNotEmpty(srcDisHis.getCodedPrimaryDiseaseSite().getName()) ) {
            AnatomicSite anatomicSite = srcDisHis.getCodedPrimaryDiseaseSite();
            AnatomicSite result =  findAnatomicSiteByName(anatomicSite, outcome);
            
            if(outcome.hasErrors()) return;
            //set in the report
            destDisHis.setCodedPrimaryDiseaseSite(result);
            //set in the participant medical history
            history.setCodedPrimaryDiseaseSite(result);

          // Copy the other Disease site if provided.
            if (StringUtils.isNotBlank(srcDisHis.getOtherPrimaryDiseaseSite()))
                destDisHis.setOtherPrimaryDiseaseSite(srcDisHis.getOtherPrimaryDiseaseSite());

    	}

       if ( srcDisHis.getMetastaticDiseaseSites().size() > 0) {            

            for ( MetastaticDiseaseSite diseaseSite : srcDisHis.getMetastaticDiseaseSites()) {
                if ( diseaseSite.getCodedSite() != null ) {
                    AnatomicSite result =  findAnatomicSiteByName(diseaseSite.getCodedSite(), outcome);
                    if(outcome.hasErrors()) return;
                    MetastaticDiseaseSite mds = new MetastaticDiseaseSite();
                    mds.setCodedSite(result);
                    //set the metastatic site in report
                    destDisHis.getMetastaticDiseaseSites().add(mds);
                    
                    //if new, add the metastatic site to the participant medical history
                    if(findIndexStudyParticipantMetastaticSite(history.getMetastaticDiseaseSites(), mds) == -1) {
                    	history.addMetastaticDiseaseSite(
                    			StudyParticipantMetastaticDiseaseSite.createAssignmentMetastaticDiseaseSite(mds));
                    }

                }
            }
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
                outcome.addError("ER-DHM-5", "Primary Disease is not found on the Study " + srcDisHis.getAbstractStudyDisease().getTermName() );
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
                outcome.addError("ER-DHM-5", "Primary Disease is not found on the Study " + srcDisHis.getAbstractStudyDisease().getTermName() );
                return;
            }

        }

        // Copy the other Primary disease site.
       if (StringUtils.isNotBlank(srcDisHis.getOtherPrimaryDisease()) )
           destDisHis.setOtherPrimaryDisease(srcDisHis.getOtherPrimaryDisease());
    }
   
    
    private int findIndexStudyParticipantMetastaticSite(List<StudyParticipantMetastaticDiseaseSite> srcMetaStaticSites, MetastaticDiseaseSite destSite) {
        int index = -1;

        for ( StudyParticipantMetastaticDiseaseSite site: srcMetaStaticSites ) {
            index ++;
            if ( site.getCodedSite().getName().equals(destSite.getCodedSite().getName())) { // Found a match.
            	 return index;
            }
         }

        return  -1;
    }


    private AnatomicSite findAnatomicSiteByName(AnatomicSite site, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
        List<AnatomicSite> resultLst = anatomicSiteDao.searchByExample(site, false);
        if(resultLst == null || resultLst.isEmpty()) {
        	outcome.addError("ER-DHM-3", "Matching disease site is not found for " + site.getName() );
        	return null;
        }
        if(resultLst.size() > 1 ) {
        	outcome.addError("ER-DHM-4", "Multiple matching disease sites found for " + site.getName() );
        	return null;
        }
        return resultLst.get(0);
    }   
           
}
