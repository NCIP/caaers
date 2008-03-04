package gov.nih.nci.cabig.caaers.domain.report;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

import java.util.Calendar;

/**
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : Apr 30, 2007
 */
public enum TimeScaleUnit implements CodedEnum<Integer> {

    SECOND(1, Calendar.SECOND), MINUTE(2, Calendar.MINUTE), HOUR(3, Calendar.HOUR_OF_DAY), DAY(4,
                    Calendar.DAY_OF_MONTH), WEEK(5, Calendar.WEEK_OF_MONTH), MONTH(6,
                    Calendar.MONTH);

    // equivalent Calendar type of this Object
    private int calendarTypeCode;

    private int code;

    private TimeScaleUnit(int code, int calendarTypeCode) {
        this.code = code;
        this.calendarTypeCode = calendarTypeCode;
        register(this);

    }

    public int getCalendarTypeCode() {
        return calendarTypeCode;
    }

    public String getDisplayName() {
        String s = name().toLowerCase();
        return ("" + s.charAt(0)).toUpperCase() + s.substring(1);
    }

    public String getName() {
        return name();
    }

    public Integer getCode() {
        return code;
    }

    public static TimeScaleUnit getByCode(int code) {
        return getByClassAndCode(TimeScaleUnit.class, code);
    }
}
