
package gov.nih.nci.cabig.caaers.validation.fields.validators;

import junit.framework.TestCase;

/**
 * Test for multi email and single email validators.
 * @author Dirk Walter
 *
 */
public class MultiEmailValidatorTest extends TestCase {
	EmailValidator ev;
	MultiEmailValidator mev;

	protected void setUp() throws Exception {
		super.setUp();
		ev = new EmailValidator();
		mev = new MultiEmailValidator();

	}

	public void testIsValid() {
		assertTrue(ev.isValid("test@test.com"));
		assertTrue(mev.isValid("test@test.com"));
		
		assertFalse(ev.isValid("test1@test.com,test2@test.com"));
		assertTrue(mev.isValid("test1@test.com,test2@test.com"));
		
		assertFalse(ev.isValid("abc.com"));
		assertFalse(mev.isValid("abc.com"));
		
		assertFalse(ev.isValid("@abc.com"));
		assertFalse(mev.isValid("@abc.com"));
		
		assertFalse(ev.isValid("@abc.com,"));
		assertFalse(mev.isValid("@abc.com,"));
		
		assertTrue(ev.isValid("cool@abc.com"));
		assertTrue(mev.isValid("cool@abc.com"));
		
		assertFalse(ev.isValid("cool@abc.com,"));
		assertFalse(mev.isValid("cool@abc.com,"));
		
		assertFalse(ev.isValid(",cool@abc.com,"));
		assertFalse(mev.isValid(",cool@abc.com,"));
		
		assertFalse(ev.isValid(",cool@abc.com"));
		assertFalse(mev.isValid(",cool@abc.com"));
	}

	public void testGetMessagePrefix() {
		assertEquals("Invalid", ev.getMessagePrefix());
		assertEquals("Invalid", mev.getMessagePrefix());
	}

	public void testGetValidatorCSSClassName() {
		assertEquals("EMAIL", ev.getValidatorCSSClassName());
		assertEquals("MULTI_EMAIL", mev.getValidatorCSSClassName());
	}
}
