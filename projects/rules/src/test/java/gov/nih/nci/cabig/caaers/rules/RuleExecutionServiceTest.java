package gov.nih.nci.cabig.caaers.rules;

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
		String bindUri = "URI_1";
		bindUri = "gov.nih.nci.cabig.caaers.rules";
		List inObjects = new ArrayList();
		StudySDO study = new StudySDO();
		study.setPrimarySponsorCode("SC_1");
		inObjects.add(study);
		this.ruleExecutionServiceImpl.fireRules(bindUri, study, inObjects);
	}
}