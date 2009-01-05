package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.utils.Lov;
import gov.nih.nci.cabig.caaers.utils.ConfigProperty;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.springframework.validation.ObjectError;

/**
 * @author Ion C. Olaru
 */

public class StudyTherapiesTabTestCase extends AbstractStudyWebTestCase {

    protected StudyCommand createCommand() {
        return createMockCommand();
    }

    protected StudyTab createTab() {
        StudyTherapiesTab tab = new StudyTherapiesTab();
        return tab;
    }

    public void testCreateFieldGroup() {
        replayMocks();
        Map<String, InputFieldGroup> map = tab.createFieldGroups(command);
        verifyMocks();

        assertNotNull(map);
        InputFieldGroup studyTherapies = map.get("studyTherapies");
        assertNotNull(studyTherapies);

        assertEquals(5, studyTherapies.getFields().size());
        assertEquals("Agent", studyTherapies.getFields().get(0).getDisplayName());
        assertEquals("Device", studyTherapies.getFields().get(1).getDisplayName());
        assertEquals("Radiation", studyTherapies.getFields().get(2).getDisplayName());
        assertEquals("Surgery", studyTherapies.getFields().get(3).getDisplayName());
        assertEquals("Behavioral", studyTherapies.getFields().get(4).getDisplayName());
    }

    public void testOnBind() {
        study.setDeviceTherapyType(true);
        study.setSurgeryTherapyType(true);

        replayMocks();
        tab.onBind(request, command, errors);
        verifyMocks();

        assertEquals(2, study.getStudyTherapies().size());
        assertNotNull(study.getStudyTherapy(StudyTherapyType.DEVICE));
        assertNotNull(study.getStudyTherapy(StudyTherapyType.SURGERY));
        assertNull(study.getStudyTherapy(StudyTherapyType.RADIATION));
        assertNull(study.getStudyTherapy(StudyTherapyType.BEHAVIORAL));
        assertNull(study.getStudyTherapy(StudyTherapyType.DRUG_ADMINISTRATION));
    }

    public void testUpdateStudyTherapies() {
        study.setDeviceTherapyType(true);
        study.setSurgeryTherapyType(true);

        ((StudyTherapiesTab)tab).updateStudyTherapies(study);

        assertEquals(2, study.getStudyTherapies().size());
        assertNotNull(study.getStudyTherapy(StudyTherapyType.DEVICE));
        assertNotNull(study.getStudyTherapy(StudyTherapyType.SURGERY));
        assertNull(study.getStudyTherapy(StudyTherapyType.RADIATION));
        assertNull(study.getStudyTherapy(StudyTherapyType.BEHAVIORAL));
        assertNull(study.getStudyTherapy(StudyTherapyType.DRUG_ADMINISTRATION));
    }

    protected StudyCommand createMockCommand() {
        StudyCommand command = new StudyCommand();
        Study study = new Study();
        command.setStudy(study);

        return command;
    }
}