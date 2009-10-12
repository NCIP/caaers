package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph 
 *
 */
public class StudyTest extends TestCase {
	Study study;
	protected void setUp() throws Exception {
		super.setUp();
		study = new LocalStudy();
		
	}
	
	/**
	 * This method tests {@link Study#isExpectedAdverseEventTerm(gov.nih.nci.cabig.ctms.domain.DomainObject)}
	 */
	public void testIsExpectedAdverseEventTerm() {
		ExpectedAECtcTerm eTerm1 = Fixtures.createExpectedAECtcTerm(1, "abcd", "abcd");
		ExpectedAECtcTerm eTerm2 = Fixtures.createExpectedAECtcTerm(2, "efg", "efg");
		study.addExpectedAECtcTerm(eTerm1);
		study.addExpectedAECtcTerm(eTerm2);
		
		CtcTerm ctcTerm = Fixtures.createCtcTerm("efg", "efg");
		ctcTerm.setId(2);
		
		assertTrue(study.isExpectedAdverseEventTerm(ctcTerm));
		
		ctcTerm.setId(3);
		assertFalse(study.isExpectedAdverseEventTerm(ctcTerm));
	}
	/**
	 * This method tests {@link Study#isExpectedAdverseEventTerm(gov.nih.nci.cabig.ctms.domain.DomainObject)}
	 */
	public void testIsExpectedAdverseEventTermPassingMedDRATerm() {
		ExpectedAECtcTerm eTerm1 = Fixtures.createExpectedAECtcTerm(1, "abcd", "abcd");
		ExpectedAECtcTerm eTerm2 = Fixtures.createExpectedAECtcTerm(2, "efg", "efg");
		study.addExpectedAECtcTerm(eTerm1);
		study.addExpectedAECtcTerm(eTerm2);
		
		LowLevelTerm llt = new LowLevelTerm();
		
		assertFalse(study.isExpectedAdverseEventTerm(llt));
	}
	/**
	 * This method tests {@link Study#isExpectedAdverseEventTerm(gov.nih.nci.cabig.ctms.domain.DomainObject)}
	 */
	public void testIsExpectedAdverseEventTermPassingNullTerm() {
		ExpectedAECtcTerm eTerm1 = Fixtures.createExpectedAECtcTerm(1, "abcd", "abcd");
		ExpectedAECtcTerm eTerm2 = Fixtures.createExpectedAECtcTerm(2, "efg", "efg");
		study.addExpectedAECtcTerm(eTerm1);
		study.addExpectedAECtcTerm(eTerm2);
		
		assertFalse(study.isExpectedAdverseEventTerm(null));
	}
	
	/**
	 * This method tests {@link Study#getActiveStudyAgents()}
	 */
	public void testGetActiveStudyAgents(){
		StudyAgent sa1 = Fixtures.createStudyAgent("a1");
		StudyAgent sa2 = Fixtures.createStudyAgent("a2");
		
		sa2.retire();
		
		study.addStudyAgent(sa1);
		study.addStudyAgent(sa2);
		
		List<StudyAgent> agents = study.getActiveStudyAgents();
		
		assertEquals(1, agents.size());
		assertSame(sa1, agents.get(0));
		
		//retire sa1 and check again.
		sa1.retire();
		agents = study.getActiveStudyAgents();
		assertNotNull(agents);
		assertTrue(agents.isEmpty());
		
	}
	
	/**
	 * This method tests {@link Study#getActiveStudySites()}
	 */
	public void testGetActiveStudySites(){
		StudySite s1 = new StudySite();
		
		StudySite s2 = new StudySite();
		s2.retire();
		
		study.addStudySite(s1);
		study.addStudySite(s2);
		
		List<StudySite> sites = study.getActiveStudySites();
		assertEquals(1, sites.size());
		assertSame(s1, sites.get(0));
		
		s1.retire();
		sites = study.getActiveStudySites();
		assertNotNull(sites);
		assertTrue(sites.isEmpty());
	}
	
	/**
	 * This method tests {@link Study#getActiveStudyDiseases()}
	 */
	public void testGetActiveStudyDiseases(){
		assertNull(study.getActiveStudyDiseases());
	}
	
