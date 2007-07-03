package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcCategory;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.service.EvaluationService;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputField;
import org.easymock.classextension.EasyMock;
import static org.easymock.classextension.EasyMock.*;

import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public class BasicsTabTest extends AeTabTestCase {
    private AdverseEvent ae0;
    private EvaluationService evaluationService;
    private Ctc ctcae3;

    @Override
    protected void setUp() throws Exception {
        evaluationService = registerMockFor(EvaluationService.class);
        super.setUp();

        ctcae3 = Fixtures.createCtcaeV3();
        command.getAssignment().getStudySite().getStudy().setCtcVersion(ctcae3);

        ae0 = command.getAeReport().getAdverseEvents().get(0);
        assertNotNull(ae0.getCtcTerm());
    }

    @Override
    protected BasicsTab createTab() {
        BasicsTab tab = new BasicsTab();
        tab.setEvaluationService(evaluationService);
        return tab;
    }

    @SuppressWarnings("unchecked")
    public void testRefDataIncludesCtcCategories() throws Exception {
        List<CtcCategory> actual = (List<CtcCategory>) getTab().referenceData(command).get("ctcCategories");
        assertEquals("Wrong categories in refdata", ctcae3.getCategories(), actual);
    }

    public void testRefDataIncludesFieldGroups() throws Exception {
        assertTrue(getTab().referenceData(command).containsKey("fieldGroups"));
    }

    public void testGradeRequired() throws Exception {
        ae0.setGrade(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.adverseEvents[0].grade", "Grade");
    }

    public void testHospitalizationRequired() throws Exception {
        ae0.setHospitalization(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.adverseEvents[0].hospitalization", "Hospitalization");
    }

    public void testCtcTermRequired() throws Exception {
        ae0.setCtcTerm(null);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.adverseEvents[0].ctcTerm", "CTC term");
    }

    public void testOtherNotRequiredIfTermDoesNotRequireIt() throws Exception {
        doValidate();
        assertEquals(0, getErrors().getFieldErrorCount("aeReport.adverseEvents[0].detailsForOther"));
    }

    public void testOtherRequiredIfTermRequiresIt() throws Exception {
        ae0.getCtcTerm().setOtherRequired(true);
        doValidate();
        assertFieldRequiredErrorRaised("aeReport.adverseEvents[0].detailsForOther", "Other (specify)");
    }

    public void testPostProcessEvaluates() throws Exception {
        expect(evaluationService.isSevere(same(assignment), same(ae0))).andReturn(true);
        replayMocks();

        getTab().postProcess(request, command, errors);
        verifyMocks();

        assertEquals(Boolean.TRUE, request.getAttribute("oneOrMoreSevere"));
    }

    public void testPostProcessWhenNotSevere() throws Exception {
        expect(evaluationService.isSevere(same(assignment), same(ae0))).andReturn(false);
        replayMocks();

        getTab().postProcess(request, command, errors);
        verifyMocks();

        assertEquals(Boolean.FALSE, request.getAttribute("oneOrMoreSevere"));
    }

    public void testPostProcessWithMultipleAEs() throws Exception {
        AdverseEvent ae1 = new AdverseEvent();
        AdverseEvent ae2 = new AdverseEvent();
        command.getAeReport().addAdverseEvent(ae1);
        command.getAeReport().addAdverseEvent(ae2);

        expect(evaluationService.isSevere(same(assignment), same(ae0))).andReturn(false);
        expect(evaluationService.isSevere(same(assignment), same(ae1))).andReturn(false);
        expect(evaluationService.isSevere(same(assignment), same(ae2))).andReturn(true);
        replayMocks();

        getTab().postProcess(request, command, errors);
        verifyMocks();

        assertEquals(Boolean.TRUE, request.getAttribute("oneOrMoreSevere"));
    }

    @SuppressWarnings({ "unchecked" })
    public void testGradeFieldOptions() throws Exception {
        InputFieldGroup main = getFieldGroup("main0");
        InputField gradeField = main.getFields().get(0);
        assertEquals("Field 0 is not grade", "aeReport.adverseEvents[0].grade", gradeField.getPropertyName());
        Map<Object, Object> options = (Map<Object, Object>) gradeField.getAttributes().get(InputField.OPTIONS);
        assertFalse("Options should not contain grade 0", options.containsKey(Grade.NORMAL.getName()));
        assertFalse("Options should not contain grade 0", options.containsValue(Grade.NORMAL.toString()));
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
