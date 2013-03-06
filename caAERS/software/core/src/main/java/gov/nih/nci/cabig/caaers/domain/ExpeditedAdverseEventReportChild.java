/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

 
/**
 * The Interface ExpeditedAdverseEventReportChild.
 *
 * @author Rhett Sutphin
 */
public interface ExpeditedAdverseEventReportChild {
    
    /**
     * Sets the report.
     *
     * @param report the new report
     */
    void setReport(ExpeditedAdverseEventReport report);

    /**
     * Gets the report.
     *
     * @return the report
     */
    ExpeditedAdverseEventReport getReport();
}
