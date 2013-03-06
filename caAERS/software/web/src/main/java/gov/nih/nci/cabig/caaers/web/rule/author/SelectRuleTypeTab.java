/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.rule.author;

import com.semanticbits.rules.ui.RuleUi;
import gov.nih.nci.cabig.caaers.dao.NotificationDao;
import gov.nih.nci.cabig.caaers.dao.RuleSetDao;
import gov.nih.nci.cabig.caaers.dao.query.NotificationQuery;
import gov.nih.nci.cabig.caaers.dao.query.RuleSetQuery;
import gov.nih.nci.cabig.caaers.domain.Notification;
import gov.nih.nci.cabig.caaers.domain.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.RuleType;
import gov.nih.nci.cabig.caaers.validation.ValidationError;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
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
import java.util.List;
import java.util.Map;

/**
 * Represents the first tab while authoring Rules.
 * 
 * @author Sujith Vellat Thayyilthodi
 * @author Biju Joseph
 */
public class SelectRuleTypeTab extends TabWithFields<RuleInputCommand> {
    private static final Log logger = LogFactory.getLog(SelectRuleTypeTab.class);
    
    private RuleSetDao ruleSetDao;
    private NotificationDao notificationDao;

    public SelectRuleTypeTab() {
        super("Rule Type", "Rule Type", "rule/author/selectRuleType");
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
        ValidationErrors validationErrors = command.getCaaersRuleSet().validate();
        logger.info(validationErrors.toString());
        if(validationErrors.hasErrors()){
            if(validationErrors.containsErrorWithCode("RUL_025")){
                errors.rejectValue("caaersRuleSet.ruleTypeName", "RUL_025");
                return;
            }

            if(validationErrors.containsErrorWithCode("RUL_014")) errors.rejectValue("caaersRuleSet.ruleLevelName", "RUL_014");

            if(validationErrors.containsErrorWithCode("RUL_011")) {
                errors.rejectValue("caaersRuleSet.sponsor", "RUL_011");
                errors.rejectValue("caaersRuleSet.institution", "RUL_011");
            }
            if(validationErrors.containsErrorWithCode("RUL_012")) errors.rejectValue("caaersRuleSet.study", "RUL_012");
        }
        
        //check if there exist another rule-set ?
        if(!errors.hasErrors()){
            RuleSetQuery ruleSetQuery = new RuleSetQuery();
            ruleSetQuery.filterByRuleType(command.getCaaersRuleSet().getRuleType());
            if(command.getCaaersRuleSet().getRuleLevel() != null) ruleSetQuery.filterByRuleLevel(command.getCaaersRuleSet().getRuleLevel());
            if(command.getCaaersRuleSet().getOrganization() != null) ruleSetQuery.filterByOrganizationId(command.getCaaersRuleSet().getOrganization().getId());
            if(command.getCaaersRuleSet().getStudy() != null) ruleSetQuery.filterByStudyId(command.getCaaersRuleSet().getStudy().getId());
            if(command.getCaaersRuleSet().getId() != null) ruleSetQuery.ignoreRuleSetId(command.getCaaersRuleSet().getId());
            
            List<RuleSet>  ruleSets = (List<RuleSet>)ruleSetDao.search(ruleSetQuery);
            if(!ruleSets.isEmpty()){
                logger.error("Duplicate ruleset identified " + String.valueOf(ruleSets));
              errors.reject("RUL_026", "For the same input configuration, another rule set exist. Please edit that instead.");
            }

        }
    }

    @Override
    public void postProcess(HttpServletRequest request, RuleInputCommand command, Errors errors) {
        super.postProcess(request, command, errors);
        if(errors.hasErrors()) return;

        CreateRuleCommand createRuleCommand = (CreateRuleCommand) command;
        if(createRuleCommand.getCaaersRuleSet().getStudy() != null){
           createRuleCommand.setTerminology(createRuleCommand.getCaaersRuleSet().getStudy().getAeTerminology().getTerm().getDisplayName());
        }

        if(StringUtils.equals(createRuleCommand.getCaaersRuleSet().getRuleTypeName(), RuleType.SAFETY_SIGNALLING_RULES.getName())){
            createRuleCommand.setRuleUi((RuleUi)request.getSession(true).getServletContext().getAttribute("ruleUi-safety"));
        }else{
            createRuleCommand.setRuleUi((RuleUi)request.getSession(true).getServletContext().getAttribute("ruleUi-general"));
        }

    }

    @Override
    public Map<String, InputFieldGroup> createFieldGroups(RuleInputCommand cmd) {
        InputFieldGroupMap fieldMap = new InputFieldGroupMap();

        return fieldMap;
    }

    public RuleSetDao getRuleSetDao() {
        return ruleSetDao;
    }

    public void setRuleSetDao(RuleSetDao ruleSetDao) {
        this.ruleSetDao = ruleSetDao;
    }

    public NotificationDao getNotificationDao() {
        return notificationDao;
    }

    public void setNotificationDao(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }
}
