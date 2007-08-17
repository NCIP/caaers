package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.dao.report.ReportDao;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.ReportStatus;
import gov.nih.nci.cabig.caaers.domain.report.ContactMechanismBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.NotificationBodyContent;
import gov.nih.nci.cabig.caaers.domain.report.PlannedEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.Recipient;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.RoleBasedRecipient;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledEmailNotification;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;
import gov.nih.nci.cabig.caaers.domain.report.TimeScaleUnit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;


public class ReportDaoStub extends ReportDao {

	HashMap<Object,Report> map = new HashMap<Object, Report>();

	public ReportDaoStub() {
		init(-444);
		init(-885);
	}
	public void init(int reportId){
		Report rs = getDummyReportSchedule(reportId);
		map.put(rs.getName() + reportId, rs);

	}
	public Report getById(int id, boolean refresh){
		map.clear();
		init(-444);
		init(-885);
		return getById(id);
	}
	@Override
	public Report getById(int id) {
		logger.debug("ReportScheduleDAOStub : getById(" + id +")");
		for(Report rs : map.values()){
			if(rs.getId() == id) return rs;
		}
		logger.debug("ReportScheduleDAOStub : getById(returning NULL )");
		return null;
	}



	/* (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.dao.ReportScheduleDao#save(gov.nih.nci.cabig.caaers.helper.ReportSchedule)
	 */
	@Override
	public void save(Report rs) {
		logger.debug("ReportDaoStub : save()\r\n" + String.valueOf(rs));
		map.put(rs.getName(), rs);
	}


	public Report getDummyReportSchedule(final int reportId){

		ReportDefinition rct = null;
		ExpeditedAdverseEventReport aeReport = new ExpeditedAdverseEventReport();
		aeReport.setId(reportId);

		Report rs = null;


		Calendar cal = GregorianCalendar.getInstance();
		Date now = new Date();

		List<Recipient> rList = new ArrayList<Recipient>();
		rList.add(new RoleBasedRecipient("Reporter"));
		rList.add(new ContactMechanismBasedRecipient("biju@gmail.com"));

		NotificationBodyContent content = new NotificationBodyContent();
		content.setBody("This is my body");

		List<ScheduledNotification> snfList = new ArrayList<ScheduledNotification>();
		List<PlannedNotification> pnfList = new ArrayList<PlannedNotification>();



		rct = new ReportDefinition();
		rct.setDescription("a rct description");
		rct.setDuration(5);
		rct.setId(-333);
		rct.setName("24 Hr 5Day report");
		rct.setTimeScaleUnitType(TimeScaleUnit.MINUTE);
		rct.setPlannedNotifications(pnfList);


		rs = new Report(){
			int callCount = 0;
			@Override
			public ReportStatus getStatus(){
				callCount++;
				if(reportId == -885 && callCount > 1)
					return ReportStatus.COMPLETED;
				return super.getStatus();
			}
		};
		rs.setStatus(ReportStatus.PENDING);
		//rs.setName("24Hour5Day("+ reportId+")");
		rs.setCreatedOn(new Date());
		rs.setId(reportId);
		rs.setReportDefinition(rct);
		rs.setAeReport(aeReport);
		aeReport.addReport(rs);
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
		senf.setSubjectLine(penf.getSubjectLine());
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
		senf.setSubjectLine(penf.getSubjectLine());
		snfList.add(senf);


		penf = new PlannedEmailNotification();
		penf.setFromAddress("biju.joseph@semanticbits.com");
		penf.setId(-3333);
		penf.setIndexOnTimeScale(3);
		penf.setSubjectLine("Subject Line for day " + penf.getIndexOnTimeScale());
		senf.setSubjectLine(penf.getSubjectLine());
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
		senf.setSubjectLine(penf.getSubjectLine());
		snfList.add(senf);

		return rs;
	}


}
