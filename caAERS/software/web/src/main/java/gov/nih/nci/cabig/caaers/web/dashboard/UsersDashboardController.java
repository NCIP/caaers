package gov.nih.nci.cabig.caaers.web.dashboard;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ion C. Olaru
 * 
 */
public class UsersDashboardController extends DashboardController {

    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("dashboard/dashboard_Users");
        return mv;
    }

}