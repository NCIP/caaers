/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
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
    
    public static final String ASSIGNMENT_ALIAS = "assignment";

    public ParticipantQuery() {
        super(queryString);
        orderBy("p.id");
    }

    /**
     * SELECT distinct p from Participant p left join fetch p.identifiers
     */
    public void leftJoinFetchOnIdentifiers() {
        leftJoinFetch("p.identifiers identifier");
    }

    /**
     * select distinct p from Participant p join p.identifiers
     */
    public void joinOnIdentifiers() {
        join("p.identifiers identifier");
    }
    
    public void joinAssignment() {
    	join("p.assignments assignment");
    }
    
    // participant shud have assignmengt and study , outerjoin method is only for naming convention in advanced search
    //public void outerjoinAssignment() {
    	//joinAssignment();
    //}
    //public void outerjoinStudy() {
    	//joinStudy();
    //}
    
    public void joinStudySite() {
    	joinAssignment();
    	join ("assignment.studySite studySite");
    }
    
    public void joinStudy() {
    	joinAssignment();
    	joinStudySite();
    	join("studySite.study s");
    }
    
    public void filterByStudySubjectIdentifier(String studySubjectIdentifier,String operator) {
    	andWhere("lower(assignment.studySubjectIdentifier) "+operator+" :SSI");
    	if (operator.equals("like")) {
    		setParameter("SSI", getLikeValue(studySubjectIdentifier.toLowerCase()));
    	} else {
    		setParameter("SSI", studySubjectIdentifier.toLowerCase());
    	}
        
    }

    public void filterByIdentifierValue(final String value) {
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(identifier.value) LIKE :" + IDENTIFIER_VALUE);
        setParameter(IDENTIFIER_VALUE, searchString);
    }

    public void filterByIdentifierValueExactMatch(final String value) {
        andWhere("identifier.value = :" + IDENTIFIER_VALUE);
        setParameter(IDENTIFIER_VALUE, value);
    }

    public void filterByIdentifierTypeExactMatch(final String type) {
        andWhere("identifier.type = :" + IDENTIFIER_TYPE);
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
