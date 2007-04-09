package gov.nih.nci.cabig.caaers.web.admin;

import gov.nih.nci.cabig.caaers.dao.InvestigatorDao;
import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kulasekaran
 */
public class CreateInvestigatorControllerTest extends WebTestCase {
	
	private CreateInvestigatorController controller = new CreateInvestigatorController();
    private InvestigatorDao investigatorDao;
	private SiteDao siteDao;
    	
    protected void setUp() throws Exception {
        super.setUp();
        investigatorDao = registerDaoMockFor(InvestigatorDao.class);        
        // siteDao = registerDaoMockFor(SiteDao.class);
        siteDao = new SiteDao() {
        	@Override
        	public List<Site> getAll() {        		        		
        		List<Site> sites = new ArrayList<Site>();
        		return sites;
        	}
        };
        controller.setInvestigatorDao(investigatorDao);
		controller.setSiteDao(siteDao);
		controller.setConfigurationProperty(new ConfigProperty());
		controller.setListValues(new ListValues());
    }
    
    public void testViewOnGet() throws Exception {
        request.setMethod("GET");
        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("admin/investigator_details", mv.getViewName());
    }
}
