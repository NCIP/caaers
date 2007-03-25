package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
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

	private RuleAuthoringService ruleAuthoringService;
	
    public CreateRuleController() {
        super();
        addTabs();
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
	protected Object formBackingObject(HttpServletRequest request) {
		return new CreateRuleCommand(ruleAuthoringService);	
	}

    @Override
    protected void initFlow() {
        super.initFlow();
        
    }

    @Override
    protected String getFlowName() {
        return "Author Rule";
    }

    protected void addTabs() {
    	getFlow().addTab(new SelectRueTypeTab());
    	getFlow().addTab(new TriggerTab());
        getFlow().addTab(new RuleTab());
        getFlow().addTab(new ReviewTab());
    }    
    
	public RuleAuthoringService getRuleAuthoringService() {
		return ruleAuthoringService;
	}

	public void setRuleAuthoringService(RuleAuthoringService ruleAuthoringService) {
		this.ruleAuthoringService = ruleAuthoringService;
	}
}