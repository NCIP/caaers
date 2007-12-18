package gov.nih.nci.cabig.caaers.domain.expeditedfields;


public enum ExpeditedReportSection {
    BASICS_SECTION("Enter AEs"),
    ADVERSE_EVENT_SECTION("Adverse events"),
    REPORTER_INFO_SECTION("Reporter"),
    CHECKPOINT_SECTION("Select Report"),
    RADIATION_INTERVENTION_SECTION("Radiation"),
    SURGERY_INTERVENTION_SECTION("Surgery"),
    MEDICAL_DEVICE_SECTION("Device"),
    DESCRIPTION_SECTION("Describe Event"),
    MEDICAL_INFO_SECTION("Patient Details"),
    TREATMENT_INFO_SECTION("Course and Agent"),
    LABS_SECTION("Labs"),
    PRIOR_THERAPIES_SECTION("Prior Therapies"),
    PRE_EXISTING_CONDITION_SECTION("Pre-existing Conditions"),
    CONCOMITANT_MEDICATION_SECTION("Conmeds"),
    OTHER_CAUSE_SECTION("Other Causes"),
    ATTRIBUTION_SECTION("Attribution"),
    ADDITIONAL_INFO_SECTION("Attachments"),
    SUBMIT_REPORT_SECTION("Submit"),
    OUTCOME_SECTION("Outcome")
    ;
    
    private String displayName;

    private ExpeditedReportSection(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ExpeditedReportSection getByDisplayName(String displayName) {
        for (ExpeditedReportSection section : values()) {
            if (section.displayName.equals(displayName)) return section;
        }
        return null;
    }
}
