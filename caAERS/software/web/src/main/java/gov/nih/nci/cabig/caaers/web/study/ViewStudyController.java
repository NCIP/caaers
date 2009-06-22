package gov.nih.nci.cabig.caaers.web.study;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

public class ViewStudyController extends ParameterizableViewController {

    public ViewStudyController() {
        setViewName("study/study_confirmation");
    }

    public ModelAndView handleRequestInternal(HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

        setViewName("study/study_confirmation");
        ModelAndView mav = new ModelAndView("study/study_confirmation");

        return mav;
    }
}
