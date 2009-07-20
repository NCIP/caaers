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

    
	private static final long serialVersionUID = 7953320109252988556L;

	protected DeliveryStatus deliveryStatus;

    protected Date createdOn;

    protected Date scheduledOn;

    protected PlannedNotification planedNotificaiton;

    protected String body;

    public ScheduledNotification() {
        deliveryStatus = DeliveryStatus.CREATED;
    }

    @Type(type = "deliveryStatus")
    @Column(name = "DELIVERY_STATUS_CODE")
    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getScheduledOn() {
        return scheduledOn;
    }

    public void setScheduledOn(Date scheduledOn) {
        this.scheduledOn = scheduledOn;
    }

    /**
     * @return the planedNotificaiton
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plnf_id", nullable = false)
    public PlannedNotification getPlanedNotificaiton() {
        return planedNotificaiton;
    }

    /**
     * @param planedNotificaiton
     *                the planedNotificaiton to set
     */
    public void setPlanedNotificaiton(PlannedNotification planedNotificaiton) {
        this.planedNotificaiton = planedNotificaiton;
    }

    /**
     * @return the bodyContent
     */
    public String getBody() {
        return body;
    }

    /**
     * @param bodyContent
     *                the bodyContent to set
     */
    public void setBody(String bodyContent) {
        this.body = bodyContent;
    }
    
    @Transient
    public Boolean isActive(){
    	if(deliveryStatus != null && deliveryStatus == DeliveryStatus.CREATED)
    		return true;
    	return false;
    }

}
