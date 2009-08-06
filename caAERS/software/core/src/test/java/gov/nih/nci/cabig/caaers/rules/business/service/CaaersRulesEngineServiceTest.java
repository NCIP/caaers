package gov.nih.nci.cabig.caaers.rules.business.service;

import edu.nwu.bioinformatics.commons.ResourceRetriever;
import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.semanticbits.rules.api.RepositoryService;
import com.semanticbits.rules.api.RulesEngineService;
import com.semanticbits.rules.brxml.Rule;
import com.semanticbits.rules.brxml.RuleSet;
import com.semanticbits.rules.impl.RuleServiceContext;
import com.semanticbits.rules.utils.RepositoryCleaner;
import com.semanticbits.rules.utils.RulesPropertiesFileLoader;

/**
 * This is a unit TestCase for RulesEngineServiceImpl class.
 * @author Monish Dombla
 *
 */
public class CaaersRulesEngineServiceTest extends CaaersTestCase{
	
	private CaaersRulesEngineService caaersRulesEngineService;
	private RulesEngineService rulesEngineService;
	private RepositoryService repositoryService;
	private JAXBContext jaxbContext = null;
    private Unmarshaller unmarshaller = null;
    private RuleSet ruleSet;
    RulesPropertiesFileLoader propertiesBean;
	
    private InputStream createInputStream(String testDataFileName) throws FileNotFoundException {
        InputStream testDataStream = ResourceRetriever.getResource(getClass().getPackage(), testDataFileName);
        return testDataStream;
    }
    
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		propertiesBean = RulesPropertiesFileLoader.getLoadedInstance();
        String url = propertiesBean.getProperties().getProperty("rules.repository");
        new RepositoryCleaner(url);
        
