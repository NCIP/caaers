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
    CREATED(1), SCHEDULED(2), DELIVERED(3), ERROR(4), RETRY(5), RECALLED(6);

    private int code;

    private DeliveryStatus(int code) {
        this.code = code;
        register(this);
    }

    public Integer getCode() {
        return code;
    }

    public String getDisplayName() {
        return name();
    }

    public String getName() {
        return name();
    }

    public static DeliveryStatus getByCode(int code) {
        return getByClassAndCode(DeliveryStatus.class, code);
    }
}
