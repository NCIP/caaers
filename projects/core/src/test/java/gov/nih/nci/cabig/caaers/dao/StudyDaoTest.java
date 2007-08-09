package gov.nih.nci.cabig.caaers.dao;

import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.assertContains;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.IMPORT_STUDIES;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.STUDY_ABSTRACTION;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Sujith Vellat Thayyilthodi
 * @author Rhett Sutphin
 * @author Ram Chilukuri
 * @author Krikor Krumlian
 */
@CaaersUseCases( { CREATE_STUDY, STUDY_ABSTRACTION, IMPORT_STUDIES })
public class StudyDaoTest extends DaoTestCase<StudyDao> {
	private OrganizationDao sitedao = (OrganizationDao) getApplicationContext().getBean("organizationDao");

	private AgentDao agentDao = (AgentDao) getApplicationContext().getBean("agentDao");

	private InvestigationalNewDrugDao indDao = (InvestigationalNewDrugDao) getApplicationContext().getBean(
			"investigationalNewDrugDao");

	public void testGet() throws Exception {
		Study loaded = getDao().getById(-2);
		assertNotNull("Study not found", loaded);
		assertEquals("Short Title", loaded.getShortTitle());
	}

	public void testGetByGridId() throws Exception {
		Study study = getDao().getByGridId("gridStudy");
		assertNotNull("Study not found", study);
	}

	public void testSaveWithCtc() throws Exception {
		Integer savedId;
		{
			Study newStudy = new Study();
			newStudy.setShortTitle("Short Title Inserted");
			newStudy.setLongTitle("Long Title Inserted");
			newStudy.setTerminology(Fixtures.createCtcV3Terminology(newStudy));
			newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
			getDao().save(newStudy);
			assertNotNull("No ID for newly saved study", newStudy.getId());
			savedId = newStudy.getId();
		}

		interruptSession();

		{
			Study reloaded = getDao().getById(savedId);
			assertNotNull("Saved Study not found", reloaded);
			assertNotNull("Terminology is null", reloaded.getTerminology());
			assertNotNull("Ctc Version is null", reloaded.getTerminology().getCtcVersion());
			assertEquals("Term should be Ctc", Term.CTC, reloaded.getTerminology().getTerm());
		}
	}

	public void testSaveWithMedDRA() throws Exception {
		Integer savedId;
		{
			Study newStudy = new Study();
			newStudy.setShortTitle("Short Title Inserted");
			newStudy.setLongTitle("Long Title Inserted");
			newStudy.setTerminology(Fixtures.createMedDRATerminology(newStudy));
			newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
			getDao().save(newStudy);
			assertNotNull("No ID for newly saved study", newStudy.getId());
			savedId = newStudy.getId();
		}

		interruptSession();

		{
			Study reloaded = getDao().getById(savedId);
			assertNotNull("Saved Study not found", reloaded);
			assertNotNull("Terminology is null", reloaded.getTerminology());
			assertNull("Ctc Version should be null", reloaded.getTerminology().getCtcVersion());
			assertEquals("Term should be MedDRA", Term.MEDDRA, reloaded.getTerminology().getTerm());
		}
	}

	public void testGetBySubnameMatchesShortTitle() throws Exception {
		List<Study> actual = getDao().getBySubnames(new String[] { "orter" });
		assertEquals("Wrong number of matches", 1, actual.size());
		assertEquals("Wrong match", -3, (int) actual.get(0).getId());
	}

	public void testGetBySubnameMatchesLongTitle() throws Exception {
		List<Study> actual = getDao().getBySubnames(new String[] { "long" });
		assertEquals("Wrong number of matches", 1, actual.size());
		assertEquals("Wrong match", -2, (int) actual.get(0).getId());
	}

	public void testGetBySubnameMatchesIntersectionOfSubnames() throws Exception {
		List<Study> actual = getDao().getBySubnames(new String[] { "long", "title" });
		assertEquals("Wrong number of matches", 1, actual.size());
		assertEquals("Wrong match", -2, (int) actual.get(0).getId());
	}

