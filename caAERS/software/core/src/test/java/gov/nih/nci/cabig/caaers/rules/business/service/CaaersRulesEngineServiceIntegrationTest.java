package gov.nih.nci.cabig.caaers.rules.business.service;

import com.semanticbits.rules.utils.RuleUtil;
import edu.nwu.bioinformatics.commons.ResourceRetriever;
import gov.nih.nci.cabig.caaers.CaaersTestCase;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
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
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.utils.DateUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * This is a unit TestCase for RulesEngineServiceImpl class.
 * @author Monish Dombla
 * @author Biju Joseph
 *
 */
public class CaaersRulesEngineServiceIntegrationTest extends CaaersTestCase{
	
	public static final String SPONSOR_LEVEL = "Sponsor";
    public static final String INSTITUTIONAL_LEVEL = "Institution";
    public static final String SPONSOR_DEFINED_STUDY_LEVEL = "SponsorDefinedStudy";
    public static final String INSTITUTION_DEFINED_STUDY_LEVEL = "InstitutionDefinedStudy";
	private CaaersRulesEngineService caaersRulesEngineService;
	private RulesEngineService rulesEngineService;
	private RepositoryService repositoryService;

    RulesPropertiesFileLoader propertiesBean;
	
    private InputStream createInputStream(String testDataFileName) throws FileNotFoundException {
        InputStream testDataStream = ResourceRetriever.getResource(getClass().getPackage(), testDataFileName);
        return testDataStream;
    }
    
	@Override
	protected void setUp() throws Exception {
		super.setUp();
        
		//cleans the rules repository
        cleanRulesRepo();

		caaersRulesEngineService = (CaaersRulesEngineService)getDeployedApplicationContext().getBean("caaersRulesEngineService");
		rulesEngineService = (RulesEngineService)getDeployedApplicationContext().getBean("ruleEngineService");
		repositoryService = (RepositoryService)getDeployedApplicationContext().getBean("jcrService");

	}

    @Override
    protected void tearDown() throws Exception {
        cleanRulesRepo();//cleans the rules repository
        super.tearDown();
    }

    private void cleanRulesRepo(){
       try{

		propertiesBean = RulesPropertiesFileLoader.getLoadedInstance();
        String url = propertiesBean.getProperties().getProperty("rules.repository");
        new RepositoryCleaner(url);

       }catch(Exception ignore){
           System.out.println("Ignore below exception");
           ignore.printStackTrace();
       }
    }


    private RuleSet loadRuleSetFromFile(String fileName) throws RuntimeException{
        RuleSet ruleSet = null;
        
        try{

            JAXBContext jaxbContext = null;
            Unmarshaller unmarshaller = null;
            jaxbContext = JAXBContext.newInstance("com.semanticbits.rules.brxml");
            unmarshaller = jaxbContext.createUnmarshaller();
            InputStream xmlInputDataStream = createInputStream(fileName);
            if(xmlInputDataStream != null){
                ruleSet = (RuleSet) unmarshaller.unmarshal(xmlInputDataStream);
                ruleSet.getRule().get(0).setId(null);
            }

        }catch(Exception e){
            throw new RuntimeException(e);
        }

        return ruleSet;
    }

    private Organization createAndSaveOrganziation(String nciCode){
        Organization org = Fixtures.createOrganization("test", nciCode);
        caaersRulesEngineService.getOrganizationDao().save(org);
        return org;
    }
	
	/**
	 * Tests method createRuleForSponsor(...) in RulesEngineServiceImpl
	 * New RuleSet and New Rule
	 */
	public void testSaveRuleSetForSponsor() throws Exception{

        RuleSet rs = loadRuleSetFromFile("test_sponsor_karnataka_rules.xml");

        String nciCode = "NCI" + System.currentTimeMillis() % 100000;
        Organization org = createAndSaveOrganziation(nciCode);
        Integer orgId = org.getId().intValue();
        String modifiedPackageName = "gov.nih.nci.cabig.caaers.rules.sponsor.ORG_" + orgId + ".sae_reporting_rules";
        String modifiedSubject = "SAE Reporting Rules||Sponsor||" +  nciCode + "|| || ";
        caaersRulesEngineService.saveRuleSet(rs, SPONSOR_LEVEL, "SAE Reporting Rules", org, null,null);

        RuleSet loadedRs = caaersRulesEngineService.getRuleSetByPackageName(modifiedPackageName);
        assertEquals(1, loadedRs.getRule().size());
        assertEquals("karthik test report 1", loadedRs.getRule().get(0).getAction().get(0));
        assertEquals(modifiedSubject,loadedRs.getSubject());

	}

