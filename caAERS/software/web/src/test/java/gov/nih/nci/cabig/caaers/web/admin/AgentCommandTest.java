/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.admin;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Agent;
import gov.nih.nci.cabig.caaers.domain.AgentSpecificTerm;
import gov.nih.nci.cabig.caaers.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.domain.Fixtures;

public class AgentCommandTest extends AbstractTestCase {

	private AgentCommand agentCommand;
	
	private AgentSpecificTerm agentSpecificTerm1;
	private AgentSpecificTerm agentSpecificTerm2;
	private AgentSpecificTerm agentSpecificTerm3;
	private AgentSpecificTerm agentSpecificTerm4;
	private AgentSpecificTerm agentSpecificTerm5;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		agentCommand = new AgentCommand();
		
		agentSpecificTerm1 = Fixtures.createAgentSpecificCtcTerm(new Agent(), new CtcTerm());
		agentSpecificTerm2 = Fixtures.createAgentSpecificCtcTerm(new Agent(), new CtcTerm());
		agentSpecificTerm3 = Fixtures.createAgentSpecificCtcTerm(new Agent(), new CtcTerm());
		agentSpecificTerm4 = Fixtures.createAgentSpecificCtcTerm(new Agent(), new CtcTerm());
		agentSpecificTerm5 = Fixtures.createAgentSpecificCtcTerm(new Agent(), new CtcTerm());
		
		resetExpectedness();
		
		agentSpecificTerm1.setId(1);
		agentSpecificTerm2.setId(2);
		agentSpecificTerm3.setId(3);
		agentSpecificTerm4.setId(4);
		
		agentCommand.setAgentSpecificTerms(new ArrayList<AgentSpecificTerm>());
		agentCommand.getAgentSpecificTerms().addAll(Arrays.asList(agentSpecificTerm1, agentSpecificTerm2, agentSpecificTerm3));
		
	}
	
	public void testTakeExpectednessSnapshot1(){
		agentCommand.takeExpectednessSnapshot();
		assertFalse(agentCommand.isUpdated(agentSpecificTerm1));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm2));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm3));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm4));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm5));
	}
	
	public void testTakeExpectednessSnapshot2(){
		agentCommand.takeExpectednessSnapshot();
		agentSpecificTerm1.setExpected(true);
		assertTrue(agentCommand.isUpdated(agentSpecificTerm1));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm2));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm3));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm4));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm5));
		resetExpectedness();
		
		agentSpecificTerm1.setExpectednessFrequency(99.0);
		assertTrue(agentCommand.isUpdated(agentSpecificTerm1));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm2));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm3));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm4));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm5));
		resetExpectedness();
		
		agentSpecificTerm1.setGrade1Frequency(99.0);
		assertTrue(agentCommand.isUpdated(agentSpecificTerm1));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm2));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm3));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm4));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm5));
		resetExpectedness();
		
		agentSpecificTerm1.setGrade2Frequency(99.0);
		assertTrue(agentCommand.isUpdated(agentSpecificTerm1));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm2));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm3));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm4));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm5));
		resetExpectedness();
		
		agentSpecificTerm1.setGrade3Frequency(99.0);
		assertTrue(agentCommand.isUpdated(agentSpecificTerm1));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm2));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm3));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm4));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm5));
		resetExpectedness();
		
		agentSpecificTerm1.setGrade4Frequency(99.0);
		assertTrue(agentCommand.isUpdated(agentSpecificTerm1));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm2));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm3));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm4));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm5));
		resetExpectedness();
		
		agentSpecificTerm1.setGrade5Frequency(99.0);
		assertTrue(agentCommand.isUpdated(agentSpecificTerm1));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm2));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm3));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm4));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm5));
		resetExpectedness();
		
		agentSpecificTerm2.setGrade5Frequency(99.0);
		assertTrue(agentCommand.isUpdated(agentSpecificTerm2));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm1));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm3));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm4));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm5));
		resetExpectedness();
	}
	
	public void testTakeExpectednessSnapshot3(){
		agentCommand.takeExpectednessSnapshot();
		
		agentSpecificTerm4.setExpected(true);
		assertFalse(agentCommand.isUpdated(agentSpecificTerm4));
		resetExpectedness();
		
		agentSpecificTerm5.setExpectednessFrequency(99.0);
		assertFalse(agentCommand.isUpdated(agentSpecificTerm5));
		resetExpectedness();
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
	
	private void setupExpectedness(){
		setupExpectedness(agentSpecificTerm1, 20.00, 10.00, 4.34, 6.23, 44.00, 60.00, false);
		setupExpectedness(agentSpecificTerm2, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, false);
		setupExpectedness(agentSpecificTerm3, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, false);
		setupExpectedness(agentSpecificTerm4, 13.0, 14.0, 15.0, 16.0, 17.0, 18.0, false);
		setupExpectedness(agentSpecificTerm5, 19.0, 20.0, 21.0, 22.0, 23.0, 24.0, false);
	}
	
	private void resetExpectedness(){
		setupExpectedness();
		assertFalse(agentCommand.isUpdated(agentSpecificTerm1));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm2));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm3));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm4));
		assertFalse(agentCommand.isUpdated(agentSpecificTerm5));
	}
}
