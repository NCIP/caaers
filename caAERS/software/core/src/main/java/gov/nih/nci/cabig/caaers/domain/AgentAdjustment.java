package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * The Enum AgentAdjustment.
 */
public enum AgentAdjustment implements CodedEnum<Integer> {
    
    /** The DOS e_ increased. */
    DOSE_INCREASED(1, "Dose increased"), 
 /** The DOS e_ notchanged. */
 DOSE_NOTCHANGED(2, "Dose not changed"), 
 /** The DOS e_ reduced. */
 DOSE_REDUCED(3, "Dose reduced"), 
 /** The DRU g_ withdrawn. */
 DRUG_WITHDRAWN(4, "Drug withdrawn"), 
 /** The NA. */
 NA(5, "Not applicable");
 
    /** The code. */
    private int code;

    /** The display name. */
    private String displayName;

    /**
     * Instantiates a new agent adjustment.
     *
     * @param code the code
     */
    AgentAdjustment(int code) {
        this(code, null);
    }

    /**
     * Instantiates a new agent adjustment.
     *
     * @param code the code
     * @param longName the long name
     */
    AgentAdjustment(int code, String longName) {
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
    public static AgentAdjustment getByCode(int code) {
        return getByClassAndCode(AgentAdjustment.class, code);
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
