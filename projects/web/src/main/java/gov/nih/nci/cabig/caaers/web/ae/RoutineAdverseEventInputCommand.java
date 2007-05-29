package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.AdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.dao.StudyParticipantAssignmentDao;

import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public interface RoutineAdverseEventInputCommand extends AdverseEventInputCommandInterface{
     
    AdverseEventReport getAeReport();
    
    RoutineAdverseEventReport getAeRoutineReport();

    /* attributionMap[attributionKey][ae index][cause index]; indexes are the same as the equivs
     * in AdverseEventReport and AdverseEvent */
    Map<String, List<List<Attribution>>> getAttributionMap();
}
