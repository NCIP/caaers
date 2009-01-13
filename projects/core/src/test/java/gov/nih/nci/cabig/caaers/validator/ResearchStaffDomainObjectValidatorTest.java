package gov.nih.nci.cabig.caaers.validator;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Organization;
import gov.nih.nci.cabig.caaers.domain.ResearchStaff;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;

import java.util.ArrayList;
import java.util.List;

public class ResearchStaffDomainObjectValidatorTest extends CaaersDbTestCase{
	ResearchStaff rStaff;
	Organization organization;
	List<String> errors;
	DomainObjectValidator domainObjectValidator;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		rStaff = new ResearchStaff();
		organization = Fixtures.createOrganization("NCI");
		errors = new ArrayList<String>();
		domainObjectValidator = (DomainObjectValidator)getDeployedApplicationContext().getBean("domainObjectValidator");
	}
	
	public void testResearchStaffValid(){
		
		rStaff.setFirstName("John");
		rStaff.setLastName("Doe");
		rStaff.setEmailAddress("john.doe@semanticbits.com");
		rStaff.setPhoneNumber("111-111-1111");
		rStaff.setFaxNumber("111-111-1112");
		rStaff.setNciIdentifier("JOHN-D2");
		rStaff.setOrganization(organization);
		
		errors = domainObjectValidator.validate(rStaff);
		
		assertEquals(0,errors.size());
	}
	
	

}
