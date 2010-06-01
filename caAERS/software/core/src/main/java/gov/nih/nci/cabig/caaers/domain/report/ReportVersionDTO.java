package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;
import java.util.Date;

/*
* @author Ion C. Olaru
* 
* */
public class ReportVersionDTO implements Serializable {
    protected ReportVersion rv;
    protected String reportName;
    protected String subjectFirstName;
    protected String subjectLastName;
    protected String subjectPrimaryIdentifier;
    protected String studyShortTitle;
    protected Integer reportID;
    protected Integer aeReportID;
    protected String studySiteName;
    protected String studySiteCode;
    protected String courseName;
    protected Integer periodCycle;
    protected Date periodStartDate;

    public ReportVersionDTO(ReportVersion rv, String rdLabel, String subjectFirstName, String subjectLastName, String studyShortTitle, String subjectPrimaryIdentifier, Integer aeReportID, Integer reportID, Integer periodCycle, Date periodStartDate, String ssName, String ssCode) {
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
    }

    public ReportVersion getRv() {
        return this.rv;
    }

    public String getReportName() {
        return this.reportName;
    }

    public String getSubjectFirstName() {
        return this.subjectFirstName;
    }

    public String getSubjectLastName() {
        return this.subjectLastName;
    }

    public String getSubjectPrimaryIdentifier() {
        return this.subjectPrimaryIdentifier;
    }

    public String getStudyShortTitle() {
        return this.studyShortTitle;
    }

    public Integer getReportID() {
        return reportID;
    }

    public Integer getAeReportID() {
        return aeReportID;
    }

    public String getStudySiteName() {
        return studySiteName;
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getPeriodCycle() {
        return periodCycle;
    }

    public Date getPeriodStartDate() {
        return periodStartDate;
    }

    public String getStudySiteCode() {
        return studySiteCode;
    }

    public void setStudySiteCode(String studySiteCode) {
        this.studySiteCode = studySiteCode;
    }
}