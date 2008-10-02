package gov.nih.nci.cabig.caaers.tools.spring.tabbedflow;

import org.apache.commons.lang.StringUtils;

import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.dao.MutableDomainObjectDao;
import gov.nih.nci.cabig.ctms.domain.MutableDomainObject;
import gov.nih.nci.cabig.ctms.web.tabs.AutomaticSaveFlowFormController;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kruttik
 * 
 */
public abstract class AutomaticSaveAjaxableFormController<C, D extends MutableDomainObject, A extends MutableDomainObjectDao<D>>
                extends AutomaticSaveFlowFormController<C, D, A> {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                    HttpServletResponse response) throws Exception {
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
        
        if(!errors.hasErrors() && !refdata.containsKey("flashMessage")){
        	if(page == WebUtils.getPreviousPage(request)) refdata.put("flashMessage", "Information saved successfully"); 
        }
        
        return refdata;
    }

    @Override
    protected void postProcessPage(HttpServletRequest request, Object command, Errors errors,
                    int page) throws Exception {
        if (isAjaxRequest(request)) {
            AjaxableTab<C> ajaxTab = (AjaxableTab<C>) getFlow((C) command).getTab(page);
            ModelAndView modelAndView = ajaxTab.postProcessAsynchronous(request, (C) command,
                            errors);
            setAjaxModelAndView(request, modelAndView);
            if (!errors.hasErrors() && shouldSave(request, (C) command, getTab((C) command, page))) {
                C newCommand = save((C) command, errors);
                if (newCommand != null) {
                    request.getSession().setAttribute(
                                    getReplacedCommandSessionAttributeName(request), newCommand);
                }
            }
        }
        else {
            super.postProcessPage(request, command, errors, page);
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
}
