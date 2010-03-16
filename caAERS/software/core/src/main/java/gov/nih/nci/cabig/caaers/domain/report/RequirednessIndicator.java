package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.CodedEnum;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.*;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;

public enum RequirednessIndicator implements CodedEnum<Integer> {

	OPTIONAL(0, "Optional"),
    MANDATORY(1,"Mandatory"),
    NA(-1 , "Not Applicable"),
    RULE(2, "Rule based");

    private Integer code;

    private String displayName;

    RequirednessIndicator(int code) {
        this(code, null);
    }

    RequirednessIndicator(Integer code, String displayName) {
        this.code = code;
        this.displayName = displayName;
        register(this);
    }

    public static RequirednessIndicator getByCode(int code) {
        return getByClassAndCode(RequirednessIndicator.class, code);
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