package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ReportDefinitionExistsQueryTest extends TestCase {

	
	public ReportDefinitionExistsQuery query = new ReportDefinitionExistsQuery();
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testFilterByDifferentId() {
		assertEquals("select count(rd) from ReportDefinition rd", query.getQueryString());
		query.filterByDifferentId(55);
		assertEquals("select count(rd) from ReportDefinition rd WHERE  rd.id != :rdid", query.getQueryString());
	}

	public void testFilterByName() {
		query.filterByName("abcd");
		assertEquals("select count(rd) from ReportDefinition rd WHERE rd.name like :rdname", query.getQueryString());
	}

}
