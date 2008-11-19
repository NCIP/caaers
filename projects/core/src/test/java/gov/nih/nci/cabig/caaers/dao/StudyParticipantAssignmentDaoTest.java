package gov.nih.nci.cabig.caaers.dao;

import static gov.nih.nci.cabig.caaers.CaaersUseCase.ASSIGN_PARTICIPANT;
import gov.nih.nci.cabig.caaers.CaaersUseCases;
import gov.nih.nci.cabig.caaers.DaoNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.domain.DateValue;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.PreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantConcomitantMedication;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantDiseaseHistory;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantPreExistingCondition;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantPriorTherapy;

import java.util.Date;

/**
 * @author Rhett Sutphin
 */
@CaaersUseCases( { ASSIGN_PARTICIPANT })
public class StudyParticipantAssignmentDaoTest extends DaoNoSecurityTestCase<StudyParticipantAssignmentDao> {
    private ParticipantDao participantDao = (ParticipantDao) getApplicationContext().getBean(
                    "participantDao");

    private StudyDao studyDao = (StudyDao) getApplicationContext().getBean("studyDao");
    private PreExistingConditionDao pecDao = (PreExistingConditionDao) getApplicationContext().getBean("preExistingConditionDao");
    private AnatomicSiteDao anatomicSiteDao = (AnatomicSiteDao) getApplicationContext().getBean("anatomicSiteDao");
    private PriorTherapyDao priorTherapyDao = (PriorTherapyDao) getApplicationContext().getBean("priorTherapyDao");

    public void testGetById() throws Exception {
        StudyParticipantAssignment assignment = getDao().getById(-13);
        assertEquals("Wrong studySite", -10, (int) assignment.getStudySite().getId());
        assertEquals("Wrong participant", -5, (int) assignment.getParticipant().getId());
        assertEquals("Wrong number of AE reports", 2, assignment.getAeReports().size());
        assertEquals("Wrong number of AE reporting periods", 2, assignment.getReportingPeriods().size());
        
        // Test preExistingConditions
        assertEquals("Wrong number of Pre Existing Conditions", 2, assignment.getPreExistingConditions().size());
        
        // Test diseaseHistory
        assertEquals("Wrong disease history", -1, (int) assignment.getDiseaseHistory().getId());
        
        // Test concomitantMedications
        assertEquals("Wrong number of Concomitant Medications", 3, assignment.getConcomitantMedications().size());
        
        // Test priorTherapies
        assertEquals("Wrong number of prior therapies", 2, assignment.getPriorTherapies().size());
    }

    public void testGetFromParticipantAndStudy() throws Exception {
        Participant p = participantDao.getById(-5);
        Study s = studyDao.getById(-4);

        StudyParticipantAssignment actual = getDao().getAssignment(p, s);
        assertEquals("Wrong assignment found", -13, (int) actual.getId());
    }

    public void testIsAssignmentExistTest() throws Exception {
        Study s = studyDao.getById(-4);
        Participant p = new Participant();
        p.setId(-9);
        boolean exist = getDao().isAssignmentExist(p, s.getStudySites().get(0));
        assertTrue("Assignment should exist", exist);

        p = participantDao.getById(-5);
        exist = getDao().isAssignmentExist(p, s.getStudySites().get(0));
        assertTrue("Assignment should exist", exist);

        p = participantDao.getById(-99);
        exist = getDao().isAssignmentExist(p, s.getStudySites().get(0));
        assertFalse("Assignment should not exist", exist);

    }
    
    public void testSavePreExistingConditionsTest() throws Exception {
    	StudyParticipantAssignment assignment = getDao().getById(-13);
    	PreExistingCondition pec = new PreExistingCondition();
    	pec.setId(-3);
    	pec.setText("sample text 1");
    	pec.setMeddraLlt("sample meddra llt 1");
    	pec.setMeddraLltCode("sample meddra Code 1");
    	pecDao.save(pec);
    	StudyParticipantPreExistingCondition spPec = new StudyParticipantPreExistingCondition();
    	spPec.setId(-3);
    	spPec.setAssignment(assignment);
    	spPec.setPreExistingCondition(pec);
    	spPec.setOther("sample Other 1");
    	assignment.addPreExistingCondition(spPec);
    	getDao().save(assignment);
    	
    	// Interrup the session
    	interruptSession();
    	
    	// Get the assigment again and test if the preExistingCondition was persisted
    	assignment = getDao().getById(-13);
    	
    	// Test preExistingConditions
    	assertEquals("Wrong number of Pre Existing Conditions", 3, assignment.getPreExistingConditions().size());
    }
    
    public void testSaveDiseaseHistoryTest() throws Exception {
    	StudyParticipantAssignment assignment = getDao().getById(-15);
    	StudyParticipantDiseaseHistory spDisHistory = new StudyParticipantDiseaseHistory();
    	spDisHistory.setAssignment(assignment);
    	spDisHistory.setOtherPrimaryDisease("Sample Other Primary Disease -15");
    	spDisHistory.setCodedPrimaryDiseaseSite(anatomicSiteDao.getById(-1));
    	assignment.setDiseaseHistory(spDisHistory);
    	getDao().save(assignment);
    	
    	// Interrup session
    	interruptSession();
    	
    	// Get the assignment again and test if the diseaseHistory was persisted
    	assignment = getDao().getById(-15);
    	
    	// Test DiseaseHistory
    	assertEquals("Wrong disease history", "Sample Other Primary Disease -15", assignment.getDiseaseHistory().getOtherPrimaryDisease());
    	
    }
    
    public void testSaveConcomitantMedicationsTest() throws Exception {
    	StudyParticipantAssignment assignment = getDao().getById(-15);
    	StudyParticipantConcomitantMedication spcm = new StudyParticipantConcomitantMedication();
    	spcm.setAssignment(assignment);
    	spcm.setAgentName("sample Agent Name 1");
    	spcm.setStartDate(new DateValue(new Date()));
    	spcm.setEndDate(new DateValue(new Date()));
    	assignment.getConcomitantMedications().add(spcm);
    	getDao().save(assignment);
    	
    	// Interrupt the session
    	interruptSession();
    	
    	// Get the assignment again and test if the concomitantMedication was persisted
    	assignment = getDao().getById(-15);
    	
    	// Test concomitantMedication
    	assertEquals("Wrong number of concomitant medications", 1, assignment.getConcomitantMedications().size());
    	
    }
    
    public void testSavePriorTherapiesTest() throws Exception{
    	StudyParticipantAssignment assignment = getDao().getById(-15);
    	StudyParticipantPriorTherapy sppt = new StudyParticipantPriorTherapy();
    	sppt.setOther("sample Other 1");
    	sppt.setPriorTherapy(priorTherapyDao.getById(-1));
    	assignment.getPriorTherapies().add(sppt);
    	sppt.setAssignment(assignment);
    	getDao().save(assignment);
    	
    	// Interrupt the session
    	interruptSession();
    	
    	// Get the assignment again and test if the priorTherapy was persisted
    	assignment = getDao().getById(-15);
    	
    	// Test prior Therapies
    	assertEquals("Wrong number of prior therapies", 1, assignment.getPriorTherapies().size());
    }
}
