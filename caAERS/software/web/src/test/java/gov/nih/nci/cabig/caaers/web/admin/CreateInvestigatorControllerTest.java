package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kulasekaran
 */
public class CreateInvestigatorControllerTest extends WebTestCase {

    private CreateInvestigatorController controller = new CreateInvestigatorController();

    private InvestigatorDao investigatorDao;

    private OrganizationDao organizationDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        investigatorDao = registerDaoMockFor(InvestigatorDao.class);
        // siteDao = registerDaoMockFor(SiteDao.class);
        organizationDao = new OrganizationDao() {
            @Override
            public List<Organization> getAll() {
                List<Organization> organizations = new ArrayList<Organization>();
                return organizations;
            }
        };
        controller.setInvestigatorDao(investigatorDao);
        controller.setOrganizationDao(organizationDao);
        controller.setConfigurationProperty(new ConfigProperty());
        // controller.setListValues(new ListValues());
    }

    public void testViewOnGet() throws Exception {
        // request.setMethod("GET");
        // ModelAndView mv = controller.handleRequest(request, response);
        // assertEquals("admin/investigator_details", mv.getViewName());
    }
}
