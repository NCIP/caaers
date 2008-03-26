package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.*;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.domain.*;
import org.easymock.classextension.EasyMock;

import java.util.List;

/**
 * @author Biju Joseph
 */
public class StudyImportServiceTest extends AbstractTestCase {

    private StudyImportServiceImpl studyImportService;

    private OrganizationDao organizationDao;
    private AgentDao agentDao;
    private MedDRADao meddraDao;
    private CtcDao ctcDao;
    private MeddraVersionDao meddraVersionDao;
    private SiteInvestigatorDao siteInvestigatorDao;
    private ResearchStaffDao researchStaffDao;
    private DiseaseTermDao diseaseTermDao;
    private LowLevelTermDao lowLevelTermDao;
    private InvestigationalNewDrugDao investigationalNewDrugDao;
    private StudyDao studyDao;
    private Study xstreamStudy;
    private Organization organization;

    private OrganizationAssignedIdentifier organizationAssignedIdentifier;

    protected void setUp() throws Exception {
        super.setUp();
        organizationDao = registerMockFor(OrganizationDao.class);
        ctcDao = registerDaoMockFor(CtcDao.class);
        meddraDao = registerMockFor(MedDRADao.class);
        studyDao = registerDaoMockFor(StudyDao.class);

        meddraVersionDao = registerDaoMockFor(MeddraVersionDao.class);
        studyImportService = new StudyImportServiceImpl();

        studyImportService.setOrganizationDao(organizationDao);
        studyImportService.setCtcDao(ctcDao);
        studyImportService.setMeddraDao(meddraDao);
        studyImportService.setMeddraVersionDao(meddraVersionDao);
        studyImportService.setStudyDao(studyDao);

        xstreamStudy = Fixtures.createStudy("short title");

        organization = Fixtures.createOrganization("org ");
        organizationAssignedIdentifier = Fixtures.createOrganizationAssignedIdentifier("value", organization);
    }

    public void testImportStudyForMigratingCoordinatingCenter() {

        CoordinatingCenter coordinatingCenter = Fixtures.createCoordinatingCenter(organization, organizationAssignedIdentifier);
        xstreamStudy.setCoordinatingCenter(coordinatingCenter);

        EasyMock.expect(organizationDao.getByName(organization.getName())).andReturn(organization);
        EasyMock.expect(studyDao.getByIdentifier(organizationAssignedIdentifier)).andReturn(null);

        replayMocks();
        DomainObjectImportOutcome<Study> studyDomainObjectImportOutcome = studyImportService.importStudy(xstreamStudy);
        verifyMocks();

        Study study = studyDomainObjectImportOutcome.getImportedDomainObject();
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

        validateOutcome(studyDomainObjectImportOutcome, 1);

    }


    public void testImportStudyForMigratingFundingSponsors() {
        FundingSponsor fundingSponsor = Fixtures.createFundingSponsor(organization, organizationAssignedIdentifier);
        xstreamStudy.setFundingSponsor(fundingSponsor);
        EasyMock.expect(organizationDao.getByName(organization.getName())).andReturn(organization);
        EasyMock.expect(studyDao.getByIdentifier(organizationAssignedIdentifier)).andReturn(null);

        replayMocks();
        DomainObjectImportOutcome<Study> studyDomainObjectImportOutcome = studyImportService.importStudy(xstreamStudy);
        verifyMocks();

        Study study = studyDomainObjectImportOutcome.getImportedDomainObject();
        assertFalse(study.getIdentifiers().isEmpty());
        assertFalse(study.getStudyFundingSponsors().isEmpty());

        OrganizationAssignedIdentifier actualOrganizationAssignedIdentifier = (OrganizationAssignedIdentifier) study.getIdentifiers().get(0);
        assertEquals(xstreamStudy.getFundingSponsor().getOrganizationAssignedIdentifier(), actualOrganizationAssignedIdentifier);

        assertEquals(xstreamStudy.getFundingSponsor().getOrganizationAssignedIdentifier().getOrganization(), actualOrganizationAssignedIdentifier.getOrganization());
        assertEquals(xstreamStudy.getFundingSponsor().getOrganizationAssignedIdentifier().getType(), actualOrganizationAssignedIdentifier.getType());
        assertEquals(OrganizationAssignedIdentifier.SPONSOR_IDENTIFIER_TYPE, actualOrganizationAssignedIdentifier.getType());

        assertEquals(xstreamStudy.getFundingSponsor().getOrganizationAssignedIdentifier().getValue(), actualOrganizationAssignedIdentifier.getValue());

        StudyFundingSponsor actualFundingSponsor = study.getStudyFundingSponsors().get(0);

        assertEquals(xstreamStudy.getFundingSponsor().getStudyFundingSponsor().getOrganization(), actualFundingSponsor.getOrganization());

        validateOutcome(studyDomainObjectImportOutcome, 1);

    }


