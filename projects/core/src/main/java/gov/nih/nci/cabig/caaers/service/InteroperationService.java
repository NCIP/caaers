package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.CaaersSystemException;

/**
 * @author Rhett Sutphin
 */
public interface InteroperationService {
    void pushToStudyCalendar(ExpeditedAdverseEventReport aeReport) throws CaaersSystemException;
    void pushToStudyCalendar(RoutineAdverseEventReport roReport) throws CaaersSystemException;
}
