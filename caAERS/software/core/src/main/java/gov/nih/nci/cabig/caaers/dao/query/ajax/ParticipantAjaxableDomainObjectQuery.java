package gov.nih.nci.cabig.caaers.dao.query.ajax;

import gov.nih.nci.cabig.caaers.domain.DateValue;

import java.text.ParseException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * @author Biju Joseph
 */
public class ParticipantAjaxableDomainObjectQuery extends AbstractAjaxableDomainObjectQuery {

    private static String queryString = 
    	
    		"Select " +
                    "participant.id," +
                    "participant.firstName," +
                    "participant.lastName, "+
    		        "participant.gender," +
                    "participant.race," +
                    "participant.ethnicity, " +
    		        "identifier.value, " +
                    "identifier.primaryIndicator, " +
    		        "study.shortTitle as st, " +
                    "study.id as studyId, "+
    		        "sIdentifier.value, " +
                    "sIdentifier.primaryIndicator, "+
    		        "studyOrgs.organization.name, " +
                    "studyOrgs.id, " +
                    "studyOrgs.class, " +
                    "studyOrgs.organization.nciInstituteCode, " +
                    "siteResearchStaff.researchStaff.id, " +
    		        "ss.organization.id as assignedSiteId, " +
                    "ss.organization.name as assignedSite, " +
                    "ss.organization.nciInstituteCode as assignedSiteCode, " +
                    "spa.studySubjectIdentifier " +
            "from Participant participant "+
                    "left join participant.identifiers as identifier "+
                    "left join participant.assignments as spa " +
                    "join spa.studySite as ss "+
                    "join ss.study as study "+
                    "join study.identifiers as sIdentifier "+
                    "join study.studyOrganizations as studyOrgs "+
                    "left join studyOrgs.studyPersonnelsInternal as stper " +
                    "left join stper.siteResearchStaff as siteResearchStaff " +
            "order by participant.firstName ";

    private static final String IDENTIFIER_VALUE = "identifierValue";
    private static final String STUDY_IDENTIFIER_VALUE = "studyIdentifierValue";
    private static final String STUDY_SUBJECT_IDENTIFIER = "studySubjectIdentifier";
    private static final String IDENTIFIER_TYPE = "type";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String STUDY_ID = "studyId";
    
    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String DAY = "day";
    
    private static final String RACE = "race";

    private static final String ETHNITICTY = "ethnicity";
    
    private static final String SHORT_TITLE = "shortTitle";

    public ParticipantAjaxableDomainObjectQuery() {
        super(queryString);


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
                                FIRST_NAME, LAST_NAME, IDENTIFIER_TYPE, IDENTIFIER_VALUE, STUDY_SUBJECT_IDENTIFIER));
        setParameter(IDENTIFIER_VALUE, searchString);
        setParameter(IDENTIFIER_TYPE, searchString);
        setParameter(FIRST_NAME, searchString);
        setParameter(LAST_NAME, searchString);
        setParameter(STUDY_SUBJECT_IDENTIFIER, searchString);

    }
    
    public void filterByStudyIdentifierValue(final String value) {
        if (!StringUtils.isBlank(value)) {
            String searchString = "%" + value.toLowerCase() + "%";
            //join("study.identifiers as sIdentifier");
            andWhere("lower(sIdentifier.value) LIKE :" + STUDY_IDENTIFIER_VALUE);
            setParameter(STUDY_IDENTIFIER_VALUE, searchString);
        }
    }

    public void filterByStudyShortTitle(final String value) {
        if (!StringUtils.isBlank(value)) {
            String searchString = "%" + value.toLowerCase() + "%";
            andWhere("lower(study.shortTitle) LIKE :" + SHORT_TITLE);
            setParameter(SHORT_TITLE, searchString);
        }
    }
    
    public void filterStudiesWithShortTitle(String text) {
        if (!StringUtils.isBlank(text)) {

            String searchString = text != null ? "%" + text.toLowerCase() + "%" : null;

            andWhere(String.format("(lower(study.shortTitle) LIKE :%s )"
                    , SHORT_TITLE));
            setParameter(SHORT_TITLE, searchString);
        }
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
        if (props.get("participantEthnicity") != null) {
            this.filteryByEthnicity(props.get("participantEthnicity").toString());
        }
        if (props.get("participantGender") != null) {
            this.filterByHavingRace(props.get("participantGender").toString());
        }

        if (props.get("participantDateOfBirth") != null) {
        	DateValue dob = DateValue.stringToDateValue(props.get("participantDateOfBirth").toString());
            this.filterByDateOfBirth(dob);
        }
		
	}
    
    public void filterByDateOfBirth(DateValue dateOfBirth) {
        if (dateOfBirth != null && !dateOfBirth.isNull()) {
        	
            andWhere(String.format(" participant.dateOfBirth.year = :%s", YEAR));
            setParameter(YEAR, dateOfBirth.getYear());

            if (dateOfBirth.getMonth() > 0) {
                andWhere(String.format(" participant.dateOfBirth.month = :%s", MONTH));
                setParameter(MONTH, dateOfBirth.getMonth());

            }
            if (dateOfBirth.getDay() > 0) {
                andWhere(String.format(" participant.dateOfBirth.day = :%s", DAY));
                setParameter(DAY, dateOfBirth.getDay());
            }

        }

    }
    
    public void filterByFirstName(final String firstName) {
        if (!StringUtils.isBlank(firstName)) {
            String searchString = "%" + firstName.toLowerCase() + "%";
//            andWhere("lower(participant.firstName) LIKE :" + FIRST_NAME + " OR participant.firstName IS NULL");
            andWhere("lower(participant.firstName) LIKE :" + FIRST_NAME);
            setParameter(FIRST_NAME, searchString);
        }
    }
    

    public void filterByLastName(final String lastName) {
        if (!StringUtils.isBlank(lastName)) {
            String searchString = "%" + lastName.toLowerCase() + "%";
            andWhere("lower(participant.lastName) LIKE :" + LAST_NAME);
//            andWhere("lower(participant.lastName) LIKE :" + LAST_NAME + " OR participant.lastName IS NULL");
            setParameter(LAST_NAME, searchString);
        }
    }

    public void filterByParticipantIdentifierValue(final String value) {
        if (!StringUtils.isBlank(value)) {
            String searchString = "%" + value.toLowerCase() + "%";
            //leftJoin("participant.identifiers as pIdentifier");
//            andWhere("lower(identifier.value) LIKE :" + IDENTIFIER_VALUE + " OR identifier.value IS NULL");
            andWhere("lower(identifier.value) LIKE :" + IDENTIFIER_VALUE + " or lower(spa.studySubjectIdentifier) LIKE :" + IDENTIFIER_VALUE);
            setParameter(IDENTIFIER_VALUE, searchString);
        }
    }


    public void filterByHavingRace(final String race) {
        if (!StringUtils.isBlank(race)) {
            andWhere("participant.gender = :" + RACE);
            setParameter(RACE, race);
        }
    }

    public void filteryByEthnicity(final String ethnicity) {
        if (!StringUtils.isBlank(ethnicity)) {
            andWhere("participant.ethnicity = :" + ETHNITICTY);
            setParameter(ETHNITICTY, ethnicity);
        }
    }

    
    public void filterByStudy(Integer studyId) {
        if (studyId != null) {

            //leftJoin("participant.assignments as spa join spa.studySite as ss join ss.study as study");
            andWhere("study.id =:" + STUDY_ID);
            setParameter(STUDY_ID, studyId);
        }
    }
    

}