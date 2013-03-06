/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation.annotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.LocalStudy;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.TreatmentAssignment;
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
    
    public void testValidateRetireableObjects(){
        
        Study s = Fixtures.createStudy("hello");
        s.setId(1);
        
        TreatmentAssignment tac1 = Fixtures.createTreatmentAssignment("t1");
        tac1.setStudy(s);

        TreatmentAssignment tac2 = Fixtures.createTreatmentAssignment("t2");
        tac2.setStudy(s);
        
        assertTrue(validator.validate(Arrays.asList(tac1, tac2)));
        

        TreatmentAssignment tac3 = Fixtures.createTreatmentAssignment("t1");
        tac3.setStudy(s);


        assertFalse(validator.validate(Arrays.asList(tac1, tac2, tac3)));

        tac1.retire();

        assertTrue(validator.validate(Arrays.asList(tac1, tac2, tac3)));


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
