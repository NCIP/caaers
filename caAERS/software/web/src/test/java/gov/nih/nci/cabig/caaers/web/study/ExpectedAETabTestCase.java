package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.AeTerminology;
import gov.nih.nci.cabig.caaers.domain.Ctc;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.ExpectedAECtcTerm;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ion C. Olaru
 */

public class ExpectedAETabTestCase extends AbstractStudyWebTestCase {

   

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

   
}