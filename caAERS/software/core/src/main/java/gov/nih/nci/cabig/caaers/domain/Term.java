package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * This class represents the Term domain object associated with the Adverse event report.
 * 
 * @author Krikor Krumlian
 */
public enum Term implements CodedEnum<Integer> {
    
    /** The CTC. */
    CTC(1, "CTCAE"), 
 /** The MEDDRA. */
 MEDDRA(2, "MedDRA");

    /** The code. */
    private int code;

    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new term.
     *
     * @param code the code
     */
    Term(int code) {
        this(code, null);
    }

    /**
     * Instantiates a new term.
     *
     * @param code the code
     * @param longName the long name
     */
    Term(int code, String longName) {
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
    public static Term getByCode(int code) {
        return getByClassAndCode(Term.class, code);
    }
    
    /**
     * The same as {@link Enum#valueOf}, but returns null instead of throwing {@link IllegalArgumentException}.
     *
     * @param name the name
     * @return the by name
     */
    public static Term getByName(String name) {
        try {
			return valueOf(name);
		} catch (IllegalArgumentException e) {
			return null;
		}
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
