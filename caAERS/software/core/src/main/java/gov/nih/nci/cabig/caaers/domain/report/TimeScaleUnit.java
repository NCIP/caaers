/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.report;

import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.getByClassAndCode;
import static gov.nih.nci.cabig.ctms.domain.CodedEnumHelper.register;
import gov.nih.nci.cabig.ctms.domain.CodedEnum;

import java.util.Calendar;

 
/**
 * The Enum TimeScaleUnit.
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : Apr 30, 2007
 */
public enum TimeScaleUnit implements CodedEnum<Integer> {
	
    /** The SECOND. */
    SECOND(1, Calendar.SECOND, 1000, "s"), 
    
    /** The MINUTE. */
    MINUTE(2, Calendar.MINUTE, 1000 * 60 ,"m"), 
    
    /** The HOUR. */
    HOUR(3, Calendar.HOUR_OF_DAY, 1000 * 60 *60, "Hm"), 
    
    /** The DAY. */
    DAY(4,Calendar.DAY_OF_MONTH, 1000 * 60 * 60 * 24, "dH"), 
    
    /** The WEEK. */
    WEEK(5, Calendar.WEEK_OF_MONTH , 1000 * 60 *60 * 24 * 7, "d"), 
    
    /** The MONTH. */
    MONTH(6,Calendar.MONTH, 1000 * 60 *60 * 24 * 7 * 30, "Md");

    // equivalent Calendar type of this Object
    /** The calendar type code. */
    private int calendarTypeCode;

    /** The code. */
    private int code;
    
    /** The milli second conversion factor. */
    private int milliSecondConversionFactor; //represents the fraction of milli seconds, this time unit represents
    
    /** The format. */
    private String format;

    /**
     * Instantiates a new time scale unit.
     *
     * @param code the code
     * @param calendarTypeCode the calendar type code
     * @param milliSecondConversionFactor the milli second conversion factor
     * @param format the format
     */
    private TimeScaleUnit(int code, int calendarTypeCode, int milliSecondConversionFactor, String format) {
        this.code = code;
        this.calendarTypeCode = calendarTypeCode;
        this.milliSecondConversionFactor = milliSecondConversionFactor;
        this.format = format;
        register(this);

    }

    /**
     * Gets the calendar type code.
     *
     * @return the calendar type code
     */
    public int getCalendarTypeCode() {
        return calendarTypeCode;
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getDisplayName()
     */
    public String getDisplayName() {
        String s = name().toLowerCase();
        return ("" + s.charAt(0)).toUpperCase() + s.substring(1);
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name();
    }

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.ctms.domain.CodedEnum#getCode()
     */
    public Integer getCode() {
        return code;
    }
    
    /**
     * Gets the milli second conversion factor.
     *
     * @return the milli second conversion factor
     */
    public int getMilliSecondConversionFactor() {
		return milliSecondConversionFactor;
	}
    
    /**
     * Sets the milli second conversion factor.
     *
     * @param milliSecondConversionFactor the new milli second conversion factor
     */
    public void setMilliSecondConversionFactor(int milliSecondConversionFactor) {
		this.milliSecondConversionFactor = milliSecondConversionFactor;
	}
    
    /**
     * Gets the format.
     *
     * @return the format
     */
    public String getFormat() {
		return format;
	}
    
    /**
     * Sets the format.
     *
     * @param format the new format
     */
    public void setFormat(String format) {
		this.format = format;
	}
    
    /**
     * Gets the by code.
     *
     * @param code the code
     * @return the by code
     */
    public static TimeScaleUnit getByCode(int code) {
        return getByClassAndCode(TimeScaleUnit.class, code);
    }
}
