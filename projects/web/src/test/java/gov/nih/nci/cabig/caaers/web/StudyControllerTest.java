package gov.nih.nci.cabig.caaers.web;

import org.springframework.web.servlet.ModelAndView;

import gov.nih.nci.cabig.caaers.dao.StudyDao;

public class StudyControllerTest extends WebTestCase {

    private StudyController controller = new StudyController();
    private StudyDao studyDao;

    protected void setUp() throws Exception {
        super.setUp();
        studyDao = registerMockFor(StudyDao.class);
        controller.setStudyDao(studyDao);
    }
    
    public void testViewOnGet() throws Exception {
        request.setMethod("GET");
        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("createStudy", mv.getViewName());
    }

    public void testViewOnGoodSubmit() throws Exception {
        request.addParameter("multiInstitutionIndicator", "true");
        request.addParameter("shortTitle", "Scott");
        request.addParameter("longTitle", "Male");
        request.addParameter("description", "Description");
        request.addParameter("principalInvestigatorCode", "Principal Investigator Code");
        request.addParameter("principalInvestigatorName", "Principal Investigator Name");
        request.addParameter("primarySponsorCode", "Primary Sponsor Code");
        request.addParameter("primarySponsorName", "Primary Sponsor Name");
        request.addParameter("phaseCode", "PhaseCode");
        request.addParameter("reviewDate", "2006-01-01");
        
        ModelAndView mv = controller.handleRequest(request, response);
        assertEquals("createStudy", mv.getViewName());
    }    
    
}
