package gov.nih.nci.cabig.caaers.dao.query;

public class InvestigatorQuery extends AbstractQuery {

	private static String queryString = "SELECT distinct i from Investigator i left join fetch i.siteInvestigatorsInternal order by i.id";

	private static String FIRST_NAME = "firstName";

	private static String LAST_NAME = "lastName";

	private static String NCI_CODE = "nciIdentifier";

	public InvestigatorQuery() {

		super(queryString);
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

	public void filterByNciIdentifier(final String value) {
		String searchString = "%" + value.toLowerCase() + "%";
		andWhere("lower(i.nciIdentifier) LIKE :" + NCI_CODE);
		setParameter(NCI_CODE, searchString);
	}

}