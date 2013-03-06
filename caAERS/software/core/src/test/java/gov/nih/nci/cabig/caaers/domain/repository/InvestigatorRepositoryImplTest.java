/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.repository;

import static org.easymock.EasyMock.expect;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.InvestigatorConverterDao;
import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.event.EventFactory;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacade;
import gov.nih.nci.cabig.caaers.security.CaaersSecurityFacadeImpl;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is the repository class for managing investigators
 *
 * @author Biju Joseph
 */
public class InvestigatorRepositoryImplTest extends AbstractTestCase {
    InvestigatorRepositoryImpl repositoryImpl;
    InvestigatorDao investigatorDao;
    CaaersSecurityFacade caaersSecurityFacade;
    OrganizationDao organizationDao;
    SiteInvestigatorDao siteInvestigatorDao;
    EventFactory eventFactory;
    InvestigatorConverterDao investigatorConverterDao;

    protected void setUp() throws Exception {
        super.setUp();
        eventFactory = registerMockFor(EventFactory.class);
        repositoryImpl = new InvestigatorRepositoryImpl();
        investigatorDao = registerDaoMockFor(InvestigatorDao.class);
        organizationDao = registerDaoMockFor(OrganizationDao.class);
        investigatorConverterDao = registerDaoMockFor(InvestigatorConverterDao.class);
        siteInvestigatorDao = registerDaoMockFor(SiteInvestigatorDao.class);
        repositoryImpl.setInvestigatorDao(investigatorDao);
        repositoryImpl.setInvestigatorConverterDao(investigatorConverterDao);
        caaersSecurityFacade = registerMockFor(CaaersSecurityFacade.class);
        repositoryImpl.setCaaersSecurityFacade(caaersSecurityFacade);
        repositoryImpl.setEventFactory(eventFactory);
        repositoryImpl.setAuthenticationMode("local");
        repositoryImpl.setOrganizationDao(organizationDao);
        repositoryImpl.setSiteInvestigatorDao(siteInvestigatorDao);
        assertSame(eventFactory, repositoryImpl.getEventFactory());
        assertSame(investigatorDao, repositoryImpl.getInvestigatorDao());
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

    public void testSearchInvestigator_EmptyCriteria() throws Exception {
        InvestigatorQuery query = new InvestigatorQuery();
        HashMap searchCriteriaMap = new HashMap();
        List<Investigator> investigators = new ArrayList<Investigator>();
        expect(investigatorDao.getLocalInvestigator(query)).andReturn(investigators);
        replayMocks();

        List<Investigator> invList = repositoryImpl.searchInvestigator(query, searchCriteriaMap);
        assertSame(investigators, invList);
        verifyMocks();
    }


    public void testSearchInvestigator_OnFirstName() throws Exception {
        InvestigatorQuery query = new InvestigatorQuery();
        HashMap criteria = new HashMap();
        criteria.put("firstName", "%");
        List<Investigator> investigators = new ArrayList<Investigator>();
        expect(investigatorDao.getLocalInvestigator(query)).andReturn(investigators);

        replayMocks();

        List<Investigator> invList = repositoryImpl.searchInvestigator(query, criteria);
        assertSame(investigators, invList);
        verifyMocks();
    }


    public void testSearchInvestigator_OnLastName() throws Exception {
        InvestigatorQuery query = new InvestigatorQuery();
        HashMap criteria = new HashMap();
        criteria.put("lastName", "%");
        List<Investigator> investigators = new ArrayList<Investigator>();
        expect(investigatorDao.getLocalInvestigator(query)).andReturn(investigators);
        replayMocks();

        List<Investigator> invList = repositoryImpl.searchInvestigator(query, criteria);
        assertSame(investigators, invList);
        verifyMocks();
    }


    public void testSearchInvestigator_OnNciIdentifier() throws Exception {
        InvestigatorQuery query = new InvestigatorQuery();
        HashMap criteria = new HashMap();
        criteria.put("personIdentifier", "%");
        List<Investigator> investigators = new ArrayList<Investigator>();
        expect(investigatorDao.getLocalInvestigator(query)).andReturn(investigators);
        replayMocks();

        List<Investigator> invList = repositoryImpl.searchInvestigator(query, criteria);
        assertSame(investigators, invList);
        verifyMocks();
    }


    public void testSearchInvestigator_OnLoginId() throws Exception {
        InvestigatorQuery query = new InvestigatorQuery();
        query.filterByLoginId("SYSTEM_ADMIN");
        HashMap criteria = new HashMap();

        List<Investigator> investigators = new ArrayList<Investigator>();
        expect(investigatorDao.getLocalInvestigator(query)).andReturn(investigators);
        replayMocks();

        List<Investigator> invList = repositoryImpl.searchInvestigator(query, criteria);
        assertSame(investigators, invList);
        verifyMocks();
    }


    public void testSearchInvestigator_OnlyRemoteInvestigators() throws Exception {
        InvestigatorQuery query = new InvestigatorQuery();
        HashMap criteria = new HashMap();
        criteria.put("organization", "1");
        Organization org = Fixtures.createOrganization("test", 1);

        List<Investigator> investigators = new ArrayList<Investigator>();
        expect(investigatorDao.getLocalInvestigator(query)).andReturn(investigators);
        expect(organizationDao.getById(1)).andReturn(org);
        expect(investigatorDao.getRemoteInvestigators((RemoteInvestigator) EasyMock.anyObject())).andReturn(investigators);
        replayMocks();

        List<Investigator> invList = repositoryImpl.searchInvestigator(query, criteria);
        assertSame(investigators, invList);
        verifyMocks();
    }

    public void testGetBySubnames() throws Exception {
        List<SiteInvestigator> siteInvestigators = new ArrayList<SiteInvestigator>();
        String[] subNames = new String[]{"a", "b"};
        expect(siteInvestigatorDao.getBySubnames(subNames, 1)).andReturn(siteInvestigators);
        replayMocks();
        List<SiteInvestigator> siteInvList = repositoryImpl.getBySubnames(subNames, 1);
        assertSame(siteInvestigators, siteInvList);
        verifyMocks();
    }

    public void testGetById() throws Exception {
        Investigator inv = Fixtures.createInvestigator("x");
        expect(investigatorDao.getInvestigatorById(1)).andReturn(inv);
        replayMocks();
        Investigator i = repositoryImpl.getById(1);
        assertSame(inv, i);
        verifyMocks();
    }


    public void testConvertToRemote() throws Exception {
        Investigator local = Fixtures.createInvestigator("x");
        local.setId(1);
        RemoteInvestigator remote = Fixtures.createRemoteInvestigator("r1");
        ConverterInvestigator conInv = new ConverterInvestigator();
        expect(investigatorConverterDao.getById(1)).andReturn(conInv);
        investigatorConverterDao.save(conInv);
        replayMocks();
        repositoryImpl.convertToRemote(local, remote);
        assertEquals("REMOTE", conInv.getType());
        assertEquals(remote.getFirstName(), conInv.getFirstName());
        assertEquals(remote.getLastName(), conInv.getLastName());
        assertEquals(remote.getExternalId(), conInv.getExternalId());
        verifyMocks();


    }
}
