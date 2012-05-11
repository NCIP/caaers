package gov.nih.nci.cabig.caaers.dao.query;

import gov.nih.nci.cabig.caaers.domain.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    
    
    
    public static final String OTHER_INT_ALIAS = "i";
    
    public static final String DEVICE_INT_ALIAS = "d";
    
   	public static final String AGENT_INT_ALIAS = "sai";
    
    public static final String AGENT_ALIAS = "agt";
    
    

    private SimpleDateFormat dateFormat;

    public StudyQuery() {
        super("select  distinct "+STUDY_ALIAS+" from Study "+STUDY_ALIAS);
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    }

    public void filterByRetiredStatus(Boolean status) {
        super.filterByRetiredStatus(STUDY_ALIAS, status);
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
    
    public void joinOtherIntervention() {

        leftOuterJoin("s.otherInterventions as i");

    }
    
    public void joinDeviceIntervention() {
        leftOuterJoin("s.studyDevices as d");

    }
    
    public void joinAgentIntervention() {

        leftOuterJoin("s.studyAgentsInternal as sai");
    }
    
    public void joinStudyIntervention() {
        leftOuterJoin("s.studyDevices as d");
        leftOuterJoin("s.otherInterventions as i");
        leftOuterJoin("s.studyAgentsInternal as sai");
    }
    

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
 
    public void filterByOtherIntervention(Integer code, String operator) {
    	orWhere("i.studyTherapyType " + operator + " '" + code + "'" );

    }
    
    public void filterByDeviceIntervention(Integer code, String operator) {
  
    	orWhere("d.studyTherapyType " + operator + " '" + code + "'" );
    	
    }
    
    public void filterByAgentIntervention(Integer code, String operator) {

    	orWhere("sai.studyTherapyType " + operator + " '" + code + "'" );
    }
    
    public void filterByStudyIntervention(Integer code, String operator) {
    	orWhere("i.studyTherapyType " + operator + " '" + code + "'" );
    	orWhere("d.studyTherapyType " + operator + " '" + code + "'" );
    	orWhere("sai.studyTherapyType " + operator + " '" + code + "'" );
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
            Organization org =  ((OrganizationAssignedIdentifier) identifier).getOrganization();
            if(org.getNciInstituteCode() != null){
                andWhere("identifier.organization.nciInstituteCode = :orgNCICode" );
                setParameter("orgNCICode", org.getNciInstituteCode());
            }else{
                andWhere("identifier.organization.id = :" + STUDY_IDENTIFIER_ORGANIZATION );
                setParameter(STUDY_IDENTIFIER_ORGANIZATION, org.getId());
            }

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

    public void filterByShortTitleOrIdentifiers(String text) {
        orWhere("lower(s.shortTitle) LIKE :" + STUDY_SHORT_TITLE);
        setParameter(STUDY_SHORT_TITLE, "%" + text.toLowerCase() + "%");
        leftOuterJoin(STUDY_ALIAS + ".identifiers as identifier");
        orWhere("lower(identifier.value) LIKE :"  + IDENTIFIER_VALUE);
        setParameter(IDENTIFIER_VALUE, "%" + text.toLowerCase() + "%");
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
        andWhere(String.format("(lower(s.shortTitle) LIKE :%s or lower(s.longTitle) LIKE :%s " + "or lower(identifier.value) LIKE :%s)", STUDY_SHORT_TITLE, STUDY_LONG_TITLE, IDENTIFIER_VALUE));
        setParameter(IDENTIFIER_VALUE, searchString);
        setParameter(STUDY_SHORT_TITLE, searchString);
        setParameter(STUDY_LONG_TITLE, searchString);
    }
    
    /**
     * Introduced to have left join fetch on identifiers
     * @param text
     */
    public void filterStudiesMatchingText(String text) {
    	leftJoinFetch(STUDY_ALIAS+".identifiers as identifier");
        String searchString = text != null ? "%" + text.toLowerCase() + "%" : null;
        andWhere(String.format("(lower(s.shortTitle) LIKE :%s or lower(s.longTitle) LIKE :%s " + "or lower(identifier.value) LIKE :%s)", STUDY_SHORT_TITLE, STUDY_LONG_TITLE, IDENTIFIER_VALUE));
        setParameter(IDENTIFIER_VALUE, searchString);
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
    
    public void filterStudiesByOrganizations(String[] organizationCodes) {
        if (organizationCodes != null && organizationCodes.length > 0) {
        	joinStudyOrganization();
            andWhere(String.format("ss.organization.nciInstituteCode in (:%s)", "OrganizationCodes"));
            setParameter("OrganizationCodes", Arrays.asList(organizationCodes));
        }
    }
    
}
