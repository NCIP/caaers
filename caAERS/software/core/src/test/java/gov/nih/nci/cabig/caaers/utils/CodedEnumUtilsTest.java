package gov.nih.nci.cabig.caaers.utils;

import gov.nih.nci.cabig.caaers.domain.Grade;
import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class CodedEnumUtilsTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testCompare() {
		assertTrue(CodedEnumUtils.compare(Grade.DEATH, Grade.MILD) < 0);
		assertTrue(CodedEnumUtils.compare(Grade.NORMAL, Grade.DEATH ) > 0);
		assertTrue(CodedEnumUtils.compare(Grade.MILD, Grade.MILD) == 0);
		
		assertEquals(0, CodedEnumUtils.compare(null, null));
		assertEquals(-1, CodedEnumUtils.compare(Grade.DEATH, null));
		assertEquals(1, CodedEnumUtils.compare(null, Grade.DEATH));
	}

}
