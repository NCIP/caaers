package gov.nih.nci.cabig.caaers.domain.expeditedfields;

 
/**
 * The Enum ExpeditedReportSection.
 */
public enum ExpeditedReportSection {
    
    /** The BASIC s_ section. */
    BASICS_SECTION("Adverse Events", true), 
    
    /** The ADVERS e_ even t_ section. */
    ADVERSE_EVENT_SECTION("Adverse Events"),
    
    /** The REPORTE r_ inf o_ section. */
    REPORTER_INFO_SECTION("Reporter"),
    
    /** The RADIATIO n_ interventio n_ section. */
    RADIATION_INTERVENTION_SECTION("Radiation"),
    
    /** The AGENT s_ interventio n_ section. */
    AGENTS_INTERVENTION_SECTION("Agents", true),
    
    /** The SURGER y_ interventio n_ section. */
    SURGERY_INTERVENTION_SECTION("Surgery"),

    BEHAVIORAL_INTERVENTION_SECTION("Behavioral"),
    BIOLOGICAL_INTERVENTION_SECTION("Biological"),
    GENETIC_INTERVENTION_SECTION("Genetic"),
    DIETARY_INTERVENTION_SECTION("Dietary"),
    OTHER_AE_INTERVENTION_SECTION("Other"),

    /** The MEDICA l_ devic e_ section. */
    MEDICAL_DEVICE_SECTION("Device", true),
    
    /** The DESCRIPTIO n_ section. */
    DESCRIPTION_SECTION("Describe Event", true), 
    
    /** The STUD y_ interventions. */
    STUDY_INTERVENTIONS("Study Interventions", true),
    
    /** The MEDICA l_ inf o_ section. */
    MEDICAL_INFO_SECTION("Subject Details", true), 
    
    /** The TREATMEN t_ inf o_ section. */
    TREATMENT_INFO_SECTION("Course", true), 
    
    /** The LAB s_ section. */
    LABS_SECTION("Labs", true), 
    
    /** The PRIO r_ therapie s_ section. */
    PRIOR_THERAPIES_SECTION("Prior Therapies", true), 
    
    /** The PR e_ existin g_ conditio n_ section. */
    PRE_EXISTING_CONDITION_SECTION("Pre-existing Conditions", true), 
    
    /** The CONCOMITAN t_ medicatio n_ section. */
    CONCOMITANT_MEDICATION_SECTION("Conmeds"), 
    
    /** The OTHE r_ caus e_ section. */
    OTHER_CAUSE_SECTION("Other Causes"), 
    
    /** The ATTRIBUTIO n_ section. */
    ATTRIBUTION_SECTION("Attribution", true), 
    
    /** The ADDITIONA l_ inf o_ section. */
    ADDITIONAL_INFO_SECTION("Additional Info"), 
    
    /** The SUBMI t_ repor t_ section. */
    SUBMIT_REPORT_SECTION("Review & Submit", true), 
    
    /** The OUTCOM e_ section. */
    OUTCOME_SECTION("Outcome");

    /** The display name. */
    private String displayName;
    
    /** The associated to business rules. */
    private boolean associatedToBusinessRules;

    /**
     * Instantiates a new expedited report section.
     *
     * @param displayName the display name
     */
    private ExpeditedReportSection(String displayName) {
        this(displayName, false);
    }

    /**
     * Instantiates a new expedited report section.
     *
     * @param displayName the display name
     * @param associatedToBusinessRules the associated to business rules
     */
    private ExpeditedReportSection(String displayName, boolean associatedToBusinessRules) {
        this.displayName = displayName;
        this.associatedToBusinessRules = associatedToBusinessRules;
    }

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Checks if is associated to business rules.
     *
     * @return true, if is associated to business rules
     */
    public boolean isAssociatedToBusinessRules() {
        return associatedToBusinessRules;
    }

    /**
     * Gets the by display name.
     *
     * @param displayName the display name
     * @return the by display name
     */
    public static ExpeditedReportSection getByDisplayName(String displayName) {
        for (ExpeditedReportSection section : values()) {
            if (section.displayName.equals(displayName)) return section;
        }
        return null;
    }
}
