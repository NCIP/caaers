package gov.nih.nci.cabig.caaers.web.search;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.web.ae.CaptureAdverseEventAjaxFacade;
import gov.nih.nci.cabig.caaers.web.ae.CaptureAdverseEventController;
import gov.nih.nci.cabig.caaers.web.dwr.AjaxOutput;
import gov.nih.nci.cabig.caaers.web.search.ui.AdvancedSearchUi;
import gov.nih.nci.cabig.caaers.web.search.ui.DependentObject;
import gov.nih.nci.cabig.caaers.web.search.ui.SearchTargetObject;
import gov.nih.nci.cabig.caaers.web.search.ui.UiAttribute;

public class AdvancedSearchAjaxFacade{
	
	private static Class<?>[] CONTROLLERS = { AdvancedSearchController.class   };
	
	private static final Log log = LogFactory.getLog(AdvancedSearchAjaxFacade.class);
	
	private static AdvancedSearchUi advancedSearchUi;
	
	static{
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("advancedSearch-ui.xml");

		Unmarshaller unmarshaller;
		try {
			unmarshaller = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.web.search.ui").createUnmarshaller();
			advancedSearchUi = (AdvancedSearchUi) unmarshaller.unmarshal(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * This method is called when the search target object is modified. Following tasks are done on this update-
	 *  a. The dependent objects are identified and one default criteria is added for each of them 
	 *     to the criteriaParameters list.
	 *  b. The attributes for each of these dependent objects are known through the advancedSearchUi object and hence the dropdown
	 *     for the attributes is populated.
	 *  c. The html is created using criteriaDetailsSection.jsp
	 */
	public AjaxOutput updateSearchTargetObject(String targetObjectClassName){
		AjaxOutput ajaxOutput = new AjaxOutput();
		AdvancedSearchCommand command = (AdvancedSearchCommand) extractCommand();
		
		// This is when the user selects "please select" in the searchTargetObject. The searchtargetObject is set to null in the command.
		if(targetObjectClassName.equals("")){
			command.setSearchTargetObject(null);
			command.setCriteriaParameters(null);
			ajaxOutput.setHtmlContent("");
			return ajaxOutput;
		}
		
		SearchTargetObject targetObject = AdvancedSearchUiUtil.getSearchTargetObjectByName(advancedSearchUi, targetObjectClassName);
		command.setSearchTargetObject(targetObject);
		
		// For each dependent object in the targetObject add a default criteriaParameter to the criteriaParameters list
		for(int i = 0; i < targetObject.getDependentObject().size(); i++){
			AdvancedSearchCriteriaParameter criteriaParameter = new AdvancedSearchCriteriaParameter();
			criteriaParameter.setDeleted(false);
			criteriaParameter.setObjectName(targetObject.getDependentObject().get(i).getClassName());
			command.getCriteriaParameters().add(criteriaParameter);
		}
		
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("ajax_action", "updateSearchTargetObject");
		ajaxOutput.setHtmlContent(renderAjaxView("criteriaDetailsSection", params));
		
		return ajaxOutput;
	}
	
	/**
	 * This method is called when an attribute is updated. Here we need to pass the possible operators in a list within the ajaxOutput
	 * value returned. While the html of the value column is returned through ajaxOutput's htmlContent.
	 */
	public AjaxOutput updateAttribute(String attributeName, Integer index){
		AjaxOutput ajaxOutput = new AjaxOutput();
		AdvancedSearchCommand command = (AdvancedSearchCommand) extractCommand();
		
		String dependentObjectName = command.getCriteriaParameters().get(index).getObjectName();
		DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByName(command.getSearchTargetObject(), dependentObjectName);
		for(UiAttribute uiAttribute: dObject.getUiAttribute()){
			if(uiAttribute.getName().equals(attributeName))
				ajaxOutput.setObjectContent(uiAttribute.getOperator());
		}

		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("ajax_action", "updateAttribute");
		params.put("index", index.toString());
		ajaxOutput.setHtmlContent(renderAjaxView("updateValueContent", params));
		
		return ajaxOutput;
	}
	
	/**
	 * This method is called to delete a criteria. The deleted flag in advancedSearchCriteriaParameter object is set to true.
	 */
	public AjaxOutput deleteCriteria(Integer index){
		AdvancedSearchCommand command = (AdvancedSearchCommand) extractCommand();
		command.getCriteriaParameters().get(index).setDeleted(true);
		return null;
	}
	
	/**
	 * This method is called to add a new criteria to a dependent object. The class name of the dependent object is passed as a
	 * parameter to this method.
	 * @param dependentObjectClassName
	 * @return
	 */
	public AjaxOutput addCriteria(String dependentObjectDisplayName){
		AjaxOutput ajaxOutput = new AjaxOutput();
		AdvancedSearchCommand command = (AdvancedSearchCommand) extractCommand();
		DependentObject dObject = AdvancedSearchUiUtil.getDependentObjectByDisplayName(command.getSearchTargetObject(), dependentObjectDisplayName);
		AdvancedSearchCriteriaParameter parameter = new AdvancedSearchCriteriaParameter();
		parameter.setDeleted(false);
		parameter.setObjectName(dObject.getClassName());
		command.getCriteriaParameters().add(parameter);
		
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("ajax_action", "addNewCriteria");
		params.put("dependentObjectDisplayName", dObject.getDisplayName());
		ajaxOutput.setHtmlContent(renderAjaxView("addNewCriteria", params));
		
		return ajaxOutput;
	}
	
	public Class<?>[] controllers() {
		return CONTROLLERS;
	}
	
	protected String renderAjaxView(String viewName, Map<String, String> params) {
        WebContext webContext = WebContextFactory.get();

        params.put(AdvancedSearchController.AJAX_SUBVIEW_PARAMETER, viewName);

        String url = String.format("%s?%s", getCurrentPageContextRelative(webContext), createQueryString(params));
        log.debug("Attempting to return contents of " + url);
        try {
            String html = webContext.forwardToString(url);
            if (log.isDebugEnabled()) log.debug("Retrieved HTML:\n" + html);
            return html;
        } catch (ServletException e) {
            throw new CaaersSystemException(e);
        } catch (IOException e) {
            throw new CaaersSystemException(e);
        }
    }
	
	private String getCurrentPageContextRelative(WebContext webContext) {
        String contextPath = webContext.getHttpServletRequest().getContextPath();
        String page = webContext.getCurrentPage();
        if (contextPath == null) {
            log.debug("context path not set");
            return page;
        } else if (!page.startsWith(contextPath)) {
            log.debug(page + " does not start with context path " + contextPath);
            return page;
        } else {
            return page.substring(contextPath.length());
        }
    }
	
	protected String createQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey()).append('=').append(entry.getValue())
                    .append('&');
        }
        return sb.toString().substring(0, sb.length() - 1);
    }
	
	protected Object extractCommand() {
        WebContext webContext = getWebContext();
        Object command = null;
        for (Class<?> controllerClass : controllers()) {
            String formSessionAttributeName = controllerClass.getName() + ".FORM.command";
            command = webContext.getSession().getAttribute(formSessionAttributeName);
            if (command == null) {
                log.debug("Command not found using name " + formSessionAttributeName);
            } else {
                log.debug("Command found using name " + formSessionAttributeName);
                break;
            }
        }
        if (command == null) {
            throw new CaaersSystemException("Could not find command in session");
        } else {
            return command;
        }
    }
	
	protected WebContext getWebContext(){
    	return WebContextFactory.get();
    }
}