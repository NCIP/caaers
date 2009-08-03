package gov.nih.nci.cabig.caaers.dao.query;

/**
 * @author Ion C. Olaru
 */
public class SiteResearchStaffQuery extends AbstractQuery {

    private static String queryString = "SELECT distinct srs from SiteResearchStaff srs " +
            "left join fetch srs.researchStaff rs " +
            "left join fetch srs.organization org " +
            "order by srs.id";

    private static String FIRST_NAME = "firstName";
    private static String LAST_NAME = "lastName";
    private static String ORGANIZATION = "organization";

    public SiteResearchStaffQuery() {
        super(queryString);
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

    public void filterByOrganization(final String organization) {
        String searchString = organization.trim();
        andWhere("srs.organization.id =:" + ORGANIZATION);
        setParameter(ORGANIZATION, Integer.parseInt(searchString));
    }
}