		caaersRulesEngineService = (CaaersRulesEngineService)getDeployedApplicationContext().getBean("caaersRulesEngineService");
		rulesEngineService = (RulesEngineService)getDeployedApplicationContext().getBean("ruleEngineService");
		repositoryService = (RepositoryService)getDeployedApplicationContext().getBean("jcrService");
		jaxbContext = JAXBContext.newInstance("com.semanticbits.rules.brxml");
		unmarshaller = jaxbContext.createUnmarshaller();
		InputStream xmlInputDataStream = createInputStream("test_sponsor_ctep_rules.xml");
		if(xmlInputDataStream != null){
			ruleSet = (RuleSet) unmarshaller.unmarshal(xmlInputDataStream);
		}
		List<RuleSet> ruleSetListBeforeDelete = rulesEngineService.getAllRuleSets();
		for (RuleSet ruleSet : ruleSetListBeforeDelete){
			rulesEngineService.deleteRuleSet(ruleSet.getName());
		}
		List<RuleSet> ruleSetListAfterDelete = rulesEngineService.getAllRuleSets();
		assertEquals(1,ruleSetListAfterDelete.size());
	}
	
	/**
	 * Tests method createRuleForSponsor(...) in RulesEngineServiceImpl
	 * New RuleSet and New Rule
	 */
	public void testCreateRuleForSponsor(){
		
        if(ruleSet != null){
        	try{
        		String ruleSetName = "SemanticBitsRules";
        		String sponsorName = "National C I";
                String subject = "SemanticBits Rules";
                String state = "Draft";
                Rule ruleToCreate;
                ruleToCreate = ruleSet.getRule().get(0);
            	String uuid = caaersRulesEngineService.createRuleForSponsor(ruleToCreate, ruleSetName, sponsorName, subject, state);
            	assertNotNull(uuid);
            	assertNotEquals("", uuid);
            	
            	RuleSet createdRuleset = repositoryService.getRuleSet("gov.nih.nci.cabig.caaers.rules.sponsor.national_c_i.semanticbitsrules", false);
            	Rule createdRule = repositoryService.getRule(uuid);
            	
            	assertNotNull(createdRuleset);
            	assertEquals("gov.nih.nci.cabig.caaers.rules.sponsor.national_c_i.semanticbitsrules", createdRuleset.getName());
            	assertNotNull(createdRule);
            	
        	}catch(Exception e){
        		fail("No exceptions are expected " +e.getMessage());
        	}
        }
	}
	
	/**
	 * Tests method createRuleForSponsor(...) in RulesEngineServiceImpl
	 * Add New Rule to an existing RuleSet
	 */
	public void testCreateRuleForSponsorExistingRuleSet(){
		
        if(ruleSet != null){
        	try{
        		String ruleSetName = "SemanticBitsRules";
        		String sponsorName = "National C I";
                String subject = "SemanticBits Rules";
                String state = "Draft";
                Rule ruleToCreate;
                ruleToCreate = ruleSet.getRule().get(0);
            	String uuid = caaersRulesEngineService.createRuleForSponsor(ruleToCreate, ruleSetName, sponsorName, subject, state);
            	assertNotNull(uuid);
            	assertNotEquals("", uuid);
            	
            	RuleSet createdRuleset = repositoryService.getRuleSet("gov.nih.nci.cabig.caaers.rules.sponsor.national_c_i.semanticbitsrules", false);
            	Rule createdRule = repositoryService.getRule(uuid);
            	
            	assertNotNull(createdRuleset);
            	assertEquals("gov.nih.nci.cabig.caaers.rules.sponsor.national_c_i.semanticbitsrules", createdRuleset.getName());
            	assertNotNull(createdRule);
            	
            	Rule ruleToAdd = ruleSet.getRule().get(1);
            	uuid = caaersRulesEngineService.createRuleForSponsor(ruleToAdd, ruleSetName, sponsorName, subject, state);
            	
            	RuleSet existingRuleset = repositoryService.getRuleSet("gov.nih.nci.cabig.caaers.rules.sponsor.national_c_i.semanticbitsrules", false);
            	Rule addedRule = repositoryService.getRule(uuid);
            	
            	assertNotNull(existingRuleset);
            	assertNotNull(addedRule);
            	assertEquals(2, existingRuleset.getRule().size());
            	
        	}catch(Exception e){
        		fail("No exceptions are expected " +e.getMessage());
        	}
        }
	}
	
	/**
	 * Tests method createRuleForSponsorDefinedStudy(...) in RulesEngineServiceImpl
	 * New RuleSet and New Rule
	 */
	public void testCreateRuleForSponsorDefinedStudy(){
		
		if(ruleSet != null){
			try{
				Rule rule = ruleSet.getRule().get(1);
				String ruleSetName = "AstanaRules";
		        String studyShortTitle = "Study For LL";
		        String sponsorName = "Astana";
		        String subject = "RuleForSponsorDefinedStudy";
		        String state = "Draft";
		        
		        String uuid = caaersRulesEngineService.createRuleForSponsorDefinedStudy(rule, ruleSetName, studyShortTitle, sponsorName, subject, state);
		        
		        assertNotNull("uuid returned cannot be null", uuid);
		        assertNotEquals("", uuid);
		        
		        RuleSet createdRuleset = repositoryService.getRuleSet("gov.nih.nci.cabig.caaers.rules.sponsor.study.study_for_ll.astana.astanarules", false);
            	Rule createdRule = repositoryService.getRule(uuid);
            	
            	assertNotNull(createdRuleset);
            	assertEquals("gov.nih.nci.cabig.caaers.rules.sponsor.study.study_for_ll.astana.astanarules", createdRuleset.getName());
            	assertEquals(1, createdRuleset.getRule().size());
            	assertNotNull(createdRule);
		        
			}catch(Exception e){
				fail("No exceptions are expected");
			}
		}
	}
	/**
	 * Tests method createRuleForSponsorDefinedStudy(...) in RulesEngineServiceImpl
	 * Add New Rule to existing RuleSet
	 */
	public void testCreateRuleForSponsorDefinedStudyExistingRuleSet(){
		
		if(ruleSet != null){
			try{
				Rule rule = ruleSet.getRule().get(0);
				String ruleSetName = "AstanaRules";
		        String studyShortTitle = "Study For LL";
		        String sponsorName = "Astana";
		        String subject = "RuleForSponsorDefinedStudy";
		        String state = "Draft";
		        
		        String uuid = caaersRulesEngineService.createRuleForSponsorDefinedStudy(rule, ruleSetName, studyShortTitle, sponsorName, subject, state);
		        
		        assertNotNull("uuid returned cannot be null", uuid);
		        assertNotEquals("", uuid);
		        
		        RuleSet createdRuleset = repositoryService.getRuleSet("gov.nih.nci.cabig.caaers.rules.sponsor.study.study_for_ll.astana.astanarules", false);
            	Rule createdRule = repositoryService.getRule(uuid);
            	
            	assertNotNull(createdRuleset);
            	assertEquals("gov.nih.nci.cabig.caaers.rules.sponsor.study.study_for_ll.astana.astanarules", createdRuleset.getName());
            	assertEquals(1, createdRuleset.getRule().size());
            	assertNotNull(createdRule);
            	
            	Rule ruleToAdd = ruleSet.getRule().get(1);
            	uuid = caaersRulesEngineService.createRuleForSponsorDefinedStudy(ruleToAdd, ruleSetName, studyShortTitle, sponsorName, subject, state);
            	
            	RuleSet existingRuleset = repositoryService.getRuleSet("gov.nih.nci.cabig.caaers.rules.sponsor.study.study_for_ll.astana.astanarules", false);
            	Rule addedRule = repositoryService.getRule(uuid);
            	
            	assertNotNull(existingRuleset);
            	assertNotNull(addedRule);
            	assertEquals(2, existingRuleset.getRule().size());
		        
			}catch(Exception e){
				fail("No exceptions are expected");
			}
		}
	}
	
	/**
	 * Tests method createRuleForInstitution(...) in RulesEngineServiceImpl
	 * New RuleSet New Rule
	 */
	public void testCreateRuleForInstitution(){
		if(ruleSet != null){
        	try{
        		String ruleSetName = "SemanticBitsRules";
        		String institutionName = "National C I";
                String subject = "SemanticBits Rules";
                String state = "Draft";
                Rule ruleToCreate;
                ruleToCreate = ruleSet.getRule().get(0);
            	String uuid = caaersRulesEngineService.createRuleForInstitution(ruleToCreate, ruleSetName, institutionName, subject, state);
            	assertNotNull(uuid);
            	assertNotEquals("", uuid);
            	
            	RuleSet createdRuleset = repositoryService.getRuleSet("gov.nih.nci.cabig.caaers.rules.institution.national_c_i.semanticbitsrules", false);
            	Rule createdRule = repositoryService.getRule(uuid);
            	
            	assertNotNull(createdRuleset);
            	assertEquals("gov.nih.nci.cabig.caaers.rules.institution.national_c_i.semanticbitsrules", createdRuleset.getName());
            	assertNotNull(createdRule);
            	
        	}catch(Exception e){
        		fail("No exceptions are expected " +e.getMessage());
        	}
        }
	}
	
	/**
	 * Tests method createRuleForInstitution(...) in RulesEngineServiceImpl
	 * Add New Rule to an existing RuleSet
	 */
	public void testCreateRuleForInstitutionExistingRuleSet(){
		
        if(ruleSet != null){
        	try{
        		String ruleSetName = "SemanticBitsRules";
        		String institutionName = "National C I";
                String subject = "SemanticBits Rules";
                String state = "Draft";
                Rule ruleToCreate;
                ruleToCreate = ruleSet.getRule().get(0);
            	String uuid = caaersRulesEngineService.createRuleForInstitution(ruleToCreate, ruleSetName, institutionName, subject, state);
            	assertNotNull(uuid);
            	assertNotEquals("", uuid);
            	
            	RuleSet createdRuleset = repositoryService.getRuleSet("gov.nih.nci.cabig.caaers.rules.institution.national_c_i.semanticbitsrules", false);
            	Rule createdRule = repositoryService.getRule(uuid);
            	
            	assertNotNull(createdRuleset);
            	assertEquals("gov.nih.nci.cabig.caaers.rules.institution.national_c_i.semanticbitsrules", createdRuleset.getName());
            	assertNotNull(createdRule);
            	
            	Rule ruleToAdd = ruleSet.getRule().get(1);
            	uuid = caaersRulesEngineService.createRuleForInstitution(ruleToAdd, ruleSetName, institutionName, subject, state);
            	
            	RuleSet existingRuleset = repositoryService.getRuleSet("gov.nih.nci.cabig.caaers.rules.institution.national_c_i.semanticbitsrules", false);
            	Rule addedRule = repositoryService.getRule(uuid);
            	
            	assertNotNull(existingRuleset);
            	assertNotNull(addedRule);
            	assertEquals(2, existingRuleset.getRule().size());
            	
        	}catch(Exception e){
        		fail("No exceptions are expected " +e.getMessage());
        	}
        }
	}
	
	/**
	 * Tests method createRuleForInstitutionDefinedStudy(...) in RulesEngineServiceImpl
	 * New RuleSet and New Rule
	 */
	public void testCreateRuleForInstitutionDefinedStudy(){
		
		if(ruleSet != null){
			try{
				Rule rule = ruleSet.getRule().get(1);
				String ruleSetName = "AstanaRules";
		        String studyShortTitle = "Study For LL";
		        String institutionName = "Astana";
		        String subject = "RuleForSponsorDefinedStudy";
		        String state = "Draft";
		        
		        String uuid = caaersRulesEngineService.createRuleForInstitutionDefinedStudy(rule, ruleSetName, studyShortTitle, institutionName, subject, state);
		        
		        assertNotNull("uuid returned cannot be null", uuid);
		        assertNotEquals("", uuid);
		        
		        RuleSet createdRuleset = repositoryService.getRuleSet("gov.nih.nci.cabig.caaers.rules.institution.study.study_for_ll.astana.astanarules", false);
            	Rule createdRule = repositoryService.getRule(uuid);
            	
            	assertNotNull(createdRuleset);
            	assertEquals("gov.nih.nci.cabig.caaers.rules.institution.study.study_for_ll.astana.astanarules", createdRuleset.getName());
            	assertEquals(1, createdRuleset.getRule().size());
            	assertNotNull(createdRule);
		        
			}catch(Exception e){
				fail("No exceptions are expected");
			}
		}
	}
	
	
	
