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
    
		DiseaseHistory src = aeReportSrc.getDiseaseHistory();
		DiseaseHistory dest = aeReportDest.getDiseaseHistory();

        dest.setOtherPrimaryDisease(src.getOtherPrimaryDisease());
        dest.setOtherPrimaryDiseaseSite(src.getOtherPrimaryDiseaseSite());
        dest.setDiagnosisDate(src.getDiagnosisDate());

        AbstractStudyDisease abstractStudyDisease = null;
        if(src.getAbstractStudyDisease() != null && src.getAbstractStudyDisease().getTerm() != null && src.getAbstractStudyDisease().getTermName() != null) {
            abstractStudyDisease = aeReportDest.getStudy().findActiveStudyDisease(src.getAbstractStudyDisease().getTermName());
            if(abstractStudyDisease == null) outcome.addError("ER-DHM-5", "Primary Disease is not found on the Study " + src.getAbstractStudyDisease().getTermName() );
        }
        dest.setAbstractStudyDisease(abstractStudyDisease);

        dest.setCodedPrimaryDiseaseSite(findAnatomicSite(src.getCodedPrimaryDiseaseSite(), outcome));

        for(MetastaticDiseaseSite diseaseSite : src.getMetastaticDiseaseSites()){
            MetastaticDiseaseSite metastaticDiseaseSite = new MetastaticDiseaseSite();
            AnatomicSite anatomicSite = findAnatomicSite(diseaseSite.getCodedSite(), outcome);
            metastaticDiseaseSite.setCodedSite(anatomicSite);
            if(anatomicSite != null) metastaticDiseaseSite.setOtherSite(diseaseSite.getOtherSite());
            if(outcome.hasErrors()) return;
            dest.addMetastaticDiseaseSite(metastaticDiseaseSite);
        }
    }


    private AnatomicSite findAnatomicSite(AnatomicSite site, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
        if(site == null || site.getName() == null) return null;
        String name = site.getName();
        String category = site.getCategory();


        AnatomicSite anatomicSite = category != null ?  getAnatomicSiteDao().findByNameAndCategory(name,category) :  getAnatomicSiteDao().findByName(name);
        if(anatomicSite == null){
            outcome.addError("ER-DHM-3", "Matching disease site is not found for " + site.getName());
        }

        return anatomicSite;
    }

}
