package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;

@Embeddable
public class DateValue {
	private String format = "%02d/%02d/%04d";
	
	private Integer day;
	private Integer month;
	private Integer year;
	
	public DateValue(){
		this(0,0,2008);
	}
	public DateValue(int year){
		this(0,0,year);
	}
	
	public DateValue(int month, int year) {
		this(0,month,year);
	}
	
	public DateValue(Integer day, Integer month, Integer year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public DateValue(Date date){
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final DateValue other = (DateValue) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	
	
	public String toString(){
		return String.format(format, day == null ? 0 : day ,month == null ? 0 : month ,year == null ? 0 : year);
	}
	
	public Date toDate(){
		return new GregorianCalendar(
				year == null ? 2008 : year , 
				month == null ? 0 : month, 
				day == null ? 0 : day).getTime();
	}
	
}
