package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.web.rule.AutocompleterField;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTextArea;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the first tab while authoring Rules.
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class SelectRueTypeTab extends DefaultTab {

    private static final String RULESET_FIELD_GROUP = "ruleset";
    private static final String TRIGGER_LEVEL_FIELD_GROUP = "triggerLevel";

	public SelectRueTypeTab(String longTitle, String shortTitle, String viewName) {
		super(longTitle, shortTitle, viewName);
	}

	public SelectRueTypeTab() {
        super("Select Rule Level", "Rule Level", "rule/author/selectRuleLevel");
	}

    @Override
    protected void initFields() {

    }


    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();

        refdata.put("ruleLevels", getCategories());
        return refdata;
    }
    
    private List<Category> getCategories() {
    	List<Category> list = new ArrayList<Category>();
		Category category = new Category();
		MetaData metaData = new MetaData();
		category.setPath("/");
		metaData.setName("Sponsor");
		metaData.setDescription("Sponsor Level Triggers are registered under this category");
		category.setMetaData(metaData);
        list.add(category);
        
        category = new Category();
		metaData = new MetaData();
		category.setPath("Sponsor");
		metaData.setName("Institution");
		metaData.setDescription("Institutional Level Triggers are registered under this category");
		category.setMetaData(metaData);
        list.add(category);        

        category = new Category();
		metaData = new MetaData();
		category.setPath("Institution");
		metaData.setName("Study");
		metaData.setDescription("Study Level Triggers are registered under this category");
		category.setMetaData(metaData);
        list.add(category);
        
        return list;
    }
    
}