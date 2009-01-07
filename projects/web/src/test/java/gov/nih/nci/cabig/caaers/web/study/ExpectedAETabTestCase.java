package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ion C. Olaru
 */

public class ExpectedAETabTestCase extends AbstractStudyWebTestCase {

    protected StudyCommand createCommand() {
        return createMockCommand();
    }

    protected StudyTab createTab() {
        ExpectedAEsTab tab = new ExpectedAEsTab();
        tab.setConfigurationProperty(new ConfigProperty());

        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        tab.getConfigurationProperty().setMap(map);

        return tab;
    }

    public void testReferenceData() {
        study.setAeTerminology(new AeTerminology());
        study.getAeTerminology().setCtcVersion(new Ctc());
        study.getAeTerminology().setTerm(Term.CTC);

        replayMocks();
        Map<String, Object> output = tab.referenceData(request, command);
        verifyMocks();

        assertEquals(0, errors.getErrorCount());
    }

    public void testValidationNoData() {
        replayMocks();
        tab.validate(command, errors);
        verifyMocks();
        assertEquals(0, errors.getErrorCount());
    }

    public void testValidationGoodData() {
        study.setAeTerminology(new AeTerminology());
        study.getAeTerminology().setCtcVersion(new Ctc());

        ExpectedAECtcTerm t1 = new ExpectedAECtcTerm();
        t1.setId(7);
        t1.setTerm(new CtcTerm());
        t1.getTerm().setTerm("Term");
        t1.getTerm().setSelect("Select");
        t1.getTerm().setId(70);
        study.addExpectedAECtcTerm(t1);

        assertEquals(1, study.getExpectedAECtcTerms().size());

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();
        assertEquals(0, errors.getErrorCount());
        assertEquals(1, study.getExpectedAECtcTerms().size());
    }

    public void testValidationBadData() {
        study.setAeTerminology(new AeTerminology());
        study.getAeTerminology().setCtcVersion(new Ctc());

        ExpectedAECtcTerm t1 = new ExpectedAECtcTerm();
        t1.setId(7);
        t1.setTerm(new CtcTerm());
        t1.getTerm().setTerm("Term70");
        t1.getTerm().setSelect("Select");
        t1.getTerm().setId(70);
        study.addExpectedAECtcTerm(t1);

        ExpectedAECtcTerm t2 = new ExpectedAECtcTerm();
        t2.setId(8);
        t2.setTerm(new CtcTerm());
        t2.getTerm().setTerm("Term70");
        t2.getTerm().setSelect("Select");
        t2.getTerm().setId(70);
        study.addExpectedAECtcTerm(t2);

        assertEquals(2, study.getExpectedAECtcTerms().size());

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();
        assertEquals(1, errors.getErrorCount());
        assertEquals(2, study.getExpectedAECtcTerms().size());
    }

    public void testPostProcess() {
        replayMocks();
        tab.postProcess(request, command, errors);
        verifyMocks();
        assertEquals(0, errors.getErrorCount());
    }

    protected StudyCommand createMockCommand() {
        StudyCommand command = new StudyCommand();
        Study study = new Study();
        command.setStudy(study);

        return command;
    }
}