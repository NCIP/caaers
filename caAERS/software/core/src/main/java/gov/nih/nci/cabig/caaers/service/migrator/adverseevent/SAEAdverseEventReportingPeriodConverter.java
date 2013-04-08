/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.adverseevent.CourseType;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.AdverseEventResult;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.SaveAndEvaluateAEsInputMessage;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.Criteria;

import java.util.ArrayList;
import java.util.Map;

/**
 * User: Vijendhar Meda
 * Date: 3/27/13
 */
public class SAEAdverseEventReportingPeriodConverter {

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
    public AdverseEventReportingPeriod convert(SaveAndEvaluateAEsInputMessage aeiMsg,Map<AdverseEvent, AdverseEventResult> mapAE2DTO){
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

              // Create a Map to convert given ae to result object.
              AdverseEventResult result = new AdverseEventResult();
              result.setAdverseEvent(aeType);
              result.setRequiresReporting(false);
              mapAE2DTO.put(ae, result);

              rp.addAdverseEvent(ae);
          }
        }

        return rp;
    }

}
