package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.CodedEnum;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;

/**
 * @author Biju Joseph
 * @date 3/26/12
 */
public enum NotificationStatus  implements CodedEnum<Integer> {

    IGNORE(1, "NA"),
    NOTIFY(2, "Notify"),
    DO_NOT_NOTIFY(3, "Do not notify"),
    ;

    /** The code. */
    private int code;

    /** The display name. */
    private String displayName;


    /**
     * Instantiates a new event status.
     *
     * @param code the code
     * @param longName the long name
     */
    private NotificationStatus(int code, String longName) {
        this.code = code;
        this.displayName = longName;
        register(this);
    }

    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static NotificationStatus getByCode(int code) {
        return getByClassAndCode(NotificationStatus.class, code);
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getCode()
     */
    public Integer getCode() {
        return code;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getDisplayName()
     */
    public String getDisplayName() {
        return displayName;
    }

    public static NotificationStatus[] statuesForRules(){
        return new NotificationStatus[]{NOTIFY,DO_NOT_NOTIFY};
    }
}