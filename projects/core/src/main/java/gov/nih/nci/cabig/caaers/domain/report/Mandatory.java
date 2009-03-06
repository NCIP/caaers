package gov.nih.nci.cabig.caaers.domain.report;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.toStringHelper;
import static gov.nih.nci.cabig.ctms.domain.EnumHelper.sentenceCasedName;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

	public enum Mandatory implements CodedEnum<Integer> {
		OPTIONAL(0, "Optional"),  MANDATORY(1,"Mandatory"), NA(-1 , "Not Applicable");

    private Integer code;

    private String displayName;
    
    Mandatory(int code) {
        this(code, null);
    }

    Mandatory(Integer code, String displayName) {
        this.code = code;
        this.displayName = displayName;
        register(this);
    }

    public static Mandatory getByCode(int code) {
        return getByClassAndCode(Mandatory.class, code);
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
