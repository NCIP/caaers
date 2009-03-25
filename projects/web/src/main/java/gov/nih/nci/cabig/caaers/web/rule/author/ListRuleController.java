package gov.nih.nci.cabig.caaers.web.rule.author;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.semanticbits.rules.api.RuleAuthoringService;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class ListRuleController extends SimpleFormController {

    private RuleAuthoringService ruleAuthoringService;

    public ListRuleController() {
        setCommandClass(ListRuleCommand.class);
        setBindOnNewForm(true);
        setFormView("rule/author/list");
        setSuccessView("rule/author/list");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        return new ListRuleCommand(ruleAuthoringService);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request, Object command, Errors errors)
                    throws Exception {
        Map<String, Object> refdata = new HashMap<String, Object>();
        refdata.put("pageTitle", "List Rules: Select Rule to Update");
        return refdata;
    }

    public RuleAuthoringService getRuleAuthoringService() {
        return ruleAuthoringService;
    }

    public void setRuleAuthoringService(RuleAuthoringService ruleAuthoringService) {
        this.ruleAuthoringService = ruleAuthoringService;
    }
}