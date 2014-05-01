/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.AdverseEventRecommendedReportQuery;
import gov.nih.nci.cabig.caaers.dao.report.ReportDefinitionDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.AdverseEventRecommendedReport;
import gov.nih.nci.cabig.caaers.domain.report.PlannedNotification;
import gov.nih.nci.cabig.caaers.domain.report.ReportDefinition;

import java.util.Date;
import java.util.List;

public class AdverseEventRecommendedReportDaoTest extends DaoNoSecurityTestCase<IntegrationLogDao>{
	
	private AdverseEventRecommendedReportDao dao = (AdverseEventRecommendedReportDao) getApplicationContext().getBean("adverseEventRecommendedReportDao");
	private AdverseEventDao adverseEventDao = (AdverseEventDao) getApplicationContext().getBean("adverseEventDao");
	private ReportDefinitionDao reportDefinitionDao = (ReportDefinitionDao) getApplicationContext().getBean("reportDefinitionDao");
	
	
	public void testSaveAdverseEventRecommendedReport() throws Exception {
		AdverseEventRecommendedReport aeRecomReport = new AdverseEventRecommendedReport();
		AdverseEvent ae = adverseEventDao.getById(-3);
		aeRecomReport.setAdverseEvent(ae);
		
		ReportDefinition rd = reportDefinitionDao.getById(-30);
		aeRecomReport.setReportDefinition(rd);
		
		aeRecomReport.setAeReported(false);
		aeRecomReport.setDueDate(new Date());
		
		dao.save(aeRecomReport);
		
		interruptSession();
		
		List<AdverseEventRecommendedReport> recomReports = dao.searchAdverseEventRecommendedReportsByAdverseEvent(ae);
		
		assertNotNull(recomReports);
		assertEquals("Wrong size", 1, recomReports.size());
		assertNotNull(recomReports.get(0).getAdverseEvent());
		assertNotNull(recomReports.get(0).getReportDefinition());
		assertFalse(recomReports.get(0).getAeReported());
		
		recomReports = dao.getAllUnreportedRecords();
		
		assertNotNull(recomReports);
		assertEquals("Wrong size", 1, recomReports.size());
	}
	
	
	public void testGetAllUnreportedRecords() throws Exception {
		AdverseEventRecommendedReport aeRecomReport = new AdverseEventRecommendedReport();
		AdverseEvent ae = adverseEventDao.getById(-3);
		aeRecomReport.setAdverseEvent(ae);
		
		ReportDefinition rd = reportDefinitionDao.getById(-30);
		aeRecomReport.setReportDefinition(rd);
		
		aeRecomReport.setAeReported(true);
		aeRecomReport.setDueDate(new Date());
		
		dao.save(aeRecomReport);
		
		interruptSession();
		
		List<AdverseEventRecommendedReport> recomReports = dao.getAllUnreportedRecords();
		
		assertNotNull(recomReports);
		assertEquals("Wrong size", 0, recomReports.size());
		
	}
	
	public void testSearchAdverseEventRecommendedReport() throws Exception {
		AdverseEventRecommendedReport aeRecomReport = new AdverseEventRecommendedReport();
		AdverseEvent ae = adverseEventDao.getById(-3);
		aeRecomReport.setAdverseEvent(ae);
		
		ReportDefinition rd = reportDefinitionDao.getById(-30);
		aeRecomReport.setReportDefinition(rd);
		
		aeRecomReport.setAeReported(false);
		aeRecomReport.setDueDate(new Date());
		
		dao.save(aeRecomReport);
		
		interruptSession();
		
		AdverseEventRecommendedReportQuery query = new AdverseEventRecommendedReportQuery();
		query.filterByAdverseEvent(ae.getId());
		
		List<AdverseEventRecommendedReport> recomReports = dao.searchAdverseEventRecommendedReports(query);
		
		assertNotNull(recomReports);
		assertEquals("Wrong size", 1, recomReports.size());
		assertNotNull(recomReports.get(0).getAdverseEvent());
		assertNotNull(recomReports.get(0).getReportDefinition());
		assertFalse(recomReports.get(0).getAeReported());
		
		recomReports = dao.getAllUnreportedRecords();
		
		assertNotNull(recomReports);
		assertEquals("Wrong size", 1, recomReports.size());
	}
	
