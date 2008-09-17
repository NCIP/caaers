package gov.nih.nci.cabig.caaers.domain;

import org.apache.commons.lang.StringUtils;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;

@Embeddable
public class DateValue implements Comparable<DateValue> {
    private String format = "%02d/%02d/%04d";

    private Integer day;

    private Integer month;

    private Integer year;

    private int zone = 0; //attribute kept to force Hibernate in instantiating this object

    public DateValue() {
        this(null, null, null);
    }

    public DateValue(int year) {
        this(null, null, year);
    }

    public DateValue(int month, int year) {
        this(null, month, year);
    }

    public DateValue(Integer day, Integer month, Integer year) {
        super();
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public DateValue(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        this.day = gc.get(Calendar.DAY_OF_MONTH);
        this.month = gc.get(Calendar.MONTH) + 1;
        this.year = gc.get(Calendar.YEAR);
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    @Transient
    public String getYearString() {
        if (year == null) return null;
        return String.format("%04d", year);
    }

    @Transient
    public void setYearString(String year) {
        if (StringUtils.isEmpty(year))
            this.year = null;
        else
            this.year = new Integer(year);
    }

    @Transient
    public String getMonthString() {
        if (month == null) return null;
        return String.format("%02d", month);
    }

    @Transient
    public void setMonthString(String month) {
        if (StringUtils.isEmpty(month))
            this.month = null;
        else
            this.month = new Integer(month);
    }

    @Transient
    public String getDayString() {
        if (day == null) return null;
        return String.format("%02d", day);
    }

    @Transient
    public void setDayString(String day) {
        if (StringUtils.isEmpty(day))
            this.day = null;
        else
            this.day = new Integer(day);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((month == null) ? 0 : month.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
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

    public String toString() {
        return String.format(format, month == null ? 0 : month, day == null ? 0 : day,
                year == null ? 0 : year);
    }

    public Date toDate() {
        return new GregorianCalendar(year == null ? 2008 : year, month == null ? 0 : month,
                day == null ? 0 : day).getTime();
    }

    public boolean checkIfDateIsInValid() {

        Calendar now = Calendar.getInstance();

        return (this.getMonth() != null && (this.getMonth() < 0 || this.getMonth() > 12))
                || (this.getDay() != null && (this.getDay() < 0 || this.getDay() > 31))
                || (this.getYear() == null || this.getYear() < 1900 || this.getYear() > now
                .get(Calendar.YEAR)) || now.after(this.toDate());
    }

    @Transient
    public boolean isNull() {
        return (this.getMonth() == null && this.getDay() == null && this.getDay() == null);
    }
}
