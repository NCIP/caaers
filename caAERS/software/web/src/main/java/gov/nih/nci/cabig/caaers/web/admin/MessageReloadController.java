package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.CaaersFieldsTree;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Biju Joseph
 */
public class MessageReloadController implements Controller {

    protected final Log log = LogFactory.getLog(getClass());
    protected ReloadableResourceBundleMessageSource messageSource;
    protected CaaersFieldsTree caaersFieldsTree;

	public MessageReloadController(){
	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        messageSource.clearCache();
        caaersFieldsTree.initialize();
        return null;
	}

    @Required
    public void setMessageSource(ReloadableResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }


    @Required
    public void setCaaersFieldsTree(CaaersFieldsTree caaersFieldsTree) {
        this.caaersFieldsTree = caaersFieldsTree;
    }
}
