package gov.nih.nci.cabig.caaers.rules;

import gov.nih.nci.cabig.caaers.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.domain.Grade;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringServiceImpl;
import gov.nih.nci.cabig.caaers.rules.business.service.AdverseEventEvaluationService;
import gov.nih.nci.cabig.caaers.rules.business.service.AdverseEventEvaluationServiceImpl;
import junit.framework.TestCase;

public class AdverseEventEvaluationServiceTest extends TestCase{
	
	private AdverseEventEvaluationService adverseEventEvaluationService;
	
	protected void setUp() throws Exception {
		super.setUp();
		this.adverseEventEvaluationService = new AdverseEventEvaluationServiceImpl();
	}
	
	public void testRoutineAE() throws Exception{
		AdverseEvent ae = new AdverseEvent();
		ae.setGrade(Grade.MILD);
		Study study = new Study();
		study.setPrimarySponsorCode("OUR_DREAM_SPONSOR");
		study.setShortTitle("MY_STUDY");
		String str = adverseEventEvaluationService.assesAdverseEvent(ae, study);
		System.out.println(str);
		
		
	}

}
