package gov.nih.nci.cabig.caaers.service.synchronizer;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyAgent;
import gov.nih.nci.cabig.caaers.service.DomainObjectImportOutcome;

public class StudyAgentsSynchronizerTest extends AbstractTestCase{
	
	Study dbStudy;
	Study xmlStudy;
	StudyAgentsSynchronizer studyAgentsSynchronizer;
	DomainObjectImportOutcome<Study> outcome;
	StudyAgent studyAgent1,studyAgent2,studyAgent1a,studyAgent2a,studyAgent3a;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		studyAgentsSynchronizer = new StudyAgentsSynchronizer();
		outcome = new DomainObjectImportOutcome<Study>();
		dbStudy = Fixtures.createStudy("abcd");
		xmlStudy = Fixtures.createStudy("abcd");
	}
	
	public void testStudyAgentsAdd(){
		
		studyAgent1 = Fixtures.createStudyAgent("agent1");
		studyAgent1.setId(1);
		studyAgent2 = Fixtures.createStudyAgent("agent2");
		studyAgent2.setId(2);
		dbStudy.addStudyAgent(studyAgent1);
		dbStudy.addStudyAgent(studyAgent2);
		
		studyAgent1a = Fixtures.createStudyAgent("agent1");
		studyAgent2a = Fixtures.createStudyAgent("agent2");
		studyAgent3a = Fixtures.createStudyAgent("agent3a");
		xmlStudy.addStudyAgent(studyAgent1a);
		xmlStudy.addStudyAgent(studyAgent2a);
		xmlStudy.addStudyAgent(studyAgent3a);
		
		studyAgentsSynchronizer.migrate(dbStudy, xmlStudy, outcome);
		
		assertEquals(3, dbStudy.getStudyAgents().size());
	}
}
