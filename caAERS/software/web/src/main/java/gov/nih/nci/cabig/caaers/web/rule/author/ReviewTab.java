package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.RuleInputCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.semanticbits.rules.brxml.Column;
import com.semanticbits.rules.brxml.ReadableRule;
import com.semanticbits.rules.brxml.Rule;
import com.semanticbits.rules.brxml.RuleSet;
import com.semanticbits.rules.utils.RuleUtil;

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

        // Retrieve RuleSet based on the one chosen by the user.
        createRuleCommand.retrieveRuleSet();

        return super.referenceData(command);

    }
}
