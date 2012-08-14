package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.web.ListValues;
import gov.nih.nci.cabig.caaers.web.WebTestCase;
import org.easymock.EasyMock;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: Biju Joseph
 */
public class SearchAdEERSStudyControllerTest extends WebTestCase {
    SearchAdEERSStudyController controller;
    StudyRepository studyRepository;
    ListValues listValues;
    List<ListValues> studySearchTypes = new ArrayList<ListValues>();
    public void setUp() throws Exception {
       super.setUp();
        controller = new SearchAdEERSStudyController();
        listValues = registerMockFor(ListValues.class);
        studyRepository = registerMockFor(StudyRepository.class);
        controller.setListValues(listValues);
        controller.setStudyRepository(studyRepository);
    }
    
    public void testReferenceData() throws Exception {
        EasyMock.expect(listValues.getStudySearchType()).andReturn(studySearchTypes);
        replayMocks();
        Map<String, Object> refdata = controller.referenceData(request);
        assertSame(studySearchTypes, refdata.get("studySearchType"));
        verifyMocks();
    }

    public void testFormBackingObject() throws  Exception{
        Object sCommand = controller.formBackingObject(request);
        assertTrue(sCommand instanceof SearchCommand) ;
        assertEquals("study/study_adeers_search", controller.getFormView());
        assertEquals("study/study_adeers_search", controller.getSuccessView());
    }

    public void testOnSubmit() throws Exception {


        {
            SearchCommand sCommand = new SearchCommand();
            BindException errors = new BindException(sCommand, "command");
            controller.onSubmit(request, response, sCommand, errors);
            assertTrue(errors.hasErrors());
        }
        {
            SearchCommand sCommand = new SearchCommand();
            sCommand.setSearchText("abc");
            List<Study> studies = new ArrayList<Study>();
            BindException errors = new BindException(sCommand, "command");
            EasyMock.expect(studyRepository.searchInAdEERS("abc")).andReturn(studies);
            replayMocks();
            ModelAndView mv = controller.onSubmit(request, response, sCommand, errors);
            assertFalse(errors.hasErrors());
            assertSame(studies, mv.getModel().get("studies"));
            verifyMocks();
        }


    }



    public void testOnSubmitWithSearchInAdEERSThrowingException() throws Exception {



        {
            SearchCommand sCommand = new SearchCommand();
            sCommand.setSearchText("abc");
            List<Study> studies = new ArrayList<Study>();
            BindException errors = new BindException(sCommand, "command");
            EasyMock.expect(studyRepository.searchInAdEERS("abc")).andThrow(new Exception("test"));
            replayMocks();
            ModelAndView mv = controller.onSubmit(request, response, sCommand, errors);
            assertTrue(errors.hasErrors());
            assertEquals(studies, mv.getModel().get("studies"));
            verifyMocks();
        }

    }
}
