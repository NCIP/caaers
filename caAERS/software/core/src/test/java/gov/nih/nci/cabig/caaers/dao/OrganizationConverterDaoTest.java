package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.ConverterOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;

public class OrganizationConverterDaoTest extends DaoNoSecurityTestCase<OrganizationConverterDao> {

	
	private OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean(
    "organizationDao");
	
	public void testSaveFabricatedOrg(){
		
		ConverterOrganization conOrg = new ConverterOrganization();
		conOrg.setName("NAME-1");
		conOrg.setCity("CITY-1");
		conOrg.setState("STATE-1");
		conOrg.setCountry("COUNTRY-1");
		conOrg.setType("LOCAL");
		conOrg.setNciInstituteCode("NCI-1");
		getDao().save(conOrg);
		
		interruptSession();
		
		ConverterOrganization conOrg1 = getDao().getByName("NAME-1");
		
		assertNotNull(conOrg1);
		assertEquals("NAME-1",conOrg1.getName());
		assertEquals("NCI-1", conOrg1.getNciInstituteCode());
		
	}
	
	public void testUpdateFabricatedOrg(){
		
		ConverterOrganization conOrg = getDao().getByName("New Site");
		conOrg.setType("REMOTE");
		conOrg.setExternalId("E-ID");
		getDao().save(conOrg);
		
		interruptSession();
		
		ConverterOrganization conOrg1 = getDao().getByName("New Site");
		
		assertNotNull(conOrg1);
		assertEquals("New Site",conOrg1.getName());
		assertEquals("code 1", conOrg1.getNciInstituteCode());
		assertEquals("REMOTE", conOrg1.getType());
		assertEquals("E-ID", conOrg1.getExternalId());
	}
	
	public void testScenario1(){
		
		Organization localOrg = organizationDao.getById(-1001);
		localOrg.setName("Name-222");
		organizationDao.flush();
		
		ConverterOrganization conOrg = getDao().getById(-1001);
		conOrg.setType("REMOTE");
		conOrg.setExternalId("E-ID");
		getDao().save(conOrg);
		interruptSession();
		
		conOrg = getDao().getById(-1001);
		assertNotNull(conOrg);
		assertEquals("REMOTE", conOrg.getType());
		assertEquals("E-ID", conOrg.getExternalId());
	}
}
