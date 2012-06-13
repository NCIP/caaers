package gov.nih.nci.cabig.caaers.service;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.dao.AgentDao;
import gov.nih.nci.cabig.caaers.dao.AgentSpecificTermDao;
import gov.nih.nci.cabig.caaers.dao.StudyAgentDao;
import gov.nih.nci.cabig.caaers.dao.StudyDao;
import gov.nih.nci.cabig.caaers.domain.AbstractStudyInterventionExpectedAE;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.ExpectedAECtcTerm;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignmentAgent;

import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author Ion C. Olaru
 *
 */
public class AgentSpecificAdverseEventListServiceTest extends CaaersDbTestCase {
    StudyDao studyDao;
    AgentDao agentDao;
    StudyAgentDao studyAgentDao;
    AgentSpecificTermDao agentSpecificTermDao;
    AgentSpecificAdverseEventListServiceImpl service;

    protected void setUp() throws Exception {
        super.setUp();
        studyDao = (StudyDao)getDeployedApplicationContext().getBean("studyDao");
        agentDao = (AgentDao)getDeployedApplicationContext().getBean("agentDao");
        studyAgentDao = (StudyAgentDao)getDeployedApplicationContext().getBean("studyAgentDao");
        agentSpecificTermDao = (AgentSpecificTermDao)getDeployedApplicationContext().getBean("agentSpecificTermDao");

        service = new AgentSpecificAdverseEventListServiceImpl();
        service.setAgentSpecificTermDao(agentSpecificTermDao);
    }
    
    public void testNumberOfStudyAgents() {
        Study s = studyDao.getById(-2);
        assertEquals(2, s.getStudyAgents().size());
    }

    public void testNumberOfAgentTerms() {
        List l = service.getListByAgent(-990);
        assertEquals(2, l.size());
    }

    public void testNumberOfStudyExpectedAEs() {
        Study s = studyDao.getById(-2);
        assertEquals(2, s.getExpectedAECtcTerms().size());
        assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
        assertEquals(2, s.getTreatmentAssignments().size());
        assertEquals(2, s.getTreatmentAssignments().get(0).getTreatmentAssignmentStudyInterventions().size());
        assertEquals(2, s.getTreatmentAssignments().get(1).getTreatmentAssignmentStudyInterventions().size());
        s = studyDao.getById(-3);
        assertEquals(0, s.getExpectedAECtcTerms().size());
        assertEquals(0, s.getExpectedAEMeddraLowLevelTerms().size());
        assertEquals(2, s.getTreatmentAssignments().size());
    }
    
