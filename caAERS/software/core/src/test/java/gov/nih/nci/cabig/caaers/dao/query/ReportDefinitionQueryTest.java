package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ReportDefinitionQueryTest extends TestCase {
	
	ReportDefinitionQuery query;
	protected void setUp() throws Exception {
		super.setUp();
		query = new ReportDefinitionQuery();
	}

	public void testFilterByOrganizationId() {
		assertEquals("select rd from ReportDefinition rd", query.getQueryString());
		query.filterByOrganizationId(4);
		assertEquals("select rd from ReportDefinition rd WHERE  rd.organization.id = :orgId", query.getQueryString());
	}

	public void testFilterOffReportDefinitionId() {
		
		query.filterOffReportDefinitionId(4);
		assertEquals( "select rd from ReportDefinition rd WHERE  rd.id <> :rdid" , query.getQueryString());
	}

}
