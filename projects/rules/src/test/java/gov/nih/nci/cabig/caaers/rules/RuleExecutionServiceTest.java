package gov.nih.nci.cabig.caaers.rules;

import gov.nih.nci.cabig.caaers.rules.domain.AdverseEventSDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionServiceImpl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
public class RuleExecutionServiceTest extends TestCase {

	private RuleExecutionService ruleExecutionServiceImpl;

	protected void setUp() throws Exception {
		super.setUp();
		this.ruleExecutionServiceImpl = new RuleExecutionServiceImpl();
	}
	
	public void testExecuteRule() throws Exception {
		String bindUri = "CAAERS_AE_RULES";
		List inObjects = new ArrayList();
		
		//create the adverse event object
		AdverseEventSDO adverseEvent = new AdverseEventSDO();
		//adverseEvent.setGrade("NORMAL");
		
		StudySDO study = new StudySDO();
		study.setShortTitle("Rule Study");
		study.setPrimarySponsorCode("SC_1");
		inObjects.add(adverseEvent);
		this.ruleExecutionServiceImpl.fireRules(bindUri, study, inObjects);
	}
}