/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.domain;

import java.text.DecimalFormat;

import edu.emory.mathcs.backport.java.util.Arrays;
import junit.framework.TestCase;

/**
 * @author Biju Joseph
 */
public class TreatmentAssignmentTest extends TestCase {
    TreatmentAssignment assignment;
    
    StudyAgent sa1 = null;
    StudyAgent sa2 = null;
    StudyAgent sa3 = null;
    StudyAgent sa4 = null;
    StudyAgent sa5 = null;
    StudyAgent sa6 = null;
    
    TreatmentAssignmentAgent treatmentAssignmentAgent1;
    TreatmentAssignmentAgent treatmentAssignmentAgent2;
    TreatmentAssignmentAgent treatmentAssignmentAgent3;
    
    AgentSpecificTerm agentSpecificTerm1;
    AgentSpecificTerm agentSpecificTerm2;
    AgentSpecificTerm agentSpecificTerm3;
    AgentSpecificTerm agentSpecificTerm4;
    
    CtcTerm ctcTerm1;
    CtcTerm ctcTerm2;
    CtcTerm ctcTerm3;

    Study study = null;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        assignment = Fixtures.createTreatmentAssignment();
        
        sa1 = Fixtures.createStudyAgent("sa1");
        sa2 = Fixtures.createStudyAgent("sa2");
        sa3 = Fixtures.createStudyAgent("sa3");
        sa4 = Fixtures.createStudyAgent("sa4");
        sa4.setIndType(INDType.CTEP_IND);
		sa4.setPartOfLeadIND(true);
		sa5 = Fixtures.createStudyAgent("sa4");
        sa5.setIndType(INDType.CTEP_IND);
		sa5.setPartOfLeadIND(true);
		sa6 = Fixtures.createStudyAgent("sa4");
        sa6.setIndType(INDType.CTEP_IND);
		sa6.setPartOfLeadIND(true);
		
		ctcTerm1 = Fixtures.createCtcTerm("ctepTerm1", "ctepCode1");
		ctcTerm1.setId(1);
		ctcTerm2 = Fixtures.createCtcTerm("ctepTerm2", "ctepCode2");
		ctcTerm2.setId(2);
		ctcTerm3 = Fixtures.createCtcTerm("ctepTerm3", "ctepCode3");
		ctcTerm3.setId(3);
		
		agentSpecificTerm1 = Fixtures.createAgentSpecificCtcTerm(sa4.getAgent(), ctcTerm1);
		agentSpecificTerm2 = Fixtures.createAgentSpecificCtcTerm(sa5.getAgent(), ctcTerm1);
		agentSpecificTerm3 = Fixtures.createAgentSpecificCtcTerm(sa6.getAgent(), ctcTerm2);
		agentSpecificTerm4 = Fixtures.createAgentSpecificCtcTerm(sa6.getAgent(), ctcTerm3);
		
		setupExpectedness(agentSpecificTerm1, 20.00, 10.00, 4.34, 6.23, 44.00, 60.00, false);
		setupExpectedness(agentSpecificTerm2, 2.3432, 65.00, 5.432, 78.2316, 1.00, 90.33, false);
		
        study = Fixtures.createStudy("test");

        sa1.setStudy(study);
        sa2.setStudy(study);
        sa3.setStudy(study);
        sa4.setStudy(study);
        sa5.setStudy(study);
        sa6.setStudy(study);
        
        study.getStudyAgentsInternal().addAll(Arrays.asList(new StudyAgent[]{sa1,sa2,sa3,sa4,sa5,sa6}));
        
