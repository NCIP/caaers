package gov.nih.nci.cabig.caaers.dao.query;

/*
*
* @author Ion C. Olaru
* 
* */
public class ExpeditedAdverseEventReportQuery extends AbstractQuery {

    public ExpeditedAdverseEventReportQuery() {
        super("select er from ExpeditedAdverseEventReport er");
    }

    public void joinReports() {
        join("er.reports as report");
    }

    public void joinReportVersions() {
        join("report.reportVersions as reportVersion");
    }
}