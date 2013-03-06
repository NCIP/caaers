/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.LocalOrganization;
import gov.nih.nci.cabig.caaers.domain.Organization;
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
	
	public void testFilterByOrganization() {
		Organization org = new LocalOrganization();
		org.setNciInstituteCode("nciCode1");
		query.filterByOrganization(org);
		assertEquals("select count(rd) from ReportDefinition rd WHERE rd.organization.nciInstituteCode like :nciInstituteCode", query.getQueryString());
	}

}
