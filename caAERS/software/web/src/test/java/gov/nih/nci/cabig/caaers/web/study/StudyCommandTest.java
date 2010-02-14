package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.InvestigationalNewDrugDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.StudyTherapyType;
import gov.nih.nci.cabig.caaers.domain.SystemAssignedIdentifier;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;
import gov.nih.nci.cabig.caaers.domain.repository.StudyRepository;
import gov.nih.nci.cabig.caaers.utils.DateUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.easymock.classextension.EasyMock;

/**
 * 
 * @author Biju Joseph
 *
 */
public class StudyCommandTest extends AbstractTestCase {
	StudyCommand command;
	StudyDao studyDao;
	StudyRepository studyRepository;
	InvestigationalNewDrugDao investigationalNewDrugDao;
	protected void setUp() throws Exception {
		super.setUp();
		studyDao = registerDaoMockFor(StudyDao.class);
		studyRepository = registerMockFor(StudyRepository.class);
		investigationalNewDrugDao = registerDaoMockFor(InvestigationalNewDrugDao.class);
		command = new StudyCommand(studyDao, investigationalNewDrugDao);
		command.setStudyRepository(studyRepository);
		
		Study s = Fixtures.createStudy("test");
		s.setDataEntryStatus(true);
		command.setStudy(s);
		
		List<StudyAgent> studyagents = new ArrayList<StudyAgent>();
		
		StudyAgent sa1 = Fixtures.createStudyAgent("test1");
		StudyAgent sa2 = Fixtures.createStudyAgent("test2");
		
		studyagents.add(sa1);
		studyagents.add(sa2);
		s.setStudyAgents(studyagents);
		
		List<TreatmentAssignment> assignments = new ArrayList<TreatmentAssignment>();
		TreatmentAssignment ta1 = Fixtures.createTreatmentAssignment("abc1");
		TreatmentAssignment ta2 = Fixtures.createTreatmentAssignment("abc2");
		
		assignments.add(ta1);
		assignments.add(ta2);
		s.setTreatmentAssignments(assignments);
		
		
		StudySite ss1 = new StudySite();
		StudySite ss2 = new StudySite();
		s.addStudySite(ss1);
		s.addStudySite(ss2);
	}

	public void testIsDataEntryComplete() {
		assertTrue(command.isDataEntryComplete());
	}

	public void testDeleteStudyAgentAtIndex() {
		assertEquals(2, command.getStudy().getStudyAgents().size());
		assertFalse(command.getStudy().getStudyAgents().get(1).isRetired());
		command.getStudy().getStudyAgents().get(1).setId(55);
		command.deleteStudyAgentAtIndex(1);
		assertEquals(2, command.getStudy().getStudyAgents().size());
		assertTrue(command.getStudy().getStudyAgents().get(1).isRetired());
	}
	
	
	
	public void testDeleteTreatmentAssignmentsAtIndex() {
		assertEquals(2, command.getStudy().getTreatmentAssignments().size());
		assertFalse(command.getStudy().getTreatmentAssignments().get(1).isRetired());
		command.getStudy().getTreatmentAssignments().get(1).setId(44);
		command.deleteTreatmentAssignmentAtIndex(1);
		assertEquals(2, command.getStudy().getTreatmentAssignments().size());
		assertTrue(command.getStudy().getTreatmentAssignments().get(1).isRetired());
	}
	
	
	public void testDeleteStudySiteAtIndex() {
		assertEquals(2, command.getStudy().getStudySites().size());
		assertFalse(command.getStudy().getStudySites().get(1).isRetired());
		command.getStudy().getStudySites().get(1).setId(55);
		command.deleteStudySiteAtIndex(1);
		assertEquals(2, command.getStudy().getStudySites().size());
		assertTrue(command.getStudy().getStudySites().get(1).isRetired());
	}
	
	public void testDeleteCtepStudyDiseaseAtIndex(){
	    Fixtures.createDiseaseTerminology(command.getStudy());
		Fixtures.createCtepStudyDisease(command.getStudy(), new DiseaseTerm());
		Fixtures.createCtepStudyDisease(command.getStudy(), new DiseaseTerm());
		assertEquals(2, command.getStudy().getCtepStudyDiseases().size());
		assertFalse(command.getStudy().getCtepStudyDiseases().get(1).isRetired());
		command.deleteCtepStudyDiseaseAtIndex(1);
		assertEquals(2, command.getStudy().getCtepStudyDiseases().size());
		assertFalse(command.getStudy().getCtepStudyDiseases().get(0).isRetired());
	}

