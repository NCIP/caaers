/*
 * Sematicbits Copyright message
 */
package gov.nih.nci.cabig.caaers.domain.report;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

 
/**
 * The Class ScheduledEmailNotification.
 *
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 11, 2007
 * @version %I%, %G%
 * @since 1.0
 */
@Entity
@DiscriminatorValue("email")
public class ScheduledEmailNotification extends ScheduledNotification {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -475181649630877114L;

	/** The from address. */
	String fromAddress;

    /** The to address. */
    String toAddress;

    /** The subject line. */
    String subjectLine;

    /**
     * Gets the from address.
     *
     * @return the from address
     */
    @Column(name = "FROM_ADDR")
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * Sets the from address.
     *
     * @param fromAddress the new from address
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

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

    /**
     * Gets the to address.
     *
     * @return the to address
     */
    @Column(name = "TO_ADDR")
    public String getToAddress() {
        return toAddress;
    }

    /**
     * Sets the to address.
     *
     * @param toAddress the new to address
     */
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ScheduledEmailNotification [").append("id :").append(String.valueOf(getId()))
                        .append("from:").append(fromAddress).append("scheduledOn:").append(
                                        String.valueOf(scheduledOn)).append("recipients").append(
                                        String.valueOf(toAddress));
        sb.append("]");
        return sb.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final ScheduledEmailNotification other = (ScheduledEmailNotification) obj;
        if (other.getId() != null && getId() != null && other.getId().equals(getId())) return true;
        if (fromAddress == null) {
            if (other.fromAddress != null) return false;
        } else if (!fromAddress.equals(other.fromAddress)) return false;
        if (subjectLine == null) {
            if (other.subjectLine != null) return false;
        } else if (!subjectLine.equals(other.subjectLine)) return false;
        if (toAddress == null) {
            if (other.toAddress != null) return false;
        } else if (!toAddress.equals(other.toAddress)) return false;
        return true;
    }

}
