package gov.nih.nci.cabig.caaers.web.utils;

import edu.emory.mathcs.backport.java.util.Arrays;
import gov.nih.nci.cabig.caaers.domain.ActiveInactiveStatus;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.Participant;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.security.SecurityTestUtils;
import gov.nih.nci.cabig.caaers.validation.fields.validators.FieldValidator;
import gov.nih.nci.cabig.caaers.web.WebTestCase;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.easymock.EasyMock;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class WebUtilsTest extends WebTestCase{
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		SecurityTestUtils.switchToSuperuser();
	}
	
	
	public void testCollectOptions() throws Exception{
		
		Map<Object,Object> enumOptions = WebUtils.collectOptions(ActiveInactiveStatus.values(), "Please Select");
		
		assertEquals("Unexpected wrapped value",enumOptions.get(""),"Please Select");
		assertEquals("Unexpected wrapped value",enumOptions.get("AC"),"Ac");
		assertEquals("Unexpected wrapped value",enumOptions.get("IN"),"In");
	}
	
	public void testCollectOptions1() throws Exception{
		
		// test for wrapping null
		BeanWrapper nullWrapper = new BeanWrapperImpl();
		assertNull(nullWrapper.getWrappedInstance());
		
		Study study = Fixtures.createStudy("Test Study");
		Collection<Object> options = Arrays.asList(new Object[]{study});
		Map<Object,Object> objectOptions = WebUtils.collectOptions(options, "shortTitle", "longTitle", null);
		assertEquals((String)objectOptions.get("Test Study"),"Test Study");
		
	}
	
	
	public void testCollectOptions2() throws Exception{
		
		Study study = Fixtures.createStudy("Test Study");
		Participant participant = Fixtures.createParticipant("John", "Doe");
		Organization organization = Fixtures.createOrganization("Cancer Clinic");
		StudyParticipantAssignment spa = Fixtures.assignParticipant(participant, study, organization);
		Collection<Object> options = Arrays.asList(new Object[]{spa});
		
		Map<Object,Object> objectOptions = WebUtils.collectCustomOptions(options, "participant.fullName", "participant.firstName", "participant.lastName", ".");
		assertEquals((String)objectOptions.get("John Doe"),"John.Doe");
		
		objectOptions = WebUtils.collectCustomOptions(options, "participant.fullName", "participant.firstName", "participant.lastName", null);
		assertEquals((String)objectOptions.get("John Doe"),"John-Doe");
	}
	
	
	public void testGetPreviousPage() throws Exception{
		Integer pageNo = WebUtils.getPreviousPage(request);
		assertEquals(new Integer(-1),pageNo);
		
		request.setParameter("_page", "3");
		pageNo = WebUtils.getPreviousPage(request);
		assertEquals(new Integer(3),pageNo);
	}
	
	public void testGetTargetPage() throws Exception{
		request.setParameter("_target4", "_target4");
		Integer target = WebUtils.getTargetPage(request);
		assertEquals(new Integer(4),target);
	}
	
	public void testGetIntParameters() throws Exception{
		request.setParameter("intParam", "1");
		int[] intParams = WebUtils.getIntParameters(request, "intParam") ;
		assertEquals(1,intParams.length);
		assertEquals(1,intParams[0]);
	}
	
	public void testGetIntParameter() throws Exception{
		request.setParameter("intParam", "1");
		Integer intParam = WebUtils.getIntParameter(request, "intParam") ;
		assertNotNull(intParam);
		assertEquals(new Integer(1),intParam);
	}
	
	public void testGetStringParameter() throws Exception{
		request.setParameter("stringParam", "value1");
		String stringParam = WebUtils.getStringParameter(request, "stringParam") ;
		assertNotNull(stringParam);
		assertEquals("value1",stringParam);
	}
	
	public void testHasParameter() throws Exception{
		request.setParameter("stringParam", "value1");
		assertTrue(WebUtils.hasParameter(request, "stringParam")) ;
	}
	
	public void testRequiredValidator() throws Exception {
		FieldValidator[] validators = new FieldValidator[] {FieldValidator.DATE_VALIDATOR, FieldValidator.TEXTSIZE_VALIDATOR};
		assertNotNull (WebUtils.getRequiredValidator(validators, FieldValidator.TEXTSIZE_VALIDATOR));
		
	}

}
