package gov.nih.nci.cabig.caaers.domain.expeditedfields;

public enum ExpeditedReportSection {

	ADVERSE_EVENT_SECTION("Adverse events", "Adverse Events"),
	REPORTER_INFO_SECTION("Reporter info", "Reporter Info"),
	CHECKPOINT_SECTION("Checkpoint", "Checkpoint"),
	RADIATION_INTERVENTION_SECTION("Radiation intervention","Radiation Intervention"),
	SURGERY_INTERVENTION_SECTION("Surgery intervention","Surgery Intervention"),
	MEDICAL_DEVICE_SECTION("Medical device", "Medical Device"),
	DESCRIPTION_SECTION("Event and response description", "Description"),
	MEDICAL_INFO_SCECTION("Medical info", "Medical Info"),
	TREATMENT_INFO_SECTION("Treatment information","Treatment Information")
	;
	private String section;
	private String tabTitle;

	private ExpeditedReportSection(String section, String tabTitle){
		this.section = section;
		this.tabTitle = tabTitle;
	}
	public String section() {
		return section;
	}
	public String tabTitle() {
		return tabTitle;
	}
}
