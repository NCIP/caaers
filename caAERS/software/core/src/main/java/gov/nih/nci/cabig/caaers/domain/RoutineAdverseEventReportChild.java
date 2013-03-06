/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

 
/**
 * The Interface RoutineAdverseEventReportChild.
 *
 * @author Rhett Sutphin
 */
public interface RoutineAdverseEventReportChild {
    
    /**
     * Sets the routine report.
     *
     * @param report the new routine report
     */
    void setRoutineReport(RoutineAdverseEventReport report);

    /**
     * Gets the routine report.
     *
     * @return the routine report
     */
    RoutineAdverseEventReport getRoutineReport();
}
