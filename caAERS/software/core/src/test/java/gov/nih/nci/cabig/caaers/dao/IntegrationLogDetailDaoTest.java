package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.query.IntegrationLogDetailQuery;
import gov.nih.nci.cabig.caaers.dao.query.IntegrationLogQuery;
import gov.nih.nci.cabig.caaers.domain.IntegrationLog;
import gov.nih.nci.cabig.caaers.domain.IntegrationLogDetail;
import gov.nih.nci.cabig.caaers.domain.SynchStatus;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.Date;
import java.util.List;

public class IntegrationLogDetailDaoTest extends DaoNoSecurityTestCase<IntegrationLogDetailDao>{
	
	private IntegrationLogDetailDao dao = (IntegrationLogDetailDao) getApplicationContext().getBean("integrationLogDetailDao");
	private IntegrationLogDao integrationLogDao = (IntegrationLogDao) getApplicationContext().getBean("integrationLogDao");
	
	public void testGetByEntity() throws Exception {
		IntegrationLogDetailQuery query = new IntegrationLogDetailQuery();
		query.filterBySynchStatus(SynchStatus.REQUEST_RECEIVED);
		List<IntegrationLogDetail> queriedIntLogDetails = dao.searchIntegrationLogDetails(query);
		assertEquals(1,queriedIntLogDetails.size());
		assertEquals(5,queriedIntLogDetails.get(0).getSynchStatus().getCode().intValue());
		
		// test case insensitivity
		query.filterByBusinessId("-1");
		queriedIntLogDetails = dao.searchIntegrationLogDetails(query);
		assertEquals(1,queriedIntLogDetails.size());
	}
	
	
	public void testSaveIntegrationDetailLog() throws Exception {
		IntegrationLogDetail intDetailLog = new IntegrationLogDetail();
		intDetailLog.setBusinessId("2");
		intDetailLog.setSynchStatus(SynchStatus.CAAERS_WS_INVOCATION_INITIATED);
		intDetailLog.setOutcome("success");
		
		IntegrationLogQuery intLogquery = new IntegrationLogQuery();
		intLogquery.filterByEntity("Organization");
		List<IntegrationLog> queriedLogs = integrationLogDao.searchIntegrationLogs(intLogquery);
		
		queriedLogs.get(0).getIntegrationLogDetails().add(intDetailLog);
		assertEquals(1,queriedLogs.get(0).getIntegrationLogDetails().size());
		
		
		integrationLogDao.save(queriedLogs.get(0));
		
		interruptSession();
		
		IntegrationLogQuery query = new IntegrationLogQuery();
		query.filterByCorrelationId("-1");
		List<IntegrationLog> queriedIntLogs = integrationLogDao.searchIntegrationLogs(query);
		
		assertEquals(2,queriedIntLogs.get(0).getIntegrationLogDetails().size());
		assertEquals(65,queriedIntLogs.get(0).getSynchStatus().getCode().intValue());
	}
	
	public void testQueryByDate() throws Exception {
		IntegrationLogQuery query1 = new IntegrationLogQuery();
		Date queryDate1 = DateUtils.parseDate("04/23/2012");
		query1.filterByLoggedOn(queryDate1, "<");
		List<IntegrationLog> queriedIntLogs = integrationLogDao.searchIntegrationLogs(query1);
		assertEquals(6,queriedIntLogs.size());
		
		IntegrationLogQuery query2 = new IntegrationLogQuery();
		Date queryDate2 = DateUtils.parseDate("04/22/2012");
		query2.filterByLoggedOn(queryDate2, "<");
		queriedIntLogs = integrationLogDao.searchIntegrationLogs(query2);
		assertEquals(3,queriedIntLogs.size());
		
		IntegrationLogQuery query3 = new IntegrationLogQuery();
		Date queryDate3 = DateUtils.parseDate("04/22/2012");
		query3.filterByLoggedOn(queryDate3, "<=");
		queriedIntLogs = integrationLogDao.searchIntegrationLogs(query3);
		assertEquals(6,queriedIntLogs.size());
	}

}
