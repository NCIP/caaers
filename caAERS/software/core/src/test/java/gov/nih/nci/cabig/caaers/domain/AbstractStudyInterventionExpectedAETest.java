package gov.nih.nci.cabig.caaers.domain;

import java.text.DecimalFormat;

import junit.framework.TestCase;

public class AbstractStudyInterventionExpectedAETest extends TestCase{

	private AbstractStudyInterventionExpectedAE abstractStudyInterventionExpectedAE;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		abstractStudyInterventionExpectedAE = new StudyInterventionExpectedCtcTerm();
	}
	
	public void testRecalculateExpectedBoolean() {
		setupExpectedness(null, null, null, null, null, null, false);
		abstractStudyInterventionExpectedAE.recalculateExpectedness(getAgentSpecificTerm(null, null, null, null, null, null, false), false);
		assertEquals(null, abstractStudyInterventionExpectedAE.getExpectednessFrequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade1Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade2Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade3Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade4Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade5Frequency());
		assertFalse(abstractStudyInterventionExpectedAE.isExpected());
		
		setupExpectedness(null, null, null, null, null, null, true);
		abstractStudyInterventionExpectedAE.recalculateExpectedness(getAgentSpecificTerm(null, null, null, null, null, null, false), false);
		assertEquals(null, abstractStudyInterventionExpectedAE.getExpectednessFrequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade1Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade2Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade3Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade4Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade5Frequency());
		assertTrue(abstractStudyInterventionExpectedAE.isExpected());
		
		setupExpectedness(null, null, null, null, null, null, true);
		abstractStudyInterventionExpectedAE.recalculateExpectedness(getAgentSpecificTerm(null, null, null, null, null, null, false), true);
		assertEquals(null, abstractStudyInterventionExpectedAE.getExpectednessFrequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade1Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade2Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade3Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade4Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade5Frequency());
		assertTrue(abstractStudyInterventionExpectedAE.isExpected());
		
		setupExpectedness(null, null, null, null, null, null, false);
		abstractStudyInterventionExpectedAE.recalculateExpectedness(getAgentSpecificTerm(null, null, null, null, null, null, false), true);
		assertEquals(null, abstractStudyInterventionExpectedAE.getExpectednessFrequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade1Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade2Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade3Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade4Frequency());
		assertEquals(null, abstractStudyInterventionExpectedAE.getGrade5Frequency());
		assertTrue(abstractStudyInterventionExpectedAE.isExpected());
		
	}
	
	public void testRecalculateExpectednessNullValues() {
		setupExpectedness(null, null, null, null, null, null, false);
		abstractStudyInterventionExpectedAE.recalculateExpectedness(getAgentSpecificTerm(20.00, 10.00, 4.34, 6.23, 44.00, 60.00, false), false);
		assertEquals(60.00, abstractStudyInterventionExpectedAE.getExpectednessFrequency());
		assertEquals(20.00, abstractStudyInterventionExpectedAE.getGrade1Frequency());
		assertEquals(10.00, abstractStudyInterventionExpectedAE.getGrade2Frequency());
		assertEquals(4.34, abstractStudyInterventionExpectedAE.getGrade3Frequency());
		assertEquals(6.23, abstractStudyInterventionExpectedAE.getGrade4Frequency());
		assertEquals(44.00, abstractStudyInterventionExpectedAE.getGrade5Frequency());
		assertFalse(abstractStudyInterventionExpectedAE.isExpected());
		
		setupExpectedness(20.00, 10.00, 4.34, 6.23, 44.00, 60.00, false);
		abstractStudyInterventionExpectedAE.recalculateExpectedness(getAgentSpecificTerm(null, null, null, null, null, null, false), false);
		assertEquals(60.00, abstractStudyInterventionExpectedAE.getExpectednessFrequency());
		assertEquals(20.00, abstractStudyInterventionExpectedAE.getGrade1Frequency());
		assertEquals(10.00, abstractStudyInterventionExpectedAE.getGrade2Frequency());
		assertEquals(4.34, abstractStudyInterventionExpectedAE.getGrade3Frequency());
		assertEquals(6.23, abstractStudyInterventionExpectedAE.getGrade4Frequency());
		assertEquals(44.00, abstractStudyInterventionExpectedAE.getGrade5Frequency());
		assertFalse(abstractStudyInterventionExpectedAE.isExpected());
	}
	
	public void testRecalculateExpectedness() {
		setupExpectedness(20.00, 10.00, 4.34, 6.23, 44.00, 60.00, false);
		abstractStudyInterventionExpectedAE.recalculateExpectedness(getAgentSpecificTerm(20.00, 10.00, 4.34, 6.23, 44.00, 60.00, false), false);
		assertEquals("84.00", format(abstractStudyInterventionExpectedAE.getExpectednessFrequency()));
		assertEquals("36.00", format(abstractStudyInterventionExpectedAE.getGrade1Frequency()));
		assertEquals("19.00", format(abstractStudyInterventionExpectedAE.getGrade2Frequency()));
		assertEquals("8.49", format(abstractStudyInterventionExpectedAE.getGrade3Frequency()));
		assertEquals("12.07", format(abstractStudyInterventionExpectedAE.getGrade4Frequency()));
		assertEquals("68.64", format(abstractStudyInterventionExpectedAE.getGrade5Frequency()));
		assertFalse(abstractStudyInterventionExpectedAE.isExpected());
		
		setupExpectedness(20.00, 10.00, 4.34, 6.23, 44.00, 60.00, false);
		abstractStudyInterventionExpectedAE.recalculateExpectedness(getAgentSpecificTerm(2.3432, 65.00, 5.432, 78.2316, 1.00, 90.33, false), false);
		assertEquals("96.13", format(abstractStudyInterventionExpectedAE.getExpectednessFrequency()));
		assertEquals("21.87", format(abstractStudyInterventionExpectedAE.getGrade1Frequency()));
		assertEquals("68.50", format(abstractStudyInterventionExpectedAE.getGrade2Frequency()));
		assertEquals("9.54", format(abstractStudyInterventionExpectedAE.getGrade3Frequency()));
		assertEquals("79.59", format(abstractStudyInterventionExpectedAE.getGrade4Frequency()));
		assertEquals("44.56", format(abstractStudyInterventionExpectedAE.getGrade5Frequency()));
		assertFalse(abstractStudyInterventionExpectedAE.isExpected());
		
	}
	
	private void setupExpectedness(Double _1, Double _2, Double _3, Double _4, Double _5, Double _overall, boolean _expected){
		abstractStudyInterventionExpectedAE.setExpected(_expected);
		abstractStudyInterventionExpectedAE.setExpectednessFrequency(_overall);
		abstractStudyInterventionExpectedAE.setGrade1Frequency(_1);
		abstractStudyInterventionExpectedAE.setGrade2Frequency(_2);
		abstractStudyInterventionExpectedAE.setGrade3Frequency(_3);
		abstractStudyInterventionExpectedAE.setGrade4Frequency(_4);
		abstractStudyInterventionExpectedAE.setGrade5Frequency(_5);
	}
	
	private AgentSpecificTerm getAgentSpecificTerm(Double _1, Double _2, Double _3, Double _4, Double _5, Double _overall, boolean _expected){
		AgentSpecificTerm agentSpecificTerm = new AgentSpecificCtcTerm();
		agentSpecificTerm.setExpected(_expected);
		agentSpecificTerm.setExpectednessFrequency(_overall);
		agentSpecificTerm.setGrade1Frequency(_1);
		agentSpecificTerm.setGrade2Frequency(_2);
		agentSpecificTerm.setGrade3Frequency(_3);
		agentSpecificTerm.setGrade4Frequency(_4);
		agentSpecificTerm.setGrade5Frequency(_5);
		return agentSpecificTerm;
	}
	
	private String format(Double d){
		if (d == null) return null;
        DecimalFormat f = new DecimalFormat("##.00");  // this will help always keeps in two decimal places
        return f.format(d); 
	}

}
