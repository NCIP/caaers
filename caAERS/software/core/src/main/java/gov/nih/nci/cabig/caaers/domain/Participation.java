package gov.nih.nci.cabig.caaers.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Krikor Krumlian
 */

@Embeddable
public class Participation {

    // private String roleCode;
    // private String statusCode;

    private Date startDate;

    private Date endDate;

    @Column(name = "endDate", nullable = true)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "startDate", nullable = true)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
