package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.ASSIGN_PARTICIPANT;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_PARTICIPANT;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.IMPORT_PARTICIPANTS;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.*;

import java.util.List;

/**
 * @author Ion C. Olaru
 */

@CaaersUseCases({CREATE_PARTICIPANT, ASSIGN_PARTICIPANT, IMPORT_PARTICIPANTS})
public class SiteInvestigatorDaoTest extends DaoNoSecurityTestCase<SiteInvestigatorDao> {

    private OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean("organizationDao");
    private InvestigatorDao investigatorDao = (InvestigatorDao) getApplicationContext().getBean("investigatorDao");

    public void testGetDomainClass(){
        Object obj = getDao().domainClass();
        assertNotNull(obj);
    }

    public void testGetOrganizationInvestigator() {
        Organization o = organizationDao.getById(-1003);
        Investigator i = investigatorDao.getById(-3004);
        SiteInvestigator si = getDao().getOrganizationInvestigator(o, i);
        assertNotNull(si);
        assertEquals(-4004, si.getId().intValue());
    }

    public void testGetOrganizationInvestigators() {
        Organization o = organizationDao.getById(-1003);
        List<SiteInvestigator> all = getDao().getOrganizationInvestigators(o);
        assertNotNull(all);
        assertEquals(2, all.size());
    }

    public void testGetBySubnames() {
        Organization o = organizationDao.getById(-1003);
        List<SiteInvestigator> all = getDao().getBySubnames(new String[] {"Aa"}, o.getId());
        assertNotNull(all);
        assertEquals(1, all.size());
        assertEquals("312-555-0102", all.get(0).getInvestigator().getPhoneNumber());
    }

    public void testGetByNciIdentifier() {
        Organization o = organizationDao.getById(-1003);
        List<SiteInvestigator> all = getDao().getByNciIdentifier(new String[] {"NCI_ID-01"}, o.getId());
        assertNotNull(all);
        assertEquals(1, all.size());
        assertEquals("312-555-0102", all.get(0).getInvestigator().getPhoneNumber());
    }

}