package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.IntegrationLogQuery;
import gov.nih.nci.cabig.caaers.domain.IntegrationLog;
import gov.nih.nci.cabig.caaers.domain.SynchStatus;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Date;
import java.util.List;

public class IntegrationLogDaoTest extends DaoNoSecurityTestCase<IntegrationLogDao>{
	
	private IntegrationLogDao dao = (IntegrationLogDao) getApplicationContext().getBean("integrationLogDao");
	
	public void testGetByEntity() throws Exception {
		IntegrationLogQuery query = new IntegrationLogQuery();
		query.filterByEntity("Organization");
		List<IntegrationLog> queriedLogs = dao.searchIntegrationLog(query);
		assertEquals(1,queriedLogs.size());
		assertEquals("createOrUpdate",queriedLogs.get(0).getOperation());
		assertEquals(5,queriedLogs.get(0).getSynchStatus().getCode().intValue());
		
		// test case insensitivity
		query.filterByEntity("organization");
		queriedLogs = dao.searchIntegrationLog(query);
		assertEquals(1,queriedLogs.size());
	}
	
	
	public void testSaveIntegrationLog() throws Exception {
		IntegrationLog intLog = new IntegrationLog();
		intLog.setCorrelationId("2");
		intLog.setEntity("PriorTherapy");
		intLog.setLoggedOn(new Date());
		intLog.setOperation("importPriorTherapy");
		intLog.setSynchStatus(SynchStatus.CAAERS_WS_INVOCATION);
		intLog.setNotes("invoking caaers webservice for importing prior therapies");
		dao.save(intLog);
		
		interruptSession();
		
		IntegrationLogQuery query = new IntegrationLogQuery();
		query.filterByOperation("importPriorTherapy");
		List<IntegrationLog> queriedLogs = dao.searchIntegrationLog(query);
		assertEquals(1,queriedLogs.size());
		assertEquals("importPriorTherapy",queriedLogs.get(0).getOperation());
		assertEquals(65,queriedLogs.get(0).getSynchStatus().getCode().intValue());
	}
	
	public void testQueryByDate() throws Exception {
		IntegrationLogQuery query1 = new IntegrationLogQuery();
		Date queryDate1 = DateUtils.parseDate("04/23/2012");
		query1.filterByLoggedOn(queryDate1, "<");
		List<IntegrationLog> queriedLogs = dao.searchIntegrationLog(query1);
		assertEquals(6,queriedLogs.size());
		
		IntegrationLogQuery query2 = new IntegrationLogQuery();
		Date queryDate2 = DateUtils.parseDate("04/22/2012");
		query2.filterByLoggedOn(queryDate2, "<");
		queriedLogs = dao.searchIntegrationLog(query2);
		assertEquals(3,queriedLogs.size());
		
		IntegrationLogQuery query3 = new IntegrationLogQuery();
		Date queryDate3 = DateUtils.parseDate("04/22/2012");
		query3.filterByLoggedOn(queryDate3, "<=");
		queriedLogs = dao.searchIntegrationLog(query3);
		assertEquals(6,queriedLogs.size());
	}

}
