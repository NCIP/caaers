package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.List;
import java.util.Map;


/**
 * This tab will display all the Rules.
 * User will be crreating / editing / deleting rules from this tab.
 * @author Sujith Vellat Thayyilthodi
 * */
public class RuleTab extends DefaultTab {
	
	public RuleTab(String longTitle, String shortTitle, String viewName) {
		super(longTitle, shortTitle, viewName);
	}

	public RuleTab() {
		super("Add Rules","Rules","rule/author/authorRules");		
	}

    @Override
    public Map<String, Object> referenceData(RuleInputCommand command) {
    	CreateRuleCommand createRuleCommand = ((CreateRuleCommand)command);
    	RuleSet ruleSet = createRuleCommand.getRuleSet();
    	if (ruleSet.getRule().size() == 0) {
			try {
				RuleAuthoringService ruleAuthoringService = createRuleCommand.getRuleAuthoringService();
				List<Rule> rules = ruleAuthoringService.getRulesByCategory(getCategoryPath((CreateRuleCommand) command));
				ruleSet.getRule().addAll(rules);
				createRuleCommand.setRuleSet(ruleSet);
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