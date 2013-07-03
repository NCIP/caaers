package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.DiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.MetastaticDiseaseSite;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;
import org.apache.commons.lang.ObjectUtils;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class DiseaseHistorySynchronizer implements Migrator<ExpeditedAdverseEventReport> {

    public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
        DiseaseHistory src = aeReportSrc.getDiseaseHistory();
        DiseaseHistory dest = aeReportDest.getDiseaseHistory();

        if(!ObjectUtils.equals(dest.getAbstractStudyDisease(), src.getAbstractStudyDisease())){
            if(dest.getAbstractStudyDisease() != null) aeReportDest.cascaeDeleteToAttributions(dest.getAbstractStudyDisease());
            dest.setAbstractStudyDisease(src.getAbstractStudyDisease());
        }

        dest.setCodedPrimaryDiseaseSite(src.getCodedPrimaryDiseaseSite());
        dest.setDiagnosisDate(src.getDiagnosisDate());
        dest.setOtherPrimaryDisease(src.getOtherPrimaryDisease());
        dest.setOtherPrimaryDiseaseSite(src.getOtherPrimaryDiseaseSite());

        List<MetastaticDiseaseSite> destMetastaticDiseases = dest.getMetastaticDiseaseSites();
        List<MetastaticDiseaseSite> newMetastaticDiseases = new ArrayList<MetastaticDiseaseSite>();
        List<MetastaticDiseaseSite> existingMetastaticDiseases = new ArrayList<MetastaticDiseaseSite>();
        if(dest.getMetastaticDiseaseSites() != null) existingMetastaticDiseases.addAll(dest.getMetastaticDiseaseSites());
        for(MetastaticDiseaseSite mds : src.getMetastaticDiseaseSites()){
            final MetastaticDiseaseSite mdSrc = mds;
            MetastaticDiseaseSite found = CollectionUtils.find(destMetastaticDiseases, new Predicate<MetastaticDiseaseSite>() {
                public boolean evaluate(MetastaticDiseaseSite metastaticDiseaseSite) {
                    return metastaticDiseaseSite.equals(mdSrc);
                }
            });

            if(found != null){
                //remove from existing,as there is nothing to be synced
               existingMetastaticDiseases.remove(found);
            } else {
                newMetastaticDiseases.add(mds);
            }
        }

        //remove the unwanted ones
        for(MetastaticDiseaseSite md : existingMetastaticDiseases){
            dest.getMetastaticDiseaseSites().remove(md);
        }
        //add the newly added ones
        for(MetastaticDiseaseSite md : newMetastaticDiseases){
            dest.addMetastaticDiseaseSite(md);
        }
    }
}
