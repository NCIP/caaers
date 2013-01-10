package gov.nih.nci.cabig.caaers.service.migrator;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

/**
 * User: Biju Joseph
 * Date: 1/8/13
 */
public class ExpeditedReportMigrator extends CompositeMigrator<ExpeditedAdverseEventReport>{

    @Override
    public void preMigrate(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

        if(src.getInvestigationalDeviceAdministered() != null) dest.setInvestigationalDeviceAdministered(src.getInvestigationalDeviceAdministered());
        //set the created date is not present and is available in the source
        if(dest.getCreatedAt() == null && src.getCreatedAt() != null) dest.setCreatedAt(src.getCreatedAt());

        //identify the reporting period, participant and study to use.
        if(true) return; //DELETE THIS LINE
        AdverseEventReportingPeriod  rpSrc = src.getReportingPeriod();
        Participant subjectSrc = rpSrc.getParticipant();
        if(subjectSrc == null){
            outcome.addError("ER-SUB-1", "Subject information is missing in input message");
        }
        //fetch the participant
        //TODO: determine how to find the subject

        Study studySrc = rpSrc.getStudy() ;
        if(studySrc == null){
            outcome.addError("ER-STU-1", "Study information is missing in input message");
        }
        //fetch the study
        //TODO: determine how to find the study

        //TODO: find the assignment

        //TODO: find the reporting period
    }


}
