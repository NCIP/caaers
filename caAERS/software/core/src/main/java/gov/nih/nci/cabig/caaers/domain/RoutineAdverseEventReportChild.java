/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

/**
 * @author Rhett Sutphin
 */
public interface RoutineAdverseEventReportChild {
    void setRoutineReport(RoutineAdverseEventReport report);

    RoutineAdverseEventReport getRoutineReport();
}
