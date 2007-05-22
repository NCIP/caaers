package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents the first tab while authoring Rules.
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class SelectRuleTypeTab extends DefaultTab 
{

    private static final String RULESET_FIELD_GROUP = "ruleset";
    private static final String TRIGGER_LEVEL_FIELD_GROUP = "triggerLevel";

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
        
        // VP: This is commeted out as we do not need categories
        //refdata.put("ruleLevels", getCategories());
        return refdata;
    }
    
    //REVISIT! We do not need this method
    // @deprecated
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