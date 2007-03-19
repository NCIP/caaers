package gov.nih.nci.cabig.caaers.web.rule.author;

import javax.servlet.http.HttpServletRequest;

import gov.nih.nci.cabig.caaers.web.ae.ListAdverseEventsCommand;

import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class ListRuleController extends SimpleFormController {
    
	public ListRuleController() {
        setCommandClass(ListAdverseEventsCommand.class);
        setBindOnNewForm(true);
        setFormView("rule/list");
        setSuccessView("rule/list");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new ListRuleCommand();
    }

}