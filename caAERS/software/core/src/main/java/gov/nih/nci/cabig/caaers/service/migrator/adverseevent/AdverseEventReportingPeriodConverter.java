package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.manageae.AdverseEventsInputMessage;
import gov.nih.nci.cabig.caaers.integration.schema.manageae.CourseType;
import gov.nih.nci.cabig.caaers.integration.schema.manageae.Criteria;

import java.util.ArrayList;

/**
 * User: Biju Joseph
 * Date: 1/28/13
 */
public class AdverseEventReportingPeriodConverter {

    private AdverseEventConverter aeConverter;

    public AdverseEventConverter getAeConverter() {
        return aeConverter;
    }

    public void setAeConverter(AdverseEventConverter aeConverter) {
        this.aeConverter = aeConverter;
    }

    /**
     * Will convert to domain object from AdverseEventInputMessage
     * @param aeiMsg
     * @return
     */
    public AdverseEventReportingPeriod convert(AdverseEventsInputMessage aeiMsg){
        Criteria criteria = aeiMsg.getCriteria();
        AdverseEventReportingPeriod rp = new AdverseEventReportingPeriod();
        Study study = new LocalStudy();
        StudySite studySite = new StudySite();
        study.addStudySite(studySite);

        Participant participant = new Participant();
        StudyParticipantAssignment assignment = new StudyParticipantAssignment();
        assignment.setReportingPeriods(new ArrayList<AdverseEventReportingPeriod>());
        participant.addAssignment(assignment);
        rp.setAssignment(assignment);
        assignment.getReportingPeriods().add(rp);
        assignment.setStudySite(studySite);

        //set SSI
        assignment.setStudySubjectIdentifier(criteria.getStudySubjectIdentifier());

        //set Study Protocol Identifier
        Identifier studyIdentifier = new OrganizationAssignedIdentifier();
        studyIdentifier.setType(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE);
        studyIdentifier.setValue(criteria.getStudyIdentifier());
        study.addIdentifier(studyIdentifier);

        //set the course details
        CourseType course = criteria.getCourse();
        if(course.getCycleNumber() != null ) rp.setCycleNumber(course.getCycleNumber().intValue());
        if(course.getExternalId() !=null ) rp.setExternalId(course.getExternalId());
        if(course.getTreatmentType() !=null ) {
            rp.setEpoch(new Epoch());
            rp.getEpoch().setName(course.getTreatmentType());
        }
        if(course.getTreatmentAssignmentCode() !=null ) {
            rp.setTreatmentAssignment(new TreatmentAssignment());
            rp.getTreatmentAssignment().setStudy(study);
            rp.getTreatmentAssignment().setCode(course.getTreatmentAssignmentCode());
        }
        if(course.getOtherTreatmentAssignmentDescription() !=null ) {
            rp.setTreatmentAssignmentDescription(course.getOtherTreatmentAssignmentDescription());
        }

        if(course.getStartDateOfFirstCourse() != null){
            rp.getAssignment().setStartDateOfFirstCourse(course.getStartDateOfFirstCourse().toGregorianCalendar().getTime());
        }

        if(course.getStartDateOfThisCourse() != null){
            rp.setStartDate(course.getStartDateOfThisCourse().toGregorianCalendar().getTime());
        }

        if(course.getEndDateOfThisCourse() != null){
            rp.setEndDate(course.getEndDateOfThisCourse().toGregorianCalendar().getTime());
        }

        if(aeiMsg.getAdverseEvents() != null && aeiMsg.getAdverseEvents().getAdverseEvent() != null){
          for(AdverseEventType aeType : aeiMsg.getAdverseEvents().getAdverseEvent()) {
              AdverseEvent ae = aeConverter.convert(aeType);
              rp.addAdverseEvent(ae);
          }
        }

        return rp;
    }

}
