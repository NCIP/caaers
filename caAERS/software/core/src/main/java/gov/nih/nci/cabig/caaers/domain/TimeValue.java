/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;


 
/**
 * The Class TimeValue.
 */
@Embeddable
public class TimeValue {

    /** The hour. */
    private Integer hour;
    
    /** The minute. */
    private Integer minute;
    
    /** The type. */
    private int type = 0; // this is to force hibernate to instantiate it.


    /**
     * Gets the hour.
     *
     * @return the hour
     */
    public Integer getHour() {
        return hour;
    }


    /**
     * Sets the hour.
     *
     * @param hour the new hour
     */
    public void setHour(Integer hour) {
        this.hour = hour;
    }


    /**
     * Gets the minute.
     *
     * @return the minute
     */
    public Integer getMinute() {
        return minute;
    }


    /**
     * Sets the minute.
     *
     * @param minute the new minute
     */
    public void setMinute(Integer minute) {
        this.minute = minute;
    }


    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public int getType() {
        return type;
    }
    
    /**
     * Gets the minute string.
     *
     * @return the minute string
     */
    @Transient
    public String getMinuteString(){
    	if(minute == null) return null;
    	return String.format("%02d", minute);
    }
    
    /**
     * Sets the minute string.
     *
     * @param min the new minute string
     */
    public void setMinuteString(String min){
    	minute = StringUtils.isBlank(min)? null: Integer.valueOf(min);
    }
    
    /**
     * Gets the hour string.
     *
     * @return the hour string
     */
    @Transient
    public String getHourString(){
    	if(hour == null) return null;
    	return String.format("%02d", hour);
    }
    
    /**
     * Sets the hour string.
     *
     * @param hr the new hour string
     */
    public void setHourString(String hr){
    	hour = StringUtils.isBlank(hr) ? null : Integer.valueOf(hr);
    }
    
    /**
     * Checks if is aM.
     *
     * @return true, if is aM
     */
    @Transient
    public boolean isAM() {
        return type == 0;
    }

    /**
     * Checks if is pM.
     *
     * @return true, if is pM
     */
    @Transient
    public boolean isPM() {
        return type == 1;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("%02d:%02d %s", hour, minute, (type == 1 ? "PM" : "AM"));
    }


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hour == null) ? 0 : hour.hashCode());
		result = prime * result + ((minute == null) ? 0 : minute.hashCode());
		result = prime * result + type;
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
	
	/**
	 * Checks if is blank.
	 *
	 * @return true, if is blank
	 */
	@Transient
	public boolean isBlank() {
		return getHour()==null && getMinute()==null;
	}
    

}
