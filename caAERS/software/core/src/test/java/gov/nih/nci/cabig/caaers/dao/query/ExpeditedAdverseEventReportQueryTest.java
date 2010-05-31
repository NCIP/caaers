package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/*
*
* @author Ion C. Olaru
*
* */
public class ExpeditedAdverseEventReportQueryTest extends TestCase{

	public void testQueryConstructor() throws Exception {
		ReportVersionDTOQuery q = new ReportVersionDTOQuery();
        assertEquals("Wrong parsing for constructor", "select new gov.nih.nci.cabig.caaers.domain.report.ReportVersionDTO(rv, rd.label, subject.firstName, subject.lastName, study.shortTitle, identifier.value) from ExpeditedAdverseEventReport aer join aer.reports as report join report.reportVersions as rv join report.reportDefinition as rd join report.aeReport as aeReport join aeReport.reportingPeriod as period join period.assignment as assignment join assignment.participant as subject left join subject.identifiers as identifier with identifier.primaryIndicator = :primaryIndicator join assignment.studySite as ss join ss.study as study", q.getQueryString());
    }

}