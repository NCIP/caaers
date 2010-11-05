package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.Term;

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
    
    public static final String STUDY_PARTICIPANT_ALIAS = "spa";
    
    public static final String TERMINOLOGY_ALIAS = "terminology";
    
    public static final String TREATMENT_ASSIGNMENT_ALIAS = "ta";
    
    public static final String ORGANIZATION_ALIAS = "org";
    
    public static final String STUDY_THERAPY_ALIAS = "sthe";
    
    public static final String AGENT_ALIAS = "agt";

    private SimpleDateFormat dateFormat;

    public StudyQuery() {
        super("select  distinct "+STUDY_ALIAS+" from Study "+STUDY_ALIAS);
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    }

    public void joinIdentifier() {
        join(STUDY_ALIAS+".identifiers as identifier");
    }

    public void joinStudyOrganization() {
        join("s.studyOrganizations as ss");
    }

    public void outerjoinStudyOrganization() {
    	leftOuterJoin("s.studyOrganizations as ss");
    }
    
    public void joinOrganization() {
    	joinStudyOrganization();
        join("ss.organization as org");
    }

    public void outerjoinOrganization() {
    	outerjoinStudyOrganization();
    	leftOuterJoin("ss.organization as org");
    }
    
    public void joinStudyParticipantAssignment() {
        joinStudyOrganization();
        join("ss.studyParticipantAssignments as spa");
    }
    
    public void joinParticipant() {
        joinStudyParticipantAssignment();
        join("spa.participant as p");
    }
    // dont change naming convention .. (outer key word..)
    public void outerjoinStudyParticipantAssignment() {
        joinStudyOrganization();
        leftOuterJoin("ss.studyParticipantAssignments as spa");
    }
    
    public void outerjoinParticipant() {
    	outerjoinStudyParticipantAssignment();
    	leftOuterJoin("spa.participant as p");
    }
    
    
    public void joinAeTerminology() {
        join("s.aeTerminology as terminology");
    }

    public void joinTreatmentAssignment() {
        join("s.treatmentAssignmentsInternal as ta");
    }
    
/*
    public void joinStudyTherapy() {
        join("s.studyTherapies as sthe");
    }
*/

    public void joinParticipantIdentifier() {
        joinParticipant();
        join("p.identifiers as pIdentifier");
    }
    public void joinStudyAgents(){
    	join("s.studyAgentsInternal as sagents");
    }
    public void joinAgent() {
    	joinStudyAgents();
        join("sagents.agent as agt");
    }
    
    public void outerjoinStudyAgents(){
    	leftOuterJoin("s.studyAgentsInternal as sagents");
    }
    public void outerjoinAgent() {
    	outerjoinStudyAgents();
    	leftOuterJoin("sagents.agent as agt");
    }    
    
    /**
     * Add a NOT condition on Study.id
     * @param id
     */
    public void ignoreStudyById(Integer id){
    	andWhere("s.id <> :" + STUDY_ID);
    	setParameter(STUDY_ID, id);
    }
    
    public void filterByTerminology(Integer code, String operator) {
    	andWhere("terminology.term "+operator+" :term");
        setParameter("term", Term.getByCode(code));
    }
    
    public void filterByTreatmentCode(String code, String operator) {
    	andWhere("lower(ta.code) "+operator+" :CODE");
    	if (operator.equals("like")) {
    		setParameter("CODE", getLikeValue(code.toLowerCase()));
    	} else {
    		setParameter("CODE", code.toLowerCase());
    	}
    }
    
    public void filterByTreatmentDescription(String description,String operator) {
    	andWhere("lower(ta.description) "+operator+" :DESC");
    	if (operator.equals("like")) {
    		setParameter("DESC", getLikeValue(description.toLowerCase()));
    	} else {
    		setParameter("DESC", description.toLowerCase());
    	}
        
    }
 
    public void filterByStudyTherapy(Integer code,String operator) {
    	andWhere("sthe.studyTherapyType "+operator+" '"+code+"'" );
        //setParameter("therapy", StudyTherapyType.getByCode(code));
    }
    
    public void filterByStudySubjectIdentifier(String studySubjectIdentifier,String operator) {
    	andWhere("lower(spa.studySubjectIdentifier) "+operator+" :SSI");
    	if (operator.equals("like")) {
    		setParameter("SSI", getLikeValue(studySubjectIdentifier.toLowerCase()));
    	} else {
    		setParameter("SSI", studySubjectIdentifier.toLowerCase());
    	}
        
    }

    public void filterByAgent(Integer id ,String operator) {
    	andWhere("agt.id "+ operator+" :ID");
        setParameter("ID", id);
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

    // longTitle
    public void filterByLongTitle(final String longTitleText) {
        andWhere("lower(s.shortTitle) LIKE :" + "LONG_SHORT_TITLE");
        setParameter("LONG_SHORT_TITLE", "%" + longTitleText.toLowerCase() + "%");
    }
    
    // id
    public void filterById(final Integer id) {
        andWhere("s.id = :ID");
        setParameter("ID", id);
    }

    // participant-id
    public void filterByParticipantId(final Integer id) {
        andWhere("p.id = :id");
        setParameter("id", id);
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

    /**
     * filter ther result by sponsor organizationId
     * @param id
     */
    public void filterBySponsorOrganizationId(Integer id){
    	andWhere("ss.class = 'SFS'");
    	andWhere("ss.organization.id = :sponsorId");
    	setParameter("sponsorId", id);
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
