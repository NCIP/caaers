package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.CaaersSystemException;

/**
 * Contains do-nothing implementations of {@link InteroperationService}, suitable for
 * local testing.
 *
 * @author Rhett Sutphin
 */
public class MockInteroperationService implements InteroperationService {

    // this implementation throws an exception 25% of the time in order to test exception handling
    public void pushToStudyCalendar(AdverseEventReport aeReport) {
        int rand = (int) Math.floor(Math.random() * 4);
        if (rand == 3) throw new CaaersSystemException("You lose");
    }
}
