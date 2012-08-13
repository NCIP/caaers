package gov.nih.nci.cabig.caaers.utils.el;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.easymock.EasyMock;

public class MockFunctionMapperTest extends TestCase {
	
	MockFunctionMapper classUnderTest;
	Map functionMap ;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		functionMap =new HashMap<String,Object>();
		functionMap.put("methodName", Object.class.getMethod("toString", null));
		classUnderTest = new MockFunctionMapper(functionMap);
	}
	
	
	public void testAddIdentityMethod() throws Exception{
		classUnderTest.addIdentityMethod("unused name");
	}
	
	public void testIdentity(){
		assertEquals("input", classUnderTest.identity("input"));
	}
	
	public void testResolveFunction() {
		assertEquals("toString",classUnderTest.resolveFunction("pre", "methodName").getName().toString());
	}

}
