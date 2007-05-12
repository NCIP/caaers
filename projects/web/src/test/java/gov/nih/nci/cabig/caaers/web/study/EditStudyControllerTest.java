package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.dao.SiteDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Site;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kulasekaran
 */
public class EditStudyControllerTest extends WebTestCase {
	
	private EditStudyController controller = new EditStudyController();
    private StudyDao studyDao;
	private SiteDao siteDao;
    	
    protected void setUp() throws Exception {
        super.setUp();
        //studyDao = registerDaoMockFor(StudyDao.class);
        studyDao = new StudyDao() {
        	@Override
        	public Study getStudyDesignById(int id) {				
                return new Study();
            }        	
        	
        	@Override        	
        	public void save(Study study) {                                        
            }
        	
        };
        
        siteDao = new SiteDao() {
        	@Override
        	public List<Site> getAll() {        		        		
        		List<Site> sites = new ArrayList<Site>();
        		return sites;
        	}
        };
        controller.setStudyDao(studyDao);
		controller.setSiteDao(siteDao);
	//	controller.setConfigurationProperty(new ConfigProperty());
    }
    
    public void testViewOnGet() throws Exception {
        request.setMethod("GET");
        request.setParameter("studyId", "1");
        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("study/edit_study_details", mv.getViewName());
    }

    public void testViewOnGoodSubmit() throws Exception {
    	request.setParameter("studyId", "1");
        request.addParameter("multiInstitutionIndicator", "true");
        request.addParameter("shortTitle", "Scott");
        request.addParameter("longTitle", "Male");
        request.addParameter("description", "Description");
        request.addParameter("primarySponsorCode", "Primary Sponsor Code");
        request.addParameter("phaseCode", "PhaseCode");
        //request.addParameter("reviewDate", "2006-01-01");
        request.setParameter("_target0", "");
        
        controller.handleRequest(request, response);
        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("study/edit_study_details", mv.getViewName());
    }    
   
   /*private NewParticipantCommand postAndReturnCommand() throws Exception {
        request.setMethod("POST");
        participantDao.save((Participant) notNull());  
        expectLastCall().atLeastOnce().asStub();

        replayMocks();
        ModelAndView mv = controller.handleRequest(request, response);
        verifyMocks();

        Object command = mv.getModel().get("command");
        assertNotNull("Command not present in model: " + mv.getModel(), command);
        return (NewParticipantCommand) command;
    } */
}
