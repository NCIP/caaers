package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.FundingSponsor;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.OrganizationAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

import java.util.ArrayList;
import java.util.List;

public class StudyOrganizationSynchronizerTest extends AbstractTestCase{
	Study dbStudy;
	Study xmlStudy;
	StudyOrganizationSynchronizer studyOrganizationSynchronizer;
	DomainObjectImportOutcome<Study> outcome;
	StudyFundingSponsor studyFundingSponsor1,studyFundingSponsor2,studyFundingSponsor1a,studyFundingSponsor2a;
	//StudyCoordinatingCenter studyCoordinatingCenter1,studyCoordinatingCenter2;
	//StudySite studySite1,studySite2,studySite1a,studySite2a;
	StudyInvestigator studyInvestigator1,studyInvestigator2,studyInvestigator1a,studyInvestigator2a,studyInvestigator3a;
	StudyPersonnel studyPersonnel1,studyPersonnel2,studyPersonnel1a,studyPersonnel2a,studyPersonnel3a;
	Organization organization1,organization2;
	OrganizationAssignedIdentifier orgIdentifier1,orgIdentifier2;
	ResearchStaff researchStaff1,researchStaff1a,researchStaff2,researchStaff2a,researchStaff3a;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		studyOrganizationSynchronizer = new StudyOrganizationSynchronizer();
		outcome = new DomainObjectImportOutcome<Study>();
		
		organization1 = Fixtures.createOrganization("SemanticBits1");
		organization2 = Fixtures.createOrganization("SemanticBits2");
		
		orgIdentifier1 = Fixtures.createOrganizationAssignedIdentifier("111", organization1);
		orgIdentifier1.setId(1);
		orgIdentifier2 = Fixtures.createOrganizationAssignedIdentifier("222", organization1);
		orgIdentifier2.setId(2);
		
		dbStudy = Fixtures.createStudy("abcd");
		xmlStudy = Fixtures.createStudy("abcd");
	}
	
	
	public void testStudyFundingSponsorAddSI(){
		
		FundingSponsor fundingSponsor1 = Fixtures.createFundingSponsor(organization1, orgIdentifier1);
		studyFundingSponsor1 = Fixtures.createStudyFundingSponsor(organization1);
		studyInvestigator1 = Fixtures.createStudyInvestigator("Monish", organization1);
		studyInvestigator1.setId(1);
		studyInvestigator2 = Fixtures.createStudyInvestigator("Sameer", organization1);
		studyInvestigator2.setId(2);
		studyFundingSponsor1.addStudyInvestigators(studyInvestigator1);
		studyFundingSponsor1.addStudyInvestigators(studyInvestigator2);
		fundingSponsor1.setStudyFundingSponsor(studyFundingSponsor1);
		dbStudy.setFundingSponsor(fundingSponsor1);
		
		
		FundingSponsor fundingSponsor1a = Fixtures.createFundingSponsor(organization1, orgIdentifier1);
		studyFundingSponsor1a = Fixtures.createStudyFundingSponsor(organization1);
		studyInvestigator1a = Fixtures.createStudyInvestigator("Monish", organization1);
		studyInvestigator2a = Fixtures.createStudyInvestigator("Sameer", organization1);
		studyInvestigator3a = Fixtures.createStudyInvestigator("Biju", organization1);
		studyFundingSponsor1a.addStudyInvestigators(studyInvestigator1a);
		studyFundingSponsor1a.addStudyInvestigators(studyInvestigator2a);
		studyFundingSponsor1a.addStudyInvestigators(studyInvestigator3a);
		fundingSponsor1a.setStudyFundingSponsor(studyFundingSponsor1a);
		xmlStudy.setFundingSponsor(fundingSponsor1a);
		
		studyOrganizationSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		assertEquals(3, dbStudy.getFundingSponsor().getStudyFundingSponsor().getStudyInvestigators().size());
		
	}
	
	public void testStudyFundingSponsorAddSP(){
		FundingSponsor fundingSponsor1 = Fixtures.createFundingSponsor(organization1, orgIdentifier1);
		List<UserGroupType> groupTypeList = new ArrayList<UserGroupType>();
		studyFundingSponsor1 = Fixtures.createStudyFundingSponsor(organization1);
		researchStaff1 = Fixtures.createResearchStaff(organization1, groupTypeList, "Monish");
		studyPersonnel1 = Fixtures.createStudyPersonnel(researchStaff1);
		studyPersonnel1.setId(1);
		researchStaff2 = Fixtures.createResearchStaff(organization1, groupTypeList, "Sameer");
		studyPersonnel2 = Fixtures.createStudyPersonnel(researchStaff2);
		studyPersonnel2.setId(2);
		studyFundingSponsor1.addStudyPersonnel(studyPersonnel1);
		studyFundingSponsor1.addStudyPersonnel(studyPersonnel2);
		fundingSponsor1.setStudyFundingSponsor(studyFundingSponsor1);
		dbStudy.setFundingSponsor(fundingSponsor1);
		
		FundingSponsor fundingSponsor1a = Fixtures.createFundingSponsor(organization1, orgIdentifier1);
		studyFundingSponsor1a = Fixtures.createStudyFundingSponsor(organization1);
		researchStaff1a = Fixtures.createResearchStaff(organization1, groupTypeList, "Monish");
		studyPersonnel1a = Fixtures.createStudyPersonnel(researchStaff1a);
		researchStaff2a = Fixtures.createResearchStaff(organization1, groupTypeList, "Sameer");
		studyPersonnel2a = Fixtures.createStudyPersonnel(researchStaff2a);
		researchStaff3a = Fixtures.createResearchStaff(organization1, groupTypeList, "Biju");
		researchStaff3a.setFirstName("Biju");
		researchStaff3a.setLastName("Joseph");
		researchStaff3a.setNciIdentifier("Nci 123");
		studyPersonnel3a = Fixtures.createStudyPersonnel(researchStaff3a);
		studyFundingSponsor1a.addStudyPersonnel(studyPersonnel1a);
		studyFundingSponsor1a.addStudyPersonnel(studyPersonnel2a);
		studyFundingSponsor1a.addStudyPersonnel(studyPersonnel3a);
		fundingSponsor1a.setStudyFundingSponsor(studyFundingSponsor1a);
		xmlStudy.setFundingSponsor(fundingSponsor1a);
		
		studyOrganizationSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		assertEquals(3,dbStudy.getFundingSponsor().getStudyFundingSponsor().getStudyPersonnels().size());
	}
	
}
