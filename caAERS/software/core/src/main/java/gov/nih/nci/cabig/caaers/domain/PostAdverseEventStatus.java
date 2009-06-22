package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public enum PostAdverseEventStatus implements CodedEnum<Integer> {

    INTERVENTION_CONTINUES(1, "Intervention for AE continues"), 
    RECOVERING(2,"Recovering/Resolving"), 
    RECOVERED_WITH_SEQUELAE(3,"Recovered/Resolved with Sequelae"),
    RECOVERED_WITHOUT_SEQUELAE(4,"Recovered/Resolved without Sequelae"), 
    NOT_RECOVERED(5,"Not recovered/Not resolved"), 
    DEAD(6, "Fatal/Died");

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
        return displayName;
    }
}
