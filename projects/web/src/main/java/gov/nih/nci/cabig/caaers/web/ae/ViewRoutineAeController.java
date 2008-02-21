package gov.nih.nci.cabig.caaers.web.ae;


import gov.nih.nci.cabig.caaers.dao.ParticipantDao;
import gov.nih.nci.cabig.caaers.dao.RoutineAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
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

public class ViewRoutineAeController extends ParameterizableViewController {

	private static Log log = LogFactory.getLog(ViewRoutineAeController.class);
	private RoutineAdverseEventReportDao routineAdverseEventReportDao;
	
	public ViewRoutineAeController() {
		setViewName("ae/viewRoutineAe");
	}

	public ModelAndView handleRequestInternal(
		        HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
		 
		 RoutineAdverseEventReport routineAdverseEventReport = routineAdverseEventReportDao.getById(Integer.parseInt(request.getParameter("id")));
		 return new ModelAndView("ae/viewRoutineAe","aeRoutineReport",routineAdverseEventReport);
		
		         
	}

	public RoutineAdverseEventReportDao getRoutineAdverseEventReportDao() {
		return routineAdverseEventReportDao;
	}

	public void setRoutineAdverseEventReportDao(
			RoutineAdverseEventReportDao routineAdverseEventReportDao) {
		this.routineAdverseEventReportDao = routineAdverseEventReportDao;
	}
	
	
	
}
