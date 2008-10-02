package gov.nih.nci.cabig.caaers.dao.query.ajax;

import gov.nih.nci.cabig.caaers.domain.Study;

/**
 * @author Biju Joseph
 */
public class StudyAjaxableDomainObjectQuery extends AbstractAjaxableDomainObjectQuery {

    private static String queryString = "Select study.id,study.shortTitle,identifier.value,identifier.primaryIndicator " +
            "from Study study left join study.identifiers as identifier  order by study.shortTitle ";

    private static final String IDENTIFIER_VALUE = "identifierValue";

    private static final String IDENTIFIER_TYPE = "type";
    private static final String SHORT_TITLE = "shortTitle";
    private static final String LONG_TITLE = "longTitle";
    private static final String PARTICIPANT_ID = "participantId";
    private static final String STATUS = "status";

    public StudyAjaxableDomainObjectQuery(String queryString) {
        super(queryString);

    }

    public StudyAjaxableDomainObjectQuery() {
        super(queryString);


    }

    public void filterStudiesWithMatchingText(String text) {
        String searchString = text != null ? "%" + text.toLowerCase() + "%" : null;

        andWhere(String.format("(lower(study.shortTitle) LIKE :%s or lower(study.longTitle) LIKE :%s " +
                "or  lower(identifier.type) LIKE :%s " +
                "or lower(identifier.value) LIKE :%s)", SHORT_TITLE, LONG_TITLE, IDENTIFIER_TYPE, IDENTIFIER_VALUE));
        setParameter(IDENTIFIER_VALUE, searchString);
        setParameter(IDENTIFIER_TYPE, searchString);
        setParameter(SHORT_TITLE, searchString);
        setParameter(LONG_TITLE, searchString);

    }

    public void filterByParticipant(Integer participantId) {

        leftJoin("study.studyOrganizations as ss join ss.studyParticipantAssignments as spa join spa.participant as p");
        andWhere("p.id =:" + PARTICIPANT_ID);
        setParameter(PARTICIPANT_ID, participantId);

    }


    public void filterByStudyStatus(boolean ignoreCompletedStudy) {
        if (ignoreCompletedStudy) {
            andWhere("study.status <> :" + STATUS);
            setParameter(STATUS, Study.STATUS_ADMINISTRATIVELY_COMPLETE);
        }
    }
}
