package gov.nih.nci.cabig.caaers.grid;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.InvestigatorQuery;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteInvestigator;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.repository.InvestigatorRepository;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.ccts.domain.AddressType;
import gov.nih.nci.cabig.ccts.domain.CoordinatingCenterStudyStatusType;
import gov.nih.nci.cabig.ccts.domain.HealthcareSiteInvestigatorType;
import gov.nih.nci.cabig.ccts.domain.HealthcareSiteType;
import gov.nih.nci.cabig.ccts.domain.InvestigatorType;
import gov.nih.nci.cabig.ccts.domain.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.ccts.domain.Study;
import gov.nih.nci.cabig.ccts.domain.StudyCoordinatingCenterType;
import gov.nih.nci.cabig.ccts.domain.StudyDataEntryStatusType;
import gov.nih.nci.cabig.ccts.domain.StudyFundingSponsorType;
import gov.nih.nci.cabig.ccts.domain.StudyInvestigatorType;
import gov.nih.nci.cabig.ccts.domain.StudyOrganizationType;
import gov.nih.nci.cabig.ctms.audit.dao.AuditHistoryRepository;
import gov.nih.nci.ccts.grid.studyconsumer.stubs.types.StudyCreationException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;

/**
 * 
 * @author Monish Dombla
 *
 */
public class CaaersStudyConsumerTest extends CaaersTestCase {
    CaaersStudyConsumer studyConsumer;
    protected OrganizationRepository organizationRepository;
    protected InvestigatorRepository investigatorRepository;
    protected ConfigProperty configProperty;
    protected StudyDao studyDao;
    protected SiteInvestigatorDao siteInvestigatorDao;
    protected AuditHistoryRepository auditHistoryRepository;

