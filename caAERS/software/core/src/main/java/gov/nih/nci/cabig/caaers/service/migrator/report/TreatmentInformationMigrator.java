/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.report;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;
import org.apache.axis.utils.StringUtils;

/**
 * User: Biju Joseph
 * Date: 1/8/13
 */
public class TreatmentInformationMigrator implements Migrator<ExpeditedAdverseEventReport> {
    public void migrate(ExpeditedAdverseEventReport aeReportSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
        TreatmentInformation src = aeReportSrc.getTreatmentInformation();
        TreatmentInformation dest = aeReportDest.getTreatmentInformation();

        // Setting the Course Information from the reporting period.
        CourseDate adverseEventCourseDate = new CourseDate();
        adverseEventCourseDate.setDate(dest.getReport().getReportingPeriod().getStartDate());
        adverseEventCourseDate.setNumber(dest.getReport().getReportingPeriod().getCycleNumber());
        dest.setAdverseEventCourse(adverseEventCourseDate);

        dest.setFirstCourseDate(aeReportDest.getAssignment().getStartDateOfFirstCourse());
        dest.setTotalCourses(src.getTotalCourses());
        dest.setTreatmentAssignmentDescription(src.getTreatmentAssignmentDescription());
        dest.setInvestigationalAgentAdministered(src.getInvestigationalAgentAdministered());

        AdverseEventReportingPeriod reportingPeriod = aeReportDest.getReportingPeriod();
        if(src.getTreatmentAssignment() != null && !StringUtils.isEmpty(src.getTreatmentAssignment().getCode())){
            //find the tac from study
            TreatmentAssignment treatmentAssignment = reportingPeriod.getStudy().findActiveTreatmentAssignment(src.getTreatmentAssignment().getCode());
            if(treatmentAssignment == null){
                outcome.addError("WS_SAE_002", "Treatment assignment is no longer active " + src.getTreatmentAssignment().getCode(), src.getTreatmentAssignment().getCode());
                return;
            }
            dest.setTreatmentAssignment(treatmentAssignment);
        } else {
            //default it to what it is there in reporting period
            dest.setTreatmentAssignment(reportingPeriod.getTreatmentAssignment());
            dest.setTreatmentDescription(reportingPeriod.getTreatmentAssignmentDescription());
        }

        //migrate course agents
        for(CourseAgent caSrc : src.getCourseAgents()){

            migrateCourseAgent(caSrc, aeReportDest, outcome);
        }


    }

    private void migrateCourseAgent(CourseAgent caSrc, ExpeditedAdverseEventReport aeReportDest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome){

        Study study = aeReportDest.getStudy();


        StudyAgent studyAgent = caSrc.getStudyAgent();
        if(studyAgent == null || ( (studyAgent.getAgent() == null || StringUtils.isEmpty(studyAgent.getAgent().getNscNumber() ) ) && StringUtils.isEmpty(studyAgent.getOtherAgent() ) ) )  {
            outcome.addWarning("ER-CA-1", "Study Agent is missing in the source");
            return;
        }

        StudyAgent realStudyAgent = studyAgent.getAgent() == null ? study.findStudyAgentByNscOrName(studyAgent.getOtherAgent()) : study.findStudyAgentByNscOrName(studyAgent.getAgent().getNscNumber());
        if(realStudyAgent == null){
            outcome.addWarning("ER-CA-2", "Given Agent is no longer associated to the study");
            return;
        }

        CourseAgent caDest = new CourseAgent();
        aeReportDest.getTreatmentInformation().addCourseAgent(caDest);

        // set the study agent
        caDest.setStudyAgent(realStudyAgent);

        caDest.setDose(caSrc.getDose());
        caDest.setAdministrationDelay(caSrc.getAdministrationDelay());
        caDest.setAdministrationDelayAmount(caSrc.getAdministrationDelayAmount());
        caDest.setAdministrationDelayUnits(caSrc.getAdministrationDelayUnits());
        caDest.setComments(caSrc.getComments());
        caDest.setAgentAdjustment(caSrc.getAgentAdjustment());
        caDest.setModifiedDose(caSrc.getModifiedDose());
        caDest.setLastAdministeredDate(caSrc.getLastAdministeredDate());
        caDest.setDurationAndSchedule(caSrc.getDurationAndSchedule());
        caDest.setFirstAdministeredDate(caSrc.getFirstAdministeredDate());
        caDest.setTotalDoseAdministeredThisCourse(caSrc.getTotalDoseAdministeredThisCourse());
        caDest.setFormulation(caSrc.getFormulation());
        caDest.setLotNumber(caSrc.getLotNumber());

    }
}
