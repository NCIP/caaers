package gov.nih.nci.cabig.caaers.domain.report;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

import java.util.Calendar;

/**
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : Apr 30, 2007
 */
public enum TimeScaleUnit implements CodedEnum<Integer> {
	
    SECOND(1, Calendar.SECOND, 1000, "s"), 
    MINUTE(2, Calendar.MINUTE, 1000 * 60 ,"m"), 
    HOUR(3, Calendar.HOUR_OF_DAY, 1000 * 60 *60, "Hm"), 
    DAY(4,Calendar.DAY_OF_MONTH, 1000 * 60 * 60 * 24, "dH"), 
    WEEK(5, Calendar.WEEK_OF_MONTH , 1000 * 60 *60 * 24 * 7, "d"), 
    MONTH(6,Calendar.MONTH, 1000 * 60 *60 * 24 * 7 * 30, "Md");

    // equivalent Calendar type of this Object
    private int calendarTypeCode;

    private int code;
    
    private int milliSecondConversionFactor; //represents the fraction of milli seconds, this time unit represents
    
    private String format;

    private TimeScaleUnit(int code, int calendarTypeCode, int milliSecondConversionFactor, String format) {
        this.code = code;
        this.calendarTypeCode = calendarTypeCode;
        this.milliSecondConversionFactor = milliSecondConversionFactor;
        this.format = format;
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
    
    public int getMilliSecondConversionFactor() {
		return milliSecondConversionFactor;
	}
    
    public void setMilliSecondConversionFactor(int milliSecondConversionFactor) {
		this.milliSecondConversionFactor = milliSecondConversionFactor;
	}
    
    public String getFormat() {
		return format;
	}
    public void setFormat(String format) {
		this.format = format;
	}
    
    public static TimeScaleUnit getByCode(int code) {
        return getByClassAndCode(TimeScaleUnit.class, code);
    }
}
