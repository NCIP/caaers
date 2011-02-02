package gov.nih.nci.cabig.caaers.domain.report;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

 
/**
 * This enumeration represents the status of an EmailNotificaiton.
 * 
 * @author Biju Joseph
 */
public enum DeliveryStatus implements CodedEnum<Integer> {
    
    /** The CREATED. */
    CREATED(1), 
 /** The SCHEDULED. */
 SCHEDULED(2), 
 /** The DELIVERED. */
 DELIVERED(3), 
 /** The ERROR. */
 ERROR(4), 
 /** The RETRY. */
 RETRY(5), 
 /** The RECALLED. */
 RECALLED(6);

    /** The code. */
    private int code;

    /**
     * Instantiates a new delivery status.
     *
     * @param code the code
     */
    private DeliveryStatus(int code) {
        this.code = code;
        register(this);
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
        return name();
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name();
    }

    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static DeliveryStatus getByCode(int code) {
        return getByClassAndCode(DeliveryStatus.class, code);
    }
}
