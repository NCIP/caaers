package gov.nih.nci.cabig.caaers.web.rule.author;

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
 * */
public class SelectRuleTypeTab extends DefaultTab 
{
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
    public Map<String, Object> referenceData() 
    {
        Map<String, Object> refdata = super.referenceData();
        
        return refdata;
    }

	@Override
	public void postProcess(HttpServletRequest arg0, RuleInputCommand arg1, Errors arg2)
	{
		logger.debug("In SelectRuleTab post process");
		super.postProcess(arg0, arg1, arg2);
		
	}
    
    
}