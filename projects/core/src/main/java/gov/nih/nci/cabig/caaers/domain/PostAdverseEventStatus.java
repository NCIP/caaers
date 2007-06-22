package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.*;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * @author Rhett Sutphin
 */
public enum PostAdverseEventStatus implements CodedEnum<Integer> {
    INTERVENTION_CONTINUES(1, "Intervention for AE continues"),
    RECOVERING(2),
    RECOVERED_WITH_SEQUELAE(3),
    RECOVERED_WITHOUT_SEQUELAE(4),
    NOT_RECOVERED(4),
    DEAD(5)
    ;

    private int code;
    private String displayName;

    PostAdverseEventStatus(int code) {
        this(code, null);
    }

    PostAdverseEventStatus(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    public static PostAdverseEventStatus getByCode(int code) {
        return getByClassAndCode(PostAdverseEventStatus.class, code);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return displayName == null ? sentenceCasedName(this) : displayName;
    }
}
