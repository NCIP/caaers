/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.tools.configuration.Configuration;
import gov.nih.nci.cabig.caaers.tools.mail.CaaersJavaMailSender;
import gov.nih.nci.cabig.ctms.tools.configuration.ConfigurationProperty;
import gov.nih.nci.cabig.ctms.tools.configuration.ConfigurationPropertyEditor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class ConfigurationController extends SimpleFormController {
    private Configuration configuration;
    private CaaersJavaMailSender caaersJavaMailSender;

    public ConfigurationController() {
        setCommandClass(ConfigurationCommand.class);
        setFormView("admin/configure");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new ConfigurationCommand(configuration);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        super.initBinder(request, binder);
        for (ConfigurationProperty<?> property : configuration.getProperties().getAll()) {
            binder.registerCustomEditor(Object.class, "conf[" + property.getKey() + "].value", new ConfigurationPropertyEditor(property));
        }
    }

    @Override
    protected ModelAndView onSubmit(Object command, BindException errors) throws Exception {
        if(caaersJavaMailSender != null) caaersJavaMailSender.afterPropertiesSet();           //SUITE-626
        return new ModelAndView("redirectToConfiguration", "updated", true);
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
    
    @Required
    public CaaersJavaMailSender getCaaersJavaMailSender() {
        return caaersJavaMailSender;
    }

    public void setCaaersJavaMailSender(CaaersJavaMailSender caaersJavaMailSender) {
        this.caaersJavaMailSender = caaersJavaMailSender;
    }
}
