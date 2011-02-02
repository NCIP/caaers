package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * This enumeration represents the outcomes which are possible for seriousness indicator.
 * 
 * @author Krikor Krumlian
 * @author Biju Joseph
 */
public enum OutcomeType implements CodedEnum<Integer> {
	
	/** The DEATH. */
	DEATH(1, "Death"), 
	
	/** The HOSPITALIZATION. */
	HOSPITALIZATION(3,"Hospitalization - initial or prolonged"),
	
	/** The LIF e_ threatening. */
	LIFE_THREATENING(2, "Life-threatening"), 
	
	/** The DISABILITY. */
	DISABILITY(4,"Disability or Permanent Damage"), 
	
	/** The CONGENITA l_ anomaly. */
	CONGENITAL_ANOMALY(5,"Congenital Anomaly/Birth Defect"), 
	
	/** The REQUIRE d_ intervention. */
	REQUIRED_INTERVENTION(7,"Required Intervention to Prevent Permanent Impairment/Damage (Devices)"),
	
	/** The OTHE r_ serious. */
	OTHER_SERIOUS(6,"Other Serious (Important Medical Events)");

    /** The display name. */
    private String displayName;

    /** The code. */
    private int code;

    /**
     * Instantiates a new outcome type.
     *
     * @param code the code
     * @param displayName the display name
     */
    private OutcomeType(final int code, final String displayName) {
        this.code = code;
        this.displayName= displayName;
        register(this);

    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getDisplayName()
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getCode()
     */
    public Integer getCode() {
    	return code;
    }

    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static OutcomeType getByCode(final int code) {
        return getByClassAndCode(OutcomeType.class, code);
    }

}
