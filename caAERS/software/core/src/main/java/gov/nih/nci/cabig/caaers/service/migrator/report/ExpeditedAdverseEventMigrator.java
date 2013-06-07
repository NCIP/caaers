package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class ExpeditedAdverseEventMigrator implements Migrator<ExpeditedAdverseEventReport> {
    
    public void migrate(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

        AdverseEventReportingPeriod reportingPeriod = dest.getReportingPeriod();

        // Associate the updated information to adverse events.
        for ( AdverseEvent aeSrc : src.getAdverseEvents() ) {
            AdverseEvent aeDest = reportingPeriod.findAdverseEventByIdTermAndDates(aeSrc);   //TODO: BJ - May be we must pick unreported AEs here (see the big problem comment below)
            if(aeDest == null ){
                outcome.addError("WS_AEMS_079", "Could not find the AE linked to Safety report", aeSrc.getAdverseEventTerm()!=null? aeSrc.getAdverseEventTerm().getFullName() : "",
                        String.valueOf(aeSrc.getStartDate()), String.valueOf(aeSrc.getEndDate()), String.valueOf(aeSrc.getExternalId()));
                return;
            }


            aeDest.setStartDate(aeSrc.getStartDate());
            aeDest.setEndDate(aeSrc.getEndDate());

            //TODO: BJ - we need another transient holder of AE
            dest .addAdverseEvent(aeDest);
        }
    }
}
