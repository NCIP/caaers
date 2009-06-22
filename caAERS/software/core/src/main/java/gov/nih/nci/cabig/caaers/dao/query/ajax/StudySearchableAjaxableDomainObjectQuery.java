package gov.nih.nci.cabig.caaers.dao.query.ajax;

import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Study;

import org.apache.commons.lang.StringUtils;

/**
 * this query Will NOT work if you are retriving study sites by searching on participants also
 *
 * @author Biju Joseph
 */
public class StudySearchableAjaxableDomainObjectQuery extends AbstractAjaxableDomainObjectQuery {
    private static String queryString = "Select study.id,study.shortTitle," +
            "identifier.value,identifier.primaryIndicator,study.phaseCode,study.status," +
            "(select sfs.organization.nciInstituteCode from StudyFundingSponsor sfs  where sfs.study.id =study.id) as fundingSponsor, " +
            "ss.organization.name,ss.id,ss.class,ss.organization.nciInstituteCode, stper.researchStaff.id," +
            "(select scc.organization.nciInstituteCode from StudyCoordinatingCenter scc  where scc.study.id =study.id) as coordinatingCenter "+
            "from Study study " +
            "left join study.identifiers as identifier " +
           // "left join study.studyOrganizations as ss  " +
            "join study.studyOrganizations as ss left join ss.studyPersonnelsInternal as stper " +
            "order by study.shortTitle ";


    private static final String FIRST_NAME = "firstName";

    private static final String LAST_NAME = "lastName";


    private static final String RACE = "race";

    private static final String ETHNITICTY = "ethnicity";
    private static final String IDENTIFIER_EXACT_TYPE = "identifierType";
    private static final String IDENTIFIER_EXACT_VALUE = "identifierValue";
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String DAY = "day";
    private static final String STUDY_SITE_CLASS = "studySiteClass";
    private static final String SITE_ID = "siteId";
    private static final String IDENTIFIER_VALUE = "identifierValue";

    private static final String IDENTIFIER_TYPE = "type";
    private static final String SHORT_TITLE = "shortTitle";
    private static final String LONG_TITLE = "longTitle";
    private static final String PARTICIPANT_ID = "participantId";
    private static final String STATUS = "status";
    private static final String QC_STATUS = "qcStatus";

    public StudySearchableAjaxableDomainObjectQuery() {
        super(queryString);


    }

    public void filterStudiesByStudySiteBySiteId(Integer siteId) {
        if (siteId != null) {
            andWhere(String.format("ss.class = :%s", STUDY_SITE_CLASS));
            setParameter(STUDY_SITE_CLASS, "SST");
            andWhere(String.format("ss.organization.id = :%s", SITE_ID));
            setParameter(SITE_ID, siteId);
        }
    }

    public void filterStudiesWithMatchingIdentifierOnly(String text) {
        if (!StringUtils.isBlank(text)) {

            String searchString = text != null ? "%" + text.toLowerCase() + "%" : null;

            andWhere(String.format("(lower(identifier.type) LIKE :%s " +
                    "or lower(identifier.value) LIKE :%s)", IDENTIFIER_EXACT_TYPE, IDENTIFIER_EXACT_VALUE));
            setParameter(IDENTIFIER_EXACT_VALUE, searchString);
            setParameter(IDENTIFIER_EXACT_TYPE, searchString);
        }
    }

    public void filterStudiesWithExactMatchingIdentifierOnly(String text) {
        if (!StringUtils.isBlank(text)) {
            String searchString = text != null ?  text.toLowerCase()  : null;

            andWhere(String.format("lower(identifier.value) LIKE :%s)", IDENTIFIER_EXACT_VALUE));
            setParameter(IDENTIFIER_EXACT_VALUE, searchString);
        }
    }
    
