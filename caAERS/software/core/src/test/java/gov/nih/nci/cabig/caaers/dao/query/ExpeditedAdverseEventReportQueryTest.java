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
        assertEquals("Wrong parsing for constructor", "select rv from ExpeditedAdverseEventReport aer join aer.reports as report join report.reportVersions as rv", q.getQueryString());
    }

}