    public void testSynchronizeStudyWithAgent(){
        {
            StudyAgent studyAgent = studyAgentDao.getById(-1);
            Study s = studyDao.getById(-2);
            System.out.println(s.getExpectedAECtcTerms().size());
            service.synchronizeStudyWithAgent(studyAgent, AgentSpecificTerm.EXPTECTED_AE_ADDED);
            studyDao.save(studyAgent.getStudy());
        }
        
    	
    	interruptSession();
        {

            Study s = studyDao.getById(-2);
            assertEquals(3, s.getExpectedAECtcTerms().size());
            assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
            assertEquals(2, s.getTreatmentAssignments().get(0).getTreatmentAssignmentStudyInterventions().size());
            assertEquals(2, s.getTreatmentAssignments().get(1).getTreatmentAssignmentStudyInterventions().size());
            assertEquals(2, s.getTreatmentAssignments().get(0).getAbstractStudyInterventionExpectedAEs().size());
            assertEquals(2, s.getTreatmentAssignments().get(1).getAbstractStudyInterventionExpectedAEs().size());
            assertExpectednessSingleTAAE(s.getTreatmentAssignments().get(0).getExistingTerm(agentSpecificTermDao.getById(-1).getTerm()));
        }
        
        interruptSession();
        {

            StudyAgent studyAgent = studyAgentDao.getById(-2);
            service.synchronizeStudyWithAgent(studyAgent, AgentSpecificTerm.EXPTECTED_AE_ADDED);
            studyDao.save(studyAgent.getStudy());

        }
    	interruptSession();
        {

            Study s = studyDao.getById(-2);
            assertEquals(6, s.getExpectedAECtcTerms().size());
            assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
            assertEquals(2, s.getTreatmentAssignments().get(0).getTreatmentAssignmentStudyInterventions().size());
            assertEquals(2, s.getTreatmentAssignments().get(1).getTreatmentAssignmentStudyInterventions().size());
            assertEquals(4, s.getTreatmentAssignments().get(0).getAbstractStudyInterventionExpectedAEs().size());
            assertEquals(4, s.getTreatmentAssignments().get(1).getAbstractStudyInterventionExpectedAEs().size());
            assertExpectednessMultipleTAAE(s.getTreatmentAssignments().get(0).getExistingTerm(agentSpecificTermDao.getById(-1).getTerm()));

            StudyAgent studyAgent = studyAgentDao.getById(-2);
            service.synchronizeStudyWithAgent(studyAgent, AgentSpecificTerm.EXPTECTED_AE_ADDED);
            studyDao.save(studyAgent.getStudy());
        }
    	
    	interruptSession();
    	
        {
            Study s = studyDao.getById(-2);
            assertEquals(6, s.getExpectedAECtcTerms().size());
            assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
            assertEquals(2, s.getTreatmentAssignments().get(0).getTreatmentAssignmentStudyInterventions().size());
            assertEquals(2, s.getTreatmentAssignments().get(1).getTreatmentAssignmentStudyInterventions().size());
            assertEquals(4, s.getTreatmentAssignments().get(0).getAbstractStudyInterventionExpectedAEs().size());
            assertEquals(4, s.getTreatmentAssignments().get(1).getAbstractStudyInterventionExpectedAEs().size());
            assertExpectednessMultipleTAAE(s.getTreatmentAssignments().get(0).getExistingTerm(agentSpecificTermDao.getById(-1).getTerm()));
        }
        
        interruptSession();
        {
            Study s = studyDao.getById(-2);
            s.getExpectedAECtcTerms().size();
            s.getExpectedAEMeddraLowLevelTerms().size();
            for(TreatmentAssignment ta: s.getTreatmentAssignments()){
                ta.getTreatmentAssignmentStudyInterventions().size();
                for(AbstractStudyInterventionExpectedAE as : ta.getAbstractStudyInterventionExpectedAEs()){
                    as.getTreatmentAssignmentAgents().size();
                }
            }
            for(StudyAgent sa : s.getStudyAgents()){
                if(sa.getAgent()!=null){
                    sa.getAgent().getAgentSpecificTerms().size();
                    sa.getTreatmentAssignmentAgents().size();
                }
            }
        }
        
        interruptSession();
        {
            Study s = studyDao.getById(-2);
            StudyAgent studyAgent = s.getStudyAgents().get(0);
            studyAgent.retire();
            service.synchronizeStudyWithAgent(studyAgent, AgentSpecificTerm.EXPTECTED_AE_DELETED);
            studyAgent.removeTreatmentAssignmentAgents();
            studyDao.save(studyAgent.getStudy());

            interruptSession();

            s = studyDao.getById(-2);
            for(ExpectedAECtcTerm ex_ae: s.getExpectedAECtcTerms()){
                System.out.println(ex_ae.getCtcTerm().getCtepTerm());
            }


            assertEquals(4, s.getExpectedAECtcTerms().size());
            assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
            assertEquals(1, s.getTreatmentAssignments().get(0).getTreatmentAssignmentStudyInterventions().size());
            assertEquals(1, s.getTreatmentAssignments().get(1).getTreatmentAssignmentStudyInterventions().size());
            assertEquals(2, s.getTreatmentAssignments().get(0).getAbstractStudyInterventionExpectedAEs().size());
            assertEquals(2, s.getTreatmentAssignments().get(1).getAbstractStudyInterventionExpectedAEs().size());
            assertExpectednessSingleTAAE(s.getTreatmentAssignments().get(0).getExistingTerm(agentSpecificTermDao.getById(-1).getTerm()));
        }


    }
    
