/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.report;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * The Enum ReportFormat.
 */
public enum ReportFormat implements CodedEnum<Integer> {
    
    /** The TEXT. */
    TEXT("Text"), 
 /** The XML. */
 XML("XML"), 
 /** The PDF. */
 PDF("PDF");
    
    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new report format.
     *
     * @param displayName the display name
     */
    private ReportFormat(String displayName) {
        this.displayName = displayName;
        register(this);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getDisplayName()
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the display name.
     *
     * @param displayName the new display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.caaers.domain.CodedEnum#getCode()
     */
    public Integer getCode() {
        return ordinal();
    }

    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static ReportFormat getByCode(int code) {
        return getByClassAndCode(ReportFormat.class, code);
    }
}
