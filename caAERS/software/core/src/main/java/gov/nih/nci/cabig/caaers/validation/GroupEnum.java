package gov.nih.nci.cabig.caaers.validation;

public enum GroupEnum {
	
	AdverseEventGroup(AdverseEventGroup.class, CaaersFieldConfigurationManager.AE_FIELD_GROUP),
	CourseCycleGroup(CourseCycleGroup.class,  CaaersFieldConfigurationManager.COURSE_FIELD_GROUP);
	
	Class group;
	
	String tabName;

	private GroupEnum(Class group, String tabName) {
		this.group = group;
		this.tabName = tabName;
	}

	public Class getGroup() {
		return group;
	}

	public void setGroup(Class group) {
		this.group = group;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
}
