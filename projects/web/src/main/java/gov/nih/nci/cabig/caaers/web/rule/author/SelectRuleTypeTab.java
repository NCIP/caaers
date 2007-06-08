package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;

import java.util.Map;

/**
 * Represents the first tab while authoring Rules.
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class SelectRuleTypeTab extends DefaultTab 
{

	public SelectRuleTypeTab(String longTitle, String shortTitle, String viewName) {
		super(longTitle, shortTitle, viewName);
	}

	public SelectRuleTypeTab() {
        super("Select Rule Level", "Select Rule Level", "rule/author/selectRuleLevel");
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
}