/*
 * Sematicbits Copyright message
 */
package gov.nih.nci.cabig.caaers.domain.report;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a> Created-on : May 11, 2007
 * @version %I%, %G%
 * @since 1.0
 */
@Entity
@DiscriminatorValue("email")
public class ScheduledEmailNotification extends ScheduledNotification {

	private static final long serialVersionUID = -475181649630877114L;

	String fromAddress;

    String toAddress;

    String subjectLine;

    @Column(name = "FROM_ADDR")
    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    @Column(name = "SUBJECT")
    public String getSubjectLine() {
        return subjectLine;
    }

    public void setSubjectLine(String subjectLine) {
        this.subjectLine = subjectLine;
    }

    @Column(name = "TO_ADDR")
    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

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
