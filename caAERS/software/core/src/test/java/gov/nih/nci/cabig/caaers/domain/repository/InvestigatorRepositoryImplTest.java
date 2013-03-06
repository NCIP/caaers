package gov.nih.nci.cabig.caaers.domain.repository;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.event.EventFactory;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeImpl;
/**
 * This is the repository class for managing investigators
 * @author Biju Joseph
 *
 */
public class InvestigatorRepositoryImplTest extends AbstractTestCase {
	InvestigatorRepositoryImpl repositoryImpl;
	InvestigatorDao investigatorDao;
	CaaersSecurityFacade caaersSecurityFacade;
    EventFactory eventFactory;
	protected void setUp() throws Exception {
		super.setUp();
		eventFactory = registerMockFor(EventFactory.class);
        repositoryImpl = new InvestigatorRepositoryImpl();
		investigatorDao = registerDaoMockFor(InvestigatorDao.class);
		repositoryImpl.setInvestigatorDao(investigatorDao);
		caaersSecurityFacade = registerMockFor(CaaersSecurityFacade.class);
		repositoryImpl.setCaaersSecurityFacade(caaersSecurityFacade);
        repositoryImpl.setEventFactory(eventFactory);
		repositoryImpl.setAuthenticationMode("local");

	}

	public void testSave() {
		Investigator inv = Fixtures.createInvestigator("Joel");
		Organization org = Fixtures.createOrganization("NCI");
		String changeUrl = "/pages/url";
		
		expect(investigatorDao.merge(inv)).andReturn(inv).anyTimes();
		
		
		replayMocks();
		repositoryImpl.save(inv, changeUrl);
		verifyMocks();
	}
	
	
	public void testSave_NotAllowedToLogin() {
		Investigator inv = Fixtures.createInvestigator("Joel");
		inv.setAllowedToLogin(false);
		Organization org = Fixtures.createOrganization("NCI");
		String changeUrl = "/pages/url";
		
		expect(investigatorDao.merge(inv)).andReturn(inv).anyTimes();
		
		replayMocks();
		repositoryImpl.save(inv, changeUrl);
		verifyMocks();
	}
}
