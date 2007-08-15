package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.business.service.RulesEngineService;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.text.ParseException;
import java.util.HashMap;
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
    	
    	String studyShortTitle = createRuleCommand.getCategoryIdentifier();
    	
    	System.out.println(" S R I N O " + studyShortTitle );
    	
    	if (!studyShortTitle.trim().equals("")) {
    		Map<String, String> props = new HashMap<String, String>();
    		props.put("studyShortTitle", studyShortTitle);
    		try {
    			List<Study> studies =  createRuleCommand.getStudyDao().getBySubnames(new String[] {studyShortTitle});
    			if (studies.size() > 0) {
    				Study study = studies.get(0);
    				createRuleCommand.setTerminology(study.getTerminology().getTerm().getDisplayName());
    			}
    			
    			//System.out.println(study.getTerminology().getTerm().getDisplayName());
    			//System.out.println(study.getTerminology().getTerm().getCode());
			} catch (Exception e) {
				logger.error("Exception while retrieving the Study in RuleTab", e);
			}
    	}
    		
    	
    	createRuleCommand.setReportDefinitions(createRuleCommand.getReportDefinitionDao().getAll());
    
    	RuleSet ruleSet = createRuleCommand.getRuleSet();
    	
    	// Return if the rules are already retrieved
    	if (ruleSet != null && ruleSet.getDescription() != null && 
    			ruleSet.getDescription().equals(createRuleCommand.getRuleSetName()) && !createRuleCommand.isDataChanged())
    	{
    		return super.referenceData(command);
    	}
    	createRuleCommand.setDataChanged(false);
    	
    	// Retrieve RuleSet based on the one choosen by the user
			try 
			{
				RulesEngineService rulesEngineService = createRuleCommand.getRulesEngineService();
				
				if (CreateRuleCommand.SPONSOR_LEVEL.equals(createRuleCommand.getLevel()))
				{

						ruleSet = rulesEngineService.getRuleSetForSponsor(createRuleCommand.getRuleSetName(), createRuleCommand.getSponsorName());
						
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
									if("factResolver".equals(columns.get(i).getIdentifier()))
									{
										columns.remove(i);
										i = -1;
										continue;
									}
								}
							}
						}
//					}
				}
				else if (CreateRuleCommand.SPONSOR_DEFINED_STUDY_LEVEL.equals(createRuleCommand.getLevel()))
				{
					
					
					String packageName = createRuleCommand.constructPackageName(createRuleCommand.getLevel());

					ruleSet = rulesEngineService.getRuleSetForSponsorDefinedStudy(createRuleCommand.getRuleSetName(), createRuleCommand.getCategoryIdentifier(), createRuleCommand.getSponsorName());

					boolean areSponsorRules = false;
					// Check whether ruleset exists? Otherwise retrieve sponsor ruleset
					if (ruleSet == null)
					{
						ruleSet = rulesEngineService.getRuleSetForSponsor(createRuleCommand.getRuleSetName(), createRuleCommand.getSponsorName());
						areSponsorRules = true;
					}
					

						if (ruleSet != null && ruleSet.getRule().size() > 0)
						{	
							//ruleSet.setName(packageName);
							List <Rule> rules = ruleSet.getRule();
							
							for(Rule rule : rules) 
							{
								rule.getMetaData().setPackageName(packageName); 
								//rule.setId(null);
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
									if("factResolver".equals(columns.get(i).getIdentifier()))
									{
										columns.remove(i);
										i = -1;
										continue;
									}									
								}
								
								// Remove category from sponsor rules
                                if (areSponsorRules)
                                {
                                	rule.setId(null);
									if (rule.getMetaData() != null)
                                    {
 										rule.getMetaData().setCategory(null);
                                    }
                                }

							}
						}
				} else if (CreateRuleCommand.INSTITUTIONAL_LEVEL.equals(createRuleCommand.getLevel())) {

					String packageName = createRuleCommand.constructPackageName(createRuleCommand.getLevel());

					ruleSet = rulesEngineService.getRuleSetForInstitution(createRuleCommand.getRuleSetName(), createRuleCommand.getInstitutionName());

					
					
					
						if (ruleSet != null && ruleSet.getRule().size() > 0)
						{	
							//ruleSet.setName(packageName);
							List <Rule> rules = ruleSet.getRule();
							
							for(Rule rule : rules) 
							{
								rule.getMetaData().setPackageName(packageName); 
								//rule.setId(null);
								List<Column> columns = rule.getCondition().getColumn();
								
								//System.out.println("size ..." + columns.size());
								
								for(int i = 0; i < columns.size(); i++) 
								{
									if("organizationSDO".equals(columns.get(i).getIdentifier())) 
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
									if("factResolver".equals(columns.get(i).getIdentifier()))
									{
										columns.remove(i);
										i = -1;
										continue;
									}									
								}
							}
						}					
				}
				
				else if (CreateRuleCommand.INSTITUTION_DEFINED_STUDY_LEVEL.equals(createRuleCommand.getLevel())) {
					String packageName = createRuleCommand.constructPackageName(createRuleCommand.getLevel());

					ruleSet = rulesEngineService.getRuleSetForInstitutionDefinedStudy(createRuleCommand.getRuleSetName(), createRuleCommand.getCategoryIdentifier(), createRuleCommand.getInstitutionName());

					boolean areSponsorRules = false;
					// Check whether ruleset exists? Otherwise retrieve inst ruleset
					if (ruleSet == null)
					{
						ruleSet = rulesEngineService.getRuleSetForInstitution(createRuleCommand.getRuleSetName(), createRuleCommand.getInstitutionName());
						areSponsorRules = true;
					}
					

						if (ruleSet != null && ruleSet.getRule().size() > 0)
						{	
							//ruleSet.setName(packageName);
							List <Rule> rules = ruleSet.getRule();
							
							for(Rule rule : rules) 
							{
								rule.getMetaData().setPackageName(packageName); 
								//rule.setId(null);
								List<Column> columns = rule.getCondition().getColumn();
								
								for(int i = 0; i < columns.size(); i++) 
								{
									if("studySDO".equals(columns.get(i).getIdentifier())) 
									{
										columns.remove(i);
										i = -1;
										continue;
									}
									if("organizationSDO".equals(columns.get(i).getIdentifier())) 
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
									if("factResolver".equals(columns.get(i).getIdentifier()))
									{
										columns.remove(i);
										i = -1;
										continue;
									}									
								}
								
								// Remove category from sponsor rules
                                if (areSponsorRules)
                                {
                                	rule.setId(null);
									if (rule.getMetaData() != null)
                                    {
 										rule.getMetaData().setCategory(null);
                                    }
                                }

							}
						}
				}
				
				if (ruleSet == null)
				{
					ruleSet = new RuleSet();
					ruleSet.setDescription(createRuleCommand.getRuleSetName());
				}
				createRuleCommand.setRuleSet(ruleSet);
				
			} 
			catch (Exception e) 
			{
				logger.error("Exception while retrieving the RuleSet", e);
			}
    	
    	return super.referenceData(command);
    }

}