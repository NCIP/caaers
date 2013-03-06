/*******************************************************************************
 * Copyright SemanticBits, Northwestern University and Akaza Research
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/caaers/LICENSE.txt for details.
 ******************************************************************************/
package gov.nih.nci.cabig.caaers.web.user;

import gov.nih.nci.cabig.caaers.service.security.passwordpolicy.PasswordPolicyService;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import gov.nih.nci.cabig.caaers.web.WebTestCase;
import gov.nih.nci.cabig.caaers.web.user.ChangePasswordController.ChangePasswordCommand;
/**
 * 
 * @author Biju Joseph
 *
 */
public class ChangePasswordControllerTest extends WebTestCase {
	ChangePasswordController controller;
	 protected Errors errors;
	 ChangePasswordCommand command;
    PasswordPolicyService passwordPolicyService;
	 
	protected void setUp() throws Exception {
		super.setUp();
		controller = new ChangePasswordController();
        passwordPolicyService = registerMockFor(PasswordPolicyService.class);
        controller.setPasswordPolicyService(passwordPolicyService);
		command = (ChangePasswordCommand) controller.formBackingObject(request);
		
		errors = new BindException(command, "command");
	}

	public void testOnBindAndValidateHttpServletRequestObjectBindException() throws Exception {
		command.setPasswordNew("abcd");
		command.setPasswordConfirm("abcd");
		command.setUserName("hijk");
		controller.onBindAndValidate(request, command, (BindException) errors);
		assertFalse(errors.hasErrors());
	}
	
	public void testOnBindAndValidate_ThrowingException() throws Exception {
		command.setPasswordNew("abcd");
		command.setPasswordConfirm("abcd2");
		command.setUserName("hijk");
		controller.onBindAndValidate(request, command, (BindException) errors);
		assertTrue(errors.hasErrors());
		assertEquals(1,errors.getFieldErrorCount("passwordConfirm"));
	}

}
