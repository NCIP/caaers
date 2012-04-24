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
		query.filterByEntity("Organization");
		List<IntegrationLogDetail> queriedIntLogDetails = dao.searchIntegrationLogDetail(query);
		assertEquals(1,queriedIntLogDetails.size());
		assertEquals(5,queriedIntLogDetails.get(0).getSynchStatus().getCode().intValue());
		
		// test case insensitivity
		query.filterByEntity("organization");
		queriedIntLogDetails = dao.searchIntegrationLogDetail(query);
		assertEquals(1,queriedIntLogDetails.size());
	}
	
	
	public void testSaveIntegrationDetailLog() throws Exception {
		IntegrationLogDetail intDetailLog = new IntegrationLogDetail();
		intDetailLog.setBusinessId("2");
		intDetailLog.setSynchStatus(SynchStatus.CAAERS_WS_INVOCATION);
		intDetailLog.setOutcome("success");
		
		IntegrationLogQuery intLogquery = new IntegrationLogQuery();
		intLogquery.filterByEntity("Organization");
		List<IntegrationLog> queriedLogs = integrationLogDao.searchIntegrationLog(intLogquery);
		
		intDetailLog.setIntegrationLog(queriedLogs.get(0));
		
		dao.save(intDetailLog);
		
		interruptSession();
		
		IntegrationLogDetailQuery query = new IntegrationLogDetailQuery();
		query.filterByEntity("Organization");
		query.filterByOperation("createOrUpdate");
		List<IntegrationLogDetail> queriedIntLogDetails = dao.searchIntegrationLogDetail(query);
		assertEquals(2,queriedIntLogDetails.size());
		assertEquals(65,queriedIntLogDetails.get(0).getSynchStatus().getCode().intValue());
	}
	
	public void testQueryByDate() throws Exception {
		IntegrationLogDetailQuery query1 = new IntegrationLogDetailQuery();
		Date queryDate1 = DateUtils.parseDate("04/23/2012");
		query1.filterByLoggedOn(queryDate1, "<");
		List<IntegrationLogDetail> queriedIntLogDetails = dao.searchIntegrationLogDetail(query1);
		assertEquals(6,queriedIntLogDetails.size());
		
		IntegrationLogDetailQuery query2 = new IntegrationLogDetailQuery();
		Date queryDate2 = DateUtils.parseDate("04/22/2012");
		query2.filterByLoggedOn(queryDate2, "<");
		queriedIntLogDetails = dao.searchIntegrationLogDetail(query2);
		assertEquals(3,queriedIntLogDetails.size());
		
		IntegrationLogDetailQuery query3 = new IntegrationLogDetailQuery();
		Date queryDate3 = DateUtils.parseDate("04/22/2012");
		query3.filterByLoggedOn(queryDate3, "<=");
		queriedIntLogDetails = dao.searchIntegrationLogDetail(query3);
		assertEquals(6,queriedIntLogDetails.size());
	}

}
