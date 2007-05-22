package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.Errors;

/*
 * This class is used for displaying the rule sets avaiable based on the user selection. User can choose sponsor, intitution or study.
 * 
 *  @version 1.0
 *  @author Visu Patlolla
 */
public class DisplayRuleSetsTab extends DefaultTab 
{

	public DisplayRuleSetsTab(String longTitle, String shortTitle, String viewName) 
	{
		super(longTitle, shortTitle, viewName);
	}

	public DisplayRuleSetsTab() 
	{
        super("Select Rule Set", "Select Rule Set", "rule/author/displayRuleSets");
	}

    @Override
    protected void initFields() 
    {

    }

    /*
     * This method retrieves the rule sets based on the Rule Level or Rule Level and Study name. 
     * 
     * (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.web.rule.DefaultTab#referenceData(gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand)
     */
    @Override
    public Map<String, Object> referenceData(RuleInputCommand command) 
    {
    	CreateRuleCommand createRuleCommand = ((CreateRuleCommand)command);
    	
    	List<RuleSet> ruleSets = createRuleCommand.getExistingRuleSets();
    	
    	// THis method should retrieve rule sets based on the SponsorName or InstitutionName or Study Name
    	
		RuleAuthoringService ruleAuthoringService = createRuleCommand.getRuleAuthoringService();
		
		if (CreateRuleCommand.SPONSOR_LEVEL.equals(createRuleCommand.getLevel()))
		{
			ruleSets = ruleAuthoringService.findRuleSetsForSponsor(createRuleCommand.getSponsorName());
		}
		else if (CreateRuleCommand.STUDY_LEVEL.equals(createRuleCommand.getLevel()))
		{
			ruleSets = ruleAuthoringService.findRuleSetsForStudy(createRuleCommand.getSponsorName(), createRuleCommand.getCategoryIdentifier());
		}
		else
		{
			
		}
    	
    	createRuleCommand.setExistingRuleSets(ruleSets);
    	
        return super.referenceData(createRuleCommand);
    }

	@Override
	public void postProcess(HttpServletRequest request, RuleInputCommand command, Errors errors)
	{
		CreateRuleCommand createRuleCommand = (CreateRuleCommand) command;
		
	}
    
    
    
}