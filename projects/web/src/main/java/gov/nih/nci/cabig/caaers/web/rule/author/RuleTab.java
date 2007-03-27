package gov.nih.nci.cabig.caaers.web.rule.author;

import edu.nwu.bioinformatics.commons.CollectionUtils;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.HashMap;
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
				List<Rule> ruleIds = ruleAuthoringService.getRulesByCategory(getCategoryPath((CreateRuleCommand) command));
				//Unfortunately...the version of XFire - Jaxb combination we use has some problem at client side
				//in serializing the list of values..it doesn't set value for properties when called in a List. 
				//So for this iteration i am fetching this one by one...hope this will stand for this release as number 
				//of rules created will be less per study
				for(Rule ruleId : ruleIds) {
					Rule rule = ruleAuthoringService.getRule(ruleId.getId());
					List<Column> columns = rule.getCondition().getColumn();
					for(int i = 0; i < columns.size(); i++) {
						if("studySDO".equals(columns.get(i).getIdentifier())) {
							columns.remove(i);
						}
					}
					ruleSet.getRule().add(rule);
				}
				createRuleCommand.setRuleSet(ruleSet);
			} catch (Exception e) {
				e.printStackTrace();
				//throw new CaaersSystemException(e.getMessage(), e);
			}
		}
    	Map refdata = new HashMap();
    	refdata.put("notifications", createRuleCommand.getNotificationDao().getAll());
        return super.referenceData(command);
    }
    
    private String getCategoryPath(CreateRuleCommand command) {
		String categoryPath = null;
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
	}
    

}