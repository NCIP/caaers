package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.Embeddable;

 
/**
 * This class represents the CourseDate domain object associated with the Adverse event report.
 *
 * @author Rhett Sutphin
 */
@Embeddable
public class CourseDate {
    /** The code. */
    private int code; //to force hibernate to load the object
    
    /** The number. */
    private Integer number;

    /** The date. */
    private Date date;

    /**
     * Instantiates a new course date.
     */
    public CourseDate() {
    }

    /**
     * Instantiates a new course date.
     *
     * @param number the number
     */
    public CourseDate(Integer number) {
        this.number = number;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public int getCode(){
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets the number.
     *
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Sets the number.
     *
     * @param number the new number
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * Gets the date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date the new date
     */
    public void setDate(Date date) {
        this.date = date;
    }


}
