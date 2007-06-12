/*
 * Sematicbits Copyright message
 */
package gov.nih.nci.cabig.caaers.domain.report;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;


/**
 * 
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
 * Created-on : May 11, 2007
 * @version     %I%, %G%
 * @since       1.0
 */
@Entity
@DiscriminatorValue("email")
public class ScheduledEmailNotification extends ScheduledNotification {
	
	
	String fromAddress;
//	@Column(name="")
//	String fromDisplayName;
	
	String toAddress;
//	@Column(name="")
//	String toDisplayName;
//	@Column(name="")
//	String replyToAddress;
	
	/**
	 * @return the fromAddress
	 */
	@Column(name="FROM_ADDR")
	public String getFromAddress() {
		return fromAddress;
	}



	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

//	/**
//	 * @return the fromDisplayName
//	 */
//	public String getFromDisplayName() {
//		return fromDisplayName;
//	}

//	/**
//	 * @param fromDisplayName the fromDisplayName to set
//	 */
//	public void setFromDisplayName(String fromDisplayName) {
//		this.fromDisplayName = fromDisplayName;
//	}
//
//
//	/**
//	 * @return the replyToAddress
//	 */
//	public String getReplyToAddress() {
//		return replyToAddress;
//	}
//
//
//
//	/**
//	 * @param replyToAddress the replyToAddress to set
//	 */
//	public void setReplyToAddress(String replyToAddress) {
//		this.replyToAddress = replyToAddress;
//	}
//

	/**
	 * @return the toAddress
	 */
	@Column(name="TO_ADDR")
	public String getToAddress() {
		return toAddress;
	}

	/**
	 * @param toAddress the toAddress to set
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}


//	/**
//	 * @return the toDisplayName
//	 */
//	public String getToDisplayName() {
//		return toDisplayName;
//	}
//
//
//
//	/**
//	 * @param toDisplayName the toDisplayName to set
//	 */
//	public void setToDisplayName(String toDisplayName) {
//		this.toDisplayName = toDisplayName;
//	}
//
	@Transient
	public String getSubjectLine(){
		if(this.planedNotificaiton != null){
			return ((PlannedEmailNotification)this.planedNotificaiton).getSubjectLine();
		}
		return "Not associated to PlannedNotification";
	}
	
	@Override
	public  String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("ScheduledEmailNotification [")
		.append("id :").append(String.valueOf(getId()))
		.append("from:").append(fromAddress)
		.append("scheduledOn:").append(String.valueOf(scheduledOn))
		.append("recipients").append(String.valueOf(toAddress));
		sb.append("]");
		return sb.toString();
	}

}
