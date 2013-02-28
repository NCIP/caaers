/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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

    public void testRequecedQuery(){
        query = new ReportDefinitionQuery(true);
        query.filterOffReportDefinitionId(4);
        assertEquals( "select new ReportDefinition(rd.id, rd.name, rd.label) from ReportDefinition rd WHERE  rd.id <> :rdid" , query.getQueryString());
    }

}
