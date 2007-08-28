package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * This enumeration represents the status of an EmailNotificaiton.
 * 
 * @author Biju Joseph
 */
public enum StudyTherapyType implements CodedEnum<Integer> {
	DRUG_ADMINISTRATION(1, "Drug Administration"), RADIATION(2, "Radiation"), SURGERY(3, "Surgery"), DEVICE(4, "Device");

	private String studyTherapyType;

	private int code;

	private StudyTherapyType(final int code, final String studyTherapyType) {
		this.code = code;
		this.studyTherapyType = studyTherapyType;
		register(this);

	}

	public String getStudyTherapyType() {
		return studyTherapyType;
	}

	public String getDisplayName() {
		String s = name().toLowerCase();
		return ("" + s.charAt(0)).toUpperCase() + s.substring(1);
	}

	public String getName() {
		return name();
	}

	public Integer getCode() {
		return code;
	}

	public static StudyTherapyType getByCode(final int code) {
		return getByClassAndCode(StudyTherapyType.class, code);
	}

}
