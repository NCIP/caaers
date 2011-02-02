package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

 
/**
 * The Class Participation.
 *
 * @author Krikor Krumlian
 */

@Embeddable
public class Participation {

    // private String roleCode;
    // private String statusCode;

    /** The start date. */
    private Date startDate;

    /** The end date. */
    private Date endDate;

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    @Column(name = "endDate", nullable = true)
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     *
     * @param endDate the new end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    @Column(name = "startDate", nullable = true)
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     *
     * @param startDate the new start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
