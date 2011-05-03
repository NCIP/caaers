package gov.nih.nci.cabig.caaers.dao;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

public class PersonDaoTest extends CaaersDbNoSecurityTestCase  {
	private PersonDao personDao;
	private UserDao userDao;
	private OrganizationDao organizationDao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		personDao = (PersonDao)getDeployedApplicationContext().getBean("personDao");
		userDao = (UserDao)getDeployedApplicationContext().getBean("userDao");
		organizationDao = (OrganizationDao)getDeployedApplicationContext().getBean("organizationDao");
	}
	
	public void testGetByEmailAddress(){
		Person p = personDao.getByEmailAddress("ki@def.com");
		assertNotNull(p);
	}
	
	//Investigator as Person Only
	public void testSavePersonAsInvestigatorOnly(){
		
		LocalInvestigator inv = new LocalInvestigator();
		inv.setFirstName("Monish");
		inv.setMiddleName("M");
		inv.setLastName("Dombla");
		inv.setEmailAddress("monish.dombla@semanticbits.com");
		inv.setPhoneNumber("703-123-1234");
		inv.setFaxNumber("703-123-1234");
		
		SiteInvestigator siteInv = new SiteInvestigator();
		Organization org = organizationDao.getById(-1003);
		siteInv.setOrganization(org);
		inv.addSiteInvestigator(siteInv);
		
		personDao.save(inv);
		
		interruptSession();
		
		Person p = personDao.getByEmailAddress("monish.dombla@semanticbits.com");
		assertNotNull(p);
		assertTrue(p instanceof LocalInvestigator);
		assertEquals("Monish", p.getFirstName());
		assertNotNull(((LocalInvestigator)p).getSiteInvestigators());
		assertEquals(1,((LocalInvestigator)p).getSiteInvestigators().size());
		assertNull(p.getCaaersUser());
	}


    public void testDeactivateStudyInvestigator(){
        {
            LocalInvestigator inv = new LocalInvestigator();
            inv.setFirstName("Monish");
            inv.setMiddleName("M");
            inv.setLastName("Dombla");
            inv.setEmailAddress("bijujoseph@semanticbits.com");
            inv.setPhoneNumber("703-123-1234");
            inv.setFaxNumber("703-123-1234");

            SiteInvestigator siteInv = new SiteInvestigator();
            Organization org = organizationDao.getById(-1003);
            siteInv.setStartDate(DateUtils.today());
            siteInv.setOrganization(org);
            inv.addSiteInvestigator(siteInv);

            personDao.save(inv);
        }
        interruptSession();
        {
          Person p = personDao.getByEmailAddress("bijujoseph@semanticbits.com");
		  assertNotNull(p);
          Investigator i = (Investigator)p;
          assertNull(i.getSiteInvestigators().get(0).getEndDate());
          assertTrue(i.getSiteInvestigators().get(0).isActive());
          personDao.deactivateStudyInvestigator(i.getSiteInvestigators().get(0));

        }
        interruptSession();
        {
           Person p = personDao.getByEmailAddress("bijujoseph@semanticbits.com");
		  assertNotNull(p);
          Investigator i = (Investigator)p;
          assertFalse(i.getSiteInvestigators().get(0).isActive());
        }

    }
	
	//Investigator as Person & User
	public void testSavePersonAsInvestigatorAndUser(){
		
		User user = new User();
		user.setLoginName("monishd");
		userDao.save(user);
		
		LocalInvestigator inv = new LocalInvestigator();
		inv.setCaaersUser(user);
		inv.setFirstName("Monish");
		inv.setMiddleName("M");
		inv.setLastName("Dombla");
		inv.setEmailAddress("monish.dombla@semanticbits.com");
		inv.setPhoneNumber("703-123-1234");
		inv.setFaxNumber("703-123-1234");
		
		SiteInvestigator siteInv = new SiteInvestigator();
		Organization org = organizationDao.getById(-1003);
		siteInv.setOrganization(org);
		inv.addSiteInvestigator(siteInv);
		
		personDao.save(inv);
		
		interruptSession();
		
		Person p = personDao.getByEmailAddress("monish.dombla@semanticbits.com");
		assertNotNull(p);
		assertTrue(p instanceof LocalInvestigator);
		assertEquals("Monish", p.getFirstName());
		assertNotNull(((LocalInvestigator)p).getSiteInvestigators());
		assertEquals(1,((LocalInvestigator)p).getSiteInvestigators().size());
		assertNotNull(p.getCaaersUser());
		assertEquals("monishd", p.getCaaersUser().getLoginName());
	}
	
	//ResearchStaff as Person & User
	public void testSavePersonAsResearchStaffOnly(){
		
		LocalResearchStaff rs = new LocalResearchStaff();
		rs.setFirstName("Biju");
		rs.setMiddleName("P");
		rs.setLastName("Joseph");
		rs.setPhoneNumber("123-123-1234");
		rs.setFaxNumber("111-111-1111");
		rs.setEmailAddress("biju.joseph@semanticbits.com");
		
		Address rsAddress = new Address();
		rsAddress.setCity("Herndon");
		rsAddress.setState("VA");
		rsAddress.setZip("20171");
		rsAddress.setCountry("USA");
		rs.setAddress(rsAddress);
		
		SiteResearchStaff srs =  new SiteResearchStaff();
		Organization org = organizationDao.getById(-1003);
		srs.setOrganization(org);
		rs.addSiteResearchStaff(srs);
		
		personDao.save(rs);
		
		interruptSession();
		
		Person p = personDao.getByEmailAddress("biju.joseph@semanticbits.com");
		assertNotNull(p);
		assertEquals("biju.joseph@semanticbits.com", p.getEmailAddress());
	}	
	
	//ResearchStaff as Person & User
	public void testSavePersonAsResearchStaffAndUser(){
		User user = new User();
		user.setLoginName("bijuj");
		userDao.save(user);
		
		LocalResearchStaff rs = new LocalResearchStaff();
		rs.setFirstName("Biju");
		rs.setMiddleName("P");
		rs.setLastName("Joseph");
		rs.setPhoneNumber("123-123-1234");
		rs.setFaxNumber("111-111-1111");
		rs.setEmailAddress("biju.joseph@semanticbits.com");
		rs.setCaaersUser(user);
		
		Address rsAddress = new Address();
		rsAddress.setCity("Herndon");
		rsAddress.setState("VA");
		rsAddress.setZip("20171");
		rsAddress.setCountry("USA");
		rs.setAddress(rsAddress);
		
		SiteResearchStaff srs =  new SiteResearchStaff();
		Organization org = organizationDao.getById(-1003);
		srs.setOrganization(org);
		rs.addSiteResearchStaff(srs);
		
		personDao.save(rs);
		
		interruptSession();
		
		Person p = personDao.getByEmailAddress("biju.joseph@semanticbits.com");
		assertNotNull(p);
	}
	
	public void testGetPersonByLoginId(){
		Person p = personDao.getByLoginId("sunil");
		assertNotNull(p);
		assertTrue(p instanceof LocalResearchStaff);
		assertNotNull(p.getCaaersUser());
		assertEquals("sunil", p.getCaaersUser().getLoginName());
		assertEquals("Bill", p.getFirstName());
		assertEquals("big@def.com", p.getEmailAddress());
	}
	
	public void testGetPersonById(){
		Person p = personDao.getById(-200);
		assertNotNull(p);
		assertTrue(p instanceof LocalInvestigator);
		assertNotNull(p.getCaaersUser());
		assertEquals("karthiki", p.getCaaersUser().getLoginName());
		assertEquals("karthik", p.getFirstName());
		assertEquals("ki@def.com", p.getEmailAddress());
	}
}
