package gov.nih.nci.cabig.caaers.dao;

import static edu.nwu.bioinformatics.commons.testing.CoreTestCase.assertContains;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.CREATE_STUDY;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.IMPORT_STUDIES;
import static gov.nih.nci.cabig.caaers.CaaersUseCase.STUDY_ABSTRACTION;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.query.StudyQuery;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.DiseaseCodeTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Identifier;
import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.LoadStatus;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.domain.StudyCoordinatingCenter;
import gov.nih.nci.cabig.caaers.domain.StudyFundingSponsor;
import gov.nih.nci.cabig.caaers.domain.StudySite;
import gov.nih.nci.cabig.caaers.domain.Term;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.ctms.domain.DomainObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.StatementCallback;

/**
 * @author Sujith Vellat Thayyilthodi
 * @author Rhett Sutphin
 * @author Ram Chilukuri
 * @author Krikor Krumlian
 * @author <a href="mailto:biju.joseph@semanticbits.com">Biju Joseph</a>
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
		Study study = getDao().getByGridId("f2321");
		assertNotNull("Study not found", study);
	}
	
	public void testSaveWithCtc() throws Exception {
		Integer savedId;
		{
			Study newStudy = new Study();
			newStudy.setShortTitle("Short Title Inserted");
			newStudy.setLongTitle("Long Title Inserted");
			newStudy.setAeTerminology(Fixtures.createCtcV3Terminology(newStudy));
			newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
			newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
			newStudy.setAdeersReporting(Boolean.TRUE);
			getDao().save(newStudy);
			assertNotNull("No ID for newly saved study", newStudy.getId());
			savedId = newStudy.getId();
		}

		interruptSession();

		{
			Study reloaded = getDao().getById(savedId);
			assertNotNull("Saved Study not found", reloaded);
			assertNotNull("AeTerminology is null", reloaded.getAeTerminology());
			assertNotNull("Ctc Version is null", reloaded.getAeTerminology().getCtcVersion());
			assertEquals("Term should be Ctc", Term.CTC, reloaded.getAeTerminology().getTerm());
		}
	}

	public void testSaveWithMedDRA() throws Exception {
		Integer savedId;
		{
			Study newStudy = new Study();
			newStudy.setShortTitle("Short Title Inserted");
			newStudy.setLongTitle("Long Title Inserted");
			newStudy.setAeTerminology(Fixtures.createMedDRATerminology(newStudy));
			newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
			newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
			newStudy.setAdeersReporting(Boolean.TRUE);
			getDao().save(newStudy);
			assertNotNull("No ID for newly saved study", newStudy.getId());
			savedId = newStudy.getId();
		}

		interruptSession();

		{
			Study reloaded = getDao().getById(savedId);
			assertNotNull("Saved Study not found", reloaded);
			assertNotNull("AeTerminology is null", reloaded.getAeTerminology());
			assertNull("Ctc Version should be null", reloaded.getAeTerminology().getCtcVersion());
			assertEquals("Term should be MedDRA", Term.MEDDRA, reloaded.getAeTerminology().getTerm());
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
		assertEquals("Wrong number of matches", 1, actual.size());
		Set<Integer> ids = new HashSet<Integer>();
		for (Study study : actual) {
			ids.add(study.getId());
		}
		assertTrue(ids.contains(-3));
		
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
		{
			Study study = getDao().getById(-2);
			List<StudyCoordinatingCenter> centers = study.getStudyCoordinatingCenters();
			assertEquals("Wrong number of study coordinating centers", 1, centers.size());
			List<Integer> ids = collectIds(centers);

			assertContains("Missing expected study funding sponsor", ids, -1004);

			StudyCoordinatingCenter sc = centers.get(0);
			study.removeStudyOrganization(sc);
			getDao().save(study);
		}
		interruptSession();
		{
			Study study = getDao().getById(-2);
			List<StudyCoordinatingCenter> centers = study.getStudyCoordinatingCenters();
			int size = 0;
			if(centers != null) size = centers.size();
			assertEquals("Wrong number of study co-ordinating centers", 0, size);
		}

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
			study.setAeTerminology(Fixtures.createCtcV3Terminology(study));
			study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
			study.setAdeersReporting(Boolean.TRUE);

			// Study Site
			StudySite studySite = new StudySite();
			studySite.setOrganization(organization);
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
			study.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
			// study.setType("Type");
			study.setMultiInstitutionIndicator(true);
			study.setAeTerminology(Fixtures.createCtcV3Terminology(study));
			study.setAdeersReporting(Boolean.TRUE);
			// study.setCtcVersion(ctc);

			// Study Site
			StudySite studySite = new StudySite();
			studySite.setOrganization(organization);
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

	public void testMatchStudyByParticipant() throws Exception {
		List<Study> results;
		Integer participantId = -100 ;
		results = getDao().matchStudyByParticipant(participantId,"or", null);
		assertEquals("Wrong number of results", 1, results.size());
		assertEquals("Wrong match", "Short Title", results.get(0).getShortTitle());
	}

	public void testMatchStudyByParticipantByIdentifier() throws Exception {
		List<Study> results;
		Integer participantId = -100 ;
		// Full Identifier Value
		results = getDao().matchStudyByParticipant(participantId,"1138-43", null);
		assertEquals("Wrong number of results", 1, results.size());
		assertEquals("Wrong match", "Short Title", results.get(0).getShortTitle());

		// Partial  Identifier Value
		results = getDao().matchStudyByParticipant(participantId,"-43", null);
		assertEquals("Wrong number of results", 1, results.size());
		assertEquals("Wrong match", "Short Title", results.get(0).getShortTitle());

		// Partial  Identifier type
		results = getDao().matchStudyByParticipant(participantId,"lo", null);
		assertEquals("Wrong number of results", 1, results.size());
		assertEquals("Wrong match", "Short Title", results.get(0).getShortTitle());

		// Full  Identifier type
		results = getDao().matchStudyByParticipant(participantId,"local", null);
		assertEquals("Wrong number of results", 1, results.size());
		assertEquals("Wrong match", "Short Title", results.get(0).getShortTitle());
	}

	public void testGetBySubnamesJoinOnIdentifier() throws Exception {
		List<Study> results;

		// Full Identifier Value
		String[] str = {"1138-43"};
		results = getDao().getBySubnamesJoinOnIdentifier(str, null);
		assertEquals("Wrong number of results", 1, results.size());
		assertEquals("Wrong match", "Short Title", results.get(0).getShortTitle());

		// Partial  Identifier Value
		String[] str1 = {"-43"};
		results = getDao().getBySubnamesJoinOnIdentifier(str1, null);
		assertEquals("Wrong number of results", 1, results.size());
		assertEquals("Wrong match", "Short Title", results.get(0).getShortTitle());
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

	public void testSearchStudyByMultipleCriteria() throws Exception {
		List<Study> results;
		Map<String, String> m = new HashMap<String, String>();
		m.put("participantFirstName", "Dilbert");
		m.put("participantGender", "Female");
		m.put("participantDateOfBirth", "01/02/2006");
		results = getDao().searchStudy(m);
		assertEquals("Wrong number of results", 1, results.size());
		assertEquals("Wrong match", "Short Title", results.get(0).getShortTitle());
	}

	public void testGetByShortTitle() throws Exception {
		Study s =  getDao().getByShortTitle("Short Title");
		assertNotNull("Study should not be null", s);

	}
	public void testSaveNewStudyWithINDAgent() {
		int studyId = 0;
		{

			Study newStudy = new Study();
			newStudy.setShortTitle("Short Title Inserted");
			newStudy.setLongTitle("Long Title Inserted");
			newStudy.setAeTerminology(Fixtures.createCtcV3Terminology(newStudy));
			newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
			newStudy.setAdeersReporting(Boolean.TRUE);
			// study agent
			Agent agent = agentDao.getById(-990);
			assertNotNull(agent);
			StudyAgent sa = new StudyAgent();
			sa.setAgent(agent);
			newStudy.addStudyAgent(sa);
			newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
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
		}
	}

	

	

	public void testSaveUpdateStudyWithTreatmentAssignment(){
		int studyId = 0;
		Date d = new Date();
		{
			Study newStudy = new Study();
			newStudy.setShortTitle("Short Title Inserted");
			newStudy.setLongTitle("Long Title Inserted");
			newStudy.setAeTerminology(Fixtures.createCtcV3Terminology(newStudy));
			newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
			newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
			newStudy.setAdeersReporting(Boolean.TRUE);

			TreatmentAssignment ta = new TreatmentAssignment();
			ta.setCode("111");
			ta.setComments("no comments");
			ta.setDescription("sample description");
			ta.setDoseLevelOrder(33);
			ta.setStudy(newStudy);
			newStudy.addTreatmentAssignment(ta);
			

			getDao().save(newStudy);
			assertNotNull("No ID for newly saved study", newStudy.getId());
			assertNotNull("Treatment is not saved", newStudy.getTreatmentAssignments().get(0).getId());
			studyId = newStudy.getId();
		}
		interruptSession();
		{
			Study loaded = getDao().getById(studyId);
			assertNotNull("Could not reload study with id " + studyId, loaded);
			// assertNotNull("GridId not updated", loaded.getGridId());
			assertEquals("Wrong name", "Short Title Inserted", loaded.getShortTitle());
			assertEquals("Treatment Assigments are not loaded properly", 1, loaded.getTreatmentAssignments().size());
			TreatmentAssignment ta = loaded.getTreatmentAssignments().get(0);
			assertEquals("Invalid treatment code", "111", ta.getCode());
			loaded.getTreatmentAssignments().remove(ta);
			getDao().save(loaded);
		}
		interruptSession();
		{
			Study loaded = getDao().getById(studyId);
			assertNotNull("Could not reload study with id " + studyId, loaded);
			assertEquals("Wrong name", "Short Title Inserted", loaded.getShortTitle());
			assertEquals("Treatment Assigments are not removed properly", 0, loaded.getTreatmentAssignments().size());
		}

	}
	
	public void testSearchWithStudyQuery(){
		 StudyQuery query = new StudyQuery();
		 query.filterByIdentifierValueExactMatch("1138-42");
		 List<Study> studies = getDao().find(query);
		 for(Study study : studies){
			 System.out.println("Short title :" + study.getShortTitle());
		 }
		 assertNotNull("There should be study corresponding to identifier 1138-42" );
		 assertEquals("There should be exactly one study with identifier 1138-42" , 1, studies.size());
	}
	
	public void testSearchWithStudyQueryHavingIdentifierTypeAndValue(){
		 StudyQuery query = new StudyQuery();
		 query.filterByIdentifierValue("1138-42");
		 query.filterByIdentifierType("local");
		 List<Study> studies = getDao().find(query);
		 assertNotNull("There should be study corresponding to identifier 1138-421" ,studies.get(0));
		 assertEquals("There should be two identifiers for the study with identifier 1138-421" , 2, studies.size());
	}
	
	public void testUnableToLoadInprogressStudy() throws Exception{
			Study study = getDao().getById(-4);
			assertNull("Study should be null", study);
	}
	
	
	

	public void testSaveStudyInInprogressStatus() throws Exception {
		Integer savedId;
		
			Study newStudy = new Study();
			newStudy.setShortTitle("Short Title Inserted");
			newStudy.setLongTitle("Long Title Inserted");
			newStudy.setAeTerminology(Fixtures.createCtcV3Terminology(newStudy));
			newStudy.getDiseaseTerminology().setDiseaseCodeTerm(DiseaseCodeTerm.CTEP);
			newStudy.setMultiInstitutionIndicator(Boolean.FALSE);
			newStudy.setLoadStatus(LoadStatus.INPROGRESS.getCode());
			newStudy.setAdeersReporting(Boolean.TRUE);
			getDao().save(newStudy);
			assertNotNull("No ID for newly saved study", newStudy.getId());
			savedId = newStudy.getId();
		
		System.out.println(savedId);
		

		interruptSession();
		System.out.println("loadstatus :" + newStudy.getLoadStatus());
		assertNotNull("Study must be saved", savedId);
		final int studyId = savedId.intValue();
		Study study = (Study)getJdbcTemplate().execute(new StatementCallback(){
          	public Object doInStatement(Statement st) throws SQLException,
          			DataAccessException {
          			ResultSet rs = st.executeQuery("select * from studies where load_status = 0 and id = " + studyId);
          			if(rs.next()) {
          				Study s = new Study();
          				s.setId(rs.getInt(1));
          				s.setShortTitle("Shortest");
          				return s;
          			}
          		return null;
          	}
          });
		System.out.println(study.getId());
		assertEquals("Study Id should be same", studyId, study.getId().intValue());
	}
	
	
	public void testDeleteStudy(){
		Study study = getDao().getById(-3);
		getDao().delete(study);
		interruptSession();
		study = getDao().getById(-3);
		assertNull("Study identified by id -3 must be null", study);
	}

	
	
}
