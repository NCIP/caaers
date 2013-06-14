package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;
import gov.nih.nci.cabig.caaers.domain.*;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ReporterSynchronizer implements Migrator<ExpeditedAdverseEventReport> {

    public void migrate(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
        Reporter xmlReporter = src.getReporter();
        Reporter dbReporter = dest.getReporter();
        if(dbReporter == null || xmlReporter == null){
            dest.setReporter(xmlReporter);
            return;
        }

        Investigator xmlInv = xmlReporter.getInvestigator();
        Investigator dbInv = dbReporter.getInvestigator();
        if(dbInv == null || xmlInv == null || !dbInv.getId().equals(xmlInv.getId())){
            dbReporter.setInvestigator(xmlInv);
        }

        ResearchStaff xmlRs = xmlReporter.getResearchStaff();
        ResearchStaff dbRs = dbReporter.getResearchStaff();
        if(dbRs == null || xmlRs == null || !dbRs.getId().equals(xmlRs.getId())){
            dbReporter.setResearchStaff(xmlRs);
        }

        //copy reporter details.
        dbReporter.copy(xmlReporter);
    }
}
