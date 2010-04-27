package gov.nih.nci.cabig.caaers.dao.query.ajax;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * Query is used to return Participant object in autocompleter and other search pages
 *
 * @author Biju Joseph
 */
public class ParticipantAjaxableDomainObjectQuery extends AbstractAjaxableDomainObjectQuery {

    private static final String IDENTIFIER_VALUE = "identifierValue";
    private static final String STUDY_SUBJECT_IDENTIFIER = "studySubjectIdentifier";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String STUDY_ID = "studyId";
    

    public ParticipantAjaxableDomainObjectQuery() {
        super("select participant.id, participant.firstName, participant.lastName, participant.gender," +
                "participant.race,participant.ethnicity,identifier.value,identifier.primaryIndicator," +
                "spa.studySubjectIdentifier from Participant participant");
        leftJoin("participant.identifiers as identifier");
        leftJoin("participant.assignments as spa");
        orderBy("participant.firstName");
    }
    
    public void filterByPrimaryIdentifiers() {
       andWhere("identifier.primaryIndicator is true and sIdentifier.primaryIndicator is true");
    }
    

    public void filterParticipantsWithMatchingText(String text) {

        String searchString = text != null ? "%" + text.toLowerCase() + "%" : null;

        andWhere(String.format("(lower(participant.firstName) LIKE :%s " +
                                "or lower(participant.lastName) LIKE :%s " +
                                "or lower(identifier.value) LIKE :%s " +
                                "or lower(spa.studySubjectIdentifier) LIKE :%s)",
                                FIRST_NAME, LAST_NAME, IDENTIFIER_VALUE, STUDY_SUBJECT_IDENTIFIER));
        setParameter(IDENTIFIER_VALUE, searchString);
        setParameter(FIRST_NAME, searchString);
        setParameter(LAST_NAME, searchString);
        setParameter(STUDY_SUBJECT_IDENTIFIER, searchString);

    }
   
    
    public void filterParticipants(final Map props) throws ParseException {
		

        if (props.get("participantIdentifier") != null) {
        	filterByParticipantIdentifierValue(props.get("participantIdentifier").toString());
        }

        if (props.get("participantFirstName") != null) {
            this.filterByFirstName(props.get("participantFirstName").toString());
        }
        if (props.get("participantLastName") != null) {
            this.filterByLastName(props.get("participantLastName").toString());
        }

		
	}
    
    public void filterByFirstName(final String firstName) {
        if (!StringUtils.isBlank(firstName)) {
            String searchString = "%" + firstName.toLowerCase() + "%";
            andWhere("lower(participant.firstName) LIKE :" + FIRST_NAME);
            setParameter(FIRST_NAME, searchString);
        }
    }
    

    public void filterByLastName(final String lastName) {
        if (!StringUtils.isBlank(lastName)) {
            String searchString = "%" + lastName.toLowerCase() + "%";
            andWhere("lower(participant.lastName) LIKE :" + LAST_NAME);
            setParameter(LAST_NAME, searchString);
        }
    }

    public void filterByParticipantIdentifierValue(final String value) {
        if (!StringUtils.isBlank(value)) {
            String searchString = "%" + value.toLowerCase() + "%";
            andWhere("lower(identifier.value) LIKE :" + IDENTIFIER_VALUE + " or lower(spa.studySubjectIdentifier) LIKE :" + IDENTIFIER_VALUE);
            setParameter(IDENTIFIER_VALUE, searchString);
        }
    }

    
    public void filterByStudy(Integer studyId) {
        if (studyId != null) {
            join("spa.studySite as ss");
        	join("ss.study as study");
            andWhere("study.id =:" + STUDY_ID);
            setParameter(STUDY_ID, studyId);
        }
    }
    

}