	public void testGetBySubnameWithNullSubnamesReturnsNothing() throws Exception {
		List<Study> actual = getDao().getBySubnames(null);
		assertEquals(0, actual.size());
	}

	public void testGetBySubnameWithNoSubnamesReturnsNothing() throws Exception {
		List<Study> actual = getDao().getBySubnames(new String[] {});
		assertEquals(0, actual.size());
	}

	public void testSearchByExactExample() throws Exception {
		Study example = new Study();
		example.setDescription("Description");

		List<Study> actual = getDao().searchByExample(example, false);
		assertEquals("Wrong number of matches", 1, actual.size());
		assertEquals("Wrong match", -2, (int) actual.get(0).getId());
	}

	public void testSearchByWildcardExample() throws Exception {
		Study example = new Study();
		example.setShortTitle("orte");

		List<Study> actual = getDao().searchByExample(example, true);
		assertEquals("Wrong number of matches", 2, actual.size());
		Set<Integer> ids = new HashSet<Integer>();
		for (Study study : actual) {
			ids.add(study.getId());
		}
		assertTrue(ids.contains(-3));
		assertTrue(ids.contains(-4));
	}

	public void testGetByIdentifierByTypeAndValue() throws Exception {
		Identifier id = Identifier.createTemplate("local", "1138-42");
		Study match = getDao().getByIdentifier(id);
		assertNotNull("No matches found", match);
		assertEquals("Wrong study matched", -3, (int) match.getId());
	}

	/**
	 * Test for retrieving all study sites associated with this Study
	 * 
	 * @throws Exception
	 */
	public void testGetStudySites() throws Exception {
		Study study = getDao().getById(-2);
		List<StudySite> sites = study.getStudySites();
		assertEquals("Wrong number of study sites", 1, sites.size());
		List<Integer> ids = collectIds(sites);

		assertContains("Missing expected study site", ids, -1000);
	}

	/**
	 * Test for retrieving all study funding sponsors associated with this Study
	 * 
	 * @throws Exception
	 */
	public void testGetStudyFundingSponsors() throws Exception {
		Study study = getDao().getById(-2);
		List<StudyFundingSponsor> sponsors = study.getStudyFundingSponsors();
		assertEquals("Wrong number of study funding sponsors", 1, sponsors.size());
		System.out.println("Study funding sponsor is: " + sponsors.get(0).getOrganization().getName());
		List<Integer> ids = collectIds(sponsors);

		assertContains("Missing expected study funding sponsor", ids, -1001);
	}

	/**
	 * Test for retrieving all study coordinating centers associated with this Study
	 * 
	 * @throws Exception
	 */
	public void testGetStudyCoordinatingCenters() throws Exception {
		Study study = getDao().getById(-2);
		List<StudyCoordinatingCenter> centers = study.getStudyCoordinatingCenters();
		assertEquals("Wrong number of study coordinating centers", 1, centers.size());
		List<Integer> ids = collectIds(centers);

		assertContains("Missing expected study funding sponsor", ids, -1004);
	}

	public void testSaveNewStudyWithFundingSponsor() throws Exception {
		Integer savedId;
		{
			Organization sponsor = sitedao.getById(-1001);
			Organization organization = sitedao.getById(-1003);

			Study study = new Study();
			study.setShortTitle("ShortTitleText");
			study.setLongTitle("LongTitleText");
			study.setPhaseCode("PhaseCode");
			study.setStatus("Status");
			study.setTargetAccrualNumber(150);
			// study.setType("Type");
			study.setMultiInstitutionIndicator(true);
			study.setTerminology(Fixtures.createCtcV3Terminology(study));

			// Study Site
			StudySite studySite = new StudySite();
			studySite.setOrganization(organization);
			studySite.setRoleCode("role");
			studySite.setStatusCode("active");

			study.addStudySite(studySite);

			// Study funding sponsor
			StudyFundingSponsor fundingSponsor = new StudyFundingSponsor();
			fundingSponsor.setOrganization(sponsor);
			study.addStudyOrganization(fundingSponsor);

			getDao().save(study);

			savedId = study.getId();
			assertNotNull("The saved study didn't get an id", savedId);
		}

		interruptSession();
		{
			Study loaded = getDao().getById(savedId);
			assertNotNull("Could not reload study with id " + savedId, loaded);
			// assertNotNull("GridId not updated", loaded.getGridId());
			assertEquals("Wrong name", "ShortTitleText", loaded.getShortTitle());
			assertEquals("Wrong study funding sponsor", "National Cancer Institute", loaded.getStudyFundingSponsors()
					.get(0).getOrganization().getName());
		}
	}

