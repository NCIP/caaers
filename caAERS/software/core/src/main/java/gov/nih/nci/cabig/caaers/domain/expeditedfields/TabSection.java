package gov.nih.nci.cabig.caaers.domain.expeditedfields;

/**
 * @author Ion C. Olaru
 */
public enum TabSection {
    CAPTURE_AE_TAB_SECTION("Capture AE"),
    COURSE_CYCLE_SECTION("Course Cycle");

    private String displayName;
    private boolean associatedToBusinessRules;

    private TabSection(String displayName) {
        this(displayName, false);
    }

    private TabSection(String displayName, boolean associatedToBusinessRules) {
        this.displayName = displayName;
        this.associatedToBusinessRules = associatedToBusinessRules;
    }

    public String getDisplayName() {
        return displayName;
    }

}