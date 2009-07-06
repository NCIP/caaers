package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.OrganizationDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.EpochDao;
import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.validation.validator.WebControllerValidator;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import junit.framework.TestCase;

import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

public class GenerateBlankFormPdfControllerTest extends WebTestCase {
    private GenerateBlankFormPdfController controller;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private StudyDao studyDao;
    private EpochDao epochDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        controller = new GenerateBlankFormPdfController();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        studyDao= EasyMock.createMock(StudyDao.class);
        epochDao= EasyMock.createMock(EpochDao.class);
        controller.setStudyDao(studyDao);
        controller.setEpochDao(epochDao);
    }

    public void testViewOnSubmit() throws Exception {
        request.setMethod("GET");
        request.setParameter("st", "1");
        request.setParameter("ep", "5");

        try {
            replayMocks();
            ModelAndView mv = controller.handleRequest(request, response);
            verifyMocks();
            fail("Should have throught the RequestNotSupportedException");
        } catch (Exception e) {
        }
    }

}