/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.validation.fields.validators.EmailValidator;

/**
 * 
 * @author Ramakrishna Gundala
 *
 */

public class ExpeditedAdverseEventReportConverterUtilityTest extends AbstractTestCase{
	
	EmailValidator ev = new EmailValidator();
	
	public void testMultipleEmailsWithSemiColon(){
		String emails = "abc@def.com; xys@ere.com,are@were.com ";
    	String replacedEmailString = emails.replace(";", ",").replace(" ", "");
    	assertFalse(replacedEmailString.contains(";"));
	}
	
	public void testMultipleEmailsWithWhiteSpaces(){
		String emails = "abc@def.com; xys@ere.com  , are@were.com ";
		assertTrue(emails.contains(" "));
    	String replacedEmailString = emails.replace(";", ",").replace(" ", "");
    	assertFalse(replacedEmailString.contains(";"));
    	assertFalse(replacedEmailString.contains(" "));
    	
    	String[] emailArray = replacedEmailString.split("[,]");
    	assertEquals("Wrong number of emails ", 3, emailArray.length);
    	for(String email : emailArray){
    		assertTrue(ev.isValid(email));
    	}
	}
	
}
