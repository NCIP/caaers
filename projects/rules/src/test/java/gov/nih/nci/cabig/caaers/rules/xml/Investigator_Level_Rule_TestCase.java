package gov.nih.nci.cabig.caaers.rules.xml;


import gov.nih.nci.cabig.caaers.rules.domain.AdverseEvent;
import gov.nih.nci.cabig.caaers.rules.domain.CtcTerm;
import gov.nih.nci.cabig.caaers.rules.domain.Grade;
import gov.nih.nci.cabig.caaers.rules.domain.Hospitalization;
import gov.nih.nci.cabig.caaers.rules.domain.Site;
import gov.nih.nci.cabig.caaers.rules.domain.StudySDO;
import gov.nih.nci.cabig.caaers.rules.domain.StudySite;
import gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringServiceImpl;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionService;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.rules.StatelessRuleSession;

import junit.framework.TestCase;

/**
 * The test class for testing the rules defined at investigator level.
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class Investigator_Level_Rule_TestCase extends TestCase {

	private RuleExecutionService ruleExecutionService;
	
	private RuleAuthoringServiceImpl ruleAuthoringService;


	protected void setUp() throws Exception {
		super.setUp();
		this.ruleAuthoringService = new RuleAuthoringServiceImpl();
		this.ruleExecutionService = new RuleExecutionServiceImpl();
	}

	
    public void testSponsorId_Rule_xml() throws Exception {

    	String bindUri_xml = "/xml/ae_sponsorId.xml";
		final Map<String, Object> map_xml = new HashMap<String, Object>();
		map_xml.put("source", "xml");
		this.ruleAuthoringService.addRuleExecutionSet(bindUri_xml+"SS",
				Investigator_Level_Rule_TestCase.class.getResourceAsStream(bindUri_xml), map_xml);
    	
    	final Map properties = new HashMap();
        final List inObjects = new ArrayList<Object>();

        final StudySDO study = getStudy();
        study.setPrimarySponsorCode("SC_1");
        inObjects.add(study);
        // execute the rules
        this.ruleExecutionService.fireRules( bindUri_xml+"SS", study, inObjects );

/*        assertEquals( "incorrect size",
                      1,
                      outList.size() );
        assertContains( outList, study );*/
    }

    public void testSponsorId_Rule_Eval_xml() throws Exception {

    	String bindUri_xml = "/ae_sponsorId_eval.xml";
		final Map<String, Object> map_xml = new HashMap<String, Object>();
		map_xml.put("source", "xml");
		this.ruleAuthoringService.addRuleExecutionSet(bindUri_xml+"SS",
				Investigator_Level_Rule_TestCase.class.getResourceAsStream(bindUri_xml), map_xml);
    	
    	final Map properties = new HashMap();
        final List inObjects = new ArrayList();

        final StudySDO study = getStudy();
        study.setPrimarySponsorCode("SC_1");
        inObjects.add(study);
        // execute the rules
        this.ruleExecutionService.fireRules( bindUri_xml+"SS", study, inObjects );

/*        assertEquals( "incorrect size",
                      1,
                      outList.size() );
        assertContains( outList, study );
*/
    }	

    
    
	public void testSponsorId_AND_ProtocolId_Rule_xml() throws Exception {

		String bindUri_xml = "/ae_sponsorId_AND_protocolId.xml";
		final Map<String, Object> map_xml = new HashMap<String, Object>();
		map_xml.put("source", "xml");
		this.ruleAuthoringService.addRuleExecutionSet(bindUri_xml,
				Investigator_Level_Rule_TestCase.class.getResourceAsStream(bindUri_xml), map_xml);

		final Map properties = new HashMap();
		final List inObjects = new ArrayList<Object>();

		final StudySDO study = getStudy();
		study.setPrimarySponsorCode("SC_1");
		//study.setId(new Integer(100));
		inObjects.add(study);
		// execute the rules
        this.ruleExecutionService.fireRules( bindUri_xml+"SS", study, inObjects );
/*
		assertEquals("incorrect size", 1, outList.size());
		assertContains(outList, study);
*/	
    }    
    

    

	public void testSponsorId_OR_ProtocolId_Rule_xml() throws Exception {

		String bindUri_xml = "/ae_sponsorId_OR_protocolId.xml";
		final Map<String, Object> map_xml = new HashMap<String, Object>();
		map_xml.put("source", "xml");
		this.ruleAuthoringService.addRuleExecutionSet(bindUri_xml,
				Investigator_Level_Rule_TestCase.class.getResourceAsStream(bindUri_xml), map_xml);

		final Map properties = new HashMap();
		final List inObjects = new ArrayList<Object>();

		final StudySDO study = getStudy();
		study.setPrimarySponsorCode("SC_3");
		inObjects.add(study);
		// execute the rules
        this.ruleExecutionService.fireRules( bindUri_xml+"SS", study, inObjects );

/*		assertEquals("incorrect size", 1, outList.size());
		assertContains(outList, study);
	*/
	}	    


	public void testAEGrade_GT_Rule_xml() throws Exception {

		String bindUri_xml = "/ae_grade_GT.xml";
		final Map<String, Object> map_xml = new HashMap<String, Object>();
		map_xml.put("source", "xml");
		this.ruleAuthoringService.addRuleExecutionSet(bindUri_xml,
				Investigator_Level_Rule_TestCase.class.getResourceAsStream(bindUri_xml), map_xml);

		final Map properties = new HashMap();

		final List<AdverseEvent> inObjects = new ArrayList<AdverseEvent>();

		final AdverseEvent adverseEvent = getAdverseEvent();
		adverseEvent.setGrade(Grade.MODERATE);
		inObjects.add(adverseEvent);
		
		// execute the rules
/*
TO DOO		
		final List outList = this.ruleExecutionService.fireRules( bindUri_xml+"SS", inObjects );

		assertEquals("incorrect size", 1, outList.size());
		assertContains(outList, adverseEvent);
*/
		
	}	    

	

	public void testAETerm_AND_AEHospitalization_OR_SponsorId_Rule_xml() throws Exception {

		String bindUri_xml = "/aeTerm_AND_Hospitalization_OR_sponsorId.xml";
		final Map<String, Object> map_xml = new HashMap<String, Object>();
		map_xml.put("source", "xml");
		this.ruleAuthoringService.addRuleExecutionSet(bindUri_xml,
				Investigator_Level_Rule_TestCase.class.getResourceAsStream(bindUri_xml), map_xml);

		final Map properties = new HashMap();

		final List<Object> inObjects = new ArrayList<Object>();

		final StudySDO study = getStudy();
		study.setPrimarySponsorCode("SC_1");
		inObjects.add(study);
		
		final AdverseEvent adverseEvent = getAdverseEvent();
		adverseEvent.getCtcTerm().setTerm("Test_CtCTerm");
		adverseEvent.setHospitalization(Hospitalization.PROLONGED_HOSPITALIZATION);
		inObjects.add(adverseEvent);
		
		// execute the rules
/*
TO DOO
		final List outList = this.ruleExecutionService.fireRules( bindUri_xml+"SS", inObjects );

		assertEquals("incorrect size", 2, outList.size());
		assertContains(outList, study);*/
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
 //       study.getStudySite().add(studySite);
    	return study;
    }
    
    private AdverseEvent getAdverseEvent() {
    	AdverseEvent adverseEvent = new AdverseEvent();
    	CtcTerm ctcTerm = new CtcTerm();
    	adverseEvent.setCtcTerm(ctcTerm);
    	return adverseEvent;
    }
  
}
