package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * @author Rhett Sutphin
 */
public enum Grade implements CodedEnum<Integer>, CodedGrade {
    NOT_EVALUATED(-1,"Not evaluated"), NORMAL(0, "Normal or Evaluated"), MILD(1), MODERATE(2), SEVERE(3), LIFE_THREATENING(4, "Life-threatening or disabling"), DEATH(5);

    private Integer code;

    private String displayName;

    Grade(Integer code) {
        this(code, null);
    }

    Grade(Integer code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    public static Grade getByCode(int code) {
        return getByClassAndCode(Grade.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }

    // for bean-property access
    public String getName() {
        return name();
    }

    // ditto
    public String getString() {
        return toString();
    }

    @Override
    public String toString() {
        return toStringHelper(this);
    }
}
