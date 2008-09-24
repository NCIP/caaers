package gov.nih.nci.cabig.caaers.web.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

public class ViewResearchStaffController extends ParameterizableViewController {

	private String authenticationMode;
	
    public ViewResearchStaffController() {
        setViewName("admin/research_staff_confirmation");
    }

    public ModelAndView handleRequestInternal(HttpServletRequest request,
                    HttpServletResponse response) throws Exception {

        setViewName("admin/research_staff_confirmation");
        ModelAndView mav = new ModelAndView("admin/research_staff_confirmation");
        mav.addObject("authenticationMode", getAuthenticationMode());

        return mav;
    }

	public String getAuthenticationMode() {
		return authenticationMode;
	}

	public void setAuthenticationMode(String authenticationMode) {
		this.authenticationMode = authenticationMode;
	}
}
