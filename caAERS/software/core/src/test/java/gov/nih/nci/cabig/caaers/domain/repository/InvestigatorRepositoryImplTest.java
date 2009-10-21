package gov.nih.nci.cabig.caaers.domain.repository;

import static org.easymock.EasyMock.expect;

import org.hibernate.jdbc.Expectation;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
/**
 * This is the repository class for managing investigators
 * @author Biju Joseph
 *
 */
public class InvestigatorRepositoryImplTest extends AbstractTestCase {
	InvestigatorRepositoryImpl repositoryImpl;
	InvestigatorDao investigatorDao;
	CSMUserRepository csmUserRepository;
	protected void setUp() throws Exception {
		super.setUp();
		repositoryImpl = new InvestigatorRepositoryImpl();
		investigatorDao = registerDaoMockFor(InvestigatorDao.class);
		repositoryImpl.setInvestigatorDao(investigatorDao);
		csmUserRepository = registerMockFor(CSMUserRepository.class);
		repositoryImpl.setCsmUserRepository(csmUserRepository);
		repositoryImpl.setAuthenticationMode("local");
	}

	public void testSave() {
		Investigator inv = Fixtures.createInvestigator("Joel");
		Organization org = Fixtures.createOrganization("NCI");
		String changeUrl = "/pages/url";
		
		expect(investigatorDao.merge(inv)).andReturn(inv).anyTimes();
		
		csmUserRepository.createOrUpdateCSMUserAndGroupsForInvestigator(inv, changeUrl);
		
		replayMocks();
		repositoryImpl.save(inv, changeUrl);
		verifyMocks();
	}
	
	public void testSaveWebSso_ValidLogiID() {
		Investigator inv = Fixtures.createInvestigator("Joel");
		inv.setLoginId("loginId");
		Organization org = Fixtures.createOrganization("NCI");
		String changeUrl = "/pages/url";
		expect(investigatorDao.merge(inv)).andReturn(inv).anyTimes();
		csmUserRepository.createOrUpdateCSMUserAndGroupsForInvestigator(inv, changeUrl);
		repositoryImpl.setAuthenticationMode("webSSO");
		replayMocks();
		repositoryImpl.save(inv, changeUrl);
		verifyMocks();
	}
	public void testSaveWebSso() {
		Investigator inv = Fixtures.createInvestigator("Joel");
		Organization org = Fixtures.createOrganization("NCI");
		String changeUrl = "/pages/url";
		expect(investigatorDao.merge(inv)).andReturn(inv).anyTimes();
		csmUserRepository.createOrUpdateCSMUserAndGroupsForInvestigator(inv, changeUrl);
		repositoryImpl.setAuthenticationMode("webSSO");
		replayMocks();
		try {
			repositoryImpl.save(inv, changeUrl);
			fail("should throw validation error");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
	}
}
