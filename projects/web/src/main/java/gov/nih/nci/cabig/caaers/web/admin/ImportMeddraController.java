package gov.nih.nci.cabig.caaers.web.admin;

// import Apache commons
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//Spring imports
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.ModelAndView;

//java servlet imports
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImportMeddraController extends ParameterizableViewController {

	private static Log log = LogFactory.getLog(ImportMeddraController.class);
	
	public ImportMeddraController() {
		setViewName("admin/meddra_import");
	}

	public ModelAndView handleRequestInternal(
		        HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		 
		 setViewName("admin/meddra_import");
		 ModelAndView mav = new ModelAndView("admin/meddra_import");
		 log.debug("modelAndView" + mav.getViewName());
		
		 return mav;        
		    }
}
