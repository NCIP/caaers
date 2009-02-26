package gov.nih.nci.cabig.caaers.domain;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

/**
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 15, 2007
 * @version %I%, %G%
 * @since 1.0
 */
public enum ReportStatus implements CodedEnum<Integer> {
    PENDING(1), COMPLETED(2), WITHDRAWN(3), INPROCESS(4), FAILED(5), REPLACED(6);
    private int code;

    private ReportStatus(int code) {
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

    public static ReportStatus getByCode(int code) {
        return getByClassAndCode(ReportStatus.class, code);
    }
}
