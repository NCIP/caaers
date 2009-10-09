package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersSystemException;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;

/**
 * This service class is used to send AE reports to the study calendar.
 * 
 * @author Rhett Sutphin
 */
public interface InteroperationService {
    void pushToStudyCalendar(ExpeditedAdverseEventReport aeReport) throws CaaersSystemException;

    void pushToStudyCalendar(RoutineAdverseEventReport roReport) throws CaaersSystemException;
    
    //String broadcastCOPPA(String message,Metadata metaData) throws CaaersSystemException;
}
