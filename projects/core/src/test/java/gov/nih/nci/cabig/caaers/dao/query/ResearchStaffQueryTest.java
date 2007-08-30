package gov.nih.nci.cabig.caaers.dao.query;

import junit.framework.TestCase;

public class ResearchStaffQueryTest extends TestCase {

	public void testQueryConstructor() throws Exception {
		ResearchStaffQuery reserachStaffQuery = new ResearchStaffQuery();
		assertEquals("wrong parsing for constructor",
				"SELECT distinct rs from ResearchStaff rs left join fetch rs.organization order by rs.id",
				reserachStaffQuery.getQueryString());

	}

	public void testFilterByTitle() throws Exception {
		ResearchStaffQuery reserachStaffQuery = new ResearchStaffQuery();
		reserachStaffQuery.filterByOrganizationName("a");
		assertEquals(
				"SELECT distinct rs from ResearchStaff rs left join fetch rs.organization WHERE lower(rs.organization.name) LIKE :name order by rs.id",
				reserachStaffQuery.getQueryString());
		assertEquals("wrong number of parameters", reserachStaffQuery.getParameterMap().size(), 1);
		assertTrue("missing paramenter name", reserachStaffQuery.getParameterMap().containsKey("name"));
		assertEquals("wrong parameter value", reserachStaffQuery.getParameterMap().get("name"), "%a%");

		// reserachStaffQuery.filterByNciInstituteCode("b");
		// assertEquals("wrong number of parameters", reserachStaffQuery.getParameterMap().size(), 2);
		// assertTrue("missing paramenter name", reserachStaffQuery.getParameterMap().containsKey("nciInstituteCode"));
		// assertEquals("wrong parameter value", reserachStaffQuery.getParameterMap().get("nciInstituteCode"), "%b%");

	}

	public void testFilterByNCICode() throws Exception {
		// ResearchStaffQuery reserachStaffQuery = new ResearchStaffQuery();
		// reserachStaffQuery.filterByNciInstituteCode("a");
		// assertEquals(
		// "SELECT distinct o from ReserchStaff o WHERE o.nciInstituteCode LIKE :nciInstituteCode order by o.id",
		// reserachStaffQuery.getQueryString());
		// assertEquals("wrong number of parameters", reserachStaffQuery.getParameterMap().size(), 1);
		// assertTrue("missing paramenter name", reserachStaffQuery.getParameterMap().containsKey("nciInstituteCode"));
		// assertEquals("wrong parameter value", reserachStaffQuery.getParameterMap().get("nciInstituteCode"), "%a%");

	}

}