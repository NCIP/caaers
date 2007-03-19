package gov.nih.nci.cabig.caaers.web.rule.author;

import gov.nih.nci.cabig.caaers.web.rule.DefaultTab;

public class ReviewTab extends DefaultTab {

	public ReviewTab(String longTitle, String shortTitle, String viewName) {
		super(longTitle, shortTitle, viewName);
	}

	public ReviewTab(){
		super("Review and Submit", "Review", "rule/review");
	}
}
