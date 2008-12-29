package gov.nih.nci.cabig.caaers.web.study;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;
import static gov.nih.nci.cabig.caaers.domain.Fixtures.setId;
import static org.easymock.EasyMock.aryEq;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.web.DwrFacadeTestCase;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_STUDY })
public class CreateStudyAjaxFacadeTest extends DwrFacadeTestCase {
    private CreateStudyAjaxFacade facade;

    protected Study study;
    protected StudyCommand command;

    private SiteInvestigatorDao siteInvestigatorDao;

    private InvestigationalNewDrugDao investigationalNewDrugDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        siteInvestigatorDao = registerDaoMockFor(SiteInvestigatorDao.class);
        investigationalNewDrugDao = registerDaoMockFor(InvestigationalNewDrugDao.class);
        facade = new CreateStudyAjaxFacade();
        facade.setSiteInvestigatorDao(siteInvestigatorDao);
        facade.setInvestigationalNewDrugDao(investigationalNewDrugDao);

        command = new StudyCommand();
        study = new Study();
        command.setStudy(study);
        request.getSession().setAttribute(CreateStudyController.class.getName() + ".FORM.command", command);
    }

    public void testSiteInvestigatorsReduced() throws Exception {
        SiteInvestigator expectedInvestigator = new SiteInvestigator();
        expectedInvestigator.setId(34);
        Investigator investigator = new Investigator();
        investigator.setFirstName("Joe");
        investigator.setLastName("Something");
        expectedInvestigator.setInvestigator(investigator);

        StudySite studySite = new StudySite();
        studySite.setOrganization(setId(6, new Organization()));
        study.addStudySite(studySite);

        expect(siteInvestigatorDao.getBySubnames(aryEq(new String[] { "foo" }), eq(6))).andReturn(Arrays.asList(expectedInvestigator));
        replayMocks();
        List<SiteInvestigator> results = facade.matchSiteInvestigator("foo", 0);
        verifyMocks();

        assertEquals("Wrong number of results", 1, results.size());
        SiteInvestigator actual = results.get(0);
        assertEquals("id not copied to result", 34, (int) actual.getId());
        assertEquals("First name not copied", "Joe", actual.getInvestigator().getFirstName());
        assertEquals("Last name not copied", "Something", actual.getInvestigator().getLastName());
    }

    public void testResearchStaffReduced() throws Exception {
        // TODO
    }

    public void testMatchINDs() throws Exception {
        InvestigationalNewDrug expectedIND = new InvestigationalNewDrug();
        expectedIND.setId(32);
        expectedIND.setIndNumber(99);

        expect(investigationalNewDrugDao.findByIds(aryEq(new String[] { "9" }))).andReturn(
                        Arrays.asList(expectedIND));
        replayMocks();
        List<InvestigationalNewDrug> results = facade.matchINDs("9");
        verifyMocks();

        assertEquals("Wrong number of results", 1, results.size());
        InvestigationalNewDrug actual = results.get(0);
        assertEquals("IND number is not matching", (int) actual.getIndNumber(), 99);
        //
    }
}
