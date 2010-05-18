package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.ReportStatus;

/*
*
* @author Ion C. Olaru
* 
* */
public class ReportVersionQuery extends AbstractQuery {

    public ReportVersionQuery() {
        super("select rv from ExpeditedAdverseEventReport aer");
        join("aer.reports as report");
        join("report.reportVersions as rv");
    }

    public void filterByReportStatus(ReportStatus status) {
        orWhere("rv.reportStatus = :status");
        setParameter("status", status);
    }
}