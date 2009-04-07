package gov.nih.nci.cabig.caaers.validator;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.Investigator;
import gov.nih.nci.cabig.caaers.domain.LocalInvestigator;
import gov.nih.nci.cabig.caaers.validation.validator.DomainObjectValidator;

import java.util.ArrayList;
import java.util.List;

public class InvestigatorDomainObjectValidatorTest  extends CaaersTestCase{
	
	Investigator investigator;
	List<String> errors;
	DomainObjectValidator domainObjectValidator;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		investigator = new LocalInvestigator();
		errors = new ArrayList<String>();
		domainObjectValidator = (DomainObjectValidator)getDeployedApplicationContext().getBean("domainObjectValidator");
	}

	public void testInvestigatorValid(){
		investigator.setFirstName("John");
		investigator.setLastName("Doe");
		investigator.setEmailAddress("john.doe@semanticbits.com");
		investigator.setNciIdentifier("JON-D1");
		investigator.setPhoneNumber("121-111-1211");
		investigator.setFaxNumber("121-111-1212");
		
		errors = domainObjectValidator.validate(investigator);
		
		assertEquals(0, errors.size());
	}
}
