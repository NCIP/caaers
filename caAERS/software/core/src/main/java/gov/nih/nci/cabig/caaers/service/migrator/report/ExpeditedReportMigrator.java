package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.CompositeMigrator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * User: Biju Joseph
 * Date: 1/8/13
 */
public class ExpeditedReportMigrator extends CompositeMigrator<ExpeditedAdverseEventReport> {
    private static Log logger = LogFactory.getLog(ExpeditedReportMigrator.class);
    private OrganizationDao organizationDao;
    private StudyDao studyDao;
    private ParticipantDao participantDao;

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }


    @Override
    public void preMigrate(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {

        if(src.getInvestigationalDeviceAdministered() != null) dest.setInvestigationalDeviceAdministered(src.getInvestigationalDeviceAdministered());
        //set the created date is not present and is available in the source
        if(dest.getCreatedAt() == null && src.getCreatedAt() != null) dest.setCreatedAt(src.getCreatedAt());

        //identify the reporting period, participant and study to use.
       
        AdverseEventReportingPeriod  rpSrc = src.getReportingPeriod();
        if(rpSrc == null){
            outcome.addError("ER-RP-1", "Missing Reporting period and Adverse event in input message");
            return;
        }
        StudySite studySiteSrc = rpSrc.getStudySite();
        if(studySiteSrc == null){
            outcome.addError("ER-STU-1", "StudySite information is missing in input message");
            return;
        }
        Study studySrc = rpSrc.getStudy() ;
        if(studySrc == null){
            outcome.addError("ER-STU-2", "Study information is missing in input message");
            return;
        }

        if(studySiteSrc.getOrganization() == null || studySiteSrc.getOrganization().getNciInstituteCode() == null){
            outcome.addError("ER-STU-3", "Missing Study Site details - Organization NCI code");
            return;
        }

        Participant subjectSrc = rpSrc.getParticipant();
        if(subjectSrc == null){
            outcome.addError("ER-SUB-1", "Subject information is missing in input message");
            return;
        }

        StudyParticipantAssignment assignmentSrc = subjectSrc.getAssignments().get(0);
        ParticipantQuery pq = new ParticipantQuery();
        pq.filterByStudySubjectIdentifier(assignmentSrc.getStudySubjectIdentifier());
        pq.filterByStudySiteNciCode(studySiteSrc.getOrganization().getNciInstituteCode());

        List<Participant> dbParticipants = participantDao.searchParticipant(pq);
        if(dbParticipants == null || dbParticipants.isEmpty()){
           outcome.addError("ER-SUB-2", "Subject is not present in caAERS database");
           return;
        }
        StudyParticipantAssignment assignment = dbParticipants.get(0).findAssignemtByStudySubjectIdentifier(assignmentSrc.getStudySubjectIdentifier());
        if(assignment == null){
            outcome.addError("ER-SUB-3", "Subject is not assigned to Study :" + studySrc.getFundingSponsorIdentifierValue() + " at site " + studySiteSrc.getOrganization().getNciInstituteCode());
            return;
        }
        String epochName = rpSrc.getEpoch() != null ? rpSrc.getEpoch().getName() : null;
        String tac = rpSrc.getTreatmentAssignment() != null ? rpSrc.getTreatmentAssignment().getCode() : rpSrc.getTreatmentAssignmentDescription();
        AdverseEventReportingPeriod rpFound = assignment.findReportingPeriod(rpSrc.getExternalId(), rpSrc.getStartDate(), rpSrc.getEndDate(), rpSrc.getCycleNumber(), epochName,tac);

        if(rpFound == null){
            outcome.addError("ER-RP-1", "Reporting period not found", studySrc.getFundingSponsorIdentifierValue(),
                    studySiteSrc.getOrganization().getNciInstituteCode(),
                    assignmentSrc.getStudySubjectIdentifier());
            return;
        }
        dest.setReportingPeriod(rpFound);
    }

}