    public void testImportStudyForMigratingCtcTermTerminology() {

        //first migrate for CtC

        AeTerminology ctcV3Terminology = Fixtures.createCtcV3Terminology(xstreamStudy);

        EasyMock.expect(ctcDao.getById(Integer.parseInt(ctcV3Terminology.getCtcVersion().getName()))).andReturn(ctcV3Terminology.getCtcVersion());
        replayMocks();
        DomainObjectImportOutcome<Study> studyDomainObjectImportOutcome = studyImportService.importStudy(xstreamStudy);
        verifyMocks();
        assertNotNull(studyDomainObjectImportOutcome.getImportedDomainObject().getAeTerminology().getCtcVersion());

        validateOutcome(studyDomainObjectImportOutcome, 2);

    }

    public void testImportStudyForMigratingMedDRATerminology() {

        //first migrate for CtC

        AeTerminology medDRATerminology = Fixtures.createMedDRATerminology(xstreamStudy);

        EasyMock.expect(meddraVersionDao.getById(Integer.parseInt(medDRATerminology.getMeddraVersion().getName()))).andReturn(medDRATerminology.getMeddraVersion());
        replayMocks();
        DomainObjectImportOutcome<Study> studyDomainObjectImportOutcome = studyImportService.importStudy(xstreamStudy);
        verifyMocks();
        assertNotNull(studyDomainObjectImportOutcome.getImportedDomainObject().getAeTerminology().getMeddraVersion());

        validateOutcome(studyDomainObjectImportOutcome, 2);

    }

    public void testImportStudyForBasicProperties() {

        DomainObjectImportOutcome<Study> studyDomainObjectImportOutcome = studyImportService.importStudy(xstreamStudy);

        validateOutcome(studyDomainObjectImportOutcome, 2);

    }


    public void testImportStudyForMigratingTherapy() {

        xstreamStudy.setDrugAdministrationTherapyType(Boolean.TRUE);
        xstreamStudy.setRadiationTherapyType(Boolean.TRUE);


        DomainObjectImportOutcome<Study> studyDomainObjectImportOutcome = studyImportService.importStudy(xstreamStudy);
        assertEquals(2, studyDomainObjectImportOutcome.getImportedDomainObject().getStudyTherapies().size());

        validateOutcome(studyDomainObjectImportOutcome, 2);

    }

    public void testImportStudyForMigratingStudyOrganizations() {

        StudySite studySite = new StudySite();
        studySite.setStudy(xstreamStudy);
        studySite.setOrganization(organization);
        xstreamStudy.addStudyOrganization(studySite);

        StudyFundingSponsor studyFundingSponsor = Fixtures.createStudyFundingSponsor(organization);
        xstreamStudy.addStudyFundingSponsor(studyFundingSponsor);

        EasyMock.expect(organizationDao.getByName(organization.getName())).andReturn(organization).anyTimes();
        replayMocks();
        DomainObjectImportOutcome<Study> studyDomainObjectImportOutcome = studyImportService.importStudy(xstreamStudy);
        verifyMocks();
        assertEquals(2, studyDomainObjectImportOutcome.getImportedDomainObject().getStudyOrganizations().size());

        validateOutcome(studyDomainObjectImportOutcome, 2);

    }

    public void testImportStudyForMigratingTreatmentAssignments() {
        TreatmentAssignment treatmentAssignment = Fixtures.createTreatmentAssignment();
        xstreamStudy.addTreatmentAssignment(treatmentAssignment);

        DomainObjectImportOutcome<Study> studyDomainObjectImportOutcome = studyImportService.importStudy(xstreamStudy);

        validateOutcome(studyDomainObjectImportOutcome, 2);

    }

    private void validateOutcome(final DomainObjectImportOutcome<Study> studyDomainObjectImportOutcome, final int expected) {
        validate(xstreamStudy, studyDomainObjectImportOutcome);
        validateImportedObject(studyDomainObjectImportOutcome);


        List<DomainObjectImportOutcome.Message> messages = studyDomainObjectImportOutcome.getMessages();

        assertEquals(expected, messages.size());
        assertEquals("Disease Code Term is either Empty or Not Valid", messages.get(0).getMessage());
        if (expected == 2) {
            assertEquals("Identifiers are either Empty or Not Valid", messages.get(1).getMessage());
        }
    }

    private void validateImportedObject(final DomainObjectImportOutcome<Study> studyDomainObjectImportOutcome) {
        assertTrue(studyDomainObjectImportOutcome.hasErrors());
        assertFalse(studyDomainObjectImportOutcome.isSavable());

    }

