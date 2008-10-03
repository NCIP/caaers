package gov.nih.nci.cabig.caaers.grid;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.ctms.audit.dao.AuditHistoryRepository;
import gov.nih.nci.cabig.ccts.domain.AddressType;
import gov.nih.nci.cabig.ccts.domain.CoordinatingCenterStudyStatusType;
import gov.nih.nci.cabig.ccts.domain.HealthcareSiteType;
import gov.nih.nci.cabig.ccts.domain.OrganizationAssignedIdentifierType;
import gov.nih.nci.cabig.ccts.domain.Study;
import gov.nih.nci.cabig.ccts.domain.StudyCoordinatingCenterType;
import gov.nih.nci.cabig.ccts.domain.StudyDataEntryStatusType;
import gov.nih.nci.cabig.ccts.domain.StudyFundingSponsorType;
import gov.nih.nci.cabig.ccts.domain.StudyOrganizationType;

import java.math.BigInteger;

public class CaaersStudyConsumerTest extends CaaersTestCase {
    CaaersStudyConsumer studyConsumer;

    private String studyResourceName;

    private String serviceUrl;

    private String configLoction;

    protected void setUp() throws Exception {
        super.setUp();
        // this.configLoction="/client-config.wsdd";
        // this.studyResourceName = "/study.xml";

        this.configLoction = "/client-config.wsdd";
        this.studyResourceName = "/study.xml";

        this.serviceUrl = "http://cbvapp-d1017.nci.nih.gov:18080/wsrf/services/cagrid/StudyConsumer";
        studyConsumer = createStudyConsumer();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateStudyLocal() throws Exception {
        try {
            Study study = obtainStudyDTO();
            studyConsumer.createStudy(study);
        } catch (Exception e) {
            e.printStackTrace();
//            throw e;
        }
    }

    public void testRollbackStudyLocal() throws Exception {

        try {
            Study study = obtainStudyDTO();
            studyConsumer.rollback(study);
        } catch (Exception e) {
            e.printStackTrace();
  //          throw e;
        }

    }

    public CaaersStudyConsumer createStudyConsumer() {
        CaaersStudyConsumer consumer = new CaaersStudyConsumer();
        consumer.setConfigurationProperty((ConfigProperty) getDeployedApplicationContext().getBean(
                        "configurationProperty"));
        consumer.setOrganizationDao((OrganizationDao) getDeployedApplicationContext().getBean(
                        "organizationDao"));
        consumer.setStudyDao((StudyDao) getDeployedApplicationContext().getBean("studyDao"));
        consumer.setAuditHistoryRepository((AuditHistoryRepository) getDeployedApplicationContext()
                        .getBean("auditHistoryRepository"));
        consumer.setSiteInvestigatorDao((SiteInvestigatorDao) getDeployedApplicationContext()
                        .getBean("siteInvestigatorDao"));
        consumer.setStudyConsumerGridServiceUrl("/pages/task");
        consumer.setRollbackInterval(1);
        return consumer;
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
        siteType.setNciInstituteCode("DUKE");
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

}
