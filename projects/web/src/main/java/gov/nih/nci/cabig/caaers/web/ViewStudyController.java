package gov.nih.nci.cabig.caaers.web;


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

public class ViewStudyController extends ParameterizableViewController {
	
	public ViewStudyController() {
		setViewName("study/study_confirmation");
	}	

	public ModelAndView handleRequestInternal(
		        HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		 
		 setViewName("study/study_confirmation");
		 ModelAndView mav = new ModelAndView("study/study_confirmation");
		
		 return mav;        
		    }
}
