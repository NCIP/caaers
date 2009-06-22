package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.common.RuleLevel;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

/**
 * Represents the first tab while authoring Rules.
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class SelectRuleTypeTab extends DefaultTab {
    private static final Log logger = LogFactory.getLog(SelectRuleTypeTab.class);

    public SelectRuleTypeTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    public SelectRuleTypeTab() {
        super("Rule Level", "Rule Level", "rule/author/selectRuleLevel");
    }

    @Override
    protected void initFields() {

    }

    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();

        return refdata;
    }

    @Override
    public void postProcess(HttpServletRequest arg0, RuleInputCommand arg1, Errors arg2) {
        logger.debug("In SelectRuleTab post process");
        super.postProcess(arg0, arg1, arg2);

    }

    @Override
    public void validate(RuleInputCommand cmd, Errors errors) {

        CreateRuleCommand command = (CreateRuleCommand) cmd;
        if (command != null) {
            String level = command.getLevel();
            if (level != null) {
                if (level.equals(RuleLevel.Sponsor.getName())) {
                    if (command.getSponsorName().trim().equals("")) {
                        errors.reject("RUL_011", "Missing Sponsor");
                    }
                } else if (level.equals(RuleLevel.SponsorDefinedStudy.getName())) {
                    if (command.getSponsorName().trim().equals("")) {
                        errors.reject("RUL_011", "Missing Sponsor");
                    }
                    if (command.getCategoryIdentifier().trim().equals("")) {
                        errors.reject("RUL_012", "Missing Study");
                    }
                } else if (level.equals(RuleLevel.Institution.getName())) {
                    System.out.println("inst:" + command.getInstitutionName() + ":");
                    if (command.getInstitutionName().trim().equals("")) {
                        errors.reject("RUL_013", "Missing Instiution");
                    }
                } else if (level.equals(RuleLevel.InstitutionDefinedStudy.getName())) {
                    if (command.getInstitutionName().trim().equals("")) {
                        errors.reject("RUL_013", "Missing Instiution");
                    }
                    if (command.getCategoryIdentifier().trim().equals("")) {
                        errors.reject("RUL_012", "Missing Study");
                    }
                }
            }
        }

    }

}