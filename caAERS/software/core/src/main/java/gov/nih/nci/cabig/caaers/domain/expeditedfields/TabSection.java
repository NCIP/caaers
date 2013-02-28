/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.expeditedfields;

 
/**
 * The Enum TabSection.
 *
 * @author Ion C. Olaru
 */
public enum TabSection {
    
    /** The CAPTUR e_ a e_ ta b_ section. */
    CAPTURE_AE_TAB_SECTION("Capture AE"),
    
    /** The COURS e_ cycl e_ section. */
    COURSE_CYCLE_SECTION("Course Cycle");

    /** The display name. */
    private String displayName;
    
    /** The associated to business rules. */
    private boolean associatedToBusinessRules;

    /**
     * Instantiates a new tab section.
     *
     * @param displayName the display name
     */
    private TabSection(String displayName) {
        this(displayName, false);
    }

    /**
     * Instantiates a new tab section.
     *
     * @param displayName the display name
     * @param associatedToBusinessRules the associated to business rules
     */
    private TabSection(String displayName, boolean associatedToBusinessRules) {
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

}