    public void testSynchronizeStudyWithAgentTerm(){
    	StudyAgent studyAgent = studyAgentDao.getById(-1);
    	AgentSpecificTerm ast = agentSpecificTermDao.getById(-1);
    	service.synchronizeStudyWithAgentTerm(studyAgent, ast, AgentSpecificTerm.EXPTECTED_AE_ADDED);
    	ast = agentSpecificTermDao.getById(-2);
    	service.synchronizeStudyWithAgentTerm(studyAgent, ast, AgentSpecificTerm.EXPTECTED_AE_ADDED);
    	studyDao.save(studyAgent.getStudy());
    	interruptSession();
    	Study s = studyDao.getById(-2);
        assertEquals(3, s.getExpectedAECtcTerms().size());
        assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
        assertEquals(2, s.getTreatmentAssignments().get(0).getTreatmentAssignmentStudyInterventions().size());
        assertEquals(2, s.getTreatmentAssignments().get(1).getTreatmentAssignmentStudyInterventions().size());
        assertEquals(2, s.getTreatmentAssignments().get(0).getAbstractStudyInterventionExpectedAEs().size());
        assertEquals(2, s.getTreatmentAssignments().get(1).getAbstractStudyInterventionExpectedAEs().size());
        assertExpectednessSingleTAAE(s.getTreatmentAssignments().get(0).getExistingTerm(agentSpecificTermDao.getById(-1).getTerm()));
        interruptSession();

        studyAgent = studyAgentDao.getById(-2);
        ast = agentSpecificTermDao.getById(-3);
    	service.synchronizeStudyWithAgentTerm(studyAgent, ast, AgentSpecificTerm.EXPTECTED_AE_ADDED);
    	ast = agentSpecificTermDao.getById(-4);
    	service.synchronizeStudyWithAgentTerm(studyAgent, ast, AgentSpecificTerm.EXPTECTED_AE_ADDED);
    	ast = agentSpecificTermDao.getById(-5);
    	service.synchronizeStudyWithAgentTerm(studyAgent, ast, AgentSpecificTerm.EXPTECTED_AE_ADDED);
    	studyDao.save(studyAgent.getStudy());
    	interruptSession();
    	s = studyDao.getById(-2);
        assertEquals(6, s.getExpectedAECtcTerms().size());
        assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
        assertEquals(2, s.getTreatmentAssignments().get(0).getTreatmentAssignmentStudyInterventions().size());
        assertEquals(2, s.getTreatmentAssignments().get(1).getTreatmentAssignmentStudyInterventions().size());
        assertEquals(4, s.getTreatmentAssignments().get(0).getAbstractStudyInterventionExpectedAEs().size());
        assertEquals(4, s.getTreatmentAssignments().get(1).getAbstractStudyInterventionExpectedAEs().size());
        assertExpectednessMultipleTAAE(s.getTreatmentAssignments().get(0).getExistingTerm(agentSpecificTermDao.getById(-1).getTerm()));
        
        studyAgent = studyAgentDao.getById(-2);
        ast = agentSpecificTermDao.getById(-3);
    	service.synchronizeStudyWithAgentTerm(studyAgent, ast, AgentSpecificTerm.EXPTECTED_AE_ADDED);
    	studyDao.save(studyAgent.getStudy());
    	interruptSession();
    	s = studyDao.getById(-2);
        assertEquals(6, s.getExpectedAECtcTerms().size());
        assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
        assertEquals(2, s.getTreatmentAssignments().get(0).getTreatmentAssignmentStudyInterventions().size());
        assertEquals(2, s.getTreatmentAssignments().get(1).getTreatmentAssignmentStudyInterventions().size());
        assertEquals(4, s.getTreatmentAssignments().get(0).getAbstractStudyInterventionExpectedAEs().size());
        assertEquals(4, s.getTreatmentAssignments().get(1).getAbstractStudyInterventionExpectedAEs().size());
        assertExpectednessMultipleTAAE(s.getTreatmentAssignments().get(0).getExistingTerm(agentSpecificTermDao.getById(-1).getTerm()));
        
        studyAgent = studyAgentDao.getById(-2);
        ast = agentSpecificTermDao.getById(-3);
        ast.setGrade1Frequency(20.00);
        ast.setGrade2Frequency(10.00);
        ast.setGrade3Frequency(4.34);
        ast.setGrade4Frequency(6.23);
        ast.setGrade5Frequency(44.00);
        ast.setExpectednessFrequency(60.00);
        ast.setExpected(false);
    	service.synchronizeStudyWithAgentTerm(studyAgent, ast, AgentSpecificTerm.EXPTECTED_AE_UPDATED);
    	studyDao.save(studyAgent.getStudy());
    	interruptSession();
    	s = studyDao.getById(-2);
        assertEquals(6, s.getExpectedAECtcTerms().size());
        assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
        assertEquals(2, s.getTreatmentAssignments().get(0).getTreatmentAssignmentStudyInterventions().size());
        assertEquals(2, s.getTreatmentAssignments().get(1).getTreatmentAssignmentStudyInterventions().size());
        assertEquals(4, s.getTreatmentAssignments().get(0).getAbstractStudyInterventionExpectedAEs().size());
        assertEquals(4, s.getTreatmentAssignments().get(1).getAbstractStudyInterventionExpectedAEs().size());
        assertExpectednessUpdatedTAAE(s.getTreatmentAssignments().get(0).getExistingTerm(agentSpecificTermDao.getById(-1).getTerm()));
        
        studyAgent = studyAgentDao.getById(-2);
        assertTrue(studyAgent.equals(studyAgent.getStudy().getStudyAgents().get(0)));
        ast = agentSpecificTermDao.getById(-3);
    	service.synchronizeStudyWithAgentTerm(studyAgent, ast, AgentSpecificTerm.EXPTECTED_AE_DELETED);
    	ast = agentSpecificTermDao.getById(-4);
    	service.synchronizeStudyWithAgentTerm(studyAgent, ast, AgentSpecificTerm.EXPTECTED_AE_DELETED);
    	ast = agentSpecificTermDao.getById(-5);
    	service.synchronizeStudyWithAgentTerm(studyAgent, ast, AgentSpecificTerm.EXPTECTED_AE_DELETED);
    	studyDao.save(studyAgent.getStudy());
        interruptSession();
    	s = studyDao.getById(-2);
        assertEquals(4, s.getExpectedAECtcTerms().size());
        assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
        assertEquals(2, s.getTreatmentAssignments().get(0).getTreatmentAssignmentStudyInterventions().size());
        assertEquals(2, s.getTreatmentAssignments().get(1).getTreatmentAssignmentStudyInterventions().size());
        assertEquals(2, s.getTreatmentAssignments().get(0).getAbstractStudyInterventionExpectedAEs().size());
        assertEquals(2, s.getTreatmentAssignments().get(1).getAbstractStudyInterventionExpectedAEs().size());
        assertExpectednessSingleTAAE(s.getTreatmentAssignments().get(0).getExistingTerm(agentSpecificTermDao.getById(-1).getTerm()));
    }

//    public void testSynchronizeStudyExpectedAEsWithNotExistingTerm() {
//        Study s = studyDao.getById(-2);
//        assertEquals(2, s.getExpectedAECtcTerms().size());
//        AgentSpecificTerm t = agentSpecificTermDao.getById(-4);
//        service.synchronizeStudyWithAgentTerm(s, t);
//        assertEquals(3, s.getExpectedAECtcTerms().size());
//    }
//
//    public void testSynchronizeStudyExpectedAEsWithExistingTerm() {
//        Study s = studyDao.getById(-2);
//        assertEquals(2, s.getExpectedAECtcTerms().size());
//        AgentSpecificTerm t = agentSpecificTermDao.getById(-5);
//        service.synchronizeStudyWithAgentTerm(s, t);
//        assertEquals(2, s.getExpectedAECtcTerms().size());
//    }
//
//    public void testSynchronizeStudyExpectedAEsWithAgent() {
//        Study s = studyDao.getById(-2);
//        Agent a = agentDao.getById(-990);
//        assertEquals(2, s.getExpectedAECtcTerms().size());
//        assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
//        service.synchronizeStudyWithAgent(s, a);
//        assertEquals(6, s.getExpectedAECtcTerms().size());
//        assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
//    }
//
//    public void testSynchronizeStudyExpectedAEsDeleteAgentTerm() {
//        Study s = studyDao.getById(-2);
//        Agent a = agentDao.getById(-990);
//        assertEquals(2, s.getExpectedAECtcTerms().size());
//        assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
//        AgentSpecificTerm t = agentSpecificTermDao.getById(-5);
//        service.synchronizeStudyWithAgentTerm(s, t, true);
//        assertEquals(1, s.getExpectedAECtcTerms().size());
//        assertEquals(1, s.getExpectedAEMeddraLowLevelTerms().size());
//    }
    