    private void validate(final Study xstreamStudy, final DomainObjectImportOutcome<Study> studyDomainObjectImportOutcome) {

        Study study = studyDomainObjectImportOutcome.getImportedDomainObject();

        assertEquals(xstreamStudy.getLongTitle(), study.getLongTitle());
        assertEquals(xstreamStudy.getDescription(), study.getDescription());
        assertEquals(xstreamStudy.getPrecis(), study.getPrecis());
        assertEquals(xstreamStudy.getPhaseCode(), study.getPhaseCode());
        assertEquals(xstreamStudy.getStatus(), study.getStatus());
        assertEquals(xstreamStudy.getMultiInstitutionIndicator(), study.getMultiInstitutionIndicator());
        assertEquals(xstreamStudy.getAdeersReporting(), study.getAdeersReporting());
        assertEquals(xstreamStudy.getDesign(), study.getDesign());
        assertEquals(xstreamStudy.getDrugAdministrationTherapyType(), study.getDrugAdministrationTherapyType());
        assertEquals(xstreamStudy.getRadiationTherapyType(), study.getRadiationTherapyType());
        assertEquals(xstreamStudy.getDeviceTherapyType(), study.getDeviceTherapyType());
        assertEquals(xstreamStudy.getSurgeryTherapyType(), study.getSurgeryTherapyType());
        assertEquals(xstreamStudy.getBehavioralTherapyType(), study.getBehavioralTherapyType());

        if (!study.getStudyTherapies().isEmpty()) {


            StudyTherapy actualTherapy = study.getStudyTherapies().get(0);
            assertEquals(StudyTherapyType.DRUG_ADMINISTRATION, actualTherapy.getStudyTherapyType());
            assertEquals(study, actualTherapy.getStudy());

            actualTherapy = study.getStudyTherapies().get(1);

            assertEquals(StudyTherapyType.RADIATION, actualTherapy.getStudyTherapyType());
            assertEquals(study, actualTherapy.getStudy());


        }
        assertEquals(study.getTreatmentAssignments().size(), xstreamStudy.getTreatmentAssignments().size());
        if (!xstreamStudy.getTreatmentAssignments().isEmpty()) {

            assertEquals(1, study.getTreatmentAssignments().size());

            final TreatmentAssignment actualTreatmentAssignment = study.getTreatmentAssignments().get(0);
            final TreatmentAssignment expectedTreatmentAssignment = xstreamStudy.getTreatmentAssignments().get(0);

            assertEquals(actualTreatmentAssignment.getCode(), expectedTreatmentAssignment.getCode());
            assertEquals(actualTreatmentAssignment.getComments(), expectedTreatmentAssignment.getComments());
            assertEquals(actualTreatmentAssignment.getDescription(), expectedTreatmentAssignment.getDescription());
            assertEquals(actualTreatmentAssignment.getDoseLevelOrder(), expectedTreatmentAssignment.getDoseLevelOrder());


            assertEquals(xstreamStudy, expectedTreatmentAssignment.getStudy());

            assertEquals(study, actualTreatmentAssignment.getStudy());

        }

        if (!xstreamStudy.getStudyOrganizations().isEmpty()) {

            assertEquals(study.getStudyOrganizations().size(), xstreamStudy.getStudyOrganizations().size());

            StudyOrganization actualStudyOrganization = study.getStudyOrganizations().get(0);
            StudyOrganization expectedStudyOrganization = xstreamStudy.getStudyOrganizations().get(0);

            assertEquals(actualStudyOrganization.getOrganization(), expectedStudyOrganization.getOrganization());

            assertEquals(study, actualStudyOrganization.getStudy());

            actualStudyOrganization = study.getStudyOrganizations().get(1);
            expectedStudyOrganization = xstreamStudy.getStudyOrganizations().get(1);

            assertEquals(actualStudyOrganization.getOrganization(), expectedStudyOrganization.getOrganization());

            assertEquals(study, actualStudyOrganization.getStudy());

        }
        assertNotNull(study.getAeTerminology());

        assertNotNull(xstreamStudy.getAeTerminology());

        if (xstreamStudy.getAeTerminology().getCtcVersion() != null) {
            assertEquals(study.getAeTerminology().getCtcVersion(), xstreamStudy.getAeTerminology().getCtcVersion());
            assertEquals(study.getAeTerminology().getTerm(), xstreamStudy.getAeTerminology().getTerm());

        }
        if (xstreamStudy.getAeTerminology().getMeddraVersion() != null) {
            assertEquals(study.getAeTerminology().getMeddraVersion(), xstreamStudy.getAeTerminology().getMeddraVersion());
            assertEquals(study.getAeTerminology().getTerm(), xstreamStudy.getAeTerminology().getTerm());

        }


    }

}
