package gov.nih.nci.cabig.caaers.web.study;

import static gov.nih.nci.cabig.caaers.domain.Fixtures.setId;
import static org.easymock.EasyMock.aryEq;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import gov.nih.nci.cabig.caaers.dao.SiteInvestigatorDao;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.web.DwrFacadeTestCase;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rhett Sutphin
 */
public class CreateStudyAjaxFacadeTest extends DwrFacadeTestCase {
    private CreateStudyAjaxFacade facade;
    protected Study command;

    private SiteInvestigatorDao siteInvestigatorDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        siteInvestigatorDao = registerDaoMockFor(SiteInvestigatorDao.class);

        facade = new CreateStudyAjaxFacade();
        facade.setSiteInvestigatorDao(siteInvestigatorDao);

        command = new Study();
        request.getSession().setAttribute(
            CreateStudyController.class.getName() + ".FORM.command",
            command
        );
    }

    public void testSiteInvestigatorsReduced() throws Exception {
        SiteInvestigator expectedInvestigator = new SiteInvestigator();
        expectedInvestigator.setId(34);
        Investigator investigator = new Investigator();
        investigator.setFirstName("Joe");
        investigator.setLastName("Something");
        expectedInvestigator.setInvestigator(investigator);

        StudySite studySite = new StudySite();
        studySite.setSite(setId(6, new Site()));
        command.addStudySite(studySite);
        
        expect(siteInvestigatorDao.getBySubnames(aryEq(new String[] { "foo" }), eq(6)))
            .andReturn(Arrays.asList(expectedInvestigator));
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
}
