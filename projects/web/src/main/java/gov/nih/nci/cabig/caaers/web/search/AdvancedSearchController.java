package gov.nih.nci.cabig.caaers.web.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gov.nih.nci.cabig.caaers.web.ae.AbstractAdverseEventInputController;
import gov.nih.nci.cabig.caaers.web.ae.ListAdverseEventsCommand;
import gov.nih.nci.cabig.caaers.web.rule.author.CreateRuleCommand;
//import gov.nih.nci.cabig.caaers.web.search.ui.CriteriaObject;
//import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.study.SearchStudyCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class AdvancedSearchController extends SimpleFormController{
	
	private static final Log log = LogFactory.getLog(AdvancedSearchController.class);
	public static final String AJAX_SUBVIEW_PARAMETER = "subview";
	public static final String AJAX_ACTION_PARAMETER = "ajax_action";
	
	public AdvancedSearchController() {
        setCommandClass(AdvancedSearchCommand.class);
        setSessionForm(true);
        setFormView("search/advancedSearch");
        setSuccessView("search/advancedSearchResults");
    }
	
	@Override
    protected Object formBackingObject(HttpServletRequest request) {
		AdvancedSearchCommand command = new AdvancedSearchCommand();
		request.getSession(true).setAttribute(AdvancedSearchController.class.getName() + ".FORM.command", command);
        return new AdvancedSearchCommand();
    }
	
	@Override
    @SuppressWarnings("unchecked")
    protected boolean isFormSubmission(HttpServletRequest request) {
		String finishAttribute = (String) findInRequest(request, "_finish");
		if(finishAttribute != null)
			return true;
		else
			return false;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors) throws Exception{
		ModelAndView mv = super.showForm(request, response, errors);
		
		String ajaxAction = (String) findInRequest(request, AJAX_ACTION_PARAMETER);
		if(ajaxAction != null){
			String ajaxSubview = (String)findInRequest(request, AJAX_SUBVIEW_PARAMETER);
			mv.setViewName("search/ajax/" + ajaxSubview);
		}
		return mv;
	}
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		// Form submission or new form to show?
		if (isFormSubmission(request)) {
			return super.handleRequestInternal(request, response);
		}else{
			String ajaxAction = (String) findInRequest(request, AJAX_ACTION_PARAMETER);
			if(ajaxAction == null)
				return super.handleRequestInternal(request, response);
			else{
				Object command = getCommand(request);
				ServletRequestDataBinder binder = bindAndValidate(request, command);
				BindException errors = new BindException(binder.getBindingResult());
				return showForm(request, response, errors);
			}
		}
	}
	
	@Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request, Object cmd, Errors errors) throws Exception {
		AdvancedSearchCommand command = (AdvancedSearchCommand) cmd;
        Map<String, Object> refdata = new HashMap<String, Object>();
        
        return refdata;
	}
	
	/**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final ServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }
}