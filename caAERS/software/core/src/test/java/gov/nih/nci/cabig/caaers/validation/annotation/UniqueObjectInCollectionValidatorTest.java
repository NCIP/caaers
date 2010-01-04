package gov.nih.nci.cabig.caaers.validation.annotation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class UniqueObjectInCollectionValidatorTest extends TestCase {
	UniqueObjectInCollectionValidator validator;
	protected void setUp() throws Exception {
		super.setUp();
		validator = new UniqueObjectInCollectionValidator();
	}

	public void testValidate() {
		
		assertTrue(validator.validate(new Object()));
		
		Object o = new Object();
		List<Object> objects = new ArrayList<Object>();
		objects.add(o);
		assertTrue(validator.validate(objects));
		objects.add(o);
		assertFalse(validator.validate(objects));
		
		
		AnObject a1 = new AnObject(1);
		AnObject a2 = new AnObject(2);
		AnObject a3 = new AnObject(3);
		AnObject a = new AnObject(2,4);
		
		objects.clear();
		objects.add(a1);
		objects.add(a2);
		objects.add(a3);
		assertTrue(validator.validate(objects));
		objects.add(a);
		assertFalse(validator.validate(objects));
		
		HashSet<AnObject> set = new HashSet<AnObject>();
		set.add(a);
		set.add(a1);
		set.add(a2);
		set.add(a3);
		assertEquals(4, set.size());
	}
	class AnObject extends Object{
		int i ;
		Integer j;
		
		public AnObject(int i) {
			this(i, i);
		}
		public AnObject(int i, Integer j) {
			this.i = i;
			this.j = j;
		}
		
		@Override
		public boolean equals(Object obj) {
			AnObject o = (AnObject)obj;
			return o.i == i;
		}
		
		@Override
		public int hashCode() {
			return j.hashCode();
		}
		
	}

}
