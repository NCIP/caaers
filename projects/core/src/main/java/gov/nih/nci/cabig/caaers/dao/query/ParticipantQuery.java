package gov.nih.nci.cabig.caaers.dao.query;

public class ParticipantQuery extends AbstractQuery {

	private static String queryString = "SELECT distinct p from Participant p left join fetch p.identifiersInternal order by p.id";

	private static String FIRST_NAME = "firstName";

	private static String LAST_NAME = "lastName";

	private static String IDENTIFIER_VALUE = "identifier";

	public ParticipantQuery() {

		super(queryString);
	}

	public void filterByFirstName(final String firstName) {
		String searchString = "%" + firstName.toLowerCase() + "%";
		andWhere("lower(p.firstName) LIKE :" + FIRST_NAME);
		setParameter(FIRST_NAME, searchString);
	}

	public void filterByLastName(final String lastName) {
		String searchString = "%" + lastName.toLowerCase() + "%";
		andWhere("lower(p.lastName) LIKE :" + LAST_NAME);
		setParameter(LAST_NAME, searchString);
	}

	public void filterByIdentifierValue(final String value) {
		String searchString = "%" + value.toLowerCase() + "%";
		andWhere("lower(p.identifiersInternal.value) LIKE :" + IDENTIFIER_VALUE);
		setParameter(IDENTIFIER_VALUE, searchString);
	}

}