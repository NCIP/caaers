package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Represents the first tab while authoring Rules.
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author Biju Joseph
 */
public class SelectRuleTypeTab extends TabWithFields<RuleInputCommand> {
    private static final Log logger = LogFactory.getLog(SelectRuleTypeTab.class);
    private static final String RULE_LEVEL_FIELD_GROUP = "RuleLevel";

    public SelectRuleTypeTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    public SelectRuleTypeTab() {
        super("Rule Type", "Rule Type", "rule/author/selectRuleType");
    }

    @Override
    public Map<String, Object> referenceData(RuleInputCommand cmd) {
        Map<String, Object> refdata = super.referenceData();
        
        CreateRuleCommand command = (CreateRuleCommand) cmd;
        boolean initializeLevelSelect = false;
        boolean initializeRuleSetNameSelect = false;
        String initialLevel = "Please select a Rule level";
        String initialRuleSet = "Please select a RuleSet Name";
        if(command.getLevel() != null && !command.getLevel().equals("")){
        	initializeLevelSelect = true;
        	initialLevel = command.getLevel();
        }
        if(command.getRuleSetName() != null && !command.getRuleSetName().equals("")){
        	initializeRuleSetNameSelect = true;
        	initialRuleSet = command.getRuleSetName();
        }
        refdata.put("initializeLevelSelect", initializeLevelSelect);
        refdata.put("initializeRuleSetNameSelect", initializeRuleSetNameSelect);
        refdata.put("initialLevel", initialLevel);
        refdata.put("initialRuleSet", initialRuleSet);
        

        return refdata;
    }
    
    @Override
    public Map<String, InputFieldGroup> createFieldGroups(RuleInputCommand cmd) {
    	InputFieldGroupMap fieldMap = new InputFieldGroupMap();
    	
    	return fieldMap;
    }
    
    @Override
    public void postProcess(HttpServletRequest arg0, RuleInputCommand arg1, Errors arg2) {
        logger.debug("In SelectRuleTab post process");
        super.postProcess(arg0, arg1, arg2);

    }

    /**
     * Validates the input for the pressens of following :
     *  - RuleSet name
     *  - Rule Level
     *  - Sponsor or Institution
     *  - Study (if a study based rule)
     * @param cmd - The command object
     * @param commandBean  - A command wrapper
     * @param fieldGroups  - The fields
     * @param errors     - The errors object.
     */
    @Override
    public void validate(RuleInputCommand cmd, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        CreateRuleCommand command = (CreateRuleCommand) cmd;
        String level = command.getLevel();
        String ruleSetName = command.getRuleSetName();

        if(StringUtils.isEmpty(ruleSetName)){
           errors.rejectValue("ruleSetName", "RUL_010");
           return;
        }
        //for field level rules ignore further validations. 
        if(StringUtils.equals(ruleSetName,  RuleType.FIELD_LEVEL_RULES.getName())) {
            return;
        }

        if(StringUtils.isEmpty(level)){
           errors.rejectValue("level", "RUL_014");
           return;
        }

        if(command.isSponsorBased() && command.getSponsor() == null){
           errors.rejectValue("sponsor", "RUL_011");
        }
        if(command.isInstitutionBased() && command.getInstitution() == null){
           errors.rejectValue("institution", "RUL_013"); 
        }
        if(command.isStudyBased() && command.getStudy() == null){
           errors.rejectValue("study", "RUL_012");
        }

    }
    

}