    private void assertExpectednessSingleTAAE(AbstractStudyInterventionExpectedAE abstractStudyInterventionExpectedAE){
		assertEquals("60.00", format(abstractStudyInterventionExpectedAE.getExpectednessFrequency()));
		assertEquals("20.00", format(abstractStudyInterventionExpectedAE.getGrade1Frequency()));
		assertEquals("10.00", format(abstractStudyInterventionExpectedAE.getGrade2Frequency()));
		assertEquals("4.34", format(abstractStudyInterventionExpectedAE.getGrade3Frequency()));
		assertEquals("6.23", format(abstractStudyInterventionExpectedAE.getGrade4Frequency()));
		assertEquals("44.00", format(abstractStudyInterventionExpectedAE.getGrade5Frequency()));
		assertFalse(abstractStudyInterventionExpectedAE.isExpected());
	}
	
	private void assertExpectednessMultipleTAAE(AbstractStudyInterventionExpectedAE abstractStudyInterventionExpectedAE){
		assertEquals("96.13", format(abstractStudyInterventionExpectedAE.getExpectednessFrequency()));
		assertEquals("21.87", format(abstractStudyInterventionExpectedAE.getGrade1Frequency()));
		assertEquals("68.50", format(abstractStudyInterventionExpectedAE.getGrade2Frequency()));
		assertEquals("9.54", format(abstractStudyInterventionExpectedAE.getGrade3Frequency()));
		assertEquals("79.59", format(abstractStudyInterventionExpectedAE.getGrade4Frequency()));
		assertEquals("44.56", format(abstractStudyInterventionExpectedAE.getGrade5Frequency()));
		assertTrue(abstractStudyInterventionExpectedAE.isExpected());
	}
	
	private void assertExpectednessUpdatedTAAE(AbstractStudyInterventionExpectedAE abstractStudyInterventionExpectedAE){
		assertEquals("84.00", format(abstractStudyInterventionExpectedAE.getExpectednessFrequency()));
		assertEquals("36.00", format(abstractStudyInterventionExpectedAE.getGrade1Frequency()));
		assertEquals("19.00", format(abstractStudyInterventionExpectedAE.getGrade2Frequency()));
		assertEquals("8.49", format(abstractStudyInterventionExpectedAE.getGrade3Frequency()));
		assertEquals("12.07", format(abstractStudyInterventionExpectedAE.getGrade4Frequency()));
		assertEquals("68.64", format(abstractStudyInterventionExpectedAE.getGrade5Frequency()));
		assertFalse(abstractStudyInterventionExpectedAE.isExpected());
	}
	
	private String format(Double d){
		if (d == null) return null;
        DecimalFormat f = new DecimalFormat("##.00");  // this will help always keeps in two decimal places
        return f.format(d); 
	}

}