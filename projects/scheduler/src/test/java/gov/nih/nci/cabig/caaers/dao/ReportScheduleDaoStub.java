package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.notification.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.notification.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.notification.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.notification.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.notification.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.notification.Recipient;
import gov.nih.nci.cabig.caaers.domain.notification.ReportCalendarTemplate;
import gov.nih.nci.cabig.caaers.domain.notification.ReportSchedule;
import gov.nih.nci.cabig.caaers.domain.notification.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.notification.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.notification.ScheduledNotification;
import gov.nih.nci.cabig.caaers.domain.notification.TimeScaleUnit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;


public class ReportScheduleDaoStub extends ReportScheduleDao {
	
	HashMap<Object,ReportSchedule> map = new HashMap<Object, ReportSchedule>();
	
	public ReportScheduleDaoStub() {
		init(-444);
		init(-885);
	}
	public void init(int reportId){
		ReportSchedule rs = getDummyReportSchedule(reportId);
		map.put(rs.getName(), rs);

	}
	@Override
	public ReportSchedule getById(int id) {
		logger.debug("ReportScheduleDAOStub : getById(" + id +")");
		for(ReportSchedule rs : map.values()){
			if(rs.getId() == id) return rs;
		}
		logger.debug("ReportScheduleDAOStub : getById(returning NULL )");
		return null;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.dao.ReportScheduleDao#getByName(java.lang.String)
	 */
	@Override
	public ReportSchedule getByName(String name) {
		logger.debug("ReportScheduleDAO Stub : getByName(" + name +"), will return " + map.containsKey(name));
		return map.get(name);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.dao.ReportScheduleDao#save(gov.nih.nci.cabig.caaers.domain.notification.ReportSchedule)
	 */
	@Override
	public void save(ReportSchedule rs) {
		logger.debug("ReportScheduleDaoStub : save()\r\n" + String.valueOf(rs));
		map.put(rs.getName(), rs);
	}
	

	public ReportSchedule getDummyReportSchedule(final int reportId){
		
		ReportCalendarTemplate rct = null;
		AdverseEventReport aeReport = new AdverseEventReport(){
			int callCount = 0;
			public ReportStatus getStatus(){
				callCount++;
				if(reportId == -885 && callCount > 1)
					return ReportStatus.COMPLETED;
				return super.getStatus();
			}
		};
		aeReport.setStatus(ReportStatus.PENDING);
		aeReport.setId(reportId);
		
		ReportSchedule rs = null;
		
	
		Calendar cal = GregorianCalendar.getInstance();
		Date now = new Date();
		
		List<Recipient> rList = new ArrayList<Recipient>();
		rList.add(new RoleBasedRecipient("Reporter"));
		rList.add(new ContactMechanismBasedRecipient("biju@gmail.com"));
		
		NotificationBodyContent content = new NotificationBodyContent();
		content.setBody("This is my body".getBytes());
		
		List<ScheduledNotification> snfList = new ArrayList<ScheduledNotification>();
		List<PlannedNotification> pnfList = new ArrayList<PlannedNotification>();
		
		

		rct = new ReportCalendarTemplate();
		rct.setDescription("a rct description");
		rct.setDuration(5);
		rct.setId(-333);
		rct.setName("An RCT");
		rct.setTimeScaleUnitType(TimeScaleUnit.MINUTE);
		rct.setPlannedNotifications(pnfList);
		

		rs = new ReportSchedule();
		rs.setName("24Hour5Day("+ reportId+")");
		rs.setCreatedOn(new Date());
		rs.setId(reportId);
		rs.setReportCalendarTemplate(rct);
		rs.setAeReport(aeReport);
		aeReport.setReportSchedule(rs);
		rs.setScheduledNotifications(snfList); 
		
		PlannedEmailNotification penf = new PlannedEmailNotification();
		penf.setFromAddress("biju.joseph@semanticbits.com");
		penf.setId(-3331);
		penf.setIndexOnTimeScale(1);
		penf.setSubjectLine("Subject Line for day " + penf.getIndexOnTimeScale());
		penf.setNotificationBodyContent(content);
		penf.setRecipients(rList);
		pnfList.add(penf);
		
		cal.setTime(now);
		cal.add(rct.getTimeScaleUnitType().getCalendarTypeCode(), penf.getIndexOnTimeScale());
		ScheduledEmailNotification senf = new ScheduledEmailNotification();
		senf.setId(reportId - 11);
		senf.setBody(penf.getNotificationBodyContent().getBody());
		senf.setCreatedOn(new Date());
		senf.setDeliveryStatus(DeliveryStatus.CREATED);
		senf.setFromAddress(penf.getFromAddress());
		senf.setPlanedNotificaiton(penf);
		senf.setScheduledOn(cal.getTime());
		senf.setToAddress("biju.joseph@semanticbits.com");
		snfList.add(senf);
		
		
		
		penf = new PlannedEmailNotification();
		penf.setFromAddress("biju.joseph@semanticbits.com");
		penf.setId(-3332);
		penf.setIndexOnTimeScale(2);
		penf.setSubjectLine("Subject Line for day " + penf.getIndexOnTimeScale());
		penf.setNotificationBodyContent(content);
		penf.setRecipients(rList);
		pnfList.add(penf);
		

		cal.setTime(now);
		cal.add(rct.getTimeScaleUnitType().getCalendarTypeCode(), penf.getIndexOnTimeScale());
	    senf = new ScheduledEmailNotification();
	    senf.setId(reportId - 12);
		senf.setBody(penf.getNotificationBodyContent().getBody());
		senf.setCreatedOn(new Date());
		senf.setDeliveryStatus(DeliveryStatus.CREATED);
		senf.setFromAddress(penf.getFromAddress());
		senf.setPlanedNotificaiton(penf);
		senf.setScheduledOn(cal.getTime());
		senf.setToAddress("biju.joseph@semanticbits.com");
		snfList.add(senf);
		
		
		penf = new PlannedEmailNotification();
		penf.setFromAddress("biju.joseph@semanticbits.com");
		penf.setId(-3333);
		penf.setIndexOnTimeScale(3);
		penf.setSubjectLine("Subject Line for day " + penf.getIndexOnTimeScale());
		penf.setNotificationBodyContent(content);
		penf.setRecipients(rList);
		pnfList.add(penf);
		

		cal.setTime(now);
		cal.add(rct.getTimeScaleUnitType().getCalendarTypeCode(), penf.getIndexOnTimeScale());
	    senf = new ScheduledEmailNotification();
	    senf.setId(reportId - 13);
		senf.setBody(penf.getNotificationBodyContent().getBody());
		senf.setCreatedOn(new Date());
		senf.setDeliveryStatus(DeliveryStatus.CREATED);
		senf.setFromAddress(penf.getFromAddress());
		senf.setPlanedNotificaiton(penf);
		senf.setScheduledOn(cal.getTime());
		senf.setToAddress("biju.joseph@semanticbits.com");
		snfList.add(senf);
		
		return rs;
	}
	
	
}
