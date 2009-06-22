package gov.nih.nci.cabig.caaers.domain.workflow;

import junit.framework.TestCase;

public class TaskConfigTest extends TestCase {
	TaskConfig taskConfig;
	protected void setUp() throws Exception {
		super.setUp();
		taskConfig = new TaskConfig();
		taskConfig.setTaskName("abcd");
	}

	public void testAddTransition() {
		TransitionConfig t1 = new TransitionConfig();
		assertNull(taskConfig.getTransitions());
		taskConfig.addTransition(t1);
		assertNotNull(taskConfig.getTransitions());
		assertSame(t1, taskConfig.getTransitions().get(0));
	}

	public void testFindTransitionConfig() {
		TransitionConfig t1 = new TransitionConfig();
		t1.setTransitionName("abcd");
		
		TransitionConfig t2 = new TransitionConfig();
		t2.setTransitionName("efg");
		
		taskConfig.addTransition(t1);
		taskConfig.addTransition(t2);
		
		assertSame(t1,taskConfig.findTransitionConfig("abcd"));
		assertNull(taskConfig.findTransitionConfig(null));
	}

}
