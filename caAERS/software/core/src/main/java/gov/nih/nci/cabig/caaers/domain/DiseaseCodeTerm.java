package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * This class represents the DiseaseCodeTerm domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
public enum DiseaseCodeTerm implements CodedEnum<Integer> {

    /** The CTEP. */
    CTEP(1, "CTEP"), /** The MEDDRA. */
 MEDDRA(2, "MedDRA"), /** The OTHER. */
 OTHER(3, "Other - Specify");

    /** The code. */
    private int code;
    
    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new disease code term.
     *
     * @param code the code
     */
    DiseaseCodeTerm(int code) {
        this(code, null);
    }

    /**
     * Instantiates a new disease code term.
     *
     * @param code the code
     * @param longName the long name
     */
    DiseaseCodeTerm(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static DiseaseCodeTerm getByCode(int code) {
        return getByClassAndCode(DiseaseCodeTerm.class, code);
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
        return displayName == null ? sentenceCasedName(this) : displayName;
    }
}
