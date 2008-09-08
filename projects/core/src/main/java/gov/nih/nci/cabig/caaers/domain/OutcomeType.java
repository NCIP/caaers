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
	DEATH(1, "Death"), 
	HOSPITALIZATION(3,"Hospitalization - initial or prolonged"),
	LIFE_THREATENING(2, "Life-threatening"), 
	DISABILITY(4,"Disability or Permanent Damage"), 
	CONGENITAL_ANOMALY(5,"Congenital Anomaly/Birth Defect"), 
	REQUIRED_INTERVENTION(7,"Required Intervention to Prevent Permanent Impairment/Damage (Devices)"),
	OTHER_SERIOUS(6,"Other Serious (Important Medical Events)");

    private String displayName;

    private int code;

    private OutcomeType(final int code, final String displayName) {
        this.code = code;
        this.displayName= displayName;
        register(this);

    }

    public String getDisplayName() {
        return displayName;
    }

    public String getName() {
        return name();
    }

    public Integer getCode() {
    	return code;
    }

    public static OutcomeType getByCode(final int code) {
        return getByClassAndCode(OutcomeType.class, code);
    }

}
