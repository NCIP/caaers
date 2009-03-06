package gov.nih.nci.cabig.caaers.service.migrator;

import static org.easymock.EasyMock.isA;
import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.ResearchStaffDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.query.OrganizationQuery;
import gov.nih.nci.cabig.caaers.domain.CoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.FundingSponsor;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome.Message;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;

public class StudyOrganizationMigratorTest extends AbstractTestCase {

    private OrganizationDao organizationDao;
    private SiteInvestigatorDao siteInvestigatorDao;
    private ResearchStaffDao researchStaffDao;

    Study xstreamStudy;
    DomainObjectImportOutcome<Study> outcome;
    Study dest;
    StudyOrganizationMigrator migrator;

    private Organization organization;
    private OrganizationAssignedIdentifier organizationAssignedIdentifier;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        organizationDao = registerDaoMockFor(OrganizationDao.class);
        siteInvestigatorDao = registerDaoMockFor(SiteInvestigatorDao.class);
        researchStaffDao = registerDaoMockFor(ResearchStaffDao.class);

        migrator = new StudyOrganizationMigrator();
        outcome = new DomainObjectImportOutcome<Study>();
        dest = new Study();

        migrator.setOrganizationDao(organizationDao);
        migrator.setResearchStaffDao(researchStaffDao);
        migrator.setSiteInvestigatorDao(siteInvestigatorDao);
        organization = Fixtures.createOrganization("org ");
        organizationAssignedIdentifier = Fixtures.createOrganizationAssignedIdentifier("value", organization);

