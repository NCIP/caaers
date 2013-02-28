/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;
import java.util.Date;

 
/*
* @author Ion C. Olaru
* 
* */
/**
 * The Class ReportVersionDTO.
 */
public class ReportVersionDTO implements Serializable {
    
    /** The rv. */
    protected ReportVersion rv;
    
    /** The report name. */
    protected String reportName;
    
    /** The subject first name. */
    protected String subjectFirstName;
    
    /** The subject last name. */
    protected String subjectLastName;
    
    /** The subject primary identifier. */
    protected String subjectPrimaryIdentifier;
    
    /** The study short title. */
    protected String studyShortTitle;
    
    /** The report id. */
    protected Integer reportID;
    
    /** The ae report id. */
    protected Integer aeReportID;
    
    /** The study site name. */
    protected String studySiteName;
    
    /** The study site code. */
    protected String studySiteCode;
    
    /** The course name. */
    protected String courseName;
    
    /** The period cycle. */
    protected Integer periodCycle;
    
    /** The period start date. */
    protected Date periodStartDate;

    protected Integer assignmentID;
    protected String studyPrimaryIdentifier;

    /**
     * Instantiates a new report version dto.
     *
     * @param rv the rv
     * @param rdLabel the rd label
     * @param subjectFirstName the subject first name
     * @param subjectLastName the subject last name
     * @param studyShortTitle the study short title
     * @param subjectPrimaryIdentifier the subject primary identifier
     * @param aeReportID the ae report id
     * @param reportID the report id
     * @param periodCycle the period cycle
     * @param periodStartDate the period start date
     * @param ssName the ss name
     * @param ssCode the ss code
     */
    public ReportVersionDTO(ReportVersion rv, String rdLabel, String subjectFirstName, String subjectLastName, String studyShortTitle, String subjectPrimaryIdentifier, Integer aeReportID, Integer reportID, Integer periodCycle, Date periodStartDate, String ssName, String ssCode, Integer assignmentID, String studyPrimaryIdentifier) {
        this.rv = rv;
        this.reportName = rdLabel;
        this.subjectFirstName = subjectFirstName;
        this.subjectLastName = subjectLastName;
        this.subjectPrimaryIdentifier = subjectPrimaryIdentifier;
        this.studyShortTitle = studyShortTitle;
        this.reportID = reportID;
        this.aeReportID = aeReportID;
        this.periodCycle = periodCycle;
        this.periodStartDate = periodStartDate;
        this.studySiteName = ssName;
        this.studySiteCode = ssCode;
        this.assignmentID = assignmentID;
        this.studyPrimaryIdentifier = studyPrimaryIdentifier;
    }

    /**
     * Gets the rv.
     *
     * @return the rv
     */
    public ReportVersion getRv() {
        return this.rv;
    }

    /**
     * Gets the report name.
     *
     * @return the report name
     */
    public String getReportName() {
        return this.reportName;
    }

    /**
     * Gets the subject first name.
     *
     * @return the subject first name
     */
    public String getSubjectFirstName() {
        return this.subjectFirstName;
    }

    /**
     * Gets the subject last name.
     *
     * @return the subject last name
     */
    public String getSubjectLastName() {
        return this.subjectLastName;
    }

    /**
     * Gets the subject primary identifier.
     *
     * @return the subject primary identifier
     */
    public String getSubjectPrimaryIdentifier() {
        return this.subjectPrimaryIdentifier;
    }

    /**
     * Gets the study short title.
     *
     * @return the study short title
     */
    public String getStudyShortTitle() {
        return this.studyShortTitle;
    }

    /**
     * Gets the report id.
     *
     * @return the report id
     */
    public Integer getReportID() {
        return reportID;
    }

    /**
     * Gets the ae report id.
     *
     * @return the ae report id
     */
    public Integer getAeReportID() {
        return aeReportID;
    }

    /**
     * Gets the study site name.
     *
     * @return the study site name
     */
    public String getStudySiteName() {
        return studySiteName;
    }

    /**
     * Gets the course name.
     *
     * @return the course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Gets the period cycle.
     *
     * @return the period cycle
     */
    public Integer getPeriodCycle() {
        return periodCycle;
    }

    /**
     * Gets the period start date.
     *
     * @return the period start date
     */
    public Date getPeriodStartDate() {
        return periodStartDate;
    }

    /**
     * Gets the study site code.
     *
     * @return the study site code
     */
    public String getStudySiteCode() {
        return studySiteCode;
    }

    /**
     * Sets the study site code.
     *
     * @param studySiteCode the new study site code
     */
    public void setStudySiteCode(String studySiteCode) {
        this.studySiteCode = studySiteCode;
    }

    public Integer getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(Integer assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getStudyPrimaryIdentifier() {
        return studyPrimaryIdentifier;
    }

    public void setStudyPrimaryIdentifier(String studyPrimaryIdentifier) {
        this.studyPrimaryIdentifier = studyPrimaryIdentifier;
    }
}
