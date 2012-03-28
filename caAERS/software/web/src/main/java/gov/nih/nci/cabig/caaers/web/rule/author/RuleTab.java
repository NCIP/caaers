package gov.nih.nci.cabig.caaers.web.rule.author;

import com.semanticbits.rules.brxml.Column;
import com.semanticbits.rules.brxml.FieldConstraint;
import com.semanticbits.rules.brxml.LiteralRestriction;
import com.semanticbits.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import gov.nih.nci.cabig.caaers.web.utils.WebUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * This tab will display all the Rules. User will be crreating / editing / deleting rules from this
 * tab.
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author Biju Joseph
 */
public class RuleTab extends DefaultTab {
    private static final Log logger = LogFactory.getLog(RuleTab.class);


    public RuleTab() {
        super("Rules", "Rules", "rule/author/authorRules");
    }
    
    @Override
    public void validate(RuleInputCommand cmd, Errors errors) {
    	CreateRuleCommand command = (CreateRuleCommand) cmd;
    	if(command.getRuleSet().getRule() == null || command.getRuleSet().getRule().size() < 1)
    		errors.rejectValue("ruleSet.rule", "RUL_015");
    	// Need to check for all the selects.
    	int i = -1;
    	for(Rule rule: command.getRuleSet().getRule()){
            i++;
    		int j = -1;
    		for(Column column: rule.getCondition().getColumn()){

                j++;

    			if(column.getObjectType() == null || column.getObjectType().equals(""))
    				errors.rejectValue("ruleSet.rule[" + i + "].condition.column[" + j + "].objectType", "RUL_016");
                
                if(CollectionUtils.isEmpty(column.getFieldConstraint()) || StringUtils.isEmpty(column.getFieldConstraint().get(0).getFieldName())){
                    errors.rejectValue("ruleSet.rule[" + i + "].condition.column[" + j + "].fieldConstraint[0].fieldName", "RUL_017");
                    continue;
                }
                
                FieldConstraint fc = column.getFieldConstraint().get(0);
                if(CollectionUtils.isEmpty(fc.getLiteralRestriction()) || StringUtils.isEmpty(fc.getLiteralRestriction().get(0).getEvaluator())){
                    errors.rejectValue("ruleSet.rule[" + i + "].condition.column[" + j + "].fieldConstraint[0].literalRestriction[0].evaluator", "RUL_018");
                    continue;
                }
                LiteralRestriction lr = fc.getLiteralRestriction().get(0);
                if(CollectionUtils.isEmpty(lr.getValue()) || StringUtils.isEmpty(lr.getValue().get(0)))  {
                    errors.rejectValue("ruleSet.rule[" + i + "].condition.column[" + j + "].fieldConstraint[0].literalRestriction[0].value", "RUL_019");
                }

    		}
    		if(CollectionUtils.isEmpty(rule.getAction()))
    			errors.rejectValue("ruleSet.rule[" + i + "].action", "RUL_020");
    	}
    	command.setErrorsForFields(new HashMap<String, Boolean>());
        WebUtils.populateErrorFieldNames(command.getErrorsForFields(), errors);
        
    }
    
    @Override
    public void postProcess(HttpServletRequest request, RuleInputCommand cmd, Errors errors) {
    	logger.debug("In RuleTab post process");
        CreateRuleCommand command = (CreateRuleCommand) cmd;
        command.removeDeletedRules();

        int prevPage = WebUtils.getPreviousPage(request);
        int targetPage = WebUtils.getTargetPage(request);
        if(!errors.hasErrors() && targetPage > prevPage){
            try{
                if(!command.isCreateMode())  {
                    command.saveAndDeploy();
                }
            }catch(Exception e){
                logger.error("error while processing the rules", e);
                errors.reject("RUL_022", "Unable to process the rules due to error");
            }
        }


    }

    @Override
    public Map<String, Object> referenceData(RuleInputCommand command) {
        Map<String, Object> referenceData =  super.referenceData(command);
        CreateRuleCommand createRuleCommand = ((CreateRuleCommand) command);


        createRuleCommand.retrieveReportDefinitions();

        if(!createRuleCommand.isCreateMode() ) return referenceData;

        if( !(createRuleCommand.isSafetySignallingRule() ||  createRuleCommand.isFieldLevelRule()) ){
            //copy the rules from parent if one is available
            createRuleCommand.retrieveParentRuleSet();
        }

        //add an empty rule if the rule-set do not have any
        createRuleCommand.addDefaultRule();

        return referenceData;
    }

}
