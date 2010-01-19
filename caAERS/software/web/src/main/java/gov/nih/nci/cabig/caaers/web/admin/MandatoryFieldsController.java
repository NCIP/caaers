package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.CaaersFieldsTree;
import gov.nih.nci.cabig.caaers.domain.security.passwordpolicy.PasswordPolicy;
import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Jared Flatow
 */
public class MandatoryFieldsController extends SimpleFormController {

	private CaaersFieldsTree caaersFieldsTree;
	
    public MandatoryFieldsController() {
        setFormView("admin/mandatory_fields");
        setBindOnNewForm(true);
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return caaersFieldsTree;
    }

    @Override
    protected ModelAndView onSubmit(Object command, BindException errors) throws Exception {
        ModelAndView modelAndView = new ModelAndView(getFormView(), errors.getModel());
        return modelAndView.addObject("updated", true);
    }

    @Required
    public void setCaaersFieldsTree(CaaersFieldsTree caaersFieldsTree){
    	this.caaersFieldsTree = caaersFieldsTree;
    }
}
