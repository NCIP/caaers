package gov.nih.nci.cabig.caaers.service.synchronizer.report;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.migrator.Migrator;
import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Biju Joseph
 * @since 1.5
 */
public class TreatmentInformationSynchronizer implements Migrator<ExpeditedAdverseEventReport> {

    public void migrate(ExpeditedAdverseEventReport src, ExpeditedAdverseEventReport dest, DomainObjectImportOutcome<ExpeditedAdverseEventReport> outcome) {
        TreatmentInformation xmlTi = src.getTreatmentInformation();
        TreatmentInformation dbTi =  dest.getTreatmentInformation();

        dbTi.setTreatmentAssignment(xmlTi.getTreatmentAssignment());
        dbTi.setTreatmentAssignmentDescription(xmlTi.getTreatmentAssignmentDescription());
        dbTi.setFirstCourseDate(xmlTi.getFirstCourseDate());
        dbTi.setTotalCourses(xmlTi.getTotalCourses());
        dbTi.setAdverseEventCourse(xmlTi.getAdverseEventCourse());
        dbTi.setInvestigationalAgentAdministered(xmlTi.getInvestigationalAgentAdministered());

        List<CourseAgent> newlyAddedCourseAgents = new ArrayList<CourseAgent>();
        List<CourseAgent> existingCourseAgents = new ArrayList<CourseAgent>();
        if(dbTi.getCourseAgentsInternal() != null) existingCourseAgents.addAll(dbTi.getCourseAgentsInternal());
        if(xmlTi.getCourseAgentsInternal() != null){
            for(CourseAgent ca : xmlTi.getCourseAgentsInternal()){
                final CourseAgent xmlCourseAgent = ca;
                CourseAgent found = CollectionUtils.find(existingCourseAgents, new Predicate<CourseAgent>(){
                    public boolean evaluate(CourseAgent courseAgent) {
                        return xmlCourseAgent.getStudyAgent().getId().equals(courseAgent.getStudyAgent().getId());
                    }
                });

                if(found != null){
                    //synchronize course agent details
                    synchronize(xmlCourseAgent, found);

                    existingCourseAgents.remove(found);
                } else {
                    newlyAddedCourseAgents.add(xmlCourseAgent);
                }
            }
        }

        //remove unwanted ones
        for(CourseAgent ca : existingCourseAgents) dbTi.removeCourseAgent(ca);
        //add newly added course agent
        for(CourseAgent ca : newlyAddedCourseAgents) dbTi.addCourseAgent(ca);
    }

    public void synchronize(CourseAgent xmlCourseAgent, CourseAgent dbCourseAgent){

        dbCourseAgent.setComments(xmlCourseAgent.getComments());
        dbCourseAgent.setLotNumber(xmlCourseAgent.getLotNumber());
        dbCourseAgent.setFormulation(xmlCourseAgent.getFormulation());
        dbCourseAgent.setDose(xmlCourseAgent.getDose());
        dbCourseAgent.setDurationAndSchedule(xmlCourseAgent.getDurationAndSchedule());
        dbCourseAgent.setAdministrationDelay(xmlCourseAgent.getAdministrationDelay());
        dbCourseAgent.setAdministrationDelayUnits(xmlCourseAgent.getAdministrationDelayUnits());
        dbCourseAgent.setAgentAdjustment(xmlCourseAgent.getAgentAdjustment());
        dbCourseAgent.setModifiedDose(xmlCourseAgent.getModifiedDose());
        dbCourseAgent.setLastAdministeredDate(xmlCourseAgent.getLastAdministeredDate());
        dbCourseAgent.setFirstAdministeredDate(xmlCourseAgent.getFirstAdministeredDate());
        dbCourseAgent.setTotalDoseAdministeredThisCourse(xmlCourseAgent.getTotalDoseAdministeredThisCourse());

    }
}
