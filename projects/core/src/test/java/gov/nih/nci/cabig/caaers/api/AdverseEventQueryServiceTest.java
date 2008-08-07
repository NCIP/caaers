package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.CaaersDbTestCase;
import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.domain.Participant;

import java.util.List;

public class AdverseEventQueryServiceTest extends CaaersDbTestCase {

	private AdverseEventQueryService svc = null;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		svc = (AdverseEventQueryService)getDeployedApplicationContext().getBean("adverseEventQueryService");
	}
	
	public void testGetByParticipant() {
		Participant participant = new Participant();
		participant.setFirstName("Dilbert");
		List<AdverseEvent> aes = svc.getByParticipant(participant);		
		assertEquals(2, aes.size());
		
		participant = new Participant();
		participant.setFirstName("John");
		aes = svc.getByParticipant(participant);
		assertEquals(0, aes.size());		
	}
	
	public void testGetByParticipantAdverseEvent() {
		Participant participant = new Participant();
		participant.setFirstName("Dilbert");
		AdverseEvent ae = new AdverseEvent();
		ae.setGrade(Grade.DEATH);	
		List<AdverseEvent> aes = svc.getByParticipant(participant,ae);		
		assertEquals(1, aes.size());
		
		AdverseEvent resultAe = aes.get(0);
		assertEquals(-3, resultAe.getId().intValue());
		
		ae = new AdverseEvent();
		ae.setGrade(Grade.SEVERE);
		ae.setHospitalization(Hospitalization.YES);
		aes = svc.getByParticipant(participant,ae);	
		assertEquals(1, aes.size());
		
		resultAe = aes.get(0);
		assertEquals(-2, resultAe.getId().intValue());
		
		participant = new Participant();
		participant.setFirstName("Dilbert");
		participant.setLastName("Akkala");
		aes = svc.getByParticipant(participant,ae);
		assertEquals(0, aes.size());
	}	
/*
	public void testGetByStudy() {
		Study study = new Study();
		study.setShortTitle("Short Title");
		List<AdverseEvent> aes = svc.getByStudy(study);		
		assertEquals(2, aes.size());
		
		study = new Study();
		study.setShortTitle("Not Short Title");
		aes = svc.getByStudy(study);	
		assertEquals(0, aes.size());		
	}
	public void atestGet() {
		try {
			
			Identifier participantIdentifier = new Identifier();
			participantIdentifier.setValue("NCB438");
			participantIdentifier.setPrimaryIndicator(true);
			
			Participant participant = new Participant();
			participant.setFirstName("Chris");
			participant.setLastName("Wilson");
			DateValue dv = new DateValue();
			dv.setMonth(12);
			dv.setYear(1956);
			participant.setDateOfBirth(dv);
			//participant.addIdentifier(participantIdentifier);
			
			AdverseEvent ae = new AdverseEvent();
			ae.setGrade(Grade.SEVERE);
			ae.setSolicited(true);
			//ae.setHospitalization(Hospitalization.YES);
			
			//List<AdverseEvent> l = svc.getByParticipant(participant);
			
			Study study = new Study();
			study.setShortTitle("% study oct July%");
			

			
			List<AdverseEvent> l = svc.getByStudy(study);
			
			//for (AdverseEvent ae1:l) {
				System.out.println(svc.getXML(l));
			//}
			System.out.println(l.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
}
