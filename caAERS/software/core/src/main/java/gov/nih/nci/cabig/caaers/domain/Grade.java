package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * The Enum Grade.
 *
 * @author Rhett Sutphin
 */
public enum Grade implements CodedEnum<Integer>, CodedGrade {
    
    /** The NO t_ evaluated. */
    NOT_EVALUATED(-1, "Not evaluated"),
    
    /** The NORMAL. */
    NORMAL(0, "Normal"),
    
    /** The MILD. */
    MILD(1, "Mild; asymptomatic or mild symptoms; clinical or diagnostic observations only; intervention not indicated."),
    
    /** The MODERATE. */
    MODERATE(2, "Moderate; minimal, local or noninvasive intervention indicated; limiting age-appropriate instrumental ADL."),
    
    /** The SEVERE. */
    SEVERE(3, "Severe or medically significant but not immediately life-threatening; hospitalization or prolongation of hospitalization indicated; disabling; limiting self care ADL."),
    
    /** The LIF e_ threatening. */
    LIFE_THREATENING(4, "Life-threatening consequences; urgent intervention indicated."),
    
    /** The DEATH. */
    DEATH(5, "Death related to AE.");

    /** The code. */
    private Integer code;

    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new grade.
     *
     * @param code the code
     */
    Grade(Integer code) {
        this(code, null);
    }

    /**
     * Instantiates a new grade.
     *
     * @param code the code
     * @param longName the long name
     */
    Grade(Integer code, String longName) {
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
    public static Grade getByCode(int code) {
        return getByClassAndCode(Grade.class, code);
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

    // for bean-property access
    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.CodedGrade#getName()
     */
    public String getName() {
        return name();
    }

    // ditto
    /**
     * Gets the string.
     *
     * @return the string
     */
    public String getString() {
        return toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return toStringHelper(this);
    }
}
