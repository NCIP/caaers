package gov.nih.nci.cabig.caaers.web.admin;

//Spring imports
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.ModelAndView;

//java servlet imports
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewInvestigatorController extends ParameterizableViewController {
	
	public ViewInvestigatorController() {
		setViewName("admin/investigator_confirmation");
	}	

	public ModelAndView handleRequestInternal(
		        HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		 
		 setViewName("admin/investigator_confirmation");
		 ModelAndView mav = new ModelAndView("admin/investigator_confirmation");
		
		 return mav;        
	}
}
