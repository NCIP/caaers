package gov.nih.nci.cabig.caaers.grid;

import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.RemoteOrganization;
import gov.nih.nci.cabig.caaers.domain.repository.OrganizationRepository;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.ccts.domain.AddressType;
import gov.nih.nci.cabig.ccts.domain.CoordinatingCenterStudyStatusType;
import gov.nih.nci.cabig.ccts.domain.HealthcareSiteType;
import gov.nih.nci.cabig.ccts.domain.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.ccts.domain.Study;
import gov.nih.nci.cabig.ccts.domain.StudyCoordinatingCenterType;
import gov.nih.nci.cabig.ccts.domain.StudyDataEntryStatusType;
import gov.nih.nci.cabig.ccts.domain.StudyFundingSponsorType;
import gov.nih.nci.cabig.ccts.domain.StudyOrganizationType;
import gov.nih.nci.cabig.ctms.audit.dao.AuditHistoryRepository;

import java.math.BigInteger;

import org.easymock.classextension.EasyMock;

public class CaaersStudyConsumerTest extends CaaersTestCase {
    CaaersStudyConsumer studyConsumer;
    protected OrganizationRepository organizationRepository;
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
}
