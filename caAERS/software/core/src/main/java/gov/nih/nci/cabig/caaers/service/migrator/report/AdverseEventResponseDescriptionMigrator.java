package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.AdverseEventResponseDescription;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * User: Biju Joseph
 * Date: 1/8/13
 */
public class AdverseEventResponseDescriptionMigrator implements Migrator<ExpeditedAdverseEventReport> {

    public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
        AdverseEventResponseDescription src = aeReportSrc.getResponseDescription();
        AdverseEventResponseDescription dest = aeReportDest.getResponseDescription();

        dest.setEventDescription(src.getEventDescription());
        dest.setRecoveryDate(src.getRecoveryDate());
        dest.setRetreated(src.getRetreated());
        dest.setDateRemovedFromProtocol(src.getDateRemovedFromProtocol());
        dest.setBlindBroken(src.getBlindBroken());
        dest.setStudyDrugInterrupted(src.getStudyDrugInterrupted());
        dest.setReducedDose(src.getReducedDose());
        dest.setReducedDate(src.getReducedDate());
        dest.setDaysNotGiven(src.getDaysNotGiven());
        dest.setPrimaryTreatment(src.getPrimaryTreatment());
        dest.setCauseOfDeath(src.getCauseOfDeath());
        dest.setAutopsyPerformed(src.getAutopsyPerformed());
        dest.setPrimaryTreatmentApproximateTime(src.getPrimaryTreatmentApproximateTime());
        dest.setEventAbate(src.getEventAbate());
        dest.setEventReappear(src.getEventReappear());
        dest.setPresentStatus(src.getPresentStatus());

    }
}
