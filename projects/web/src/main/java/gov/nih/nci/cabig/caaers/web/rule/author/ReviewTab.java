package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.rules.brxml.Column;
import gov.nih.nci.cabig.caaers.rules.brxml.ReadableRule;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.RuleUtil;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReviewTab extends DefaultTab {

    public ReviewTab(String longTitle, String shortTitle, String viewName) {
        super(longTitle, shortTitle, viewName);
    }

    public ReviewTab() {
        super("Review and Submit", "Review", "rule/author/review");
    }

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

        return super.referenceData(command);

    }
}
