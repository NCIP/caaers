package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.MAPPING_VOCAB;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoTestCase;
import gov.nih.nci.cabig.caaers.dao.meddra.LowLevelTermDao;
import gov.nih.nci.cabig.caaers.dao.MeddraVersionDao;
import gov.nih.nci.cabig.caaers.domain.MeddraVersion;
import gov.nih.nci.cabig.caaers.domain.meddra.LowLevelTerm;

import java.util.List;

/**
 * @author Krikor Krumlian
 * 
 */
@CaaersUseCases( { MAPPING_VOCAB })
public class LowLevelTermDaoTest extends DaoTestCase<LowLevelTermDao> {
    //private OrganizationDao organizationDao = (OrganizationDao) getApplicationContext().getBean(
    //                "organizationDao");
	private MeddraVersionDao meddraVersionDao = (MeddraVersionDao) getApplicationContext().getBean(
					  "meddraVersionDao");
	

    public void testGetById() throws Exception {
        //LowLevelTerm llt = getDao().getById(12);
        //assertNotNull("LowLevelTerm not found", llt);
    }

    /*public void testGetByMeddraCode() throws Exception {
        List<LowLevelTerm> llt = getDao().getByMeddraCode("123");
        assertNotNull("LowLevelTerm not found", llt.get(0));
    }

    public void testFailGetByMeddraCodePartial() throws Exception {
        List<LowLevelTerm> llt = getDao().getByMeddraCode("12");
        assertEquals("LowLevelTerm not found", 0, llt.size());
    }

    public void testGetByVersionSubnames() throws Exception {
    	String[] subnames = {"Abruption"};
    	//List<LowLevelTerm> llt = getDao().getBySubnames(subnames);
    	List<LowLevelTerm> llt = getDao().getByVersionSubnames(1, subnames);
    	llt.get(0).getPreferredTerm();
    	assertNull("LowLevelTerm found", llt);
    }
    
    public void testLoadMeddraGetByVersionSubnames() throws Exception {
    	String[] subnames = {"%"};
    	List<MeddraVersion> meddraVersionList = meddraVersionDao.getAll();
    	assertNotNull("MeddraVersion not found", meddraVersionList);
    	List<LowLevelTerm> llt = getDao().getByVersionSubnames(1, subnames);
    	assertNotNull("LowLevelTerm not found", llt);
    }*/
    /*
     * public void testGetIsReadOnly() throws Exception { { Participant participant =
     * getDao().getById(-100); assertEquals("Wrong number of identifiers initially", 1,
     * participant.getIdentifiers().size()); participant.getIdentifiers().clear(); }
     * 
     * interruptSession();
     *  { Participant participant = getDao().getById(-100); assertEquals("Identifiers incorrectly
     * purged", 1, participant.getIdentifiers().size()); } }
     * 
     * public void testSaveAssignment() throws Exception { { Organization organization =
     * organizationDao.getById(-1001); StudySite studySite = organization.getStudySites().get(0);
     * assertEquals("Wrong study site found in test setup", -3001, (int) studySite.getId());
     * Participant participant = getDao().getById(-100); assertEquals("Participant should already
     * have one assignment", 1, participant.getAssignments().size());
     * 
     * StudyParticipantAssignment spa = new StudyParticipantAssignment();
     * spa.setParticipant(participant); spa.setStudySite(studySite); spa.setDateOfEnrollment(new
     * Date());
     * 
     * participant.addAssignment(spa);
     * 
     * getDao().save(participant); }
     * 
     * interruptSession();
     * 
     * Participant loaded = getDao().getById(-100); assertNotNull("Participant reloading failed",
     * loaded); assertEquals("Assignment not saved", 2, loaded.getAssignments().size());
     * StudyParticipantAssignment newAssignment = loaded.getAssignments().get(1);
     * assertEquals("Wrong participant", -100, (int) newAssignment.getParticipant().getId());
     * assertEquals("Wrong study site", -3001, (int) newAssignment.getStudySite().getId());
     * //assertSameDay("Wrong start date", new Date(), newAssignment.getDateOfEnrollment()); }
     * 
     * 
     * public void testSaveNewParticipant() throws Exception { Integer savedId; { Participant
     * participant = new Participant(); participant.setFirstName("Jeff");
     * participant.setLastName("Someone"); participant.setGender("Male");
     * participant.setDateOfBirth(new Date()); participant.setEthnicity("ethnicity");
     * participant.setRace("race");
     * 
     * getDao().save(participant); savedId = participant.getId(); assertNotNull("The saved
     * participant id", savedId); }
     * 
     * interruptSession();
     *  { Participant loaded = getDao().getById(savedId); assertNotNull("Could not reload
     * participant id " + savedId, loaded); assertEquals("Wrong firstname", "Jeff",
     * loaded.getFirstName()); assertEquals("Wrong lastname", "Someone", loaded.getLastName());
     * assertEquals("Wrong gender", "Male", loaded.getGender()); } }
     * 
     * public void testGetBySubnameMatchesFirstName() throws Exception { List<Participant> matches =
     * getDao().getBySubnames(new String[] { "icha" }); assertEquals("Wrong number of matches", 1,
     * matches.size()); assertEquals("Wrong match", -101, (int) matches.get(0).getId()); }
     * 
     * public void testGetBySubnameMatchesLastName() throws Exception { List<Participant> matches =
     * getDao().getBySubnames(new String[] { "cot" }); assertEquals("Wrong number of matches", 1,
     * matches.size()); assertEquals("Wrong match", -100, (int) matches.get(0).getId()); }
     * 
     * public void testGetBySubnameMatchesInstitutionalId() throws Exception { List<Participant>
     * matches = getDao().getBySubnames(new String[] { "P002" }); assertEquals("Wrong number of
     * matches", 1, matches.size()); assertEquals("Wrong match", -101, (int)
     * matches.get(0).getId()); }
     * 
     * public void testGetBySubnameWithNullSubnamesReturnsNothing() throws Exception { List<Participant>
     * actual = getDao().getBySubnames(null); assertEquals(0, actual.size()); }
     * 
     * public void testGetBySubnameWithNoSubnamesReturnsNothing() throws Exception { List<Participant>
     * actual = getDao().getBySubnames(new String[] { }); assertEquals(0, actual.size()); }
     * 
     * public void testGetBySubnameMatchesIntersectionOfMultiple() throws Exception { List<Participant>
     * matches;
     * 
     * matches = getDao().getBySubnames(new String[] { "Jor", "P001" }); assertEquals("Should be no
     * matches", 0, matches.size());
     * 
     * matches = getDao().getBySubnames(new String[] { "Jor", "P002" }); assertEquals("Wrong number
     * of matches", 1, matches.size()); assertEquals("Wrong match", -101, (int)
     * matches.get(0).getId()); }
     * 
     * public void testSearchParticipantPropertyExistance() throws Exception { Class participant =
     * gov.nih.nci.cabig.caaers.domain.Participant.class;
     * assertNotNull(participant.getDeclaredField("firstName"));
     * assertNotNull(participant.getDeclaredField("lastName"));
     * assertNotNull(participant.getDeclaredField("gender"));
     * assertNotNull(participant.getDeclaredField("ethnicity"));
     * 
     * Class study = gov.nih.nci.cabig.caaers.domain.Study.class;
     * assertNotNull(study.getDeclaredField("shortTitle"));
     * 
     * Class identifier = gov.nih.nci.cabig.caaers.domain.Identifier.class;
     * assertNotNull(identifier.getDeclaredField("value")); }
     * 
     * public void testSearchParticipantByStudyFirstName() throws Exception { List<Participant>
     * results; Map<String,String> m = new HashMap<String,String>(); m.put("studyShortTitle",
     * "sh"); results = getDao().searchParticipant(m); assertEquals("Wrong number of results", 1,
     * results.size()); assertEquals("Wrong match", "Dilbert",results.get(0).getFirstName()); }
     * 
     * public void testSearchParticipantByStudyIdentifier() throws Exception { List<Participant>
     * results; Map<String,String> m = new HashMap<String,String>(); m.put("studyIdentifier",
     * "nci_test"); results = getDao().searchParticipant(m); assertEquals("Wrong number of results",
     * 1, results.size()); assertEquals("Wrong match", "Dilbert",results.get(0).getFirstName()); }
     * 
     * public void testSearchParticipantByParticipantFirstName() throws Exception { List<Participant>
     * results; Map<String,String> m = new HashMap<String,String>(); m.put("participantFirstName",
     * "Dilbert"); results = getDao().searchParticipant(m); assertEquals("Wrong number of results",
     * 1, results.size()); assertEquals("Wrong match", "Dilbert",results.get(0).getFirstName()); }
     * 
     * public void testSearchParticipantByParticipantLastName() throws Exception { List<Participant>
     * results; Map<String,String> m = new HashMap<String,String>(); m.put("participantLastName",
     * "Scott"); results = getDao().searchParticipant(m); assertEquals("Wrong number of results", 1,
     * results.size()); assertEquals("Wrong match", "Dilbert",results.get(0).getFirstName()); }
     * 
     * public void testSearchParticipantByParticipantEthnicity() throws Exception { List<Participant>
     * results; Map<String,String> m = new HashMap<String,String>(); m.put("participantEthnicity",
     * "ethnicity"); results = getDao().searchParticipant(m); assertEquals("Wrong number of
     * results", 1, results.size()); assertEquals("Wrong match",
     * "Dilbert",results.get(0).getFirstName()); }
     * 
     * public void testSearchParticipantByParticipantEthnicityPartial() throws Exception { List<Participant>
     * results; Map<String,String> m = new HashMap<String,String>(); m.put("participantEthnicity",
     * "ethn"); results = getDao().searchParticipant(m); assertEquals("Wrong number of results", 0,
     * results.size()); }
     * 
     * public void testSearchParticipantByParticipantGender() throws Exception { List<Participant>
     * results; Map<String,String> m = new HashMap<String,String>(); m.put("participantGender",
     * "Female"); results = getDao().searchParticipant(m); assertEquals("Wrong number of results",
     * 1, results.size()); assertEquals("Wrong match", "Dilbert",results.get(0).getFirstName()); }
     * 
     * public void testSearchParticipantByParticipantGenderPartial() throws Exception { List<Participant>
     * results; Map<String,String> m = new HashMap<String,String>(); m.put("participantGender",
     * "Fema"); results = getDao().searchParticipant(m); assertEquals("Wrong number of results", 0,
     * results.size()); }
     * 
     * public void testSearchParticipantByParticipantDateOfBirth() throws Exception { List<Participant>
     * results; Map<String,String> m = new HashMap<String,String>();
     * m.put("participantDateOfBirth", "01/02/2006"); results = getDao().searchParticipant(m);
     * assertEquals("Wrong number of results", 1, results.size()); assertEquals("Wrong match",
     * "Dilbert",results.get(0).getFirstName()); }
     * 
     * public void testSearchParticipantByMultipleCriterias() throws Exception { List<Participant>
     * results; Map<String,String> m = new HashMap<String,String>(); m.put("participantFirstName",
     * "Dilbert"); m.put("participantGender", "Female"); m.put("participantDateOfBirth",
     * "01/02/2006"); results = getDao().searchParticipant(m); assertEquals("Wrong number of
     * results", 1, results.size()); assertEquals("Wrong match",
     * "Dilbert",results.get(0).getFirstName()); }
     */

}
