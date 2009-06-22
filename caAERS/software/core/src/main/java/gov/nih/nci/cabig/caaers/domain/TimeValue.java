package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;


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


    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
    
    @Transient
    public String getMinuteString(){
    	if(minute == null) return null;
    	return String.format("%02d", minute);
    }
    public void setMinuteString(String min){
    	minute = StringUtils.isBlank(min)? null: Integer.valueOf(min);
    }
    @Transient
    public String getHourString(){
    	if(hour == null) return null;
    	return String.format("%02d", hour);
    }
    public void setHourString(String hr){
    	hour = StringUtils.isBlank(hr) ? null : Integer.valueOf(hr);
    }
    @Transient
    public boolean isAM() {
        return type == 0;
    }

    @Transient
    public boolean isPM() {
        return type == 1;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d %s", hour, minute, (type == 1 ? "PM" : "AM"));
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hour == null) ? 0 : hour.hashCode());
		result = prime * result + ((minute == null) ? 0 : minute.hashCode());
		result = prime * result + type;
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
		TimeValue other = (TimeValue) obj;
		if (hour == null) {
			if (other.hour != null)
				return false;
		} else if (!hour.equals(other.hour))
			return false;
		if (minute == null) {
			if (other.minute != null)
				return false;
		} else if (!minute.equals(other.minute))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
    

}
