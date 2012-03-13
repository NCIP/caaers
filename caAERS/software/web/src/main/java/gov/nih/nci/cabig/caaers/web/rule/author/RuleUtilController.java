package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Used to delete the rule set.
 * @author Srini
 * @author Monish Dombla
 */
public class RuleUtilController extends AbstractCommandController {

    private CaaersRulesEngineService caaersRulesEngineService;

    public RuleUtilController() {
        setCommandClass(RuleUtilCommand.class);
    }

    @Override
    protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response,
                    Object arg2, BindException arg3) throws Exception {
        String ruleSetName = request.getParameter("ruleSetName");
        caaersRulesEngineService.deleteRuleSet(ruleSetName);
        return new ModelAndView("redirect:/pages/rule/list");
    }

    public void setCaaersRulesEngineService(CaaersRulesEngineService caaersRulesEngineService) {
        this.caaersRulesEngineService = caaersRulesEngineService;
    }
}
