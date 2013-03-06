/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.dashboard;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import org.easymock.EasyMock;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class StudyDashboardControllerTest extends WebTestCase {

    StudyRepository studyRepository;
    StudyDashboardController controller;
    List<Study> studyList = new ArrayList<Study>();
    public void setUp() throws Exception {
        super.setUp();
        controller = new StudyDashboardController();
        studyRepository = registerMockFor(StudyRepository.class);
        controller.setStudyRepository(studyRepository);



    }

    public void testHandleRequestInternal() throws Exception {
        request.setParameter("loadAll", "true");
        EasyMock.expect(studyRepository.getAllStudies()).andReturn(studyList) ;
        replayMocks();
        ModelAndView mv = controller.handleRequestInternal(request, response);
        assertSame(studyList,mv.getModel().get("studyList"));
        verifyMocks();
    }



    public void testHandleRequestInternal_Paginated() throws Exception {

        EasyMock.expect(studyRepository.getAllStudies(0, 20)).andReturn(studyList) ;
        replayMocks();
        ModelAndView mv = controller.handleRequestInternal(request, response);
        assertSame(studyList,mv.getModel().get("studyList"));
        verifyMocks();
    }
}
