package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

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


}
