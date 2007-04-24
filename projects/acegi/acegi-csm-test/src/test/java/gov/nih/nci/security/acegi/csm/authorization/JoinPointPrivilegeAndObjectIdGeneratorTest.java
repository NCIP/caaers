/**
 * 
 */
package gov.nih.nci.security.acegi.csm.authorization;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com">Joshua Phillips</a>
 * 
 */
public class JoinPointPrivilegeAndObjectIdGeneratorTest extends TestCase {

	/**
	 * 
	 */
	public JoinPointPrivilegeAndObjectIdGeneratorTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public JoinPointPrivilegeAndObjectIdGeneratorTest(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public void testMatching() {
		try {
			Map<String, String> objectPrivilegeMap = new LinkedHashMap<String, String>();
			objectPrivilegeMap.put("execution(* java.util.Map.get(..))",
					"some.object:some_privilege");
			objectPrivilegeMap.put("execution(* java.util.Map.*(..))",
					"some.other.object:some_other_privilege");
			JoinPointPrivilegeAndObjectIdGenerator gen = new JoinPointPrivilegeAndObjectIdGenerator();
			gen.setObjectPrivilegeMap(objectPrivilegeMap);
			gen.afterPropertiesSet();

			Signature sig1 = new MockMethodSignature(HashMap.class.getMethod(
					"get", new Class[] { Object.class }));
			JoinPoint jp1 = new MockJoinPoint(sig1, new Object[] { "yadda" });
			assertEquals("some.object", gen.generateId(jp1));
			assertEquals("some_privilege", gen.generatePrivilege(jp1));

			Signature sig2 = new MockMethodSignature(HashMap.class.getMethod(
					"put", new Class[] { Object.class, Object.class }));
			JoinPoint jp2 = new MockJoinPoint(sig2, new Object[] { "yadda",
					"dadda" });
			assertEquals("some.other.object", gen.generateId(jp2));
			assertEquals("some_other_privilege", gen.generatePrivilege(jp2));
			
		} catch (Exception ex) {
			ex.printStackTrace();
			fail("Error encountered: " + ex.getMessage());
		}
	}

	private class MockJoinPoint implements JoinPoint {

		private Signature signature;

		private Object[] args;

		MockJoinPoint(Signature signature, Object[] args) {
			this.signature = signature;
			this.args = args;
		}

		public Object[] getArgs() {
			return this.args;
		}

		public String getKind() {
			if (true)
				throw new UnsupportedOperationException("Mock not implemented");
			return null;
		}

		public Signature getSignature() {
			return this.signature;
		}

		public SourceLocation getSourceLocation() {
			if (true)
				throw new UnsupportedOperationException("Mock not implemented");
			return null;
		}

		public StaticPart getStaticPart() {
			if (true)
				throw new UnsupportedOperationException("Mock not implemented");
			return null;
		}

		public Object getTarget() {
			if (true)
				throw new UnsupportedOperationException("Mock not implemented");
			return null;
		}

		public Object getThis() {
			if (true)
				throw new UnsupportedOperationException("Mock not implemented");
			return null;
		}

		public String toLongString() {
			if (true)
				throw new UnsupportedOperationException("Mock not implemented");
			return null;
		}

		public String toShortString() {
			if (true)
				throw new UnsupportedOperationException("Mock not implemented");
			return null;
		}

	}

	private class MockMethodSignature implements MethodSignature {

		private Method method;

		MockMethodSignature(Method method) {
			this.method = method;
		}

		public Method getMethod() {
			return method;
		}

		public Class getReturnType() {
			if (true)
				throw new UnsupportedOperationException("Mock not implemented");
			return null;
		}

		public Class[] getExceptionTypes() {
			if (true)
				throw new UnsupportedOperationException("Mock not implemented");
			return null;
		}

		public String[] getParameterNames() {
			if (true)
				throw new UnsupportedOperationException("Mock not implemented");
			return null;
		}

		public Class[] getParameterTypes() {
			return this.method.getParameterTypes();
		}

		public Class getDeclaringType() {
			return this.method.getDeclaringClass();
		}

		public String getDeclaringTypeName() {
			if (true)
				throw new UnsupportedOperationException("Mock not implemented");
			return null;
		}

		public int getModifiers() {
			if (true)
				throw new UnsupportedOperationException("Mock not implemented");
			return 0;
		}

		public String getName() {
			return this.method.getName();
		}

		public String toLongString() {
			if (true)
				throw new UnsupportedOperationException("Mock not implemented");
			return null;
		}

		public String toShortString() {
			if (true)
				throw new UnsupportedOperationException("Mock not implemented");
			return null;
		}

	}
}
