package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;

/*
*
* @author Ion C. Olaru
* 
* */
public class ReportVersionDTOQuery extends AbstractQuery {

    public ReportVersionDTOQuery() {
        super("select new gov.nih.nci.cabig.caaers.domain.report.ReportVersionDTO(rv, rd.label, subject.firstName, subject.lastName, study.shortTitle, identifier.value) from ExpeditedAdverseEventReport aer");
        join("aer.reports as report");
        join("report.reportVersions as rv");
        join("report.reportDefinition as rd");
        join("report.aeReport as aeReport");
        join("aeReport.reportingPeriod as period");
        join("period.assignment as assignment");
        join("assignment.participant as subject");
        join("subject.identifiers as identifier");
        join("assignment.studySite as ss");
        join("ss.study as study");
        andWhere("identifier.primaryIndicator = :primaryIndicator");
        setParameter("primaryIndicator", Boolean.TRUE);
    }

    public void filterByReportStatus(ReportStatus status) {
        orWhere("rv.reportStatus = :status");
        setParameter("status", status);
    }
}