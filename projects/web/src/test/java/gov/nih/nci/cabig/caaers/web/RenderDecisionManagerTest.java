package gov.nih.nci.cabig.caaers.web;

import junit.framework.TestCase;

public class RenderDecisionManagerTest extends WebTestCase {

	RenderDecisionManager mgr = null;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		mgr = new RenderDecisionManager();
	}
	public void testCanRenderSection() {
		assertTrue(mgr.canRenderSection("", request, response));
	}

	public void testCanRenderField() {
		assertTrue(mgr.canRenderField("", request, response));
	}

	public void testFindActualName() {
		
		String newName = mgr.findActualName("biju.joseph");
		assertEquals("biju.joseph", newName);
		
		newName = mgr.findActualName("biju.");
		assertEquals("biju.", newName);
		
		newName = mgr.findActualName("biju[15].name.abc");
		assertEquals("biju[].name.abc", newName);
		
		newName = mgr.findActualName("biju.name[13]");
		assertEquals("biju.name[]", newName);
		
		newName = mgr.findActualName("biju[6].joseph[7].name");
		assertEquals("biju[].joseph[].name", newName);
	}

}
