package gov.nih.nci.cabig.caaers.dao.query.ajax;

/**
 * @author Biju Joseph
 */
public class ParticipantAjaxableDomainObjectQuery extends AbstractAjaxableDomainObjectQuery {

    private static String queryString = "Select participant.id,participant.firstName,participant.lastName,identifier.value,identifier.primaryIndicator " +
            "from Participant participant left join participant.identifiers as identifier  order by participant.firstName ";

    private static final String IDENTIFIER_VALUE = "identifierValue";

    private static final String IDENTIFIER_TYPE = "type";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String STUDY_ID = "studyId";

    public ParticipantAjaxableDomainObjectQuery() {
        super(queryString);


    }

    public void filterParticipantsWithMatchingText(String text) {

        String searchString = text != null ? "%" + text.toLowerCase() + "%" : null;

        andWhere(String.format("(lower(participant.firstName) LIKE :%s or lower(participant.lastName) LIKE :%s " +
                "or  lower(identifier.type) LIKE :%s " +
                "or lower(identifier.value) LIKE :%s)", FIRST_NAME, LAST_NAME, IDENTIFIER_TYPE, IDENTIFIER_VALUE));
        setParameter(IDENTIFIER_VALUE, searchString);
        setParameter(IDENTIFIER_TYPE, searchString);
        setParameter(FIRST_NAME, searchString);
        setParameter(LAST_NAME, searchString);

    }

    public void filterByStudy(Integer studyId) {
        if (studyId != null) {

            leftJoin("participant.assignments as spa join spa.studySite as ss join ss.study as study");
            andWhere("study.id =:" + STUDY_ID);
            setParameter(STUDY_ID, studyId);
        }
    }


}