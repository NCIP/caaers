package gov.nih.nci.cabig.caaers.web.rule.notification;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.tools.ObjectTools;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Organization;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.web.servlet.mvc.AbstractFormController;

public class ReportDefinitionAjaxFacade {
	public static final String AJAX_SUBVIEW_PARAMETER = "subview";
	public static final String AJAX_INDEX_PARAMETER = "index";
	public static final String AJAX_REQUEST_PARAMETER = "isAjax";
	private static final Log log = LogFactory.getLog(ReportDefinitionAjaxFacade.class);
	
	private OrganizationDao orgDao;

	///LOGIC
	public String addReportDeliveryDefinition(int index, int type){
		WebContext webCtx = WebContextFactory.get();
		HttpServletRequest request = webCtx.getHttpServletRequest();
		ReportDefinitionCommand cmd = getCommand( request);
		cmd.getReportDefinition().getDeliveryDefinitions().get(index).setEntityType(type);
		//--- add necessary parameters 
		request.setAttribute(AJAX_INDEX_PARAMETER, index);
		request.setAttribute(AJAX_SUBVIEW_PARAMETER, "addReportDefinitionSection");
		request.setAttribute(AJAX_REQUEST_PARAMETER, "AJAX");
		String url = getCurrentPageContextRelative(webCtx);
		
		return getOutputFromJsp(url);
	}
	
	public List<Organization> matchOrganization(String text){
		return ObjectTools.reduceAll(orgDao.getBySubnames(text.split("\\s+")), "id", "name");
	}
	
	/// HELPER METHODS
	public ReportDefinitionCommand getCommand(HttpServletRequest request){
		
		String commandName = CreateReportDefinitionController.class.getName() +".FORM.command";
		ReportDefinitionCommand cmd = (ReportDefinitionCommand) request.getSession().getAttribute(commandName);
		request.setAttribute(AbstractFormController.DEFAULT_COMMAND_NAME,cmd);
		return cmd;
	}
	
	private String getOutputFromJsp(String jspResource) {
		String html = "Error in rendering...";
	    try {
			html = WebContextFactory.get().forwardToString(jspResource);
		} catch (ServletException e) {
			throw new CaaersSystemException(e.getMessage(), e);
		} catch (IOException e) {
			throw new CaaersSystemException(e.getMessage(), e);
		}
	    return html;
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
	
	///BEAN PROPERTIES
	public void setOrganizationDao(OrganizationDao orgDao){
		this.orgDao = orgDao;
	}
	public OrganizationDao getOrganizationDao(){
		return orgDao;
	}
}
