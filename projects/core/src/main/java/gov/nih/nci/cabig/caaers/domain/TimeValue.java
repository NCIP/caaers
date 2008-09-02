package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;

@Embeddable
public class TimeValue {
	
	private Integer hour;
	private Integer minute;
	private int type = 0; // this is to force hibernate to instantiate it. 
	
	
	public Integer getHour() {
		return hour;
	}


	public void setHour(Integer hour) {
		this.hour = hour;
	}


	public Integer getMinute() {
		return minute;
	}


	public void setMinute(Integer minute) {
		this.minute = minute;
	}


	@Override
	public String toString() {
		return String.format("%02d:%02d", hour, minute);
	}
}
