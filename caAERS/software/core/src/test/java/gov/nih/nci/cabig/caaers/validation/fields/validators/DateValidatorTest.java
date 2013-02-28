/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.validation.fields.validators;

import gov.nih.nci.cabig.caaers.utils.DateUtils;
import gov.nih.nci.cabig.caaers.validation.fields.validators.DateValidator;
import junit.framework.TestCase;
/**
 * 
 * @author Biju Joseph
 *
 */
public class DateValidatorTest extends TestCase {
	DateValidator validator ;
	protected void setUp() throws Exception {
		super.setUp();
		validator = new DateValidator();
	}

	public void testIsValid() {
		assertTrue(validator.isValid(null));
		assertTrue(validator.isValid(DateUtils.parseDateString("2008")));
		assertTrue(validator.isValid(DateUtils.parseDateString("02/02/2002").toDate()));
		
	}

	public void testGetMessagePrefix() {
		assertEquals("Invalid date value",validator.getMessagePrefix());
	}

	public void testGetValidatorCSSClassName() {
		assertEquals("DATE",validator.getValidatorCSSClassName());
	}

}
