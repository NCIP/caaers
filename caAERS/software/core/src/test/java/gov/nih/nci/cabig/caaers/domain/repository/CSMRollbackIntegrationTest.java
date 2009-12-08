package gov.nih.nci.cabig.caaers.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.security.acegi.csm.authorization.CSMObjectIdGenerator;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.dao.DIAuthorizationDao;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
/**
 * 
 * @author Biju Joseph
 *
 */
public class CSMRollbackIntegrationTest extends CaaersDbNoSecurityTestCase {
	
	private OrganizationRepository organizationRepository;
	private OrganizationDao organizationDao;
	private CSMObjectIdGenerator siteObjectIdGenerator;
	private DIAuthorizationDao authrizationDao;
	private TransactionTemplate transactionTemplate;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		organizationRepository = (OrganizationRepository)getDeployedApplicationContext().getBean("organizationRepository");
		organizationDao = (OrganizationDao)getDeployedApplicationContext().getBean("organizationDao");
		siteObjectIdGenerator = (CSMObjectIdGenerator) getDeployedApplicationContext().getBean("csmObjectIdGenerator");
		authrizationDao = (DIAuthorizationDao) getDeployedApplicationContext().getBean("csmAuthorizationDao");
		transactionTemplate = (TransactionTemplate) getApplicationContext().getBean(
        "transactionTemplate");
	}
	
	//1. will create organization, search for group in CSM
	public void testCreateOrganization(){
		
		String nciCode1 = String.format("A%d", System.currentTimeMillis());
		
		Organization org1 = Fixtures.createOrganization(nciCode1, nciCode1);
		Integer id1;
		{
			organizationRepository.create(org1);
			id1 = org1.getId();
		}
		interruptSession();
		{
			assertNotNull(organizationDao.getByNCIcode(nciCode1));
			assertEquals(id1, organizationDao.getByNCIcode(nciCode1).getId());
			final String siteId = "gov.nih.nci.cabig.caaers.domain.Organization." + nciCode1;
			getJdbcTemplate().execute(new StatementCallback() {
				public Object doInStatement(Statement st) throws SQLException, DataAccessException {
					ResultSet rs = st.executeQuery("select * from csm_group where group_name='" + siteId + "'");
					assertTrue("must have csm groups created" , rs.next());
					return rs.getInt(1);
				}
			});
		}
		
	}
	
	//1. will create organization  & rollback, search in CSM for the group, it should also be rolledback. 
	public void testCreateOrganizationAndRollback(){
		
		String nciCode2 = String.format("B%d", System.currentTimeMillis());
		final Organization org2 = Fixtures.createOrganization(null, nciCode2);
		{
			try {
				 transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					@Override
					protected void doInTransactionWithoutResult(TransactionStatus txStatus) {
						assertTrue("should be a new parent tx", txStatus.isNewTransaction());
						organizationRepository.create(org2);
					} 
				 });
				fail("must throw exception, when creating org without a name");
			} catch (RuntimeException e) {
			}
		}
		interruptSession();
		{
			assertNull(organizationDao.getByNCIcode(nciCode2));
			final String siteId = "gov.nih.nci.cabig.caaers.domain.Organization." + nciCode2;
			getJdbcTemplate().execute(new StatementCallback() {
				public Object doInStatement(Statement st) throws SQLException, DataAccessException {
					ResultSet rs = st.executeQuery("select * from csm_group where group_name='" + siteId + "'");
					assertFalse("must not have csm groups created" , rs.next());
					return null;
				}
			});
		}
		
	}
}