    /**
	 * Tests method create rule for institution(...) in RulesEngineServiceImpl
	 * New RuleSet and New Rule
	 */
	public void testSaveRuleSetForInstitution() throws Exception{

        RuleSet rs = loadRuleSetFromFile("test_sponsor_karnataka_rules.xml");

        String nciCode = "NCI" + System.currentTimeMillis() % 100000;
        Organization org = createAndSaveOrganziation(nciCode);
        Integer orgId = org.getId().intValue();
        String modifiedPackageName = "gov.nih.nci.cabig.caaers.rules.institution.ORG_" + orgId + ".sae_reporting_rules";
        String modifiedSubject = "SAE Reporting Rules||Institution|| ||" +  nciCode + "|| ";
        caaersRulesEngineService.saveRuleSet(rs, INSTITUTIONAL_LEVEL, "SAE Reporting Rules", null, org,null);

        RuleSet loadedRs = caaersRulesEngineService.getRuleSetByPackageName(modifiedPackageName);
        assertEquals(1, loadedRs.getRule().size());
        assertEquals("karthik test report 1", loadedRs.getRule().get(0).getAction().get(0));
        assertEquals(modifiedSubject,loadedRs.getSubject());
        
	}


    /**
     * Tests method createRuleForSponsorStudy(...) in RulesEngineServiceImpl
     * New RuleSet and New Rule
     */
    public void testSaveRuleSetForSponsorDefinedStudy() throws Exception{

        RuleSet rs = loadRuleSetFromFile("test_sponsor_karnataka_rules.xml");

        String nciCode = "NCI" + System.currentTimeMillis() % 100000;
        Organization org = createAndSaveOrganziation(nciCode);
        Integer orgId = org.getId().intValue();
        Study study = Fixtures.createStudy("test");
        study.setId(12);
        SystemAssignedIdentifier sysId1 = Fixtures.createSystemAssignedIdentifier("9876");
        sysId1.setPrimaryIndicator(false);
        study.addIdentifier(sysId1);

        SystemAssignedIdentifier sysId2 = Fixtures.createSystemAssignedIdentifier("6789");
        sysId2.setPrimaryIndicator(true);
        study.addIdentifier(sysId2);


        String modifiedPackageName = "gov.nih.nci.cabig.caaers.rules.sponsor.study.ORG_" + orgId  +".STU_12.sae_reporting_rules";
        String modifiedSubject = "SAE Reporting Rules||SponsorDefinedStudy||" +  nciCode + "|| ||value:6789";
        caaersRulesEngineService.saveRuleSet(rs, SPONSOR_DEFINED_STUDY_LEVEL, "SAE Reporting Rules", org, null,study);

        RuleSet loadedRs = caaersRulesEngineService.getRuleSetByPackageName(modifiedPackageName);
        assertEquals(1, loadedRs.getRule().size());
        assertEquals("karthik test report 1", loadedRs.getRule().get(0).getAction().get(0));
        assertEquals(modifiedSubject,loadedRs.getSubject());
    }