	public void testSaveNewStudyWithCoordinatingCenter() throws Exception {
		Integer savedId;
		{
			Organization sponsor = sitedao.getById(-1001);
			Organization organization = sitedao.getById(-1003);
			Organization center = sitedao.getById(-1002);

			Study study = new Study();
			study.setShortTitle("ShortTitleText");
			study.setLongTitle("LongTitleText");
			study.setPhaseCode("PhaseCode");
			study.setStatus("Status");
			study.setTargetAccrualNumber(150);
			// study.setType("Type");
			study.setMultiInstitutionIndicator(true);
			study.setTerminology(Fixtures.createCtcV3Terminology(study));
			// study.setCtcVersion(ctc);

			// Study Site
			StudySite studySite = new StudySite();
			studySite.setOrganization(organization);
			studySite.setRoleCode("role");
			studySite.setStatusCode("active");

			study.addStudySite(studySite);

			// Study funding sponsor
			StudyFundingSponsor fundingSponsor = new StudyFundingSponsor();
			fundingSponsor.setOrganization(sponsor);
			study.addStudyOrganization(fundingSponsor);

			// Study coordinating center
			StudyCoordinatingCenter coCenter = new StudyCoordinatingCenter();
			coCenter.setOrganization(center);
			study.addStudyOrganization(coCenter);

			getDao().save(study);

			savedId = study.getId();
			assertNotNull("The saved study didn't get an id", savedId);
		}

		interruptSession();
		{
			Study loaded = getDao().getById(savedId);
			assertNotNull("Could not reload study with id " + savedId, loaded);
			// assertNotNull("GridId not updated", loaded.getGridId());
			assertEquals("Wrong name", "ShortTitleText", loaded.getShortTitle());
			assertEquals("Wrong study funding sponsor", "National Cancer Institute", loaded.getStudyFundingSponsors()
					.get(0).getOrganization().getName());
			assertEquals("Wrong study coordinating center", "CALGB", loaded.getStudyCoordinatingCenters().get(0)
					.getOrganization().getName());
		}
	}

	private List<Integer> collectIds(List<? extends DomainObject> actual) {
		List<Integer> ids = new ArrayList<Integer>(actual.size());
		for (DomainObject object : actual) {
			ids.add(object.getId());
		}
		return ids;
	}

	public void testSearchStudyByStudyShortTitle() throws Exception {
		List<Study> results;
		Map<String, String> m = new HashMap<String, String>();
		m.put("studyShortTitle", "Short Title");
		results = getDao().searchStudy(m);
		assertEquals("Wrong number of results", 1, results.size());
		assertEquals("Wrong match", "Short Title", results.get(0).getShortTitle());
	}

	public void testSearchStudyByStudyIdentifier() throws Exception {
		List<Study> results;
		Map<String, String> m = new HashMap<String, String>();
		m.put("studyIdentifier", "1138-43");
		results = getDao().searchStudy(m);
		assertEquals("Wrong number of results", 1, results.size());
		assertEquals("Wrong match", "Short Title", results.get(0).getShortTitle());
	}

