package gov.nih.nci.cabig.caaers.dao.query;

/**
 * @author Saurabh Agrawal
 * @author Biju Joseph
 */
public class ResearchStaffQuery extends AbstractQuery {

    private static String queryString = "SELECT distinct rs from ResearchStaff rs left join fetch rs.organization order by rs.id";

    private static String FIRST_NAME = "firstName";

    private static String ORGANIZATION_NAME = "name";
    
    private static String ORGANIZATION_NCI_INSTITUTE_CODE = "organizationNciInstituteCode";

    private static String LAST_NAME = "lastName";

    private static String EMAIL_ADDRESS = "emailAddress";
    
    private static String LOGIN_ID = "loginId";

    private static String NCI_IDENTIFIER = "nciIdentifier";
    
    private static String ORGANIZATION = "organization";

    public ResearchStaffQuery() {

        super(queryString);
    }

    public void filterByOrganizationName(final String name) {
        String searchString = "%" + name.toLowerCase() + "%";
        andWhere("lower(rs.organization.name) LIKE :" + ORGANIZATION_NAME);
        setParameter(ORGANIZATION_NAME, searchString);
    }
    
    public void filterByOrganizationNciInstituteCode(final String organizationNciInstituteCode) {
        String searchString = "%" + organizationNciInstituteCode.toLowerCase() + "%";
        andWhere("lower(rs.organization.nciInstituteCode) LIKE :" + ORGANIZATION_NCI_INSTITUTE_CODE);
        setParameter(ORGANIZATION_NCI_INSTITUTE_CODE, searchString);
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

    public void filterByEmailAddress(final String emailAddress) {
        String searchString = "%" + emailAddress.trim().toLowerCase() + "%";
        andWhere("lower(rs.emailAddress) LIKE :" + EMAIL_ADDRESS);
        setParameter(EMAIL_ADDRESS, searchString);
    }


    public void filterByLoginId(final String loginId) {
        String searchString = "%" + loginId.trim().toLowerCase() + "%";
        andWhere(String.format("lower(rs.loginId) LIKE :%s", LOGIN_ID));
        setParameter(LOGIN_ID, searchString);
    }

    public void filterByNciIdentifier(final String nciIdentifier) {
        String searchString = "%" + nciIdentifier.toLowerCase() + "%";
        andWhere("lower(rs.nciIdentifier) LIKE :" + NCI_IDENTIFIER);
        setParameter(NCI_IDENTIFIER, searchString);
    }
    
    public void filterByOrganization(final String organization) {
        String searchString = organization.trim();
        andWhere("rs.organization.id =:" + ORGANIZATION);
        setParameter(ORGANIZATION, Integer.parseInt(searchString));
    }
    
}