/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * The Enum INDType.
 */
public enum INDType implements CodedEnum<Integer> {
    
    /** The NA. */
    NA(0, "NA"), 
    /** The N a_ commercial. */
    NA_COMMERCIAL(3, "N/A-Commercial Agent"),
    /** The IN d_ exempt. */
    IND_EXEMPT(4, "IND-Exempt"),
    /** The DC p_ ind. */
    DCP_IND(5, "DCP IND"),
    /** The OTHER. */
    OTHER(2, "Other IND Holder"),
    /** The CTE p_ ind. */
    CTEP_IND(1, "CTEP IND");

    /** The code. */
    private int code;

    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new iND type.
     *
     * @param code the code
     * @param displayName the display name
     */
    private INDType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getCode()
     */
    public Integer getCode() {
        return code;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getDisplayName()
     */
    public String getDisplayName() {
        return displayName;
    }

}
