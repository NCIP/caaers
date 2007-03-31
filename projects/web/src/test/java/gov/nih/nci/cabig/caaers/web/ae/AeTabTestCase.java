package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import org.springframework.validation.Errors;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;

/**
 * @author Rhett Sutphin
 */
public abstract class AeTabTestCase extends AeWebTestCase {
    protected Errors errors;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // initialize command as minimally valid
        // BeginTab
        command.setParticipant(new Participant());
        command.setStudy(new Study());

        // BasicsTab
        AdverseEvent event = command.getAeReport().getAdverseEvents().get(0);
        event.setGrade(Grade.MODERATE);
        event.setHospitalization(Hospitalization.NONE);
        event.setCtcTerm(new CtcTerm());

        errors = new BindException(command, "command");
    }

    protected void assertFieldRequiredErrorRaised(String fieldName, String displayName) {
        assertEquals("Wrong number of errors for " + fieldName, 1, errors.getFieldErrorCount(fieldName));
        ObjectError fieldError = errors.getFieldError(fieldName);
        assertEquals("Wrong code for " + fieldName + " error", "REQUIRED", fieldError.getCode());
        assertEquals("Wrong default message for " + fieldName + " error",
            "Missing " + displayName, fieldError.getDefaultMessage());
    }
}
