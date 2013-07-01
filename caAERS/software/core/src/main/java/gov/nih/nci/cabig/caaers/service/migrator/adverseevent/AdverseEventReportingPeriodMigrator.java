/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.ParticipantQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.CompositeMigrator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * User: Biju Joseph
 * Date: 1/29/13
 */
public class AdverseEventReportingPeriodMigrator extends CompositeMigrator<AdverseEventReportingPeriod> {
    private static Log logger = LogFactory.getLog(AdverseEventReportingPeriodMigrator.class);

    private StudyDao studyDao;
    private ParticipantDao participantDao;

    public StudyDao getStudyDao() {
        return studyDao;
    }

    public void setStudyDao(StudyDao studyDao) {
        this.studyDao = studyDao;
    }

    public ParticipantDao getParticipantDao() {
        return participantDao;
    }

    public void setParticipantDao(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public void preMigrate(AdverseEventReportingPeriod src, AdverseEventReportingPeriod dest, DomainObjectImportOutcome<AdverseEventReportingPeriod> outcome) {
       if(CollectionUtils.isEmpty(src.getAdverseEvents())) {
           logger.error("Missing adverse events in the input");
           outcome.addError("WS_AEMS_025", "Missing adverse events data");
           return;
       }

        // Check for Treatment Assignment Codes.
        if ( !((src.getTreatmentAssignment().getCode().equalsIgnoreCase("Other") && StringUtils.isNotBlank(src.getTreatmentAssignmentDescription()) && !src.getTreatmentAssignmentDescription().equalsIgnoreCase("N/A") ) ||
                (src.getTreatmentAssignmentDescription().equalsIgnoreCase("N/A") && StringUtils.isNotBlank(src.getTreatmentAssignment().getCode())) && !src.getTreatmentAssignment().getCode().equalsIgnoreCase("Other"))
            ) {
            outcome.addError("WS_AEMS_083", "Treatment Assignment code and other treatment assignment description doesn't contain valid values.");
            return;
        }  else {
               if ( ( src.getTreatmentAssignment() != null && src.getTreatmentAssignment().getCode() != null && src.getTreatmentAssignment().getCode().equalsIgnoreCase("Other") )) {
                    src.setTreatmentAssignment(null);
               }
               if ( (src.getTreatmentAssignmentDescription() != null && src.getTreatmentAssignmentDescription().equalsIgnoreCase("N/A") )) {
                    src.setTreatmentAssignmentDescription(null);
               }
        }

        // The first course of the Study cannot be greater than start date of the course associated with Expedited report.

        if  ( src.getAssignment() != null && src.getAssignment().getStartDateOfFirstCourse() != null && src.getStartDate() != null &&
                src.getAssignment().getStartDateOfFirstCourse().after(src.getStartDate()) ){
            outcome.addError("WS_AEMS_084", "The first course of the Study cannot be greater than start date of the course associated with Expedited report.");
            return;
        }

       //fetch the study
       Study studySrc = src.getStudy();
       if(studySrc == null || studySrc.getFundingSponsorIdentifierValue() == null){
           logger.error("Missing study identifier");
           outcome.addError("WS_AEMS_034",  "Missing Study Identifier" );
           return;
       }
       if(studySrc.getFundingSponsorIdentifierValue() == null){
           logger.error("Missing study identifier");
           outcome.addError("WS_AEMS_034",  "Missing Study Identifier",
                   new String[]{studySrc.getFundingSponsorIdentifierValue()});
           return;
       }

       Study study = studyDao.getByIdentifier(studySrc.getFundingSponsorIdentifier());
       if(study == null){
           logger.error("Study not present in caAERS with the sponsor identifier : " + studySrc.getFundingSponsorIdentifierValue());
           outcome.addError("WS_AEMS_003", "Study with sponsor identifier " + studySrc.getFundingSponsorIdentifierValue() +" does not exist in caAERS",
                   new String[]{studySrc.getFundingSponsorIdentifierValue()});
           return;
       }

       if(study.getDataEntryStatus() == null || !study.getDataEntryStatus()){
           logger.error("Incomplete Study");
           outcome.addError("WS_AEMS_076", "Study is not data entry complete", new String[]{study.getFundingSponsorIdentifierValue()});
           return;
       }

       if(study.getAeTerminology() == null && (study.getAeTerminology().getCtcVersion() == null || study.getAeTerminology().getMeddraVersion() == null)){
          logger.error("Invalid AE terminology in study  " + study.getAeTerminology());
          outcome.addError("WS_AEMS_077", "Invalid AE terminology in study", new String[]{studySrc.getFundingSponsorIdentifierValue()});
          return;
       }

       //fetch the assignment
       StudyParticipantAssignment srcAssignment = src.getAssignment();
       if(srcAssignment == null|| srcAssignment.getStudySubjectIdentifier() == null){
           logger.error("Missing assignment or study subject identifier");
           outcome.addError("WS_AEMS_032", "Missing study subject identifier in the input");
           return;
       }

        ParticipantQuery pQuery = new ParticipantQuery();
        pQuery.joinStudy();
        pQuery.filterByStudyId(study.getId());
        pQuery.filterByStudySubjectIdentifier(srcAssignment.getStudySubjectIdentifier());
        List<Participant> participants = (List<Participant>) participantDao.search(pQuery);
        if(CollectionUtils.isEmpty(participants)){
            logger.error("Could not find the participant with SSI " + srcAssignment.getStudySubjectIdentifier() + ", on study ID " + study.getId() + ", with Study Sponsor Identifier : " + studySrc.getFundingSponsorIdentifierValue());
            outcome.addError("WS_AEMS_033", "Study subject not found with SSI " + srcAssignment.getStudySubjectIdentifier(), new String[]{srcAssignment.getStudySubjectIdentifier()});
            return;
        }

        Participant participant = participants.get(0);
        StudyParticipantAssignment assignment = participant.findAssignemtByStudySubjectIdentifier(srcAssignment.getStudySubjectIdentifier());
        if(assignment == null){
            logger.error("Cannot find matching assignment in the participant loaded from DB (participant Id : " + participant.getId() + ")");
            outcome.addError("WS_PMS_005", "Unable convert or correlate participant correctly", new String[]{srcAssignment.getStudySubjectIdentifier()});
            return;
        }

        //migrate external Id
        dest.setExternalId(src.getExternalId());
        //grid-id
        dest.setGridId(src.getGridId());
        //cycle
        dest.setCycleNumber(src.getCycleNumber());

        //dates
        dest.setStartDate(src.getStartDate());
        dest.setEndDate(src.getEndDate());
        dest.setDescription(src.getDescription());


        //migrate TAC
        dest.setTreatmentAssignmentDescription(src.getTreatmentAssignmentDescription());
        if(src.getTreatmentAssignment() != null){
            TreatmentAssignment ta = study.findActiveTreatmentAssignment(src.getTreatmentAssignment().getCode());
            if(ta == null){
                logger.error("Could not identify a treatment assignment with TAC : " + src.getTreatmentAssignment().getCode() +", in the study : " + study.getId());
                outcome.addError("WS_AEMS_009", "Treatment assignment code is not valid", new String[]{src.getTreatmentAssignment().getCode()});
                return;
            }
            dest.setTreatmentAssignment(ta);
        }

        //migrate Epoch
        if(src.getEpoch() != null){
            Epoch epoch = study.findActiveEpoch(src.getEpoch().getName());
            if(epoch == null){
                logger.error("Could not find the Treatment Type or Epoch with the name : " + src.getEpoch().getName());
                outcome.addError("WS_AEMS_010", "Treatment type is not valid", new String[]{src.getEpoch().getName()});
                return;
            }
            dest.setEpoch(epoch);
        }

        //assignment
        dest.setAssignment(assignment);

        //migrate the first course details.
        dest.getAssignment().setStartDateOfFirstCourse(src.getAssignment().getStartDateOfFirstCourse());


    }
}
