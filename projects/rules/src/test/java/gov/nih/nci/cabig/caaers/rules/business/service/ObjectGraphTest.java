package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.rules.objectgraph.FactResolver;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class ObjectGraphTest extends TestCase {
	
	protected void setUp() throws Exception {
		
	}
	public void testFactResolver() {
		Study study = new Study ();
		study.setShortTitle("cgems");
		
		StudyAgent sa1 = new StudyAgent();
		sa1.setAgentAsString("agent1");
		
		InvestigationalNewDrug investigationalNewDrug = new InvestigationalNewDrug();
		investigationalNewDrug.setHolderName("NCI");
		
		StudyAgentINDAssociation studyAgentINDAssociation = new StudyAgentINDAssociation();
		studyAgentINDAssociation.setInvestigationalNewDrug(investigationalNewDrug);
		
		List<StudyAgentINDAssociation> studyAgentINDAssociations = new ArrayList<StudyAgentINDAssociation>();
		studyAgentINDAssociations.add(studyAgentINDAssociation);
		
		sa1.setStudyAgentINDAssociations(studyAgentINDAssociations);
		
		List<StudyAgent> studyAgents = new ArrayList<StudyAgent>();
		studyAgents.add(sa1);
		/*
		StudyAgent sa2 = new StudyAgent();
		sa2.setAgentAsString("agent2");
		
		
		
		studyAgents.add(sa2);
		*/
		study.setStudyAgents(studyAgents);
		
		FactResolver factResolver = new FactResolver();
		
		//boolean result = factResolver.assertFact(study, 
		//					"gov.nih.nci.cabig.caaers.domain.StudyAgent", 
		//					"agentAsString", "agent2");
		boolean result = factResolver.assertFact(study, 
				"gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug", 
				"holderName", "NCIss");		
		System.out.println(result);
	}
}
