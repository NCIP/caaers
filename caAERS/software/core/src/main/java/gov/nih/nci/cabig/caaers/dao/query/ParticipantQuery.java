package gov.nih.nci.cabig.caaers.dao.query;

public class ParticipantQuery extends AbstractQuery {

    private static final String queryString = "SELECT distinct p from Participant p ";

    private static final String FIRST_NAME = "firstName";

    private static final String LAST_NAME = "lastName";

    private static final String IDENTIFIER_VALUE = "identifier";

    private static final String IDENTIFIER_TYPE = "type";

    private static final String STUDY_SITE_ID = "studySiteId";

    private static final String GENDER = "gender";

    private static final String RACE = "race";

    private static final String ETHNITICTY = "ethnicity";

    public ParticipantQuery() {
        super(queryString);
        orderBy("order by p.id");
    }

   

    /**
     * SELECT distinct p from Participant p left join fetch p.identifiers
     */
    public void leftJoinFetchOnIdentifiers() {
        leftJoinFetch("p.identifiers");
    }

    /**
     * select distinct p from Participant p join p.identifiers
     */
    public void joinOnIdentifiers() {
        join("p.identifiers");
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
        andWhere("lower(p.identifiers.value) LIKE :" + IDENTIFIER_VALUE);
        setParameter(IDENTIFIER_VALUE, searchString);
    }

    public void filterByIdentifierValueExactMatch(final String value) {
        andWhere("p.identifiers.value = :" + IDENTIFIER_VALUE);
        setParameter(IDENTIFIER_VALUE, value);
    }

    public void filterByIdentifierTypeExactMatch(final String type) {
        andWhere("p.identifiers.type = :" + IDENTIFIER_TYPE);
        setParameter(IDENTIFIER_TYPE, type);
    }

    public void filterByNotMachingStudySiteId(final Integer studySiteId) {
        andWhere("p.id not in (select assignments.participant.id from  StudyParticipantAssignment assignments where assignments.studySite.id=:"
                        + STUDY_SITE_ID + ")");
        setParameter(STUDY_SITE_ID, studySiteId);
    }

    public void excludeHavingGender(final String gender) {
        andWhere("p.gender != :" + GENDER);
        setParameter(GENDER, gender);

    }

    public void excludeHavingRace(final String race) {
        andWhere("p.race != :" + RACE);
        setParameter(RACE, race);
    }

    public void excludeHavingEthnicity(final String ethnicity) {
        andWhere("p.ethnicity != :" + ETHNITICTY);
        setParameter(ETHNITICTY, ethnicity);
    }

    public void filterByOrganizationId(Integer organizationId){
    	andWhere("p.id in (select assignments.participant.id from StudyParticipantAssignment assignments where assignments.studySite.organization.id=:orgId)");
    	setParameter("orgId", organizationId);
    }    

}