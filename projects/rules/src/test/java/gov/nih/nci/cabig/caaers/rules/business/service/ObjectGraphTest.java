package gov.nih.nci.cabig.caaers.rules.business.service;

import gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.domain.StudyAgentINDAssociation;
import gov.nih.nci.cabig.caaers.rules.objectgraph.FactResolver;
import gov.nih.nci.cabig.caaers.rules.objectgraph.NavigationPath;
import gov.nih.nci.cabig.caaers.rules.objectgraph.Node;
import gov.nih.nci.cabig.caaers.rules.objectgraph.ObjectGraphFactory;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class ObjectGraphTest extends TestCase {
	
	
	private ObjectGraphFactory objectGraphFactory;
	
	protected void setUp() throws Exception {
		objectGraphFactory = ObjectGraphFactory.getInstance();
	}
	
	public void testLoadObjectGraph() throws Exception{
		NavigationPath path = objectGraphFactory.findNavigationPath("gov.nih.nci.cabig.caaers.domain.Study", "gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug");
		
		for (Node node:path.getNode()) {
			System.out.println(node.getObjectType());
		}
		
	}
	
	public void atestFactResolver() {
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
				"holderName", "NCI");		
		System.out.println(result);
	}
}
