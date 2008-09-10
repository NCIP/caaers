package gov.nih.nci.cabig.caaers.domain;

import javax.persistence.Embeddable;
import java.util.Date;

/**
 * This class represents the CourseDate domain object associated with the Adverse event report.
 *
 * @author Rhett Sutphin
 */
@Embeddable
public class CourseDate {
    private Integer number;

    private Date date;

    public CourseDate() {
    }

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