        xstreamStudy = Fixtures.createStudy("test");
    }


    public void testMigrate_CoordinatingCenter() {

        CoordinatingCenter coordinatingCenter = Fixtures.createCoordinatingCenter(organization, organizationAssignedIdentifier);
        xstreamStudy.setCoordinatingCenter(coordinatingCenter);

        List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);

        EasyMock.expect(organizationDao.searchOrganization(isA(OrganizationQuery.class))).andReturn(organizations);

        replayMocks();
        migrator.migrate(xstreamStudy, dest, outcome);
        verifyMocks();

        Study study = dest;

        assertFalse(study.getIdentifiers().isEmpty());
        assertFalse(study.getStudyOrganizations().isEmpty());

        OrganizationAssignedIdentifier actualOrganizationAssignedIdentifier = (OrganizationAssignedIdentifier) study.getIdentifiers().get(0);
        assertEquals(xstreamStudy.getCoordinatingCenter().getOrganizationAssignedIdentifier(), actualOrganizationAssignedIdentifier);

        assertEquals(xstreamStudy.getCoordinatingCenter().getOrganizationAssignedIdentifier().getOrganization(), actualOrganizationAssignedIdentifier.getOrganization());
        assertEquals(xstreamStudy.getCoordinatingCenter().getOrganizationAssignedIdentifier().getType(), actualOrganizationAssignedIdentifier.getType());
        assertEquals(OrganizationAssignedIdentifier.COORDINATING_CENTER_IDENTIFIER_TYPE, actualOrganizationAssignedIdentifier.getType());

        assertEquals(xstreamStudy.getCoordinatingCenter().getOrganizationAssignedIdentifier().getValue(), actualOrganizationAssignedIdentifier.getValue());

        StudyOrganization actualCoordinatingCenter = study.getStudyOrganizations().get(0);
        assertTrue(actualCoordinatingCenter instanceof StudyCoordinatingCenter);

        assertEquals(xstreamStudy.getCoordinatingCenter().getStudyCoordinatingCenter().getOrganization(), actualCoordinatingCenter.getOrganization());

        assertTrue(outcome.getMessages().isEmpty());
    }

    public void testMigrate_CoordinatingCenter_InvalidOrganization() {

        CoordinatingCenter coordinatingCenter = Fixtures.createCoordinatingCenter(organization, organizationAssignedIdentifier);
        xstreamStudy.setCoordinatingCenter(coordinatingCenter);

        List<Organization> organizations = null;

        EasyMock.expect(organizationDao.searchOrganization(isA(OrganizationQuery.class))).andReturn(organizations);

        replayMocks();
        migrator.migrate(xstreamStudy, dest, outcome);
        verifyMocks();

        assertEquals("Error message count incorrect", 1, outcome.getMessages().size());
        Message msg = (Message) outcome.getMessages().get(0);
        assertEquals("Incorrect error message", "The organization specified in coordinatingCenter is invalid", msg.getMessage());
    }

    public void testMigrate_FundingSponsor() {

        FundingSponsor sponsor = Fixtures.createFundingSponsor(organization, organizationAssignedIdentifier);
        xstreamStudy.setFundingSponsor(sponsor);

        List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);

        EasyMock.expect(organizationDao.searchOrganization(isA(OrganizationQuery.class))).andReturn(organizations);

        replayMocks();
        migrator.migrate(xstreamStudy, dest, outcome);
        verifyMocks();

        Study study = dest;

        assertFalse(study.getIdentifiers().isEmpty());
        assertFalse(study.getStudyOrganizations().isEmpty());

        OrganizationAssignedIdentifier actualOrganizationAssignedIdentifier = (OrganizationAssignedIdentifier) study.getIdentifiers().get(0);
        assertEquals(sponsor.getOrganizationAssignedIdentifier(), actualOrganizationAssignedIdentifier);

        assertEquals(sponsor.getOrganizationAssignedIdentifier().getOrganization(), actualOrganizationAssignedIdentifier.getOrganization());
        assertEquals(sponsor.getOrganizationAssignedIdentifier().getType(), actualOrganizationAssignedIdentifier.getType());
        assertEquals(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE, actualOrganizationAssignedIdentifier.getType());

        assertEquals(sponsor.getOrganizationAssignedIdentifier().getValue(), actualOrganizationAssignedIdentifier.getValue());

        StudyOrganization migratedSponsor = study.getStudyOrganizations().get(0);
        assertTrue(migratedSponsor instanceof StudyFundingSponsor);

        assertEquals(sponsor.getStudyFundingSponsor().getOrganization(), migratedSponsor.getOrganization());

        assertTrue(outcome.getMessages().isEmpty());
    }

    public void testMigrate_FundingSponsor_InvalidOrganization() {

        FundingSponsor sponsor = Fixtures.createFundingSponsor(organization, organizationAssignedIdentifier);
        xstreamStudy.setFundingSponsor(sponsor);

        List<Organization> organizations = null;
        EasyMock.expect(organizationDao.searchOrganization(isA(OrganizationQuery.class))).andReturn(organizations);

        replayMocks();
        migrator.migrate(xstreamStudy, dest, outcome);
        verifyMocks();
        assertEquals("Error message count incorrect", 1, outcome.getMessages().size());
        Message msg = (Message) outcome.getMessages().get(0);
        assertEquals("Incorrect error message", "The organization specified in fundingSponsor is invalid", msg.getMessage());
    }


    public void testMigrateStudyOrganization() {

        StudySite studySite = new StudySite();
        studySite.setStudy(xstreamStudy);
        studySite.setOrganization(organization);
        xstreamStudy.addStudyOrganization(studySite);

        StudyFundingSponsor studyFundingSponsor = Fixtures.createStudyFundingSponsor(organization);
        xstreamStudy.addStudyFundingSponsor(studyFundingSponsor);

        List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);

        EasyMock.expect(organizationDao.searchOrganization(isA(OrganizationQuery.class))).andReturn(organizations).anyTimes();
        replayMocks();
        migrator.migrate(xstreamStudy, dest, outcome);
        verifyMocks();

        assertEquals(1, dest.getStudyOrganizations().size());
        assertTrue(outcome.getMessages().isEmpty());
    }

    public void testMigrateStudyOrganization_WithInvestigators() {
        organization.setId(11);

        StudyInvestigator si1 = Fixtures.createStudyInvestigator("biju", organization);

        //to return from easymock
        List<SiteInvestigator> si1List = new ArrayList<SiteInvestigator>();
        si1List.add(si1.getSiteInvestigator());

        StudySite srcSSite = new StudySite();

        srcSSite.setStudy(xstreamStudy);
        srcSSite.setOrganization(organization);

        srcSSite.addStudyInvestigators(si1);

        xstreamStudy.addStudyOrganization(srcSSite);

        StudyFundingSponsor studyFundingSponsor = Fixtures.createStudyFundingSponsor(organization);
        xstreamStudy.addStudyFundingSponsor(studyFundingSponsor);

        List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);

        EasyMock.expect(organizationDao.searchOrganization(isA(OrganizationQuery.class))).andReturn(organizations).anyTimes();
        EasyMock.expect(siteInvestigatorDao.getBySubnames((String[]) EasyMock.anyObject(), EasyMock.anyInt())).andReturn(si1List);

        replayMocks();
        migrator.migrate(xstreamStudy, dest, outcome);
        verifyMocks();

        StudySite ss = dest.getStudySites().get(0);

        assertEquals(1, dest.getStudyOrganizations().size());
        assertTrue(outcome.getMessages().isEmpty());
        assertEquals("Investiagors incorrect", 1, ss.getStudyInvestigators().size());

    }

    public void testMigrateStudyOrganization_With_WrongInvestigators() {
        organization.setId(11);

        StudyInvestigator si1 = Fixtures.createStudyInvestigator("biju", organization);

        //to return from easymock
        List<SiteInvestigator> si1List = new ArrayList<SiteInvestigator>();

        StudySite srcSSite = new StudySite();

        srcSSite.setStudy(xstreamStudy);
        srcSSite.setOrganization(organization);

        srcSSite.addStudyInvestigators(si1);

        xstreamStudy.addStudyOrganization(srcSSite);

        StudyFundingSponsor studyFundingSponsor = Fixtures.createStudyFundingSponsor(organization);
        xstreamStudy.addStudyFundingSponsor(studyFundingSponsor);

        List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);

        EasyMock.expect(organizationDao.searchOrganization(isA(OrganizationQuery.class))).andReturn(organizations).anyTimes();
        EasyMock.expect(siteInvestigatorDao.getBySubnames((String[]) EasyMock.anyObject(), EasyMock.anyInt())).andReturn(si1List);

        replayMocks();
        migrator.migrate(xstreamStudy, dest, outcome);
        verifyMocks();

        StudySite ss = dest.getStudySites().get(0);

        assertEquals(1, dest.getStudyOrganizations().size());
        assertEquals("Incorrect error message", 1, outcome.getMessages().size());

    }


    public void testMigrateStudyOrganization_WithResearchStaff() {
        organization.setId(11);
        List<UserGroupType> groupTypeList = new ArrayList<UserGroupType>();
        ResearchStaff rs = Fixtures.createResearchStaff(organization, groupTypeList, "biju");

        StudyPersonnel sp = Fixtures.createStudyPersonnel(rs);

        //to return from easymock
        List<ResearchStaff> si1List = new ArrayList<ResearchStaff>();
        si1List.add(rs);

        StudySite srcSSite = new StudySite();

        srcSSite.setStudy(xstreamStudy);
        srcSSite.setOrganization(organization);

        srcSSite.addStudyPersonnel(sp);

        xstreamStudy.addStudyOrganization(srcSSite);
        FundingSponsor fundingSponsor = Fixtures.createFundingSponsor(organization, organizationAssignedIdentifier);
        StudyFundingSponsor studyFundingSponsor = Fixtures.createStudyFundingSponsor(organization);
        fundingSponsor.setStudyFundingSponsor(studyFundingSponsor);
        xstreamStudy.setFundingSponsor(fundingSponsor);

        List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);

        EasyMock.expect(organizationDao.searchOrganization(isA(OrganizationQuery.class))).andReturn(organizations).anyTimes();
        EasyMock.expect(researchStaffDao.getByNciIdentifier((String[]) EasyMock.anyObject(), EasyMock.anyInt())).andReturn(si1List);

        replayMocks();
        migrator.migrate(xstreamStudy, dest, outcome);
        verifyMocks();

        StudySite ss = dest.getStudySites().get(0);

        assertEquals(2, dest.getStudyOrganizations().size());
        assertTrue(outcome.getMessages().isEmpty());
        assertEquals("Researchstaffs incorrect", 1, ss.getStudyPersonnels().size());

    }

    public void testMigrateStudyOrganization_With_WrongResearchStaff() {
        organization.setId(11);
        List<UserGroupType> groupTypeList = new ArrayList<UserGroupType>();
        ResearchStaff rs = Fixtures.createResearchStaff(organization, groupTypeList, "biju");

        StudyPersonnel sp = Fixtures.createStudyPersonnel(rs);

        //to return from easymock
        List<ResearchStaff> si1List = new ArrayList<ResearchStaff>();

        StudySite srcSSite = new StudySite();

        srcSSite.setStudy(xstreamStudy);
        srcSSite.setOrganization(organization);

        srcSSite.addStudyPersonnel(sp);

        xstreamStudy.addStudyOrganization(srcSSite);
        
        FundingSponsor fundingSponsor = Fixtures.createFundingSponsor(organization, organizationAssignedIdentifier);
        StudyFundingSponsor studyFundingSponsor = Fixtures.createStudyFundingSponsor(organization);
        fundingSponsor.setStudyFundingSponsor(studyFundingSponsor);
        xstreamStudy.setFundingSponsor(fundingSponsor);

        List<Organization> organizations = new ArrayList<Organization>();
        organizations.add(organization);

        EasyMock.expect(organizationDao.searchOrganization(isA(OrganizationQuery.class))).andReturn(organizations).anyTimes();
        EasyMock.expect(researchStaffDao.getByNciIdentifier((String[]) EasyMock.anyObject(), EasyMock.anyInt())).andReturn(si1List);

        replayMocks();
        migrator.migrate(xstreamStudy, dest, outcome);
        verifyMocks();

        StudySite ss = dest.getStudySites().get(0);

        assertEquals(2, dest.getStudyOrganizations().size());
        assertEquals("Incorrect error message", 1, outcome.getMessages().size());

    }
}