//	/**
//	 * Tests method createRuleForInstitutionDefinedStudy(...) in RulesEngineServiceImpl
//	 */
//	public void testCreateRuleForInstitutionDefinedStudy(){
//		
//	}
//	
//	/**
//	 * Tests method CreateRuleSetForInstitution(...) in RulesEngineServiceImpl
//	 */
//	public void testCreateRuleSetForInstitution(){
//		
//	}
//	
//	public void testCreateRuleSetForInstitutionDefinedStudy(){
//		
//	}
//	
//	public void testGetRuleSetForInstitution(){
//		
//	}
//	
//	public void testGetRuleSetForSponsor(){
//		
//	}
//	
//	public void testGetRuleSetForSponsorDefinedStudy(){
//		
//	}
//	
//	public void testGetRuleSetForInstitutionDefinedStudy(){
//		
//	}
//	
//	public void testGetRuleSetForInstitutionCachedTrue(){
//		
//	}
//	
//	public void testGetRuleSetForSponsorCachedTrue(){
//		
//	}
//	
//	public void testGetRuleSetForSponsorDefinedStudyCachedTrue(){
//		
//	}
//	
//	public void testGetRuleSetForInstitutionDefinedStudyCachedTrue(){
//		
//	}
//	
//	public void testGetAllRuleSetsForSponsorDefinedStudy(){
//		
//	}
//	
//	public void testGetAllRuleSetsForInstitutionDefinedStudy(){
//		
//	}
//	
//	public void testGetRulesByCategory(){
//		
//	}
//	
//	public void testSaveRulesForInstitution(){
//		
//	}
//	
//	public void testSaveRulesForSponsor(){
//		
//	}
//	
//	public void testSaveRulesForSponsorDefinedStudy(){
//		
//	}
//	
//	public void testSaveRulesForInstitutionDefinedStudy(){
//		
//	}
	
}
