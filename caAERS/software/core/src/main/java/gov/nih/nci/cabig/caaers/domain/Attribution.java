package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * This class represents the Attribution domain object associated with the Adverse event report.
 * 
 * @author Rhett Sutphin
 */
public enum Attribution implements CodedEnum<Integer>{
    UNRELATED(1), UNLIKELY(2), POSSIBLE(3), PROBABLE(4), DEFINITE(5);

    private int code;

    Attribution(int code) {
        this.code = code;
        register(this);
    }

    public static Attribution getByCode(int code) {
        return getByClassAndCode(Attribution.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return sentenceCasedName(this);
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
