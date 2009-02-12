package gov.nih.nci.cabig.caaers.web.ae;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_EXPEDITED_REPORT;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_ROUTINE_REPORT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.dao.CtcDao;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.expeditedfields.ExpeditedReportSection;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.validation.ValidationErrors;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldAttributes;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.easymock.classextension.EasyMock;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { CREATE_EXPEDITED_REPORT, CREATE_ROUTINE_REPORT })
public class CtcBasicsTabTest extends AeTabTestCase {
    private AdverseEvent ae0;

    private Ctc ctcae3;
    private CtcDao ctcDao;
    
    @Override
    protected void setUp() throws Exception {
    	 ctcae3 = Fixtures.createCtcaeV3();
        super.setUp();

        AeTerminology t = Fixtures.createCtcV3Terminology(command.getAssignment().getStudySite().getStudy());
        command.getAssignment().getStudySite().getStudy().setAeTerminology(t);
        command.getAssignment().getStudySite().getStudy().getAeTerminology().setCtcVersion(ctcae3);

        ae0 = command.getAeReport().getAdverseEvents().get(0);
        assertNotNull(ae0.getAdverseEventCtcTerm().getAdverseEvent());
        
    }

    @Override
    protected CtcBasicsTab createTab() {
        CtcBasicsTab ctcBasicsTab = new CtcBasicsTab();
        EvaluationService evaluationServiceMock = registerMockFor(EvaluationService.class);
        ctcBasicsTab.setEvaluationService(evaluationServiceMock);
        EasyMock.expect(evaluationServiceMock.validateReportingBusinessRules(command.getAeReport(),
                                        ExpeditedReportSection.BASICS_SECTION)).andReturn(new ValidationErrors()).anyTimes();
        ctcDao = registerDaoMockFor(CtcDao.class);
        ctcBasicsTab.setCtcDao(ctcDao);
        return ctcBasicsTab;
    }

    @SuppressWarnings("unchecked")
    public void testRefDataIncludesCtcCategories() throws Exception {
    	 EasyMock.expect(ctcDao.getById(ctcae3.getId())).andReturn(ctcae3);
    	replayMocks();
        List<CtcCategory> actual = (List<CtcCategory>) getTab().referenceData(request, command).get("ctcCategories");
        assertEquals("Wrong categories in refdata", ctcae3.getCategories().size(), actual.size());
    }

    public void testRefDataIncludesFieldGroups() throws Exception {
    	 EasyMock.expect(ctcDao.getById(ctcae3.getId())).andReturn(ctcae3);
    	replayMocks();
        assertTrue(getTab().referenceData(request, command).containsKey("fieldGroups"));
    }

    public void testGradeRequired() throws Exception {
        ae0.setGrade(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.adverseEvents[0].grade", "Grade");
    }

   

    public void testCtcTermRequired() throws Exception {
        ae0.getAdverseEventCtcTerm().setCtcTerm(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.adverseEvents[0].adverseEventCtcTerm.ctcTerm",
                        "CTC term");
    }

    public void testOtherNotRequiredIfTermDoesNotRequireIt() throws Exception {
        doValidate();
        assertEquals(0, getErrors().getFieldErrorCount("aeReport.adverseEvents[0].detailsForOther"));
    }

    public void testOtherRequiredIfTermRequiresIt() throws Exception {
        ae0.getAdverseEventCtcTerm().getCtcTerm().setOtherRequired(true);
        ae0.setLowLevelTerm(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.adverseEvents[0].lowLevelTerm", "Other (MedDRA)");
        // assertFieldRequiredErrorRaised("aeReport.adverseEvents[0].detailsForOther","Other (verbatim)");
    }

    @SuppressWarnings( { "unchecked" })
    public void testGradeFieldOptions() throws Exception {
        InputFieldGroup main = getFieldGroup("main0");
        InputField gradeField = main.getFields().get(1);
        assertEquals("Field 0 is not grade", "aeReport.adverseEvents[0].grade", gradeField.getPropertyName());
        Map<Object, Object> options = InputFieldAttributes.getOptions(gradeField);
        assertFalse("Options should not contain grade 0", options.containsKey(Grade.NORMAL.getName()));
        assertFalse("Options should not contain grade 0", options.containsValue(Grade.NORMAL.toString()));
        assertFalse("Options should not contain grade -1", options.containsKey(Grade.NOT_EVALUATED.toString()));
        assertFalse("Options should not contain grade -1", options.containsValue(Grade.NOT_EVALUATED.toString()));
        assertEquals("Wrong number of options", 5, options.size());
        assertGradeOptionPresent(options, Grade.MILD);
        assertGradeOptionPresent(options, Grade.MODERATE);
        assertGradeOptionPresent(options, Grade.SEVERE);
        assertGradeOptionPresent(options, Grade.LIFE_THREATENING);
        assertGradeOptionPresent(options, Grade.DEATH);
    }

    private void assertGradeOptionPresent(Map<Object, Object> options, Grade grade) {
        assertTrue("Missing key for " + grade, options.containsKey(grade.getName()));
        assertEquals("Wrong value for " + grade, grade.toString(), options.get(grade.getName()));
    }
}
