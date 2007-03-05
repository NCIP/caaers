package gov.nih.nci.cabig.caaers.rules.runtime;


public enum Global {

	RULE_CONTEXT("ruleContext", "", "Contextual Information which is shared across all the Rules being fired."),
	ACTION_DISPATCHER("actionDispatcher", "", "");

	private String code;
	private String displayResourceUri;
	private String description;
	
	Global(String code) {
		this(code, "","");
	}
	
	Global(String code, String displayResourceUri) {
		this(code, "",displayResourceUri);
	}
	
	Global(String code, String displayResourceUri, String description) {
		this.code = code;
		this.displayResourceUri = displayResourceUri;
		this.description = description;
	}
	
	public String getCode() {
		return this.code;
	}

	public String getDescription() {
		return description;
	}

	public String getDisplayResourceUri() {
		return displayResourceUri;
	}
	
}
