package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;

@Embeddable
public class DateValue {
	
	private String format = "%02d/%02d/%04d";
	
	private int day;
	private int month;
	private int year;
	
	public DateValue(){
		this(0,0,2008);
	}
	public DateValue(int year){
		this(0,0,year);
	}
	
	public DateValue(int month, int year) {
		this(0,month,year);
	}
	
	public DateValue(int day, int month, int year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public DateValue(Date date){
		super();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		this.day = gc.get(Calendar.DAY_OF_MONTH);
		this.month = gc.get(Calendar.MONTH) + 1;
		this.year = gc.get(Calendar.YEAR);
	}

	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
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
		if (day != other.day)
			return false;
		if (month != other.month)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	
	public boolean validate() {
		if(year < 0 || day < 0 || month < 0) return false;
		return true;
	}
	
	public String toString(){
		return String.format(format, day,month,year);
	}
	
	public Date toDate(){
		return new GregorianCalendar(getYear(),getMonth(),getDay()).getTime();
	}
	
}
