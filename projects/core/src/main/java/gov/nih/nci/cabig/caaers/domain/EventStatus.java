package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

public enum EventStatus implements CodedEnum<Integer> {

    YES(1, "Yes"), 
    NO(2,"No"), 
    NA(3,"Not applicable");


    private int code;

    private String displayName;

    EventStatus(int code) {
        this(code, null);
    }

    EventStatus(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    public static EventStatus getByCode(int code) {
        return getByClassAndCode(EventStatus.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName;
    }
}
