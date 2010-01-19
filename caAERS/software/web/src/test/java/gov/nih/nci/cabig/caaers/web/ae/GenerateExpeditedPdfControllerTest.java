package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.dao.EpochDao;
import gov.nih.nci.cabig.caaers.dao.ExpeditedAdverseEventReportDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import org.easymock.classextension.EasyMock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public class GenerateExpeditedPdfControllerTest extends WebTestCase {
    private GenerateExpeditedPdfController controller;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private ExpeditedAdverseEventReportDao aeReportDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        controller = new GenerateExpeditedPdfController();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        aeReportDao= EasyMock.createMock(ExpeditedAdverseEventReportDao.class);
        controller.setAeReportDao(aeReportDao);
    }

    public void testExportCustomPDF() throws Exception {
        request.setMethod("GET");
        request.setParameter("format", "customPDF");
        request.setParameter("aeReport", "5");
        request.setParameter("reportId", "6");

        try {
            replayMocks();
            ModelAndView mv = controller.handleRequest(request, response);
            verifyMocks();
            fail("Should have throught the RequestNotSupportedException");
        } catch (Exception e) {
        }
    }

}