	public void testDeleteMeddraStudyDiseaseAtIndex(){
	    Fixtures.createDiseaseTerminology(command.getStudy());
	    command.getStudy().getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.MEDDRA);
		command.getStudy().addMeddraStudyDisease(Fixtures.createMeddraStudyDisease(command.getStudy(), null));
		command.getStudy().addMeddraStudyDisease(Fixtures.createMeddraStudyDisease(command.getStudy(), null));
		assertEquals(2, command.getStudy().getMeddraStudyDiseases().size());
		assertFalse(command.getStudy().getMeddraStudyDiseases().get(1).isRetired());
		command.getStudy().getMeddraStudyDiseases().get(1).setId(2);
		command.getStudy().getMeddraStudyDiseases().get(0).setId(1);
		command.deleteMeddraStudyDiseaseAtIndex(1);
		assertEquals(2, command.getStudy().getMeddraStudyDiseases().size());
		assertTrue(command.getStudy().getMeddraStudyDiseases().get(1).isRetired());
	}
	
	
	public void testDeleteStudyConditionAtIndex(){
	    Fixtures.createDiseaseTerminology(command.getStudy());
	    command.getStudy().getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.OTHER);
	    Fixtures.createStudyCondition(command.getStudy(), null);
	    Fixtures.createStudyCondition(command.getStudy(), null);
		assertEquals(2, command.getStudy().getStudyConditions().size());
		assertFalse(command.getStudy().getStudyConditions().get(1).isRetired());
		command.getStudy().getStudyConditions().get(1).setId(1);
		command.getStudy().getStudyConditions().get(0).setId(2);
		
		command.deleteStudyConditionAtIndex(1);
		assertEquals(2, command.getStudy().getStudyConditions().size());
		assertTrue(command.getStudy().getStudyConditions().get(1).isRetired());
	}
	
	public void testStudyInvestigatorAtIndex(){

		Organization o = Fixtures.createOrganization("Organization Name");
		
		StudyInvestigator si1 = Fixtures.createStudyInvestigator("StudyInvestigator A", o);
		StudyInvestigator si2 = Fixtures.createStudyInvestigator("Study Investigator B", o);
		si1.setStartDate(DateUtils.yesterday());
		si2.setStartDate(DateUtils.yesterday());
		command.getStudy().getStudySites().get(0).addStudyInvestigators(si1);
		command.getStudy().getStudySites().get(0).addStudyInvestigators(si2);
		
		assertEquals(2, command.getStudy().getStudySites().size());
		assertEquals(2, command.getStudy().getStudyOrganizations().get(0).getStudyInvestigators().size());
		
		command.getStudy().getStudyOrganizations().get(0).getStudyInvestigators().get(0).setId(1);
		command.getStudy().getStudyOrganizations().get(0).getStudyInvestigators().get(1).setId(2);

		command.deleteSiteInvestigatorAtIndex(0, 1);
		
		assertEquals(2, command.getStudy().getStudyOrganizations().get(0).getStudyInvestigators().size());
		assertFalse(command.getStudy().getStudyOrganizations().get(0).getStudyInvestigators().get(0).isRetired());
		assertTrue(command.getStudy().getStudyOrganizations().get(0).getStudyInvestigators().get(1).isRetired());
		
		assertEquals(1, command.getStudy().getStudySites().get(0).getActiveStudyInvestigators().size());
	}
	

	public void testStudyPersonAtIndex(){
		
		Organization o = Fixtures.createOrganization("test");
		
		List<UserGroupType> groups = new ArrayList<UserGroupType>();
		groups.add(UserGroupType.caaers_admin);
		
		ResearchStaff s1 = Fixtures.createResearchStaff(o, groups, "test1");
		ResearchStaff s2 = Fixtures.createResearchStaff(o, groups, "test2");
		
		StudyPersonnel sp1 = Fixtures.createStudyPersonnel(s1);
		StudyPersonnel sp2 = Fixtures.createStudyPersonnel(s2);
		
		command.getStudy().getStudySites().get(0).addStudyPersonnel(sp1);
		command.getStudy().getStudySites().get(0).addStudyPersonnel(sp2);
		
		assertEquals(2, command.getStudy().getStudySites().size());
		assertEquals(2, command.getStudy().getStudyOrganizations().get(0).getStudyPersonnels().size());
		
		command.getStudy().getStudyOrganizations().get(0).getStudyPersonnels().get(0).setId(1);
		command.getStudy().getStudyOrganizations().get(0).getStudyPersonnels().get(1).setId(2);
		
		command.deleteStudyPersonAtIndex(0, 1);
		assertEquals(2, command.getStudy().getStudyOrganizations().get(0).getStudyPersonnels().size());
		assertFalse(command.getStudy().getStudyOrganizations().get(0).getStudyPersonnels().get(0).isRetired());
		assertTrue(command.getStudy().getStudyOrganizations().get(0).getStudyPersonnels().get(1).isRetired());
		
		assertEquals(1, command.getStudy().getStudySites().get(0).getActiveStudyPersonnel().size());
	}
	
	public void testOpenStudy(){
		command.getStudy().setDataEntryStatus(false);
		assertEquals("Inprogress", command.getDataEntryStatus());
		EasyMock.expect(studyRepository.merge(command.getStudy())).andReturn(command.getStudy());
		EasyMock.expect(studyDao.initialize(command.getStudy())).andReturn(command.getStudy());
		replayMocks();
		command.openStudy();
		verifyMocks();
		assertEquals("Complete", command.getDataEntryStatus());
	}
	
	public void testCheckForDuplicateStudyByIdentifier(){
		SystemAssignedIdentifier id = Fixtures.createSystemAssignedIdentifier("test");
		EasyMock.expect(studyDao.find((StudyQuery) EasyMock.anyObject())).andReturn(null);
		replayMocks();
		assertNull(command.checkForDuplicateStudyByIdentifier(id));
		verifyMocks();
		
	}
	
	public void testCheckForDuplicateStudyByIdentifier_AnotherStudyReturned(){
		SystemAssignedIdentifier id = Fixtures.createSystemAssignedIdentifier("test");
		
		Study s2 = Fixtures.createStudy("test");
		s2.setId(56);
		
		EasyMock.expect(studyDao.find((StudyQuery) EasyMock.anyObject())).andReturn(Arrays.asList(s2));
		replayMocks();
		assertSame(s2, command.checkForDuplicateStudyByIdentifier(id));
		verifyMocks();
		
	}
	
	public void testUpdateStudyTherapies(){
		//empty case
		assertTrue(command.getStudy().getStudyTherapies().isEmpty());
		command.updateStudyTherapies();
		assertTrue(command.getStudy().getStudyTherapies().isEmpty());
		
		//having study therapy case
		command.getStudy().addStudyTherapy(StudyTherapyType.DEVICE);
		command.setBehavioralTherapyType(true);
		command.updateStudyTherapies();
		assertTrue(command.getBehavioralTherapyType());
		command.updateStudyTherapies();
		assertFalse(command.getStudy().hasTherapyOfType(StudyTherapyType.DEVICE));
		assertTrue(command.getStudy().hasTherapyOfType(StudyTherapyType.BEHAVIORAL));
	}

    //new study save.
    public void testSave(){

        Study s = Fixtures.createStudy("test");

        EasyMock.expect(studyRepository.merge(command.getStudy())).andReturn(s);
        EasyMock.expect(studyDao.initialize(s)).andReturn(s);
        replayMocks();

        command.getStudy().setAdeersPDFType(true);
        command.getStudy().setCaaersXMLType(false);

        command.save();

        assertSame(s, command.getStudy());
        assertTrue(command.getStudy().getAdeersPDFType());
        assertFalse(command.getStudy().getCaaersXMLType());

        verifyMocks();

    }


    //new study edit.
    public void testSave_EditingStudy(){

        Study s = Fixtures.createStudy("test");

        command.getStudy().setId(5);
        studyRepository.synchronizeStudyPersonnel(command.getStudy());
        EasyMock.expect(studyRepository.merge(command.getStudy())).andReturn(s);
        EasyMock.expect(studyDao.initialize(s)).andReturn(s);
        replayMocks();

        command.getStudy().setAdeersPDFType(true);
        command.getStudy().setCaaersXMLType(false);

        command.save();

        assertSame(s, command.getStudy());
        assertTrue(command.getStudy().getAdeersPDFType());
        assertFalse(command.getStudy().getCaaersXMLType());

        verifyMocks();

    }

}