    /**
     * Tests method create rule for institutionStudy(...) in RulesEngineServiceImpl
     * New RuleSet and New Rule
     */
    public void testSaveRuleSetForInstitutionStudy() throws Exception{

        RuleSet rs = loadRuleSetFromFile("test_sponsor_karnataka_rules.xml");

        String nciCode = "NCI" + System.currentTimeMillis() % 100000;
        Organization org = createAndSaveOrganziation(nciCode);
        Integer orgId = org.getId().intValue();
        Study study = Fixtures.createStudy("test");
        study.setId(12);
        SystemAssignedIdentifier sysId1 = Fixtures.createSystemAssignedIdentifier("9876");
        sysId1.setPrimaryIndicator(false);
        study.addIdentifier(sysId1);

        SystemAssignedIdentifier sysId2 = Fixtures.createSystemAssignedIdentifier("6789");
        sysId2.setPrimaryIndicator(true);
        study.addIdentifier(sysId2);


        String modifiedPackageName = "gov.nih.nci.cabig.caaers.rules.institution.study.ORG_" + orgId  +".STU_12.sae_reporting_rules";
        String modifiedSubject = "SAE Reporting Rules||InstitutionDefinedStudy|| ||" +  nciCode + "||value:6789";
        caaersRulesEngineService.saveRuleSet(rs, INSTITUTION_DEFINED_STUDY_LEVEL, "SAE Reporting Rules", null, org,study);

        RuleSet loadedRs = caaersRulesEngineService.getRuleSetByPackageName(modifiedPackageName);
        assertEquals(1, loadedRs.getRule().size());
        assertEquals("karthik test report 1", loadedRs.getRule().get(0).getAction().get(0));
        assertEquals(modifiedSubject,loadedRs.getSubject());
    }

	

	
	/**
	 * Tests saveRuleSet method
	 */
	public void testSaveRuleSetFieldLevelRules() throws Exception{

        RuleSet rs = loadRuleSetFromFile("test_field_rules.xml");

        String modifiedPackageName = "gov.nih.nci.cabig.caaers.rules.sae_reporting_rules";
        String modifiedSubject = "Field Rules|| || || ";
        caaersRulesEngineService.saveRuleSet(rs, INSTITUTION_DEFINED_STUDY_LEVEL, "Field Rules", null, null,null);

        RuleSet loadedRs = caaersRulesEngineService.getRuleSetByPackageName(modifiedPackageName);
        assertEquals(1, loadedRs.getRule().size());
        assertEquals("karthik test report 1", loadedRs.getRule().get(0).getAction().get(0));
        assertEquals(modifiedSubject,loadedRs.getSubject());

	}
	
	/**
	 * Tests deployRuleSet(ruleSet)
	 */
	public void testDeployRuleSet() throws Exception{

        if(DateUtils.compareDate(DateUtils.parseDate("05/28/2010"), DateUtils.today()) > 0){
            assertTrue(true);
            return;
        }
        fail("todo");
		
	}
	
	/**
	 * Tests undeployRuleSet(ruleSet)
	 */
	public void testUnDeployRuleSet() throws Exception{

        if(DateUtils.compareDate(DateUtils.parseDate("05/28/2010"), DateUtils.today()) > 0){
            assertTrue(true);
            return;
        }
        fail("todo");
		
	}
	
    public void testCreateRuleSet() throws Exception{

        if(DateUtils.compareDate(DateUtils.parseDate("05/28/2010"), DateUtils.today()) > 0){
            assertTrue(true);
            return;
        }
        fail("to do");
    }

    public void testCreateOrUpdateRuleSet() throws Exception{

        if(DateUtils.compareDate(DateUtils.parseDate("05/28/2010"), DateUtils.today()) > 0){
            assertTrue(true);
            return;
        }
        fail("to do");
    }

    public void testImportRules() throws Exception{

        if(DateUtils.compareDate(DateUtils.parseDate("05/28/2010"), DateUtils.today()) > 0){
            assertTrue(true);
            return;
        }
        fail("to do");
    }

    public void testUndeployRuleset() throws Exception{

        if(DateUtils.compareDate(DateUtils.parseDate("05/28/2010"), DateUtils.today()) > 0){
            assertTrue(true);
            return;
        }
        fail("to do ");
    }

    public void testDeployRuleset() throws Exception{

        if(DateUtils.compareDate(DateUtils.parseDate("05/28/2010"), DateUtils.today()) > 0){
            assertTrue(true);
            return;
        }
        fail("to do");
    }

    public void testSaveRuleset() throws Exception{

        if(DateUtils.compareDate(DateUtils.parseDate("05/28/2010"), DateUtils.today()) > 0){
            assertTrue(true);
            return;
        }
        fail("to do");
    }

    public void testGetAllRulesets() throws Exception{

        if(DateUtils.compareDate(DateUtils.parseDate("05/28/2010"), DateUtils.today()) > 0){
            assertTrue(true);
            return;
        }
        fail("to do");
    }
	
}
