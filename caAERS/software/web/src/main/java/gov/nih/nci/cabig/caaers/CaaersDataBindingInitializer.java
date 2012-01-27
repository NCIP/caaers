package gov.nih.nci.cabig.caaers;

import gov.nih.nci.cabig.caaers.web.ControllerTools;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * Will register custom editors and other binding initializations.
 */
public class CaaersDataBindingInitializer implements WebBindingInitializer {
    public void initBinder(WebDataBinder binder, WebRequest webRequest) {
        binder.setAutoGrowNestedPaths(false);
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, ControllerTools.getDateEditor(false));
    }
}
