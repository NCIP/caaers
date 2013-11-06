/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * The Enum ReportFormatType.
 */
public enum ReportFormatType implements CodedEnum<Integer> {

    /** The CAAERSXML. */
    CAAERSXML(1, "CaAERS XML"), 
    
    /** The ADEERSPDF. */
    ADEERSPDF(2, "AdEERS Expedited Adverse Event Report"), 
    
    /** The MEDWATCHPDF. */
    MEDWATCHPDF(3, "Medwatch Form FDA 3500A"), 
    
    /** The DCPSAEFORM. */
    DCPSAEFORM(4, "DCP SAE Form"), 
    
    /** The CIOMSFORM. */
    CIOMSFORM(5, "CIOMS Form"), 
    
    /** The CIOMSSAEFORM. */
    CIOMSSAEFORM(6, "CIOMS SAE Form"),
    
    /** The CUSTO m_ report. */
    CUSTOM_REPORT(7, "caAERS Custom Report PDF"),
    
    /** E2B Format */
    E2BXML(8, "E2B XML");

    /** The code. */
    private int code;

    /** The display name. */
    private String displayName;
    
    /**
     * Instantiates a new report format type.
     *
     * @param code the code
     */
    ReportFormatType(int code) {
        this(code, null);
    }
    
    /**
     * Instantiates a new report format type.
     *
     * @param code the code
     * @param displayName the display name
     */
    ReportFormatType(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
        register(this);
    }

    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static ReportFormatType getByCode(int code) {
        return getByClassAndCode(ReportFormatType.class, code);
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
        if (displayName == null) {
            return sentenceCasedName(this);
        } else {
            return displayName;
        }
    }

    // for bean-property access
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name();
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    public String toString() {
        return toStringHelper(this);
    }
}
