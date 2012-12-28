package gov.nih.nci.cabig.caaers.api.impl;

import gov.nih.nci.cabig.caaers.CaaersDbNoSecurityTestCase;
import gov.nih.nci.cabig.caaers.api.impl.SAEEvaluationServiceImpl;
import gov.nih.nci.cabig.caaers.domain.Fixtures;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.StudyParticipantAssignment;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.AdverseEventType;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.AdverseEvents;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.OutComeEnumType;
import gov.nih.nci.cabig.caaers.integration.schema.saerules.OutcomeType;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SAEEvaluationServiceTest extends CaaersDbNoSecurityTestCase {
	
	private SAEEvaluationServiceImpl SAEEvaluationService = null;
	
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        SAEEvaluationService = (SAEEvaluationServiceImpl)getApplicationContext().getBean("SAEEvaluationServiceImpl");
    }
    
	public void testSAERules() throws Exception{
		
		// Adverse Event 
		AdverseEventType ae = new AdverseEventType();
		ae.setId(BigInteger.ONE);
		ae.setGrade(Grade.DEATH.getCode());
		List<OutcomeType> listOutcomes = new ArrayList<OutcomeType>();
		
		OutcomeType type = new OutcomeType();
		type.setOutComeEnumType(OutComeEnumType.DEATH);
		type.setOther(null);
		
		listOutcomes.add(type);
		ae.setOutcome(listOutcomes);
		
		
		List<AdverseEventType> aes = new ArrayList<AdverseEventType>();
		aes.add(ae);
		
		AdverseEvents event = new AdverseEvents();
		event.setAdverseEvent(aes);
				
		// Study Assignment
		StudyParticipantAssignment assignment = new StudyParticipantAssignment();
		assignment.setStudySite(Fixtures.createStudySite(Fixtures.createOrganization("Mayo Clinic"), 1));
		assignment.setId(1);
		
		SAEEvaluationService.processAdverseEvents("12345-ABC", event, assignment, "TAC1");
		
	}

}
