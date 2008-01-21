package gov.nih.nci.cabig.caaers.web.participant;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import gov.nih.nci.cabig.caaers.web.ListValues;

/**
 * @author Krikor Krumlian
 */
public class AssignController extends SimpleFormController {
	private static Log log = LogFactory.getLog(AssignController.class);
	private ListValues listValues;
	
	public ListValues getListValues() {
		return listValues;
	}
	
	public void setListValues(ListValues listValues) {
		this.listValues = listValues;
	}
	
	protected Map<String, Object> referenceData(
			HttpServletRequest httpServletRequest) throws Exception {
		Map<String, Object> refdata = new HashMap<String, Object>();
		
		// The reason We are setting the following attributes in session is because AssignController submits to 
		// either /assignParticipant or /assignStudy which is a wizard. When a GET is recieved to a wizard the following 
		//attributes are put in sesssion. So since we do a POST to the wizard from the start we create these here.
		httpServletRequest.getSession().setAttribute(AssignStudyController.class.getName()+".PAGE."+ getCommandName(), 0);
		httpServletRequest.getSession().setAttribute(AssignParticipantController.class.getName()+".PAGE."+ getCommandName(), 0);
		refdata.put("studySearchType", listValues.getStudySearchType());
		refdata.put("participantSearchType", listValues.getParticipantSearchType());
		return refdata;
	}
	
	public AssignController() {
		setCommandClass(AssignParticipantStudyCommand.class);
		setFormView("par/reg_assign");
		setSuccessView("reg_protocol_search");
	}

	protected ModelAndView onSubmit(HttpServletRequest request,	HttpServletResponse response, Object oCommand, BindException errors)
			throws Exception {
		return null;
	}
}
