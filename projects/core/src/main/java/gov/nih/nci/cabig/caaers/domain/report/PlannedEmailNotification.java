package gov.nih.nci.cabig.caaers.domain.report;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 13, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
@Entity
@DiscriminatorValue("email")
public class PlannedEmailNotification extends PlannedNotification {

	/** The subject line of the email */
	private String subjectLine;
	/** The from address */
	private String fromAddress;
	
    ////// LOGIC

    @Override
    public ScheduledEmailNotification createScheduledNotification(Object objTo) {
        ScheduledEmailNotification notification = new ScheduledEmailNotification();
        notification.setPlanedNotificaiton(this);
        notification.setFromAddress(getFromAddress());
        notification.setToAddress((String)objTo);
        return notification;
    }

    ///BEAN PROPERTIES
    
	@Column(name="FROM_ADDR")
	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	
	@Column(name="SUBJECT")
	public String getSubjectLine() {
		return subjectLine;
	}

	public void setSubjectLine(String subjectLine) {
		this.subjectLine = subjectLine;
	}
	
	///Object methods
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null)return false;
		if (getClass() != obj.getClass())return false;
		final PlannedEmailNotification other = (PlannedEmailNotification) obj;
		if(other.getId() != null && getId() != null && other.getId().equals(getId()))  return true;
		if (fromAddress == null) {
			if (other.fromAddress != null) return false;
		} else if (!fromAddress.equals(other.fromAddress)) return false;
		if (subjectLine == null) {
			if (other.subjectLine != null) return false;
		} else if (!subjectLine.equals(other.subjectLine))return false;
		return true;
	}
	
	
}
