/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.service.migrator.adverseevent;

import gov.nih.nci.cabig.caaers.AbstractTestCase;
import gov.nih.nci.cabig.caaers.domain.Reporter;
import gov.nih.nci.cabig.caaers.domain.report.ReportVersion;
import gov.nih.nci.cabig.caaers.utils.CaaersUtils;
import gov.nih.nci.cabig.caaers.validation.fields.validators.EmailValidator;
import gov.nih.nci.cabig.caaers.validation.fields.validators.MultiEmailValidator;

/**
 * 
 * @author Ramakrishna Gundala
 *
 */

public class ExpeditedAdverseEventReportConverterUtilityTest extends AbstractTestCase{
	
	EmailValidator ev = new EmailValidator();
	MultiEmailValidator mv = new MultiEmailValidator();
	
	public void testMultipleEmailsWithSemiColon(){
		String emails = "abc@def.com; xys@ere.com,are@were.com ";
    	String replacedEmailString = emails.replace(";", ",").replace(" ", "");
    	assertFalse(replacedEmailString.contains(";"));
	}
	
	public void testReporterAlternateEmails() throws Exception {
		
		String emailString = "abc@def.com; xys@ere.com  , are@were.com ";
		
		Reporter reporter = new Reporter();
		reporter.setAlternateEmailAddress(emailString);
		
		if(!mv.isValid(reporter.getAlternateEmailAddress())) {
			fail("Invalid multiple email string");
		}
		
		assertTrue(reporter.getAlternateEmailAddress().contains(" "));
		assertTrue(reporter.getAlternateEmailAddress().contains(";"));
    	reporter.setAlternateEmailAddress(CaaersUtils.getEmailStringWithoutSemiColonsAndSpaces(
    			reporter.getAlternateEmailAddress()));
    	assertFalse(reporter.getAlternateEmailAddress().contains(";"));
    	assertFalse(reporter.getAlternateEmailAddress().contains(" "));
    	
    	String[] emailArray = reporter.getAlternateEmailAddress().split("[,]");
    	assertEquals("Wrong number of emails ", 3, emailArray.length);
    	for(String email : emailArray){
    		assertTrue(ev.isValid(email));
    	}
	}
	
	public void testReportVersionCcEmails() throws Exception {
		
		String inValidEmailString = ",abc@def.com; xys@ere.com  , are@were.com ";
		ReportVersion reportVersion = new ReportVersion();
		reportVersion.setCcEmails(inValidEmailString);
		
		if(mv.isValid(reportVersion.getCcEmails())) {
			fail("Invalid multiple email string");
		}
		
		inValidEmailString = "abc@def.com; xys@ere.com  , are@were.com ;";
		reportVersion.setCcEmails(inValidEmailString);
		if(mv.isValid(reportVersion.getCcEmails())) {
			fail("Invalid multiple email string");
		}
		
		String validEmailString = "abc@def.com; xys@ere.com  , are@were.com ";
		reportVersion.setCcEmails(validEmailString);
		
		assertTrue(reportVersion.getCcEmails().contains(" "));
		assertTrue(reportVersion.getCcEmails().contains(";"));
		reportVersion.setCcEmails(CaaersUtils.getEmailStringWithoutSemiColonsAndSpaces(
				reportVersion.getCcEmails()));
    	assertFalse(reportVersion.getCcEmails().contains(";"));
    	assertFalse(reportVersion.getCcEmails().contains(" "));
    	
    	String[] emailArray = reportVersion.getCcEmails().split("[,]");
    	assertEquals("Wrong number of emails ", 3, emailArray.length);
    	for(String email : emailArray){
    		assertTrue(ev.isValid(email));
    	}
	}
	
}
