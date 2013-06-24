package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.CompositeMigrator;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ExpeditedAdverseEventReportSynchronizer extends CompositeMigrator<ExpeditedAdverseEventReport> {
    public void preMigrate(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

        if(src.getInvestigationalDeviceAdministered() != null) dest.setInvestigationalDeviceAdministered(src.getInvestigationalDeviceAdministered());
        //set the created date is not present and is available in the source
        if(dest.getCreatedAt() == null && src.getCreatedAt() != null) dest.setCreatedAt(src.getCreatedAt());

    }
}
