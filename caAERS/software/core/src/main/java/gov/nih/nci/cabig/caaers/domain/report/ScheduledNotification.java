package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

 
/**
 * This class contains the details of the notification, that is to be send out.
 * 
 * @author Biju Joseph
 * 
 */

@Entity
@Table(name = "SCHEDULED_NOTIFICATIONS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("dtype")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = { @Parameter(name = "sequence", value = "seq_scheduled_notifications_id") })
public abstract class ScheduledNotification extends AbstractMutableDomainObject implements
                Serializable {

    
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7953320109252988556L;

	/** The delivery status. */
	protected DeliveryStatus deliveryStatus;

    /** The created on. */
    protected Date createdOn;

    /** The scheduled on. */
    protected Date scheduledOn;

    /** The planed notificaiton. */
    protected PlannedNotification planedNotificaiton;

    /** The body. */
    protected String body;

    /**
     * Instantiates a new scheduled notification.
     */
    public ScheduledNotification() {
        deliveryStatus = DeliveryStatus.CREATED;
    }

    /**
     * Gets the delivery status.
     *
     * @return the delivery status
     */
    @Type(type = "deliveryStatus")
    @Column(name = "DELIVERY_STATUS_CODE")
    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    /**
     * Sets the delivery status.
     *
     * @param deliveryStatus the new delivery status
     */
    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    /**
     * Gets the created on.
     *
     * @return the created on
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the created on.
     *
     * @param createdOn the new created on
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Gets the scheduled on.
     *
     * @return the scheduled on
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getScheduledOn() {
        return scheduledOn;
    }

    /**
     * Sets the scheduled on.
     *
     * @param scheduledOn the new scheduled on
     */
    public void setScheduledOn(Date scheduledOn) {
        this.scheduledOn = scheduledOn;
    }

    /**
     * Gets the planed notificaiton.
     *
     * @return the planedNotificaiton
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plnf_id", nullable = false)
    public PlannedNotification getPlanedNotificaiton() {
        return planedNotificaiton;
    }

    /**
     * Sets the planed notificaiton.
     *
     * @param planedNotificaiton the planedNotificaiton to set
     */
    public void setPlanedNotificaiton(PlannedNotification planedNotificaiton) {
        this.planedNotificaiton = planedNotificaiton;
    }

    /**
     * Gets the body.
     *
     * @return the bodyContent
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body.
     *
     * @param bodyContent the bodyContent to set
     */
    public void setBody(String bodyContent) {
        this.body = bodyContent;
    }
    
    /**
     * Checks if is active.
     *
     * @return the boolean
     */
    @Transient
    public Boolean isActive(){
    	if(deliveryStatus != null && deliveryStatus == DeliveryStatus.CREATED)
    		return true;
    	return false;
    }

}
