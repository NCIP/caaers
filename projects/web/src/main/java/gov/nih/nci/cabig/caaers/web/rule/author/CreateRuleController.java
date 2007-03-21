package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.web.rule.AbstractRuleInputController;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Sujith Vellat Thayyilthodi
 * */
public class CreateRuleController extends AbstractRuleInputController<CreateRuleCommand> {

    public CreateRuleController() {
        super();
        setCommandClass(CreateRuleCommand.class);
    }

	@Override
	protected ModelAndView processFinish(HttpServletRequest arg0, HttpServletResponse arg1, Object oCommand, BindException arg3) throws Exception {
		CreateRuleCommand command = (CreateRuleCommand) oCommand;
        command.save();
		Map<String, Object> model = new HashMap();
		model.put("ruleSet", command.getRuleSet());
        return new ModelAndView("redirectToTriggerList", model);

	}

    @Override
    protected void initFlow() {
        super.initFlow();
    }

    @Override
    protected String getFlowName() {
        return "Author Rule";
    }
}