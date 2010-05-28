package gov.nih.nci.cabig.caaers.domain.report;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;

import java.io.Serializable;

public class ReportVersionDTO implements Serializable {
    protected ReportVersion rv;
    protected String reportName;
    protected String subjectFirstName;
    protected String subjectLastName;
    protected String subjectPrimaryIdentifier;
    protected String studyShortTitle;

    public ReportVersionDTO(ReportVersion rv, String rdLabel, String subjectFirstName,  String subjectLastName, String studyShortTitle, String subjectPrimaryIdentifier) {
        this.rv = rv;
        this.reportName = rdLabel;
        this.subjectFirstName = subjectFirstName;
        this.subjectLastName = subjectLastName;
        this.subjectPrimaryIdentifier = subjectPrimaryIdentifier;
        this.studyShortTitle = studyShortTitle;
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


}