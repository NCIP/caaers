/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.ae;

import gov.nih.nci.cabig.caaers.domain.Attribution;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;
import gov.nih.nci.cabig.caaers.domain.RoutineAdverseEventReport;

import java.util.List;
import java.util.Map;

/**
 * @author Rhett Sutphin
 */
public interface RoutineAdverseEventInputCommand extends AdverseEventInputCommand {

    ExpeditedAdverseEventReport getAeReport();

    RoutineAdverseEventReport getAeRoutineReport();

    String[] getCtcTermIds();

    /*
     * attributionMap[attributionKey][ae index][cause index]; indexes are the same as the equivs in
     * AdverseEventReport and AdverseEvent
     */
    Map<String, List<List<Attribution>>> getAttributionMap();
}
