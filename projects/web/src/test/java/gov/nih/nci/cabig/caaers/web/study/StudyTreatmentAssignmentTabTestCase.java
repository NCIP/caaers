package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldFactory;
import gov.nih.nci.cabig.caaers.web.fields.RepeatingFieldGroupFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.springframework.validation.ObjectError;

/**
 * @author Ion C. Olaru
 */

public class StudyTreatmentAssignmentTabTestCase extends AbstractStudyWebTestCase {

    protected StudyCommand createCommand() {
        return createMockCommand();
    }

    protected StudyTab createTab() {
        TreatmentAssignmentTab tab = new TreatmentAssignmentTab();
        tab.setConfigurationProperty(new ConfigProperty());
        Map<String, List<Lov>> map = new HashMap<String, List<Lov>>();
        tab.getConfigurationProperty().setMap(map);
        return tab;
    }

    public void testCreateFieldGroup() {
        replayMocks();
        Map<String, InputFieldGroup> map = tab.createFieldGroups(command);
        verifyMocks();

        assertNotNull(map);
        assertEquals(0, map.size());
    }

    public void testCreateFieldGroupWithTwoTreatments() {
        TreatmentAssignment ta1 = new TreatmentAssignment();
        TreatmentAssignment ta2 = new TreatmentAssignment();

        study.addTreatmentAssignment(ta1);
        study.addTreatmentAssignment(ta2);

        replayMocks();
        Map<String, InputFieldGroup> map = tab.createFieldGroups(command);
        verifyMocks();

        assertNotNull(map);
        assertEquals(2, map.size());

        RepeatingFieldGroupFactory.RepeatingFieldGroup main0 = (RepeatingFieldGroupFactory.RepeatingFieldGroup)map.get("main0");
        assertEquals(4, main0.getFields().size());

        RepeatingFieldGroupFactory.RepeatingFieldGroup main1 = (RepeatingFieldGroupFactory.RepeatingFieldGroup)map.get("main1");
        assertEquals(4, main1.getFields().size());
    }

    public void testPostProcessRemoveTreatmentAssignment() {
        assertEquals(0, study.getTreatmentAssignments().size());

        TreatmentAssignment ta = new TreatmentAssignment();
        study.addTreatmentAssignment(ta);

        assertEquals(1, study.getTreatmentAssignments().size());

        replayMocks();
        request.setParameter("_action", "removeTreatmentAssignment");
        request.setParameter("_selected", "0");
        tab.postProcess(request, command, errors);
        verifyMocks();

        assertEquals(0, study.getTreatmentAssignments().size());
    }

    public void testValidation() {
        TreatmentAssignment ta = new TreatmentAssignment();
        ta.setCode("taCode");
        ta.setDescription("taDescription");

        study.addTreatmentAssignment(ta);

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();

        assertEquals(0, errors.getErrorCount());
        assertEquals("taCode", study.getTreatmentAssignments().get(0).getCode());
        assertEquals("taDescription", study.getTreatmentAssignments().get(0).getDescription());
    }

    public void testValidationNegative() {
        study.addTreatmentAssignment(new TreatmentAssignment());

        replayMocks();
        tab.validate(command, errors);
        verifyMocks();

        assertEquals(2, errors.getErrorCount());
        assertEquals("Missing Code", ((ObjectError)errors.getAllErrors().get(0)).getDefaultMessage());
        assertEquals("Missing Description", ((ObjectError)errors.getAllErrors().get(1)).getDefaultMessage());
    }

    protected StudyCommand createMockCommand() {
        StudyCommand command = new StudyCommand();
        Study study = new Study();
        command.setStudy(study);

        return command;
    }
}