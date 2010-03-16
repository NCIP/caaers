package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.web.ae.CaptureAdverseEventInputCommand;
import gov.nih.nci.cabig.caaers.web.ae.ReportingPeriodCommand;
import gov.nih.nci.cabig.caaers.web.fields.DefaultInputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroupMap;
import gov.nih.nci.cabig.caaers.web.fields.TabWithFields;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.validation.Errors;

/**
 * Represents the first tab while authoring Rules.
 * 
 * @author Sujith Vellat Thayyilthodi
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

    @Override
    public void validate(RuleInputCommand cmd, BeanWrapper commandBean, Map<String, InputFieldGroup> fieldGroups, Errors errors) {
        CreateRuleCommand command = (CreateRuleCommand) cmd;
        String level = command.getLevel();
        String ruleSetName = command.getRuleSetName();
        
        if(StringUtils.isEmpty(ruleSetName)){
           errors.rejectValue("ruleSetName", "RUL_010");
        }else if(!StringUtils.equals(ruleSetName, RuleType.FIELD_LEVEL_RULES.getName())){

            if(level == null || level.equals("")){
                errors.rejectValue("level", "RUL_014");
            }else{
                if (level.equals(RuleLevel.Sponsor.getName())) {
                    if (command.getSponsorName().trim().equals("")) {
                        errors.rejectValue("sponsorName", "RUL_011");
                    }
                } else if (level.equals(RuleLevel.SponsorDefinedStudy.getName())) {
                    if (command.getSponsorName().trim().equals("")) {
                        errors.rejectValue("sponsorName", "RUL_011");
                    }
                    if (command.getCategoryIdentifier().trim().equals("")) {
                        errors.rejectValue("categoryIdentifier", "RUL_012");
                    }
                } else if (level.equals(RuleLevel.Institution.getName())) {
                    if (command.getInstitutionName().trim().equals("")) {
                        errors.rejectValue("institutionName", "RUL_013");
                    }
                } else if (level.equals(RuleLevel.InstitutionDefinedStudy.getName())) {
                    if (command.getInstitutionName().trim().equals("")) {
                        errors.rejectValue("institutionName", "RUL_013");
                    }
                    if (command.getCategoryIdentifier().trim().equals("")) {
                        errors.rejectValue("categoryIdentifier", "RUL_012");
                    }
               }
            }

        }
    }
    

}