	/**
	 * This method tests {@link Study#getActiveStudyDiseases()}
	 */
	public void testGetActiveStudyDiseases_CtepStudyDisease(){
		CtepStudyDisease d1 = Fixtures.createCtepStudyDisease(study, null);
		CtepStudyDisease d2 = Fixtures.createCtepStudyDisease(study, null);
		d2.retire();
		
		List<CtepStudyDisease> diseases = new ArrayList<CtepStudyDisease>();
		diseases.add(d1);
		diseases.add(d2);
		
		study.setCtepStudyDiseases(diseases);
		study.setDiseaseTerminology(Fixtures.createDiseaseTerminology(study));
		
		assertEquals(1, study.getActiveStudyDiseases().size());
		assertSame(d1, study.getActiveStudyDiseases().get(0));
		
		d1.retire();
		
		assertTrue(study.getActiveStudyDiseases().isEmpty());
	}
	
	/**
	 * This method tests {@link Study#getActiveStudyDiseases()}
	 */
	public void testGetActiveStudyDiseases_MeddraStudyDisease(){
		MeddraStudyDisease d1 = Fixtures.createMeddraStudyDisease(study, null);
		MeddraStudyDisease d2 = Fixtures.createMeddraStudyDisease(study, null);
		d2.retire();
		
		List<MeddraStudyDisease> diseases = new ArrayList<MeddraStudyDisease>();
		diseases.add(d1);
		diseases.add(d2);
		
		study.setDiseaseTerminology(Fixtures.createDiseaseTerminology(study));
		study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.MEDDRA);
		study.setMeddraStudyDiseases(diseases);
		
		assertEquals(1, study.getActiveStudyDiseases().size());
		assertSame(d1, study.getActiveStudyDiseases().get(0));
		
		d1.retire();
		
		assertTrue(study.getActiveStudyDiseases().isEmpty());
	}
	
	/**
	 * This method tests {@link Study#getActiveStudyDiseases()}
	 */
	public void testGetActiveStudyDiseases_StudyCondition(){
		StudyCondition d1 = Fixtures.createStudyCondition(study, null);
		StudyCondition d2 = Fixtures.createStudyCondition(study, null);
		d2.retire();
		
		List<StudyCondition> diseases = new ArrayList<StudyCondition>();
		diseases.add(d1);
		diseases.add(d2);
		
		study.setStudyConditions(diseases);
		study.setDiseaseTerminology(Fixtures.createDiseaseTerminology(study));
		study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.OTHER);
		
		assertEquals(1, study.getActiveStudyDiseases().size());
		assertSame(d1, study.getActiveStudyDiseases().get(0));
		
		d1.retire();
		
		assertTrue(study.getActiveStudyDiseases().isEmpty());
	}
	
	public void testFindEmailAddressByRole(){
		Organization org = Fixtures.createOrganization("test");
		StudyOrganization studyOrg = Fixtures.createStudyFundingSponsor(org);
		ResearchStaff staff = Fixtures.createResearchStaff(org, Arrays.asList(UserGroupType.caaers_ae_cd), "test");
		StudyPersonnel sp = Fixtures.createStudyPersonnel(staff);
		studyOrg.addStudyPersonnel(sp);
		sp.setRoleCode(UserGroupType.caaers_ae_cd.getCsmName());
		study.addStudyOrganization(studyOrg);
		
		List<String> emails = study.findEmailAddressByRole("caaers_ae_cd");
		assertEquals(1, emails.size());
		assertEquals("test@def.com", emails.get(0));
	}
	
	public void testRemoveStudyTherapy(){
		study.getStudyTherapies().clear();
		
		study.addStudyTherapy(StudyTherapyType.BEHAVIORAL);
		study.addStudyTherapy(StudyTherapyType.DEVICE);
		assertEquals(2, study.getStudyTherapies().size());
		study.removeTherapiesOfType(StudyTherapyType.BEHAVIORAL);
		assertEquals(1, study.getStudyTherapies().size());
		study.removeTherapiesOfType(StudyTherapyType.BEHAVIORAL);
		assertEquals(1, study.getStudyTherapies().size());
	}
	
}
