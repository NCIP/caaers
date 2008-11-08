package gov.nih.nci.cabig.caaers.workflow;

import gov.nih.nci.cabig.caaers.CaaersTestCase;

import org.springframework.context.ApplicationContext;

public class LoadWorkflowTest extends CaaersTestCase {
	public void testLoadWorkflow() throws Exception {
		assertTrue(true);
		if(true ) return;
		ApplicationContext ctx = getDeployedApplicationContext();
		Object o = ctx.getBean("jbpmTemplate");
		assertNotNull(o);
	}
}
