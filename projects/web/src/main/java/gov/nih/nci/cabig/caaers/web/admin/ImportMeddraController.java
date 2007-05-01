package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.domain.Participant;

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
	//private ParticipantDao participantDao;
	
	public ImportMeddraController() {
		setViewName("admin/meddra_import");
	}

	public ModelAndView handleRequestInternal(
		        HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		 
		 // TODO: according to type go to a different view
		 //String type = request.getParameter("type");
		 setViewName("admin/meddra_import");
		 //Participant participant =participantDao.getById(Integer.parseInt(request.getParameter("participantId")));
		 ModelAndView mav = new ModelAndView("admin/meddra_import");
		
		 return mav;        
		    }
}
