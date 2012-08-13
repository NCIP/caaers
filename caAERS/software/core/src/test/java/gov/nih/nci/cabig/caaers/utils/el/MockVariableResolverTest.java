package gov.nih.nci.cabig.caaers.utils.el;

import junit.framework.TestCase;

public class MockVariableResolverTest extends TestCase{
	
	MockVariableResolver classUnderTest;
	@Override
	protected void setUp() throws Exception{
		super.setUp();
		classUnderTest = new MockVariableResolver();
	}
	
	public void testAddResolveVariable() throws Exception{
		classUnderTest.addVariable("var", new Integer(1));
		assertEquals(new Integer(1),classUnderTest.resolveVariable("var"));
	}

}