        treatmentAssignmentAgent1 = Fixtures.createTreatementAssignmentStudyIntervention(assignment, sa4);
        treatmentAssignmentAgent2 = Fixtures.createTreatementAssignmentStudyIntervention(assignment, sa5);
        treatmentAssignmentAgent3 = Fixtures.createTreatementAssignmentStudyIntervention(assignment, sa6);
    }

    public void testRemoveInterventionFromTreatmentAssignment() throws Exception {

         assignment.addInterventionToTreatmentAssignment(sa1);
         assignment.addInterventionToTreatmentAssignment(sa2);
        
         assertEquals(2, assignment.getTreatmentAssignmentStudyInterventions().size());

         assignment.removeInterventionFromTreatmentAssignment(sa1);

        assertEquals(1, assignment.getTreatmentAssignmentStudyInterventions().size());
        assertSame(sa2, assignment.getTreatmentAssignmentStudyInterventions().get(0).getStudyIntervention());
    }

    public void testAddInterventionToTreatmentAssignment() throws Exception {


        assignment.addInterventionToTreatmentAssignment(sa1);
        assignment.addInterventionToTreatmentAssignment(sa2);

        assertEquals(2, assignment.getTreatmentAssignmentStudyInterventions().size());
        assertSame(sa1, assignment.getTreatmentAssignmentStudyInterventions().get(0).getStudyIntervention());
        assertSame(sa2, assignment.getTreatmentAssignmentStudyInterventions().get(1).getStudyIntervention());

    }

    public void testAddTreatmentAssignmentStudyIntervention() throws Exception {

        assignment.addInterventionToTreatmentAssignment(sa1);
        assignment.addInterventionToTreatmentAssignment(sa2);

        assertEquals(2, assignment.getTreatmentAssignmentStudyInterventions().size());
        assertSame(sa1, assignment.getTreatmentAssignmentStudyInterventions().get(0).getStudyIntervention());
        assertSame(sa2, assignment.getTreatmentAssignmentStudyInterventions().get(1).getStudyIntervention());
    }

    public void testHasIntervention() throws Exception {


        assignment.addInterventionToTreatmentAssignment(sa1);
        assignment.addInterventionToTreatmentAssignment(sa2);

        assertEquals(2, assignment.getTreatmentAssignmentStudyInterventions().size());
        assertSame(sa1, assignment.getTreatmentAssignmentStudyInterventions().get(0).getStudyIntervention());
        assertSame(sa2, assignment.getTreatmentAssignmentStudyInterventions().get(1).getStudyIntervention());

        assertFalse(assignment.hasIntervention(sa3) != null);
        assertTrue(assignment.hasIntervention(sa1) != null);
        assertTrue(assignment.hasIntervention(sa2) != null);
        assertSame(sa1, assignment.hasIntervention(sa1).getStudyIntervention());
    }

    public void testRegenerateAllInterventionIdList() throws Exception {

        sa1.setId(100);

        sa2.setId(200);

        assignment.addInterventionToTreatmentAssignment(sa1);
        assignment.addInterventionToTreatmentAssignment(sa2);
        assignment.regenerateAllInterventionIdList();
        assertTrue(assignment.getSelectedStudyAgentInterventionIds().contains(100));
        assertTrue(assignment.getSelectedStudyAgentInterventionIds().contains(200));
        assertFalse(assignment.getSelectedStudyAgentInterventionIds().contains(300));
        assertFalse(assignment.getSelectedOtherInteterventionIds().contains(200));
        assertFalse(assignment.getSelectedStudyDeviceInterventionIds().contains(200));
    }
    
    public void testEquals(){
        TreatmentAssignment ta1 = Fixtures.createTreatmentAssignment("abcd");
        TreatmentAssignment ta2 = Fixtures.createTreatmentAssignment("abcd");
        assertTrue(ta1.equals(ta2));
        assertEquals(ta1.hashCode(), ta2.hashCode());
        ta2.setDescription("changed");
        assertTrue(ta1.equals(ta2));
        assertEquals(ta1.hashCode(), ta2.hashCode());
        ta2.setComments("changed");
        assertTrue(ta1.equals(ta2));
        assertEquals(ta1.hashCode(), ta2.hashCode());

        TreatmentAssignment ta3 = Fixtures.createTreatmentAssignment("abcd");
        assertTrue(ta3.equals(ta1));
        ta3.setCode("changed");
        assertFalse(ta1.equals(ta3));
        
        TreatmentAssignment ta4 = Fixtures.createTreatmentAssignment("abcd");
        Study s1 = Fixtures.createStudy("s1");
        s1.setId(1);

        ta1.setStudy(s1);
        ta4.setStudy(s1);
        assertTrue(ta1.equals(ta4));
        assertEquals(ta1.hashCode(), ta4.hashCode());

        TreatmentAssignment ta5 = Fixtures.createTreatmentAssignment("abcd");
        ta1.setStudy(s1);
        assertFalse(ta1.equals(ta5));
        assertTrue(ta1.hashCode()!= ta5.hashCode());
        
    }
    
    public void testAddExpectedAE1() throws Exception{
    	assertEquals(0, assignment.getAbstractStudyInterventionExpectedAEs().size());
    	assignment.addExpectedAE(treatmentAssignmentAgent1, agentSpecificTerm1);
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().size());
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().size());
    	assertEquals(treatmentAssignmentAgent1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(0));
    	assertExpectednessSingleTAAE(assignment.getAbstractStudyInterventionExpectedAEs().get(0));
    	
		assignment.addExpectedAE(treatmentAssignmentAgent2, agentSpecificTerm2);
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().size());
    	assertEquals(2, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().size());
    	assertEquals(treatmentAssignmentAgent2, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(1));
    	assertExpectednessMultipleTAAE(assignment.getAbstractStudyInterventionExpectedAEs().get(0));
    	
    	assignment.addExpectedAE(treatmentAssignmentAgent3, agentSpecificTerm3);
    	assertEquals(2, assignment.getAbstractStudyInterventionExpectedAEs().size());
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTreatmentAssignmentAgents().size());
    	assertEquals(treatmentAssignmentAgent3, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTreatmentAssignmentAgents().get(0));
		
    	assignment.addExpectedAE(treatmentAssignmentAgent1, agentSpecificTerm1);
    	assertEquals(2, assignment.getAbstractStudyInterventionExpectedAEs().size());
    	assertEquals(2, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().size());
    	assertEquals(treatmentAssignmentAgent1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(0));
    	assertEquals(treatmentAssignmentAgent2, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(1));
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTreatmentAssignmentAgents().size());
    	assertEquals(treatmentAssignmentAgent3, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTreatmentAssignmentAgents().get(0));
    }
    
    public void testAddExpectedAE2() throws Exception{
    	assertEquals(0, assignment.getAbstractStudyInterventionExpectedAEs().size());
    	assignment.addExpectedAEs(treatmentAssignmentAgent1);
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().size());
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().size());
    	assertEquals(treatmentAssignmentAgent1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(0));
    	
		assignment.addExpectedAEs(treatmentAssignmentAgent2);
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().size());
    	assertEquals(2, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().size());
    	assertEquals(treatmentAssignmentAgent2, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(1));
    	
    	assignment.addExpectedAEs(treatmentAssignmentAgent3);
    	assertEquals(3, assignment.getAbstractStudyInterventionExpectedAEs().size());
    	assertEquals(2, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().size());
    	assertEquals(ctcTerm1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTerm());
    	assertEquals(treatmentAssignmentAgent1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(0));
    	assertEquals(treatmentAssignmentAgent2, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(1));
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTreatmentAssignmentAgents().size());
    	assertEquals(ctcTerm2, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTerm());
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().get(2).getTreatmentAssignmentAgents().size());
    	assertEquals(ctcTerm3, assignment.getAbstractStudyInterventionExpectedAEs().get(2).getTerm());
    	assertEquals(treatmentAssignmentAgent3, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTreatmentAssignmentAgents().get(0));
    	assertEquals(treatmentAssignmentAgent3, assignment.getAbstractStudyInterventionExpectedAEs().get(2).getTreatmentAssignmentAgents().get(0));
    	
    	assignment.addExpectedAEs(treatmentAssignmentAgent3);
    	assertEquals(3, assignment.getAbstractStudyInterventionExpectedAEs().size());
    	assertEquals(2, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().size());
    	assertEquals(treatmentAssignmentAgent1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(0));
    	assertEquals(treatmentAssignmentAgent2, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(1));
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTreatmentAssignmentAgents().size());
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().get(2).getTreatmentAssignmentAgents().size());
    	assertEquals(treatmentAssignmentAgent3, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTreatmentAssignmentAgents().get(0));
    	assertEquals(treatmentAssignmentAgent3, assignment.getAbstractStudyInterventionExpectedAEs().get(2).getTreatmentAssignmentAgents().get(0));
    }
    
    public void testRemoveExpectedAE1() throws Exception{
    	assignment.addExpectedAEs(treatmentAssignmentAgent1);
    	assignment.addExpectedAEs(treatmentAssignmentAgent2);
    	assignment.addExpectedAEs(treatmentAssignmentAgent3);
    	
    	assignment.removeExpectedAE(treatmentAssignmentAgent3, ctcTerm1);
    	assertEquals(3, assignment.getAbstractStudyInterventionExpectedAEs().size());
    	assertEquals(2, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().size());
    	assertEquals(ctcTerm1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTerm());
    	assertEquals(treatmentAssignmentAgent1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(0));
    	assertEquals(treatmentAssignmentAgent2, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(1));
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTreatmentAssignmentAgents().size());
    	assertEquals(ctcTerm2, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTerm());
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().get(2).getTreatmentAssignmentAgents().size());
    	assertEquals(ctcTerm3, assignment.getAbstractStudyInterventionExpectedAEs().get(2).getTerm());
    	assertEquals(treatmentAssignmentAgent3, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTreatmentAssignmentAgents().get(0));
    	assertEquals(treatmentAssignmentAgent3, assignment.getAbstractStudyInterventionExpectedAEs().get(2).getTreatmentAssignmentAgents().get(0));
    	assertExpectednessMultipleTAAE(assignment.getAbstractStudyInterventionExpectedAEs().get(0));
    	
    	assignment.removeExpectedAE(treatmentAssignmentAgent3, ctcTerm2);
    	assertEquals(2, assignment.getAbstractStudyInterventionExpectedAEs().size());
    	assertEquals(2, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().size());
    	assertEquals(ctcTerm1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTerm());
    	assertEquals(treatmentAssignmentAgent1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(0));
    	assertEquals(treatmentAssignmentAgent2, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(1));
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTreatmentAssignmentAgents().size());
    	assertEquals(ctcTerm3, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTerm());
    	assertEquals(treatmentAssignmentAgent3, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTreatmentAssignmentAgents().get(0));
    	
    	assignment.removeExpectedAE(treatmentAssignmentAgent2, ctcTerm1);
    	assertEquals(2, assignment.getAbstractStudyInterventionExpectedAEs().size());
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().size());
    	assertEquals(ctcTerm1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTerm());
    	assertEquals(treatmentAssignmentAgent1, assignment.getAbstractStudyInterventionExpectedAEs().get(0).getTreatmentAssignmentAgents().get(0));
    	assertEquals(1, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTreatmentAssignmentAgents().size());
    	assertEquals(ctcTerm3, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTerm());
    	assertEquals(treatmentAssignmentAgent3, assignment.getAbstractStudyInterventionExpectedAEs().get(1).getTreatmentAssignmentAgents().get(0));
    	assertExpectednessSingleTAAE(assignment.getAbstractStudyInterventionExpectedAEs().get(0));
    	
    }
    
    private void setupExpectedness(AgentSpecificTerm agentSpecificTerm, Double _1, Double _2, Double _3, Double _4, Double _5, Double _overall, boolean _expected){
		agentSpecificTerm.setExpected(_expected);
		agentSpecificTerm.setExpectednessFrequency(_overall);
		agentSpecificTerm.setGrade1Frequency(_1);
		agentSpecificTerm.setGrade2Frequency(_2);
		agentSpecificTerm.setGrade3Frequency(_3);
		agentSpecificTerm.setGrade4Frequency(_4);
		agentSpecificTerm.setGrade5Frequency(_5);
	}
	
	private String format(Double d){
		if (d == null) return null;
        DecimalFormat f = new DecimalFormat("##.00");  // this will help always keeps in two decimal places
        return f.format(d); 
	}
	
	private void assertExpectednessSingleTAAE(AbstractStudyInterventionExpectedAE abstractStudyInterventionExpectedAE){
		assertEquals(60.00, abstractStudyInterventionExpectedAE.getExpectednessFrequency());
		assertEquals(20.00, abstractStudyInterventionExpectedAE.getGrade1Frequency());
		assertEquals(10.00, abstractStudyInterventionExpectedAE.getGrade2Frequency());
		assertEquals(4.34, abstractStudyInterventionExpectedAE.getGrade3Frequency());
		assertEquals(6.23, abstractStudyInterventionExpectedAE.getGrade4Frequency());
		assertEquals(44.00, abstractStudyInterventionExpectedAE.getGrade5Frequency());
		assertFalse(abstractStudyInterventionExpectedAE.isExpected());
	}
	
	private void assertExpectednessMultipleTAAE(AbstractStudyInterventionExpectedAE abstractStudyInterventionExpectedAE){
		assertEquals("96.13", format(abstractStudyInterventionExpectedAE.getExpectednessFrequency()));
		assertEquals("21.87", format(abstractStudyInterventionExpectedAE.getGrade1Frequency()));
		assertEquals("68.50", format(abstractStudyInterventionExpectedAE.getGrade2Frequency()));
		assertEquals("9.54", format(abstractStudyInterventionExpectedAE.getGrade3Frequency()));
		assertEquals("79.59", format(abstractStudyInterventionExpectedAE.getGrade4Frequency()));
		assertEquals("44.56", format(abstractStudyInterventionExpectedAE.getGrade5Frequency()));
		assertFalse(abstractStudyInterventionExpectedAE.isExpected());
	}

}
