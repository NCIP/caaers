package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;

@Embeddable
public class TimeValue {
	
	private Integer hour;
	private Integer minute;
	private int type = 0; // this is to force hibernate to instantiate it. 
	
	
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	@Override
	public String toString() {
		return String.format("%02d:%02d", hour, minute);
	}
}
