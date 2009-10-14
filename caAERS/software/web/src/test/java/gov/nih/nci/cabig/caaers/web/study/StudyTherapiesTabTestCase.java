package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.web.fields.InputFieldGroup;

import java.util.Map;

/**
 * @author Ion C. Olaru
 */

public class StudyTherapiesTabTestCase extends AbstractStudyWebTestCase {


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
    	command.setDeviceTherapyType(true);
    	command.setSurgeryTherapyType(true);

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
    	command.setDeviceTherapyType(true);
    	command.setSurgeryTherapyType(true);

    	command.updateStudyTherapies();

        assertEquals(2, study.getStudyTherapies().size());
        assertNotNull(study.getStudyTherapy(StudyTherapyType.DEVICE));
        assertNotNull(study.getStudyTherapy(StudyTherapyType.SURGERY));
        assertNull(study.getStudyTherapy(StudyTherapyType.RADIATION));
        assertNull(study.getStudyTherapy(StudyTherapyType.BEHAVIORAL));
        assertNull(study.getStudyTherapy(StudyTherapyType.DRUG_ADMINISTRATION));
    }

   
}