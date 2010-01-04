package gov.nih.nci.cabig.caaers.web.study;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.ConditionDao;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.Condition;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import org.easymock.EasyMock;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 * @author Ion C. Olaru
 */

@CaaersUseCases( { CREATE_STUDY })
public class DCPDiseaseStudyTest extends WebTestCase {

    private CreateStudyController controller;
    private DiseaseTab diseaseTab;
    private StudyDao studyDao;
    private ConditionDao conditionDao;
    private Study study;
    private Errors errors;
    private InvestigationalNewDrugDao investigationalNewDrugDao;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        studyDao = registerDaoMockFor(StudyDao.class);
        conditionDao = registerDaoMockFor(ConditionDao.class);
        investigationalNewDrugDao = registerDaoMockFor(InvestigationalNewDrugDao.class);
        controller = new CreateStudyController();
        controller.setStudyDao(studyDao);
        controller.setConditionDao(conditionDao);

        study = Fixtures.createStudy("study");
      
        diseaseTab = new DiseaseTab();
        diseaseTab.setConditionDao(conditionDao);
    }

    public void testViewOnGet() throws Exception {

        Condition condition = new Condition();
        errors = new BindException(new BeanPropertyBindingResult(study, "command"));
        condition.setId(5);

        request.setMethod("POST");
        request.setParameter("condition", "5");
        request.setParameter("_action", "addOtherCondition");
        request.setParameter("_selected", "0");
        request.setParameter("_page", "5");
        EasyMock.expect(conditionDao.getById(5)).andReturn(condition);
        replayMocks();

        StudyCommand command  = new StudyCommand(studyDao, investigationalNewDrugDao);
        command.setStudy(study);
        command.setCondition("5");

        diseaseTab.postProcess(request, command, errors);

        verifyMocks();
        assertEquals(new Integer(5), study.getStudyConditions().get(0).getTerm().getId());
    }
}