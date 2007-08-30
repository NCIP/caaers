package gov.nih.nci.cabig.caaers.dao.query;

/**
 * @author Saurabh Agrawal
 */
public class ResearchStaffQuery extends AbstractQuery {

	private static String queryString = "SELECT distinct rs from ResearchStaff rs left join fetch rs.organization order by rs.id";

	private static String FIRST_NAME = "firstName";

	private static String ORGANIZATION_NAME = "name";

	private static String LAST_NAME = "lastName";

	public ResearchStaffQuery() {

		super(queryString);
	}

	public void filterByOrganizationName(final String name) {
		String searchString = "%" + name.toLowerCase() + "%";
		andWhere("lower(rs.organization.name) LIKE :" + ORGANIZATION_NAME);
		setParameter(ORGANIZATION_NAME, searchString);
	}

	public void filterByFirstName(final String firstName) {
		String searchString = "%" + firstName.toLowerCase() + "%";
		andWhere("lower(rs.firstName) LIKE :" + FIRST_NAME);
		setParameter(FIRST_NAME, searchString);
	}

	public void filterByLastName(final String lastName) {
		String searchString = "%" + lastName.toLowerCase() + "%";
		andWhere("lower(rs.lastName) LIKE :" + LAST_NAME);
		setParameter(LAST_NAME, searchString);
	}

}