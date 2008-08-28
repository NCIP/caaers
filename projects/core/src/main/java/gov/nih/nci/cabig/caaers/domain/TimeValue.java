package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;

@Embeddable
public class TimeValue {
	
	private int hour;
	private int minute;
	
	
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
