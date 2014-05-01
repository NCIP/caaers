/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.domain.ReportDefinitionNotificationType;
import gov.nih.nci.cabig.caaers.utils.ProjectedList;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.*;

 
/**
 * This class represent the details that which is to be used while creating the actual
 * ScheduledNotification.
 *
 * @author Biju Joseph
 */
@Entity
@Table(name = "planned_notifications")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("dtype")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_planned_notifications_id")})
public abstract class PlannedNotification extends AbstractMutableDomainObject implements
        Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6921536076182762022L;

	/** The actual mark selected on the time scale. */
    @Column(name = "index_on_time_scale")
    private int indexOnTimeScale;

    /** The recipients of this content. */
    private List<Recipient> recipients;

    /** The body content. */
    private NotificationBodyContent bodyContent;

    /** The attachments. */
    private List<NotificationAttachment> attachments;
    
    private ReportDefinitionNotificationType reportDefinitionNotificationType;

    @Type(type = "reportDefinitionNotificationType")
    @Column(name = "rpt_def_notification_type")
    public ReportDefinitionNotificationType getReportDefinitionNotificationType() {
		return reportDefinitionNotificationType;
	}

	public void setReportDefinitionNotificationType(
			ReportDefinitionNotificationType reportDefinitionNotificationType) {
		this.reportDefinitionNotificationType = reportDefinitionNotificationType;
	}

    // TODO: this signature may be insufficient
    /**
     * Creates the scheduled notification.
     *
     * @param obj the obj
     * @return the scheduled notification
     */
    public abstract ScheduledNotification createScheduledNotification(Object obj);

    /**
     * Gets the notification body content.
     *
     * @return the notification body content
     */
    @Embedded
    public NotificationBodyContent getNotificationBodyContent() {
        return bodyContent;
    }

    /**
     * Sets the notification body content.
     *
     * @param content the new notification body content
     */
    public void setNotificationBodyContent(NotificationBodyContent content) {
        this.bodyContent = content;
    }

    /**
     * Gets the index on time scale.
     *
     * @return the index on time scale
     */
    public int getIndexOnTimeScale() {
        return indexOnTimeScale;
    }

    /**
     * Sets the index on time scale.
     *
     * @param indexOnTimeScale the new index on time scale
     */
    public void setIndexOnTimeScale(int indexOnTimeScale) {
        this.indexOnTimeScale = indexOnTimeScale;
    }

    /**
     * Gets the recipients.
     *
     * @return the recipients
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "plnf_id", nullable = false)
    @Cascade(value = {CascadeType.ALL })
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<Recipient> getRecipients() {

        return recipients;
    }

    /**
     * Sets the recipients.
     *
     * @param recipients the new recipients
     */
    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }

    /**
     * Gets the attachments.
     *
     * @return the attachments
     */
    @OneToMany (orphanRemoval = true)
    @JoinColumn(name = "plnf_id", nullable = false)
    @Cascade(value = {CascadeType.ALL})
    @Fetch(value = org.hibernate.annotations.FetchMode.SUBSELECT)
    public List<NotificationAttachment> getAttachments() {
        return attachments;
    }

    /**
     * Sets the attachments.
     *
     * @param attachments the attachments to set
     */
    public void setAttachments(List<NotificationAttachment> attachments) {
        this.attachments = attachments;
    }

    /**
     * Gets the role based recipients.
     *
     * @return the role based recipients
     */
    @Transient
    public List<RoleBasedRecipient> getRoleBasedRecipients() {
        return new ProjectedList<RoleBasedRecipient>(recipients, RoleBasedRecipient.class);
    }

    /**
     * Gets the contact mechanism based recipients.
     *
     * @return the contact mechanism based recipients
     */
    @Transient
    public List<ContactMechanismBasedRecipient> getContactMechanismBasedRecipients() {
        return new ProjectedList<ContactMechanismBasedRecipient>(recipients,
                ContactMechanismBasedRecipient.class);
    }

    /**
     * Adds the recipient.
     *
     * @param rr the rr
     */
    public void addRecipient(Recipient rr) {
        getRecipients().add(rr);
    }

}
