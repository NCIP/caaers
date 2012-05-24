package gov.nih.nci.cabig.caaers.web.study;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ion C. Olaru
 *         Date: 5/23/12 -6:24 PM
 */
public class IntermediateStudyController extends StudyController<StudyCommand> {

    @Override
    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        return null;
    }

}
