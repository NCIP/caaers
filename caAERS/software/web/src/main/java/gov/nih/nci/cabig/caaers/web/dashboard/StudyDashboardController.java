package gov.nih.nci.cabig.caaers.web.dashboard;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Nikhil Pingili
 */
public class StudyDashboardController extends DashboardController {

    StudyRepository studyRepository;

    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Study> studyList = studyRepository.getAllStudies();
        ModelAndView mv = new ModelAndView("dashboard/dashboard_Study");
        mv.addObject("studyList", studyList);
        return mv;
    }

    public StudyRepository getStudyRepository() {
        return studyRepository;
    }

    public void setStudyRepository(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }
}