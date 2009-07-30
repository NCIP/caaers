package gov.nih.nci.cabig.caaers.web.admin;

import java.util.Map;

import gov.nih.nci.cabig.ctms.web.tabs.Tab;

public class ImportReviewTab extends Tab<ImportCommand>{
	
	public ImportReviewTab(String longTitle, String shortTitle, String viewName){
		super(longTitle, shortTitle, viewName);
	}
	
    @Override
    public Map<String, Object> referenceData() {
        Map<String, Object> refdata = super.referenceData();
        // refdata.put("action", "New");
        return refdata;
    }

}
