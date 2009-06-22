package gov.nih.nci.cabig.caaers.domain.workflow;

import junit.framework.TestCase;

public class WorkflowConfigTest extends TestCase {
	WorkflowConfig wfConfig;
	protected void setUp() throws Exception {
		super.setUp();
		wfConfig = new WorkflowConfig();
		TaskConfig t1 = new TaskConfig();
		t1.setTaskName("abcd");
		t1.setApplicable(true);
		
		TaskConfig t2 = new TaskConfig();
		t2.setTaskName("efg");
		t2.setApplicable(true);
		
		wfConfig.addTaskConfigs(t1);
		wfConfig.addTaskConfigs(t2);
	}

	public void testAddTaskConfigs() {
		TaskConfig t3 = new TaskConfig();
		t3.setTaskName("hijk");
		t3.setApplicable(true);
		wfConfig.addTaskConfigs(t3);
		
		assertSame(t3, wfConfig.getTaskConfigs().get(2));
	}

	public void testIsTaskActive() {
		boolean active = wfConfig.isTaskActive("abcd");
		assertTrue(active);
	}

	public void testFindTaskConfig() {
		TaskConfig t4 = new TaskConfig();
		t4.setTaskName("kkk");
		t4.setApplicable(true);
		assertNull(wfConfig.findTaskConfig("kkk"));
		wfConfig.addTaskConfigs(t4);
		assertSame(t4, wfConfig.findTaskConfig("kkk"));
	}

}
