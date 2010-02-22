package gov.nih.nci.cabig.caaers.domain.expeditedfields;


/**
 * @author Sameer Sawant
 * @author Ion C. Olaru
 * This enum enlists all the tabs in the caaers UI, whose fields are configurable.
 */
public enum CaaersTab {
	CAPTURE_ADVERSE_EVENTS_TAB("CaptureAdverseEventTab", "Capture adverse events"),
    COURSE_TAB("CourseCycleTab", "Create Course/Cycle");

	private String displayName;
	private String className;
	
	private CaaersTab(String className, String displayName){
		this.displayName = displayName;
		this.className = className;
	}
	
	public String getClassName(){
		return className;
	}
	
	public String getDisplayName(){
		return displayName;
	}
	
	public static CaaersTab getByDisplayName(String displayName) {
        for (CaaersTab tab : values()) {
            if (tab.displayName.equals(displayName)) return tab;
        }
        return null;
    }
	
	public static CaaersTab getByClassName(String className){
		for(CaaersTab tab: values()){
			if(tab.getClassName().equals(className)) return tab;
		}
		return null;
	}
}