	public void testSearchStudyByParticipantFirstName() throws Exception {
		List<Study> results;
		Map<String, String> m = new HashMap<String, String>();
		m.put("participantFirstName", "Dilbert");
		results = getDao().searchStudy(m);
		assertEquals("Wrong number of results", 1, results.size());
		assertEquals("Wrong match", "Short Title", results.get(0).getShortTitle());
	}

	public void testSearchStudyByParticipantLastName() throws Exception {
		List<Study> results;
		Map<String, String> m = new HashMap<String, String>();
		m.put("participantLastName", "Scott");
		results = getDao().searchStudy(m);
		assertEquals("Wrong number of results", 1, results.size());
		assertEquals("Wrong match", "Short Title", results.get(0).getShortTitle());
	}

	public void testSearchStudyByParticipantIdentifier() throws Exception {
		List<Study> results;
		Map<String, String> m = new HashMap<String, String>();
		m.put("participantIdentifier", "13js77");
		results = getDao().searchStudy(m);
		assertEquals("Wrong number of results", 1, results.size());
		assertEquals("Wrong match", "Short Title", results.get(0).getShortTitle());
	}

	public void testSearchStudyByMultipleCriterias() throws Exception {
		List<Study> results;
		Map<String, String> m = new HashMap<String, String>();
		m.put("participantFirstName", "Dilbert");
		m.put("participantGender", "Female");
		m.put("participantDateOfBirth", "01/02/2006");
		results = getDao().searchStudy(m);
		assertEquals("Wrong number of results", 1, results.size());
		assertEquals("Wrong match", "Short Title", results.get(0).getShortTitle());
	}

	public void testSaveNewStudyWithINDAgent() {
		int studyId = 0;
		{

			Study newStudy = new Study();
			newStudy.setShortTitle("Short Title Inserted");
			newStudy.setLongTitle("Long Title Inserted");
			newStudy.setTerminology(Fixtures.createCtcV3Terminology(newStudy));
			newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
			// study agent
			Agent agent = agentDao.getById(-990);
			assertNotNull(agent);
			StudyAgent sa = new StudyAgent();
			sa.setAgent(agent);
			newStudy.addStudyAgent(sa);
			sa.setStudy(newStudy);
			agent = agentDao.getById(-991);
			sa = new StudyAgent();
			sa.setAgent(agent);
			InvestigationalNewDrug ind1 = indDao.getById(-881);
			InvestigationalNewDrug ind2 = indDao.getById(-882);
			StudyAgentINDAssociation ass1 = new StudyAgentINDAssociation();
			ass1.setStudyAgent(sa);
			sa.addStudyAgentINDAssociation(ass1);

			ass1.setInvestigationalNewDrug(ind1);
			StudyAgentINDAssociation ass2 = new StudyAgentINDAssociation();
			ass2.setStudyAgent(sa);
			ass1.setInvestigationalNewDrug(ind1);
			ass2.setInvestigationalNewDrug(ind2);
			sa.addStudyAgentINDAssociation(ass2);

			newStudy.addStudyAgent(sa);

			getDao().save(newStudy);
			assertNotNull("No ID for newly saved study", newStudy.getId());
			studyId = newStudy.getId();
		}
		interruptSession();
		{
			Study loaded = getDao().getById(studyId);
			assertNotNull("Could not reload study with id " + studyId, loaded);
			// assertNotNull("GridId not updated", loaded.getGridId());
			assertEquals("Wrong name", "Short Title Inserted", loaded.getShortTitle());
			assertNotNull("Agents should not be null", loaded.getStudyAgents());
			assertEquals("Study agents size", 2, loaded.getStudyAgents().size());
			assertEquals("Agent ID is wrong", -990, loaded.getStudyAgents().get(0).getAgent().getId().intValue());
			List<StudyAgentINDAssociation> aList = loaded.getStudyAgents().get(1).getStudyAgentINDAssociations();
			assertEquals("IND size", 2, aList.size());
			assertEquals("IND # wrong", -881, aList.get(0).getInvestigationalNewDrug().getIndNumber().intValue());
		}
	}
}
