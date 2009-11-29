package gov.nih.nci.cabig.caaers.dao.query;

public class InvestigatorQuery extends AbstractQuery {

    private static String queryString = "SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal si order by i.id";
    
    private static String FIRST_NAME = "firstName";

    private static String LAST_NAME = "lastName";

    private static String NCI_CODE = "nciIdentifier";
    
    private static String EMAIL_ADDRESS = "emailAddress";
    
    private static String LOGIN_ID = "loginId";
    
    private static String ORGANIZATION = "organization";

    public InvestigatorQuery() {

        super(queryString);
    }
    
    public void filterByDifferentInvestigatorId(Integer id){
    	if(id == null) return;
    	andWhere(" i.id != :iid");
    	setParameter("iid", id);
    }

    public void filterByFirstName(final String firstName) {
        String searchString = "%" + firstName.toLowerCase() + "%";
        andWhere("lower(i.firstName) LIKE :" + FIRST_NAME);
        setParameter(FIRST_NAME, searchString);
    }

    public void filterByLastName(final String lastName) {
        String searchString = "%" + lastName.toLowerCase() + "%";
        andWhere("lower(i.lastName) LIKE :" + LAST_NAME);
        setParameter(LAST_NAME, searchString);
    }
    
    public void filterByEmailAddress(final String emailAddress) {
        String searchString = "%" + emailAddress.trim().toLowerCase() + "%";
        andWhere("lower(i.emailAddress) LIKE :" + EMAIL_ADDRESS);
        setParameter(EMAIL_ADDRESS, searchString);
    }

    public void filterByNciIdentifier(final String value) {
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(i.nciIdentifier) LIKE :" + NCI_CODE);
        setParameter(NCI_CODE, searchString);
    }
    public void filterByNciIdentifierExactMatch(final String value) {
        String searchString = value.toLowerCase();
        andWhere("lower(i.nciIdentifier) LIKE :" + NCI_CODE);
        setParameter(NCI_CODE, searchString);
    }
    public void filterByLoginId(final String loginId) {
        String searchString = "%" + loginId.trim().toLowerCase() + "%";
        andWhere(String.format("lower(i.loginId) LIKE :%s", LOGIN_ID));
        setParameter(LOGIN_ID, searchString);
    }
    
    public void filterByExactLoginId(final String loginId) {
    	 String searchString = loginId.trim().toLowerCase();
         andWhere(String.format("lower(i.loginId) = :%s", LOGIN_ID));
         setParameter(LOGIN_ID, searchString);
    }
    
    public void filterByOrganization(final String organization) {
        String searchString = organization.trim();
        andWhere("si.organization.id =:" + ORGANIZATION);
        setParameter(ORGANIZATION, Integer.parseInt(searchString));
    }

}