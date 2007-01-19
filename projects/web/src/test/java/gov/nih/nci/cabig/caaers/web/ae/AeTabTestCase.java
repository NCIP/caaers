package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;
import org.easymock.classextension.EasyMock;
import static org.easymock.classextension.EasyMock.*;
import org.springframework.validation.Errors;
import org.springframework.validation.BindException;
import org.springframework.web.bind.EscapedErrors;

/**
 * @author Rhett Sutphin
 */
public abstract class AeTabTestCase extends AeWebTestCase {
    protected Errors errors;

    protected void setUp() throws Exception {
        super.setUp();

        // initialize command as valid
        command.setParticipant(new Participant());
        command.setStudy(new Study());

        errors = new BindException(command, "command");
    }

}
