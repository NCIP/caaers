package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

/*
*
* @author Ion C. Olaru
*
* */
public class ExpeditedAdverseEventReportQueryTest extends TestCase{

	public void testQueryConstructor() throws Exception {
		ReportVersionQuery q = new ReportVersionQuery();
        assertEquals("Wrong parsing for constructor", "select er from ExpeditedAdverseEventReport er", q.getQueryString());
    }

}