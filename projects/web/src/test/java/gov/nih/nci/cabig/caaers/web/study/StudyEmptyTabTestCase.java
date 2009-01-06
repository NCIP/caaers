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

public class StudyEmptyTabTestCase extends AbstractStudyWebTestCase {

    protected StudyCommand createCommand() {
        return createMockCommand();
    }

    protected StudyTab createTab() {
        EmptyStudyTab tab = new EmptyStudyTab("", "", "");
        tab.setConfigurationProperty(new ConfigProperty());
        return tab;
    }

    public void testReferenceData() {
        replayMocks();
        Map<String, Object> output = tab.referenceData(request, command);
        verifyMocks();

        assertNotNull(output);
        assertNotNull(output.get("listOfSolicitedAERows"));
    }

    protected StudyCommand createMockCommand() {
        StudyCommand command = new StudyCommand();
        Study study = new Study();
        command.setStudy(study);

        return command;
    }
}