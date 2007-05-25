package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * This tab will display all the Rules.
 * User will be crreating / editing / deleting rules from this tab.
 * @author Sujith Vellat Thayyilthodi
 * */
public class RuleTab extends DefaultTab 
{
	private static final Log logger = LogFactory.getLog(RuleTab.class);
	
	public RuleTab(String longTitle, String shortTitle, String viewName) {
		super(longTitle, shortTitle, viewName);
	}

	public RuleTab() {
		super("Add Rules","RuleSet","rule/author/authorRules");
	}
	

    @Override
    public Map<String, Object> referenceData(RuleInputCommand command) 
    {
    	CreateRuleCommand createRuleCommand = ((CreateRuleCommand)command);
    
    	RuleSet ruleSet = createRuleCommand.getRuleSet();
    	
    	if (ruleSet != null && ruleSet.getDescription() != null && 
    			ruleSet.getDescription().equals(createRuleCommand.getRuleSetName()))
    	{
    		return super.referenceData(command);
    	}
    	
    	// Retrieve RuleSet based on the one choosen by the user
			try 
			{
				RuleAuthoringService ruleAuthoringService = createRuleCommand.getRuleAuthoringService();
				
/*				List<Rule> ruleIds = ruleAuthoringService.getRulesByCategory(getCategoryPath((CreateRuleCommand) command));
				
				//Unfortunately...the version of XFire - Jaxb combination we use has some problem at client side
				//in serializing the list of values..it doesn't set value for properties when called in a List. 
				//So for this iteration i am fetching this one by one...hope this will stand for this release as number 
				//of rules created will be less per study
				for(Rule ruleId : ruleIds) 
				{
					Rule rule = ruleAuthoringService.getRule(ruleId.getId());
					List<Column> columns = rule.getCondition().getColumn();
					for(int i = 0; i < columns.size(); i++) {
						if("studySDO".equals(columns.get(i).getIdentifier())) {
							columns.remove(i);
						}
					}
					ruleSet.getRule().add(rule);
				}
*/	
				// Load the rules based on the package name. If package is found, then load rules from it. Otherwise get the Sponsor level package
				String packageName = createRuleCommand.constructPackageName(createRuleCommand.getLevel());
				
				// Check whether this package exists
				if (ruleAuthoringService.containsRuleSet(packageName))
				{	
					ruleSet = ruleAuthoringService.getRuleSet(packageName);

					if (ruleSet != null && ruleSet.getRule().size() > 0)
					{	
						List <Rule> rules = ruleSet.getRule();
						
						for(Rule rule : rules) 
						{
							List<Column> columns = rule.getCondition().getColumn();
							
							for(int i = 0; i < columns.size(); i++) 
							{
								if("studySDO".equals(columns.get(i).getIdentifier())) 
								{
									columns.remove(i);
									i = -1;
									continue;
								}
								
								if("adverseEventEvaluationResult".equals(columns.get(i).getIdentifier()))
								{
									columns.remove(i);
									i = -1;
									continue;
								}
							}
						}
					}
				}
				else if (CreateRuleCommand.STUDY_LEVEL.equals(createRuleCommand.getLevel()))
				{
					String sponsorPackageName = createRuleCommand.constructPackageName(CreateRuleCommand.SPONSOR_LEVEL);

					// Load the sponsor level package
					if (ruleAuthoringService.containsRuleSet(sponsorPackageName))
					{	
						ruleSet = ruleAuthoringService.getRuleSet(sponsorPackageName);

						if (ruleSet != null && ruleSet.getRule().size() > 0)
						{	
							ruleSet.setName(packageName);
							List <Rule> rules = ruleSet.getRule();
							
							for(Rule rule : rules) 
							{
								rule.getMetaData().setPackageName(packageName); 
								rule.setId(null);
								List<Column> columns = rule.getCondition().getColumn();
								
								for(int i = 0; i < columns.size(); i++) 
								{
									if("studySDO".equals(columns.get(i).getIdentifier())) 
									{
										columns.remove(i);
										i = -1;
									}
									if("adverseEventEvaluationResult".equals(columns.get(i).getIdentifier()))
									{
										columns.remove(i);
										i = -1;
										continue;
									}
								}
							}
						}
					}
					
				}
				
				createRuleCommand.setRuleSet(ruleSet);
				
			} 
			catch (Exception e) 
			{
				logger.error("Exception while retrieving the RuleSet", e);
			}
    	
    	// This is commented as we do not need notifications while authoring the rules
    	//Map refdata = new HashMap();
    	//refdata.put("notifications", createRuleCommand.getNotificationDao().getAll());
        
    	return super.referenceData(command);
    }
    
    /*
     * This method construts the category path based on the user selections. It needs Sponsor name, Institution Name or Study Name along with RuleSet Name
     */
    private String getCategoryPath(CreateRuleCommand command) 
    {
/*		String categoryPath = null;
		if (CreateRuleCommand.STUDY_LEVEL.equals(command.getLevel())) {
			String shortTitle = command.getCategoryIdentifier();
			Study template = new Study();
			template.setShortTitle(shortTitle);
			Study study = CollectionUtils.firstElement(command.getStudyDao()
					.searchByExample(template, false));
			categoryPath = study.getPrimarySponsorCode() + "/"
					+ study.getStudySites().get(0).getSite().getName() + "/"
					+ study.getShortTitle();
		}
		return categoryPath;
*/	
    	final String SPONSOR_BASE_PATH = "/Sponsor";
    	final String INSTITUTION_BASE_PATH = "/Institution";
    	final String STUDY_BASE_PATH = "/Study";
    	
    	String categoryPath = null;
    	
    	if (CreateRuleCommand.SPONSOR_LEVEL.equalsIgnoreCase(command.getLevel()))
    	{
    		categoryPath = SPONSOR_BASE_PATH + "/" + command.getSponsorName() + "/" + command.getRuleSetName(); 
    	}
    	else if (CreateRuleCommand.INSTITUTIONAL_LEVEL.equalsIgnoreCase(command.getLevel()))
    	{
    		categoryPath = INSTITUTION_BASE_PATH + "/" + command.getInstitutionName() + "/" + command.getRuleSetName();
    	}
    	else
    	{
    		categoryPath = STUDY_BASE_PATH + "/" + command.getSponsorName() + "/" + command.getCategoryIdentifier() + "/" + command.getRuleSetName();
    	}
    	
    	return categoryPath;
    }

    /*
     * 
     */
	private String getStringWithoutSpaces(String str)
	{
		String _str= str.trim();
		return _str.replace(" ", "_");
	}

    

}