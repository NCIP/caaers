/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.dao.query;

public class ParticipantQuery extends AbstractQuery {

    private static final String queryString = "SELECT distinct p from Participant p ";
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
    	joinAssignment();
    	final String ssi = generateParam();
    	andWhere("lower(assignment.studySubjectIdentifier) " + parseOperator(operator) + " :" + ssi);
    	if (operator.equals("like")) {
    		setParameter(ssi, getLikeValue(studySubjectIdentifier.toLowerCase()));
    	} else {
    		setParameter(ssi, studySubjectIdentifier.toLowerCase());
    	}
        
    }

    public void filterByStudySubjectIdentifier(String studySubjectIdentifier) {
       filterByStudySubjectIdentifier(studySubjectIdentifier, "=");
    }

    public void filterByIdentifierValue(final String value) {
        String searchString = "%" + value.toLowerCase() + "%";
        andWhere("lower(identifier.value) LIKE :" + generateParam(searchString));
    }

    public void filterByIdentifierValueExactMatch(final String value) {
        andWhere("identifier.value = :" + generateParam(value));
    }

    public void filterByIdentifierTypeExactMatch(final String type) {
        andWhere("identifier.type = :" + generateParam(type));
    }
        
    public void filterByNotMachingStudySiteId(final Integer studySiteId) {
        andWhere("p.id not in (select assignments.participant.id from  StudyParticipantAssignment assignments where assignments.studySite.id = :"
                        + generateParam(studySiteId) + ")");
    }

    public void excludeHavingGender(final String gender) {
        andWhere("p.gender != :" + generateParam(gender));

    }

    public void excludeHavingRace(final String race) {
        andWhere("p.race != :" + generateParam(race));
    }

    public void excludeHavingEthnicity(final String ethnicity) {
        andWhere("p.ethnicity != :" + generateParam(ethnicity));
    }

    public void filterByOrganizationId(Integer organizationId){
    	andWhere("p.id in (select assignments.participant.id from StudyParticipantAssignment assignments where assignments.studySite.organization.id = :" + generateParam(organizationId));
    }

    public void filterByStudySiteNciCode(String nciCode){
        joinStudySite();
        andWhere("studySite.organization.nciInstituteCode = :" + generateParam(nciCode));
    }

}
