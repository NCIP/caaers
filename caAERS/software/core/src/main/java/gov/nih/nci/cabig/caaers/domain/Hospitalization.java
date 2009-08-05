package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * This class represents the Hospitalization domain object associated with the Adverse event report.
 * 
 * @author Rhett Sutphin
 */
public enum Hospitalization implements CodedEnum<Integer> {
	
    NONE(0, "Please select"), YES(1, "Yes"), NO(2, "No");

    private int code;

    private String displayName;

    Hospitalization(int code) {
        this(code, null);
    }

    Hospitalization(int code, String displayName) {
        this.code = code;
        this.displayName = displayName;
        register(this);
    }

    public static Hospitalization getByCode(int code) {
        return getByClassAndCode(Hospitalization.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        if (displayName == null) {
            return sentenceCasedName(this);
        } else {
            return displayName;
        }
    }

    // for bean-property access
    public String getName() {
        return name();
    }

    public String toString() {
        return toStringHelper(this);
    }
}
