package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.Study;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StudyQuery extends AbstractQuery {

    private static String STUDY_IDENTIFIER_VALUE = "identifier";

    private static String STUDY_IDENTIFIER_TYPE = "idType";

    private static String STUDY_SHORT_TITLE = "shortTitle";

    private SimpleDateFormat dateFormat;

    public StudyQuery() {
        super("select s from Study s");
        // andWhere("s.loadStatus > 0");
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    }

    public void joinIdentifier() {
        join("s.identifiers as identifier");
    }

    public void joinStudyOrganization() {
        join("s.studyOrganizations as ss");
    }

    public void joinStudyParticipantAssignment() {
        joinStudyOrganization();
        join("ss.studyParticipantAssignments as spa");
    }

    public void joinParticipant() {
        joinStudyParticipantAssignment();
        join("spa.participant as p");
    }

    public void joinParticipantIdentifier() {
        joinParticipant();
        join("p.identifiers as pIdentifier");
    }

    // s.status <> 'adminstratively complete'
    public void filterByNonAdministrativelyComplete() {
        andWhere("s.status <> '" + Study.STATUS_ADMINISTRATIVELY_COMPLETE + "'");
    }

    // identifier
    public void filterByIdentifierValue(final String Identifiervalue) {
        String searchString = "%" + Identifiervalue.toLowerCase() + "%";
        andWhere("lower(s.identifiers.value) LIKE :" + STUDY_IDENTIFIER_VALUE);
        setParameter(STUDY_IDENTIFIER_VALUE, searchString);
    }

    public void filterByIdentifierValueExactMatch(final String identifiervalue) {
            String searchString = identifiervalue.toLowerCase();
            andWhere("lower(s.identifiers.value) LIKE :" + STUDY_IDENTIFIER_VALUE);
            setParameter(STUDY_IDENTIFIER_VALUE, searchString);
        
    }

    public void filterByIdentifierType(final String type) {
        andWhere("s.identifiers.type LIKE :" + STUDY_IDENTIFIER_TYPE);
        setParameter(STUDY_IDENTIFIER_TYPE, type);
    }

    // shortTitle
    public void filterByShortTitle(final String shortTitleText) {
        andWhere("lower(s.shortTitle) LIKE :" + STUDY_SHORT_TITLE);
        setParameter(STUDY_SHORT_TITLE, "%" + shortTitleText.toLowerCase() + "%");
    }

    // participant-FirstName
    public void filterByParticipantFirstName(final String fName) {
        andWhere("lower(p.firstName) LIKE :pfName");
        setParameter("pfName", "%" + fName.toLowerCase() + "%");
    }

    // participant - LastName
    public void filterByParticipantLastName(final String lName) {
        andWhere("lower(p.lastName) LIKE :plName");
        setParameter("plName", "%" + lName.toLowerCase() + "%");
    }

    // participant - Ethnicity
    public void filterByParticipantEthnicity(String ethenicity) {
        andWhere("lower(p.ethnicity) LIKE :pEthenicity");
        setParameter("pEthenicity", "%" + ethenicity.toLowerCase() + "%");
    }

    // p.gender
    public void filterByParticipantGender(final String gender) {
        andWhere("lower(p.gender) LIKE :pGender");
        setParameter("pGender", gender.toLowerCase());
    }

    // participant DOB
    public void filterByParticipantDateOfBirth(String strDob) {
        andWhere("p.dateOfBirth = :pDOB");
        Date dob = null;
        try {
            dob = dateFormat.parse(strDob);
        } catch (Exception e) {
        }
        setParameter("pDOB", dob);
    }

    // participantIdentifier
    public void filterByParticipantIdentifierValue(final String participantIdentifierValue) {
        andWhere("lower(pIdentifier.value) LIKE :pIdVal");
        setParameter("pIdVal", "%" + participantIdentifierValue.toLowerCase() + "%");
    }
    
    public void filterByFundingSponsorNameExactMatch(String sponsorName){
    	andWhere("ss.class = 'SFS'");
    	andWhere("ss.organization.name = :sponsorName");
    	setParameter("sponsorName", sponsorName);
    }
    
    public void filterByStudyOrganizationNameExactMatch(String studyOrgName){
    	andWhere("ss.organization.name = :studyOrgName");
    	setParameter("studyOrgName", studyOrgName);
    }
    
    /**
     * If true, will return only DATA ENTRY completed studies.
     * @param ignoreNonQCedStudy
     */
    public void filterByDataEntryStatus(boolean ignoreNonQCedStudy) {
        if (ignoreNonQCedStudy) {
            andWhere("s.dataEntryStatus = :dataEntryStatus");
            setParameter("dataEntryStatus", true);
        }
    }
}