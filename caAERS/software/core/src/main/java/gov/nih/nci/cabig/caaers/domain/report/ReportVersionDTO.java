/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

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

    //When we seralize this via DWR, we will throw away ReportVersion --
    /** The created on. */
    private Date createdOn;

    /** The due on. */
    private Date dueOn;

    /** The submitted on. */
    private Date submittedOn;

    /** The withdrawn on. */
    private Date withdrawnOn;

    /** The amended on. */
    private Date amendedOn;

    private String reportStatus;
    private Integer id;
    //--- up till above attrivutes are added for smooth DWR seraialization ----

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
        copyReportVersionAttributes(rv);
    }

    public void copyReportVersionAttributes(ReportVersion rv){
        if(rv == null) return;
        dueOn = rv.getDueOn();
        createdOn = rv.getCreatedOn();
        submittedOn = rv.getSubmittedOn();
        withdrawnOn = rv.getWithdrawnOn();
        amendedOn = rv.getAmendedOn();
        if(rv.getReportStatus() != null)   reportStatus = rv.getReportStatus().name();
        id = rv.getId();
    }

    /**
     * Gets the rv.
     *
     * @return the rv
     */
    public ReportVersion getRv() {
        return this.rv;
    }

    public  void setRv(ReportVersion rv){
        this.rv = rv;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getDueOn() {
        return dueOn;
    }

    public void setDueOn(Date dueOn) {
        this.dueOn = dueOn;
    }

    public Date getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(Date submittedOn) {
        this.submittedOn = submittedOn;
    }

    public Date getWithdrawnOn() {
        return withdrawnOn;
    }

    public void setWithdrawnOn(Date withdrawnOn) {
        this.withdrawnOn = withdrawnOn;
    }

    public Date getAmendedOn() {
        return amendedOn;
    }

    public void setAmendedOn(Date amendedOn) {
        this.amendedOn = amendedOn;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDue(){
        return StringUtils.equals("PENDING", reportStatus) ||
                StringUtils.equals("INPROCESS", reportStatus)|| StringUtils.equals(reportStatus, "FAILED");
    }
    public boolean isWithdrawn(){return StringUtils.equals("REPLACED", reportStatus) || StringUtils.equals("WITHDRAWN", reportStatus);}

    public boolean isComplete(){return StringUtils.equals("COMPLETED", reportStatus) || StringUtils.equals("AMENDED", reportStatus);}

    public String getStatusDisplayDate(){
        if(isDue() && dueOn != null) return DateUtils.formatDate(dueOn);
        if(isWithdrawn() && withdrawnOn != null) return DateUtils.formatDate(withdrawnOn);
        if(isComplete() && submittedOn != null) return DateUtils.formatDate(submittedOn);
        return "";
    }

    public String getSubjectDisplayName(){
        String s = "";
        if(subjectFirstName != null) s  = getSubjectFirstName();
        if(subjectLastName != null) s  =  s + " " + getSubjectLastName();
        if(subjectPrimaryIdentifier != null) s  =  s + " (" + getSubjectPrimaryIdentifier() + ")";
        return  s;
    }

    public String getStudySiteDisplayName(){
        String s = "";
        if(studySiteName != null) s = getStudySiteName();
        if(studySiteCode != null) s = s + " (" + getStudySiteCode() + ")";
        return s;
    }

    public String getCourseDisplayName(){
        String s = "";
        if(periodCycle != null) s = "" + getPeriodCycle();
        if(periodStartDate != null) s = s + " (" + DateUtils.formatDate(periodStartDate)+ ")";
        return s;
    }
}
