package gov.nih.nci.cabig.caaers.validator;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyOrganization;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;

import java.util.ArrayList;
import java.util.List;

public class StudyDomainObjectValidatorTest extends CaaersTestCase{

	Study sampleStudy;
	List<String> errors;
	DomainObjectValidator domainObjectValidator;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		sampleStudy = new Study();
		errors = new ArrayList<String>();
		domainObjectValidator = (DomainObjectValidator)getDeployedApplicationContext().getBean("domainObjectValidator");
	}
	
	public void testDuplicateStudyAgents(){
		sampleStudy = Fixtures.createStudy("short title");
		
		StudyAgent sa1 = Fixtures.createStudyAgent("agent1");
		StudyAgent sa2 = Fixtures.createStudyAgent("agent1");
		sampleStudy.addStudyAgent(sa1);
		sampleStudy.addStudyAgent(sa2);
		
		errors = domainObjectValidator.validate(sampleStudy);
		assertEquals(1, errors.size());
	}
	
	public void testUniqueStudyAgents(){
		sampleStudy = Fixtures.createStudy("short title");
		
		StudyAgent sa1 = Fixtures.createStudyAgent("agent1");
		StudyAgent sa2 = Fixtures.createStudyAgent("agent2");
		sampleStudy.addStudyAgent(sa1);
		sampleStudy.addStudyAgent(sa2);
		
		errors = domainObjectValidator.validate(sampleStudy);
		assertEquals(0, errors.size());
	}
	
	public void testDuplicateIdentifier(){
		sampleStudy = Fixtures.createStudy("Short Title");
		Organization organization = Fixtures.createOrganization("CTEP");
		Identifier orgIdentifier1 = Fixtures.createOrganizationAssignedIdentifier("ORG-1", organization);
		Identifier orgIdentifier2 = Fixtures.createOrganizationAssignedIdentifier("ORG-1", organization);
		sampleStudy.addIdentifier(orgIdentifier1);
		sampleStudy.addIdentifier(orgIdentifier2);
		errors = domainObjectValidator.validate(sampleStudy);
		assertEquals(1, errors.size());
	}
	
	public void testUniqueIdentifier(){
		sampleStudy = Fixtures.createStudy("Short Title");
		Organization organization1 = Fixtures.createOrganization("CTEP");
		Organization organization2 = Fixtures.createOrganization("CALGB");
		Identifier orgIdentifier1 = Fixtures.createOrganizationAssignedIdentifier("ORG-1", organization1);
		Identifier orgIdentifier2 = Fixtures.createOrganizationAssignedIdentifier("ORG-2", organization2);
		sampleStudy.addIdentifier(orgIdentifier1);
		sampleStudy.addIdentifier(orgIdentifier2);
		errors = domainObjectValidator.validate(sampleStudy);
		assertEquals(0, errors.size());
	}
	
	public void testDuplicateTreatmentAssignments(){
		sampleStudy = Fixtures.createStudy("Short Title");
		TreatmentAssignment treatmentAssignment1 = Fixtures.createTreatmentAssignment("Tac1");
		TreatmentAssignment treatmentAssignment2 = Fixtures.createTreatmentAssignment("Tac1");
		sampleStudy.addTreatmentAssignment(treatmentAssignment1);
		sampleStudy.addTreatmentAssignment(treatmentAssignment2);
		errors = domainObjectValidator.validate(sampleStudy);
		assertEquals(1, errors.size());
	}
	
	public void testUniqueTreatmentAssignments(){
		sampleStudy = Fixtures.createStudy("Short Title");
		TreatmentAssignment treatmentAssignment1 = Fixtures.createTreatmentAssignment("Tac1");
		TreatmentAssignment treatmentAssignment2 = Fixtures.createTreatmentAssignment("Tac2");
		sampleStudy.addTreatmentAssignment(treatmentAssignment1);
		sampleStudy.addTreatmentAssignment(treatmentAssignment2);
		errors = domainObjectValidator.validate(sampleStudy);
		assertEquals(0, errors.size());
	}
	
	public void testDuplicateCtepStudyDiseases(){
		sampleStudy = Fixtures.createStudy("Short Title");
		DiseaseTerm dTerm1 = new DiseaseTerm();
		dTerm1.setTerm("term");
		dTerm1.setMeddraCode("meddraCode");
		Fixtures.createCtepStudyDisease(sampleStudy, dTerm1);
		Fixtures.createCtepStudyDisease(sampleStudy, dTerm1);
		errors = domainObjectValidator.validate(sampleStudy);
		assertEquals(1, errors.size());
		
	}
	
	public void testUniqueCtepStudyDiseases(){
		sampleStudy = Fixtures.createStudy("Short Title");
		DiseaseTerm dTerm1 = new DiseaseTerm();
		dTerm1.setTerm("term1");
		dTerm1.setMeddraCode("meddraCode1");
		DiseaseTerm dTerm2 = new DiseaseTerm();
		dTerm2.setTerm("term2");
		dTerm2.setMeddraCode("meddraCode2");
		Fixtures.createCtepStudyDisease(sampleStudy, dTerm1);
		Fixtures.createCtepStudyDisease(sampleStudy, dTerm2);
		errors = domainObjectValidator.validate(sampleStudy);
		assertEquals(0, errors.size());
	}
	
	public void testDuplicateMeddraStudyDiseases(){
		sampleStudy = Fixtures.createStudy("Short Title");
		LowLevelTerm lowLevelTerm1 = new LowLevelTerm();
		lowLevelTerm1.setMeddraCode("meddraCode");
		lowLevelTerm1.setMeddraTerm("meddraTerm");
		MeddraVersion mv = new MeddraVersion();
		mv.setName("meddra v9");
		lowLevelTerm1.setMeddraVersion(mv);
		sampleStudy.addMeddraStudyDisease(Fixtures.createMeddraStudyDisease(sampleStudy, lowLevelTerm1));
		sampleStudy.addMeddraStudyDisease(Fixtures.createMeddraStudyDisease(sampleStudy, lowLevelTerm1));
		errors = domainObjectValidator.validate(sampleStudy);
		assertEquals(1, errors.size());
	}
	
	public void testUniqueMeddraStudyDiseases(){
		sampleStudy = Fixtures.createStudy("Short Title");
		LowLevelTerm lowLevelTerm1 = new LowLevelTerm();
		lowLevelTerm1.setMeddraCode("meddraCode");
		lowLevelTerm1.setMeddraTerm("meddraTerm");
		MeddraVersion mv = new MeddraVersion();
		mv.setName("meddra v9");
		lowLevelTerm1.setMeddraVersion(mv);
		
		LowLevelTerm lowLevelTerm2 = new LowLevelTerm();
		lowLevelTerm2.setMeddraCode("meddraCode-1");
		lowLevelTerm2.setMeddraTerm("meddraTerm-1");
		lowLevelTerm2.setMeddraVersion(mv);
		
		sampleStudy.addMeddraStudyDisease(Fixtures.createMeddraStudyDisease(sampleStudy, lowLevelTerm1));
		sampleStudy.addMeddraStudyDisease(Fixtures.createMeddraStudyDisease(sampleStudy, lowLevelTerm2));
		errors = domainObjectValidator.validate(sampleStudy);
		assertEquals(0, errors.size());
	}
	
	public void testDuplicateStudyrganizations(){
		sampleStudy = Fixtures.createStudy("short title");
		Organization organization = Fixtures.createOrganization("CTEP");
		Identifier orgIdentifier = Fixtures.createOrganizationAssignedIdentifier("ORG-1", organization);
		StudyOrganization ss1 = new StudySite();
		ss1.setOrganization(organization);
		ss1.setStudy(sampleStudy);
		StudyOrganization ss2 = new StudySite();
		ss2.setOrganization(organization);
		ss2.setStudy(sampleStudy);
		sampleStudy.addIdentifier(orgIdentifier);
		sampleStudy.addStudyOrganization(ss1);
		sampleStudy.addStudyOrganization(ss2);
		
		errors = domainObjectValidator.validate(sampleStudy);
		assertEquals(1, errors.size());
		
	}
	
	public void testUniqueStudyrganizations(){
		sampleStudy = Fixtures.createStudy("short title");
		Organization organization1 = Fixtures.createOrganization("CTEP");
		Organization organization2 = Fixtures.createOrganization("CALGB");
		Identifier orgIdentifier1 = Fixtures.createOrganizationAssignedIdentifier("ORG-1", organization1);
		Identifier orgIdentifier2 = Fixtures.createOrganizationAssignedIdentifier("ORG-2", organization2);
		StudyOrganization ss1 = new StudySite();
		ss1.setOrganization(organization1);
		ss1.setStudy(sampleStudy);
		StudyOrganization ss2 = new StudySite();
		ss2.setOrganization(organization2);
		ss2.setStudy(sampleStudy);
		sampleStudy.addIdentifier(orgIdentifier1);
		sampleStudy.addIdentifier(orgIdentifier2);
		sampleStudy.addStudyOrganization(ss1);
		sampleStudy.addStudyOrganization(ss2);
		
		errors = domainObjectValidator.validate(sampleStudy);
		assertEquals(0, errors.size());
		
	}
	
	public void testMultipleDuplicateCollections(){
		sampleStudy = Fixtures.createStudy("short title");
		//Duplicate Study Agents
		StudyAgent sa1 = Fixtures.createStudyAgent("agent1");
		StudyAgent sa2 = Fixtures.createStudyAgent("agent1");
		sampleStudy.addStudyAgent(sa1);
		sampleStudy.addStudyAgent(sa2);
		
		//Duplicate Identifiers
		Organization organization = Fixtures.createOrganization("CTEP");
		Identifier orgIdentifier1 = Fixtures.createOrganizationAssignedIdentifier("ORG-1", organization);
		Identifier orgIdentifier2 = Fixtures.createOrganizationAssignedIdentifier("ORG-1", organization);
		sampleStudy.addIdentifier(orgIdentifier1);
		sampleStudy.addIdentifier(orgIdentifier2);
		
		//Duplicate Treatment Assignments
		TreatmentAssignment treatmentAssignment1 = Fixtures.createTreatmentAssignment("Tac1");
		TreatmentAssignment treatmentAssignment2 = Fixtures.createTreatmentAssignment("Tac1");
		sampleStudy.addTreatmentAssignment(treatmentAssignment1);
		sampleStudy.addTreatmentAssignment(treatmentAssignment2);
		
		//Duplicate CtepStudyDiseases
		DiseaseTerm dTerm1 = new DiseaseTerm();
		dTerm1.setTerm("term");
		dTerm1.setMeddraCode("meddraCode");
		Fixtures.createCtepStudyDisease(sampleStudy, dTerm1);
		Fixtures.createCtepStudyDisease(sampleStudy, dTerm1);
		
		//Duplicate MeddraStudyDiseases
		LowLevelTerm lowLevelTerm1 = new LowLevelTerm();
		lowLevelTerm1.setMeddraCode("meddraCode");
		lowLevelTerm1.setMeddraTerm("meddraTerm");
		MeddraVersion mv = new MeddraVersion();
		mv.setName("meddra v9");
		lowLevelTerm1.setMeddraVersion(mv);
		sampleStudy.addMeddraStudyDisease(Fixtures.createMeddraStudyDisease(sampleStudy, lowLevelTerm1));
		sampleStudy.addMeddraStudyDisease(Fixtures.createMeddraStudyDisease(sampleStudy, lowLevelTerm1));
		
		errors = domainObjectValidator.validate(sampleStudy);
		
		assertEquals(5, errors.size());
	}
	
	public void testDuplicateStudies(){
		Study study1 = Fixtures.createStudy("Study 1");
		Study study2 = Fixtures.createStudy("Study 2");
		Study study3 = Fixtures.createStudy("Study 3");
		//Duplicate Identifiers
		Organization organization = Fixtures.createOrganization("CTEP");
		Identifier orgIdentifier1 = Fixtures.createOrganizationAssignedIdentifier("ORG-1", organization);
		Identifier orgIdentifier2 = Fixtures.createOrganizationAssignedIdentifier("ORG-1", organization);
		Identifier orgIdentifier3 = Fixtures.createOrganizationAssignedIdentifier("ORG-2", organization);
		study1.addIdentifier(orgIdentifier1);
		study2.addIdentifier(orgIdentifier2);
		study3.addIdentifier(orgIdentifier3);
		
		assertEquals(true, study1.equals(study2));
		assertEquals(false, study1.equals(study3));
		
	}
	
}
