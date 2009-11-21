package gov.nih.nci.cabig.caaers.web.user;

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
	 
	protected void setUp() throws Exception {
		super.setUp();
		controller = new ChangePasswordController();
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
