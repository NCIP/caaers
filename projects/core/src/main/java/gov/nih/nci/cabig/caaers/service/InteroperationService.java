package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.CaaersSystemException;

/**
 * @author Rhett Sutphin
 */
public interface InteroperationService {
    void pushToStudyCalendar(AdverseEventReport aeReport) throws CaaersSystemException;
}
