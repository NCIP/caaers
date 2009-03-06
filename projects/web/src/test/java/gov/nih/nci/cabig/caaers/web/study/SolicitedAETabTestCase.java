package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.Arm;
import gov.nih.nci.cabig.caaers.domain.Epoch;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.utils.Lov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.easymock.EasyMock;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ion C. Olaru
 */

public class SolicitedAETabTestCase extends AbstractStudyWebTestCase {

    protected StudyCommand createCommand() {
        return createMockCommand();
    }

    protected StudyTab createTab() {
        SolicitedAdverseEventTab tab = new SolicitedAdverseEventTab();
        tab.setConfigurationProperty(new ConfigProperty());
        tab.setCtcTermDao(ctcTermDao);
        tab.setLowLevelTermDao(lowLevelTermDao);
        tab.setEpochDao(epochDao);

        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        tab.getConfigurationProperty().setMap(map);

        return tab;
    }

    public void testReferenceData() {
        replayMocks();
        Map<String, Object> output = tab.referenceData(request, command);
        verifyMocks();

        assertNotNull(output);
        assertNotNull(output.get("listOfSolicitedAERows"));
    }

    public void testValidationNoData() {
        replayMocks();
        tab.validate(command, errors);
        verifyMocks();
        assertEquals(0, errors.getErrorCount());
    }

    public void testOnBind() {
        request.setParameter("epoch_id", "1");
        request.setParameter("epoch_id", "2");

        Epoch e1 = new Epoch();
        e1.setId(90);
        e1.setEpochOrder(1);
        e1.setName("E-1");

        List<Arm> arms = new ArrayList<Arm>();
        Arm a1 = new Arm();
        a1.setName("Arm-1");
        a1.setDescriptionText("Description");
        arms.add(a1);
        e1.setArms(arms);

        List<Epoch> epochs = new ArrayList<Epoch>();
        epochs.add(e1);

        study.setEpochs(epochs);

        EasyMock.expect(epochDao.getCountReportingPeriodsByEpochId(90)).andReturn(0);
        
        replayMocks();
        tab.onBind(request, command, errors);
        verifyMocks();
        assertEquals(0, errors.getErrorCount());
    }

    public void testFindInRequest() {
        request.setAttribute("p1", "v1");
        SolicitedAdverseEventTab t = (SolicitedAdverseEventTab) tab;

        replayMocks();
        Object o = t.findInRequest(request, "p1");
        verifyMocks();
        assertEquals("v1", o.toString());
    }

    public void testAddEpoch() {
        SolicitedAdverseEventTab t = (SolicitedAdverseEventTab) tab;

        Epoch e1 = new Epoch();
        e1.setId(90);
        e1.setEpochOrder(1);
        e1.setName("E-1");

        List<Arm> arms = new ArrayList<Arm>();
        Arm a1 = new Arm();
        a1.setName("Arm-1");
        a1.setDescriptionText("Description");
        arms.add(a1);
        e1.setArms(arms);

        List<Epoch> epochs = new ArrayList<Epoch>();
        epochs.add(e1);

        study.setEpochs(epochs);

        replayMocks();
        ModelAndView mv = t.addEpoch(request, command, errors);
        verifyMocks();

        assertEquals(0, errors.getErrorCount());
        assertEquals(2, study.getEpochs().size());
        assertEquals("Enter name here", study.getEpochs().get(1).getName());
        assertEquals(2, study.getEpochs().get(1).getEpochOrder().intValue());
    }

    public void testDeleteEpoch() {
        SolicitedAdverseEventTab t = (SolicitedAdverseEventTab) tab;
        EasyMock.expect(epochDao.getCountReportingPeriodsByEpochId(91)).andReturn(0);
        EasyMock.expect(epochDao.getCountReportingPeriodsByEpochId(90)).andReturn(0);

        Epoch e1 = new Epoch();
        e1.setId(90);
        e1.setEpochOrder(1);
        e1.setName("E-1");

        Epoch e2 = new Epoch();
        e2.setId(91);
        e2.setEpochOrder(2);
        e2.setName("E-2");

        List<Arm> arms = new ArrayList<Arm>();
        Arm a1 = new Arm();
        a1.setName("Arm-1");
        a1.setDescriptionText("Description");
        arms.add(a1);
        e1.setArms(arms);

        List<Epoch> epochs = new ArrayList<Epoch>();
        epochs.add(e1);
        epochs.add(e2);

        study.setEpochs(epochs);

        request.setParameter("epoch_id", "90");
        assertEquals(2, study.getEpochs().size());

        replayMocks();
        ModelAndView mv = t.deleteEpoch(request, command, errors);
        verifyMocks();

        assertEquals(0, errors.getErrorCount());
        assertEquals(0, study.getEpochs().size());
    }

    protected StudyCommand createMockCommand() {
        StudyCommand command = new StudyCommand();
        Study study = new Study();
        command.setStudy(study);

        return command;
    }
}