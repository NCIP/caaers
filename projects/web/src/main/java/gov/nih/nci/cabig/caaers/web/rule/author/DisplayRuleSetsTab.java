package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.business.service.RulesEngineService;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

/*
 * This class is used for displaying the rule sets avaiable based on the user selection. User can choose sponsor, intitution or study.
 * 
 *  @version 1.0
 *  @author Visu Patlolla
 */
public class DisplayRuleSetsTab extends DefaultTab 
{
	private static final Log logger = LogFactory.getLog(DisplayRuleSetsTab.class);
	
	public DisplayRuleSetsTab(String longTitle, String shortTitle, String viewName) 
	{
		super(longTitle, shortTitle, viewName);
	}

	public DisplayRuleSetsTab() 
	{
        super("Select Rule Set", "Select Rule Set", "rule/author/displayRuleSets");
	}

    
    /*
     * This method retrieves the rule sets based on the Rule Level or Rule Level and Study name. 
     * 
     */
    @Override
    public Map<String, Object> referenceData(RuleInputCommand command) 
    {
    	CreateRuleCommand createRuleCommand = ((CreateRuleCommand)command);
    	
    	List<RuleSet> ruleSets = createRuleCommand.getExistingRuleSets();
    	
    	// Use RuleEngineService to retrieve all the RuleSets
    	
    	try
    	{
        	
    		RulesEngineService rulesEngineService = createRuleCommand.getRulesEngineService();

        	if (CreateRuleCommand.SPONSOR_LEVEL.equals(createRuleCommand.getLevel()))
    		{
    			ruleSets = rulesEngineService.getAllRuleSetsForSponsor(createRuleCommand.getSponsorName());
    		}
    		else if (CreateRuleCommand.SPONSOR_DEFINED_STUDY_LEVEL.equals(createRuleCommand.getLevel()))
    		{
    			ruleSets = rulesEngineService.getAllRuleSetsForSponsorDefinedStudy(createRuleCommand.getCategoryIdentifier(), createRuleCommand.getSponsorName());
    		}
    		else if (CreateRuleCommand.INSTITUTIONAL_LEVEL.equals(createRuleCommand.getLevel()))
    		{
    			ruleSets = rulesEngineService.getAllRuleSetsForInstitution(createRuleCommand.getInstitutionName());
    		}
    		else if (CreateRuleCommand.INSTITUTION_DEFINED_STUDY_LEVEL.equals(createRuleCommand.getLevel()))
    		{
    			ruleSets = rulesEngineService.getAllRuleSetsForInstitutionDefinedStudy(createRuleCommand.getCategoryIdentifier(), createRuleCommand.getInstitutionName());
    		}
        	
    	}
    	catch(Exception ex)
    	{
    		logger.error("Exception while retrieving rule sets", ex);
    		// REVISIT: Create meaningful error message
    	}
    	
    	createRuleCommand.setExistingRuleSets(ruleSets);
    	
        return super.referenceData(createRuleCommand);
    }

	@Override
	public void postProcess(HttpServletRequest request, RuleInputCommand command, Errors errors)
	{
		// Make sure that user has selected a RuleSet
		//CreateRuleCommand createRuleCommand = (CreateRuleCommand) command;
		
	}
}