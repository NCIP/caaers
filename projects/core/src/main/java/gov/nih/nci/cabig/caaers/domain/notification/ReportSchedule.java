package gov.nih.nci.cabig.caaers.domain.notification;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * A report sending schedule for an adverse event. 
 * The RuleExecutionService, evaluates pre-defined set of rules over the attributes of an AdverseEvent,
 * and creates a ReportSchedule.  
 * 
 * @author Biju Joseph
 *
 */
@Entity
@Table(name = "REPORT_SCHEDULES")
@GenericGenerator(name = "id-generator", strategy = "native",
    parameters = {
        @Parameter(name = "sequence", value = "seq_report_schedules_id")
    }
)
public class ReportSchedule extends AbstractMutableDomainObject implements Serializable{
	
	private String name;
	
	private AdverseEventReport aeReport;
	
	private ReportCalendarTemplate rcTemplate;
	
	/** The list of notificaiton that are to be scheduled */
	private List<ScheduledNotification> notifications;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@Column(name="DUE_ON")
	private Date dueOn;
	
	@Column(name="SUBMITTED_ON")
	private Date submittedOn;
	
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rct_id")
	public ReportCalendarTemplate getReportCalendarTemplate(){
		return rcTemplate;
	}
	
	public void setReportCalendarTemplate(ReportCalendarTemplate rcTemplate){
		this.rcTemplate = rcTemplate;
	}
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="rpsh_id", nullable=false)
	@Cascade(value = { CascadeType.ALL })
	public List<ScheduledNotification> getScheduledNotifications() {
		return notifications;
	}
	public void setScheduledNotifications(List<ScheduledNotification> notifications) {
		this.notifications = notifications;
	}
	
	
	/**
	 * @return the aeReport
	 */
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="report_id")
	public AdverseEventReport getAeReport() {
		return aeReport;
	}
	/**
	 * @param aeReport the aeReport to set
	 */
	public void setAeReport(AdverseEventReport aeReport) {
		this.aeReport = aeReport;
	}
	/**
	 * @return the dueOn
	 */
	public Date getDueOn() {
		return dueOn;
	}
	/**
	 * @param dueOn the dueOn to set
	 */
	public void setDueOn(Date dueOn) {
		this.dueOn = dueOn;
	}
	/**
	 * @return the submittedOn
	 */
	public Date getSubmittedOn() {
		return submittedOn;
	}
	/**
	 * @param submittedOn the submittedOn to set
	 */
	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}
	public void addScheduledNotification(ScheduledNotification nf){
		if(notifications == null)
			notifications = new ArrayList<ScheduledNotification>();
		notifications.add(nf);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
//	/**
//	 * The email notifications will get associated to a ReportScdule when a  ReportCalendarTemplate is applied. 
//	 * @param template 
//	 */
//	public void applyNotificationCalendarTemplate(ReportCalendarTemplate template){
//		List<PlannedNotification> plannedNotifications = template.getEventList();
//		Date now = new Date();
//		Calendar calendar = GregorianCalendar.getInstance();
//		int i =0;
//		for(PlannedNotification event: plannedNotifications){
//			//copy all the necessary attributes from event to email notificatoin
//			ScheduledNotification nf = new ScheduledNotification();
//			nf.setId(i++);
//			nf.setContent(event.getBodyContent().getContent());
//			nf.setContentType(event.getBodyContent().getContentType());
//			nf.setSubject(event.getSubjectLine());
//			nf.setCreatedOn(new Date());
//			nf.setFromAddress(event.getFromAddress());
//			nf.setRecipients(event.getRecipients());
//			//set the scheduled time of notification
//			calendar.setTime(now);
//			calendar.add(template.getTimeScaleUnitType().getCalendarTypeCode(), event.getIndexOnTimeScale());
//			nf.setScheduledOn(calendar.getTime());
//			addEmailNotification(nf);
//		}
//	}
	
}

