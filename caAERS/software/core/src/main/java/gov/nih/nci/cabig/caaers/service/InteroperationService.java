package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;

/**
 * This service class is used to send AE reports to the study calendar.
 * 
 * @author Rhett Sutphin
 */
public interface InteroperationService {
    void pushToStudyCalendar(ExpeditedAdverseEventReport aeReport) throws CaaersSystemException;
}
