package gov.nih.nci.cabig.caaers.tools.spring.tabbedflow;

import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import gov.nih.nci.cabig.ctms.domain.MutableDomainObject;
import gov.nih.nci.cabig.ctms.web.tabs.AutomaticSaveFlowFormController;

import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kruttik
 * 
 */
public abstract class AutomaticSaveAjaxableFormController<C, D extends MutableDomainObject, A extends MutableDomainObjectDao<D>> extends AutomaticSaveFlowFormController<C, D, A> {

	 protected static final Log log = LogFactory.getLog(AutomaticSaveAjaxableFormController.class);
	 
	 protected MessageSource messageSource;
	 
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (isAjaxRequest(request)) {
            synchronized (this) {
                ModelAndView superModelAndView = super.handleRequestInternal(request, response);
                ModelAndView modelAndView = getAjaxModelAndView(request);
                modelAndView.getModel().putAll(superModelAndView.getModel());
                if (isAjaxResponseFreeText(modelAndView)) {
                    respondAjaxFreeText(modelAndView, response);
                    return null;
                }
                return modelAndView;
            }
        }
        return super.handleRequestInternal(request, response);
    }
    
    
    @Override
    protected Map referenceData(HttpServletRequest request, Object oCommand, Errors errors, int page)
                    throws Exception {
        C command = (C) oCommand;
        Map refdata = super.referenceData(request, command, errors, page);
        WorkFlowTab<C> current = (WorkFlowTab<C>) getFlow(command).getTab(page);
        refdata.putAll(current.referenceData(request, (C) command));
        
        populateSaveConfirmationMessage(refdata, request, oCommand, errors, page);
        
        return refdata;
    }
    
    /**
     * This method puts the flash message, when user clicks on save.
     * @param refdata
     * @param request
     * @param oCommand
     * @param errors
     * @param page
     */
    public void populateSaveConfirmationMessage( Map refdata, HttpServletRequest request, Object oCommand, Errors errors, int page){
    	   if(!errors.hasErrors() && !refdata.containsKey("flashMessage")){
           	if(page == WebUtils.getPreviousPage(request)) refdata.put("flashMessage", "Information saved successfully"); 
           }
    }
    
    @Override
    protected void postProcessPage(HttpServletRequest request, Object command, Errors errors, int page) throws Exception {
        try {
        	//if there is an optimistic locking error, make sure, proper error message is populated.
        	Throwable optimisticLockException = (Throwable)request.getAttribute("OPTIMISTIC_LOCKING_ERROR");
        	if(optimisticLockException != null){
        		log.warn("Optimistic Locking error", optimisticLockException);
    			errors.reject("GEN_002", "Cannot continue this operation, as another user is working on the same data.");
        	}
        	
			if (isAjaxRequest(request)) {
			    AjaxableTab<C> ajaxTab = (AjaxableTab<C>) getFlow((C) command).getTab(page);
			    ModelAndView modelAndView = ajaxTab.postProcessAsynchronous(request, (C) command,errors);
			    setAjaxModelAndView(request, modelAndView);
			    if (!errors.hasErrors() && shouldSave(request, (C) command, getTab((C) command, page))) {
			        C newCommand = save((C) command, errors);
			        if (newCommand != null) {
			            request.getSession().setAttribute(getReplacedCommandSessionAttributeName(request), newCommand);
			        }
			    }
			}
			else {
			    super.postProcessPage(request, command, errors, page);
			}
		} catch (DataIntegrityViolationException e) {
			log.warn("Data integrity error", e);
			errors.reject("GEN_001", "Cannot continue this operation, as there are other entities referencing this.");
		} catch(org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException e){
			log.warn("Optimistic Locking error", e);
			errors.reject("GEN_002", "Cannot continue this operation, as another user is working on the same data.");
		}
    }

    protected boolean isAjaxRequest(HttpServletRequest request) {
        if (StringUtils.equalsIgnoreCase(request.getParameter(getAjaxRequestParamName()), "true")) return true;
        return false;
    }

    protected void setAjaxModelAndView(HttpServletRequest request, ModelAndView modelAndView) {
        request.setAttribute(getAjaxModelAndViewAttr(), modelAndView);
    }

    protected ModelAndView getAjaxModelAndView(HttpServletRequest request) {
        return (ModelAndView) request.getAttribute(getAjaxModelAndViewAttr());
    }

    protected boolean isAjaxResponseFreeText(ModelAndView modelAndView) {
    	return StringUtils.isBlank(modelAndView.getViewName());
        /*if (StringUtils.getBlankIfNull(modelAndView.getViewName()).equals("")) {
            return true;
        }
        return false;*/
    }

    protected void respondAjaxFreeText(ModelAndView modelAndView, HttpServletResponse response)
                    throws Exception {
        PrintWriter pr = response.getWriter();
        pr.println(modelAndView.getModel().get(getFreeTextModelName()));
        pr.flush();
    }

    protected String getAjaxRequestParamName() {
        return "_asynchronous";
    }

    protected String getAjaxModelAndViewAttr() {
        return "async_model_and_view";
    }

    protected String getFreeTextModelName() {
        return "free_text";
    }
    
    public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
    public MessageSource getMessageSource() {
		return messageSource;
	}
    
    protected String getMessage(String code, String defaultMsg, Object...objects){
    	return messageSource.getMessage(code, objects, defaultMsg, Locale.getDefault());
    }
}