	public void testGetUnreportedReportDefinition() throws Exception {
		AdverseEventRecommendedReport aeRecomReport = new AdverseEventRecommendedReport();
		AdverseEvent ae = adverseEventDao.getById(-3);
		aeRecomReport.setAdverseEvent(ae);
		
		ReportDefinition rd = reportDefinitionDao.getById(-30);
		aeRecomReport.setReportDefinition(rd);
		
		aeRecomReport.setAeReported(false);
		aeRecomReport.setDueDate(new Date());
		
		dao.save(aeRecomReport);
		
		interruptSession();
		
		List<ReportDefinition> rds = dao.getAllRecommendedReportsNotReported();
		assertNotNull(rds);
		assertEquals("wrong number of report definitions", 1, rds.size());
	}
	
	public void testGetAdverseEventsGivenReportDefinition() throws Exception {
		AdverseEventRecommendedReport aeRecomReport = new AdverseEventRecommendedReport();
		AdverseEvent ae = adverseEventDao.getById(-3);
		aeRecomReport.setAdverseEvent(ae);
		
		ReportDefinition rd = reportDefinitionDao.getById(-30);
		aeRecomReport.setReportDefinition(rd);
		
		aeRecomReport.setAeReported(false);
		aeRecomReport.setDueDate(new Date());
		
		dao.save(aeRecomReport);
		
		interruptSession();
		
		List<AdverseEventRecommendedReport> aeRecomReports = dao.getAllAdverseEventsGivenReportDefinition(rd);
		assertNotNull(aeRecomReports);
		assertEquals("wrong number of recommendation reports", 1, aeRecomReports.size());
		assertEquals("wrong id", ae.getId(), aeRecomReports.get(0).getAdverseEvent().getId());
		assertEquals("wrong ae term", ae.getAdverseEventTerm().getFullName(), aeRecomReports.get(0).getAdverseEvent().getAdverseEventTerm().getFullName());
	}
	
	public void testGetEmailRecipients() throws Exception {
		AdverseEventRecommendedReport aeRecomReport = new AdverseEventRecommendedReport();
		AdverseEvent ae = adverseEventDao.getById(-3);
		aeRecomReport.setAdverseEvent(ae);
		
		ReportDefinition rd = reportDefinitionDao.getById(-30);
		aeRecomReport.setReportDefinition(rd);
		
		aeRecomReport.setAeReported(false);
		aeRecomReport.setDueDate(new Date());
		
		dao.save(aeRecomReport);
		
		interruptSession();
		
		List<ReportDefinition> rds = dao.getAllRecommendedReportsNotReported();
		
		assertNotNull(rds);
		assertEquals("wrong number of report definitions", 1, rds.size());
		assertNotNull(rds.get(0).getPlannedNotifications());
		List<PlannedNotification> unreportedReportAePlannedNotifications = rds.get(0).getUnreportedAePlannedNotification();
		assertEquals("Wrong number of planned notifications", 1, unreportedReportAePlannedNotifications.size());
		assertNotNull(unreportedReportAePlannedNotifications.get(0).getContactMechanismBasedRecipients());
		assertEquals(unreportedReportAePlannedNotifications.get(0).getContactMechanismBasedRecipients().get(0).getContactName(),"jerry@rediffmail.com");
	}
	
	public void testDeleteAdverseEventRecommendationReport() throws Exception {
		AdverseEventRecommendedReport aeRecomReport = new AdverseEventRecommendedReport();
		AdverseEvent ae = adverseEventDao.getById(-3);
		aeRecomReport.setAdverseEvent(ae);
		
		ReportDefinition rd = reportDefinitionDao.getById(-30);
		aeRecomReport.setReportDefinition(rd);
		
		aeRecomReport.setAeReported(false);
		aeRecomReport.setDueDate(new Date());
		
		dao.save(aeRecomReport);
		
		List<AdverseEventRecommendedReport> aeRecomReports = dao.getAllAdverseEventsGivenReportDefinition(rd);
		assertNotNull(aeRecomReports);
		assertEquals("wrong number of recommendation reports", 1, aeRecomReports.size());
		
		Integer savedId = aeRecomReports.get(0).getId();
		
		dao.delete(aeRecomReports.get(0));
		assertNull(dao.getById(savedId));
		
		
	}
	
}
