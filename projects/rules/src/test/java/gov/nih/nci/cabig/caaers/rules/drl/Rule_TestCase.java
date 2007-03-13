
package gov.nih.nci.cabig.caaers.rules.drl;

import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringServiceImpl;
import gov.nih.nci.cabig.caaers.rules.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.rules.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.rules.domain.Site;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySite;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionServiceImpl;
import gov.nih.nci.cabig.caaers.rules.xml.Investigator_Level_Rule_TestCase;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;


public class Rule_TestCase extends TestCase {

	private RuleExecutionService ruleExecutionService;
	
	private RuleAuthoringServiceImpl ruleAuthoringService;

	private static BufferedReader reader = new BufferedReader(new
			java.io.InputStreamReader(System.in));

	protected void setUp() throws Exception {
		super.setUp();
		this.ruleAuthoringService = new RuleAuthoringServiceImpl();
		this.ruleExecutionService = new RuleExecutionServiceImpl();
	}
	
    public void testSponsorId_Rule() throws Exception {

    	String bindUri_xml = "/drl/ae.drl";
		final Map<String, Object> map_xml = new HashMap<String, Object>();
//		map_xml.put("source", "xml");
		this.ruleAuthoringService.addRuleExecutionSet(bindUri_xml+"SS",
				Investigator_Level_Rule_TestCase.class.getResourceAsStream(bindUri_xml), map_xml);
    	
    	final Map properties = new HashMap();
        final List inObjects = new ArrayList();

        final StudySDO study = getStudy();
        study.setPrimarySponsorCode("SC_1");
        inObjects.add(study);
        // execute the rules
        this.ruleExecutionService.fireRules( bindUri_xml+"SS", study, inObjects);

    }

    public void testSponsorId_Eval_Rule() throws Exception {

    	String bindUri_xml = "/drl/ae_Eval.drl";
		final Map<String, Object> map_xml = new HashMap<String, Object>();
//		map_xml.put("source", "xml");
		this.ruleAuthoringService.addRuleExecutionSet(bindUri_xml+"SS",
				Investigator_Level_Rule_TestCase.class.getResourceAsStream(bindUri_xml), map_xml);
    	
    	final Map properties = new HashMap();
        final List inObjects = new ArrayList();

        final StudySDO study = getStudy();
        study.setPrimarySponsorCode("SC_1");
        inObjects.add(study);
        // execute the rules
        this.ruleExecutionService.fireRules( bindUri_xml+"SS", study, inObjects);
    }
    
    protected void assertContains(final List expected, final Object object) {
		if (expected.contains(object)) {
			return;
		}
		fail(object + " not in " + expected);
	}
    
    private StudySDO getStudy() {
        final StudySDO study = new StudySDO();
        StudySite studySite = new StudySite();
        Site site = new Site();
        studySite.setSite(site);
  //      study.getStudySite().add(studySite);
    	return study;
    }

    private AdverseEvent getAdverseEvent() {
    	AdverseEvent adverseEvent = new AdverseEvent();
    	CtcTerm ctcTerm = new CtcTerm();
    	adverseEvent.setCtcTerm(ctcTerm);
    	return adverseEvent;
    }
	
    
}
