package gov.nih.nci.cabig.caaers.web.dashboard;

import org.springframework.web.servlet.ModelAndView;
import gov.nih.nci.cabig.caaers.domain.repository.StudyParticipantAssignmentRepository;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Ion C. Olaru
 * 
 */
public class SubjectDashboardController extends DashboardController {

     StudyParticipantAssignmentRepository studyParticipantAssignmentRepository;

    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("dashboard/dashboard_Subject");
        List<StudyParticipantAssignment> subjectList = studyParticipantAssignmentRepository.getAllAssignments();
        mv.addObject("subjectList", subjectList);
        return mv;
    }

    public StudyParticipantAssignmentRepository getStudyParticipantAssignmentRepository() {
        return studyParticipantAssignmentRepository;
    }

    public void setStudyParticipantAssignmentRepository(StudyParticipantAssignmentRepository studyParticipantAssignmentRepository) {
        this.studyParticipantAssignmentRepository = studyParticipantAssignmentRepository;
    }
}