    protected void setUp() throws Exception {
        super.setUp();
        organizationRepository = (OrganizationRepository) getDeployedApplicationContext().getBean("organizationRepository");
        configProperty = (ConfigProperty) getDeployedApplicationContext().getBean("configurationProperty");
        studyDao = (StudyDao) getDeployedApplicationContext().getBean("studyDao");
        siteInvestigatorDao = (SiteInvestigatorDao) getDeployedApplicationContext().getBean("siteInvestigatorDao");
        auditHistoryRepository = (AuditHistoryRepository) getDeployedApplicationContext().getBean("auditHistoryRepository");
        studyConsumer = new CaaersStudyConsumer();
        studyConsumer.setConfigurationProperty(configProperty);
        studyConsumer.setOrganizationRepository(organizationRepository);
        studyConsumer.setStudyDao(studyDao);
        studyConsumer.setAuditHistoryRepository(auditHistoryRepository);
        studyConsumer.setSiteInvestigatorDao(siteInvestigatorDao);
        studyConsumer.setStudyConsumerGridServiceUrl("/pages/task");
        studyConsumer.setRollbackInterval(1);
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateStudyLocal() throws Exception {
        try {
            Study study = obtainStudyDTO();
            //studyConsumer.createStudy(study);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testRollbackStudyLocal() throws Exception {

        try {
            Study study = obtainStudyDTO();
            //studyConsumer.rollback(study);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Study obtainStudyDTO() throws Exception {
        Study s = new Study();
        s.setBlindedIndicator("true");
        s.setLongTitleText("Long");
        s.setShortTitleText("short");
        s.setMultiInstitutionIndicator("true");
        s.setRandomizedIndicator("true");
        s.setPhaseCode("Phase I/II Trial");
        s.setDataEntryStatus(StudyDataEntryStatusType.INCOMPLETE);
        s.setCoordinatingCenterStudyStatus(CoordinatingCenterStudyStatusType.PENDING);
        s.setType("Genetic Non-therapeutic");
        s.setDescriptionText("description");
        s.setTargetAccrualNumber(BigInteger.ONE);

        // OrgIdentifiers
        OrganizationAssignedIdentifierType[] idType = new OrganizationAssignedIdentifierType[2];
        idType[0] = new OrganizationAssignedIdentifierType();
        idType[0].setGridId("1111111111");
        idType[0].setPrimaryIndicator(true);
        idType[0].setType("Coordinating Center Identifier");
        idType[0].setValue("abcd123");
        HealthcareSiteType siteType = new HealthcareSiteType();
        siteType.setAddress(new AddressType());
        siteType.setDescriptionText("abce");
        siteType.setNciInstituteCode("NCI");
        idType[0].setHealthcareSite(siteType);

        idType[1] = new OrganizationAssignedIdentifierType();
        idType[1].setGridId("1113311111");
        idType[1].setPrimaryIndicator(true);
        idType[1].setType("Protocol Authority Identifier");
        idType[1].setValue("x123");
        idType[1].setHealthcareSite(siteType);

        s.setIdentifier(idType);
        StudyOrganizationType[] orgType = new StudyOrganizationType[2];
        StudyCoordinatingCenterType ccType = new StudyCoordinatingCenterType();
        ccType.setGridId("11111");
        HealthcareSiteType[] sites = new HealthcareSiteType[1];
        sites[0] = siteType;
        ccType.setHealthcareSite(sites);
        orgType[0] = ccType;

        StudyFundingSponsorType sfType = new StudyFundingSponsorType();
        sfType.setGridId("eeeee");
        sfType.setHealthcareSite(sites);
        orgType[1] = sfType;
        s.setStudyOrganization(orgType);

        return s;
    }
    
    /**
     * This test invokes studyConsumer.populateOrganization(HealthCareSiteType healthCareSiteType, Organization organization);
     * Contents of HealthCareSiteType are mapped/copied to the Organization being passed.  
     */
	public void testPopulateOrganization(){
		HealthcareSiteType healthCareSiteType = new HealthcareSiteType();
		Organization organization = new RemoteOrganization();
		healthCareSiteType.setGridId("54321");
		healthCareSiteType.setName("ORG-FROM-C3PR");
		healthCareSiteType.setNciInstituteCode("C3PR-001");
		AddressType addressType = new AddressType();
		addressType.setCity("Fairfax");
		addressType.setStateCode("VA");
		addressType.setCountryCode("USA");
		healthCareSiteType.setAddress(addressType);
		
		studyConsumer.populateOrganization(healthCareSiteType, organization);
		
		assertEquals("54321", organization.getExternalId());
		assertEquals("ORG-FROM-C3PR", organization.getName());
		assertEquals("C3PR-001", organization.getNciInstituteCode());
		assertEquals("Fairfax", organization.getCity());
		assertEquals("VA", organization.getState());
		assertEquals("USA", organization.getCountry());
		
		healthCareSiteType.setAddress(null);
		organization = new RemoteOrganization();
		studyConsumer.populateOrganization(healthCareSiteType, organization);
		assertEquals("54321", organization.getExternalId());
		assertEquals("ORG-FROM-C3PR", organization.getName());
		assertEquals("C3PR-001", organization.getNciInstituteCode());
		assertNull(organization.getCity());
		assertNull(organization.getState());
		assertNull(organization.getCountry());
	}
	
	
	/**
	 * This test invokes studyConsumer.fetchOrganization(HealthcareSiteType healthCareSiteType)
	 * HealthCareSiteType has a gridId, hence fetchOrganization should return a RemoteOrganization.
	 */
	public void testFetchOrganization_ReturnRemoteOrg(){

		organizationRepository = registerMockFor(OrganizationRepository.class);
		studyConsumer.setOrganizationRepository(organizationRepository);
		expect(organizationRepository.searchOrganization((OrganizationQuery)EasyMock.anyObject())).andReturn(null);		
		organizationRepository.create((Organization)EasyMock.anyObject());
		
		replayMocks();
		
		HealthcareSiteType healthCareSiteType = new HealthcareSiteType();
		healthCareSiteType.setGridId("54321");
		healthCareSiteType.setName("REMOTE-ORG-FROM-C3PR");
		healthCareSiteType.setNciInstituteCode("C3PR-001");
		AddressType addressType = new AddressType();
		addressType.setCity("Fairfax");
		addressType.setStateCode("VA");
		addressType.setCountryCode("USA");
		healthCareSiteType.setAddress(addressType);
		
		Organization organization = studyConsumer.fetchOrganization(healthCareSiteType);
		
		verifyMocks();
		assertTrue(organization instanceof RemoteOrganization);
		assertEquals("54321", organization.getExternalId());
		assertEquals("REMOTE-ORG-FROM-C3PR", organization.getName());
		assertEquals("C3PR-001", organization.getNciInstituteCode());
		assertEquals("Fairfax", organization.getCity());
		assertEquals("VA", organization.getState());
		assertEquals("USA", organization.getCountry());
	}
	
	
	/**
	 * This test invokes studyConsumer.fetchOrganization(HealthcareSiteType healthCareSiteType)
	 * HealthCareSiteType does not have a gridId, hence fetchOrganization should return a LocalOrganization.
	 */
	public void testFetchOrganization_ReturnLocalOrg(){

		organizationRepository = registerMockFor(OrganizationRepository.class);
		studyConsumer.setOrganizationRepository(organizationRepository);
		expect(organizationRepository.searchOrganization((OrganizationQuery)EasyMock.anyObject())).andReturn(null);		
		organizationRepository.create((Organization)EasyMock.anyObject());
		
		replayMocks();
		
		HealthcareSiteType healthCareSiteType = new HealthcareSiteType();
		healthCareSiteType.setName("LOCAL-ORG-FROM-C3PR");
		healthCareSiteType.setNciInstituteCode("C3PR-002");
		AddressType addressType = new AddressType();
		addressType.setCity("Herndon");
		addressType.setStateCode("VA");
		addressType.setCountryCode("USA");
		healthCareSiteType.setAddress(addressType);
		
		Organization organization = studyConsumer.fetchOrganization(healthCareSiteType);
		
		verifyMocks();
		assertTrue(organization instanceof LocalOrganization);
		assertNull(organization.getExternalId());
		assertEquals("LOCAL-ORG-FROM-C3PR", organization.getName());
		assertEquals("C3PR-002", organization.getNciInstituteCode());
		assertEquals("Herndon", organization.getCity());
		assertEquals("VA", organization.getState());
		assertEquals("USA", organization.getCountry());
	}
	
	/**
	 * This test invokes studyConsumer.CreateOrUpdateInvestigator(...)
	 * InvestiagtorType has empty externalId. Hence we got to create a LocalInvestigator
	 */
	public void testCreateOrUpdateInvestigator_LocalInv(){
		investigatorRepository = registerMockFor(InvestigatorRepository.class);
		studyConsumer.setInvestigatorRepository(investigatorRepository);
		investigatorRepository.save((Investigator)EasyMock.anyObject(),(String)EasyMock.anyObject());
		replayMocks();
		
		Organization organization = new LocalOrganization();
		organization.setName("Organization");
		organization.setNciInstituteCode("1234");
		
		InvestigatorType[] invTypes = new InvestigatorType[1];
		invTypes[0] = new InvestigatorType();
		invTypes[0].setFirstName("first_name");
		invTypes[0].setLastName("last_name");
		invTypes[0].setPhoneNumber("000-000-0001");
		invTypes[0].setFaxNumber("000-000-0002");
		invTypes[0].setNciIdentifier("12345");
		invTypes[0].setEmail("unknown@example.com");
		
		Investigator investigator = studyConsumer.createOrUpdateInvestigator(null, organization, invTypes[0]);
		verifyMocks();
		
		assertNotNull(investigator);
		assertTrue(investigator instanceof LocalInvestigator);
		assertEquals("12345", investigator.getNciIdentifier());
		assertEquals("unknown@example.com", investigator.getEmailAddress());
		assertNotNull(investigator.getSiteInvestigators());
		assertEquals(1, investigator.getSiteInvestigators().size());
	}
	
	/**
	 * This test invokes studyConsumer.CreateOrUpdateInvestigator(...)
	 * InvestiagtorType has an externalId. Hence we got to create a RemoteInvestigator
	 */
	public void testCreateOrUpdateInvestigator_RemoteInv(){
		investigatorRepository = registerMockFor(InvestigatorRepository.class);
		studyConsumer.setInvestigatorRepository(investigatorRepository);
		investigatorRepository.save((Investigator)EasyMock.anyObject(),(String)EasyMock.anyObject());
		replayMocks();
		
		Organization organization = new LocalOrganization();
		organization.setName("Organization");
		organization.setNciInstituteCode("1234");
		
		InvestigatorType[] invTypes = new InvestigatorType[1];
		invTypes[0] = new InvestigatorType();
		invTypes[0].setFirstName("first_name");
		invTypes[0].setLastName("last_name");
		invTypes[0].setPhoneNumber("000-000-0001");
		invTypes[0].setFaxNumber("000-000-0002");
		invTypes[0].setNciIdentifier("12345");
		invTypes[0].setExternalId("98765");
		invTypes[0].setEmail("unknown@example.com");
		
		Investigator investigator = studyConsumer.createOrUpdateInvestigator(null, organization, invTypes[0]);
		verifyMocks();
		
		assertNotNull(investigator);
		assertTrue(investigator instanceof RemoteInvestigator);
		assertEquals("12345", investigator.getNciIdentifier());
		assertEquals("98765", investigator.getExternalId());
		assertNotNull(investigator.getSiteInvestigators());
		assertEquals(1, investigator.getSiteInvestigators().size());
	}
	
	/**
	 * This test invokes studyConsumer.CreateOrUpdateInvestigator(...)
	 * InvestiagtorType has an empty externalId. Investigator is not associated to the site.
	 * New SiteInvestigator must be created and associated to the investigator. 
	 */
	public void testCreateOrUpdateInvestigator_NewAssocaition(){
		investigatorRepository = registerMockFor(InvestigatorRepository.class);
		studyConsumer.setInvestigatorRepository(investigatorRepository);
		investigatorRepository.save((Investigator)EasyMock.anyObject(),(String)EasyMock.anyObject());
		replayMocks();

		Investigator investigator = new LocalInvestigator();
		investigator.setFirstName("first_name");
		investigator.setLastName("last_name");
		investigator.setPhoneNumber("000-000-0001");
		investigator.setFaxNumber("000-000-0002");
		investigator.setNciIdentifier("12345");
		Organization organization1 = new LocalOrganization();
		organization1.setName("Organization 1");
		organization1.setNciInstituteCode("1234");
		SiteInvestigator siteInvestigator = new SiteInvestigator();
		siteInvestigator.setInvestigator(investigator);
		siteInvestigator.setOrganization(organization1);
		investigator.addSiteInvestigator(siteInvestigator);
		
		Organization organization2 = new LocalOrganization();
		organization2.setName("Organization 2");
		organization2.setNciInstituteCode("4321");
		
		InvestigatorType[] invTypes = new InvestigatorType[1];
		invTypes[0] = new InvestigatorType();
		invTypes[0].setFirstName("first_name");
		invTypes[0].setLastName("last_name");
		invTypes[0].setPhoneNumber("000-000-0001");
		invTypes[0].setFaxNumber("000-000-0002");
		invTypes[0].setNciIdentifier("12345");
		invTypes[0].setEmail("unknown@example.com");
		
		investigator = studyConsumer.createOrUpdateInvestigator(investigator, organization2, invTypes[0]);
		verifyMocks();
		
		assertNotNull(investigator);
		assertTrue(investigator instanceof LocalInvestigator);
		assertEquals("12345", investigator.getNciIdentifier());
		assertNotNull(investigator.getSiteInvestigators());
		assertEquals(2, investigator.getSiteInvestigators().size());

	}
	
	/**
	 * Investigator is not in caAERS. New Investigator must be created and assocaited to the Site/Org provided.
	 */
	public void testPopulateInvestigators_NewInvestigator(){
		
		investigatorRepository = registerMockFor(InvestigatorRepository.class);
		studyConsumer.setInvestigatorRepository(investigatorRepository);
		investigatorRepository.searchInvestigator((InvestigatorQuery)EasyMock.anyObject());
		expectLastCall().andReturn(null);
		investigatorRepository.save((Investigator)EasyMock.anyObject(),(String)EasyMock.anyObject());
		replayMocks();
		
		StudyOrganization studyOrganization = new StudyFundingSponsor();
		Organization organization1 = new LocalOrganization();
		organization1.setName("Organization 1");
		organization1.setNciInstituteCode("1234");
		studyOrganization.setOrganization(organization1);

		StudyInvestigatorType[] studyInvTypes = new StudyInvestigatorType[1];
		studyInvTypes[0] = new StudyInvestigatorType();
		studyInvTypes[0].setRoleCode("Principal Investigator");
		studyInvTypes[0].setStatusCode("AC");
		HealthcareSiteInvestigatorType hcsInvType = new HealthcareSiteInvestigatorType();
		InvestigatorType[] invTypes = new InvestigatorType[1];
		invTypes[0] = new InvestigatorType();
		invTypes[0].setFirstName("first_name");
		invTypes[0].setLastName("last_name");
		invTypes[0].setPhoneNumber("000-000-0001");
		invTypes[0].setFaxNumber("000-000-0002");
		invTypes[0].setNciIdentifier("12345");
		invTypes[0].setEmail("unknown@example.com");
		
		hcsInvType.setInvestigator(invTypes);
		studyInvTypes[0].setHealthcareSiteInvestigator(hcsInvType);
		
		assertNotNull(studyInvTypes[0].getHealthcareSiteInvestigator().getInvestigator(0));
		
		try {
			studyConsumer.populateInvestigators(studyOrganization, studyInvTypes);
		} catch (StudyCreationException e) {
			fail("No Exceptions are expected");
		}
		verifyMocks();
		assertNotNull(studyOrganization.getStudyInvestigators());
		assertEquals(1, studyOrganization.getStudyInvestigators().size());
		assertNotNull(studyOrganization.getStudyInvestigators().get(0));
		assertNotNull(studyOrganization.getStudyInvestigators().get(0).getSiteInvestigator());
	}
	
	
	/**
	 * Investigator is available in caAERS, but not associted to the Site provide.
	 * New SiteInvestigator must be created.
	 */
	public void testPopulateInvestigators_NewSite(){
		
		Organization organization1 = new LocalOrganization();
		organization1.setName("Organization 1");
		organization1.setNciInstituteCode("1234");
		
		StudyOrganization studyOrganization2 = new StudySite();
		Organization organization2 = new LocalOrganization();
		organization2.setName("Organization 2");
		organization2.setNciInstituteCode("987654");
		studyOrganization2.setOrganization(organization2);
		
		Investigator investigator = new LocalInvestigator();
		investigator.setFirstName("first_name");
		investigator.setLastName("last_name");
		investigator.setPhoneNumber("000-000-0001");
		investigator.setFaxNumber("000-000-0002");
		investigator.setNciIdentifier("12345");
		SiteInvestigator siteInvestigator = new SiteInvestigator();
		siteInvestigator.setInvestigator(investigator);
		siteInvestigator.setOrganization(organization1);
		investigator.addSiteInvestigator(siteInvestigator);
		
		List<Investigator> invList = new ArrayList<Investigator>();
		invList.add(investigator);
		
		investigatorRepository = registerMockFor(InvestigatorRepository.class);
		studyConsumer.setInvestigatorRepository(investigatorRepository);
		investigatorRepository.searchInvestigator((InvestigatorQuery)EasyMock.anyObject());
		expectLastCall().andReturn(invList);
		investigatorRepository.save((Investigator)EasyMock.anyObject(),(String)EasyMock.anyObject());
		replayMocks();
		
		StudyInvestigatorType[] studyInvTypes = new StudyInvestigatorType[1];
		studyInvTypes[0] = new StudyInvestigatorType();
		studyInvTypes[0].setRoleCode("Principal Investigator");
		studyInvTypes[0].setStatusCode("AC");
		HealthcareSiteInvestigatorType hcsInvType = new HealthcareSiteInvestigatorType();
		InvestigatorType[] invTypes = new InvestigatorType[1];
		invTypes[0] = new InvestigatorType();
		invTypes[0].setFirstName("first_name");
		invTypes[0].setLastName("last_name");
		invTypes[0].setPhoneNumber("000-000-0001");
		invTypes[0].setFaxNumber("000-000-0002");
		invTypes[0].setNciIdentifier("12345");
		invTypes[0].setEmail("unknown@example.com");
		
		hcsInvType.setInvestigator(invTypes);
		studyInvTypes[0].setHealthcareSiteInvestigator(hcsInvType);
		
		assertNotNull(studyInvTypes[0].getHealthcareSiteInvestigator().getInvestigator(0));
		
		try {
			studyConsumer.populateInvestigators(studyOrganization2, studyInvTypes);
		} catch (StudyCreationException e) {
			fail("No Exceptions are expected");
		}
		verifyMocks();
		assertNotNull(studyOrganization2.getStudyInvestigators());
		assertEquals(1, studyOrganization2.getStudyInvestigators().size());
	}
	
}
