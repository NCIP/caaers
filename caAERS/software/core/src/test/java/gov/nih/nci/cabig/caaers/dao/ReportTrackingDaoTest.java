package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.report.ReportTrackingDao;
import gov.nih.nci.cabig.caaers.dao.report.ReportVersionDao;
import gov.nih.nci.cabig.caaers.domain.report.ReportTracking;
import gov.nih.nci.cabig.caaers.domain.report.ReportTrackingStatus;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.ctms.lang.NowFactory;

import java.util.List;

public class ReportTrackingDaoTest extends DaoTestCase<ReportTrackingDao> {
	
	private ReportVersionDao reportVersionDao;
	@Override
    protected void setUp() throws Exception {
        super.setUp();
        reportVersionDao = (ReportVersionDao)getApplicationContext().getBean("reportVersionDao");
    }
	
	public void testGetAll() {
		
		List<ReportTracking> rts = getDao().getAll();
		assertEquals(3,rts.size());
		
		ReportTracking rt = rts.get(1);
		assertEquals("attachment generated",rt.getAttachmentGenerated().getStatusMessage());
		assertEquals("error connecting to esb",rt.getConnectedToESB().getStatusMessage());
	
		assertNull(rt.getResponseFromExternalSystem());	
		assertEquals("New Site",rt.getReportVersion().getReport().getReportDefinition().getOrganization().getName());
		
	}
	
	public void testGetAllReports() {
		
		List<ReportVersion> reportVersions = reportVersionDao.getAllWithTracking();
		assertEquals(2,reportVersions.size());
		ReportVersion reportVersion = reportVersions.get(0);
		

		List<ReportTracking> rts = reportVersion.getReportTrackings();
		assertEquals(2,rts.size());
		assertEquals(1,rts.get(0).getAttemptNumber().intValue());
		assertEquals(2,rts.get(1).getAttemptNumber().intValue());
		
		ReportTracking rt = rts.get(0);
		
		assertEquals("attachment generated",rt.getAttachmentGenerated().getStatusMessage());
		assertEquals("error connecting to esb",rt.getConnectedToESB().getStatusMessage());
		assertEquals("error connecting to adeers",rt.getConnectedToExternalSystem().getStatusMessage());
		assertEquals("email failed",rt.getEmailNotification().getStatusMessage());
		assertEquals("email to submitter success",rt.getNotificationToSubmitter().getStatusMessage());

		assertNull(rt.getResponseFromExternalSystem());	
		assertEquals("New Site",reportVersion.getReport().getReportDefinition().getOrganization().getName());
		
	}
	/*
	public void testSave() {
		 NowFactory nowFactory = new NowFactory();
		ReportTrackingStatus status = new ReportTrackingStatus();
		status.setStatus(false);
		status.setStatusMessage("business rule error from AdEERS");
		status.setRecordedTime(nowFactory.getNow());
		ReportTracking rt = new ReportTracking();
		rt.setResponseFromExternalSystem(status);
		rt.setAttemptNumber(1);
		
		getDao().save(rt);
		
		int id = rt.getId();
		ReportTracking savedRT = getDao().getById(id);
		System.out.println(savedRT.getResponseFromExternalSystem().getRecordedTime());
		assertEquals("business rule error from AdEERS",savedRT.getResponseFromExternalSystem().getStatusMessage());
	}
*/

}
