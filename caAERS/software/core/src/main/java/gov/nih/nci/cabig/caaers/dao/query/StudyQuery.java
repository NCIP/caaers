package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StudyQuery extends AbstractQuery {
	
	private static String STUDY_ID = "studyId";

    private static String STUDY_IDENTIFIER_VALUE = "identifier";

    private static String STUDY_IDENTIFIER_TYPE = "idType";
    
    private static String STUDY_IDENTIFIER_ORGANIZATION = "idOrgId";
    
    private static String STUDY_IDENTIFIER_SYSTEM = "idSysName";

    private static String STUDY_SHORT_TITLE = "shortTitle";
    
    private static String STUDY_LONG_TITLE = "longTitle";
    
    private static final String IDENTIFIER_VALUE = "identifierValue";

    private static final String IDENTIFIER_TYPE = "type";

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
    
    /**
     * Add a NOT condition on Study.id
     * @param id
     */
    public void ignoreStudyById(Integer id){
    	andWhere("s.id <> :" + STUDY_ID);
    	setParameter(STUDY_ID, id);
    }

    // s.status <> 'adminstratively complete'
    public void filterByNonAdministrativelyComplete() {
        andWhere("s.status <> '" + Study.STATUS_ADMINISTRATIVELY_COMPLETE + "'");
    }

    // identifier
    public void filterByIdentifierValue(final String Identifiervalue) {
    	joinIdentifier();
        String searchString = "%" + Identifiervalue.toLowerCase() + "%";
        andWhere("lower(identifier.value) LIKE :" + STUDY_IDENTIFIER_VALUE);
        setParameter(STUDY_IDENTIFIER_VALUE, searchString);
    }

    public void filterByIdentifierValueExactMatch(final String identifiervalue) {
    		joinIdentifier();
            String searchString = identifiervalue.toLowerCase();
            andWhere("lower(identifier.value) LIKE :" + STUDY_IDENTIFIER_VALUE);
            setParameter(STUDY_IDENTIFIER_VALUE, searchString);
        
    }

    public void filterByIdentifierType(final String type) {
    	joinIdentifier();
        andWhere("identifier.type LIKE :" + STUDY_IDENTIFIER_TYPE);
        setParameter(STUDY_IDENTIFIER_TYPE, type);
    }
    
    public void filterByIdentifier(Identifier identifier){
    	joinIdentifier();
    	//type
    	String type = identifier.getType();
    	if(type != null){
    		andWhere("lower(identifier.type) = :"+ STUDY_IDENTIFIER_TYPE);
    		setParameter(STUDY_IDENTIFIER_TYPE, type.toLowerCase());
    	}
    	
    	//value
    	String value = identifier.getValue();
    	if(value != null){
    		andWhere("lower(identifier.value) = :" + STUDY_IDENTIFIER_VALUE);
            setParameter(STUDY_IDENTIFIER_VALUE, value.toLowerCase());
    	}
    	
    	if(identifier instanceof OrganizationAssignedIdentifier){
    		//organization
    		andWhere("identifier.organization.id = :" + STUDY_IDENTIFIER_ORGANIZATION );
    		setParameter(STUDY_IDENTIFIER_ORGANIZATION, ( (OrganizationAssignedIdentifier) identifier).getOrganization().getId());
    	}else {
    		//system
    		andWhere("lower(identifier.systemName) = :" + STUDY_IDENTIFIER_SYSTEM);
    		setParameter(STUDY_IDENTIFIER_SYSTEM, ((SystemAssignedIdentifier)identifier).getSystemName());
    	}
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
    
    public void filterStudiesWithMatchingText(String text) {
    	joinIdentifier();
        String searchString = text != null ? "%" + text.toLowerCase() + "%" : null;

        andWhere(String.format("(lower(s.shortTitle) LIKE :%s or lower(s.longTitle) LIKE :%s " +
                "or  lower(identifier.type) LIKE :%s " +
                "or lower(identifier.value) LIKE :%s)", STUDY_SHORT_TITLE, STUDY_LONG_TITLE, IDENTIFIER_TYPE, IDENTIFIER_VALUE));
        setParameter(IDENTIFIER_VALUE, searchString);
        setParameter(IDENTIFIER_TYPE, searchString);
        setParameter(STUDY_SHORT_TITLE, searchString);
        setParameter(STUDY_LONG_TITLE, searchString);

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
    
    public void filterByOrganizationId(Integer id){
    	andWhere("ss.organization.id = :id");
    	setParameter("id", id);
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