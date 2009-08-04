package gov.nih.nci.cabig.caaers.web.study;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.DiseaseTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.domain.SiteInvestigator;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyInvestigator;
import gov.nih.nci.cabig.caaers.domain.StudyPersonnel;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.UserGroupType;

import java.util.ArrayList;
import java.util.List;

import org.easymock.classextension.EasyMock;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class StudyCommandTest extends AbstractTestCase {
	StudyCommand command;
	StudyDao studyDao;
	
	protected void setUp() throws Exception {
		super.setUp();
		studyDao = registerDaoMockFor(StudyDao.class);
		
		command = new StudyCommand(studyDao);
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
		assertEquals(1, command.getStudy().getCtepStudyDiseases().size());
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

		Organization o = Fixtures.createOrganization("test");
		
		StudyInvestigator si1 = Fixtures.createStudyInvestigator("test", o);
		StudyInvestigator si2 = Fixtures.createStudyInvestigator("test2", o);
		
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
		EasyMock.expect(studyDao.merge(command.getStudy())).andReturn(command.getStudy());
		EasyMock.expect(studyDao.initialize(command.getStudy())).andReturn(command.getStudy());
		replayMocks();
		command.openStudy();
		verifyMocks();
		assertEquals("Complete", command.getDataEntryStatus());
	}

}
