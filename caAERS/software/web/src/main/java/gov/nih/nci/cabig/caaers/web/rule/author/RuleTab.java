package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.web.ae.CaptureAdverseEventInputCommand;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import gov.nih.nci.cabig.ctms.web.tabs.Tab;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestBindingException;

import com.semanticbits.rules.brxml.Column;
import com.semanticbits.rules.brxml.ReadableRule;
import com.semanticbits.rules.brxml.Rule;
import com.semanticbits.rules.brxml.RuleSet;
import com.semanticbits.rules.utils.RuleUtil;

/**
 * This tab will display all the Rules. User will be crreating / editing / deleting rules from this
 * tab.
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class RuleTab extends DefaultTab {
    private static final Log logger = LogFactory.getLog(RuleTab.class);
    
    public RuleTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    public RuleTab() {
        super("Rules", "Rules", "rule/author/authorRules");
    }

    private List<ReportDefinition> getReportDefinitions(Organization org) {
        // System.out.println("getting report definitions ....");
        // get report defnitions
    	
        List<ReportDefinition> reportDefinitions = org.getReportDefinitions();

        // cut down objects for serialization
        List<ReportDefinition> reducedReportDefinitions = new ArrayList<ReportDefinition>(
                        reportDefinitions.size());
        for (ReportDefinition reportDefinition : reportDefinitions) {
            // reportDefinition.setPlannedNotifications(null);
            // reportDefinition.setTimeScaleUnitType(null);
            reducedReportDefinitions.add(reportDefinition);
        }

        return reducedReportDefinitions;
    }
    
    @Override
    public void validate(RuleInputCommand cmd, Errors errors) {
    	CreateRuleCommand command = (CreateRuleCommand) cmd;
    	if(command.getRuleSet().getRule() == null || command.getRuleSet().getRule().size() < 1)
    		errors.rejectValue("ruleSet.rule", "RUL_015");
    	// Need to check for all the selects.
    	int i = 0;
    	for(Rule rule: command.getRuleSet().getRule()){
    		int j = 0;
    	
    		for(Column column: rule.getCondition().getColumn()){
    			if(column.getObjectType() == null || column.getObjectType().equals(""))
    				errors.rejectValue("ruleSet.rule[" + i + "].condition.column[" + j + "].objectType", "RUL_016");
    			if(column.getFieldConstraint().get(0).getFieldName() == null || column.getFieldConstraint().get(0).getFieldName().equals(""))
    				errors.rejectValue("ruleSet.rule[" + i + "].condition.column[" + j + "].fieldConstraint[0].fieldName", "RUL_017");
    			if(column.getFieldConstraint().get(0).getLiteralRestriction().get(0).getEvaluator() == null ||
    					column.getFieldConstraint().get(0).getLiteralRestriction().get(0).getEvaluator().equals(""))
    				errors.rejectValue("ruleSet.rule[" + i + "].condition.column[" + j + "].fieldConstraint[0].literalRestriction[0].evaluator", "RUL_018");
    			if(column.getFieldConstraint().get(0).getLiteralRestriction().get(0).getValue() != null &&
    					!column.getFieldConstraint().get(0).getLiteralRestriction().get(0).getValue().isEmpty()){
    				if(column.getFieldConstraint().get(0).getLiteralRestriction().get(0).getValue().get(0).equals(""))
    					errors.rejectValue("ruleSet.rule[" + i + "].condition.column[" + j + "].fieldConstraint[0].literalRestriction[0].value", "RUL_019");
    			}
    			if(column.getFieldConstraint().get(0).getLiteralRestriction().get(0).getValue() == null)
    				errors.rejectValue("ruleSet.rule[" + i + "].condition.column[" + j + "].fieldConstraint[0].literalRestriction[0].value", "RUL_019");
    			j++;
    		}
    		if(rule.getAction() == null || rule.getAction().isEmpty())
    			errors.rejectValue("ruleSet.rule[" + i + "].action", "RUL_020");
    		i++;
    	}
    	command.setErrorsForFields(new HashMap<String, Boolean>());
        WebUtils.populateErrorFieldNames(command.getErrorsForFields(), errors);
        
    }
    
    @Override
    public void postProcess(HttpServletRequest request, RuleInputCommand cmd, Errors errors) {
    	logger.debug("In RuleTab post process");
        super.postProcess(request, cmd, errors);
        
        int prevPage = WebUtils.getPreviousPage(request);
    	int targetPage = WebUtils.getTargetPage(request);
        
        CreateRuleCommand command = (CreateRuleCommand) cmd;
        if(!errors.hasErrors() && targetPage > prevPage){
        	
        	// Now incase the ruleSet in context is in edit mode we need to redploy the ruleSet on saving.
        	if(command.getMode().equals(CreateRuleCommand.EDIT_MODE))
        		command.saveAndDeploy();
        	else
        		command.save();
        }
        
    }

    @Override
    public Map<String, Object> referenceData(RuleInputCommand command) {
        CreateRuleCommand createRuleCommand = ((CreateRuleCommand) command);
        Map referenceData =  super.referenceData(command);
        
        String studyShortTitle = createRuleCommand.getCategoryIdentifier();

        if (!"".equals(studyShortTitle)) {
            Study study = createRuleCommand.getStudyDao().getByShortTitle(studyShortTitle);
            if (study != null) {
                createRuleCommand.setTerminology(study.getAeTerminology().getTerm()
                                .getDisplayName());
            }
        } else {
            createRuleCommand.setTerminology("");
        }

        createRuleCommand.setRuleUi(createRuleCommand.getTerminology());

        RuleSet ruleSet = createRuleCommand.getRuleSet();

        // Return if the rules are already retrieved
        if (ruleSet != null && ruleSet.getDescription() != null
                        && ruleSet.getDescription().equals(createRuleCommand.getRuleSetName())
                        && !createRuleCommand.isDataChanged()) {
            return super.referenceData(command);
        }
        createRuleCommand.setDataChanged(false);

        // Retrieve RuleSet based on the one chosen by the user.
        createRuleCommand.retrieveRuleSet();
        // Add a default rule if its a new ruleSet
        if(createRuleCommand.getMode().equals(CreateRuleCommand.CREATE_MODE)){
        	if(createRuleCommand.getRuleSet().getRule() == null || createRuleCommand.getRuleSet().getRule().size() < 1){
        		createRuleCommand.addDefaultRule();
        	}
        }
        	
        return referenceData;
    }
    
    /**
     * Returns the value associated with the <code>attributeName</code>, if present in
     * HttpRequest parameter, if not available, will check in HttpRequest attribute map.
     */
    protected Object findInRequest(final ServletRequest request, final String attributName) {

        Object attr = request.getParameter(attributName);
        if (attr == null) {
            attr = request.getAttribute(attributName);
        }
        return attr;
    }
    
}