    public void filterByParticipant(String firstName, String lastName, String ethnicity,
                                    final String identifierValue, String gender, DateValue dateOfBirth) {

        leftJoin("ss.studyParticipantAssignments as spa left join spa.participant as p");

        filterByParticipantIdentifierValue(identifierValue);

        filterByFirstName(firstName);
        filterByLastName(lastName);

        filteryByEthnicity(ethnicity);

        filterByHavingRace(gender);

        filterByDateOfBirth(dateOfBirth);


    }

    private void filterByDateOfBirth(DateValue dateOfBirth) {
        if (dateOfBirth != null && !dateOfBirth.isNull()) {
            andWhere(String.format(" p.dateOfBirth.year = :%s", YEAR));
            setParameter(YEAR, dateOfBirth.getYear());

            if (dateOfBirth.getMonth() > 0) {
                andWhere(String.format(" p.dateOfBirth.month = :%s", MONTH));
                setParameter(MONTH, dateOfBirth.getMonth());

            }
            if (dateOfBirth.getDay() > 0) {
                andWhere(String.format(" p.dateOfBirth.day = :%s", DAY));
                setParameter(DAY, dateOfBirth.getDay());
            }

        }

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

        leftJoin("ss.studyParticipantAssignments as spa left join spa.participant as p");
        //to get reserch staff for sites not part of assignment 
        andWhere("p.id =:" + PARTICIPANT_ID);
        //andWhere("(p.id =:" + PARTICIPANT_ID +" or p.id is null)");
        setParameter(PARTICIPANT_ID, participantId);

    }


    public void filterByStudyStatus(boolean ignoreCompletedStudy) {
        if (ignoreCompletedStudy) {
            andWhere("study.status <> :" + STATUS);
            setParameter(STATUS, Study.STATUS_ADMINISTRATIVELY_COMPLETE);
        }
    }
    
    /**
     * If true, will return only QC completed studies.
     * @param ignoreNonQCedStudy
     */
    public void filterByDataEntryStatus(boolean ignoreNonQCedStudy) {
        if (ignoreNonQCedStudy) {
            andWhere("study.dataEntryStatus = :" + QC_STATUS);
            setParameter(QC_STATUS, true);
        }
    }

    public void filterStudiesWithMatchingShortTitleOnly(String text) {
        if (!StringUtils.isBlank(text)) {

            String searchString = text != null ? "%" + text.toLowerCase() + "%" : null;

            andWhere(String.format("(lower(study.shortTitle) LIKE :%s )"
                    , SHORT_TITLE));
            setParameter(SHORT_TITLE, searchString);
        }
    }

    private void filterByFirstName(final String firstName) {
        if (!StringUtils.isBlank(firstName)) {
            String searchString = "%" + firstName.toLowerCase() + "%";
            andWhere("lower(p.firstName) LIKE :" + FIRST_NAME);
            setParameter(FIRST_NAME, searchString);
        }
    }

    private void filterByLastName(final String lastName) {
        if (!StringUtils.isBlank(lastName)) {
            String searchString = "%" + lastName.toLowerCase() + "%";
            andWhere("lower(p.lastName) LIKE :" + LAST_NAME);
            setParameter(LAST_NAME, searchString);
        }
    }

    private void filterByParticipantIdentifierValue(final String value) {
        if (!StringUtils.isBlank(value)) {
            String searchString = "%" + value.toLowerCase() + "%";
            leftJoin("p.identifiers as pIdentifier");
            andWhere("lower(pIdentifier.value) LIKE :" + IDENTIFIER_VALUE);
            setParameter(IDENTIFIER_VALUE, searchString);
        }
    }


    private void filterByHavingRace(final String race) {
        if (!StringUtils.isBlank(race)) {
            andWhere("p.gender = :" + RACE);
            setParameter(RACE, race);
        }
    }

    private void filteryByEthnicity(final String ethnicity) {
        if (!StringUtils.isBlank(ethnicity)) {
            andWhere("p.ethnicity = :" + ETHNITICTY);
            setParameter(ETHNITICTY, ethnicity);
        }
    }


}