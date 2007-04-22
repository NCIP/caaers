package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author Rhett Sutphin
 */
@Embeddable
public class CourseDate {
    private Integer number;
    private Date date;

    public CourseDate() { }

    public CourseDate(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
