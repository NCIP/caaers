package gov.nih.nci.cabig.caaers.service;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_NOTIFICATION_RULES;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_REPORT_FORMAT;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.ReportDaoStub;
import gov.nih.nci.cabig.caaers.domain.report.DeliveryStatus;
import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.domain.report.ScheduledNotification;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@CaaersUseCases( { CREATE_NOTIFICATION_RULES, CREATE_REPORT_FORMAT })
public class SchedulerServiceImplTest extends CaaersTestCase {
	static ApplicationContext appCtx;

	SchedulerServiceImpl service;

	public static ApplicationContext getApplicationContext() {
		String[] locations = new String[] { "classpath*:gov/nih/nci/cabig/caaers/applicationContext-*.xml",
				"classpath*:gov/nih/nci/cabig/caaers/testApplicationContext-scheduler.xml" };
		if (appCtx == null) {
			appCtx = new ClassPathXmlApplicationContext(locations);
			SecurityTestUtils.enableAuthorization(false, appCtx);
			SecurityTestUtils.switchToSuperuser();
		}
		return appCtx;
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		service = (SchedulerServiceImpl) getApplicationContext().getBean("schedulerService");
		replayMocks();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		verifyMocks();
		try {
			System.gc();
			Thread.sleep(5000);
		}
		catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public void testGetReportScheduleDao() {
		assertNotNull("ReportScheduleDao should not be null", service.getReportScheduleDao());
	}

	public void testGetScheduler() {
		assertNotNull("Scheduler should not be null", service.getScheduler());
	}

	public void testGetJobClassMapping() {
		assertNotNull("Scheduler Mapping should not be null", service.getJobClassMapping());
	}

	public void testScheduleNotification() throws Exception {
		scheduleJobForReport(-444);
		// verify the deliver status.

		Report rs = service.getReportScheduleDao().getById(-444);
		for (ScheduledNotification snf : rs.getScheduledNotifications()) {
			assertNotEquals("Delivery status", snf.getDeliveryStatus(), DeliveryStatus.SCHEDULED);
		}
	}

	public void testScheduleNotificationWithJobDelete() throws Exception {
		scheduleJobForReport(-885);
		// verify delivery status
		Report rs = service.getReportScheduleDao().getById(-885);
		int i = 0;
		for (ScheduledNotification snf : rs.getScheduledNotifications()) {
			if (i == 0) {
				assertNotNull("Delivery status should not be null", snf.getDeliveryStatus());
				// assertEquals("Delivery status should be delivered for fist Scheduled Notificaiton", snf.getDeliveryStatus(),
				// DeliveryStatus.DELIVERED);
			}
			else {
				assertNotNull("Delivery status should not be null", snf.getDeliveryStatus());
			}
			// assertEquals("Delivery status should be recalled", snf.getDeliveryStatus(), DeliveryStatus.RECALLED);
			i++;
		}
	}

	public void scheduleJobForReport(final int reportId) throws Exception {
		assertNotNull("SchedulerService is not valid", service);
		final Report report = service.getReportScheduleDao().getById(reportId);
		System.out.println("========== going to schedule ==================== [reportId :" + reportId + "]");
		service.scheduleNotification(report);
		Thread t = new Thread() {
			int cnt = 0;

			@Override
			public void run() {
				assertTrue("Thread has ran more than 10 times waiting for the Job to complete", cnt < 10);
				cnt++;
				try {
					Thread.sleep(1000 * 2); // delay 20 seconds, so that the job properly setup in quartz
					while (true) {

						String[] jobNames = service.getScheduler().getJobNames("JG-" + report.getId());

						if (jobNames == null || jobNames.length < 1) {
							break;
						}
						System.out.println(Arrays.asList(jobNames));
						Thread.sleep(1000 * 30); // poll every 30 seconds, for the scheduled jobs in the Scheduler
					}
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}// run
		};

		t.start();
		System.out.println("========= started waiter thread ===================");
		t.join();
	}

	public void testUnscheduleNotification() throws Exception {
		final Report report = ((ReportDaoStub) service.getReportScheduleDao()).getById(-444, true);
		service.scheduleNotification(report);
		service.unScheduleNotification(report);
		for (ScheduledNotification snf : report.getScheduledNotifications()) {
			assertEquals("Status of the scheduled notifications is wrong", snf.getDeliveryStatus(),
					DeliveryStatus.RECALLED);
		}
	}
}
