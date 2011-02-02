package gov.nih.nci.cabig.caaers.domain.report;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

 
/**
 * The Class PlannedEmailNotification.
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 13, 2007
 * @version %I%, %G%
 * @since 1.0
 */
@Entity
@DiscriminatorValue("email")
public class PlannedEmailNotification extends PlannedNotification {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2854154055470551938L;
	
	/** The subject line of the email. */
    private String subjectLine;

    // //// LOGIC

    /* (non-Javadoc)
     * @see gov.nih.nci.cabig.caaers.domain.report.PlannedNotification#createScheduledNotification(java.lang.Object)
     */
    @Override
    public ScheduledEmailNotification createScheduledNotification(Object objTo) {
        ScheduledEmailNotification notification = new ScheduledEmailNotification();
        notification.setPlanedNotificaiton(this);
        notification.setToAddress((String) objTo);
        notification.setDeliveryStatus(DeliveryStatus.CREATED);
        notification.setCreatedOn(new Date());
        return notification;
    }

    // /BEAN PROPERTIES

    /**
     * Gets the subject line.
     *
     * @return the subject line
     */
    @Column(name = "SUBJECT")
    public String getSubjectLine() {
        return subjectLine;
    }

    /**
     * Sets the subject line.
     *
     * @param subjectLine the new subject line
     */
    public void setSubjectLine(String subjectLine) {
        this.subjectLine = subjectLine;
    }

    // /Object methods
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final PlannedEmailNotification other = (PlannedEmailNotification) obj;
        if (other.getId() != null && getId() != null && other.getId().equals(getId())) return true;
        if (subjectLine == null) {
            if (other.subjectLine != null) return false;
        } else if (!subjectLine.equals(other.subjectLine)) return false;
        return true;
    }

}
