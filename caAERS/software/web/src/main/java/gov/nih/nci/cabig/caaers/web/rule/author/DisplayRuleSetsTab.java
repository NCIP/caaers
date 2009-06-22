package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.rules.business.service.CaaersRulesEngineService;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;

import com.semanticbits.rules.brxml.Column;
import com.semanticbits.rules.brxml.ReadableRule;
import com.semanticbits.rules.brxml.Rule;
import com.semanticbits.rules.brxml.RuleSet;
import com.semanticbits.rules.utils.RuleUtil;

/*
 * This class is used for displaying the rule sets avaiable based on the user selection. User can choose sponsor, intitution or study.
 * 
 *  @version 1.0
 *  @author Visu Patlolla
 */
public class DisplayRuleSetsTab extends DefaultTab {
    private static final Log logger = LogFactory.getLog(DisplayRuleSetsTab.class);

    public DisplayRuleSetsTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    public DisplayRuleSetsTab() {
        super("Rule Set", "Rule Set", "rule/author/displayRuleSets");
    }

    /*
     * This method retrieves the rule sets based on the Rule Level or Rule Level and Study name.
     * 
     */
    @Override
    public Map<String, Object> referenceData(RuleInputCommand command) {
        CreateRuleCommand createRuleCommand = ((CreateRuleCommand) command);
        
        
        RuleSet rs = createRuleCommand.getRuleSet();

        List<Rule> rules = new ArrayList<Rule>();

        int count = 1;
        for (Rule rule : rs.getRule()) {
            if (!rule.isMarkedDelete()) {
                ReadableRule readable = new ReadableRule();
                List<String> line = new ArrayList<String>();

                // add lines..
                line.add("IF");
                for (Column column : rule.getCondition().getColumn()) {
                    // skip rule type filters
                    if (!column.getExpression().equals(
                                    "getPrimaryFundingSponsorOrganization().getName()")
                                    && !column
                                                    .getObjectType()
                                                    .equals(
                                                                    "gov.nih.nci.cabig.caaers.rules.common.AdverseEventEvaluationResult")
                                    && !column.isMarkedDelete()) {
                        line.add("	" + RuleUtil.readableColumn(column));
                        line.add("AND");
                    }

                }
                line.remove(line.size() - 1);
                readable.setLine(line);

                rule.getMetaData().setName("Rule-" + count);
                rule.setReadableRule(readable);

                List<String> readableActions = new ArrayList<String>();
                if (rs.getDescription().equals("Mandatory Sections Rules")) {
                    for (String action : rule.getAction()) {
                        readableActions
                                        .add(ExpeditedReportSection.valueOf(action)
                                                        .getDisplayName());
                    }
                } else {
                    readableActions = rule.getAction();
                }

                rule.setReadableAction(readableActions);
                rules.add(rule);
                count++;
            }
        }
        rs.setRule(rules);
        createRuleCommand.setRuleSet(rs);
        
        

        List<RuleSet> ruleSets = createRuleCommand.getExistingRuleSets();

        // Use RuleEngineService to retrieve all the RuleSets

        try {

            CaaersRulesEngineService caaersRulesEngineService = createRuleCommand.getCaaersRulesEngineService();

            if (CreateRuleCommand.SPONSOR_LEVEL.equals(createRuleCommand.getLevel())) {
                ruleSets = caaersRulesEngineService.getAllRuleSetsForSponsor(createRuleCommand
                                .getSponsorName());
            } else if (CreateRuleCommand.SPONSOR_DEFINED_STUDY_LEVEL.equals(createRuleCommand
                            .getLevel())) {
                ruleSets = caaersRulesEngineService.getAllRuleSetsForSponsorDefinedStudy(
                                createRuleCommand.getCategoryIdentifier(), createRuleCommand
                                                .getSponsorName());
            } else if (CreateRuleCommand.INSTITUTIONAL_LEVEL.equals(createRuleCommand.getLevel())) {
                ruleSets = caaersRulesEngineService.getAllRuleSetsForInstitution(createRuleCommand
                                .getInstitutionName());
            } else if (CreateRuleCommand.INSTITUTION_DEFINED_STUDY_LEVEL.equals(createRuleCommand
                            .getLevel())) {
                ruleSets = caaersRulesEngineService.getAllRuleSetsForInstitutionDefinedStudy(
                                createRuleCommand.getCategoryIdentifier(), createRuleCommand
                                                .getInstitutionName());
            }

        } catch (Exception ex) {
            logger.error("Exception while retrieving rule sets", ex);
            // REVISIT: Create meaningful error message
        }

        createRuleCommand.setExistingRuleSets(ruleSets);

        return super.referenceData(createRuleCommand);
    }

    @Override
    public void postProcess(HttpServletRequest request, RuleInputCommand command, Errors errors) {
        // Make sure that user has selected a RuleSet
        // CreateRuleCommand createRuleCommand = (CreateRuleCommand) command;

    }

    @Override
    public void validate(RuleInputCommand cmd, Errors errors) {

        CreateRuleCommand command = (CreateRuleCommand) cmd;
        if (command != null) {
            String ruleSetName = command.getRuleSetName();
            System.out.println("RULE SET NAME :" + ruleSetName + ":");
            if (ruleSetName == null || (ruleSetName != null && ruleSetName.trim().equals(""))) {
                errors.reject("RUL_010", "Missing Rule Set");
            }
        }
    }

}
