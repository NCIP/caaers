package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * This enumeration represents the Therapies.
 *
 * @author Krikor Krumlian
 */
public enum OutcomeType implements CodedEnum<Integer> {
	DEATH(1, "Death"), 
	LIFE_THREATENING(2, "Life-threatening"),
	HOSPITALIZATION(3, "Hospitalization - intial or prolonged"),
	DISABILITY(4, "Disability or Permanent Damage"),
	CONGENITAL_ANOMALY(5, "Congenital Anomaly/Birth Defect"),
	OTHER_SERIOUS(6, "Other Serious (Important Medical Events)"),
	REQUIRED_INTERVENTION(7, "Required Intervention to Prevent Permanent Impairment/Damage (Devices)");
	
	private String studyTherapyType;

	private int code;

	private OutcomeType(final int code, final String studyTherapyType) {
		this.code = code;
		this.studyTherapyType = studyTherapyType;
		register(this);

	}

	public String getStudyTherapyType() {
		return studyTherapyType;
	}

	public String getDisplayName() {
		return studyTherapyType;
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
