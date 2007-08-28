package gov.nih.nci.cabig.caaers.domain.expeditedfields;

public enum ExpeditedReportSection {
	BASICS_SECTION("Basic AE information"),
	BASICS_MEDRA_SECTION("Basic AE information"),
	ADVERSE_EVENT_SECTION("Adverse events"),
	REPORTER_INFO_SECTION("Reporer Information"),
	CHECKPOINT_SECTION("Checkpoint"),
	RADIATION_INTERVENTION_SECTION("Radiation Intervention"),
	SURGERY_INTERVENTION_SECTION("Surgery Intervention"),
	MEDICAL_DEVICE_SECTION("Medical Device"),
	DESCRIPTION_SECTION("Description"),
	MEDICAL_INFO_SCECTION("Medical"),
	TREATMENT_INFO_SECTION("Treatment"),
	LABS_SECTION("Labs"),
	PRIOR_THERAPIES_SECTION("Therapies"),
	PRE_EXISTING_CONDITION_SECTION( "Pre-Existing Conditions"),
	CONCOMITANT_MEDICATION_SECTION("Concomitant medications"),
	OTHER_CAUSE_SECTION("Other contributing causes"),
	ATTRIBUTION_SECTION("Attribution"),
	ADDITIONAL_INFO_SECTION("Additional Information"),
	SUBMIT_REPORT_SECTION("Submit Report"),
	SUBMITTER_SECTION("Submitter info")
	;
	private String displayName;

	private ExpeditedReportSection(String displayName){
		this.displayName = displayName;
	}
	public String displayName() {
		return displayName;
	}

	public ExpeditedReportSection getByDisplayName(String displayName){
		for(ExpeditedReportSection section : values()){
			if(section.displayName.equals(displayName)) return section;
		}
		return null;
	}
}
