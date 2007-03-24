package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.List;
import java.util.Map;


/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class RuleTab extends DefaultTab {

	private RuleAuthoringService ruleAuthoringService;
	
	public RuleTab(String longTitle, String shortTitle, String viewName) {
		super(longTitle, shortTitle, viewName);
	}

	public RuleTab(RuleAuthoringService ruleAuthoringService) {
		super("Add Rules","Rules","rule/author/authorRules");
		this.ruleAuthoringService = ruleAuthoringService;		
	}

    @Override
    public Map<String, Object> referenceData(RuleInputCommand command) {
    	RuleSet ruleSet = ((CreateRuleCommand)command).getRuleSet();
    	if (ruleSet.getRule().size() == 0) {
			try {
				List<Rule> rules = ruleAuthoringService.getRulesByCategory(
								getCategoryPath((CreateRuleCommand) command));
				ruleSet.getRule().addAll(rules);
				((CreateRuleCommand) command).setRuleSet(ruleSet);
			} catch (Exception e) {
				e.printStackTrace();
				//throw new CaaersSystemException(e.getMessage(), e);
			}
		}
        return super.referenceData(command);
    }
    
    private String getCategoryPath(CreateRuleCommand command) {
    	String categoryPath = null;
    	if(CreateRuleCommand.STUDY_LEVEL.equals(command.getLevel())){
    		categoryPath = command.SPONSOR_LEVEL + "/" + command.INSTITUTIONAL_LEVEL + "/" + command.STUDY_LEVEL + "/" +command.getCategory();    		
    	}
    	return categoryPath;
    }
    

}