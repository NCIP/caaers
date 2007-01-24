package gov.nih.nci.cabig.caaers.web.participant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import gov.nih.nci.cabig.caaers.web.ListValues;

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

		refdata.put("studySearchType", listValues.getStudySearchType());
		refdata.put("participantSearchType", listValues.getParticipantSearchType());
		return refdata;
	}
	
	public AssignController() {
		setCommandClass(AssignParticipantStudyCommand.class);
		setFormView("par/reg_assign");
		setSuccessView("reg_protocol_search");
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object oCommand, BindException errors)
			throws Exception {

		return null;
	}
}
