/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

 
/**
 * The Class DateValue.
 */
@Embeddable
public class DateValue implements Comparable<DateValue> {
    
    /** The format. */
    private String format = "%02d/%02d/%04d";

    /** The day. */
    private Integer day;

    /** The month. */
    private Integer month;

    /** The year. */
    private Integer year;

    /** The zone. */
    private int zone = 0; //attribute kept to force Hibernate in instantiating this object

    /**
     * String to date value.
     *
     * @param date the date
     * @return the date value
     * @throws ParseException the parse exception
     */
    public static DateValue stringToDateValue(String date) throws ParseException {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        String[] dateParts = date.split("/");
        int size = dateParts.length;
        if (size != 3) throw new ParseException("Unknown format, expected format is 'mm/dd/yyyy'",
                0);
        DateValue dateValue = new DateValue();
        try {
            dateValue.setMonth(Integer.parseInt(dateParts[0]));
            dateValue.setDay(Integer.parseInt(dateParts[1]));
            dateValue.setYear(Integer.parseInt(dateParts[2]));
        } catch (NumberFormatException e) {
            throw new ParseException(
                    "Unknown format, unable to parse the date values, expected format is 'mm/dd/yyyy'",
                    0);
        }
        return dateValue;
    }

    /**
     * Instantiates a new date value.
     */
    public DateValue() {
        this(null, null, null);
    }

    /**
     * Instantiates a new date value.
     *
     * @param year the year
     */
    public DateValue(int year) {
        this(null, null, year);
    }

    /**
     * Instantiates a new date value.
     *
     * @param month the month
     * @param year the year
     */
    public DateValue(int month, int year) {
        this(null, month, year);
    }

    /**
     * Instantiates a new date value.
     *
     * @param day the day
     * @param month the month
     * @param year the year
     */
    public DateValue(Integer day, Integer month, Integer year) {
        super();
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Instantiates a new date value.
     *
     * @param date the date
     */
    public DateValue(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        this.day = gc.get(Calendar.DAY_OF_MONTH);
        this.month = gc.get(Calendar.MONTH) + 1;
        this.year = gc.get(Calendar.YEAR);
    }

    /**
     * Gets the day.
     *
     * @return the day
     */
    public Integer getDay() {
        return day;
    }

    /**
     * Sets the day.
     *
     * @param day the new day
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * Gets the month.
     *
     * @return the month
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * Sets the month.
     *
     * @param month the new month
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * Gets the year.
     *
     * @return the year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Sets the year.
     *
     * @param year the new year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Gets the zone.
     *
     * @return the zone
     */
    public int getZone() {
        return zone;
    }

    /**
     * Sets the zone.
     *
     * @param zone the new zone
     */
    public void setZone(int zone) {
        this.zone = zone;
    }

    /**
     * Gets the year string.
     *
     * @return the year string
     */
    @Transient
    public String getYearString() {
        if (year == null) return null;
        return String.format("%04d", year);
    }

    /**
     * Sets the year string.
     *
     * @param year the new year string
     */
    @Transient
    public void setYearString(String year) {
        if (StringUtils.isEmpty(year))
            this.year = null;
        else
            this.year = new Integer(year);
    }

    /**
     * Gets the month string.
     *
     * @return the month string
     */
    @Transient
    public String getMonthString() {
        if (month == null) return null;
        return String.format("%02d", month);
    }

    /**
     * Sets the month string.
     *
     * @param month the new month string
     */
    @Transient
    public void setMonthString(String month) {
        if (StringUtils.isEmpty(month))
            this.month = null;
        else
            this.month = new Integer(month);
    }

    /**
     * Gets the day string.
     *
     * @return the day string
     */
    @Transient
    public String getDayString() {
        if (day == null) return null;
        return String.format("%02d", day);
    }

    /**
     * Sets the day string.
     *
     * @param day the new day string
     */
    @Transient
    public void setDayString(String day) {
        if (StringUtils.isEmpty(day))
            this.day = null;
        else
            this.day = new Integer(day);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((month == null) ? 0 : month.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        final DateValue other = (DateValue) obj;
        if (day == null) {
            if (other.day != null) return false;
        } else if (!day.equals(other.day)) return false;
        if (month == null) {
            if (other.month != null) return false;
        } else if (!month.equals(other.month)) return false;
        if (year == null) {
            if (other.year != null) return false;
        } else if (!year.equals(other.year)) return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(DateValue o) {

        if (o == null) return 1;

        if (year == null && o.year != null) return -1;
        if (year != null && o.year == null) return 1;
        if ((year != null && o.year != null) && (year.compareTo(o.year) != 0)) return year.compareTo(o.year);

        if (month == null && o.month != null) return -1;
        if (month != null && o.month == null) return 1;
        if ((month != null && o.month != null) && (month.compareTo(o.month) != 0)) return month.compareTo(o.month);

        if (day == null && o.day != null) return -1;
        if (day != null && o.day == null) return 1;
        if ((day != null && o.day != null) && (day.compareTo(o.day) != 0)) return day.compareTo(o.day);

        return 0;
    }

    /**
     * To date.
     *
     * @return the date
     */
    public Date toDate() {
        return new GregorianCalendar(year == null ? 2008 : year, month == null ? 0 : month-1, day == null ? 0 : day).getTime();
    }

    /**
     * Check if date is in valid.
     *
     * @return true, if successful
     */
    public boolean checkIfDateIsInValid() {

        Calendar now = Calendar.getInstance();

        return (this.getMonth() != null && (this.getMonth() < 0 || this.getMonth() > 12))
                || (this.getDay() != null && (this.getDay() < 0 || this.getDay() > 31))
                || (this.getYear() == null || this.getYear() < 1900 || this.getYear() > now
                .get(Calendar.YEAR)) || now.after(this.toDate());
    }

    /**
     * Checks if is null.
     *
     * @return true, if is null
     */
    @Transient
    public boolean isNull() {
        return (this.getMonth() == null && this.getDay() == null && this.getDay() == null);
    }

    /**
     * Checks if is empty.
     *
     * @return true, if is empty
     */
    @Transient
    public boolean isEmpty() {
        return (this.getMonth() == 0 && this.getDay() == 0 && this.getDay() == 0);
    }

    /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
    public String toString() {
        return String.format(format, month == null ? 0 : month, day == null ? 0 : day,
                year == null ? 0 : year);
    }

}
